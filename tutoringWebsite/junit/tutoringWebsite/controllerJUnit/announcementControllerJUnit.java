package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class announcementControllerJUnit{
	private Announcement model;
	private AnnouncementController controller;
	
	@Before
	public void setUp() {
		model = new Announcement();
		controller = new AnnouncementController();
		LocalDate date = LocalDate.of(2020, 04, 27);
		LocalTime time = LocalTime.of(15, 00);
		model.setDate(date);
		model.setTime(time);
		model.setMessage("PHY160 Tutoring is now being held in KEC130");
		model.setAnnouncementType(1);
		model.setTypeId(1);
		
		controller.setModel(model);
	}
	
	@Test
	public void testCreate() {
		Announcement testAnnouncement = new Announcement();
		testAnnouncement = controller.create(model.getDate(), model.getTime(), model.getMessage(), 
		model.getAnnouncementType(), model.getTypeId());
		assertEquals("2020-04-27", testAnnouncement.getDate().toString());
		assertEquals("15:00", testAnnouncement.getTime().toString());
		assertEquals("PHY160 Tutoring is now being held in KEC130", testAnnouncement.getMessage());
		assertEquals(1, testAnnouncement.getAnnouncementType());
		assertEquals(1, testAnnouncement.getTypeId());
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