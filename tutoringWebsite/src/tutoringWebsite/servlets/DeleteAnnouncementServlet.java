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

public class DeleteAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("DeleteScheduleServlet: doGet");	 
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/deleteAnnouncement.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("DeleteAnnouncementServlet: doPost");
		

		String errorMessage = null;


		Announcement model = null;
		String courseName = null;
		
		User current = new User();
		int courseId = 0;
		int counter = 0;
		String announcementId = null;
		
		AnnouncementController controller = new AnnouncementController();
		DerbyDatabase db = new DerbyDatabase();
		
		controller.setModel(model);
		controller.setDB(db);
		
		announcementId = req.getParameter("announcementId");
		
		if((announcementId == "") || (announcementId == null) || (announcementId.length() == 0)) {
			errorMessage = "Please enter an Announcement ID.";
			System.out.println("Announcement ID box is blank ");
		}
		else {
			courseId = Integer.parseInt(announcementId);
		}
		
		ArrayList<Announcement> preAnnouncements = new ArrayList<Announcement>();
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		
		preAnnouncements = (ArrayList<Announcement>) controller.getAnnouncements();
		for (int j = 0; j < preAnnouncements.size(); j++) {
				announcements.add(preAnnouncements.get(j));
				if(announcements.get(j).getAnnouncementId() == courseId) {
					counter = counter + 1;
				}

		}
	
		if(counter == 1) {
			System.out.println("Picked a viable Announcement ID... about to delete...");
		}
		else {
			errorMessage = "Please enter viable Announcement ID.";
			//announcements = (ArrayList<Announcement>) controller.getAnnouncements();
		}
		
		current = (User) req.getSession().getAttribute("user");
		
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
		req.setAttribute("announcements", announcements);
		req.setAttribute("announcementId", courseId);
		
		//System.out.println("Session Size: " + announcements.size() + ", Session Tutor for First Session: " + announcements.get(1).getTutorId());
		// Forward to view to render the result HTML document
		if(errorMessage == null) {
			controller.delete(courseId);
			resp.sendRedirect(req.getContextPath() + "/index");
		}
		else {
			req.getRequestDispatcher("/_view/deleteAnnouncement.jsp").forward(req, resp);
		}
	}

	// gets double from the request with attribute named s

	}