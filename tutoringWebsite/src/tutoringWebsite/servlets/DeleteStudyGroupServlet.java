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
		
		controller = new ScheduleController();
	
		String errorMessage = null;
		String stringOfSessionId = null;
		
		int sessionId = 0; //Will use this one
		int typeId = 2;
		int userId = 0;	
		
		//Creates a user which will be used to get the current user to check permissions
		User current = new User();
		
		//Gets the parameters listed on the jsp
		stringOfSessionId = req.getParameter("studyGroup");
		
		//Checks to see if a course ID was entered
		if(stringOfSessionId != null) {
			sessionId = Integer.parseInt(stringOfSessionId);
		}
		else {
			errorMessage = "Please select a viable ID.";
		}


		//Gets the current user; will be used to get permissions
		current = (User) req.getSession().getAttribute("user");
		
		if(current == null) {
			errorMessage = "Not logged in";
		}
		else {
			System.out.println("Current user name: " + current.getName());
			userId = current.getUser_Id();
			if(current.getUserType() == 1 || current.getUserType() == 2) {
				errorMessage = "User does not have permissions to delete groups.";
			}
		}
		
		req.setAttribute("errorMessage", errorMessage);
		
		//If there is no errorMessage, a session will be created. Otherwise, an error message will
		//pop up on the web page and a session will not be created
		if (errorMessage == null) {

			resp.sendRedirect(req.getContextPath() + "/deleteStudyGroup");
		}
		else {
		
			//System.out.println("Encountered an error: " + errorMessage);
			req.getRequestDispatcher("/_view/deleteStudyGroup.jsp").forward(req, resp);
		}
	}
}