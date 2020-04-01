package tutoringWebsite.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.LoginController;
import tutoringWebsite.model.login;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private login model;
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
		String name         = null;
		String pw           = null;
		String major		= null;
		String year 		= null;
		boolean validLogin  = false;
	

		// Decode form parameters and dispatch to controller
		name = req.getParameter("email");
		pw   = req.getParameter("password");
		major = req.getParameter("major");
		year   = req.getParameter("year");

		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");			

		if (name == null || pw == null || name.equals("") || pw.equals("")) {
			errorMessage = "Please specify both user name and password";
		} else {
			model      = new login();
			controller = new LoginController(model);
			
			validLogin = controller.createAccount(name, pw, major, year);

			 if (!validLogin) {
				errorMessage = "must be a ycp username";
			}
		}

		// Add parameters as request attributes
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("major", req.getParameter("major"));
		req.setAttribute("year", req.getParameter("year"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("createAccount",        validLogin);

		// if login is valid, start a session
		if (validLogin) {
			System.out.println("  Account created - starting session, redirecting to /index");

			// store user object in session
			req.getSession().setAttribute("user", name);

			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/index");

			return;
		}

		System.out.println("   Invalid login - returning to /createAccount");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}
}
