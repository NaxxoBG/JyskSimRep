package tier2.ws;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import model.Good;
import model.Pallet;
import tier3.IDatabase;

public class DatabaseRemote {

	
	private static IDatabase dbProxy;
	
	private static IDatabase getDatabase(){
		if(dbProxy == null){
			try {
				dbProxy = (IDatabase) Naming.lookup("Warehouse");
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		return dbProxy;
	}
	
	public static boolean insertGood(Good good){
		IDatabase database = getDatabase();
		int result;
		try {
			result = database.update("INSERT INTO jysksim.good VALUES(?, ?, ?);", good.getGoodId(), good.getManufacturer(),
					good.getName());
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
			result = database.update("INSERT INTO jysksim.pallet VALUES(?, ?, ?);", pallet.getId(), pallet.getCount(),
					pallet.getGood().getGoodId());
		} catch (RemoteException | SQLException | NullPointerException e) {
			dbProxy = null;
			e.printStackTrace();
			return false;
		} 
		return result > 0;
	}

}
