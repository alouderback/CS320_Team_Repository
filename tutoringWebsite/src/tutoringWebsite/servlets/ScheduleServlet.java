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

public class ScheduleServlet extends HttpServlet {
	private User model2;
	private UserController controller2;
	private Student model1;
	private StudentController controller1;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("schedule Servlet: doGet");	 
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("schedule Servlet: doPost");
		

		String errorMessage = null;


		Schedule model = null;

		String courseName = null;

		
		ScheduleController controller = new ScheduleController();
		DerbyDatabase db = new DerbyDatabase();
		
		controller.setModel(model);
		controller.setDB(db);
		
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		////////////////////////////////////////////////
		int userType		= 0;
		User current 		= new User();
		boolean isFaculty	= false; //will turn true if student
		
		current = (User) req.getSession().getAttribute("user");	
		//gets current user
		
		model2      = new User();
		controller2 = new UserController(model2);
		
		if(current != null) { //checks if a user is logged in
			System.out.println(" user is logged in");
			userType = current.getUserType();
			if(userType == 3) { //type three is faulty; checking to see if logged in user is faculty
				System.out.println("User is faculty");
				isFaculty = true;
			}
			
		}
		
		
		////////////////////////////////////////////////
		// decode POSTed form parameters and dispatch to controller
		try {
			//String date = getInitParameter(req.getParameter("date"));
			
			
			// check for errors in the form data before using is in a calculation
			if (req.getParameter("Submit") != null) {
				sessions = (ArrayList<Session>) controller.getScheduleWithDate("Submit");
			}
			else if(req.getParameter("SubmitW") != null){
				sessions = (ArrayList<Session>) controller.getScheduleWithDate("SubmitW");
			}
			else if(req.getParameter("SubmitM") != null) {
				sessions = (ArrayList<Session>) controller.getScheduleWithDate("SubmitM");
			}
		
		} catch (NumberFormatException e) {
			errorMessage = "Try failed";
		}
		
	
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("sessions", sessions);
		
		//System.out.println("Session Size: " + sessions.size() + ", Session Tutor for First Session: " + sessions.get(1).getTutorId());
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s

	}