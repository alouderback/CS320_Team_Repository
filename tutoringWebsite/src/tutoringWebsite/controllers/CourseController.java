package tutoringWebsite.controllers;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;
import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class CourseController{
	
	private Course model;
	IDatabase db = new DerbyDatabase();
	public CourseController(Course model) {
		this.model = model;
	}
	
	public void createCourse() {
		model.setTitle(model.getTitle());
		model.setTutorList(model.getTutorList());
		model.setCourseSession(model.getCourseSession());
	}
	
	public Course getCurseByCourseId(int courseid) {
		Course course = new Course();
		course =db.getCourse(courseid);
		return course;
	}
	public List<Course> getAllCourses(){
		return db.getAllCourses();
	}
	
	public void getCourseTutors(int courseId){
		List<Integer> tutorIds = new ArrayList<Integer>();
		List<User> tutors = new ArrayList<User>();
		tutorIds = db.getTutors(courseId);
		
		for(int tutorId : tutorIds) {
			
			tutors.add(db.getSingleUser(tutorId));
			
		}
		
		model.setTutorList((ArrayList<User>)tutors);
	}
	
	public String getDayOfWeek(int courseId) {
		List<String> weekString = new ArrayList<String>();
		System.out.println(db.getSessionIdWithCourseId(courseId).get(0));
		model.setSessionId(db.getSessionIdWithCourseId(courseId).get(0));
		
		weekString = db.getDayOfWeek(model.getSessionId());
		System.out.println("WeekList Size:" + weekString.size());
		String temp = null;
		String monday = null;
		String tuesday = null;
		String wednesday = null;
		String thursday = null;
		String friday = null;
		String saturday = null;
		String sunday = null;
		
		if(courseId == 0) {
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
	
	public List<String> getTimes(int courseId) {
		String pattern = "hh:mm a";
		List<Session> courseSessions = db.getCourseSession(courseId);
		List<String> times = new ArrayList<String>();
		for(Session session : courseSessions) {
			times.add(session.getStartTime().format(DateTimeFormatter.ofPattern(pattern)) + " - " + session.getEndTime().format(DateTimeFormatter.ofPattern(pattern)));
		}
		return times;
	}
	
	public List<Session> getTutoringSessions(int courseId){
		List<Session> tutoringSessions = db.getTutoringSession(courseId);
		
		for(Session session : tutoringSessions) {
			System.out.println("Tutoring Session ID: " + session.getSessionId());
		}
		
		return tutoringSessions;
	}
	
	//eric's code, see ScheduleController
	public String getTutorName(int adminId) {
		List<User> user = new ArrayList<User>();
		user = db.getUserFromUserId(adminId);
		return user.get(0).getName();
		
	}
	
	//eric's code, see ScheduleController
	public String getCourseName(int courseId) {
		List<Course> course = new ArrayList<Course>();
		course = db.getCourseFromCourseId(courseId);
		if (course.size() == 0) {
			return "Course not found";
		}
		return course.get(0).getTitle();
	}
	
}