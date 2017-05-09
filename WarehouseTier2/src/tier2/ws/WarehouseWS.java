package tier2.ws;

import tier2.crane.CraneControl;
import tier2.model.Good;
import tier2.model.Pallet;
import tier2.model.RequestedGood;

public class WarehouseWS {

	private static CraneControl monitor = CraneControl.getInstance();
	
	public boolean insertPallet(Pallet pallet) {
		System.out.println("Insert pallet: " + pallet);
		return monitor.addToPallets(pallet);	
	}

	public static Pallet[] executeOrder(Good good, int count, int stationId) {
		System.out.println("Execute order: " + stationId + ", " + good.toString() + ", " + count);
		RequestedGood reqGood = new RequestedGood(good, count, stationId);
		monitor.addToRequestedGoods(reqGood);
		
		while(!reqGood.isFinished()){
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