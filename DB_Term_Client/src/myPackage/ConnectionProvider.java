package myPackage;
import java.sql.*;

public class ConnectionProvider {

	private static String username = "postgres";
	private static String password = "ghdyd1";
	private static String url = "jdbc:postgresql://localhost:4150/TermDatabase";
												 //host : port / database 
	private static Connection conn = null;
	
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch blocke.printStackTrace();
		}
		return conn;
	}
}

