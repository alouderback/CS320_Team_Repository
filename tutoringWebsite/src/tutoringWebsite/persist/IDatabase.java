package tutoringWebsite.persist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tutoringWebsite.model.*;

public interface IDatabase {
	//list of functions used in Derby Database
	List<Login> useLogin(String email, String password);
	List<Login> signIntoAccount(String email, String password);
	Integer createAnnouncement(String message, LocalDate date, LocalTime time, int announcementType, int typeId);
	List<User> createAccount(String email, String password, String name, int userType);
	List<Announcement> getAnnouncementsforSessionWithSessionId(int sessionId);
	List<Announcement> getAnnouncementsforStudyGroupWithStudyGroupId(int studyGroupId);
	List<Announcement> getAllAnnouncements();
}
