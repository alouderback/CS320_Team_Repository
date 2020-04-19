package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;
//import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;

public class loginJUnit{
	private Login testLogin;
	@Before
	public void setUp(){
		testLogin = new Login();
	}
	
	@Test
	public void testValidateUsername() {
		assertTrue(testLogin.validateUserName("faculty@ycp.edu") == true);
		assertTrue(testLogin.validateUserName("tutor@ycp.edu") == true);
		assertTrue(testLogin.validateUserName("ericisthebest@ycp.edu") == false);
	}
	
	@Test
	public void testValidatePW() {
		assertTrue(testLogin.validatePW("faculty@ycp.edu", "E&CS") == true);
		assertTrue(testLogin.validatePW("tutor@ycp.edu", "1234") == true);
		assertTrue(testLogin.validatePW("faculty@ycp.edu", "1234") == false);
		assertTrue(testLogin.validatePW("tutor@ycp.edu", "E&CS") == false);
		assertTrue(testLogin.validatePW("ericisthebest@ycp.edu", "swiggityswooty1234") == false);
	}
	
	@Test
	public void testCreateAccount() {
		User Eric = testLogin.createAccount("ericisthebest@ycp.edu", "swiggityswooty", "Eric", 1);
		assertTrue(testLogin.validateUserName("ericisthebest@ycp.edu") == true);
		assertTrue(testLogin.validatePW("ericisthebest@ycp.edu", "swiggityswooty") == true);
		assertTrue(Eric.getEmail() == "ericisthebest@ycp.edu");
		assertTrue(Eric.getName() == "Eric");
		assertTrue(Eric.getPassword() == "swiggityswooty");
		assertTrue(Eric.getUserType() == 1);
		//Should find a way to get user ID
	}
	
	@Test
	public void testIsStudent() {
		assertTrue(testLogin.isStudent("john@ycp.edu") == true);
		assertTrue(testLogin.isStudent("johnjonglejingle@gmail.com"));
	}
}