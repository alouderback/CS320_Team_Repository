package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.controllers.SessionController;
import tutoringWebsite.model.Session;

public class sessionJUnit {
	private Session model;
	private SessionController controller;
	
	@Before
	public void setUp() { 
		model = new Session();
		controller = new SessionController();
		
		
		model.setDate("12/12/12");
		model.setTime("6-8");
		model.setRoom("123");
		model.setTutor("john smith");		
		
		controller.setModel(model);
	}
	
	@Test
	public void testDate() {
		assertTrue(model.getDate()=="12/12/12");
		
		
	}
	@Test
	public void testTime() {
		assertTrue(model.getTime()=="6-8");
		
		
	}
	@Test
	public void testTutor() {
		assertTrue(model.getTutor()=="john smith");
	}
	@Test
	public void testRoom() {
		assertTrue(model.getRoom()=="123");
	}
}