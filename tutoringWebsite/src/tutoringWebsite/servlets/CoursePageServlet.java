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
import tutoringWebsite.model.Session;
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
		//model.setCourseSession(course.getCourseSession());
		System.out.println("Course ID:" + courseId);
		//model.setSessionId(course.getCourseSession().getSessionId());
		System.out.println("Session ID " + model.getSessionId());
		controller.getCourseTutors(courseId);
		String title = course.getTitle();
		ArrayList<User> tutorList = model.getTutorList();
		String daysOfWeek = controller.getDayOfWeek(courseId);
		
		List<String> times = controller.getTimes(courseId);
		
		List<Session> sessions = controller.getTutoringSessions(courseId);		
		
		for(int i = 0; i < sessions.size(); i++) {
			if (controller.getTutorName(sessions.get(i).getAdminId()) == null){
				sessions.get(i).setAdminName("User not found");
			}
			else {
				sessions.get(i).setAdminName(controller.getTutorName(sessions.get(i).getAdminId()));
			}
			if(controller.getCourseName(sessions.get(i).getCourseId()) == null ) {
				sessions.get(i).setCourseName("Course not found");
			}
			else {
				sessions.get(i).setCourseName(controller.getCourseName(sessions.get(i).getCourseId()));
			}
			if(sessions.get(i).getDayOfWeek() == 0) {
				sessions.get(i).setDayOfWeek(0);
			}
			else {
				sessions.get(i).setDaysOfWeekString(controller.getDayOfWeek(sessions.get(i).getCourseId()));
			}
		}
		
		req.setAttribute("title", title);
		req.setAttribute("tutorList", tutorList);
		req.setAttribute("daysOfWeek", daysOfWeek);
		req.setAttribute("times", times);
		req.setAttribute("sessions", sessions);
		
		
		req.getRequestDispatcher("/_view/coursePage.jsp").forward(req, resp);
			
		
	}
}
