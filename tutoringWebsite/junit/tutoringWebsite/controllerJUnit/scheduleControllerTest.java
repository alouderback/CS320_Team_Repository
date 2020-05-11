package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class scheduleControllerTest{
	private Schedule model;
	private ScheduleController controller;
	private CourseController controller2;
	private Course model2;
	
	@Before
	public void setUp() {
		model = new Schedule();
		controller = new ScheduleController();
		model2 = new Course();
		controller2 = new CourseController(model2);
		
		controller.setModel(model);
	}

}