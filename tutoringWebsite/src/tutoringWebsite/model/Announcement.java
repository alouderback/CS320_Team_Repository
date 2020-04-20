
package tutoringWebsite.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Announcement {
	private int announcementId;
	private String message;
	private LocalDate date;
	private LocalTime time;
	private int announcementType;
	
	// Constructor for class
	public Announcement() {
	}
	
	//Sets everything to null
	public void delete() {
		message = null;
		date= null;
		time = null;
		announcementId = (Integer) null;
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

	public int getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}

	public int getAnnouncementType() {
		return announcementType;
	}

	public void setAnnouncementType(int announcementType) {
		this.announcementType = announcementType;
	}
	
}