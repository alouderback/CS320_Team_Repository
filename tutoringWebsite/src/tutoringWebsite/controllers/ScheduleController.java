package tutoringWebsite.controllers;
import java.util.ArrayList;

import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Schedule;

public class ScheduleController{
	private Schedule model;
	
	public void setModel(Schedule model) {
		this.model = model;
	}
	
	public void display() {
		model.getSchedule();
	}
}