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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("DeleteScheduleServlet: doGet");	 
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/deleteSession.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("DeleteSessionServlet: doPost");
		

		String errorMessage = null;


		Schedule model = null;

		String courseName = null;

		
		ScheduleController controller = new ScheduleController();
		DerbyDatabase db = new DerbyDatabase();
		
		controller.setModel(model);
		controller.setDB(db);
		
		ArrayList<Session> preSessions = new ArrayList<Session>();
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		preSessions = (ArrayList<Session>) controller.getAllSessions();
		
		for (int j = 0; j < preSessions.size(); j++) {
			if(preSessions.get(j).getTypeId() == 1) {
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
				sessions.get(i).setDaysOfWeekString(controller.getDayOfWeek(sessions.get(i).getDayOfWeek()));
			}
		}
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("sessions", sessions);
		
		//System.out.println("Session Size: " + sessions.size() + ", Session Tutor for First Session: " + sessions.get(1).getTutorId());
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/deleteSession.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s

	}