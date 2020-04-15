package tutoringWebsite.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/courses.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("courses Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		Session courseSession = null;
		
		//numbers controller and model
		Course model = new Course();
		CourseController controller = new CourseController();
		SessionController controller1 = new SessionController();
		controller.setModel(model);
		
		// decode POSTed form parameters and dispatch to controller
		try {
			String title = getInitParameter(req.getParameter("title"));
			String dateString = getInitParameter(req.getParameter("date"));
			String timeString = getInitParameter(req.getParameter("time"));
			String room = getInitParameter(req.getParameter("room"));
			String tutorString = getInitParameter(req.getParameter("tutor"));
			
			
			// check for errors in the form data before using is in a calculation
			if (title == null || dateString == null||timeString ==null||room == null) {
				errorMessage = "Please specify three numbers";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				Tutor tutor = new Tutor();
				tutor.setName(tutorString);
				
				LocalDate date = LocalDate.of(2020,04,01);
				
				LocalTime time = LocalTime.of(03, 49);
				
				model.setTitle(title);
				controller1.createSession(room, date, tutor, time);
				courseSession = controller1.getSession();
				ArrayList<Session> courseSessions = new ArrayList<Session>();
				courseSessions.add(courseSession);
				model.setCourseSession(courseSessions);
				controller.createCourse();
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
	private ArrayList getArrayListFromParameter(ArrayList s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return s;
					//Double.parseDouble(s);
		}
	}
	
	
	
}
