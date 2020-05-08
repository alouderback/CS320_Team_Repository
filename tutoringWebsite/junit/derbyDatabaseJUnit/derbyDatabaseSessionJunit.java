package derbyDatabaseJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tutoringWebsite.model.*;
import tutoringWebsite.persist.DatabaseProvider;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class derbyDatabaseSessionJunit {

	private IDatabase db = null;
	List<String> daysOfWeek = null;
	
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
	public void testGetDayOfWeek() {
		System.out.println("***Testing getDayOfWeek***");
		List<String> correctDaysOfWeek = new ArrayList<String>();
		correctDaysOfWeek.add("Monday");
		correctDaysOfWeek.add("Wednesday");
		correctDaysOfWeek.add("Friday");
		int sessionId = 28;
		daysOfWeek = db.getDayOfWeek(sessionId);
		
		for(String day : daysOfWeek) {
			System.out.println(day + ", ");
		}
		
		if(!daysOfWeek.equals(correctDaysOfWeek)) {
			fail("Days of Week not returned correctly");
		}
		
	}
}
