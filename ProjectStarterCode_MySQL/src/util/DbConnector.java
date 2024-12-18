/**
 * 
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



/**
 * @author owens
 *
 */
public class DbConnector {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	/*
	 *  Alter the details below to match your own connection details
	 */
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/projectstarter"; // NOTE: DB name included!
	
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	// Have we called the registerOjdbcDriver() method?
	private static boolean registered = false;
	
	/**
	 * 
	 */
	public DbConnector() {
		// Below: only required for older versions of Java
		DbConnector.registerOjdbcDriver();
	}
	
	
	/**
	 * Check that the Oracle JDBC driver class can be discovered
	 *   Needed only for older Java versions which won't otherwise
	 *   execute the static code in the class as we need.
	 *   
	 * NOTE: If this is called and it fails then the application will exit.
	 */
	public static void registerOjdbcDriver(){
		if(registered) return;
		
		System.out.println("Registering ojdbc driver...");
		
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Driver registration was unsuccessful - ensure that:\n"
					+ " * MySQL JDBC .jar file is on the Eclipse build path (it comes with this code; it should be there already)\n"
					+ " * MySQL JDBC .jar is ALSO available to Tomcat (easiest way: copy the file into Tomcat's /lib directory).");
			e.printStackTrace();
			System.exit(1);
		}
		registered = true; // Don't do it again
		
		System.out.println("OJDBC driver registered successfully.");
	}
	
	/**
	 * Obtain a database connection
	 * @return connection
	 * @throws SQLException if the underlying connection does, or if the resultant connection is incomplete (i.e. null)
	 */
	public static Connection getConnection() throws SQLException{
		registerOjdbcDriver();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		}
		catch(SQLException sqle){
			System.err.println("Unable to connect. Before anything else, check that you have edited DbConnector.java and changed:\n"
					+ " * The value of DB_URL (is the port number correct? Is the service running? Is your database called projectstarter: check the script you used)\n"
					+ " * The value of USER (root is probably OK at first, but you might want a dedicated user)\n"
					+ " * The value of PASSWORD (if you have forgotten your password, check the settings you used in earlier weeks)");
			System.exit(1);
		}
		
		if(connection == null) {
			throw new SQLException("Connection failed: null connection returned from DriverManager");
		} 
		
		return connection;
	}

	/**
	 * Execute a query and return the result
	 * @param sqlQuery String containing the SQL query (no semicolon at the end)
	 * @return ResultSet containing the result of the query's execution
	 * @throws SQLException if there is a problem creating the statement or executing the query
	 */
	public static ResultSet _runSqlQuery(String sqlQuery) throws SQLException {
		ResultSet rs = null;
		
		/*
		 * The creation of the statement and execution of the query can
		 *  cause SQLException to be thrown.
		 */
		Statement stmt = DbConnector.getConnection().createStatement();
		rs = stmt.executeQuery(sqlQuery);
		
		return rs;
	}
	
	
	/**
	 * Execute an SQL statement via a CallableStatement (e.g. to run SQL/PSM)
	 * DOESN'T return any result... perhaps it could? Should?
	 * @param sqlQuery String containing the SQL query (no semicolon at the end)
	 * @return result of the statement's execution
	 * @throws SQLException if there is a problem creating the statement or executing the query
	 */
	public static ResultSet runSqlCallable(String sql) throws SQLException {
		/*
		 * The creation of the statement and execution of the query can
		 *  cause SQLException to be thrown.
		 */
		CallableStatement stmt = DbConnector.getConnection().prepareCall(sql);
		return stmt.executeQuery();
	}
	
	
	/**
	 * Execute an SQL statement via a PreparedStatement (e.g. to run SQL/PSM)
	 * DOESN'T return any result... perhaps it could? Should?
	 * @param sqlQuery String containing the SQL query (no semicolon at the end)
	 * @return result of the statement's execution
	 * @throws SQLException if there is a problem creating the statement or executing the query
	 */
	public static ResultSet runSqlPrepared(String sql) throws SQLException {
		/*
		 * The creation of the statement and execution of the query can
		 *  cause SQLException to be thrown.
		 */
		PreparedStatement stmt = DbConnector.getConnection().prepareStatement(sql);
		return stmt.executeQuery();
	}
	
	
	/**
	 * Run an SQL script; file name/path given via parameter script
	 * Errors are considered on a per-statement basis (an error will
	 *   not prevent the rest of the script from being parsed/executed).
	 * @param script path/to/file.extension containing script
	 */
	protected static void runSqlScript(String script) {
		/*
		 * Below: try-with-resources
		 *   The Scanner instance will be automatically closed at the end
		 *   of the try block.
		 */
		try (
				Scanner scanner = new Scanner(new FileInputStream(script));
		) {		
			String stmt = "";
			while(scanner.hasNextLine()) {
				String tmp = scanner.nextLine().trim();
				if(tmp.endsWith("/")) {
					stmt += ' ' + tmp.substring(0,tmp.length()-1);
					stmt = stmt.trim();
					try {
						if(stmt.toUpperCase().startsWith("BEGIN") || stmt.toUpperCase().startsWith("DECLARE")){
							DbConnector.runSqlCallable(stmt);
						}
						else {
							DbConnector.runSqlPrepared(stmt);
						}
					} catch(SQLException e) {
						System.err.println("Error in script:");
						System.err.println(stmt);
						e.printStackTrace();
					}
					stmt="";
				} else {
					stmt += ' '+ tmp;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Unable to find file - cannot execute script " + script);
			e.printStackTrace();
			System.exit(1);
		}
	}
		
	
}
