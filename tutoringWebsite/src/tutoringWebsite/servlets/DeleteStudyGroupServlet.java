package tutoringWebsite.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class DeleteStudyGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session model;
	private ScheduleController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\n DeleteSessionServlet: doGet");
		
		ArrayList<Session> preSessions = new ArrayList<Session>();
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		preSessions = (ArrayList<Session>) controller.getAllSessions();
				
		for (int j = 0; j < preSessions.size(); j++) {
			if(preSessions.get(j).getTypeId() == 2) {
				sessions.add(preSessions.get(j));
			}
		}
		
		for(int i = 0; i < sessions.size(); i++) {
			if (controller.getTutorName(sessions.get(i).getAdminId()) == null){
				sessions.get(i).setAdminName("User not found");
			}
			else {
				sessions.get(i).setAdminName(controller.getTutorName(sessions.get(i).getAdminId()));
			}
			if(controller.getCourseName(sessions.get(i).getCourseId()) == null ) {
				sessions.get(i).setCourseName("Course not found");
			}
			else {
				sessions.get(i).setCourseName(controller.getCourseName(sessions.get(i).getCourseId()));
			}
			if(sessions.get(i).getDayOfWeek() == 0) {
				sessions.get(i).setDayOfWeek(0);
			}
			else {
				sessions.get(i).setDaysOfWeekString(controller.getDayOfWeek(sessions.get(i).getSessionId()));
			}
		}
		
		req.setAttribute("sessions", sessions);

		req.getRequestDispatcher("/_view/deleteStudyGroup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nDeleteStudyGroupServlet: doPost");
		/*
		model = new Session();
		controller = new SessionController();
		
		controller.setModel(model);
		
		
		String errorMessage = null;
		String startDateString = null;
		String startTimeString = null;
		String endTimeString = null;
		String room = null;
		String monday = null;
		String tuesday = null;
		String wednesday = null;
		String thursday = null;
		String friday = null;
		String saturday = null;
		String sunday = null;
		String stringOfCourseId = null;
		
		int courseId = 0; //Will use this one
		int daysOfWeek = 0;
		int typeId = 2;
		int userId = 0;
		
		int month = 0;
		int day = 0;
		int year = 0;
		
		//Initializing LocalDate and LocalTime variables
		LocalDate startDate = LocalDate.now();
		LocalTime startTime = LocalTime.now();
		LocalTime endTime = LocalTime.now();	
		
		//Creates a user which will be used to get the current user to check permissions
		User current = new User();
		
		//Gets the parameters listed on the jsp
		startDateString = req.getParameter("date");
		startTimeString = req.getParameter("startTime");
		endTimeString = req.getParameter("endTime");
		room = req.getParameter("room");
		sunday = req.getParameter("sunday");
		monday = req.getParameter("monday");
		tuesday = req.getParameter("tuesday");
		wednesday = req.getParameter("wednesday");
		thursday = req.getParameter("thursday");
		friday = req.getParameter("friday");
		saturday = req.getParameter("saturday");
		stringOfCourseId = req.getParameter("course");
		
		
		
		//Checks to see if a course ID was entered
		if(stringOfCourseId != null) {
			courseId = Integer.parseInt(stringOfCourseId);
		}
		else {
			errorMessage = "Please select a course.";
		}
		
		//Check length of date; all dates should be ten long
		if((startDateString != null) && (startDateString.length() == 10)) {
			month = Integer.parseInt(startDateString.substring(0, 2));
			day = Integer.parseInt(startDateString.substring(3, 5));
			year = Integer.parseInt(startDateString.substring(6));
			
			//Gives the local date
			startDate = LocalDate.of(year, month, day);
		}
		else {
			errorMessage = "Please enter a correctly formatted date";
		}
		
		//Checks for the different sizes a time can be (ie 9:45 vs 10:45) and checks to make sure a valid time is entered
		if ((startTimeString.length() == 5) && (((Integer.parseInt(startTimeString.substring(0, 2)) >= 0) && (Integer.parseInt(startTimeString.substring(0, 2)) < 24))) && ((Integer.parseInt(startTimeString.substring(3)) >= 0) && (Integer.parseInt(startTimeString.substring(3)) < 60) )) {
			startTime = LocalTime.parse(startTimeString);
			System.out.println("StartTime formatter output : " + startTime.toString());
		}
		else if ((startTimeString.length() == 4) && (((Integer.parseInt(startTimeString.substring(0, 1)) >= 0) && (Integer.parseInt(startTimeString.substring(0, 1)) < 10))) && ((Integer.parseInt(startTimeString.substring(2)) >= 0) && (Integer.parseInt(startTimeString.substring(2)) < 60) ) && (startTimeString.charAt(1) == ':')) {
			String temp = String.join("", "0", startTimeString);
			startTime = LocalTime.parse(temp);
			System.out.println("StartTime formatter output : " + startTime.toString());
		}
		else {
			errorMessage = "Please enter correct start time";
		}
		
		//Checks for the different sizes a time can be and checks to make sure a valid time is entered
		if ((endTimeString.length() == 5) && (((Integer.parseInt(endTimeString.substring(0, 2)) >= 0) && (Integer.parseInt(endTimeString.substring(0, 2)) < 24))) && ((Integer.parseInt(endTimeString.substring(3)) >= 0) && (Integer.parseInt(endTimeString.substring(3)) < 60) )) {
			endTime = LocalTime.parse(endTimeString);
			System.out.println("EnndTime formatter output : " + endTime.toString());
		}
		else if ((endTimeString.length() == 4) && (((Integer.parseInt(endTimeString.substring(0, 1)) >= 0) && (Integer.parseInt(endTimeString.substring(0, 1)) < 10))) && ((Integer.parseInt(endTimeString.substring(2)) >= 0) && (Integer.parseInt(endTimeString.substring(2)) < 60) ) && (endTimeString.charAt(1) == ':')) {
			String temp = String.join("", "0", endTimeString);
			endTime = LocalTime.parse(temp);
			System.out.println("StartTime formatter output : " + endTime.toString());
		}
		else {
			errorMessage = "Please enter correct end time";
		}
		
		//Gets the current user; will be used to get permissions
		current = (User) req.getSession().getAttribute("user");
		
		if(current == null) {
			errorMessage = "Not logged in";
		}
		else {
			System.out.println("Current user name: " + current.getName());
			userId = current.getUser_Id();
		}
		
		req.setAttribute("errorMessage", errorMessage);
		
		//If there is no errorMessage, a session will be created. Otherwise, an error message will
		//pop up on the web page and a session will not be created
		if (errorMessage == null) {

			resp.sendRedirect(req.getContextPath() + "/groups");
		}
		else {
		*/
			//System.out.println("Encountered an error: " + errorMessage);
			req.getRequestDispatcher("/_view/deleteStudyGroup.jsp").forward(req, resp);
		//}
	}
}