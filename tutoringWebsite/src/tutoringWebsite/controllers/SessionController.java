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
	
	public void createSession( String room, LocalDate date, int tutor, LocalTime time) {
		 model.setRoom(room);
		 model.setDate(date);
		 model.setTime(time);
		 model.setTutorId(tutor);
	}
	
	public Session getSession() {
		return  this.model;
		
	}
	
	
	
}