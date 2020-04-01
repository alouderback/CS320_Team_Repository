package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.courseController;
import tutoringWebsite.model.courses;
import tutoringWebsite.model.session;
import tutoringWebsite.model.tutor;

public class coursesJUnit {
	private courses model;
	private courseController controller;
	private session courseSession;
	private ArrayList<tutor> tutorList;
	
	@Before
	public void setUp() { 
		model = new courses();
		controller = new courseController();
		courseSession = new session();
		tutorList = new ArrayList<tutor>();
		
		model.setTitle("CS320");
	
		
		courseSession.setDate("12/12/12");
		courseSession.setTime("6-8");
		courseSession.setRoom("123");
		courseSession.setTutor("john smith");
		
		model.setCourseSession(courseSession);
		
		
		tutor emily = new tutor();
		tutor john = new tutor();
		
		tutorList.add(emily);
		tutorList.add(john);
		
		model.setTutorList(tutorList);
		
		
		controller.setModel(model);
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