package tutoringWebsite.controllers;



import tutoringWebsite.model.Session;

public class SessionController{
	
	private Session model;
	
	public void setModel(Session model) {
		this.model = model; 
	}
	
	public void createSession( String room, String date, String tutor, String time) {
		 model.setRoom(room);
		 model.setDate(date);
		 model.setTime(time);
		 model.setTutor(tutor);
	}
	
	public Session getSession() {
		return  this.model;
		
	}
	
	
	
}