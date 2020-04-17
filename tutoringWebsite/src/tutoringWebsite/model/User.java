package tutoringWebsite.model;


import java.util.ArrayList;
import java.util.Map;

public class User {
	private String email;
	private String password;
	private String year;
	private String major;
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
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return this.year;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajor() {
		return this.major;
	}
	public void cancel() {
		email = null;
		password = null;
		year = null;
		major = null;
	}
}
