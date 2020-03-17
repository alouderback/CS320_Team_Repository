package tutoringWebsite.modelJUnit;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import tutoringWebsite.model.schedule;

public class scheduleTest {
	private schedule model;
	
	@Before
	public void setUp() {
		model = new schedule();
	}
	
	@Test
	public void testSetDate() {
		model.setDate("3-29-2020");
		assertEquals("3-29-2020", model.getDate());
	}
	
	@Test
	public void testSetTime() {
		model.setTime("18:00");
		assertEquals("18:00", model.getTime());
	}
	@Test
	public void testGetSchedule() {
		model.setSchedule();
		assertEquals("3/16/20 - James K. - PHY160", model.getSchedule().get(0));
		assertEquals("3/16/20 - Julie G. - PHY160", model.getSchedule().get(1));
		assertEquals("3/16/20 - James T. - PHY160", model.getSchedule().get(2));
		assertEquals("3/18/20 - James K. - PHY160", model.getSchedule().get(3));
		assertEquals("3/18/20 - Julie G. - PHY160", model.getSchedule().get(4));
		assertEquals("3/20/20 - James T. - PHY160", model.getSchedule().get(5));
	}
}