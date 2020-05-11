package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.AnnouncementController;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Session;
import tutoringWebsite.persist.DerbyDatabase;

public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Announcement Servlet: doGet");
		
		String errorMessage = null;
		Announcement model = new Announcement();
		
		AnnouncementController controller = new AnnouncementController();
		controller.setModel(model);
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		String typeName = null;
		String courseName = null;
		
		try {
			announcements = (ArrayList<Announcement>) controller.getAnnouncements();
			typeName = model.getTypeName();
			//courseName = controller.getCourseName(model.getTypeId());
		}
		catch (NumberFormatException e) {
			errorMessage = "Try failed";
		}
		for(int i = 0; i < announcements.size(); i++) {
			int num = 0;
			num = announcements.get(i).getTypeId();
			System.out.println("Type id: "+ num);
			String course = controller.getCourseName(num);
			announcements.get(i).setCourseName(course);
			System.out.println("Course Name" + course);
		}
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("announcements", announcements);
		req.setAttribute("typeName", typeName);
		if (req.getParameter("createAnnouncement") != null) {
			req.getRequestDispatcher("/_view/createAnnouncement.jsp").forward(req, resp);
			//resp.sendRedirect(req.getContextPath() + "/createAnnouncement");
		}
		else if(req.getParameter("deleteAnnouncement") != null) {
			req.getRequestDispatcher("/_view/deleteAnnouncement.jsp").forward(req, resp);
		}
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Announcement Servlet: doPost");
	}
}
