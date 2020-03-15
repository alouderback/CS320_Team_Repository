package tutoringWebsite.model;

import java.util.ArrayList;
import tutoringWebsite.model.session;
import tutoringWebsite.model.tutor;


public class courses {
	
	private ArrayList<tutor> test;
	private session courseSession;
	
	public courses() {
		
	}

	public ArrayList<tutor> getTest() {
		return test;
	}

	public void setTest(ArrayList<tutor> test) {
		this.test = test;
	}

	public session getCourseSession() {
		return courseSession;
	}

	public void setCourseSession(session courseSession) {
		this.courseSession = courseSession;
	}
	
}