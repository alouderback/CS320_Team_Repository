package tutoringWebsite.model;


import java.util.ArrayList;
import java.util.Map;

public class User {
	private int user_Id;
	private String email;
	private String password;
	private String name;
	private int userType;

	public User(){
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public void cancel() {
		email = null;
		password = null;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
}
