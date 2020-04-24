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
	List<Announcement> removeAnnouncement(int announcementId, int announcementType);
	List<User> useLogin(String email, String password);
	List<User> deleteAccount(String email, String password);
	List<User> getAccount(String email, String password);
	List<User> createAccount(String email, String password, String name, int userType);
	List<Session> getScheduleByDate(String timeframe);
}
