package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;
import tutoringWebsite.persist.DatabaseProvider;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class announcementControllerJUnit{
	private Announcement model;
	private AnnouncementController controller;
	private IDatabase db;
	
	@Before
	public void setUp() {
		model = new Announcement();
		controller = new AnnouncementController();
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
		LocalDate date = LocalDate.of(2020, 04, 27);
		LocalTime time = LocalTime.of(15, 00);
		LocalTime time2 = LocalTime.of(16, 00);
		model.setDate(date);
		model.setStartTime(time);
		model.setEndTime(time2);
		model.setMessage("PHY160 Tutoring is now being held in KEC130");
		model.setAnnouncementType(1);
		model.setTypeId(1);
		
		controller.setModel(model);
	}
	
	@Test
	public void testCreate() {
		Announcement testAnnouncement = new Announcement();
		testAnnouncement = controller.create(model.getDate(), model.getStartTime(), model.getEndTime(), model.getMessage(), 
		model.getAnnouncementType(), model.getTypeId());
		assertEquals("2020-04-27", testAnnouncement.getDate().toString());
		assertEquals("15:00", testAnnouncement.getStartTime().toString());
		assertEquals("16:00", testAnnouncement.getEndTime().toString());
		assertEquals("PHY160 Tutoring is now being held in KEC130", testAnnouncement.getMessage());
		assertEquals(1, testAnnouncement.getAnnouncementType());
		assertEquals(1, testAnnouncement.getTypeId());
	}
	
	/*@Test
	public void testDelete() {
		int test;
		String message = "testing delete";
		LocalDate date = LocalDate.of(2020, 05, 04);
		LocalTime startTime = LocalTime.of(10, 30);
		LocalTime endTime = LocalTime.of(11, 00);
		int announcementType = 1;
		int typeId = 5;
		int id = db.createAnnouncement(message, date, startTime, endTime, announcementType, typeId);
		controller.delete(id);
		
	}*/
	
}