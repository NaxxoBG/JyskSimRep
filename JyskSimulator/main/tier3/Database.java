package tier3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

public class Database implements IDatabase {
	private String driver = "org.postgresql.Driver";
	private String url = URL;
	private String user = "postgres";
	private String pw = "postgres";
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
		this.url = url + databaseName;
		System.out.println (this.url);
		Class.forName(driver);
		System.out.println (driver + " loaded");
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
			rowCount = statement.executeUpdate();
			return rowCount;
		} catch (PSQLException ex) {
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
}