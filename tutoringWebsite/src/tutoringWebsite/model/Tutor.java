package tutoringWebsite.model;

import java.util.ArrayList;

public class Tutor extends Student {
	
	private Announcement announcement;
	private ArrayList<Course> courses;
	
	
	public Tutor() {
		
	}
	
	public Announcement getAnnouncement() {
		return announcement;
	}
	
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	
}