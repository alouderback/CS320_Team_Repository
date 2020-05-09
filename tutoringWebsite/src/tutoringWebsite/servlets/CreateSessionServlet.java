package tutoringWebsite.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.*;
import tutoringWebsite.model.*;

public class CreateSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session model;
	private SessionController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nCreateSessionServlet: doGet");

		req.getRequestDispatcher("/_view/createSession.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\ncreateSession: doPost");
		
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
		int typeId = 1;
		int userId = 0;
		
		int month = 0;
		int day = 0;
		int year = 0;
		
		LocalDate startDate = LocalDate.now();
		LocalTime startTime = LocalTime.now();
		LocalTime endTime = LocalTime.now();	
		
		User current = new User();
		
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
		
		
		
		if(sunday != null) { //1
			System.out.println("******* WE GOT A SUN *******" + sunday);
			daysOfWeek = daysOfWeek + 1;
		}
		if(monday != null) { //2
			System.out.println("******* WE GOT A MON *******" + monday);
			daysOfWeek = daysOfWeek + 2;
		}
		if(tuesday != null) { //4
			System.out.println("******* WE GOT A TUE *******" + tuesday);
			daysOfWeek = daysOfWeek + 4;
		}
		if(wednesday != null) { //8
			System.out.println("******* WE GOT A WED *******");
			daysOfWeek = daysOfWeek + 8;
		}
		if(thursday != null) { //16
			System.out.println("******* WE GOT A THU *******");
			daysOfWeek = daysOfWeek + 16;
		}
		if(friday != null) { //32
			System.out.println("******* WE GOT A FRI *******");
			daysOfWeek = daysOfWeek + 32;
		}
		if(saturday != null) { //64
			System.out.println("******* WE GOT A SAT *******");
			daysOfWeek = daysOfWeek + 64;
		}
		
		
		if(stringOfCourseId != null) {
			courseId = Integer.parseInt(stringOfCourseId);
		}
		else {
			errorMessage = "Please select a course.";
		}
		
		
		if((startDateString != null) && (startDateString.length() == 10)) {
			month = Integer.parseInt(startDateString.substring(0, 2));
			day = Integer.parseInt(startDateString.substring(3, 5));
			year = Integer.parseInt(startDateString.substring(6));
			
			startDate = LocalDate.of(year, month, day);
		}
		else {
			errorMessage = "Please enter a correctly formatted date";
		}
		
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
		
		current = (User) req.getSession().getAttribute("user");
		
		if(current == null) {
			errorMessage = "Not logged in";
		}
		else {
			System.out.println("Current user name: " + current.getName());
			userId = current.getUser_Id();
			if((current.getUserType() == 1) || (current.getUserType() == 0)) {
				errorMessage = "User does not have permissions to make changes.";
			}
			
		}
	
		System.out.println("Start Date: " + startDateString);
		System.out.println("Room: " + room);
		System.out.println("Start Time: " + startTime.toString());
		System.out.println("End Time: " + endTime.toString());
		System.out.println("Days Of Week Num: " + daysOfWeek);
		System.out.println("User Id:" + userId);
		System.out.println("Course Id: " + courseId);
		System.out.println("Type Id: " + typeId);
		
		req.setAttribute("errorMessage", errorMessage);
		
		if (errorMessage == null) {
			controller.createSession(startDate, room, startTime, endTime, daysOfWeek, userId, courseId, typeId);
			req.getRequestDispatcher("/_view/createSession.jsp").forward(req, resp);
		}
		else {
			System.out.println("Encountered an error: " + errorMessage);
			req.getRequestDispatcher("/_view/createSession.jsp").forward(req, resp);
		}
	}
}