package tutoringWebsite.model;

import java.time.LocalDate;

import java.time.LocalTime;
import tutoringWebsite.persist.*;


public class Session {
	
	
	private String room;
	private LocalDate date;
	private int tutorId;
	private LocalTime time;
	private String course;
	private int sessionId;
	
	
	public Session() {
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public int getTutorId() {
		return tutorId;
	}

	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
		
}
	