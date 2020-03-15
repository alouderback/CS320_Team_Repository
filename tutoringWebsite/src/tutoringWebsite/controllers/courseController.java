package tutoringWebsite.controllers;



import java.util.ArrayList;

import tutoringWebsite.model.courses;
import tutoringWebsite.model.session;
import tutoringWebsite.model.tutor;

public class courseController{
	
	private courses model;
	
	public void setModel(courses model) {
		this.model = model; 
	}
	
	public void createCourse() {
		model.setTitle(model.getTitle());
		model.setTutorList(model.getTutorList());
		model.setCourseSession(model.getCourseSession());
	}
	
	
}