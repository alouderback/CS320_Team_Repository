
package tutoringWebsite.model;

public class Announcement {
	private int announcementId;
	private String message;
	private String date;
	private String time;
	
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
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	public int getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	
}