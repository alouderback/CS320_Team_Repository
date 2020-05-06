package tutoringWebsite.controllers;

import java.util.ArrayList;


import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.StudyGroup;

public class StudyGroupController {
	private StudyGroup model;
	
	public void setStudyGroup(StudyGroup model) {
		this.model = model;
	}
	/*public void setDB(StudyGroupDB db) {
		this.db = db;
	}*/
	
	public Course getCourse() {
		return model.getCourse();
	}
	
	public void setCourse(Course course) {
		model.setCourse(course);
	}
	
	public Session getSession() {
		return model.getSession();
	}
	
	public void setSession(Session session) {
		model.setSession(session);
	}
	
	/*public void addModelToDB() {
		db.addStudyGroup(model);
	}*/
	
	public void deleteModel() {
		//db.deleteStudyGroup(model);
		model.delete();
	}
	
	/*public ArrayList<StudyGroup> getAllStudyGroups(){
		System.out.println("returning all groups");
		return db.getStudyGroups();
	}
	
	public void setAllStudyGroups(ArrayList<StudyGroup> studyGroups) {
		db.setStudyGroups(studyGroups);
	}*/
}
