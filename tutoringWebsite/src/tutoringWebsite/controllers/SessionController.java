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
	
	public void createSession( String room, LocalDate date, int tutorId, LocalTime time) {
		 model.setRoom(room);
		 model.setDate(date);
		 model.setTime(time);
		 model.setTutorId(tutorId);
	}
	
	public Session getSession() {
		return  this.model;
		
	}
	
	
	
}