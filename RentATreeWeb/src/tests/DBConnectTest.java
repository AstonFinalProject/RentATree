package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import db.DBConnect;

class DBConnectTest {

	@Test
	void test() throws SQLException {
		DBConnect db;
		db = new DBConnect();
		//db.connect();
		
		
		Connection conn = db.getConn();
		assertEquals(true, conn.isValid(0)); // check db connection has worked
		
		
		db.close();
		assertEquals(false, conn.isValid(0)); // check db connection has been terminated
		
		db.connect();
		Connection conn2 = db.getConn();
		assertEquals(true, conn2.isValid(0)); // check db connection has been re-instated
	}

}
