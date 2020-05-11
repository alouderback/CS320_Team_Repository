
package tutoringWebsite.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Announcement {
	private int announcementId;
	private String message;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private int announcementType;// the id for session of study group
	private int typeId;
	//announcementType = 1 for session
	//announcementType = 2 for study group
	private String typeName;
	private Session session;
	private String courseName;
	
	// Constructor for class
	public Announcement() {
	}
	
	//Sets everything to null
	public void delete() {
		message = null;
		date= null;
		startTime = null;
		endTime = null;
		announcementType = -1;
		typeId = -1;
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
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalTime time) {
		this.startTime = time;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}