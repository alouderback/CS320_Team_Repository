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

public class TutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User model;
	private UserController controller;
	private Student model1;
	private StudentController controller1;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nTutorServlet: doGet");

		

		String errorMessage = null;
		User current 		= new User();
		User tutor 		= new User();
		Student student		= new Student();
		ArrayList<User> tutorList = new ArrayList<User>();
		boolean isStudent	= false;//will turn true i student

			model      = new User(); 
			controller = new UserController(model);
			model1      = new Student();
			controller1 = new StudentController(model1);
		
			tutorList = (ArrayList<User>) controller.getTutors();
			tutor = tutorList.get(0);
		
		
		System.out.println("setting attributes");	
		// Add parameters as request attributes
		
		//req.getSession().setAttribute("tutorList", tutorList);
		req.setAttribute("tutorList", tutorList);
	//req.setAttribute("tutor", tutor);

		req.getRequestDispatcher("/_view/tutors.jsp").forward(req, resp);
		
	}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {

			System.out.println("\nTutorServlet: doPost");
		// Add result objects as request attributes
		//req.setAttribute("errorMessage", errorMessage);
	
		

		// if login is valid, start a session
		System.out.println("  ");

			// store user object in session
			//currently stores onlt the name but but shuld we store the enitre class or should we store the name 
			//and a boolean true to say the user is validated as logged in???
			
			// redirect to /index page
						
		
		
		

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/tutors.jsp").forward(req, resp);
		
	}
}
