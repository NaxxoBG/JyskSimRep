package tier2.crane;

import java.util.ArrayDeque;
import java.util.Queue;

import tier2.database.DatabaseRemote;
import tier2.model.Pallet;

public class CraneMonitor {
	private static CraneMonitor crane;
	private Queue<Pallet> pallets;

	private CraneMonitor() {
		this.pallets = new ArrayDeque<>(15);
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
		System.out.println(pallets);
		if (pallets.offer(pallet)) {
			notifyAll();
			return true;
		}
		return false;
	}

	public synchronized void putPalletOnShelf() {
		while (pallets.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}

		Pallet pallet = pallets.poll();
		if (!DatabaseRemote.insertPallet(pallet)) {
			addToPallets(pallet);
		}
		notifyAll();
	}
}