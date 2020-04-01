package tutoringWebsite.controllerJUnit;

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
	
		Tutor alex = new Tutor();
		alex.setName("Alex Louderback");
		
		courseSession.setDate(LocalDate.of(2012, 12, 12));
		courseSession.setTime(LocalTime.of(03, 52));
		courseSession.setRoom("123");
		courseSession.setTutor(alex);
		
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