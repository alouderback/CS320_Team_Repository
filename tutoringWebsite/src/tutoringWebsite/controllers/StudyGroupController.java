package tutoringWebsite.controllers;

import java.util.ArrayList;
import java.util.List;

import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.StudyGroup;
import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class StudyGroupController {
	private StudyGroup model;
	IDatabase db = new DerbyDatabase();
	
	public void setStudyGroup(StudyGroup model) {
		this.model = model;
	}
	/*public void setDB(StudyGroupDB db) {
		this.db = db;
	}*/
	
	public Course getCourse() {
		return model.getCourse();
	}
	
	public void setCourse(Course course) {
		model.setCourse(course);
	}
	
	public Session getSession() {
		return model.getSession();
	}
	
	public void setSession(Session session) {
		model.setSession(session);
	}
	
	
	public void deleteModel() {
		//db.deleteStudyGroup(model);
		model.delete();
	}
	
	public List<Session> getAllStudyGroups(){
		return db.getAllStudyGroupSessions();
	}
	
	public String getCourseName(int courseId) {
		List<Course> course = new ArrayList<Course>();
		course = db.getCourseFromCourseId(courseId);
		if (course.size() == 0) {
			return "Course not found";
		}
		return course.get(0).getTitle();
	}
	
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
	
	public String getTutorName(int adminId) {
		List<User> user = new ArrayList<User>();
		user = db.getUserFromUserId(adminId);
		return user.get(0).getName();
		
	}
	
	public List<StudyGroup> joinStudyGroup(int userId) {
		return db.joinStudyGroups(userId, model.getSession().getSessionId());
	}
	
}
