package tutoringWebsite.controllers;
import tutoringWebsite.model.Announcement;

import java.util.ArrayList;
import java.util.Scanner; 

public class AnnouncementController{
	private Announcement model;
	private ArrayList<String> announcementList = new ArrayList<String>();
	
	public void setModel(Announcement model) {
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