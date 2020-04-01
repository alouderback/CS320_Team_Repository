package tutoringWebsite.controllers;
import tutoringWebsite.model.announcement;

import java.util.ArrayList;
import java.util.Scanner; 

public class announcementController{
	private announcement model;
	private ArrayList<String> announcementList = new ArrayList<String>();
	
	public void setModel(announcement model) {
		this.model = model;
	}
	
	public ArrayList<String> create(String date, String time, String message) {
		announcementList.add(date);
		announcementList.add(time);
		announcementList.add(message);
		return announcementList;
	}

	
	public void delete() {
		model.delete();
	}
	
}