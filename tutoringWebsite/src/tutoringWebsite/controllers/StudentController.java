package tutoringWebsite.controllers;

import java.util.List;

import tutoringWebsite.model.Student;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;

public class StudentController {
	private Student model = null;

	
	
	//DatabaseProvider.setInstance(new DerbyDatabase());
	//IDatabase db = DatabaseProvider.getInstance();
	
	IDatabase db = new DerbyDatabase();
	//List<User> authorBookList = db.createAccount(email, password, major, year);
	
	public StudentController(Student model) {
		this.model = model;
	}
	
	
	//add new account
	public Student getStudent(String name, String pw) {
		
			List<Student> currentStudent = db.getStudent(name, pw);
			return	currentStudent.get(0);
	}
	public Student createStudent(String major, String year,int userid) {
			List<Student> studentList = db.createStudent(major, year, userid);
			return studentList.get(0);
	}
	
	public boolean removeStudentt(String name, String pw) {
		Student delete = db.deleteStudent(name, pw);
		
		if(delete == null) {
			return true;
		}
		else {
			return false;
			//user.removeAccount(user);
		}
	
	
	}
	
}