package tutoringWebsite.controllers;



import tutoringWebsite.model.login;
import tutoringWebsite.model.user;

public class LoginController {
	private login model = null;
	
	public LoginController(login model) {
		this.model = model;
	}
	
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
	}
	
	public boolean validateCredentials(String name, String pw) {
		return model.validatePW(name, pw);
	}
	//add new account
	public boolean createAccount(String name, String pw, String major, String year) {
		if(model.isStudent(name)==false) {
			return false;
		}
		model.createAccount(name, pw, major, year);
		return true;
		
	}
}	
