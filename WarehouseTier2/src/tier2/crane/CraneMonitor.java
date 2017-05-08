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
		pallets.add(pallet);
		notifyAll();
		return true;
	}

	public synchronized void addToRequestedGoods(Good good, int count) {
		goods.add(new RequestedGood(good, count));
		notifyAll();
	}
	
	public synchronized void putPalletOnShelf() {
		while (pallets.isEmpty() && goods.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		Pallet pallet = pallets.poll();
		if (!DatabaseRemote.insertPallet(pallet)) {
			addToPallets(pallet);
		}
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyAll();
	}

	public synchronized void getGoodForOrder() {
		RequestedGood good = goods.poll();
		Pallet[] palletsToPick = DatabaseRemote.getPalletsForGood(good);
		for (Pallet pallet : palletsToPick) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
				DatabaseRemote.removePallet(pallet.getId());
				pallet.setPickStattioId(good.getStationId());
				pallets.add(pallet);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized Pallet[] getPallets(int stationId){
		List<Pallet> pal = new ArrayList<>();
		for(Pallet p : pallets){
			if(p.getPickStattioId() == stationId){
				pal.add(p);
			}
		}
		return pal.toArray(new Pallet[pal.size()]);
	}
	
	public boolean isReqQueueEmpty() {
		return goods.isEmpty();
	}
	
	public boolean isPalletQueueEmpty() {
		return pallets.isEmpty();
	}
}