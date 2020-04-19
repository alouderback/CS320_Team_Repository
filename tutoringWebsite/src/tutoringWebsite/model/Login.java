package tutoringWebsite.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Login {
	
	private ArrayList<String> emails;
	private ArrayList<String> passwords;
	private Map<String, String> credentials;
	
	// create model - test version
		public Login() {
			emails = new ArrayList<String>();
			passwords = new ArrayList<String>();
			credentials = new TreeMap<String, String>();
			
			//emails.add("student@ycp.edu");
			emails.add("faculty@ycp.edu");
			emails.add("tutor@ycp.edu");
			
			//passwords.add("ycp");
			passwords.add("E&CS");
			passwords.add("1234");
			for (int i = 0; i < emails.size(); i++) {
				credentials.put(emails.get(i), passwords.get(i));
			}
		}		

		// login name - test version
		public boolean validateUserName(String name) {
			return credentials.containsKey(name);
		}

		// login credentials - test version
		public boolean validatePW(String name, String pw) {
			if (credentials.containsKey(name)) {
				if  (credentials.get(name).equals(pw)) {
					return true;
				}
			}			
			return false;
		}
		
		public User getUser(String name) {
			User user = new User();
		
			return user;
		}
		
		public User createAccount(String email, String password, String name, int userType) {
			
			emails.add(email);
			passwords.add(password);
			credentials.put(email, password);
			
			User account = new User();
			account.setEmail(email);
			account.setPassword(password);
			account.setName(name);
			account.setUserType(userType);
			
			return account;	
		}
		public boolean isStudent(String name) {
			return name.contains("@ycp.edu");
			
		}
}