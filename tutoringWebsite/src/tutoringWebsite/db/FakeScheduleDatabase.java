package tutoringWebsite.db;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.Tutor;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class FakeScheduleDatabase {
	
	static private ArrayList<Session> sessionList;
	
	public FakeScheduleDatabase() {
		sessionList = new ArrayList<Session>();
		
		// Add initial data
		readInitialData();
		
		System.out.println(sessionList.size());
	}
	

	public void readInitialData() {
		Session session1 = new Session();
		Tutor tutor1 = new Tutor();
		Course course1 = new Course();
		tutor1.setName("Eric Bosse");
		course1.setTitle("CS101");
		session1.setDate(LocalDate.of(2020, 04, 01));
		session1.setRoom("KEC 125");
		session1.setTime(LocalTime.of(18, 00));
		session1.setTutor(tutor1);
		session1.setCourse(course1);
		
		Session session2 = new Session();
		Tutor tutor2 = new Tutor();
		Course course2 = new Course();
		tutor2.setName("Caryn Sims");
		course2.setTitle("CS201");
		session2.setDate(LocalDate.of(2020, 04, 01));
		session2.setRoom("KEC 127");
		session2.setTime(LocalTime.of(20, 00));
		session2.setTutor(tutor2);
		session2.setCourse(course2);
		
		Session session3 = new Session();
		Tutor tutor3 = new Tutor();
		Course course3 = new Course();
		tutor3.setName("Alex Louderback");
		course3.setTitle("PHY160");
		session3.setDate(LocalDate.of(2020,04,02));
		session3.setRoom("KEC 125");
		session3.setTime(LocalTime.of(18,00));
		session3.setTutor(tutor3);
		session3.setCourse(course3);
		
		Session session4 = new Session();
		Tutor tutor4 = new Tutor();
		tutor4.setName("Isabelle Hoffmann");
		session4.setDate(LocalDate.of(2020, 04, 02));
		session4.setRoom("KEC 127");
		session4.setTime(LocalTime.of(20, 00));
		session4.setTutor(tutor4);
		session4.setCourse(course1);
		
		Session session5 = new Session();
		session5.setDate(LocalDate.of(2020, 04, 29));
		session5.setRoom("KEC 127");
		session5.setTime(LocalTime.of(20, 00));
		session5.setTutor(tutor4);
		session5.setCourse(course2);
		
		sessionList.add(session1);
		sessionList.add(session2);
		sessionList.add(session3);
		sessionList.add(session4);
		sessionList.add(session5);
	}
	
	
//Returns a list of sessions
	public ArrayList<Session> findScheduleByDate(String timeframe) {
		ArrayList<Session> result = new ArrayList<Session>();
		
		int[] currentDateArray = getCurrentDay();
		System.out.println("Month: " + currentDateArray[0] + ", Day: " + currentDateArray[1] + ", Year: " + currentDateArray[2]);
		System.out.println("***** " + sessionList.size() + " *****");
		int[] placeholderDate = new int[3];
		
		if(timeframe == "Submit") {
			for (int i = 0; i < sessionList.size(); i++) {
				//placeholderDate = breakDateDown(sessionList.get(i).getDate());
				placeholderDate[0] = sessionList.get(i).getDate().getMonthValue();
				placeholderDate[1] = sessionList.get(i).getDate().getDayOfMonth();
				placeholderDate[2] = sessionList.get(i).getDate().getYear();
				System.out.println("Month:" + currentDateArray[0] + "." + ", Day:" + currentDateArray[1] + "." + ", Year:" + currentDateArray[2] + ".");
				System.out.println("(Database) Month:" + placeholderDate[0] + "." + ", Day:" + placeholderDate[1] + "." + ", Year:" + placeholderDate[2] + ".");
				
				if ((placeholderDate[0] == currentDateArray[0]) && (placeholderDate[1] == currentDateArray[1]) && (placeholderDate[2] == currentDateArray[2])) {
					result.add(sessionList.get(i));
				}
			}
			System.out.println("Result List Size: " + result.size());
		}
		else if(timeframe == "SubmitW") {
			result.add(sessionList.get(0));
			result.add(sessionList.get(1));
			result.add(sessionList.get(2));
			result.add(sessionList.get(3));
		}
		else if(timeframe == "SubmitM") {
			result.add(sessionList.get(0));
			result.add(sessionList.get(1));
			result.add(sessionList.get(2));
			result.add(sessionList.get(3));
			result.add(sessionList.get(4));
		}
		
		System.out.println(result.size());
		
		if (result.size() == 0) {
			Session session = new Session();
			session.setDate(null);
			session.setRoom(" ");
			session.setTime(null);
			session.setTutor(null);
			result.add(session);
		}
		return result;
	}
	
	public static int[] breakDateDown(String date) {
		String tempDateString = date;
		
		int day = 0;
		int month = 0;
		
		for (int i = 0; i < date.length(); i++) {
			if(date.charAt(i) == '/') {
				String tempString = date.substring(0, i);
				System.out.println("(Beg) Starting String: " + tempString);
				int tempInt = Integer.parseInt(tempString);
				
				if (month == 0) {
					month = tempInt;
				}
				else {
					day = tempInt;
				}
				
				date = date.substring((i+1));
				System.out.println("(End) String About to be analyzed: " + date);
				i = 0;
			}
		}
		
		String yearString = tempDateString.substring((tempDateString.length()-4), (tempDateString.length()));
		int year = Integer.parseInt(yearString);
		
		int[] result = new int[] {month, day, year};
		
		return result;
	}
	
	public static int[] getCurrentDay() {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime current = LocalDateTime.now();
		String dateString = date.format(current);
		
		String tempDateString = dateString;
		
		int day = 0;
		int month = 0;
		
		for (int i = 0; i < dateString.length(); i++) {
			if(dateString.charAt(i) == '/') {
				String tempString = dateString.substring(0, i);
				System.out.println("(Beg) Starting String: " + tempString);
				int tempInt = Integer.parseInt(tempString);
				
				if (month == 0) {
					month = tempInt;
				}
				else {
					day = tempInt;
				}
				
				dateString = dateString.substring((i+1));
				System.out.println("(End) String About to be analyzed: " + dateString);
				i = 0;
			}
		}
		
		String yearString = tempDateString.substring((tempDateString.length()-4), (tempDateString.length()));
		int year = Integer.parseInt(yearString);
		
		int[] result = new int[] {month, day, year};
		
		return result;
	}


}
