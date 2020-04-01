package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.userController;
import tutoringWebsite.model.user;

public class userControllerTest {
	private user model;
	private userController controller;
	private ArrayList<String> emailList;
	
	@Before
	public void setUp() {
		model = new user();
		controller = new userController();
		emailList = new ArrayList<String>();
		emailList.add("csims2@ycp.edu");
		emailList.add("ebosse@ycp.edu");
		
		controller.setUser(model);
	}
	
	@Test
	public void testLookUp() {
		
	}
	
	@Test
	public void testLogin() {
		
	}
	
	@Test
	public void testSelect(){
	
	}
	
	@Test
	public void testRequest() {
		
	}
	
	@Test
	public void testCancel(){
	
	}
}
