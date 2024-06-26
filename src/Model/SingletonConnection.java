package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	//Database connection URL
	private final String url = "jdbc:mysql://localhost:3306/carrentaloffice?useSSL=false";
	//Username
	private final String user = "root";
	//Passowrd, in my case i don t have a password
	private final String pass = "root";
	//Connection object
	private static Connection connection;

	//Private constructor
	private SingletonConnection() {
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//This method returns the connection instance if already created, or create an instance
	public static Connection getInstance() {
		if(connection == null)
			new SingletonConnection();
		return connection;
	}

	public void finalize() throws Throwable {

	}

}