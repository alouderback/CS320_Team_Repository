package tutoringWebsite.controllers;
import tutoringWebsite.model.announcement;
import tutoringWebsite.model.schedule;

public class scheduleController{
	private schedule model;
	
	public void setModel(schedule model) {
		this.model = model;
	}
	
	public void display() {
		for(int i = 0; i < model.getSchedule().size(); i++) {
			System.out.println(model.getSchedule().get(i));
		}
	}
}