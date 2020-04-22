package tutoringWebsite.persist;

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

public class DerbyDatabaseTests {

	private IDatabase db = null;
	
	ArrayList<Announcement> announcements = null;
	ArrayList<Sessions> sessions = null;
	ArrayList<StudyGroups> studyGroups = null;	
	List<Pair<Announcement, Session>> announcementSessionList = null;	
	List<Pair<Announcement, StudyGroup>> announcementStudyGroupList = null;	
	List<Announcement> allAnnouncementList = null;
	
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
	public void testgetAnnouncementsforSession() {
		System.out.println("\n*** Testing getAnnouncementsforSession ***");
		
		int sessionId = 1;

		// get the list of (Author, Book) pairs from DB
		announcementSessionList = db.getAnnouncementsforSessionWithSessionId(sessionId);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (announcementSessionList.isEmpty()) {
			System.out.println("No announcements for that session");
			fail("No announcements retrieved from Announcements DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			announcements = new ArrayList<Announcement>();
			for (Pair<Announcement, Session> announcementSession : announcementSessionList) {
				Announcement announcement = announcementSession.getLeft();
				Session session   = announcementSession.getRight();
				announcements.add(announcement);
				System.out.println(announcement.getAnnouncementType() + "," + announcement.getAnnouncementId() +"," + announcement.getMessage()
				+ "," + announcement.getDate() + "," + announcement.getTime());
			}			
		}
	}
	@Test
	public void testgetAnnouncementsforStudyGroup() {
		System.out.println("\n*** Testing getAnnouncementsforStudyGroup ***");
		
		int studyGroupId = 1;

		// get the list of (Author, Book) pairs from DB
		announcementStudyGroupList = db.getAnnouncementsforStudyGroupWithStudyGroupId(studyGroupId);studyGroupId
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (announcementStudyGroupList.isEmpty()) {
			System.out.println("No announcements for that study group");
			fail("No announcements retrieved from Announcements DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			announcements = new ArrayList<Announcement>();
			for (Pair<Announcement, StudyGroup> announcementStudyGroup : announcementStudyGroupList) {
				Announcement announcement = announcementStudyGroup.getLeft();
				StudyGroup studyGroup   = announcementStudyGroup.getRight();
				announcements.add(announcement);
				System.out.println(announcement.getAnnouncementType() + "," + announcement.getAnnouncementId() +"," + announcement.getMessage()
				+ "," + announcement.getDate() + "," + announcement.getTime());
			}			
		}
	}
	@Test
	public void testgetAllAnnouncements() {
		System.out.println("\n*** Testing getAllAnnouncements ***");

		// get the list of (Author, Book) pairs from DB
		allAnnouncementList = db.getAllAnnouncements();
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (allAnnouncementList.isEmpty()) {
			System.out.println("No announcements at all");
			fail("No announcements retrieved from Announcements DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			announcements = new ArrayList<Announcement>();
			for (Announcement announcement : allAnnouncementList) {
				announcements.add(announcement);
				System.out.println(announcement.getAnnouncementType() + "," + announcement.getAnnouncementId() +"," + announcement.getMessage()
				+ "," + announcement.getDate() + "," + announcement.getTime());
			}			
		}
	}
	@Test
	public void testCreateAnnouncementforAStudyGroup() {
		System.out.println("\n*** Testing createAnnouncementforStudyGroup ***");
		
		String message = "first test to create an Announcement for a study group";
		LocalDate date = LocalDate.of(2020, 04, 20);
		LocalTime time = LocalTime.of(10, 10);
		int announcementType = 2; //this type is study group
		int typeId = 1; //id for the study group
		
		Integer id = db.createAnnouncement(message, date, time, announcementType, typeId);
	}
	@Test
	public void testCreateAnnouncementforASession() {
		System.out.println("\n*** Testing createAnnouncementforSession ***");
		
		String message = "first test to create an Announcement for a session";
		LocalDate date = LocalDate.of(2020, 04, 20);
		LocalTime time = LocalTime.of(10, 10);
		int announcementType = 1; //this type is session
		int typeId = 1; //id for the session
		
		Integer id = db.createAnnouncement(message, date, time, announcementType, typeId);
	}
}	
