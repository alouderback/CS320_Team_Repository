package tutoringWebsite.controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tutoringWebsite.model.User;

public class UserController {
	//User type variable
	private User model;
	private ArrayList<String> emailList = new ArrayList<String>();
	Scanner keyboard = new Scanner(System.in);
	
	//set up the model
	public void setUser(User model) {
		this.model = model;
	}
	
	//Using the search bar
	public void lookUp(String item) {
		
	}
	
	//user logs into account
	// if user does not have account, prompt to make one (check if they have
	//account using email)
	//parameters are email and password inputs, compare with emails in an emailList...?
	//check to see if the password matches the one for that account
	//if password matches, if not ask to try again
	public void login(String e, String pass) {
		String email = model.getEmail();
		String password = model.getPassword();
		int hasAccount = 0;
		for(int i = 0; i < emailList.size()-1; i++) {
			if(email.equals(emailList.get(i))) {
				hasAccount++;
			}
		}
		if(hasAccount == 0) { // user makes account
			System.out.println("Enter in email: ");
			String newEmail = keyboard.next();
			emailList.add(newEmail);
			System.out.println("Enter in password: ");
			String newPassword = keyboard.next();
			//creating the account???
		}
		else if(hasAccount != 1) {
			throw new NoSuchElementException("email is already used");}
		else {
			if(pass.equals(password)) {
				//can log in
			}
			else {
				throw new NoSuchElementException("Incorrect password");}
		}
	}
	
	//go to whatever is selected
	public String select() {
		String s = null;
		return s;
	}
	
	//push a request to whom it may be concerned
	public void request() {
		
	}
	
	//cancel whatever they were trying to do
	public void cancel() {
		
	}
}
