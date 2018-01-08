import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSetMetaData;

public class DatabaseConnector {
	
	/* Database configuration */
	private static String DB_NAME = "jdbc:mysql://localhost:3306/project3";
	private static String DB_USERNAME = "root";
	private static String DB_PASSWORD = "root";
	private static Connection conn;
	
	/* Open connection function */
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("Class not found "+ e);
		}

		try {
			conn = DriverManager.getConnection(DB_NAME, DB_USERNAME, DB_PASSWORD);
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		
		return conn;
	}

	/* Execute SQL query function */
	public ResultSet querySQL(String query)  {
		openConnection();
		ResultSet result = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			result = statement.executeQuery();
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
		}

		return result;
	}
	
	/* Print result function (optional) */
	public static void printResultSet(ResultSet rs) {
		try {
		    ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		    int columnsNumber = rsmd.getColumnCount();
		    while (rs.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(" | ");
		            System.out.print(rs.getString(i));
		        }
		        System.out.println("");
		    }
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
	}
	
}