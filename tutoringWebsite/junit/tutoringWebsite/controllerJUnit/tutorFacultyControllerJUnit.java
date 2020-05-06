package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import tutoringWebsite.model.*;
import tutoringWebsite.controllers.*;

public class tutorFacultyControllerJUnit {
	private TutorFacultyController controller;
	private TutorFaculty model;
	private List<Integer> userids;
	@Before
	public void setUp() {
		model = new TutorFaculty();
		controller = new TutorFacultyController(model);
		userids = new ArrayList<Integer>();
		userids.add(35);
		userids.add(36);
		userids.add(37);
		userids.add(38);
		/*System.out.println(userids.get(0));
		System.out.println(userids.get(1));
		System.out.println(userids.get(2));
		System.out.println(userids.get(3));
		*/
	}
	@Test
	public void testGetUserIdbyCourseId() {
		/*System.out.println("\n*** Testing UserIdbyCourseId ***");
		System.out.println(userids.get(0));
		System.out.println(userids.get(1));
		System.out.println(userids.get(2));
		System.out.println(userids.get(3));*/
		List<Integer> temp1 = controller.getUserIdbyCourseId(32);
		//assertTrue(temp1.equals(userids));
		/*System.out.println(temp1.get(0));
		System.out.println(temp1.get(1));
		System.out.println(temp1.get(2));
		System.out.println(temp1.get(3));*/
		assertTrue(temp1.get(0).equals(userids.get(0)));
		List<Integer> temp = controller.getUserIdbyCourseId(12);
		int blah=temp.get(0).compareTo(5);
		System.out.println(blah);
		assertTrue(blah==0);

	}
	@Test
	public void  testGetCourseidbyUserId() {
		System.out.println("\n*** Testing GetCourseidbyUserId ***");
		List<Integer> temp = controller.getCourseidbyUserId(7);
		int blah = temp.get(0).compareTo(22);
		System.out.println(blah);
		assertTrue(blah==0);
	}
}