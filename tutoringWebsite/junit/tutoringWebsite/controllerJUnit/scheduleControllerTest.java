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
		
		ArrayList<Session> SessionList = new ArrayList<Session>();
		
		Session Session1 = new Session();
		Tutor tutor1 = new Tutor();
		tutor1.setName("Eric Bosse");
		Session1.setDate(LocalDate.of(2020, 04, 01));
		Session1.setRoom("KEC 125");
		Session1.setTime(LocalTime.of(18, 0));
		
		Session Session2 = new Session();
		Tutor tutor2 = new Tutor();
		tutor2.setName("Caryn Sims");
		Session2.setDate(LocalDate.of(2020, 04, 01));
		Session2.setRoom("KEC 127");
		Session2.setTime(LocalTime.of(20, 0));
		
		Session Session3 = new Session();
		Tutor tutor3 = new Tutor();
		tutor3.setName("Alex Louderback");
		Session3.setDate(LocalDate.of(2020, 04, 02));
		Session3.setRoom("KEC 125");
		Session3.setTime(LocalTime.of(18, 0));
		
		Session Session4 = new Session();
		Tutor tutor4 = new Tutor();
		tutor4.setName("Isabelle Hoffmann");
		Session4.setDate(LocalDate.of(2020, 04, 02));
		Session4.setRoom("KEC 127");
		Session4.setTime(LocalTime.of(20, 0));
		
		SessionList.add(Session1);
		SessionList.add(Session2);
		SessionList.add(Session3);
		SessionList.add(Session4);
		
		System.out.println(SessionList.size());
		
		assertTrue(SessionList.get(0).getDate().equals(test.get(0).getDate()));
		assertTrue(SessionList.get(0).getRoom().equals(test.get(0).getRoom()));
		assertTrue(SessionList.get(0).getTime().equals(test.get(0).getTime()));
		assertTrue(SessionList.get(0).getTutor().equals(test.get(0).getTutor()));
		//assertTrue(SessionList.get(0).equals(test.get(0)));
		assertTrue(SessionList.get(1).getDate().equals(test.get(1).getDate()));
		assertTrue(SessionList.get(1).getRoom().equals(test.get(1).getRoom()));
		assertTrue(SessionList.get(1).getTime().equals(test.get(1).getTime()));
		assertTrue(SessionList.get(1).getTutor().equals(test.get(1).getTutor()));
		
		assertTrue(SessionList.get(2).getDate().equals(test.get(2).getDate()));
		assertTrue(SessionList.get(2).getRoom().equals(test.get(2).getRoom()));
		assertTrue(SessionList.get(2).getTime().equals(test.get(2).getTime()));
		assertTrue(SessionList.get(2).getTutor().equals(test.get(2).getTutor()));
		
		assertTrue(SessionList.get(3).getDate().equals(test.get(3).getDate()));
		assertTrue(SessionList.get(3).getRoom().equals(test.get(3).getRoom()));
		assertTrue(SessionList.get(3).getTime().equals(test.get(3).getTime()));
		assertTrue(SessionList.get(3).getTutor().equals(test.get(3).getTutor()));
		
	}
}