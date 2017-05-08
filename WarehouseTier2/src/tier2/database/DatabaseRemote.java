package tier2.database;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import shared.iremote.IDatabase;
import tier2.model.Good;
import tier2.model.Pallet;

public class DatabaseRemote {

	private static IDatabase dbProxy;

	private static IDatabase getDatabase() {
		if (dbProxy == null) {
			try {
				dbProxy = (IDatabase) Naming.lookup("Warehouse");
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		return dbProxy;
	}

	public static boolean insertPallet(Pallet pallet){
		IDatabase database = getDatabase();
		int result;
		try {
			Good good = pallet.getGood();
			int goodid = getGoodId(good.getManufacturer(), good.getName());
			if (goodid == -1) {
				insertGood(good);
				goodid = getGoodId(good.getManufacturer(), good.getName());
			}
			result = database.update("INSERT INTO jysksim.pallet (count, goodid) VALUES(?, ?);", pallet.getCount(), goodid);
		} catch (RemoteException | SQLException | NullPointerException e) {
			dbProxy = null;
			e.printStackTrace();
			return false;
		} 
		return result > 0;
	}

	private static boolean insertGood(Good good) {
		IDatabase database = getDatabase();
		int result;
		try {
			result = database.update("INSERT INTO jysksim.good (manufacturer, name) VALUES(?, ?);", good.getManufacturer(), good.getName());
		} catch (RemoteException | SQLException | NullPointerException e) {
			dbProxy = null;
			e.printStackTrace();
			return false;
		} 
		return result > 0;
	}
	
	private static int getGoodId(String manufacturer, String name) {
		IDatabase database = getDatabase();
		try {
			ArrayList<Object[]> result = database.query("Select good_id from jysksim.good where jysksim.good.manufacturer like ? and jysksim.good.name like ?", manufacturer, name);
			if (result.size() < 1) {
				return -1;
			}
			return (int) result.get(0)[0];
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return -1;
	}
}