package tutoringWebsite.model;

import java.util.ArrayList;

import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;


public class Course {
	
	private ArrayList<Tutor> tutorList;
	private Session courseSession;
	private String title;
	private int courseId;
	private int sessionId;
	
	
	public Course() {
		
	}

	public ArrayList<Tutor> getTutorList() {
		return tutorList;
	}

	public void setTutorList(ArrayList<Tutor> tutorList) {
		this.tutorList = tutorList;
	}

	public Session getCourseSession() {
		return courseSession;
	}

	public void setCourseSession(Session courseSession) {
		this.courseSession = courseSession;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	
}