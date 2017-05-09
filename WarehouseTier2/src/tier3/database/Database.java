package tier3.database;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import shared.iremote.IDatabase;

public class Database implements IDatabase {
	static final String HOST = "ec2-54-228-182-57.eu-west-1.compute.amazonaws.com";
	static final String PORT = "5432";
	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/";
	private String user = "touqxjvwqyknnn";
	private String pw = "365e3f6aa75920301e66df229e9eba39a87016f78dd331c0975cd2c76c5e8a21";
	private Connection connection = null;

	public Database(String driver, String url, String user, String pw) throws ClassNotFoundException {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pw = pw;
		Class.forName(driver);
	}

	public Database(String databaseName, String user, String pw) throws ClassNotFoundException {
		this.url = url + databaseName;
		this.user = user;
		this.pw = pw;
		Class.forName(driver);
	}

	public Database(String databaseName) throws ClassNotFoundException {
		this.url = url + databaseName + "?sslmode=require";
		System.out.println(this.url);
		Class.forName(driver);
		System.out.println(driver + " loaded");
	}

	public ArrayList<Object[]> query(String sql, Object... args) throws SQLException {
		openDatabase();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}

			System.out.println(statement.toString());

			resultSet = statement.executeQuery();
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			while (resultSet.next()) {
				Object[] elements = new Object[resultSet.getMetaData().getColumnCount()];
				for (int i = 0; i < elements.length; i++) {
					elements[i] = resultSet.getObject(i + 1);
				}
				list.add(elements);
			}
			return list;
		} catch (PSQLException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			closeDatabase();
		}
	}

	public int update(String sql, Object... args) throws SQLException {
		openDatabase();
		int rowCount = 0;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}

			System.out.println(statement.toString());

			rowCount = statement.executeUpdate();
			return rowCount;
		} catch (PSQLException ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			if (statement != null)
				statement.close();
			closeDatabase();
		}
	}

	private void openDatabase() throws SQLException {
		connection = DriverManager.getConnection(url, user, pw);
	}

	private void closeDatabase() throws SQLException {
		connection.close();
	}

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