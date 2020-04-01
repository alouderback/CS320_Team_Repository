package tutoringWebsite.model;



import java.util.ArrayList;
import java.util.Map;



public class User {
	private String email;
	private String password;
	private String name;
	private String genInfo;
	
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
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void cancel() {
		email = null;
		password = null;
	}
}
