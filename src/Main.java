import java.sql.ResultSet;

public class Main {
	public static void main (String[] args) {
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.querySQL("SELECT * FROM users");
		DatabaseConnector.printResultSet(rs);
	}
}
