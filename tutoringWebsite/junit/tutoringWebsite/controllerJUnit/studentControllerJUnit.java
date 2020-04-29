package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;
import tutoringWebsite.controllers.*;

public class studentControllerJUnit {
	private StudentController controller;
	private Student model;
	private User Eric;
	private UserController ericController;
	private int userID;
	
	@Before
	public void setUp() {
		model = new Student();
		controller = new StudentController(model);
		Eric = new User();
		ericController = new UserController(Eric);
		userID = Eric.getUser_Id();
	}
	
	@Test
	public void testCreateStudent() {
		//Getting an error with execute transaction in derby database
		Eric = ericController.createAccount("ebosse@ycp.edu", "yeenk", "Eric Bosse", 1);
		assertTrue(ericController.validateCredentials("ebosse@ycp.edu", "yeenk"));
		assertFalse(ericController.validateCredentials("ebosse@ycp.edu", "yeenky"));
		userID = Eric.getUser_Id();
		
		assertTrue(Eric.getEmail().equals("ebosse@ycp.edu"));
		assertTrue(Eric.getName().equals("Eric Bosse"));
		assertTrue(Eric.getPassword().equals("yeenk"));
		assertTrue(Eric.getUserType() == 1);
		assertTrue(Eric.getUser_Id()== userID);
		assertTrue(ericController.validateCredentials("ebosse@ycp.edu", "yeenk"));
		model = controller.createStudent("computer engineering", "2022", userID);
		
		assertTrue(model.getMajor().equals("computer engineering"));
		assertTrue(model.getYear().equals("2022"));
		
	}
	@Test
	public void testGetStudent() {
		System.out.println("test get student");
		Student test = controller.getStudent("ebosse@ycp.edu","yeenk");
		System.out.println("test major: " + test.getMajor());
		assertFalse(test.equals(null));
		System.out.println("test major: " + test.getMajor());
		assertTrue(test.getMajor().equals("computer engineering"));
		assertTrue(test.getYear().equals("2022"));
		
	}
	
	@Test
	public void testRemoveStudent() {
		
		// System.out.println("test email: " + Eric.getEmail());
		  Eric = ericController.getAccount("ebosse@ycp.edu","yeenk");
		  controller.removeStudentt("ebosse@ycp.edu","yeenk");
		  ericController.removeAccount(Eric);
			
			 try {
				 ericController.getAccount(Eric.getEmail(), Eric.getPassword());
				 controller.getStudent("ebosse@ycp.edu","yeenk");
		         fail("out of bounds error");
		     } catch (final RuntimeException e) {
		         assertTrue(true);
		
		     }
		
		
	}
}