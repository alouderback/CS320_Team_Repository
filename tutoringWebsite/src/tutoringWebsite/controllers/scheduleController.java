package tutoringWebsite.controllers;
import java.util.ArrayList;

import tutoringWebsite.model.announcement;
import tutoringWebsite.model.schedule;

public class scheduleController{
	private schedule model;
	
	public void setModel(schedule model) {
		this.model = model;
	}
	
	public void display() {
		model.getSchedule();
	}
}