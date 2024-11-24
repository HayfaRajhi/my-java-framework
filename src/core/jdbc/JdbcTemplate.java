package core.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {
	private DataSource dataSource;
	private Connection con =null;
	
	public JdbcTemplate(DataSource dataSource) {
		
		this.dataSource=dataSource;
		
	}
	
	
	public  void createNewTable(String sql,String tableName) {
		try {
		Connection connection = DataSourceUtils.getConnection(dataSource);

        // Créer l'objet Statement
        Statement statement = connection.createStatement();

        statement.execute(sql);
		 statement.close();
         connection.close();

         System.out.println("Table '" + tableName + "' has been created successfully.");
     } catch (SQLException e) {
         e.printStackTrace();
     }
	}

	/* public void createNewTable(String sql, String tableName) {
	        Connection connection = null;
	        Statement statement = null;

	        try {
	            connection = DataSourceUtils.getConnection(dataSource);

	            // Check if the connection is open before creating the statement
	            if (connection != null && !connection.isClosed()) {
	                statement = connection.createStatement();
	                statement.execute(sql);
	                System.out.println("Table '" + tableName + "' has been created successfully.");
	            } else {
	                System.err.println("Connection is closed or null.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the statement and connection in the finally block
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null && !connection.isClosed()) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }*/

	
}
