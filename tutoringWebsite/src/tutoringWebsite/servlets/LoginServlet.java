package tutoringWebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.StudentController;
import tutoringWebsite.controllers.UserController;
import tutoringWebsite.model.Student;
import tutoringWebsite.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User model;
	private UserController controller;
	private Student model1;
	private StudentController controller1;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doGet");

		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doPost");

		String errorMessage = null;
		String email         = null;
		String pw           = null;
		User current 		= new User();
		boolean validLogin  = false;
		Student student		= new Student();
		int userType		= 0;
		String major = null;
		String year = null;
		boolean isStudent	= false;//will turn true i student
		boolean isFaculty  = false;
		// Decode form parameters and dispatch to controller
		email = req.getParameter("email");
		pw   = req.getParameter("password");

		System.out.println("   Email: <" + email + "> PW: <" + pw + ">");			

		if (email == null || pw == null || email.equals("") || pw.equals("")) {
			errorMessage = "Please specify both user name and password";
		} else {
			model      = new User(); 
			controller = new UserController(model);
			model1      = new Student();
			controller1 = new StudentController(model1);
			
			validLogin = controller.validateCredentials(email, pw);
			System.out.println("account accessed");	
		
			if(validLogin) {
				current    = controller.getAccount(email, pw);
				System.out.println("got user");	
				userType = current.getUserType();
				if(userType == 1|| userType == 2) {
					isStudent = true;
					System.out.println(" user is student");
					student = controller1.getStudent(email, pw);
					major = req.getParameter(student.getMajor());
					year = req.getParameter(student.getYear());
					System.out.println("  Major: <" + major + "> Year: <" + year + ">");	
				}
			
				if(userType == 3) { //type three is faulty; checking to see if logged in user is faculty
					System.out.println("User is faculty");
					isFaculty = true;
				}
				
			}
		
			else {
				errorMessage = "Username and/or password invalid";
			}
		}
		System.out.println("setting attributes");	
		// Add parameters as request attributes
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("password", req.getParameter("password"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("login",        validLogin);
		

		// if login is valid, start a session
		if (validLogin) {
			System.out.println("   Valid login - starting session, redirecting to /index");

			// store user object in session
			//currently stores only the name but but should we store the entire class or should we store the name 
			//and a boolean true to say the user is validated as logged in???
			req.getSession().setAttribute("user", current);

			req.getSession().setAttribute("isAStudent", isStudent);
			req.getSession().setAttribute("isFaculty", isFaculty);

			if(isStudent) {
			req.getSession().setAttribute("student",student);
			}
			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/announcement");

			return;
		}
		
		System.out.println("   Invalid login - returning to /Login");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}
