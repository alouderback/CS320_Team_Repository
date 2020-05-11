package tutoringWebsite.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.AnnouncementController;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;

public class CreateAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nCreateAnnouncementServlet: doGet");

		req.getRequestDispatcher("/_view/createAnnouncement.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println(" Create Announcement Servlet: doPost");
		Announcement model = new Announcement();
		AnnouncementController controller = new AnnouncementController();
		String errorMessage = null;
		String date = null;
		String startTime = null;
		String endTime = null;
		String message = null;
		int announcementType = -1;
		int courseId = -1;
		String temp = null;
		String course = null;
		int month = -1;
		int day = -1;
		int year = -1;
		int userId = -1;
		
		LocalDate aDate = LocalDate.now();
		LocalTime aTime = LocalTime.now();
		LocalTime bTime = LocalTime.now();
		
		date = req.getParameter("date");
		startTime = req.getParameter("startTime");
		endTime = req.getParameter("endTime");
		message = req.getParameter("message");
		temp = req.getParameter("announcementType");
		course = req.getParameter("course");
				
		//check announcementType
		String session, studyGroup;
		session= "Session";
		studyGroup = "Study Group";
		if(req.getParameter("announcementType") == session) {
			announcementType=1;
			System.out.print("\ntrue");
		}else if(req.getParameter("announcementType") == studyGroup){
			announcementType = 2;
		}
		else if(req.getParameter("announcementType") == null){
			errorMessage = "Please select an Announcement type";
		}
		else {//if message is blank
			errorMessage = "Please enter a message for the Announcement";
		}
		
		//checks the course selected
		if(course != null) {
			courseId = Integer.parseInt(course);
		}
		else{
			errorMessage = "Please select a course";
		}
		
		//checks the date put in
		if((date != null) && (date.length() == 10)) {
			month = Integer.parseInt(date.substring(0, 2));
			day = Integer.parseInt(date.substring(3, 5));
			year = Integer.parseInt(date.substring(6));
			
			aDate = LocalDate.of(year, month, day);
		}
		else {
			errorMessage = "Please enter a correctly formatted date";
		}
		
		//checks start time
		if ((startTime.length() == 5) && (((Integer.parseInt(startTime.substring(0, 2)) >= 0) && (Integer.parseInt(startTime.substring(0, 2)) < 24))) && ((Integer.parseInt(startTime.substring(3)) >= 0) && (Integer.parseInt(startTime.substring(3)) < 60) )) {
			aTime = LocalTime.parse(startTime);
			System.out.println("StartTime formatter output : " + aTime.toString());
		}
		else if ((startTime.length() == 4) && (((Integer.parseInt(startTime.substring(0, 1)) >= 0) && (Integer.parseInt(startTime.substring(0, 1)) < 10))) && ((Integer.parseInt(startTime.substring(2)) >= 0) && (Integer.parseInt(startTime.substring(2)) < 60) ) && (startTime.charAt(1) == ':')) {
			String temp2 = String.join("", "0", startTime);
			aTime = LocalTime.parse(temp2);
			System.out.println("StartTime formatter output : " + aTime.toString());
		}
		else {
			errorMessage = "Please enter correct start time";
		}
		
		//checks end time
		if ((endTime.length() == 5) && (((Integer.parseInt(endTime.substring(0, 2)) >= 0) && (Integer.parseInt(endTime.substring(0, 2)) < 24))) && ((Integer.parseInt(endTime.substring(3)) >= 0) && (Integer.parseInt(endTime.substring(3)) < 60) )) {
			bTime = LocalTime.parse(endTime);
			System.out.println("EnndTime formatter output : " + bTime.toString());
		}
		else if ((endTime.length() == 4) && (((Integer.parseInt(endTime.substring(0, 1)) >= 0) && (Integer.parseInt(endTime.substring(0, 1)) < 10))) && ((Integer.parseInt(endTime.substring(2)) >= 0) && (Integer.parseInt(endTime.substring(2)) < 60) ) && (endTime.charAt(1) == ':')) {
			String temp2 = String.join("", "0", endTime);
			bTime = LocalTime.parse(temp);
			System.out.println("StartTime formatter output : " + bTime.toString());
		}
		else {
			errorMessage = "Please enter correct end time";
		}
		
		//checks user is logged in/ correct user
		User current = new User();
		current = (User) req.getSession().getAttribute("user");
		
		if(current == null) {
			errorMessage = "Not logged in";
		}
		else {
			System.out.println("Current user name: " + current.getName());
			userId = current.getUser_Id();
			if((current.getUserType() == 1) || (current.getUserType() == 0)) {
				errorMessage = "User does not have permission to make Announcements.";
			}
			
		}
		
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("typeId", courseId);
		//req.setAttribute("message", message);
		if (errorMessage == null) {
			controller.create(aDate, aTime, bTime, message, announcementType, courseId);
			System.out.println("Announcement Created");
			resp.sendRedirect(req.getContextPath() + "/index");
		}
		else {
			System.out.println("Encountered an error: " + errorMessage);
			req.getRequestDispatcher("/_view/createAnnouncement.jsp").forward(req, resp);
		}
	}
}
