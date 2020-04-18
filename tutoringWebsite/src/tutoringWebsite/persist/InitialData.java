package tutoringWebsite.persist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tutoringWebsite.model.*;

public class InitialData {
	public static List<Announcement> getAnnouncement() throws IOException {
		List<Announcement> announcementList = new ArrayList<Announcement>();
		ReadCSV readAnnouncement = new ReadCSV("Announcements.csv");
		try {
			// auto-generated primary key for authors table
			Integer announcementId = 1;
			while (true) {
				List<String> tuple = readAnnouncement.next();
				if (tuple == null) {
					break;
		}
			Iterator<String> i = tuple.iterator();
			Announcement announcement = new Announcement();
			Announcement.setAnnouncementId(announcementId++);
			announcement.setMessage(i.next());
			announcement.setDate(i.next());
			announcement.setTime(i.next());
			announcementList.add(announcement);
		}
			return announcementList;
		} finally {
				readAnnouncement.close();
			}
		}

	
	public static List<User> getUser() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUser = new ReadCSV("Users.csv");
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			Integer userType = 1;
			String temp;
			
			while (true) {
				List<String> tuple = readUser.next();
				if (tuple == null) {
					break;
		}
			Iterator<String> i = tuple.iterator();
			User user = new User();
			user.setUser_Id(userId++);
			user.setEmail(i.next());
			user.setPassword(i.next());
			user.setName(i.next());
			userType = Integer.getInteger(i.next());
			user.setUserType(userType);
			userList.add(user);
		}
			return userList;
		} finally {
				readUser.close();
			}
		}



}