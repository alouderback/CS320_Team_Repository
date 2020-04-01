package tutoringWebsite.modelJUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.model.*;

public class userTest {
	private user model;
	
	@Before
	public void setUp() {
		model = new user();
	}
	
	@Test
	public void testSetEmail() {
		model.setEmail("csims2@ycp.edu");
		assertEquals("csims2@ycp.edu", model.getEmail());
	}
	@Test
	public void testSetPassword() {
		model.setPassword("1234");
		assertEquals("1234", model.getPassword());
	}
}
