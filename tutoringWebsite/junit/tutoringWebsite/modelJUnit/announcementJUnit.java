package tutoringWebsite.modelJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.model.Announcement;

public class announcementJUnit {
	private Announcement model;
	
	@Before
	public void setUp() {
		model = new Announcement();
	}
	
	@Test
	public void testSetMessage() {
		model.setMessage("Hello all, I am getting a group together to study ECE260. All are welcome to join; we will be in KEC128");
		assertEquals("Hello all, I am getting a group together to study ECE260. All are welcome to join; we will be in KEC128", model.getMessage());
	}
	
	@Test
	public void testSetDate() {
		LocalDate date = LocalDate.of(2020, 04, 27);
		model.setDate(date);
		assertEquals("2020-04-27", model.getDate().toString());
	}
	
	@Test
	public void testSetTime() {
		LocalTime time = LocalTime.of(10, 30);
		model.setTime(time);
		assertEquals("10:30", model.getTime().toString());
	}
	
	@Test
	public void testDelete() {
		model.delete();
		assertEquals(null, model.getDate());
		assertEquals(null, model.getTime());
		assertEquals(null, model.getMessage());
		assertEquals(-1, model.getAnnouncementType());
		assertEquals(-1, model.getTypeId());
	}
}