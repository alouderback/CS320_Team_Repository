package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.CourseController;
import tutoringWebsite.controllers.StudentController;
import tutoringWebsite.controllers.TutorFacultyController;
import tutoringWebsite.controllers.UserController;
import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Student;
import tutoringWebsite.model.TutorFaculty;
import tutoringWebsite.model.User;

public class TutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User model;
	private UserController controller;
	private TutorFaculty model1;
	private TutorFacultyController controller1;
	private Course model2;
	private CourseController controller2;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nTutorServlet: doGet");

		

		String errorMessage = null;
		User current 		= new User();
	
		Student student		= new Student();
		List<User> tutorList = new ArrayList<User>();
		List<Integer> courseIdList = new ArrayList<Integer>();
		List<Course> courseList = new ArrayList<Course>();
		List<Course> courseList2 = new ArrayList<Course>();
		List<Course> courseList3 = new ArrayList<Course>();
		
		boolean isStudent	= false;//will turn true i student

			model      = new User(); 
			controller = new UserController(model);
			model1      = new TutorFaculty();
			controller1 = new TutorFacultyController(model1);
			model2      = new Course();
			controller2 = new CourseController(model2);
			
			
		
			tutorList = (ArrayList<User>) controller.getTutors();//list of users
			for(User tutor : tutorList ) {//goes through each user
				
				courseIdList = controller1.getCourseidbyUserId(tutor.getUser_Id());	//for every user gets its corresponding curse ids
				for(int courseid : courseIdList ) {									//goes through course ids
					model2 = controller2.getCurseByCourseId(courseid);				//gets course from course id
					courseList.add(model2);											//add course to course list
					System.out.println(model2.getTitle());							//prints out title 
					
				}
			}
			
		
		System.out.println("setting attributes");	
		// Add parameters as request attributes
		
		//req.getSession().setAttribute("tutorList", tutorList);
		req.setAttribute("tutorList", tutorList);
		req.setAttribute("courseList", courseList);
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
