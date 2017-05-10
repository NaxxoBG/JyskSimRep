package tier3.database;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import shared.iremote.IDatabase;

public class DatabaseServer {

	public static void main(String[] args) {
		try {
			Database db = new Database("d8cs1ce2fsi9io");
			try {
				IDatabase shared = (IDatabase) UnicastRemoteObject.exportObject(db, 0);
				LocateRegistry.createRegistry(1099);
				Naming.bind("Warehouse", shared);
				System.out.println("Remote object is bound");
			} catch (Exception e) {
				System.out.println("Failed to bind remote object!\n" + e.getLocalizedMessage());
				System.exit(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
