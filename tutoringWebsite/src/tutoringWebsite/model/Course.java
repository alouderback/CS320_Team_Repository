package tutoringWebsite.model;

import java.util.ArrayList;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;


public class Course {
	
	private ArrayList<Tutor> tutorList;
	private Session courseSession;
	private String title;
	
	
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
	
}