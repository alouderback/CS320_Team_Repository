package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.CourseController;
import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;

public class coursesControllerJUnit {
	private Course model;
	private CourseController controller;
	private Session courseSession;
	private ArrayList<Tutor> tutorList;
	
	@Before
	public void setUp() { 
		model = new Course();
		controller = new CourseController();
		courseSession = new Session();
		tutorList = new ArrayList<Tutor>();
		
		model.setTitle("CS320");
	
		
		courseSession.setDate("12/12/12");
		courseSession.setTime("6-8");
		courseSession.setRoom("123");
		courseSession.setTutor("john smith");
		
		model.setCourseSession(courseSession);
		
		
		Tutor emily = new Tutor();
		Tutor john = new Tutor();
		
		tutorList.add(emily);
		tutorList.add(john);
		
		model.setTutorList(tutorList);
		
		
		controller.setModel(model);
	}

@Test
	public void methodTest() {
	
	assertTrue(1==1);
	}

}