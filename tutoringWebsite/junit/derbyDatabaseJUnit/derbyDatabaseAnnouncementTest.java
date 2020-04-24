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

public class derbyDatabaseAnnouncementTest {

	private IDatabase db = null;
	
	ArrayList<Announcement> announcements = null;
	ArrayList<Session> sessions = null;
	ArrayList<StudyGroup> studyGroups = null;	
	List<Announcement> announcementSessionList = null;	
	List<Announcement> announcementStudyGroupList = null;	
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

	/*@Test
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
			for (Announcement announcement : announcementSessionList) {
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
		announcementStudyGroupList = db.getAnnouncementsforStudyGroupWithStudyGroupId(studyGroupId);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (announcementStudyGroupList.isEmpty()) {
			System.out.println("No announcements for that study group");
			fail("No announcements retrieved from Announcements DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			announcements = new ArrayList<Announcement>();
			for (Announcement announcement : announcementStudyGroupList) {
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
	}*/
	@Test
	public void testCreatAnnouncement() {
		System.out.println("\n*** Testing createAnnouncement***");
		
		
	}
}	
