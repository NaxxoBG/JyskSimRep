package iremote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabase extends Remote {
	ArrayList<Object[]> query(String sql, Object... args) throws SQLException, RemoteException;
	int update(String sql, Object... args) throws SQLException, RemoteException;
}