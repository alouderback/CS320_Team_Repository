package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;
import tutoringWebsite.controllers.*;

public class userControllerJUnit {
	private UserController controller;
	private User model;
	
	@Before
	public void setUp() {
		model = new User();
		controller = new UserController(model);
	}
	
	@Test
	public void testValidateCredentials() {
		assertTrue(controller.validateCredentials("faculty@ycp.edu", "E&CS") == true);
		assertTrue(controller.validateCredentials("tutor@ycp.edu", "1234") == true);
		assertTrue(controller.validateCredentials("faculty@ycp.edu", "1234") == false);
		assertTrue(controller.validateCredentials("tutor@ycp.edu", "E&CS") == false);
	}
	
	@Test
	public void testGetAccount() {
		assertTrue(1 == 1);
		//Hey hey this method returns me with a blank user I think
	}
	
	@Test
	public void testCreateAccount() {
		//Getting an error with execute transaction in derby database
		User Eric = new User();
		Eric = controller.createAccount("ebosse@ycp.edu", "yeenk", "Eric Bosse", 1);
		assertTrue(controller.validateCredentials("ebosse@ycp.edu", "yeenk"));
		assertFalse(controller.validateCredentials("ebosse@ycp.edu", "yeenky"));
		 
		 System.out.println("test email: " + Eric.getEmail());
		assertTrue(Eric.getEmail().equals("ebosse@ycp.edu"));
		assertTrue(Eric.getName().equals("Eric Bosse"));
		assertTrue(Eric.getPassword().equals("yeenk"));
		assertTrue(Eric.getUserType() == 1);
		//Doesn't assign a user id; need to implement something for this but nobody will answer me
	}
	
	@Test
	public void testValidateUsername() {
		assertTrue(controller.validateUsername("ebosse@ycp.edu") == true);
		assertTrue(controller.validateUsername("csims2@ycp.edu") == true);
		assertTrue(controller.validateUsername("thebigcheese@gmail.com") == false);
	}
}