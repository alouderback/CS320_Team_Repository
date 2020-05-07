package tutoringWebsite.controllers;

import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.model.TutorFaculty;
import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;
import tutoringWebsite.persist.RDatabase;
import tutoringWebsite.persist.readOnlyDatabase;

public class TutorFacultyController {
	private TutorFaculty model = null;

	
	
	//DatabaseProvider.setInstance(new DerbyDatabase());
	//IDatabase db = DatabaseProvider.getInstance();
	
	IDatabase db = new DerbyDatabase();
	RDatabase read = new readOnlyDatabase();
	//List<User> authorBookList = db.createAccount(email, password, major, year);
	
	public TutorFacultyController(TutorFaculty model) {
		this.model = model;
	}
	
	public List<Integer> getUserIdbyCourseId(int courseid) {
		List<Integer> userid = new ArrayList<Integer>();
		System.out.println(courseid);
		userid = db.getUserIdbyCourseId(courseid);
		
		return userid;
	}
	
	public List<Integer> getCourseidbyUserId(int userid) {
		List<Integer> courseid = new ArrayList<Integer>();
		System.out.println(userid);
		courseid = db.getCourseidbyUserId(userid);
		return courseid;
	}
	
	
}