package core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtils {

	private static Connection con;
	private static DataSource dataSource;

	private DataSourceUtils() throws SQLException {
		try {
			Class.forName(dataSource.getDriverClassName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
		System.out.println("Successfully Connected!");
	}

	public static Connection getConnection(DataSource dSource) throws SQLException {
		if (con == null || con.isClosed()) { // con.isClosed zedha khatr ken yehsb con null w table thenya lezm twali
												// con null besh yecreha li hya wa9t n3awed nrunni tsyr
			try {

				dataSource = dSource;
				new DataSourceUtils();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}

}
