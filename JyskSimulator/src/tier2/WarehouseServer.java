package tier2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import model.Good;
import model.Pallet;
import tier3.Database;
import tier3.IDatabase;

public class WarehouseServer {
	private IDatabase db;
	
	private IDatabase getDatabase(){
		if(db == null){
			try {
				db = (IDatabase) Naming.lookup("Warehouse");
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		return db;
	}

	public boolean insertGood(Good good) {
		try {
			getDatabase().update("INSERT INTO jysksim.good VALUES(?, ?, ?);", good.getPalletId(), good.getManufacturer(),
					good.getName());
			return true;
		} catch (RemoteException | SQLException | NullPointerException e) {
			db = null;
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertPallet(Pallet pallet){
		IDatabase db = getDatabase();
		
		return true;
	}
}