package tier2.ws;

import tier2.crane.CraneMonitor;
import tier2.model.Good;
import tier2.model.Pallet;

public class WarehouseWS {

	private static CraneMonitor monitor = CraneMonitor.getInstance();
	
	public boolean insertPallet(Pallet pallet) {
		return monitor.addToPallets(pallet);
		
	}

	public Pallet[] executeOrder(Good good, int count, int stationId) {
		monitor.addToRequestedGoods(good, count);
		//wait and magicall be notified
		return monitor.getPallets(stationId);
	}
}