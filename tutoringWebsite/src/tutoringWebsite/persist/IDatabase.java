package tutoringWebsite.persist;

import java.util.List;

import tutoringWebsite.model.*;

public interface IDatabase {
	//list of functions used in Derby Database

		List<Announcement> createAnnouncementStudyGroup(String message, String group);
		List<Announcement> createAnnouncementCourse(String message, String date, String time);
		List<Announcement> createAnnouncementStudyGroup(String message, String date, String time);
		List<User> createAccount(String email, String password, String name, int userType);
		List<Login> useLogin(String email, String password);	
}
