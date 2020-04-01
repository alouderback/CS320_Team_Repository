package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class announcementControllerTest{
	private Announcement model;
	private AnnouncementController controller;
	
	@Before
	public void setUp() {
		model = new Announcement();
		controller = new AnnouncementController();
		
		model.setDate("3-14-2020");
		model.setTime("15:00");
		model.setMessage("PHY160 Tutoring is now being held in KEC130");
		
		controller.setModel(model);
	}
	
	@Test
	public void testCreate() {
		ArrayList<String> testAnnouncement = new ArrayList<String>();
		testAnnouncement = controller.create(model.getDate(), model.getTime(), model.getMessage());
		assertEquals("3-14-2020", testAnnouncement.get(0));
		assertEquals("15:00", testAnnouncement.get(1));
		assertEquals("PHY160 Tutoring is now being held in KEC130", testAnnouncement.get(2));
	}
	
	@Test
	public void testDelete() {
		int test;
		controller.delete();
		if((model.getDate() == null) && (model.getTime() == null) && (model.getMessage() == null)) {
			test = 1;
		}
		else {
			test = 0;
		}
		
		assertEquals(1, test);
	}
	
}