package tutoringWebsite.persist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tutoringWebsite.model.*;

public interface IDatabase {
	//list of functions used in Derby Database
	List<Login> useLogin(String email, String password);
	List<Login> signIntoAccount(String email, String password);
	List<Announcement> createAnnouncementStudyGroup(String message, LocalDate date, LocalTime time, int groupId);
	List<User> createAccount(String email, String password, String name, int userType);
	List<Announcement> createAnnouncementCourse(String message, LocalDate date, LocalTime time, int courseId);
	List<Announcement> createAnnouncementMainPage(String message, LocalDate date, LocalTime time);
}
