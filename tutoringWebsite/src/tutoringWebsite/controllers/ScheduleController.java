package tutoringWebsite.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.db.FakeScheduleDatabase;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Schedule;
import tutoringWebsite.model.Session;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.InitialData;

public class ScheduleController{
	private Schedule model;
	private DerbyDatabase db = new DerbyDatabase();
	private InitialData initialData;
	
	public void setModel(Schedule model) {
		this.model = model;
	}
	
	public void setDB(DerbyDatabase db) {
		this.db = db;
	}
	
	public List<Session> getScheduleWithDate(String timeframe) {
		//model.setSchedule(timeframe);
		return db.getScheduleByDate(timeframe);
	}
}