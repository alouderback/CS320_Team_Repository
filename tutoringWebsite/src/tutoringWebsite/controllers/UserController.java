package tutoringWebsite.controllers;

import java.util.List;

import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;
import tutoringWebsite.persist.RDatabase;
import tutoringWebsite.persist.readOnlyDatabase;

public class UserController {
	private User model = null;

	
	
	//DatabaseProvider.setInstance(new DerbyDatabase());
	//IDatabase db = DatabaseProvider.getInstance();
	
	IDatabase db = new DerbyDatabase();
	RDatabase read = new readOnlyDatabase();
	//List<User> authorBookList = db.createAccount(email, password, major, year);
	
	public UserController(User model) {
		this.model = model;
	}
	
	
	//add new account
	public User getAccount(String name, String pw) {
		
			List<User> currentUser = db.getAccount(name, pw);
		
		return	currentUser.get(0);
		
	
		
	}
	public User createAccount(String email, String pw, String name, int userType) {

			List<User> userList = db.createAccount(email, pw, name, userType);
			return userList.get(0);
		
		
	}
	public boolean validateCredentials(String name, String pw) {
		
		List<User> loginList = db.useLogin(name,pw);
		if(!loginList.isEmpty()) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean validateUsername(String name) {
		List<String> emailList = read.getEmails();
		boolean test = emailList.contains(name);
		System.out.println(test);
		return test;
		//return model.isStudent(name);
	}
	public boolean removeAccount(User user) {
		User delete = db.deleteAccount(user.getEmail(), user.getPassword());
		
		if(delete == null) {
			return true;
		}
		else {
			return false;
			//user.removeAccount(user);
		}
	
	
	}
}