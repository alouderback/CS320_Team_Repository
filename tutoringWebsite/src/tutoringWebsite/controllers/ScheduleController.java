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
		System.out.println("In Schedule Controller");
		return db.getAllTutoringSessions();
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
	
	//Gets a string for the course name given a course Id
	public String getCourseName(int courseId) {
		List<Course> course = new ArrayList<Course>();
		course = db.getCourseFromCourseId(courseId);
		if (course.size() == 0) {
			return "Course not found";
		}
		return course.get(0).getTitle();
	}
	
	//Returns a single string of weekdays given a sessionId ("Sunday, Monday, Thursday")
	public String getDayOfWeek(int sessionId) {
		ArrayList weekString = new ArrayList();
		weekString = (ArrayList<String>)db.getDayOfWeek(sessionId);
		System.out.println("WeekList Size:" + weekString.size());
		String temp = null;
		String monday = null;
		String tuesday = null;
		String wednesday = null;
		String thursday = null;
		String friday = null;
		String saturday = null;
		String sunday = null;
		
		if(sessionId == 0) {
			return "Days of week not found";
		}
		
		for (int i = 0; i < weekString.size(); i++) {
			System.out.println("In weekString: " + weekString.get(i));
			if(weekString.get(i) == "Sunday") {
				if(temp != null) {
					sunday = ", Sunday";
					temp = temp.concat(sunday);
				}
				else {
					sunday = "Sunday";
					temp = sunday;
				}
			}
			//Goes through arrayList to check each day and concatinates them
			if(weekString.get(i) == "Monday") {
				if(temp != null) {
					monday = ", Monday";
					temp = temp.concat(monday);
				}
				else {
					monday = "Monday";
					temp = monday;
				}
			}
			if(weekString.get(i) == "Tuesday") {
				if(temp != null) {
					tuesday = ", Tuesday";
					temp = temp.concat(tuesday);
				}
				else {
					tuesday = "Tuesday";
					temp = tuesday;
				}	
			}
			if(weekString.get(i) == "Wednesday") {
				if(temp != null) {
					wednesday = ", Wednesday";
					temp = temp.concat(wednesday);
				}
				else {
					wednesday = "Wednesday";
					temp = wednesday;
				}
			}
			if(weekString.get(i) == "Thursday") {
				if(temp != null) {
					thursday = ", Thursday";
					temp = temp.concat(thursday);
				}
				else {
					thursday = "Thursday";
					temp = thursday;
				}
			}
			if(weekString.get(i) == "Friday") {
				if(temp != null) {
					friday = ", Friday";
					temp = temp.concat(friday);
				}
				else {
					friday = "Friday";
					temp = friday;
				}
			}
			if(weekString.get(i) == "Saturday") {
				if(temp != null) {
					saturday = ", Saturday";
					temp = temp.concat(saturday);
				}
				else {
					saturday = "Saturday";
					temp = saturday;
				}
			}
		}
		return temp;
	}
	
	//Given a timeframe (value of buttons on the jsp), the method will return a list of sessions
	public List<Session> getScheduleWithDate(String timeframe) {
		//model.setSchedule(timeframe);
		return db.getScheduleByDate(timeframe);
	}
}