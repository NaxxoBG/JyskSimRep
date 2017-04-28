package tier2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Good;
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
			s.db.update("INSERT INTO jysksim.good VALUES(?, ?, ?);", good.getPalletid(), good.getManufacturer(), good.getName());
			return true;
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(WarehouseServer.insertGood(new Good(443, "Chicken wings", "Danpo")));
		System.out.println(WarehouseServer.insertGood(new Good(143, "Chocobar", "Mars")));

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JyskSimulator");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Good good = new Good();
		good.setPalletid(1047);
		good.setManufacturer("Yogurth");
		good.setName("Yoggi");
		em.persist(good);
		try {
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emfactory.close();
		}
	}
}