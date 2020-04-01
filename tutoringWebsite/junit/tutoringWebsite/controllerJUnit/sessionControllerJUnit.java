package tutoringWebsite.controllerJUnit;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.controllers.sessionController;
import tutoringWebsite.model.session;

public class sessionControllerJUnit {
	private session model;
	private sessionController controller;
	
	@Before
	public void setUp() { 
		model = new session();
		controller = new sessionController();
		
		model.setRoom("123");
		model.setDate("12/12/12");
		model.setTutor("john smith");
		model.setTime("6-8");
		
		
		controller.setModel(model);
	}
@Test
	public void testGetSession() {
		assertTrue(controller.getSession()==model);
	}
@Test
	public void testCreateSession() {
		session test = new session();
		
		test.setDate(model.getDate());
		test.setTime(model.getTime());
		test.setRoom(model.getRoom());
		test.setTutor(model.getTutor());
		
		
		assertTrue(test.getDate().equals(model.getDate()));
		
		
		controller.createSession("124", "12/12/12", "jane smith", "6-8");
		
		
		assertTrue(test.getRoom()!=model.getRoom());
	}
}