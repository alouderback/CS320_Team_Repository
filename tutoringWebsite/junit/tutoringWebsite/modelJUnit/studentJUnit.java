package tutoringWebsite.modelJUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;

public class studentJUnit{
	private Student testStudent;
	
	@Before
	public void setUp() {
		testStudent = new Student();
		testStudent.setEmail("jschmidt2@ycp.edu");
		testStudent.setMajor("Computer Engineering");
		testStudent.setName("John Jacob Jingleheimer Schmidt");
		testStudent.setPassword("yeenk");
		testStudent.setUser_Id(125);
		testStudent.setUserType(1);
		testStudent.setYear("Junior");
	}
	
	@Test
	public void testGetYear() {
		assertTrue(testStudent.getYear() == "Junior");
		assertFalse(testStudent.getYear() == "Senior");
	}
	
	@Test
	public void testGetMajor() {
		assertTrue(testStudent.getMajor() == "Computer Engineering");
		assertFalse(testStudent.getMajor() == "Electrical Engineering");
	}
	
	@Test
	public void testStudentExtendsUser() {
		assertTrue(testStudent.getEmail() == "jschmidt2@ycp.edu");
		assertTrue(testStudent.getName() == "John Jacob Jingleheimer Schmidt");
		assertTrue(testStudent.getPassword() == "yeenk");
		assertTrue(testStudent.getUser_Id() == 125);
		assertTrue(testStudent.getUserType() == 1);
		
		assertFalse(testStudent.getEmail() == "jschmidt@ycp.edu");
		assertFalse(testStudent.getName() == "John Schmidt");
		assertFalse(testStudent.getPassword() == "yeenky");
		assertFalse(testStudent.getUser_Id() == 126);
		assertFalse(testStudent.getUserType() == 2);
	}
	
	
}