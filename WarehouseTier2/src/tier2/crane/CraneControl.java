package tier2.crane;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import tier2.algo.BiggestAlgo;
import tier2.algo.IPickAlgo;
import tier2.database.DatabaseRemote;
import tier2.model.Pallet;
import tier2.model.RequestedGood;

public class CraneControl {
	private static CraneControl crane;
	private Queue<Pallet> pallets;
	private Queue<RequestedGood> goods;

	private IPickAlgo pickUpAlgo;

	private CraneControl() {
		this.pallets = new ArrayDeque<>();
		this.goods = new ArrayDeque<>();
		pickUpAlgo = new BiggestAlgo();
		Thread thread = new Thread(new Crane(this));
		thread.start();
		System.out.printf("|OPERATION:|%-15.15s|%-30.30s|%-6s|\n", "Manufacturer", "Name", "Count");
	}

	public synchronized static CraneControl getInstance() {
		if (crane == null) {
			crane = new CraneControl();
		}
		return crane;
	}

	public synchronized boolean addToPallets(Pallet pallet) {
		pallet.setPickStationId(-1);
		pallets.add(pallet);
		notifyAll();
		return true;
	}

	public synchronized void addToRequestedGoods(RequestedGood reqGood) {
		goods.add(reqGood);
		notifyAll();
	}

	public synchronized void putPalletOnShelf() {
		while (isPalletQueueEmpty() && isReqQueueEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if (!isPalletQueueEmpty()) {
			Pallet pallet = pallets.poll();
			if (!DatabaseRemote.insertPallet(pallet)) {
				addToPallets(pallet);
			}
			try {
				System.out.printf("|INSERTED: |%-15.15s|%-30.30s|%-6d|\n", pallet.getGood().getManufacturer(),
						pallet.getGood().getName(), pallet.getCount());
				Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void getGoodForOrder() {
		while (isReqQueueEmpty() && isPalletQueueEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if (!isReqQueueEmpty()) {
			RequestedGood good = goods.poll();
			good.setGoodid(DatabaseRemote.getGoodId(good.getManufacturer(), good.getName()));
			List<Pallet> palletsToPick = pickUpAlgo.getBestPallets(DatabaseRemote.getPallets(good), good.getCount());
			for (Pallet pallet : palletsToPick) {
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
					DatabaseRemote.removePallet(pallet.getId());
					pallet.setPickStationId(good.getStationId());
					pallets.add(pallet);

					System.out.printf("|RETRIEVED:|%-15.15s|%-30.30s|%-6d|\n", pallet.getGood().getManufacturer(),
							pallet.getGood().getName(), pallet.getCount());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			good.setFinished(true);
		}
	}

	public synchronized Pallet[] getPallets(int stationId) {
		List<Pallet> pal = new ArrayList<>();
		for (Pallet p : pallets) {
			if (p.getPickStationId() == stationId) {
				pal.add(p);
				pallets.remove(p);
			}
		}
		return pal.toArray(new Pallet[pal.size()]);
	}

	private boolean isReqQueueEmpty() {
		return goods.isEmpty();
	}

	private boolean isPalletQueueEmpty() {
		for (Pallet p : pallets) {
			if (p.getPickStationId() == -1) {
				return false;
			}
		}
		return true;
	}
}