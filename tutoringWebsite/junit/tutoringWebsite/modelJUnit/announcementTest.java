package tutoringWebsite.modelJUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.model.Announcement;

public class announcementTest {
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
		model.setDate("4-7-2020");
		assertEquals("4-7-2020", model.getDate());
	}
	
	@Test
	public void testSetTime() {
		model.setTime("14:00");
		assertEquals("14:00", model.getTime());
	}
	
	@Test
	public void testDelete() {
		model.delete();
		assertEquals(null, model.getDate());
		assertEquals(null, model.getTime());
		assertEquals(null, model.getMessage());
	}
}