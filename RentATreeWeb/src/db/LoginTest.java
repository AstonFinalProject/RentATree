package db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

class LoginTest {

	@Test
	void test() {
		Login l = new Login("Test999", "Test999");
		int success = l.loginUser();
		assertNotEquals(success, 1); // should reject as no user Test999 with password Test999 in database.
		
		// add test user Harison with password Pa$$word123
		DBConnect db;
		db = new DBConnect();
		db.connect();
		String newuserstr = "CALL createNewUser('Harison','harison@Test.com', 'Harison', 'Wright', 10, 'Lane Street', 'S1 0PD', '07657865456', 'Pa$$word123')";
		ResultSet output = db.runQuery(newuserstr);
		System.out.println(output);
		
		Login l2 = new Login("Harison", "Test");
		int success2 = l2.loginUser();
		assertNotEquals(success2, 1); // should reject as user Harison as password is not Test
		
		Login l3 = new Login("Harison", "Pa$$word123");
		int success3 = l3.loginUser();
		assertEquals(success3, 1); // should pass
	
	}

}
