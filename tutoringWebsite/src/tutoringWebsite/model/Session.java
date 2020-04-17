package tutoringWebsite.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
	
	
	private String room;
	private LocalDate date;
	private Tutor tutor;
	private LocalTime time;
	
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

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
		
}
	