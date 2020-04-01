package tutoringWebsite.controllerJUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tutoringWebsite.controllers.UserController;
import tutoringWebsite.model.User;

public class userControllerTest {
	private User model;
	private UserController controller;
	private ArrayList<String> emailList;
	
	@Before
	public void setUp() {
		model = new User();
		controller = new UserController();
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
