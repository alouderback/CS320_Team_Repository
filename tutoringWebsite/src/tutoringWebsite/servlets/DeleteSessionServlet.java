package tutoringWebsite.servlets;

import java.io.IOException;



import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.model.*;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.controllers.*;

public class DeleteSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//doGet display the page and current tutoring sessions that can be deleted
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("DeleteScheduleServlet: doGet");	 
		
		ScheduleController controller = new ScheduleController();
		
		//This segment of code gets all sessions and 'filters out' any sessions that are not tutoring sessions
		ArrayList<Session> preSessions = new ArrayList<Session>();
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		preSessions = (ArrayList<Session>) controller.getAllSessions();
				
		for (int j = 0; j < preSessions.size(); j++) {
			if(preSessions.get(j).getTypeId() == 1) {
				sessions.add(preSessions.get(j));
			}
		}
		//
		
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
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/deleteSession.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("DeleteSessionServlet: doPost");

		String errorMessage = null;


		Schedule model = null;
		String courseName = null;
		
		User current = new User();
		int sessionId = 0;
		int counter = 0;
		String sessionIdString = null;
		String submit = null;
		
		
		ScheduleController controller = new ScheduleController();
		DerbyDatabase db = new DerbyDatabase();
		
		controller.setModel(model);
		controller.setDB(db);
		
		sessionIdString = req.getParameter("sessionId");
		
		//Checks to see if the session id box is filled
		if((sessionIdString == "") || (sessionIdString == null) || (sessionIdString.length() == 0)) {
			errorMessage = "Please enter a session ID.";
			System.out.println("Session ID box is blank ");
		}
		else {
			sessionId = Integer.parseInt(sessionIdString);
		}
		
		submit = req.getParameter("submit");
		
		ArrayList<Session> preSessions = new ArrayList<Session>();
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		preSessions = (ArrayList<Session>) controller.getAllSessions();
		
		//Only gets the tutoring sessions
		for (int j = 0; j < preSessions.size(); j++) {
			if(preSessions.get(j).getTypeId() == 1) {
				sessions.add(preSessions.get(j));
			}
		}
		
		//This loop will set the names of the tutors, the name of the courses, and the days of the week
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
			if(sessions.get(i).getSessionId() == sessionId) {
				counter = counter + 1;
			}
		}
		
		//Makes sure the user picked a viable session id
		if(counter == 1) {
			System.out.println("Picked a viable session id... about to delete...");
		}
		else {
			errorMessage = "Please enter viable session ID.";
		}
		
		//Gets current user
		current = (User) req.getSession().getAttribute("user");
		
		//Checks to see if user has permissions to make change
		if(current == null) {
			errorMessage = "Not logged in";
		}
		else {
			System.out.println("Current user name: " + current.getName());
			if(current.getUserType() == 0 || current.getUserType() ==1) {
				errorMessage = "User does not have permissions to make changes";
			}
			
		}
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("sessions", sessions);
		
		//System.out.println("Session Size: " + sessions.size() + ", Session Tutor for First Session: " + sessions.get(1).getTutorId());
		// Forward to view to render the result HTML document
		if(errorMessage == null) {
			controller.deleteSession(sessionId);
		}
		
			doGet(req, resp);
			//'Refreshes' the page so that new list of tutoring sessions are printed out
		
	}

	// gets double from the request with attribute named s

	}