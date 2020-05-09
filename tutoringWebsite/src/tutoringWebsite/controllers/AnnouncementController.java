package tutoringWebsite.controllers;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.persist.DerbyDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jetty.server.session.Session; 

public class AnnouncementController{
	private Announcement model;
	private Session model2;
	private DerbyDatabase db = new DerbyDatabase();
	
	public void setModel(Announcement model) {
		this.model = model;
	}
	public void setModel2(Session model2) {
		this.model2 = model2;
	}
	public Announcement create(LocalDate date, LocalTime time, String message, int announcementType, int typeId) {
		int id = db.createAnnouncement(message, date, time, announcementType, typeId);
		Announcement announcement = new Announcement();
		announcement.setAnnouncementId(id);
		announcement.setDate(date);
		announcement.setTime(time);
		announcement.setMessage(message);
		announcement.setAnnouncementType(announcementType);
		announcement.setTypeId(typeId);
		return announcement;
	}
	public List<Announcement> getAnnouncements() {
		List<Announcement> announcements = new ArrayList<Announcement>();
		announcements = db.getAllAnnouncements();
		for(Announcement announcement: announcements) {
			int num = announcement.getAnnouncementType();
			String name = null;
			if(num == 1) {
				name = "Tutoring Session";
			}else {
				name = "Study Group";
			}
			announcement.setTypeName(name);
			announcement.setSession(db.getSingleSession(announcement.getTypeId()));
		}
		return announcements;
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