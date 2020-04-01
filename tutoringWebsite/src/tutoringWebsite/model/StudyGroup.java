package tutoringWebsite.model;

public class StudyGroup {
	private Course course;
	private Session session;
	
	public StudyGroup() {}
	
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
}
