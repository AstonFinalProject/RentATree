

package db;

import java.util.*;
import java.sql.*;

public class DBConnect {
	private Connection conn;
	public Connection getConn() {
		return conn;
	}

	private String database, username, password, url;
	private static boolean registered;
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public DBConnect() {
		conn = null;
		this.database  = "rentatree";
		this.username = "Pa$$word123";
		this.password = "pass";
		this.url = "jdbc:mysql://localhost:3306/"+this.database+"?useTimezone=true&serverTimezone=GMT";
		//System.out.println(this.url);
		DBConnect.registerOjdbcDriver();
		this.connect();
	}
	
	public void connect() {
		try {
			this.conn = DriverManager.getConnection(
					this.url, 
					this.username, 
					this.password);
			
		} catch (SQLException e) {
			//System.out.println("Connection failed.");
			e.printStackTrace();
			return;
		}

		if (conn != null) {
			//System.out.println("Connection established.");
		}
		else {
			System.out.println("Connection null still.");
		}
	}
	
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
	
	
	public ResultSet runQuery (String sql){
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.execute();			
			return pst.getResultSet();
		} catch (SQLException e) {
			System.out.println(sql + "\n failed to run.");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int execSP(ArrayList<String> params, String sql) {
		int output = -1;
		try {
			if(this.conn==null) {
				this.connect();
			}
			CallableStatement cs = this.conn.prepareCall(sql);
			int i = 1;
			for(String p: params) {
				cs.setString(i, p);
				i++;
			}
			cs.registerOutParameter(i, Types.INTEGER);
			cs.executeUpdate();
			output = cs.getInt(i);
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return output;
	}
	

	
	public void printResult(ResultSet rs){
		try {
			if(rs==null) {
				return;
			}
			ArrayList<String> columns = getColumnNames(rs);
			while (rs.next()) {
				for(String c: columns) {
					System.out.println(c + ":" + rs.getString(c));
				}
				System.out.println();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getColumnNames(ResultSet rs){
		ArrayList<String> columns = new ArrayList<String>();
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for(int i=1; i<=columnCount; i++) {
				columns.add(rsmd.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return columns;
	}
	
	public void close() {
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}
	
	// exec prep statement for all strings 
	public ResultSet executePreparedStatement(String sql, String[] args) {
		try {
			ResultSet rs;
			PreparedStatement s = this.conn.prepareStatement(sql);
			int i = 1;
			for(String arg: args) {
				s.setString(i, arg);
				i++;
			}
			rs = s.executeQuery();
			return rs;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public ResultSet executeCallableStatement(CallableStatement cs) {
		try {
			ResultSet rs = cs.executeQuery();
			return rs;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
			
	public CallableStatement getCallableStatement(String sql) {
		try {
			if(this.conn==null) {
				this.connect();
			}
			return this.conn.prepareCall(sql);
		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
}
