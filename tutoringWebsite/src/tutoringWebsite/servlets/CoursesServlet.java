package tutoringWebsite.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;
import tutoringWebsite.controllers.CourseController;
import tutoringWebsite.controllers.SessionController;

public class CoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("courses Servlet: doGet");	 
		Course model = new Course();
		CourseController controller = new CourseController(model);
		
		req.setAttribute("courses", (ArrayList<Course>)controller.getAllCourses());
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/courses.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("courses Servlet: doPost");	 
		
		
	
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/courses.jsp").forward(req, resp); 
	}
	
	
	
}
