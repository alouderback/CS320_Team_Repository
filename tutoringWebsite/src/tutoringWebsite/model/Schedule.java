package tutoringWebsite.model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Schedule {
	private LocalDate date;
	private LocalTime time;
	
	private ArrayList<String> scheduleList = new ArrayList<String>();
	
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
	
	public ArrayList<String> getSchedule() {
		return scheduleList;
	}
	
	public void setSchedule() {
		scheduleList.add("3/16/20 - James K. - PHY160");
		scheduleList.add("3/16/20 - Julie G. - PHY160");
		scheduleList.add("3/16/20 - James T. - PHY160");
		scheduleList.add("3/18/20 - James K. - PHY160");
		scheduleList.add("3/18/20 - Julie G. - PHY160");
		scheduleList.add("3/20/20 - James T. - PHY160");
	}
	
	
}