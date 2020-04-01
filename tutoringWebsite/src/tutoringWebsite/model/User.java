package tutoringWebsite.model;



public class User {
	private String email;
	private String password;
	
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
	
	public void cancel() {
		email = null;
		password = null;
	}
}
