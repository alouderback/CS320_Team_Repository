package tutoringWebsite.controllers;



import tutoringWebsite.model.Login;
import tutoringWebsite.model.User;

public class LoginController {
	private Login model = null;
	

	
	
	//DatabaseProvider.setInstance(new DerbyDatabase());
	//IDatabase db = DatabaseProvider.getInstance();
	
	private IDatabase db = new DerbyDatabase();
	//List<User> authorBookList = db.createAccount(email, password, major, year);
	

	public LoginController(Login model) {
		this.model = model;
	}
	
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
	}
	
	public boolean validateCredentials(String name, String pw) {
		return model.validatePW(name, pw);
	}
	//add new account
	public boolean createAccount(String name, String pw) {
		if(model.isStudent(name)==false) {
			return false;
		}
		model.createAccount(name, pw);
		return true;
		
	}
}	
