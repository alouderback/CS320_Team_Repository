package tutoringWebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.UserController;


import tutoringWebsite.model.User;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User model;
	private UserController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nProfileServlet: doGet");

		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nProfile Servlet: doPost");

		String errorMessage = null;
		String email         = null;
		String pw           = null;
		User current 		= new User();
		boolean validLogin  = true;
		boolean isLogin		= false;
		
		

		// Decode form parameters and dispatch to controller
		
		current = (User) req.getSession().getAttribute("user");
		System.out.println("   Email: <" + email + "> PW: <" + pw + ">");			
		model      = new User();
		controller = new UserController(model);
		if(current != null) {
		email = req.getParameter(current.getEmail());
		pw   = req.getParameter(current.getPassword());
		isLogin = controller.validateCredentials(email, pw);
		}
		if(isLogin) {
			controller.removeAccount(current);
			validLogin = controller.validateCredentials(email, pw);
			System.out.println("setting attributes");	
		}
		else {
			errorMessage = "not logged in";
		}
		// Add parameters as request attributes
	
 
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("deleteAccount", validLogin);
		//req.setAttribute("user",        current);

		// if login is valid, start a session 
		if (!validLogin) {
			System.out.println("   Account Deleted - starting session, redirecting to /index");

			// store user object in session
			//currently stores onlt the name but but shuld we store the enitre class or should we store the name 
			//and a boolean true to say the user is validated as logged in???
			req.getSession().setAttribute("user", null);

			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/index");

			return;
		}
		
		System.out.println("   Invalid deletion - returning to /profile");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
}
