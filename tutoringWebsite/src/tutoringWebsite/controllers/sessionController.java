package tutoringWebsite.controllers;



import tutoringWebsite.model.session;

public class sessionController{
	
	private session model;
	
	public void setModel(session model) {
		this.model = model; 
	}
	
	public void createSession( String room, String date, String tutor, String time) {
		 model.setRoom(room);
		 model.setDate(date);
		 model.setTime(time);
		 model.setTutor(tutor);
	}
	
	public session getSession() {
		return  new session();
		
	}
	
	
	
}