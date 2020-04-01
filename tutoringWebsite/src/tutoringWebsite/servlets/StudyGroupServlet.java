package tutoringWebsite.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutoringWebsite.controllers.StudyGroupController;
import tutoringWebsite.db.StudyGroupDB;
import tutoringWebsite.model.StudyGroup;
import tutoringWebsite.model.Session;

public class StudyGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Study Group Servlet: doGet");	 
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/groups.jsp").forward(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Study Groups Servlet: doPost");
		
		String error = null;
		
		StudyGroup model = new StudyGroup();
		StudyGroupController controller = new StudyGroupController();
		StudyGroupDB db = new StudyGroupDB();
		controller.setStudyGroup(model);
		controller.setDB(db);
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		try {
			ArrayList<StudyGroup> studyGroups = controller.getAllStudyGroups();
			if (req.getParameter("all") != null) {
				for(int i = 0; i < studyGroups.size(); i++) {
					System.out.println("getting all sessions " + studyGroups.get(i).getSession().toString());
					sessions.add(studyGroups.get(i).getSession());
				}
			}
			else if(req.getParameter("ECE260") != null) {
				for(int i = 0; i < studyGroups.size(); i++) {
					if(studyGroups.get(i).getSession().getCourse().getTitle().equals("ECE260")) {
						System.out.println("getting ECE260 sessions");
						sessions.add(studyGroups.get(i).getSession());
					}
				}
			}
			else if(req.getParameter("CS320") != null) {
				for(int i = 0; i < studyGroups.size(); i++) {
					if(studyGroups.get(i).getSession().getCourse().getTitle().equals("CS320")) {
						System.out.println("getting CS320 sessions");
						sessions.add(studyGroups.get(i).getSession());
					}
				}
			}
		} catch (NumberFormatException e) {
			error = "Try failed";
		}
		
		System.out.println("***** NUMBER OF STUDY GROUPS: " + sessions.size() +  " *****");
		req.setAttribute("error", error);
		req.setAttribute("sessions", sessions);
		System.out.println("loaded param, refreshing page " + sessions.toString());
		req.getRequestDispatcher("/_view/groups.jsp").forward(req, resp);
	}
}
