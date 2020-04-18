package tutoringWebsite.controllerJUnit;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.controllers.SessionController;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;

public class sessionControllerJUnit {
	private Session model;
	private SessionController controller;
	
	@Before
	public void setUp() { 
		model = new Session();
		controller = new SessionController();
		
		Tutor testTutor = new Tutor();
		testTutor.setName("Jimmy John");
		
		model.setDate(LocalDate.parse("2012-12-12"));
		model.setTime(LocalTime.of(12, 0));
		model.setRoom("123");
		model.setTutor(testTutor);		
		
		controller.setModel(model);
	}
@Test
	public void testGetSession() {
		assertTrue(controller.getSession()==model);
	}
@Test
	public void testCreateSession() {
		Session test = new Session();
		
		test.setDate(model.getDate());
		test.setTime(model.getTime());
		test.setRoom(model.getRoom());
		test.setTutor(model.getTutor());
		
		
		assertTrue(test.getDate().equals(model.getDate()));
		
		Tutor testTutorer = new Tutor();
		testTutorer.setName("John Jimmy");
		controller.setModel(test);
		controller.createSession("KEC123", LocalDate.parse("2012-12-11"), testTutorer, LocalTime.of(12, 30));
		System.out.println(test.getRoom());
		System.out.println(test.getDate());
		System.out.println(test.getTutor().getName());
		System.out.println(test.getTime());
		Tutor testTutor = new Tutor();
		testTutor.setName("Jimmy John");
		
		assertTrue(test.getRoom()!=model.getRoom());
		System.out.println(test.getTime());
		assertFalse(test.getTime().equals(LocalTime.of(12, 0)));
		assertFalse(test.getDate().equals(LocalDate.parse("2012-12-12")));
		assertFalse(test.getTutor().equals(testTutor));
	}
}