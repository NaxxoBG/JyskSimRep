package tier2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import tier3.IDatabase;

public class WarehouseServer {
	private IDatabase db;

	public WarehouseServer() {
		try {
			db = (IDatabase) Naming.lookup("Warehouse");	
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean insertGood(Good good) {
		WarehouseServer s = new WarehouseServer();
		try {
			s.db.update("INSERT INTO jysksim.good VALUES(?, ?, ?);", good.getManufacturer(), good.getName(), good.getPalletId());
			return true;
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(WarehouseServer.insertGood(new Good("Danpo", "Chicken wings", 443)));
		System.out.println(WarehouseServer.insertGood(new Good("Mars", "Chocobar", 143)));
	}
}
