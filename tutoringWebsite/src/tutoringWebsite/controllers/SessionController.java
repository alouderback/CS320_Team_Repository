package tutoringWebsite.controllers;



import java.time.LocalDate;
import java.time.LocalTime;

import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class SessionController{
	
	private Session model;
	IDatabase db = new DerbyDatabase();
	
	public void setModel(Session model) {
		this.model = model; 
	}
	public void createSession(LocalDate date, String room, LocalTime startTime, LocalTime endTime, int dayOfWeek, int adminId, int courseId, int typeId) {
		 db.createSession(room, date, adminId, startTime, endTime, dayOfWeek, courseId, typeId);
	}
	
	public Session getSession() {
		return  this.model;
		
	}
	
	
	
}