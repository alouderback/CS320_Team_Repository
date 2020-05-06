package tutoringWebsite.persist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tutoringWebsite.model.*;

public interface IDatabase {
	//list of functions used in Derby Database
	Integer createAnnouncement(String message, LocalDate date, LocalTime time, int announcementType, int typeId);
	List<Announcement> getAnnouncementsforSessionWithSessionId(int sessionId);
	List<Announcement> getAnnouncementsforStudyGroupWithStudyGroupId(int studyGroupId);
	List<Announcement> getAllAnnouncements();
	List<Announcement> removeAnnouncement(int announcementId);
	List<User> useLogin(String email, String password);
	User deleteAccount(String email, String password);
	List<User> createAccount(String email, String password, String name, int userType);
	List<Session> getScheduleByDate(String timeframe);
	List<User> getAccount(String email, String password);
	List<Session> deleteSession(int sessionId);

	List<User> getTutors();
	List<Student> getStudent(String email, String password);
	List<Student> createStudent(String major, String year, int userid);
	Student deleteStudent(String email, String password);
	List<Announcement> getAllStudyGroupAnnouncements();
	List<Announcement> getAllSessionAnnouncements();
	List<Session> createSession(String room, LocalDate date, int adminId, LocalTime startTime, LocalTime endTime,
		int dayOfWeek, int courseId, int typeId);
	List<String> getDayOfWeek(int sessionId);
}
