package tier2.database;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shared.iremote.IDatabase;
import tier2.algo.AlgoPick;
import tier2.model.Good;
import tier2.model.Pallet;
import tier2.model.RequestedGood;

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

	public static boolean removePallet(int palletId) {
		IDatabase database = getDatabase();
		int result = 0;
		try {
			result = database.update("delete from jysksim.pallet where id = palletId;", palletId);
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	public static int getGoodId(String manufacturer, String name) {
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

	public static Pallet[] getPalletsForGood(RequestedGood good) {
		IDatabase database = getDatabase();
		try {
			ArrayList<Object[]> res = database.query("SELECT id, count from jysksim.pallet where goodId = ?;", getGoodId(good.getManufacturer(), good.getName()));
			List<Pallet> convertedPalsFromQ = new ArrayList<>();
			for (Object[] objects : res) {
				convertedPalsFromQ.add(new Pallet((int) objects[0], (int) objects[1], good));
			}
			convertedPalsFromQ = AlgoPick.getBestPallets(convertedPalsFromQ, good.getCount());
			return convertedPalsFromQ.toArray(new Pallet[convertedPalsFromQ.size()]);
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}