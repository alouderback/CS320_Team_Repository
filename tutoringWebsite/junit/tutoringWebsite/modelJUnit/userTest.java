package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.model.User;

public class userTest {
	private User model;
	
	@Before
	public void setUp() {
		model = new User();
		model.setEmail("csims2@ycp.edu");
		model.setPassword("1234");
		model.setName("caryn sims");
		model.setUserType(2);
	}
	
	@Test
	public void testSetEmail() {
		
		assertEquals("csims2@ycp.edu", model.getEmail());
	}
	@Test
	public void testSetPassword() {
		
		assertEquals("1234", model.getPassword());
	}
	@Test
	public void testSetName() {
		
		assertEquals("caryn sims", model.getName());
	}

	@Test
	public void testSetUserType() {
		
		assertEquals(2, model.getUserType());
	}

}
