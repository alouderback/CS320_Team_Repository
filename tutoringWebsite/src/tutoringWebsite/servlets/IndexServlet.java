package tutoringWebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.model.User;

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
		User current 		= new User();
		
		 req.getSession().getAttribute("user");
		
		System.out.println(req.getSession());
		System.out.println(req.getSession().getAttribute("user"));
		//req.getSession().setAttribute("user", current);
		System.out.println(current.getEmail());
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
		else if (req.getParameter("login") != null) {
			// call login JSP
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else if (req.getParameter("createAccount") != null) {
			// call login JSP
			req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
		}
		else {
			throw new ServletException("Unknown command");
		}
	}
}
