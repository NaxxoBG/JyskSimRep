package tier2.ws;

import tier2.crane.CraneMonitor;
import tier2.model.Good;
import tier2.model.Pallet;
import tier2.model.RequestedGood;

public class WarehouseWS {

	private static CraneMonitor monitor = CraneMonitor.getInstance();
	
	public boolean insertPallet(Pallet pallet) {
		return monitor.addToPallets(pallet);
		
	}

	public  static Pallet[] executeOrder(Good good, int count, int stationId) {
		RequestedGood reqGood = new RequestedGood(good, count, stationId);
		
		monitor.addToRequestedGoods(reqGood);
		
		while(!reqGood.isFinished()){
			System.out.println(reqGood.isFinished());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Pallet[] p = monitor.getPallets(stationId);
		
		return p;
	}
	
	public static void main(String[] args){
		Good good = new Good("Denso", "jeans");
		Pallet[] pallets = executeOrder(good, 100, 1);
		if(pallets != null){
			System.out.println(pallets.length);
		}
		
		for(Pallet p : pallets){
			System.out.println(p.toString());
		}
	}
}