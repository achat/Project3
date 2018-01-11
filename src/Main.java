import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main (String[] args) throws SQLException {
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.querySQL("SELECT * FROM users");
		DatabaseConnector.printResultSet(rs);
		new SelectMovie().setVisible(true);;
	}
}
