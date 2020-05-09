package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;

public class studyGroupJUnit {
	private StudyGroup testStudy;
	private Course studyGroupCourse;
	private Session studySession;
	private LocalDate date;
	private LocalTime time;
	
	@Before
	public void setUp() {
		testStudy = new StudyGroup();
		studyGroupCourse = new Course();
		studySession = new Session();
		date = LocalDate.of(2020, 2, 2);
		time = LocalTime.parse("15:43:43");
		
		studyGroupCourse.setTitle("ECE260");
		studyGroupCourse.setCourseSession(null);
		studyGroupCourse.setTutorList(null);
		
		studySession.setDate(date);
		studySession.setRoom("KEC132");
		studySession.setSessionId(1011);
		studySession.setStartTime(time);
		Tutor tutor = new Tutor();
		tutor.setName("Caryn Sims");
		studySession.setTutor(tutor);
		
		testStudy.setCourse(studyGroupCourse);
		testStudy.setSession(studySession);
	}
	
	@Test
	public void testGetCourse() {
		assertTrue(testStudy.getCourse().equals(studyGroupCourse));
	}
	
	@Test
	public void testGetSession() {
		assertTrue(testStudy.getSession().equals(studySession));
	} 
}