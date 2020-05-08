package tutoringWebsite.controllers;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.persist.DerbyDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner; 

public class AnnouncementController{
	private Announcement model;
	private DerbyDatabase db = new DerbyDatabase();
	
	public void setModel(Announcement model) {
		this.model = model;
	}
	public int create(LocalDate date, LocalTime time, String message, int announcementType, int typeId) {
		int id = db.createAnnouncement(message, date, time, announcementType, typeId);
		return id;
	}
	public List<Announcement> getAnnouncements() {
		return db.getAllAnnouncements();
	}
	public List<Announcement> getStudyGroupAnnouncements() {
		return db.getAllStudyGroupAnnouncements();
	}
	public List<Announcement> getSessionAnnouncements() {
		return db.getAllSessionAnnouncements();
	}
	public void delete() {
		model.delete();
	}
	
}