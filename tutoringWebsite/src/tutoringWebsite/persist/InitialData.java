package tutoringWebsite.persist;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
			announcement.setAnnouncementId(announcementId++);
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
	
	public static List<Session> getSession() throws IOException {
		List<Session> sessionList = new ArrayList<Session>();
		ReadCSV readSession = new ReadCSV("Sessions.csv");
		try {
			Integer sessionId = 1;
			while(true) {
				
				List<String> tuple = readSession.next();
				if(tuple==null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Session session = new Session();
				session.setSessionID(sessionId++);
				
				LocalDate date = LocalDate.parse(i.next());
				
				session.setDate(date);
				session.setRoom(i.next());
				
				LocalTime time = LocalTime.parse(i.next());
				
				session.setTime(time);
				session.setTutorId(Integer.decode(i.next()));
				sessionList.add(session);
			}
			return sessionList;
		}finally {
			readSession.close();
		}
	}


}