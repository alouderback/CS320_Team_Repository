package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.eclipse.jetty.server.Authentication.User;
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
	//private ArrayList<tutoringWebsite.model.User> tutorList;
	
	@Before
	public void setUp() { 
		model = new Course();
		controller = new CourseController(model);
		courseSession = new Session();
		//tutorList = new ArrayList<tutoringWebsite.model.User>();
		
		model.setTitle("CS320");
	
		Tutor tutor = new Tutor();
		tutor.setName("Caryn Sims");
		courseSession.setDate(LocalDate.of(2020, 05, 01));
		courseSession.setStartTime(LocalTime.of(10, 30));
		courseSession.setRoom("123");
		courseSession.setTutor(tutor);
		
		model.setCourseSession(courseSession);
		
		
		/*User emily = new User();
		User john = new User();
		
		tutorList.add((tutoringWebsite.model.User) emily);
		tutorList.add((tutoringWebsite.model.User) john);
		
		model.setTutorList(tutorList);*/
	}
	
	@Test
	public void testSession() {
		assertTrue(model.getCourseSession()==courseSession);
		
		
	}
	@Test
	public void testTitle() {
		assertTrue(model.getTitle()=="CS320");
		
		
	}
	/*@Test
	public void testTutorList() {
		assertTrue(model.getTutorList()==tutorList);
	}*/
}