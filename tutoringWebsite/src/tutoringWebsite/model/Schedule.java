package tutoringWebsite.model;

import tutoringWebsite.model.FakeScheduleDatabase;


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Schedule {
	private LocalDate date;
	private LocalTime time;
	
	private ArrayList<session> scheduleList = new ArrayList<session>();
	
	public Schedule(){
	
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(LocalTime time) {
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