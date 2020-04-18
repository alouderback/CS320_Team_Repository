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


}