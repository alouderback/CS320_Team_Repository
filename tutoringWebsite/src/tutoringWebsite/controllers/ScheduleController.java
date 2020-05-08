package tutoringWebsite.controllers;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Course;
import tutoringWebsite.model.Schedule;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.InitialData;

public class ScheduleController{
	private Schedule model;
	private DerbyDatabase db = new DerbyDatabase();
	private InitialData initialData;
	
	public void setModel(Schedule model) {
		this.model = model;
	}
	
	public void setDB(DerbyDatabase db) {
		this.db = db;
	}
	
	//Returns a list of all sessions
	public List<Session> getAllSessions() {
		return db.getAllSessions();
	}
	
	//Deletes a session in the session table; takes userId as a parameter
	public void deleteSession(int id) {
		db.deleteSession(id);
	}
	
	//Creates a session in the session table
	public void addSession(String room, LocalDate date, int adminId, LocalTime startTime, LocalTime endTime, int dayOfWeek, int courseId, int typeId) {
		db.createSession(room, date, adminId, startTime, endTime, dayOfWeek, courseId, typeId);
	}
	
	//When given an adminId (same thing as userId in this case; different from adminId in TutorFaculty),
	// it returns the name of the user; will be used to get creator/tutor names for tutoring sessions in the schedule
	public String getTutorName(int adminId) {
		List<User> user = new ArrayList<User>();
		user = db.getUserFromUserId(adminId);
		return user.get(0).getName();
		
	}
	
	public String getCourseName(int courseId) {
		List<Course> course = new ArrayList<Course>();
		user = 
	}
	
	//Given a timeframe (value of buttons on the jsp), the method will return a list of sessions
	public List<Session> getScheduleWithDate(String timeframe) {
		//model.setSchedule(timeframe);
		return db.getScheduleByDate(timeframe);
	}
}