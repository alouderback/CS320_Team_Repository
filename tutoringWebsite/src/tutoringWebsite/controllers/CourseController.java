package tutoringWebsite.controllers;



import java.util.ArrayList;

import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;

public class CourseController{
	
	private Course model;
	
	public void setModel(Course model) {
		this.model = model; 
	}
	
	public void createCourse() {
		model.setTitle(model.getTitle());
		model.setTutorList(model.getTutorList());
		model.setCourseSession(model.getCourseSession());
	}
	
	
}