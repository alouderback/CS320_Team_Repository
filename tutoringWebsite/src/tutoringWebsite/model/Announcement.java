
package tutoringWebsite.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Announcement {
	private String message;
	private LocalDate date;
	private LocalTime time;
	
	// Constructor for class
	public Announcement() {
	}
	
	//Sets everything to null
	public void delete() {
		message = null;
		date= null;
		time = null;
	}
	
	// Returns the current message in this announcement
	public String getMessage(){
		return message;
	}
	
	// Set the message of the announcement to the input
	public void setMessage(String message) {
		this.message = message;
	}
	
	// Returns the current date of the announcement
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
	
}