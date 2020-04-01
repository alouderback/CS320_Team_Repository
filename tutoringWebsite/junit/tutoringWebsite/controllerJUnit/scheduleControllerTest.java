package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class scheduleControllerTest{
	private schedule model;
	private scheduleController controller;
	
	@Before
	public void setUp() {
		model = new schedule();
		controller = new scheduleController();
		
		controller.setModel(model);
	}
	
	@Test
	public void testGetScheduleWithDate(){
		ArrayList<session> test = controller.getScheduleWithDate("SubmitW");
		//System.out.print(test.get(0).getDate() + ", " + test.get(0).getRoom() + ", " + test.get(0).getTime() + ", " + test.get(0).getTutor());
		
		ArrayList<session> sessionList = new ArrayList<session>();
		
		session session1 = new session();
		session1.setDate("04/01/2020");
		session1.setRoom("KEC 125");
		session1.setTime("18:00");
		session1.setTutor("Eric Bosse");
		
		session session2 = new session();
		session2.setDate("04/01/2020");
		session2.setRoom("KEC 127");
		session2.setTime("20:00");
		session2.setTutor("Caryn Sims");
		
		session session3 = new session();
		session3.setDate("04/02/2020");
		session3.setRoom("KEC 125");
		session3.setTime("18:00");
		session3.setTutor("Alex Louderback");
		
		session session4 = new session();
		session4.setDate("04/02/2020");
		session4.setRoom("KEC 127");
		session4.setTime("20:00");
		session4.setTutor("Isabelle Hoffmann");
		
		sessionList.add(session1);
		sessionList.add(session2);
		sessionList.add(session3);
		sessionList.add(session4);
		
		System.out.println(sessionList.size());
		
		assertTrue(sessionList.get(0).getDate().equals(test.get(0).getDate()));
		assertTrue(sessionList.get(0).getRoom().equals(test.get(0).getRoom()));
		assertTrue(sessionList.get(0).getTime().equals(test.get(0).getTime()));
		assertTrue(sessionList.get(0).getTutor().equals(test.get(0).getTutor()));
		//assertTrue(sessionList.get(0).equals(test.get(0)));
		assertTrue(sessionList.get(1).getDate().equals(test.get(1).getDate()));
		assertTrue(sessionList.get(1).getRoom().equals(test.get(1).getRoom()));
		assertTrue(sessionList.get(1).getTime().equals(test.get(1).getTime()));
		assertTrue(sessionList.get(1).getTutor().equals(test.get(1).getTutor()));
		
		assertTrue(sessionList.get(2).getDate().equals(test.get(2).getDate()));
		assertTrue(sessionList.get(2).getRoom().equals(test.get(2).getRoom()));
		assertTrue(sessionList.get(2).getTime().equals(test.get(2).getTime()));
		assertTrue(sessionList.get(2).getTutor().equals(test.get(2).getTutor()));
		
		assertTrue(sessionList.get(3).getDate().equals(test.get(3).getDate()));
		assertTrue(sessionList.get(3).getRoom().equals(test.get(3).getRoom()));
		assertTrue(sessionList.get(3).getTime().equals(test.get(3).getTime()));
		assertTrue(sessionList.get(3).getTutor().equals(test.get(3).getTutor()));
		
	}
}