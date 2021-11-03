package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import db.NewUser;

class NewUserTest {

	@Test
	void test() {
		
		// ensure test sql script has been reloaded so database is in its test state
		
		String[] params = {"Harison987", "WrightHarison1@sky.com", "Harison", "Wright", "07526458792", "NotAPassword"};
		NewUser nu = new NewUser(params);
		int output = nu.createUser();
		assertEquals(output, -1); // should fail as already exists
		
		String[] params2 = {"Harison9999", "WrightHarison1@9999.com", "Harison", "Wright", "07526458792", "NotAPassword"};
		NewUser nu2 = new NewUser(params2);
		int output2 = nu2.createUser();
		assertTrue(output2 >= 1); // should pass and return a user id
	}

}
