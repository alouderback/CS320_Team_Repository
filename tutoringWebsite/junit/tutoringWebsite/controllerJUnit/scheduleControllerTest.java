package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//import org.eclipse.jetty.server.session.Session;
import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class scheduleControllerTest{
	private Schedule model;
	private ScheduleController controller;
	
	@Before
	public void setUp() {
		model = new Schedule();
		controller = new ScheduleController();
		
		controller.setModel(model);
	}
	
	@Test
	public void testGetScheduleWithDate(){
		ArrayList<Session> test = controller.getScheduleWithDate("SubmitW");
		//System.out.print(test.get(0).getDate() + ", " + test.get(0).getRoom() + ", " + test.get(0).getTime() + ", " + test.get(0).getTutor());
		
		ArrayList<Session> sessionList = new ArrayList<Session>();
		
		Session session1 = new Session();
		Tutor tutor1 = new Tutor();
		Course course1 = new Course();
		tutor1.setName("Eric Bosse");
		course1.setTitle("CS101");
		session1.setDate(LocalDate.of(2020, 04, 01));
		session1.setRoom("KEC 125");
		session1.setTime(LocalTime.of(18, 00));
		session1.setTutor(tutor1);
		session1.setCourse(course1);
		
		Session session2 = new Session();
		Tutor tutor2 = new Tutor();
		Course course2 = new Course();
		tutor2.setName("Caryn Sims");
		course2.setTitle("CS201");
		session2.setDate(LocalDate.of(2020, 04, 01));
		session2.setRoom("KEC 127");github
		session2.setTime(LocalTime.of(20, 00));
		session2.setTutor(tutor2);
		session2.setCourse(course2);
		
		Session session3 = new Session();
		Tutor tutor3 = new Tutor();
		Course course3 = new Course();
		tutor3.setName("Alex Louderback");
		course3.setTitle("PHY160");
		session3.setDate(LocalDate.of(2020,04,02));
		session3.setRoom("KEC 125");
		session3.setTime(LocalTime.of(18,00));
		session3.setTutor(tutor3);
		session3.setCourse(course3);
		
		Session session4 = new Session();
		Tutor tutor4 = new Tutor();
		tutor4.setName("Isabelle Hoffmann");
		session4.setDate(LocalDate.of(2020, 04, 02));
		session4.setRoom("KEC 127");
		session4.setTime(LocalTime.of(20, 00));
		session4.setTutor(tutor4);
		session4.setCourse(course1);
		
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
		
		assertTrue(sessionList.size() == (test.size()));
		
	}
}