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
		
		try {
			announcements = (ArrayList<Announcement>) controller.getAnnouncements();
			model = announcements.get(0);
			System.out.println(model.getAnnouncementId() + model.getMessage());
		}
		catch (NumberFormatException e) {
			errorMessage = "Try failed";
		}
		
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("announcements", announcements);
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
