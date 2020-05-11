package tutoringWebsite.model;

public class StudyGroup {
	private Course course;
	private Session session;
	private int sessionId;
	private int userId;
	
	public StudyGroup() {
		session = new Session();
		course = new Course();
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void delete() {
		course = null;
		session = null;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
}
