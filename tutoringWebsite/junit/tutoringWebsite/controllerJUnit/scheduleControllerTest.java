package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class scheduleControllerTest{
	private Schedule model;
	private ScheduleController controller;
	
	@Before
	public void setUp() {
		model = new Schedule();
		controller = new ScheduleController();
		
		model.setDate("3-14-2020");
		model.setTime("15:00");
		model.setSchedule();
	}
	
	@Test
	public void testDisplay(){
		ArrayList<String> testSchedule = new ArrayList<String>();
		testSchedule.add("3/16/20 - James K. - PHY160");
		testSchedule.add("3/16/20 - Julie G. - PHY160");
		testSchedule.add("3/16/20 - James T. - PHY160");
		testSchedule.add("3/18/20 - James K. - PHY160");
		testSchedule.add("3/18/20 - Julie G. - PHY160");
		testSchedule.add("3/20/20 - James T. - PHY160");
		
		assertEquals(testSchedule, model.getSchedule());
	}
}