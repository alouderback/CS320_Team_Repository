package tutoringWebsite.controllers;
import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.model.*;
import tutoringWebsite.model.announcement;
import tutoringWebsite.model.schedule;

public class scheduleController{
	private schedule model;
	
	public void setModel(schedule model) {
		this.model = model;
	}
	
	
	public ArrayList<session> getScheduleWithDate(String timeframe) {
		model.setSchedule(timeframe);
		return model.getSchedule();
	}
}