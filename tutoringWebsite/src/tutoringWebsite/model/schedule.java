package tutoringWebsite.model;

import tutoringWebsite.model.FakeScheduleDatabase;


import java.util.ArrayList;
import java.util.*;

public class schedule {
	private String date;
	private String time;
	
	private ArrayList<session> scheduleList = new ArrayList<session>();
	
	public schedule(){
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public ArrayList<session> getSchedule() {
		return scheduleList;
	}
	
	public void setSchedule(String timeframe) {
		FakeScheduleDatabase fakeSchedule = new FakeScheduleDatabase();	
		scheduleList = fakeSchedule.findScheduleByDate(timeframe);
		
	}
	
	
}