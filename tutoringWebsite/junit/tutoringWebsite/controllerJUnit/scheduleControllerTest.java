package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class scheduleControllerTest{
	private Schedule model;
	private ScheduleController controller;
	private CourseController controller2;
	private Course model2;
	
	@Before
	public void setUp() {
		model = new Schedule();
		controller = new ScheduleController();
		model2 = new Course();
		controller2 = new CourseController(model2);
		
		controller.setModel(model);
	}
	
	@Test
	public void testGetScheduleWithDate(){
		//System.out.print(test.get(0).getDate() + ", " + test.get(0).getRoom() + ", " + test.get(0).getTime() + ", " + test.get(0).getTutor());
		
		ArrayList<Session> SessionList = new ArrayList<Session>();
		
		/*Session Session1 = new Session();
		Tutor tutor1 = new Tutor();
		tutor1.setName("Eric Bosse");
		Session1.setDate(LocalDate.of(2020, 04, 01));
		Session1.setRoom("KEC 125");
		Session1.setStartTime(LocalTime.of(18, 0));
		
		Session Session2 = new Session();
		Tutor tutor2 = new Tutor();
		tutor2.setName("Caryn Sims");
		Session2.setDate(LocalDate.of(2020, 04, 01));
		Session2.setRoom("KEC 127");
		Session2.setStartTime(LocalTime.of(20, 0));
		
		Session Session3 = new Session();
		Tutor tutor3 = new Tutor();
		tutor3.setName("Alex Louderback");
		Session3.setDate(LocalDate.of(2020, 04, 02));
		Session3.setRoom("KEC 125");
		Session3.setStartTime(LocalTime.of(18, 0));
		
		Session Session4 = new Session();
		Tutor tutor4 = new Tutor();
		tutor4.setName("Isabelle Hoffmann");
		Session4.setDate(LocalDate.of(2020, 04, 02));
		Session4.setRoom("KEC 127");
		Session4.setStartTime(LocalTime.of(20, 0));
		
		SessionList.add(Session1);
		SessionList.add(Session2);
		SessionList.add(Session3);
		SessionList.add(Session4);*/
		//Session 1
		Tutor alex = new Tutor();
		alex.setName("Alex Louderback");
		Session session = new Session();
		session.setDate(LocalDate.of(2020, 05, 15));
		session.setStartTime(LocalTime.of(03, 52));
		session.setRoom("123");
		session.setTutor(alex);
		model2.setCourseSession(session);
		controller2.createCourse();
		SessionList.add(session);
		//Session 2
		Tutor caryn = new Tutor();
		alex.setName("Caryn Sims");
		Session session2 = new Session();
		session2.setDate(LocalDate.of(2020, 05, 13));
		session2.setStartTime(LocalTime.of(10, 30));
		session2.setRoom("120");
		session2.setTutor(caryn);
		model2.setCourseSession(session2);
		controller2.createCourse();
		SessionList.add(session2);
		
		ArrayList<Session> test = (ArrayList<Session>) controller.getScheduleWithDate("SubmitW");
		System.out.println(SessionList.size());
		
		assertEquals("2020-05-15", test.get(0).getDate().toString());
		//first Session
		//assertTrue(SessionList.get(0).getDate().equals(test.get(0).getDate()));
		assertTrue(SessionList.get(0).getRoom().equals(test.get(0).getRoom()));
		assertTrue(SessionList.get(0).getStartTime().equals(test.get(0).getStartTime()));
		assertTrue(SessionList.get(0).getTutor().equals(test.get(0).getTutor()));
		//second Session
		assertTrue(SessionList.get(1).getDate().equals(test.get(1).getDate()));
		assertTrue(SessionList.get(1).getRoom().equals(test.get(1).getRoom()));
		assertTrue(SessionList.get(1).getStartTime().equals(test.get(1).getStartTime()));
		assertTrue(SessionList.get(1).getTutor().equals(test.get(1).getTutor()));
		/*//third Session
		assertTrue(SessionList.get(2).getDate().equals(test.get(2).getDate()));
		assertTrue(SessionList.get(2).getRoom().equals(test.get(2).getRoom()));
		assertTrue(SessionList.get(2).getStartTime().equals(test.get(2).getStartTime()));
		assertTrue(SessionList.get(2).getTutor().equals(test.get(2).getTutor()));
		//fourth Session
		assertTrue(SessionList.get(3).getDate().equals(test.get(3).getDate()));
		assertTrue(SessionList.get(3).getRoom().equals(test.get(3).getRoom()));
		assertTrue(SessionList.get(3).getStartTime().equals(test.get(3).getStartTime()));
		assertTrue(SessionList.get(3).getTutor().equals(test.get(3).getTutor()));*/
		
	}
}