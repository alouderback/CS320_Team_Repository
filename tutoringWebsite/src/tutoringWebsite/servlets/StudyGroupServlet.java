package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.AnnouncementController;
import tutoringWebsite.controllers.StudyGroupController;

import tutoringWebsite.model.StudyGroup;
import tutoringWebsite.model.Announcement;
import tutoringWebsite.model.Session;

public class StudyGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Study Group Servlet: doGet");	 
		StudyGroup studyGroup = new StudyGroup();
		StudyGroupController controller = new StudyGroupController();
		controller.setStudyGroup(studyGroup);
		
		List<Session> studyGroupSessions = controller.getAllStudyGroups();
		
		for(Session session : studyGroupSessions) {
			session.setCourseName(controller.getCourseName(session.getCourseId()));
			session.setDaysOfWeekString(controller.getDayOfWeek(session.getSessionId()));
			session.setAdminName(controller.getTutorName(session.getAdminId()));;
		}
		
		req.setAttribute("sessions", studyGroupSessions);
		
		//printing announcements for study groups
		String errorMessage = null;
		AnnouncementController announcementController = new AnnouncementController();
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		try {
			announcements = (ArrayList<Announcement>) announcementController.getStudyGroupAnnouncements();
		}catch(NumberFormatException e){
			errorMessage = "try fail";
		}
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("announcements", announcements);
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/groups.jsp").forward(req, resp); 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Study Groups Servlet: doPost");
		

		System.out.println("Study Group Servlet: doGet");	 
		StudyGroup studyGroup = new StudyGroup();
		StudyGroupController controller = new StudyGroupController();
		controller.setStudyGroup(studyGroup);
		
		List<Session> studyGroupSessions = controller.getAllStudyGroups();
		
		for(Session session : studyGroupSessions) {
			session.setCourseName(controller.getCourseName(session.getCourseId()));
			session.setDaysOfWeekString(controller.getDayOfWeek(session.getSessionId()));
			session.setAdminName(controller.getTutorName(session.getAdminId()));;
		}
		
		req.setAttribute("sessions", studyGroupSessions);
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/groups.jsp").forward(req, resp); 
	
	}
}
