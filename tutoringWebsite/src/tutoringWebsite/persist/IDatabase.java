package tutoringWebsite.persist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tutoringWebsite.model.*;

public interface IDatabase {
	//list of functions used in Derby Database
	List<User> useLogin(String email, String password);
	User deleteAccount(String email, String password);
	List<Announcement> createAnnouncementStudyGroup(String message, String date, String time, int groupId);
	List<User> createAccount(String email, String password, String name, int userType);
	List<Announcement> createAnnouncementCourse(String message, String date, String time);
	List<Session> getScheduleByDate(String timeframe);
	List<User> getAccount(String email, String password);
	List<Session> deleteSession(int sessionId);
	List<Session> createSession(final String room, final LocalDate date, final int tutorId, final LocalTime time, final String course);
	List<User> getTutors();
}
