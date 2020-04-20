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
	
	public Announcement create(LocalDate date, LocalTime time, String message, int announcementType) {
		Announcement newAnnouncement = new Announcement();
		newAnnouncement.setDate(date);
		newAnnouncement.setTime(time);
		newAnnouncement.setMessage(message);
		newAnnouncement.setAnnouncementType(announcementType);
		
		return newAnnouncement;
	}

	
	public void delete() {
		model.delete();
	}
	
}