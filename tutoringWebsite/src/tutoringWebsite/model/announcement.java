
package tutoringWebsite.model;

public class announcement {
	private String message;
	private String date;
	private String time;
	
	// Constructor for class
	public announcement() {
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
	
}