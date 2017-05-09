package tier2.crane;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import tier2.database.DatabaseRemote;
import tier2.model.Good;
import tier2.model.Pallet;
import tier2.model.RequestedGood;

public class CraneMonitor {
	private static CraneMonitor crane;
	private Queue<Pallet> pallets;
	private Queue<RequestedGood> goods;

	private CraneMonitor() {
		this.pallets = new ArrayDeque<>();
		this.goods = new ArrayDeque<>();
		Thread thread = new Thread(new CraneThread(this));
		thread.start();
	}

	public synchronized static CraneMonitor getInstance() {
		if (crane == null) {
			crane = new CraneMonitor();
		} 
		return crane;
	}

	public synchronized boolean addToPallets(Pallet pallet) {
		System.out.println(pallet.getId());
		System.out.println(pallet.getCount());
		System.out.println(pallet.getGood());
		pallet.setPickStattioId(-1);
		pallets.add(pallet);
		notifyAll();
		return true;
	}

	public synchronized void addToRequestedGoods(RequestedGood reqGood) {
		System.out.println("addToRequestedGoods " + reqGood.toString());
		goods.add(reqGood);
		notifyAll();
	}

	public synchronized void putPalletOnShelf() {
		while (isPalletQueueEmpty() && isReqQueueEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		if(!isPalletQueueEmpty()){
			Pallet pallet = pallets.poll();
			if (!DatabaseRemote.insertPallet(pallet)) {
				addToPallets(pallet);
			}
			try {
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
			} catch (InterruptedException e) {}
		}
		if(!isReqQueueEmpty()){
			RequestedGood good = goods.poll();
			Pallet[] palletsToPick = DatabaseRemote.getPalletsForGood(good);
			for (Pallet pallet : palletsToPick) {
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
					DatabaseRemote.removePallet(pallet.getId());
					pallet.setPickStattioId(good.getStationId());
					pallets.add(pallet);
					good.setFinished(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized Pallet[] getPallets(int stationId) {
		List<Pallet> pal = new ArrayList<>();
		for (Pallet p : pallets) {
			if (p.getPickStationId() == stationId){
				pal.add(p);
			}
		}
		return pal.toArray(new Pallet[pal.size()]);
	}

	private boolean isReqQueueEmpty() {
		return goods.isEmpty();
	}

	private boolean isPalletQueueEmpty() {
		for(Pallet p : pallets){
			if(p.getPickStationId() == -1) return false;
		}
		return true;
	}
}