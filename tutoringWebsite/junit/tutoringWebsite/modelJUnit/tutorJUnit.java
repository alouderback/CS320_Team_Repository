package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;

public class tutorJUnit {
	private Tutor testTutor;
	private Announcement testAnn;
	private Announcement testAnn2;
	private Course course1;
	private Course course2;
	private Course course3;
	private LocalDate date;
	private LocalTime time;
	private ArrayList<Course> courseList;
	private ArrayList<Course> courseList2;
	
	@Before
	public void setUp() {
		date.of(2020,2,2);
		time.parse("15:43:43");
		
		testAnn = new Announcement();
		testAnn.setAnnouncementId(1);
		testAnn.setDate(date);
		testAnn.setMessage("I love my girlfriend");
		testAnn.setStartTime(time);
		
		testAnn2 = new Announcement();
		testAnn2.setAnnouncementId(1);
		testAnn2.setDate(date);
		testAnn2.setMessage("Testy test test test testy");
		testAnn2.setStartTime(time);
		
		course1 = new Course();
		course1.setCourseSession(null);
		course1.setTitle("ECE260");
		course1.setTutorList(null);
		
		course2 = new Course();
		course2.setCourseSession(null);
		course2.setTitle("CS201");
		course2.setTutorList(null);
		
		course3 = new Course();
		course3.setCourseSession(null);
		course3.setTitle("CS101");
		course3.setTutorList(null);
		
		courseList = new ArrayList<Course>();
		courseList.add(course1);
		courseList.add(course2);
		
		courseList2 = new ArrayList<Course>();
		courseList2.add(course1);
		courseList2.add(course2);
		courseList2.add(course3);
		
		testTutor = new Tutor();
		//Tutor Methods
		testTutor.setAnnouncement(testAnn);
		testTutor.setCourses(courseList);
		testTutor.setTutorId(5);
		
		//Student Methods
		testTutor.setMajor("Computer Science");
		testTutor.setYear("Senior");
		
		//User methods
		testTutor.setEmail("jhonk@ycp.edu");
		testTutor.setName("John Honk");
		testTutor.setPassword("yeenk");
		testTutor.setUser_Id(52);
		testTutor.setUserType(2);
	}
	
	@Test
	public void testGetAnnouncement() {
		assertTrue(testTutor.getAnnouncement() == testAnn);
		assertFalse(testTutor.getAnnouncement() == testAnn2);
	}
	
	@Test
	public void testGetCourses() {
		assertTrue(testTutor.getCourses() == courseList);
		assertFalse(testTutor.getCourses() == courseList2);
	}
	
	@Test
	public void testGetTutorId() {
		assertTrue(testTutor.getTutorId() == 5);
		assertFalse(testTutor.getTutorId() == 4);
		assertFalse(testTutor.getTutorId() == 6);
	}
	
	@Test
	public void testTutorExtendsStudent() {
		assertTrue(testTutor.getMajor() == "Computer Science");
		assertTrue(testTutor.getYear() == "Senior");
		
		assertTrue(testTutor.getEmail() == "jhonk@ycp.edu");
		assertTrue(testTutor.getName() == "John Honk");
		assertTrue(testTutor.getPassword() == "yeenk");
		assertTrue(testTutor.getUser_Id() == 52);
		assertTrue(testTutor.getUserType() == 2);
		
		assertFalse(testTutor.getMajor() == "Computer Engineering");
		assertFalse(testTutor.getYear() == "Freshman");
		
		assertFalse(testTutor.getEmail() == "jbeck@ycp.edu");
		assertFalse(testTutor.getName() == "John Beck");
		assertFalse(testTutor.getPassword() == "yeenky");
		assertFalse(testTutor.getUser_Id() == 53);
		assertFalse(testTutor.getUserType() == 1);
	}
}