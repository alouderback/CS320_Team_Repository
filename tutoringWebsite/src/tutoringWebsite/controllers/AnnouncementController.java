package tutoringWebsite.controllers;
import tutoringWebsite.model.Announcement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner; 

public class AnnouncementController{
	private Announcement model;
	
	public void setModel(Announcement model) {
		this.model = model;
	}
	
	public Announcement create(LocalDate date, LocalTime time, String message) {
		Announcement newAnnouncement = new Announcement();
		newAnnouncement.setDate(date);
		newAnnouncement.setTime(time);
		newAnnouncement.setMessage(message);
		
		return newAnnouncement;
	}

	
	public void delete() {
		model.delete();
	}
	
}