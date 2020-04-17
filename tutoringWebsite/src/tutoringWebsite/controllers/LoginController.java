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
	
	private IDatabase db = new DerbyDatabase();
	//List<User> authorBookList = db.createAccount(email, password, major, year);
	
	public LoginController(Login model) {
		this.model = model;
	}
	
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
	}
	
	public boolean validateCredentials(String name, String pw) {
		return model.validatePW(name, pw);
	}
	//add new account
	public User getAccount(String name, String pw) {
		//model.
		return model.getUser(name);
		
	}
	public User createAccount(String email, String pw, String name, int userType) {
		if(model.isStudent(email)) {
		List<User> userList = db.createAccount(email, pw, name, userType);
		 return userList.get(0);
		}
		else {
			return model.createAccount(name, pw, name, userType);
		}
		
		
	}
	
	public boolean validateUsername(String name) {
		return model.isStudent(name);
	}
}	
