package tutoringWebsite.db;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import tutoringWebsite.model.Course;
import tutoringWebsite.model.Session;
import tutoringWebsite.model.StudyGroup;
import tutoringWebsite.model.Tutor;

public class StudyGroupDB {
	private ArrayList<StudyGroup> studyGroups;
	
	
	public StudyGroupDB() {
		
		studyGroups = new ArrayList<StudyGroup>();
		
		
		Course course1 = new Course();
		Course course2 = new Course();
		Course course3 = new Course();
		
		Session session1 = new Session();
		Session session2 = new Session();
		Session session3 = new Session();
		
		StudyGroup studyGroup1 = new StudyGroup();
		StudyGroup studyGroup2 = new StudyGroup();
		StudyGroup studyGroup3 = new StudyGroup();
		
		//course1 variables
		Session courseSession1 = new Session();
		Tutor courseTutor1 = new Tutor();
		ArrayList<Tutor> courseTutorList1 = new ArrayList<Tutor>();
		
		//course2 variables
		Session courseSession2 = new Session();
		Tutor courseTutor2 = new Tutor();
		ArrayList<Tutor> courseTutorList2 = new ArrayList<Tutor>();
		
		//course3 variables
		Session courseSession3 = new Session();
		Tutor courseTutor3 = new Tutor();
		ArrayList<Tutor> courseTutorList3 = new ArrayList<Tutor>();
		
		//course1
		courseTutor1.setName("Alex Louderback");
		courseTutor1.setYear("Junior");
		
		courseSession1.setDate(LocalDate.of(2020, 04, 01));
		courseSession1.setRoom("KINS123");
		courseSession1.setTime(LocalTime.of(14, 00));
		courseSession1.setTutor(courseTutor1);
		
		courseTutorList1.add(courseTutor1);
		
		course1.setTitle("CS320");
		course1.setCourseSession(courseSession1);
		course1.setTutorList(courseTutorList1);
		
		//course2
		courseTutor2.setName("Caryn Sims");
		courseTutor2.setYear("Sophmore");
		
		courseSession2.setDate(LocalDate.of(2020, 03, 30));
		courseSession2.setRoom("KINS110");
		courseSession2.setTime(LocalTime.of(12, 00));
		courseSession2.setTutor(courseTutor2);
		
		courseTutorList2.add(courseTutor2);
		
		course2.setTitle("ECE280");
		course2.setCourseSession(courseSession2);
		course2.setTutorList(courseTutorList2);
		
		//course3
		courseTutor3.setName("Isabelle Hoffman");
		courseTutor3.setYear("Sophmore");
		
		courseSession3.setDate(LocalDate.of(2020, 03, 31));
		courseSession3.setRoom("KINS115");
		courseSession3.setTime(LocalTime.of(15, 00));
		courseSession3.setTutor(courseTutor3);
		
		courseTutorList3.add(courseTutor3);
		courseTutorList3.add(courseTutor2);
		
		course3.setTitle("ECE260");
		course3.setCourseSession(courseSession3);
		course3.setTutorList(courseTutorList3);
		
		//session1
		session1.setDate(LocalDate.of(2020, 04, 01));
		session1.setRoom("KINS120");
		session1.setTime(LocalTime.of(20, 00));
		session1.setTutor(courseTutor1);
		
		//session2
		session2.setDate(LocalDate.of(2020, 04, 02));
		session2.setRoom("KINS135");
		session2.setTime(LocalTime.of(21, 30));
		session2.setTutor(courseTutor2);
		
		//session3
		session3.setDate(LocalDate.of(2020, 04, 03));
		session3.setRoom("KINS122");
		session3.setTime(LocalTime.of(19, 30));
		session3.setTutor(courseTutor3);
		
		//studyGroup1
		studyGroup1.setCourse(course1);
		studyGroup1.setSession(session1);
		
		//studyGroup2
		studyGroup2.setCourse(course2);
		studyGroup2.setSession(session2);
		
		//studyGroup3
		studyGroup3.setCourse(course3);
		studyGroup3.setSession(session3);
		
		//adding to array
		studyGroups.add(studyGroup1);
		studyGroups.add(studyGroup2);
		studyGroups.add(studyGroup3);
		
	}
	
	public ArrayList<StudyGroup> getStudyGroups(){
		return studyGroups;
	}
	
	public void setStudyGroups(ArrayList<StudyGroup> studyGroups) {
		this.studyGroups = studyGroups;
	}
	
	public void addStudyGroup(StudyGroup studyGroup) {
		studyGroups.add(studyGroup);
	}
	
	public void deleteStudyGroup(StudyGroup studyGroup) {
		for(int i =0; i < studyGroups.size(); i++) {
			if(studyGroups.get(i).equals(studyGroup)) {
				studyGroups.remove(i);
			}
		}
	}
	
}
