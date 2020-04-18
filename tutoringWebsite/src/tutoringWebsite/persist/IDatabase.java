package tutoringWebsite.persist;

import java.util.List;

import tutoringWebsite.model.*;

public interface IDatabase {
	//list of functions used in Derby Database
	List<Login> createAccount(String email, String password);
	List<Announcement> createAnnouncementCourse(String message, String date, String time);
	List<Announcement> createAnnouncementStudyGroup(String message, String date, String time, int groupId);
	List<Login> signIntoAccount(String email, String password);
	List<Announcement> createAnnouncementCourse(String message, String group);		
}
