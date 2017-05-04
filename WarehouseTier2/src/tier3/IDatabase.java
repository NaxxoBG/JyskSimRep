package tier3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabase extends Remote {
	static final String HOST = "ec2-54-228-182-57.eu-west-1.compute.amazonaws.com";
	static final String PORT = "5432";  
	static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/";

	ArrayList<Object[]> query(String sql, Object... args) throws SQLException, RemoteException;
	int update(String sql, Object... args) throws SQLException, RemoteException;
}