import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main (String[] args) throws SQLException {
		ResultSet rs = new DatabaseConnector().querySQL("SELECT * FROM users");
		DatabaseConnector.printResultSet(rs);
		new Login().setVisible(true);		
	}
}
