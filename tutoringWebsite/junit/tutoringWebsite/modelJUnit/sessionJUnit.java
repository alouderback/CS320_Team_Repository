package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
//import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.controllers.SessionController;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;

public class sessionJUnit {
	private Session model;
	private SessionController controller;
	
	@Before
	public void setUp() { 
		model = new Session();
		controller = new SessionController();
		
		Tutor testTutor = new Tutor();
		testTutor.setName("Jimmy John");
		
		model.setDate(LocalDate.parse("2012-12-12"));
		model.setStartTime(LocalTime.of(12, 0));
		model.setRoom("123");
		model.setTutor(testTutor);		
		
		controller.setModel(model);
	}
	
	@Test
	public void testDate() {
		
		LocalDate testDate = LocalDate.parse("2012-12-12");
		System.out.println("Model Date: " + model.getDate() + ", Test Date: " + testDate);
		assertTrue(model.getDate().equals(testDate));
		
		
	}
	@Test
	public void testTime() {
		LocalTime testTime = LocalTime.of(12, 0);
		assertTrue(model.getStartTime()==testTime);
		
		
	}
	@Test
	public void testTutor() {
		assertTrue(model.getTutor().getName()==("Jimmy John"));
	}
	@Test
	public void testRoom() {
		assertTrue(model.getRoom()=="123");
	}
}