package tutoringWebsite.model;

import java.util.ArrayList;
import tutoringWebsite.model.session;
import tutoringWebsite.model.tutor;


public class courses {
	
	private ArrayList<tutor> tutorList;
	private session courseSession;
	private String title;
	
	
	public courses() {
		
	}

	public ArrayList<tutor> getTutorList() {
		return tutorList;
	}

	public void setTutorList(ArrayList<tutor> tutorList) {
		this.tutorList = tutorList;
	}

	public session getCourseSession() {
		return courseSession;
	}

	public void setCourseSession(session courseSession) {
		this.courseSession = courseSession;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}