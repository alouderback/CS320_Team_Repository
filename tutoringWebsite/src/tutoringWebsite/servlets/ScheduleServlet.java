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

public class ScheduleServlet extends HttpServlet {
	private User model2;
	private UserController controller2;
	private Student model1;
	private StudentController controller1;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("schedule Servlet: doGet");	
		
		//printing announcements on schedule
		String errorMessage = null;
		AnnouncementController announcementController = new AnnouncementController();
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		try {
			announcements = (ArrayList<Announcement>) announcementController.getSessionAnnouncements();
		}catch(NumberFormatException e){
			errorMessage = "try fail";
		}
		for(int i = 0; i < announcements.size(); i++) {
			int num = 0;
			num = announcements.get(i).getTypeId();
			System.out.println("Type id: "+ num);
			String course = announcementController.getCourseName(num);
			announcements.get(i).setCourseName(course);
			System.out.println("Course Name" + course);
		}
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("announcements", announcements);
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("schedule Servlet: doPost");
		
		//print Announcements
		String errorMessage = null;
		AnnouncementController announcementController = new AnnouncementController();
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		try {
			announcements = (ArrayList<Announcement>) announcementController.getSessionAnnouncements();
		}catch(NumberFormatException e){
			errorMessage = "try fail";
		}
		for(int i = 0; i < announcements.size(); i++) {
			int num = 0;
			num = announcements.get(i).getTypeId();
			System.out.println("Type id: "+ num);
			String course = announcementController.getCourseName(num);
			announcements.get(i).setCourseName(course);
			System.out.println("Course Name" + course);
		}
		req.setAttribute("announcements", announcements);


		Schedule model = null;
		Announcement ann = null;

		String courseName = null;

		
		ScheduleController controller = new ScheduleController();
		DerbyDatabase db = new DerbyDatabase();
		
		controller.setModel(model);
		controller.setDB(db);
		
		ArrayList<Session> sessions = new ArrayList<Session>();
		////////////////////////////////////////////////
		int userType		= 0;
		User current 		= new User();
		//boolean isFaculty	= false; //will turn true if student
		
		current = (User) req.getSession().getAttribute("user");	
		//gets current user
		
		model2      = new User();
		controller2 = new UserController(model2);
		
		if(current != null) { //checks if a user is logged in
			System.out.println(" user is logged in");
			
			
		}
		////////////////////////////////////////////////
		// decode POSTed form parameters and dispatch to controller
		try {
			// check for errors in the form data before using is in a calculation
			if (req.getParameter("Submit") != null) {
				sessions = (ArrayList<Session>) controller.getScheduleWithDate("Submit");
			}
			else if(req.getParameter("SubmitW") != null){
				sessions = (ArrayList<Session>) controller.getScheduleWithDate("SubmitW");
			}
			else if(req.getParameter("SubmitM") != null) {
				sessions = (ArrayList<Session>) controller.getScheduleWithDate("SubmitM");
			}
			else if(req.getParameter("CreateSession") != null) {
				resp.sendRedirect(req.getContextPath() + "/createSession");
			}
			else if (req.getParameter("DeleteSession") != null) {
				resp.sendRedirect(req.getContextPath() + "/deleteSession");
			}
		
		} catch (NumberFormatException e) {
			errorMessage = "Try failed";
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
		
		System.out.println("SESSION SIZE: " + sessions.size());
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("sessions", sessions);
		
		//System.out.println("Session Size: " + sessions.size() + ", Session Tutor for First Session: " + sessions.get(1).getTutorId());
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s

	}