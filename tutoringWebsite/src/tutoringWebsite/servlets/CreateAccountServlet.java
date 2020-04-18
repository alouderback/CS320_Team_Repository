package tutoringWebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.LoginController;
import tutoringWebsite.model.Login;
import tutoringWebsite.model.User;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Login model;
	private LoginController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nCreateAccountServlet: doGet");

		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\ncreateAccount: doPost");

		String errorMessage = null;
		String email         = null;
		String pw           = null;
		String name		= null;
		String temp	= null;
		int userType = 1;
		User current		= new User();
		boolean validLogin  = false;
	

		// Decode form parameters and dispatch to controller
		email = req.getParameter("email");
		pw   = req.getParameter("password");
		name = req.getParameter("name");
		temp = req.getParameter("userType");
	
		System.out.println("   email: <" + email + "> PW: <" + pw + ">");		
	
		String student, tutor, faculty;
		student= "student";
		tutor = "tutor";
		faculty = "faculty";
		if(temp.contains(student)) {
			userType=1;
		}else if(temp.contentEquals(tutor)){
			userType = 2;
		}else if(temp.matches(faculty)) {
			userType = 3;
		}else {
			userType = 0;
		}
		
		System.out.println("   temp: <" + temp + "> userType: <" + userType + ">");	
		if (email == null || pw == null || email.equals("") || pw.equals("")) {
			errorMessage = "Please specify both username and password";
		} else {
			validLogin = true;
			model      = new Login();
			controller = new LoginController(model);
			
			
			
			current = controller.createAccount(email, pw, name ,userType);
			
		}

		// Add parameters as request attributes
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("userType", req.getParameter("UserType"));
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("createAccount",        validLogin);

		// if login is valid, start a session
		if (validLogin) {
			System.out.println("  Account created - starting session, redirecting to /index");

			// store user object in session
			req.getSession().setAttribute("user", current);
			System.out.println(req.getSession());
			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/index");

			return;
		}

		System.out.println("   Invalid login - returning to /createAccount");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}
}