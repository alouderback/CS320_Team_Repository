package tutoringWebsite.controllers;
import tutoringWebsite.model.announcement;

import java.util.Scanner; 

public class announcementController{
	private announcement model;
	
	public void setModel(announcement model) {
		this.model = model;
	}
	
	public void create() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter date for announcement: ");
		String inputDate = input.nextLine();
		model.setDate(inputDate);
		
		System.out.print("Please enter time for announcement: ");
		String inputTime = input.nextLine();
		model.setTime(inputTime);
	
		System.out.print("Please enter message for announcement: ");
		String inputMessage = input.nextLine();
		model.setMessage(inputMessage);
	}
	
	public void display() {
		System.out.println(model.getDate() + " " + model.getTime());
		System.out.println(model.getMessage());
	}
	
	public void delete() {
		model.delete();
	}
	
}