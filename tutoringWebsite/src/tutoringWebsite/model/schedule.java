package tutoringWebsite.model;

import java.util.ArrayList;
import java.util.*;

public class schedule {
	private String date;
	private String time;
	
	private ArrayList<String> scheduleList = new ArrayList<String>();
	
	public schedule(){
		scheduleList.add("3/16/20 - James K. - PHY160");
		scheduleList.add("3/16/20 - Julie G. - PHY160");
		scheduleList.add("3/16/20 - James T. - PHY160");
		scheduleList.add("3/18/20 - James K. - PHY160");
		scheduleList.add("3/18/20 - Julie G. - PHY160");
		scheduleList.add("3/20/20 - James T. - PHY160");
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
	
	public ArrayList<String> getSchedule() {
		return scheduleList;
	}
	
	public void setSchedule() {
	}
	
	
}