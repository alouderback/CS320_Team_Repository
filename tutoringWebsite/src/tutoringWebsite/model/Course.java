package tutoringWebsite.model;

import java.util.ArrayList;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;


public class Course {
	
	private ArrayList<Tutor> tutorList;
	private ArrayList<Session> courseSessions; //Actual classtime/classtimes of course
	private String title;
	
	
	public Course() {
		
	}

	public ArrayList<Tutor> getTutorList() {
		return tutorList;
	}

	public void setTutorList(ArrayList<Tutor> tutorList) {
		this.tutorList = tutorList;
	}

	public ArrayList<Session> getCourseSession() {
		return courseSessions;
	}

	public void setCourseSession(ArrayList<Session> courseSessions) {
		this.courseSessions = courseSessions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}