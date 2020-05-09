package tutoringWebsite.modelJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.model.Schedule;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;


public class scheduleTest {
	private Schedule model;
	
	@Before
	public void setUp() {
		model = new Schedule();
	}
	
	@Test
	public void testSetDate() {
		model.setDate(LocalDate.of(2020, 05, 11));
		assertEquals("2020-05-11", model.getDate().toString());
	}
	
	@Test
	public void testSetTime() {
		model.setTime(LocalTime.of(10, 30));
		assertEquals("10:30", model.getTime().toString());
	}
	/*@Test
	public void testGetSchedule() {
		model.setSchedule("SubmitM");
		
		ArrayList<Session> sessionList = new ArrayList<Session>();
		
		Session session1 = new Session();
		Tutor tutor = new Tutor();
		tutor.setName("Eric Bosse");
		session1.setDate(LocalDate.of(2020, 04, 20));
		session1.setRoom("KEC 125");
		session1.setStartTime(LocalTime.of(12, 00));
		session1.setTutor(tutor);
		
		Session session2 = new Session();
		Tutor tutor2 = new Tutor();
		tutor2.setName("Caryn Sims");
		session2.setDate(LocalDate.of(2020, 04, 01));
		session2.setRoom("KEC 127");
		session2.setStartTime(LocalTime.of(20, 00));
		session2.setTutor(tutor2);
		
		Session session3 = new Session();
		Tutor tutor3 = new Tutor();
		tutor3.setName("Alex Louderback");
		session3.setDate(LocalDate.of(2020, 04, 05));
		session3.setRoom("KEC 125");
		session3.setStartTime(LocalTime.of(18, 00));
		session3.setTutor(tutor3);
		
		Session session4 = new Session();
		Tutor tutor4 = new Tutor();
		tutor4.setName("Isabelle Hoffmann");
		session4.setDate(LocalDate.of(2020, 04, 02));
		session4.setRoom("KEC 127");
		session4.setStartTime(LocalTime.of(9, 00));
		session4.setTutor(tutor4);
		
		Session session5 = new Session();
		Tutor tutor5 = new Tutor();
		tutor5.setName("Caryn Sims");
		session5.setDate(LocalDate.of(2020, 04, 29));
		session5.setRoom("KEC 127");
		session5.setStartTime(LocalTime.of(20, 00));
		session5.setTutor(tutor5);
		
		sessionList.add(session1);
		sessionList.add(session2);
		sessionList.add(session3);
		sessionList.add(session4);
		sessionList.add(session5);
		
		assertEquals(sessionList.get(0).getDate(), model.getSchedule().get(0).getDate());
		assertEquals(sessionList.get(0).getStartTime(), model.getSchedule().get(0).getStartTime());
		assertEquals(sessionList.get(0).getRoom(), model.getSchedule().get(0).getRoom());
		assertEquals(sessionList.get(0).getTutor(), model.getSchedule().get(0).getTutor());
		
		assertEquals(sessionList.get(1).getDate(), model.getSchedule().get(1).getDate());
		assertEquals(sessionList.get(1).getStartTime(), model.getSchedule().get(1).getStartTime());
		assertEquals(sessionList.get(1).getRoom(), model.getSchedule().get(1).getRoom());
		assertEquals(sessionList.get(1).getTutor(), model.getSchedule().get(1).getTutor());
		
		assertEquals(sessionList.get(2).getDate(), model.getSchedule().get(2).getDate());
		assertEquals(sessionList.get(2).getStartTime(), model.getSchedule().get(2).getStartTime());
		assertEquals(sessionList.get(2).getRoom(), model.getSchedule().get(2).getRoom());
		assertEquals(sessionList.get(2).getTutor(), model.getSchedule().get(2).getTutor());
		
		assertEquals(sessionList.get(3).getDate(), model.getSchedule().get(3).getDate());
		assertEquals(sessionList.get(3).getStartTime(), model.getSchedule().get(3).getStartTime());
		assertEquals(sessionList.get(3).getRoom(), model.getSchedule().get(3).getRoom());
		assertEquals(sessionList.get(3).getTutor(), model.getSchedule().get(3).getTutor());
		
		assertEquals(sessionList.get(4).getDate(), model.getSchedule().get(4).getDate());
		assertEquals(sessionList.get(4).getStartTime(), model.getSchedule().get(4).getStartTime());
		assertEquals(sessionList.get(4).getRoom(), model.getSchedule().get(4).getRoom());
		assertEquals(sessionList.get(4).getTutor(), model.getSchedule().get(4).getTutor());
	}*/
	
}