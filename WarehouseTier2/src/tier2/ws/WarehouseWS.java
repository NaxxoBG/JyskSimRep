package tier2.ws;

import tier2.crane.CraneMonitor;
import tier2.model.Good;
import tier2.model.Order;
import tier2.model.Pallet;

public class WarehouseWS {

	public boolean insertPallet(Pallet pallet) {
		return CraneMonitor.getInstance().addToPallets(pallet);
	}

	public Pallet[] executeOrder(Order order) {
		return null;
	}

		public static void main(String[] args) {
			Pallet pallet = new Pallet(5, 10, new Good(2, "cax", "csd"));
			System.out.println(CraneMonitor.getInstance().addToPallets(pallet));
		}
}