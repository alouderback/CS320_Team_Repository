package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.StudentController;
import tutoringWebsite.controllers.UserController;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Student;
import tutoringWebsite.model.User;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User model;
	private UserController controller;
	private Student model1;
	private StudentController controller1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nProfileServlet: doGet");
		User current = (User) req.getSession().getAttribute("user");
		
		if(current != null) {
			controller = new UserController(current);
			List<Session> studyGroupSessions = controller.getStudyGroups(current.getUser_Id());
			List<String> groupList = new ArrayList<String>();
			
			for(Session studyGroup : studyGroupSessions) {
				groupList.add(studyGroup.getCourseName() + " " + studyGroup.getSessionId());
			}
			req.setAttribute("groupList", groupList);
		}
		
		
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nProfile Servlet: doPost");

		String errorMessage = null;
		String email         = null;
		String pw           = null;
		int userType		= 0;
		User current 		= new User();
		boolean validLogin  = true;//should be false to if deleted
		boolean isLogin		= false;//will turn true if loggined in
		boolean isStudent	= false;//will turn true i student
		boolean logOut		= false;
		String major = null;
		String year = null;
		Student student		= new Student();
		

		// Decode form parameters and dispatch to controller
	
		current = (User) req.getSession().getAttribute("user");	//gets user
		student = (Student) req.getSession().getAttribute("student"); // if student gets student
		req.getParameter("deleteAccount");
		req.getParameter("logOut");
		model      = new User();
		controller = new UserController(model);
		model1      = new Student();
		controller1 = new StudentController(model1);
	
			
	
		if(current != null) {
			System.out.println(" user is logged in");
			email = current.getEmail();
			pw   = current.getPassword();
			userType = current.getUserType();
			
			if(userType == 1|| userType == 2) {
				System.out.println(" user is student");
				isStudent = true;
				major = req.getParameter(student.getMajor());
				year = req.getParameter(student.getYear());
			}
			System.out.println(" validating credentials....");
			System.out.println("   Email: <" + email + "> PW: <" +pw + ">");	
			isLogin = controller.validateCredentials(email, pw);
			System.out.println(isLogin);
			if(isLogin && req.getParameter("deleteAccount")!=null ) {
				if(isStudent) {
					controller1.removeStudentt(email, pw);
				}
				controller.removeAccount(current);
				System.out.println(" account removed");
				validLogin = controller.validateCredentials(email, pw);
				System.out.println(validLogin);

				System.out.println("setting attributes");	
			}else {
				errorMessage = "user is logging out";
				validLogin = false;
			}
			
		}else {
			errorMessage = "not logged in";
		}
			// Add parameters as request attributes
	
 
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("deleteAccount", validLogin);
		req.setAttribute("logOut", logOut);
		req.setAttribute("isStudent", isStudent);
		//req.setAttribute("user",        current);

		// if login is valid, start a session 
		if (!validLogin) {
			System.out.println("   Account Deleted - starting session, redirecting to /index");

			// store user object in session
			//currently stores onlt the name but but shuld we store the enitre class or should we store the name 
			//and a boolean true to say the user is validated as logged in???
			req.getSession().setAttribute("user", null);
			req.getSession().setAttribute("isFaculty", null);
			if(isStudent) {
				req.getSession().setAttribute("student", null);
				req.getSession().setAttribute("isAStudent", false);
			}
			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/announcement");

			return;
		}
		
		System.out.println("   Invalid deletion - returning to /profile");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
}
