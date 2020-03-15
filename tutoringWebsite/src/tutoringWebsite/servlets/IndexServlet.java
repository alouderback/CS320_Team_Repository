package tutoringWebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
		
		// check which button the user pressed
		if (req.getParameter("index") != null) {
			// call index JSP
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
		else if (req.getParameter("courses") != null) {
			// call courses JSP
			req.getRequestDispatcher("/_view/courses.jsp").forward(req, resp);
		}
		else if (req.getParameter("groups") != null) {
			// call groups JSP
			req.getRequestDispatcher("/_view/groups.jsp").forward(req, resp);
		}
		else if (req.getParameter("schedule") != null) {
			// call groups JSP
			req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
		}
		else if (req.getParameter("tutors") != null) {
			// call groups JSP
			req.getRequestDispatcher("/_view/tutors.jsp").forward(req, resp);
		}
		else if (req.getParameter("resources") != null) {
			// call groups JSP
			req.getRequestDispatcher("/_view/resources.jsp").forward(req, resp);
		}
		else if (req.getParameter("profile") != null) {
			// call groups JSP
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		else {
			throw new ServletException("Unknown command");
		}
	}
}
