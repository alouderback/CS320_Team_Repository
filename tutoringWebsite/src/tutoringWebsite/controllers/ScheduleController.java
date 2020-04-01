package tutoringWebsite.controllers;
import java.util.ArrayList;

import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Schedule;
import tutoringWebsite.model.Session;

public class ScheduleController{
	private Schedule model;
	
	public void setModel(Schedule model) {
		this.model = model;
	}
	
	public ArrayList<Session> getScheduleWithDate(String timeframe) {
		model.setSchedule(timeframe);
		return model.getSchedule();
	}
}