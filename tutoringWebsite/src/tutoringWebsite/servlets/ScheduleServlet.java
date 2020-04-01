package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.model.*;
import tutoringWebsite.controllers.*;

public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("courses Servlet: doGet");	 
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/courses.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("schedule Servlet: doPost");
		

		String errorMessage = null;

		Schedule model = null;
		
		ScheduleController controller = new ScheduleController();

		controller.setModel(model);
		
		// decode POSTed form parameters and dispatch to controller
		try {
			String scheduleButton = getInitParameter(req.getParameter("schedule"));

			
			
			// check for errors in the form data before using is in a calculation
			if (scheduleButton == "schedule") {
				
			}
			
			
			else {
				
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("title", req.getParameter("title"));
		req.setAttribute("tutorList", req.getParameter("tutorList"));
		req.setAttribute("courseSession", req.getParameter("courseSession"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("course", model);
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/courses.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s

	}