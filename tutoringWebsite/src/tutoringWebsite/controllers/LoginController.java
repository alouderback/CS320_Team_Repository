package tutoringWebsite.controllers;

import java.util.NoSuchElementException;
import java.util.Scanner;

import tutoringWebsite.model.login;
import tutoringWebsite.model.user;

public class LoginController {
	private login model = null;
	Scanner keyboard = new Scanner(System.in);
	
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
	public void createAccount() {
		
		/*boolean correctEmail = model.validateUserName(email);
		boolean correctPassword = model.validatePW(email, password);
		
		if(correctEmail == false) { // user makes account
			System.out.print("Enter in major: ");
			String major = keyboard.nextLine();
			System.out.print("Enter in class: ");
			String classYear = keyboard.nextLine();
			model.createAccount(email, password, major, classYear);
			//account is created
			//user is valid
			
		}
		else if(correctEmail == true) {
			if(correctPassword == true) {
				//are logged in
				//user is valid
			}
			else {
				throw new NoSuchElementException("Incorrect password");}
		}

		else {
			throw new NoSuchElementException("something went terribly wrong");		
		}*/
	
	}
}	
