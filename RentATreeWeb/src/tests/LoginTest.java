package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import db.Login;

class LoginTest {

	@Test
	void loginUserNotExistTest() {
		Login l = new Login("Test999", "Test999");
		int success = l.loginUser();
		assertNotEquals(success, 1); // should reject as no user Test999 with password Test999 in database.
	}
		// add test user Harison987 with password NotAPassword - DO THIS Manually else test might fail because of user creation not login
		
		//DBConnect db;
		//db = new DBConnect();
		//db.connect();
		//String newuserstr = "IF EXISTS (select 0 from userdetailsmaster where username='Harison') BEGIN CALL createNewUser('Harison','harison@Test.com', 'Harison', 'Wright', '07657865456', 'Pa$$word123', @var) END";
		//String newuserstr = "CALL createNewUser('Harison','harison@Test.com', 'Harison', 'Wright', '07657865456', 'Pa$$word123', @var)";
		//ResultSet output = db.runQuery(newuserstr);
		//System.out.println(output);
	
	@Test
	void loginUserIncorrectPassTest() {
		Login l2 = new Login("Harison987", "Test");
		int success2 = l2.loginUser();
		assertNotEquals(success2, 1); // should reject as user Harison987 password is not Test
	}
	
	@Test
	void loginUserTest() {
		Login l3 = new Login("Harison987", "NotAPassword");
		int success3 = l3.loginUser();
		assertEquals(success3, 1); // should pass
	}

}
