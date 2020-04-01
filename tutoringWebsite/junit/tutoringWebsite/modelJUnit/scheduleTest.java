package tutoringWebsite.modelJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.model.Schedule;
import tutoringWebsite.model.Session;


public class scheduleTest {
	private Schedule model;
	
	@Before
	public void setUp() {
		model = new Schedule();
	}
	
	@Test
	public void testSetDate() {
		model.setDate("3-29-2020");
		assertEquals("3-29-2020", model.getDate());
	}
	
	@Test
	public void testSetTime() {
		model.setTime("18:00");
		assertEquals("18:00", model.getTime());
	}
	@Test
	public void testGetSchedule() {
		model.setSchedule("SubmitM");
		
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
		
		session session5 = new session();
		session5.setDate("04/29/2020");
		session5.setRoom("KEC 127");
		session5.setTime("20:00");
		session5.setTutor("Isabelle Hoffman");
		
		sessionList.add(session1);
		sessionList.add(session2);
		sessionList.add(session3);
		sessionList.add(session4);
		sessionList.add(session5);
		
		assertEquals(sessionList.get(0).getDate(), model.getSchedule().get(0).getDate());
		assertEquals(sessionList.get(0).getTime(), model.getSchedule().get(0).getTime());
		assertEquals(sessionList.get(0).getRoom(), model.getSchedule().get(0).getRoom());
		assertEquals(sessionList.get(0).getTutor(), model.getSchedule().get(0).getTutor());
		
		assertEquals(sessionList.get(1).getDate(), model.getSchedule().get(1).getDate());
		assertEquals(sessionList.get(1).getTime(), model.getSchedule().get(1).getTime());
		assertEquals(sessionList.get(1).getRoom(), model.getSchedule().get(1).getRoom());
		assertEquals(sessionList.get(1).getTutor(), model.getSchedule().get(1).getTutor());
		
		assertEquals(sessionList.get(2).getDate(), model.getSchedule().get(2).getDate());
		assertEquals(sessionList.get(2).getTime(), model.getSchedule().get(2).getTime());
		assertEquals(sessionList.get(2).getRoom(), model.getSchedule().get(2).getRoom());
		assertEquals(sessionList.get(2).getTutor(), model.getSchedule().get(2).getTutor());
		
		assertEquals(sessionList.get(3).getDate(), model.getSchedule().get(3).getDate());
		assertEquals(sessionList.get(3).getTime(), model.getSchedule().get(3).getTime());
		assertEquals(sessionList.get(3).getRoom(), model.getSchedule().get(3).getRoom());
		assertEquals(sessionList.get(3).getTutor(), model.getSchedule().get(3).getTutor());
		
		assertEquals(sessionList.get(4).getDate(), model.getSchedule().get(4).getDate());
		assertEquals(sessionList.get(4).getTime(), model.getSchedule().get(4).getTime());
		assertEquals(sessionList.get(4).getRoom(), model.getSchedule().get(4).getRoom());
		assertEquals(sessionList.get(4).getTutor(), model.getSchedule().get(4).getTutor());
	}
	
}