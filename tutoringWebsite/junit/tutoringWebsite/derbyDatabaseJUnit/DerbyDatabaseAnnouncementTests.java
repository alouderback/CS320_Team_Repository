package tutoringWebsite.persist;

import static org.junit.Assert.*;

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
