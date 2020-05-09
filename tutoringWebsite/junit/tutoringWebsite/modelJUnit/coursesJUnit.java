package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.CourseController;
import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;

public class coursesJUnit {
	private Course model;
	private CourseController controller;
	private Session courseSession;
	private ArrayList<Tutor> tutorList;
	
	@Before
	public void setUp() { 
		model = new Course();
		controller = new CourseController(model);
		courseSession = new Session();
		tutorList = new ArrayList<Tutor>();
		
		model.setTitle("CS320");
	
		Tutor tutor = new Tutor();
		tutor.setName("Caryn Sims");
		courseSession.setDate(LocalDate.of(2020, 05, 01));
		courseSession.setStartTime(LocalTime.of(10, 30));
		courseSession.setRoom("123");
		courseSession.setTutor(tutor);
		
		model.setCourseSession(courseSession);
		
		
		Tutor emily = new Tutor();
		Tutor john = new Tutor();
		
		tutorList.add(emily);
		tutorList.add(john);
		
		model.setTutorList(tutorList);
	}
	
	@Test
	public void testSession() {
		assertTrue(model.getCourseSession()==courseSession);
		
		
	}
	@Test
	public void testTitle() {
		assertTrue(model.getTitle()=="CS320");
		
		
	}
	@Test
	public void testTutorList() {
		assertTrue(model.getTutorList()==tutorList);
	}
}