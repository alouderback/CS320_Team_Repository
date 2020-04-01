package tutoringWebsite.controllers;
import java.util.ArrayList;

import tutoringWebsite.db.FakeScheduleDatabase;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Schedule;
import tutoringWebsite.model.Session;

public class ScheduleController{
	private Schedule model;
	private FakeScheduleDatabase db;
	
	public void setModel(Schedule model) {
		this.model = model;
	}
	
	public void setDB(FakeScheduleDatabase db) {
		this.db = db;
	}
	
	public ArrayList<Session> getScheduleWithDate(String timeframe) {
		//model.setSchedule(timeframe);
		
		return db.findScheduleByDate(timeframe);
	}
}