package tutoringWebsite.controllers;



import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class CourseController{
	
	private Course model;
	IDatabase db = new DerbyDatabase();
	public CourseController(Course model) {
		this.model = model; 
	}
	
	public void createCourse() {
		model.setTitle(model.getTitle());
		model.setTutorList(model.getTutorList());
		model.setCourseSession(model.getCourseSession());
	}
	
	public Course getCurseByCourseId(int courseid) {
		Course course = new Course();
		course =db.getCourse(courseid);
		return course;
	}
	public List<Course> getAllCourses(){
		return db.getAllCourses();
	}
}