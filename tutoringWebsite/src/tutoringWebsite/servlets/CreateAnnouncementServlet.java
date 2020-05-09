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
import tutoringWebsite.persist.DerbyDatabase;

public class CreateAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Announcement Servlet: doPost");
		Announcement model = new Announcement();
		AnnouncementController controller = new AnnouncementController();
		String errorMessage = null;
		String date = null;
		String time = null;
		String message = null;
		int announcementType = -1;
		int typeId = -1;
		String temp = null;
		
		date = req.getParameter("Date");
		time = req.getParameter("Time");
		message = req.getParameter("Message");
		temp = req.getParameter("announcementType");
		
		String session, studyGroup;
		session= "Session";
		studyGroup = "Study Group";
		if(temp.contains(session)) {
			announcementType=1;
			System.out.print("\ntrue");
		}else if(temp.contentEquals(studyGroup)){
			announcementType = 2;
		}
		else {
			errorMessage = "Incorrect Announcement Type";
		}
		
		//controller.create(date, time, message, announcementType, typeId);
		req.setAttribute("errorMessage", errorMessage);
		
		req.getRequestDispatcher("/_view/announcement.jsp").forward(req, resp);
	}
}
