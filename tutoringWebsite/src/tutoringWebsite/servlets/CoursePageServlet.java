package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.CourseController;
import tutoringWebsite.model.Course;
import tutoringWebsite.model.User;

public class CoursePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("CoursePage Servlet: doGet");
		Course model = new Course();
		CourseController controller = new CourseController(model);
		ArrayList<String> courseStringList = Collections.list(req.getParameterNames());
		String courseIdString = courseStringList.get(0);
		int courseId = Integer.parseInt(courseIdString);
		Course course = controller.getCurseByCourseId(courseId);
		model.setCourseSession(course.getCourseSession());
		System.out.println("Course ID:" + courseId);
		controller.getCourseTutors(courseId);
		String title = course.getTitle();
		ArrayList<User> tutorList = model.getTutorList();
		List<String> daysOfWeek = controller.getDayOfWeek();
		//String startTime = course.getCourseSession().getStartTime().toString();
		//String endTime = course.getCourseSession().getEndTime().toString();
		
		req.setAttribute("title", title);
		req.setAttribute("tutorList", tutorList);
		req.setAttribute("daysOfWeek", daysOfWeek);
		//req.setAttribute("startTime", startTime);
		//req.setAttribute("endTime", endTime);
		
		req.getRequestDispatcher("/_view/coursePage.jsp").forward(req, resp);
			
		
	}
}
