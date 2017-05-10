package tier2.ws;

import tier2.crane.CraneControl;
import tier2.model.Good;
import tier2.model.Pallet;
import tier2.model.RequestedGood;

public class WarehouseWS {

	private static CraneControl monitor = CraneControl.getInstance();

	public boolean insertPallet(Pallet pallet) {
		return monitor.addToPallets(pallet);
	}

	public static Pallet[] retrievePallets(Good good, int count, int stationId) {
		RequestedGood reqGood = new RequestedGood(good, count, stationId);
		monitor.addToRequestedGoods(reqGood);

		while (!reqGood.isFinished()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Pallet[] p = monitor.getPallets(stationId);
		return p;
	}
}