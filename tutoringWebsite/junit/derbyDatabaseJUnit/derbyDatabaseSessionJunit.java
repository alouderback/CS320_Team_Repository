package derbyDatabaseJUnit;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tutoringWebsite.model.*;
import tutoringWebsite.persist.DatabaseProvider;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class derbyDatabaseSessionJunit {

	private IDatabase db = null;
	List<String> daysOfWeek = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetDayOfWeek() {
		System.out.println("***Testing getDayOfWeek***");
		List<String> correctDaysOfWeek = new ArrayList<String>();
		correctDaysOfWeek.add("Monday");
		correctDaysOfWeek.add("Wednesday");
		correctDaysOfWeek.add("Friday");
		int sessionId = 28;
		daysOfWeek = db.getDayOfWeek(sessionId);
		
		for(String day : daysOfWeek) {
			System.out.println(day + ", ");
		}
		
		if(!daysOfWeek.equals(correctDaysOfWeek)) {
			fail("Days of Week not returned correctly");
		}
		
	}
	
	@Test
	public void testGetScheduleByDate() {
		System.out.println("***Testing getScheduleByDate***");
		List<Session> sessions = new ArrayList<Session>();
		
		sessions = db.getScheduleByDate("SubmitM");
		
		Session sessionOne = new Session();
		sessionOne.setSessionId(11);
		sessionOne.setDate(LocalDate.parse("2020-05-15"));
		sessionOne.setRoom("KEC120");
		sessionOne.setStartTime(LocalTime.parse("20:00:00.000"));
		sessionOne.setEndTime(LocalTime.parse("21:00:00.000"));
		sessionOne.setDayOfWeek(42);
		sessionOne.setAdminId(04);
		sessionOne.setCourseId(56);
		sessionOne.setTypeId(2);
		sessionOne.setAdminName(null);
		sessionOne.setCourseName(null);
		sessionOne.setTypeName(null);
		sessionOne.setDaysOfWeekString(null);
		
		assertTrue(sessionOne.getSessionId() == sessions.get(0).getSessionId());
		assertTrue(sessionOne.getDate().equals(sessions.get(0).getDate()));
		assertTrue(sessionOne.getRoom().equals(sessions.get(0).getRoom()));
		assertTrue(sessionOne.getStartTime().equals(sessions.get(0).getStartTime()));
		assertTrue(sessionOne.getEndTime().equals(sessions.get(0).getEndTime()));
		assertTrue(sessionOne.getDayOfWeek() == sessions.get(0).getDayOfWeek());
		assertTrue(sessionOne.getAdminId() == sessions.get(0).getAdminId());
		assertTrue(sessionOne.getCourseId() == sessions.get(0).getCourseId());
		assertTrue(sessionOne.getTypeId() == sessions.get(0).getTypeId());
		assertTrue(sessionOne.getAdminName() == sessions.get(0).getAdminName());
		assertTrue(sessionOne.getCourseName() == sessions.get(0).getCourseName());
		assertTrue(sessionOne.getTypeName() == sessions.get(0).getTypeName());
	}
	
	@Test
	public void testGetAllSessions(){
		System.out.println("***Testing getAllSessions ***");
		List<Session> sessions = new ArrayList<Session>();
		
		sessions = db.getAllSessions();
		
		Session sessionOne = new Session();
		Session sessionTwo = new Session();
		Session sessionThree = new Session();
		Session sessionFive = new Session();
		Session sessionNine = new Session();
		
		sessionOne.setSessionId(1);
		sessionOne.setDate(LocalDate.parse("2020-04-25"));
		sessionOne.setRoom("KEC135");
		sessionOne.setStartTime(LocalTime.parse("03:55:00.000"));
		sessionOne.setEndTime(LocalTime.parse("04:55:00.000"));
		sessionOne.setDayOfWeek(42);
		sessionOne.setAdminId(16);
		sessionOne.setCourseId(04);
		sessionOne.setTypeId(1);
		
		sessionTwo.setSessionId(2);
		sessionTwo.setDate(LocalDate.parse("2020-04-26"));
		sessionTwo.setRoom("KEC118");
		sessionTwo.setStartTime(LocalTime.parse("08:55:00.000"));
		sessionTwo.setEndTime(LocalTime.parse("09:55:00.000"));
		sessionTwo.setDayOfWeek(42);
		sessionTwo.setAdminId(13);
		sessionTwo.setCourseId(04);
		sessionTwo.setTypeId(1);
		
		sessionThree.setSessionId(3);
		sessionThree.setDate(LocalDate.parse("2020-04-27"));
		sessionThree.setRoom("KEC119");
		sessionThree.setStartTime(LocalTime.parse("13:55:00.000"));
		sessionThree.setEndTime(LocalTime.parse("14:55:00.000"));
		sessionThree.setDayOfWeek(20);
		sessionThree.setAdminId(15);
		sessionThree.setCourseId(04);
		sessionThree.setTypeId(1);
		
		sessionFive.setSessionId(5);
		sessionFive.setDate(LocalDate.parse("2020-04-25"));
		sessionFive.setRoom("KEC135");
		sessionFive.setStartTime(LocalTime.parse("05:00:00.000"));
		sessionFive.setEndTime(LocalTime.parse("06:00:00.000"));
		sessionFive.setDayOfWeek(06);
		sessionFive.setAdminId(01);
		sessionFive.setCourseId(24);
		sessionFive.setTypeId(2);
		
		sessionNine.setSessionId(9);
		sessionNine.setDate(LocalDate.parse("2020-05-03"));
		sessionNine.setRoom("KEC120");
		sessionNine.setStartTime(LocalTime.parse("20:00:00.000"));
		sessionNine.setEndTime(LocalTime.parse("21:00:00.000"));
		sessionNine.setDayOfWeek(42);
		sessionNine.setAdminId(04);
		sessionNine.setCourseId(56);
		sessionNine.setTypeId(2);
		
		System.out.println("One DB AdminId: " + sessions.get(0).getAdminId());
		System.out.println("Two DB AdminId: " + sessions.get(1).getAdminId());
		System.out.println("Three DB AdminId: " + sessions.get(2).getAdminId());
		System.out.println("Five DB AdminId: " + sessions.get(4).getAdminId());
		System.out.println("Nine DB AdminId: " + sessions.get(8).getAdminId());
		
		assertTrue(sessions.get(0).getAdminId() == sessionOne.getAdminId());
		assertTrue(sessions.get(1).getAdminId() == sessionTwo.getAdminId());
		assertTrue(sessions.get(2).getAdminId() == sessionThree.getAdminId());
		assertTrue(sessions.get(4).getAdminId() == sessionFive.getAdminId());
		assertTrue(sessions.get(8).getAdminId() == sessionNine.getAdminId());
		
		assertTrue(sessions.get(0).getCourseId() == sessionOne.getCourseId());
		assertTrue(sessions.get(1).getCourseId() == sessionTwo.getCourseId());
		assertTrue(sessions.get(2).getCourseId() == sessionThree.getCourseId());
		assertTrue(sessions.get(4).getCourseId() == sessionFive.getCourseId());
		assertTrue(sessions.get(8).getCourseId() == sessionNine.getCourseId());
		
		assertTrue(sessions.get(0).getTypeId() == sessionOne.getTypeId());
		assertTrue(sessions.get(1).getTypeId() == sessionTwo.getTypeId());
		assertTrue(sessions.get(2).getTypeId() == sessionThree.getTypeId());
		assertTrue(sessions.get(4).getTypeId() == sessionFive.getTypeId());
		assertTrue(sessions.get(8).getTypeId() == sessionNine.getTypeId());
		
		assertTrue(sessions.get(0).getDate().equals(sessionOne.getDate()));
		assertTrue(sessions.get(1).getDate().equals(sessionTwo.getDate()));
		assertTrue(sessions.get(2).getDate().equals(sessionThree.getDate()));
		assertTrue(sessions.get(4).getDate().equals(sessionFive.getDate()));
		assertTrue(sessions.get(8).getDate().equals(sessionNine.getDate()));
		
		assertTrue(sessions.get(0).getRoom().equals(sessionOne.getRoom()));
		assertTrue(sessions.get(1).getRoom().equals(sessionTwo.getRoom()));
		assertTrue(sessions.get(2).getRoom().equals(sessionThree.getRoom()));
		assertTrue(sessions.get(4).getRoom().equals(sessionFive.getRoom()));
		assertTrue(sessions.get(8).getRoom().equals(sessionNine.getRoom()));
		
		assertTrue(sessions.get(0).getStartTime().equals(sessionOne.getStartTime()));
		assertTrue(sessions.get(1).getStartTime().equals(sessionTwo.getStartTime()));
		assertTrue(sessions.get(2).getStartTime().equals(sessionThree.getStartTime()));
		assertTrue(sessions.get(4).getStartTime().equals(sessionFive.getStartTime()));
		assertTrue(sessions.get(8).getStartTime().equals(sessionNine.getStartTime()));
		
		assertTrue(sessions.get(0).getEndTime().equals(sessionOne.getEndTime()));
		assertTrue(sessions.get(1).getEndTime().equals(sessionTwo.getEndTime()));
		assertTrue(sessions.get(2).getEndTime().equals(sessionThree.getEndTime()));
		assertTrue(sessions.get(4).getEndTime().equals(sessionFive.getEndTime()));
		assertTrue(sessions.get(8).getEndTime().equals(sessionNine.getEndTime()));
		
		assertTrue(sessions.get(0).getAdminId() == sessionOne.getAdminId());
		assertTrue(sessions.get(1).getAdminId() == sessionTwo.getAdminId());
		assertTrue(sessions.get(2).getAdminId() == sessionThree.getAdminId());
		assertTrue(sessions.get(4).getAdminId() == sessionFive.getAdminId());
		assertTrue(sessions.get(8).getAdminId() == sessionNine.getAdminId());
	}
}
