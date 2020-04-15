package tutoringWebsite.model;

public class Student extends User { 
	private String year;
	private String major;
	
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

}
