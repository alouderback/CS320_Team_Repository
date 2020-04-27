package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;
import tutoringWebsite.controllers.*;

public class userControllerJUnit {
	private UserController controller;
	private User model;
	private User Eric;
	@Before
	public void setUp() {
		model = new User();
		controller = new UserController(model);
		Eric = new User();
		Eric.setEmail("ebosse@ycp.edu");
		Eric.setPassword("yeenk");
		Eric.setName("Eric Bosse");
		Eric.setUserType(1);
	}
	
	@Test
	public void testValidateCredentials() {
		assertTrue(controller.validateCredentials("faculty@ycp.edu", "E&CS") == true);
		assertTrue(controller.validateCredentials("tutor@ycp.edu", "1234") == true);
		assertTrue(controller.validateCredentials("faculty@ycp.edu", "1234") == false);
		assertTrue(controller.validateCredentials("tutor@ycp.edu", "E&CS") == false);
	}
	@Test
	public void testCreateAccount() {
		//Getting an error with execute transaction in derby database
		User Eric2 = new User();
		Eric2 = controller.createAccount("ebosse@ycp.edu", "yeenk", "Eric Bosse", 1);
		assertTrue(controller.validateCredentials("ebosse@ycp.edu", "yeenk"));
		assertFalse(controller.validateCredentials("ebosse@ycp.edu", "yeenky"));
		 
		
		assertTrue(Eric2.getEmail().equals("ebosse@ycp.edu"));
		assertTrue(Eric2.getName().equals("Eric Bosse"));
		assertTrue(Eric2.getPassword().equals("yeenk"));
		assertTrue(Eric2.getUserType() == 1);
		//Doesn't assign a user id; need to implement something for this but nobody will answer me
	}
	@Test
	public void testGetAccount() {
		 
		User test = controller.getAccount("ebosse@ycp.edu","yeenk");
		assertFalse(test.equals(null));
		System.out.println("test email: " + test.getEmail());
		assertTrue(test.getEmail().equals("ebosse@ycp.edu"));
		assertTrue(test.getName().equals("Eric Bosse"));
		assertTrue(test.getPassword().equals("yeenk"));
		assertTrue(test.getUserType() == 1);
		
		//Hey hey this method returns me with a blank user I think
	}
	
	
	
	@Test
	public void testValidateUsername() {
		assertTrue(controller.validateUsername("ebosse@ycp.edu") == true);
		assertTrue(controller.validateUsername("csims2@ycp.edu") == true);
		assertTrue(controller.validateUsername("thebigcheese@gmail.com") == false);
	}
	@Test
	public void testRemoveAccount() {
		
		// System.out.println("test email: " + Eric.getEmail());
		  Eric = controller.getAccount("ebosse@ycp.edu","yeenk");
			controller.removeAccount(Eric);
			 try {
				 controller.getAccount(Eric.getEmail(), Eric.getPassword());
			 
		         fail("out of bounds error");
		     } catch (final RuntimeException e) {
		         assertTrue(true);
		
		     }
		
		
	}
}