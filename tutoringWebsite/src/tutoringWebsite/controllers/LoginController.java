package tutoringWebsite.controllers;



import java.util.List;

import  tutoringWebsite.model.Login;
import tutoringWebsite.model.User;
import tutoringWebsite.persist.DerbyDatabase;
import tutoringWebsite.persist.IDatabase;
import tutoringWebsite.persist.DatabaseProvider;

public class LoginController {
	private Login model = null;
	
	
	
	//DatabaseProvider.setInstance(new DerbyDatabase());
	//IDatabase db = DatabaseProvider.getInstance();
	
	IDatabase db = new DerbyDatabase();
	//List<User> authorBookList = db.createAccount(email, password, major, year);
	
	public LoginController(Login model) {
		this.model = model;
	}
	
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
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
	//add new account
	public User getAccount(String name, String pw) {
		List<User> currentUser = db.getAccount(name, pw);
		return	currentUser.get(0);
	
		
	}
	public User createAccount(String email, String pw, String name, int userType) {
		if(model.isStudent(email)) {
		List<User> userList = db.createAccount(email, pw, name, userType);
		int temp = userList.size()-1;
		 return userList.get(temp);
		}
		else {
			System.out.println("email invalid");
			return model.createAccount(name, pw, name, userType);
		}
		
		
	}
	
	public boolean validateUsername(String name) {
		return model.isStudent(name);
	}
}