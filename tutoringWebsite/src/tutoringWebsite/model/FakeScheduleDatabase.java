package tutoringWebsite.model;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tutoringWebsite.model.session;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;


public class FakeScheduleDatabase {
	
	static private ArrayList<session> sessionList;
	
	public FakeScheduleDatabase() {
		sessionList = new ArrayList<session>();
		
		// Add initial data
		readInitialData();
		
		System.out.println(sessionList.size());
	}
	

	public void readInitialData() {
		session session1 = new session();
		session1.setDate("04/01/2020");
		session1.setRoom("KEC 125");
		session1.setTime("18:00");
		session1.setTutor("Eric Bosse");
		
		session session2 = new session();
		session2.setDate("04/01/2020");
		session2.setRoom("KEC 127");
		session2.setTime("20:00");
		session2.setTutor("Caryn Sims");
		
		session session3 = new session();
		session3.setDate("04/02/2020");
		session3.setRoom("KEC 125");
		session3.setTime("18:00");
		session3.setTutor("Alex Louderback");
		
		session session4 = new session();
		session4.setDate("04/02/2020");
		session4.setRoom("KEC 127");
		session4.setTime("20:00");
		session4.setTutor("Isabelle Hoffmann");
		
		session session5 = new session();
		session5.setDate("04/29/2020");
		session5.setRoom("KEC 127");
		session5.setTime("20:00");
		session5.setTutor("Isabelle Hoffman");
		
		sessionList.add(session1);
		sessionList.add(session2);
		sessionList.add(session3);
		sessionList.add(session4);
		sessionList.add(session5);
	}
	
	
//Returns a list of sessions
	public static ArrayList<session> findScheduleByDate(String timeframe) {
		ArrayList<session> result = new ArrayList<session>();
		
		int[] currentDateArray = getCurrentDay();
		System.out.println("Month: " + currentDateArray[0] + ", Day: " + currentDateArray[1] + ", Year: " + currentDateArray[2]);
		System.out.println("***** " + sessionList.size() + " *****");
		int[] placeholderDate = new int[3];
		
		if(timeframe == "Submit") {
			for (int i = 0; i < sessionList.size(); i++) {
				placeholderDate = breakDateDown(sessionList.get(i).getDate());
				
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
			session session = new session();
			session.setDate(" ");
			session.setRoom(" ");
			session.setTime(" ");
			session.setTutor("No Tutoring Sessions Available");
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
