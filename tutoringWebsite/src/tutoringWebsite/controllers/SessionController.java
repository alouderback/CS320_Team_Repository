package tutoringWebsite.controllers;



import java.time.LocalDate;
import java.time.LocalTime;

import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;

public class SessionController{
	
	private Session model;
	
	public void setModel(Session model) {
		this.model = model; 
	}
	
	public void createSession( String room, LocalDate date, int tutorId, LocalTime time, String course) {
		 model.setRoom(room);
		 model.setDate(date);
		 model.setTime(time);
		 model.setCourse(course);
		 //model.setTutorId(tutor.getUser_Id());
	}
	
	public Session getSession() {
		return  this.model;
		
	}
	
	
	
}