package tutoringWebsite.controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tutoringWebsite.model.user;

public class userController {
	//User type variable
	private user model;

	private ArrayList<String> emailList = new ArrayList<String>();
	Scanner keyboard = new Scanner(System.in);
	
	//set up the model
	public void setUser(user model) {
		this.model = model;
	}
	public void setLogin(LoginController model2) {
		this.model2 = model2;
	}
	
	//Using the search bar
	public void lookUp(String item) {
		
	}
	
	//what we want login to do eventually, is check what kind of info can be accessed on each page depending on the user type
	//and if they are logged in or not
	public void login(String e, String pass) {
		//model2.checkLogin();
	}
	
	//go to whatever is selected
	public String select() {
		String s = null;
		return s;
	}
	
	//push a request to whom it may be concerned
	//parameter is email of person requesting from
	//message of what the request is
	public void request(String requestEmail) {
		int canDoRequest = 0;
		String message;
		//check to make sure requestEmail is valid
		for(int i = 0; i < emailList.size()-1; i++) {
			if(requestEmail.equals(emailList.get(i))) {
				canDoRequest++;
			}
		}
		if(canDoRequest == 1) {
			//put small message for what the request is for
			System.out.print("Enter message for " + requestEmail + ": ");
			message = keyboard.nextLine();
			//send request with message...?
		}
		else {
			throw new NoSuchElementException("Something is wrong");
		}
	}
	
	//cancel making new account, or logging in
	public void cancel() {
		model.cancel();
	}
}
