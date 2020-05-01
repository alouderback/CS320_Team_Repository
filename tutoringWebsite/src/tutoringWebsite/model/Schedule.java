package tutoringWebsite.model;

import java.util.ArrayList;

import tutoringWebsite.persist.DerbyDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Schedule {
	private LocalDate date;
	private LocalTime time;
	
	private List<Session> scheduleList = new ArrayList<Session>();
	
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
	
	public List<Session> getSchedule() {
		return scheduleList;
	}
	
	public void setSchedule(String timeframe) {
		DerbyDatabase fakeSchedule = new DerbyDatabase();	
		 scheduleList = fakeSchedule.getScheduleByDate(timeframe);
		//This needs to be updated
		
	}
	
	
}