package tutoringWebsite.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import tutoringWebsite.persist.*;
import tutoringWebsite.model.*;

public class DerbyDatabase implements IDatabase{
	//code from Hake Derby Database
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	//our code for website
	@Override
	public List<User> useLogin(final String email, final String password){
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				//System.out.println("IN DERBY DATABASE");
				try {
					stmt = conn.prepareStatement(
							"select * "+
							" from Users"+
							" WHERE email=? and password=?"
							);

					stmt.setString(1, email);
					stmt.setString(2, password);

					List<User> result = new ArrayList<User>();
					resultSet = stmt.executeQuery();
					
					
					Boolean found = false;


					while (resultSet.next()) {
						User user = new User();
						loadUser(user,resultSet,1);
						result.add(user);
					 
					}
					for (User temp : result) {
						System.out.println( temp.getUser_Id()+", "+temp.getEmail()+ ", " + temp.getPassword());
					}

					if(found = false) {
						System.out.println("<" + email + "> was not found in the user database");
					
					}
				//	System.out.println("done");
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
				});
	}
	@Override
	public User deleteAccount(final String email, final String password){
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				/*
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
		 */
				try {
					
					User result = new User();
					
					stmt1 = conn.prepareStatement(
							"delete from Users " +
							"where email = ? and password = ?"
					//sql to add an account to list
							);
					stmt1.setString(1, email);
					stmt1.setString(2, password);
					stmt1.executeUpdate();
					
					//System.out.println("user deleted");
					stmt3 = conn.prepareStatement(
							"select * from Users " +
							"where email = ? and password = ?"
							);
			
					
					stmt3.setString(1, email);
					stmt3.setString(2, password);
					resultSet = stmt3.executeQuery();
					Boolean found = false;

					while (resultSet.next()) {
						found = true;			
						User user = new User();
						loadUser(user,resultSet,1);
						result = user;
					}
					
					System.out.println( result.getUser_Id()+", "+result.getEmail()+ ", " + result.getPassword());
					if(found = false) {
						System.out.println("<" + email + "> was sucessfully deleted from the user database");
					}
					
					//System.out.println("user gone");
					return result;
			}finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt3);
					}
		
				}
			
		});
		
		
	}
	@Override
	public Student deleteStudent(final String email, final String password){
		return executeTransaction(new Transaction<Student>() {
			@Override
			public Student execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				int userid = 0;
			
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
				try {
					Student result = new Student();
					stmt1 = conn.prepareStatement(
								"select * "+
								" from Users"+
								" where Users.email = ? and Users.password =?"
								);
						
						
						stmt1.setString(1, email);
						stmt1.setString(2, password);
						resultSet = stmt1.executeQuery();
						System.out.println("got user");
						
						
						while (resultSet.next()) {
						User user = new User();
						loadUser(user,resultSet,1);
						System.out.println("load user worked");
						userid = user.getUser_Id();
						System.out.println("user id: "+ userid);
						}
						
					stmt2 = conn.prepareStatement(
							"delete from Students " +
							"where user_id = ? "
					//sql to add an account to list
							);
					stmt2.setInt(1, userid);
					stmt2.executeUpdate();
					
					//System.out.println("user deleted");
					stmt3 = conn.prepareStatement(
							"select * from Students " +
							"where user_id = ? "
							);
			
					
					stmt3.setInt(1, userid);
					
					resultSet = stmt3.executeQuery();
					Boolean found = false;

					while (resultSet.next()) {
						found = true;			
						Student stud = new Student();
						loadStudent(stud,resultSet,1);
						result = stud;
					}
					
					System.out.println( result.getUser_Id()+", "+result.getMajor()+ ", " + result.getYear());
					if(found = false) {
						System.out.println("<" + email + "> was sucessfully deleted from the user database");
					}
					
					//System.out.println("user gone");
					return result;
			}finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt3);
					}
		
				}
			
		});
		
		
	}
	//can create any announcement for study group or a session
	//all printed on main page
	@Override
	public Integer createAnnouncement(final String message, final LocalDate date, final LocalTime startTime, final LocalTime endTime, final int announcementType, final int typeId){
		return executeTransaction(new Transaction <Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				int resultId = -1;
				
				try {
					System.out.println("Adding Announcement...");
					stmt = conn.prepareStatement(
						"insert into Announcements(message, date, startTime, endTime, announcementType, typeId) "
							+"values(?, ?, ?, ?, ?, ?)"
							);
					stmt.setString(1, message);
					stmt.setString(2,  date.toString());
					stmt.setString(3, startTime.toString());
					stmt.setString(4, endTime.toString());
					stmt.setInt(5, announcementType);
					stmt.setInt(6, typeId);
					
					stmt.executeUpdate();
					
					System.out.println("Announcement added");
					System.out.println("Retreiving announcement ID...");
					
					stmt2 = conn.prepareStatement(
						"select announcement_id from Announcements "+
						"where message = ? and date = ? and startTime = ?"
					);
					stmt2.setString(1, message);
					stmt2.setString(2, date.toString());
					stmt2.setString(3, startTime.toString());
					
					resultSet = stmt2.executeQuery();
					
					System.out.println("ID retrieved");
					
					if (resultSet.next()){
						resultId = resultSet.getInt(1);
						System.out.println("New announcement <" + message + "> ID: " + resultId);						
					}
					else{	// really should throw an exception here - the new book should have been inserted, but we didn't find it
						System.out.println("New announcement <" + message + "> not found in Books table (ID: " + resultId);
					}
					
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
				System.out.println("Returning announcement ID");
			return resultId;
			}
		});	
	}
	@Override
	public List<Announcement> removeAnnouncement(final int announcementId) {
		return executeTransaction(new Transaction<List<Announcement>>() {
			@Override
			public List<Announcement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;						
				ResultSet resultSet    = null;
				List<Announcement> result = null;
				 				try {
					
					stmt1 = conn.prepareStatement(
						"select announcements.* "+
						"from Announcements " +
						"where announcement_id = ?"
					);
					stmt1.setInt(1, announcementId);
					resultSet = stmt1.executeQuery();
					
					if(resultSet.getFetchSize() == 0) {
						System.out.println("No announcement with that id");
					}
					else {
						
						result = new ArrayList<Announcement>();
						
						while(resultSet.next()) {
							Announcement announcement = new Announcement();
							loadAnnouncement(announcement, resultSet, 1);
							result.add(announcement);
						}
						
						stmt2 = conn.prepareStatement(
							"delete from Announcements "+
							"where announcement_id = ?"
						);
					
						stmt2.setInt(1, announcementId);
						stmt2.executeUpdate();
					
					}
										
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
				}
			}
		});
	}
	//receive announcements for the sessionId sent over
	@Override
	public List<Announcement> getAnnouncementsforSessionWithSessionId(final int sessionId){
		//using SQl, get list of announcements for one particular session
		//using the session Id, find all announcements that match it
		return executeTransaction(new Transaction<List<Announcement>>() {
			@Override
			public List<Announcement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Announcement> result;
				try {
					System.out.println("Retriveing announcements with sessionId");
					stmt = conn.prepareStatement(
						"select announcements.* "+
					"from Announcements "+
					"where typeId = ? and announcementType = ? "+
					"order by Announcements.date asc "
					);
					stmt.setInt(1, sessionId);
					stmt.setInt(2, 1);
					
					resultSet = stmt.executeQuery();
					System.out.println("Retrieved announcements");
					result = new ArrayList<Announcement>();
				
					while(resultSet.next()) {
						Announcement announcement = new Announcement();
						loadAnnouncement(announcement, resultSet, 1);
						result.add(announcement);
					}
					System.out.println("Announcements added to list");
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				System.out.println("returning list");
				return result;
			}
		});
	}
	//receive announcements for the studyGroupId sent over
	@Override
	public List<Announcement> getAnnouncementsforStudyGroupWithStudyGroupId(final int studyGroupId){
		//using SQl, get list of announcements for one particular study group
		//using the study group Id, find all announcements that match it
		return executeTransaction(new Transaction<List<Announcement>>() {
			@Override
			public List<Announcement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Announcement> result;
				try {
					stmt = conn.prepareStatement(
						"select announcements.* "+
					"from Announcements "+
					"where typeId = ? and announcementType = ?"+
					"order by Announcements.date asc"
					);
					stmt.setInt(1, studyGroupId);
					stmt.setInt(2, 2);
					resultSet = stmt.executeQuery();
					result = new ArrayList<Announcement>();
				
					while(resultSet.next()) {
						Announcement announcement = new Announcement();
						loadAnnouncement(announcement, resultSet, 1);
						result.add(announcement);
					}
					
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				return result;
			}
		});
	}
	//get all Announcements for the main page
	@Override
	public List<Announcement> getAllAnnouncements(){
		return executeTransaction(new Transaction<List<Announcement>>() {
			@Override
			public List<Announcement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Announcement> result;
				try {
					stmt = conn.prepareStatement(
						"select announcements.* "+
					"from Announcements "+
					"order by Announcements.date asc "
					);
					
					resultSet = stmt.executeQuery();
					result = new ArrayList<Announcement>();
				
					while(resultSet.next()) {
						Announcement announcement = new Announcement();
						loadAnnouncement(announcement, resultSet, 1);
						result.add(announcement);
					}
					
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				return result;
			}
		});
	}
	//get all Announcements for the study group page
	@Override
	public List<Announcement> getAllStudyGroupAnnouncements(){
		return executeTransaction(new Transaction<List<Announcement>>() {
			@Override
			public List<Announcement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Announcement> result;
				try {
					stmt = conn.prepareStatement(
						"select announcements.* "+
						"from Announcements "+
						"where announcementType = ?" +
						"order by Announcements.date asc "
					);
					stmt.setInt(1, 2);
					resultSet = stmt.executeQuery();
					result = new ArrayList<Announcement>();
				
					while(resultSet.next()) {
						Announcement announcement = new Announcement();
						loadAnnouncement(announcement, resultSet, 1);
						result.add(announcement);
					}
						
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				return result;
			}
		});
	}
	//get all Announcements for the session page
	@Override
	public List<Announcement> getAllSessionAnnouncements(){
		return executeTransaction(new Transaction<List<Announcement>>() {
			@Override
			public List<Announcement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Announcement> result;
				try {
					stmt = conn.prepareStatement(
						"select announcements.* "+
						"from Announcements "+
						"where announcementType = ?" +
						"order by Announcements.date asc "
					);
					stmt.setInt(1, 1);
					resultSet = stmt.executeQuery();
					result = new ArrayList<Announcement>();
							
					while(resultSet.next()) {
						Announcement announcement = new Announcement();
						loadAnnouncement(announcement, resultSet, 1);
						result.add(announcement);
					}				
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				return result;
			}
		});
	}
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUser_Id((resultSet.getInt(index++)));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		user.setEmail((resultSet.getString(index++)));
		user.setPassword((resultSet.getString(index++)));
		user.setName((resultSet.getString(index++)));
		user.setUserType((resultSet.getInt(index++)));
		
	}
	private void loadTutorFaculty(TutorFaculty tf, ResultSet resultSet, int index) throws SQLException {
		tf.setAdmin_id((resultSet.getInt(index++)));
		tf.setUser_Id((resultSet.getInt(index++)));
		tf.setCourse_id((resultSet.getInt(index++)));
		tf.setUserType((resultSet.getInt(index++)));
		
	}
	private void loadStudent(Student stud, ResultSet resultSet, int index) throws SQLException {
		stud.setStudent_id(resultSet.getInt(index++));
		stud.setMajor(resultSet.getString(index++));
		stud.setYear(resultSet.getString(index++));
		stud.setUser_Id(resultSet.getInt(index++));
	}
	private void loadCourse(Course course, ResultSet resultSet, int index) throws SQLException {
		course.setCourseId(resultSet.getInt(index++));
		course.setTitle(resultSet.getString(index++));
		course.setSessionId(resultSet.getInt(index++));
		
	}
	private void loadAnnouncement(Announcement announcement, ResultSet resultSet, int index) throws SQLException {
		announcement.setAnnouncementId(resultSet.getInt(index++));
		announcement.setMessage(resultSet.getString(index++));
		LocalDate date = LocalDate.now();
		date = LocalDate.parse(resultSet.getString(index++));
		announcement.setDate(date);
		LocalTime startTime = LocalTime.now();
		startTime = LocalTime.parse(resultSet.getString(index++));
		announcement.setStartTime(startTime);
		LocalTime endTime = LocalTime.now();
		endTime = LocalTime.parse(resultSet.getString(index++));
		announcement.setEndTime(endTime);
		announcement.setAnnouncementType(resultSet.getInt(index++));
		announcement.setTypeId(resultSet.getInt(index++));
	}
	private void loadSession(Session session, ResultSet resultSet, int index) throws SQLException {
		LocalDate date = LocalDate.now();
		LocalTime startTime = LocalTime.now();
		LocalTime endTime = LocalTime.now();
		
		System.out.println("******* IN LOAD SESSION *******");
		
		session.setSessionId((resultSet.getInt(index++)));
		
		System.out.println("- Set the sessionId");
		
		date = LocalDate.parse(resultSet.getString(index++));
		session.setDate(date);
		System.out.println("- Set the date - " + session.getDate().toString());
		session.setRoom(resultSet.getString(index++));
		System.out.println("- Set the room - " + session.getRoom());
		startTime = LocalTime.parse(resultSet.getString(index++));
		session.setStartTime(startTime);
		System.out.println("- Set the startTime - " + session.getStartTime().toString());
		endTime = LocalTime.parse(resultSet.getString(index++));
		session.setEndTime(endTime);
		System.out.println("- Set the endTime - " + session.getEndTime().toString());
		session.setDayOfWeek(resultSet.getInt(index++));
		System.out.println("- Set the dayOfWeek - " + session.getDayOfWeek());
		session.setAdminId(resultSet.getInt(index++));
		System.out.println("- Set the adminId - " + session.getAdminId());
		session.setCourseId(resultSet.getInt(index++));
		System.out.println("- Set the courseId - " + session.getCourseId());
		session.setTypeId(resultSet.getInt(index++));
		System.out.println("- Set the typeId - " + session.getTypeId());
		System.out.println("See below for course:");
		System.out.println("Course for session that is being loaded: " + session.getCourseId());
	}
	public List<Session> getAllSessions(){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Session> result = new ArrayList<Session>();
				
				try {
					stmt = conn.prepareStatement(
							"select sessions.* " +
							" from Sessions "
					//Trying to get all sessions
							);
					
					resultSet = stmt.executeQuery();
					
					System.out.println("Query Executed");
					
					System.out.println("Got all sessions");
					
					while (resultSet.next()) {
						System.out.println("Within while loop, see session id below:");
						Session session = new Session();
						loadSession(session, resultSet, 1);
						System.out.println("Session ID: " + session.getSessionId());
						result.add(session);
					}
					
					return result;
			
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					System.out.println("Closed");
				}
			}
		});
	}
	@Override
	public List<Session> getAllStudyGroupSessions(){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					List<Session> result = new ArrayList<Session>();
					
					stmt = conn.prepareStatement(
							"select * from Sessions " +
							"where type_id = ? "
							);
					
					stmt.setInt(1, 2);
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						System.out.println("Within while loop, see session id below:");
						Session session = new Session();
						loadSession(session, resultSet, 1);
						System.out.println("Session ID: " + session.getSessionId());
						result.add(session);
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	
	@Override
	public List<Session> getAllTutoringSessions(){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					List<Session> result = new ArrayList<Session>();
					
					stmt = conn.prepareStatement(
							"select * from Sessions " +
							"where type_id = ? "
							);
					
					stmt.setInt(1, 1);
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						System.out.println("Within while loop, see session id below:");
						Session session = new Session();
						loadSession(session, resultSet, 1);
						System.out.println("Session ID: " + session.getSessionId());
						result.add(session);
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	@Override
	public List<Course> getAllCourses(){
		return executeTransaction(new Transaction<List<Course>>() {
			public List<Course> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Course> result = new ArrayList<Course>();
				
				try {
					stmt = conn.prepareStatement(
							"select course.* " +
							" from Course "
							);
					resultSet = stmt.executeQuery();
					System.out.println("Got all courses");
					
					while(resultSet.next()) {
						Course course = new Course();
						loadCourse(course, resultSet, 1);
						result.add(course);
					}
					
					return result;
							
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	//NEEDS JUNIT
	//Returns list of ONE user; has userId as a paramter and returns a list of users
	@Override
	public List<User> getUserFromUserId (int userId) {
		return executeTransaction(new Transaction<List<User>>() {
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<User> result = new ArrayList<User>();
				
				try {
					stmt = conn.prepareStatement(
						" select users.* " +
						" from Users " +
						" where user_id = ?"
					);		
					//Gets the user
					stmt.setInt(1, userId);
					
					resultSet = stmt.executeQuery();
					
					//while loop; should only load one user
					while (resultSet.next() ) {
						User user = new User();
						loadUser(user, resultSet, 1);
						result.add(user);
					}
					
					return result;
					//If functional, should return a list of ONE user
					//Created in order to get the tutor name in scheduleServlet
					
				}finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				
			}
		});
	}
	@Override
	public User getSingleUser(int userId) {
		User user = getUserFromUserId(userId).get(0);
		return user;
	}
	
	
	public List<Session> getScheduleByDate(String timeframe){
		//Method currently uses parameter timeframe
		//Thinking about passing a LocalDate as a parameter
		return executeTransaction(new Transaction<List<Session>>() {
			
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Session> result = new ArrayList<Session>();
				LocalDate presentDateTime = LocalDate.now();
				LocalDate endDate = null;
				int day = presentDateTime.getDayOfMonth();
				int month = presentDateTime.getMonthValue();
				int year = presentDateTime.getYear();
				
				LocalDate presentDate = LocalDate.of(year, month, day);
				
				System.out.println("IN DERBY DATABASE");
				System.out.println("In getScheduleByDate");
				
				if(timeframe == "Submit") {
					endDate = presentDate.plusDays(1);
					//The loop condition is that the incrementing value needs to be less than the comparative value
					//So the endDate is the "first date" that isn't passed
					System.out.println("*** MY MAN IS GRABBING SCHEDULE FOR TODAY ***");
				}
				else if (timeframe == "SubmitW") {
					endDate = presentDate.plusDays(8);
					System.out.println("*** MY MAN IS GRABBING SCHEDULE FOR THE WEEK ***");
				}
				else if (timeframe == "SubmitM") {
					endDate = presentDate.plusMonths(1);
					endDate = endDate.plusDays(1);
					System.out.println("*** MY MAN IS GRABBING SCHEDULE FOR THE MONTH ***");
				}
				
				while(presentDate.isBefore(endDate)) {
					System.out.println("(TOL) PresentDate Value: " + presentDate);
					try {
						stmt = conn.prepareStatement(
								"select sessions.* " +
								" from Sessions "  +
								"where date = ? " +
								"and type_id = ?"
						//Trying to get all sessions
								);
						stmt.setString(1, presentDate.toString());
						stmt.setInt(2, 1);
						resultSet = stmt.executeQuery();
						
						System.out.println("Query Executed");
						
						System.out.println("Got all sessions");
						
						while (resultSet.next()) {
							System.out.println("Within while loop, see session id below:");
							Session session = new Session();
							loadSession(session, resultSet, 1);
							System.out.println("Session ID: " + session.getSessionId());
							result.add(session);
						}
	
				
				}finally {
							DBUtil.closeQuietly(resultSet);
							DBUtil.closeQuietly(stmt);
							System.out.println("Closed");
						}
					System.out.println("(BOL) PresentDate Value: " + presentDate);
					System.out.println("(BOL) EndDate Value: " + endDate);
					presentDate = presentDate.plusDays(1);
					System.out.println("(BOL) Incemented PresentDate Value: " + presentDate);
				}
			System.out.println("Result size:" + result.size());
			System.out.println("***   ***");
			return result;
				}
			
		});
		
		
	}
	
	public List<Session> createSession(final String room, final LocalDate date, final int adminId, final LocalTime startTime, final LocalTime endTime, final int dayOfWeek, final int courseId, final int typeId){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				System.out.println("Currently in createSession. Here are the following details of the session being created and inserted:");
				System.out.println("Date: " + date);
				System.out.println("Room: " + room);
				System.out.println("Start Time: " + startTime);
				System.out.println("End Time: " + endTime);
				System.out.println("Day(s) of week: " + dayOfWeek);
				System.out.println("Admin ID: " + adminId);
				System.out.println("Course ID: " + courseId);
				System.out.println("Type ID: " + typeId);
				
				System.out.println("Creating new session...");
				
				try {
					stmt = conn.prepareStatement(
						"insert into Sessions (date, room, start_time, end_time, day_of_week, admin_id, course_id, type_id) " + //Inserting new session
						"values(?, ?, ?, ?, ?, ?, ?, ?)" //Where the parameters will be 'inserted'
							);
					stmt.setString(1, date.toString()); 	//Sends localDate to string and sets the first value as the parameter date
					stmt.setString(2, room); 				//Sets the second value to the parameter room
					stmt.setString(3, startTime.toString()); 	// Send LocalTime to string and sets the third value to the parameter time
					stmt.setString(4, endTime.toString()); 	// Send LocalTime to string and sets the third value to the parameter time
					stmt.setInt(5, dayOfWeek);				// Sets 5th value to day of week
					stmt.setInt(6, adminId); 				//Sets the 6th value to the parameter adminId
					stmt.setInt(7, courseId); 				//Sets the 7th value to the parameter courseId
					stmt.setInt(8, typeId);					//Sets the 8th value to the parameter typeId
					
					List<Session> result = new ArrayList<Session>(); //This will be used later to add the new session to. Utilized after stmt1 is executed.
					
					stmt.executeUpdate();					//Executes the SQL statement and inserts new session
					
					System.out.println("Session created and inserted into table...");
					
					stmt1 = conn.prepareStatement(
						"select sessions.* from Sessions " +
						"where date = ? and room = ? and start_time = ? and end_time = ? and day_of_week = ? and admin_id = ? and course_id = ? and type_id = ?"
							);
							
					stmt1.setString(1, date.toString()); 	//Sends localDate to string and sets the first value as the parameter date
					stmt1.setString(2, room); 				//Sets the second value to the parameter room
					stmt1.setString(3, startTime.toString()); 	// Send LocalTime to string and sets the third value to the parameter time
					stmt1.setString(4, endTime.toString()); 	// Send LocalTime to string and sets the third value to the parameter time
					stmt1.setInt(5, dayOfWeek);				// Sets 5th value to day of week
					stmt1.setInt(6, adminId); 				//Sets the 6th value to the parameter adminId
					stmt1.setInt(7, courseId); 				//Sets the 7th value to the parameter courseId
					stmt1.setInt(8, typeId);					//Sets the 8th value to the parameter typeId
					
					resultSet = stmt1.executeQuery();		//Executes the SQL statement and inserts new session
					
					System.out.println("A session was found. Checking to see if it contains the correct data...");
					
					if(resultSet.next()) { //Iterates through the resultSet; in a perfect world, it should only return one session
						Session session = new Session();
						loadSession(session, resultSet, 1);
						result.add(session);
					}
					else {
						System.out.println("Session for course<" + courseId + ">, room<" + room + "> has not been found...");
					}
					
					System.out.println("Returning session information...");
					
					return result;
					
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(conn);
					
				}
			
			}
		});
	}

	public List<Session> deleteSession(int sessionId) {
		//Deletes and returns session and takes sessionId as a parameter
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				try {
					stmt = conn.prepareStatement(
						"select sessions.* from Sessions " + 		//Inserting new session
						" where session_id = ?"					//Where the parameters will be 'inserted'
							);
					stmt.setInt(1, sessionId);					//Sets the value to the parameter, sessionId
					
					List<Session> result = new ArrayList<Session>();
					
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) { 						//Iterates through the resultSet; in a perfect world, it should only return one session
						Session session = new Session();
						loadSession(session, resultSet, 1);
						result.add(session);
					}
					else {
						System.out.println("Didn't find the session you're trying to delete. Try again, kiddo...");
					}
					
					
					
					stmt1 = conn.prepareStatement(
						"delete from Sessions where session_id = ? "
							);
					stmt1.setInt(1, sessionId);
					
					stmt1.executeUpdate();					//Executes the delete
					
					System.out.println("Deleted session...");
					
					return result;
				}finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	public List<Integer> getUserId(User user) {
		//Returns an empty list if user is not found
		return executeTransaction(new Transaction <List<Integer>>() {
			public List<Integer> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Integer> result = new ArrayList<Integer>();
				
				System.out.println("Currently getting ID number of User...");
				
				try {
					stmt = conn.prepareStatement(
						"select user_id from User " +
						"where email = ?, and name = ?"
						);
					resultSet = stmt.executeQuery();
				if(resultSet.next()) {
					Integer userId = resultSet.getInt(1); //////
					result.add(userId);
				}
				
				return result;
					
				}finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}
		});
	}
	public List<User> getTutors(){ 
		//This method only returns a list of users (with type User) and not tutors; will probably change when the tutor database is implemented 
		return executeTransaction(new Transaction<List<User>>() {
			public List<User> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				System.out.println("Currently getting list of tutors...");
				
				try {
					stmt = conn.prepareStatement(
						"select * from Users " + //Selects all users who are marked as tutors
						"where userType = ? " +
						 "order by Users.name asc"
							);
					
					stmt.setInt(1, 2); //For pulling users who are tutors from the Users database, their userType will always be 2
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
						User user = new User();
						loadUser(user, resultSet, 1);
						result.add(user);
					}
					
				
					
					System.out.println("Returning list of tutors...");
					
					return result;
					
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(conn);
				}
				
			}
		});
	}
	@Override
	public List<Integer> getTutors(int courseId){
		return executeTransaction(new Transaction<List<Integer>>() {
			public List<Integer> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				System.out.println("Currently getting list of tutors for given course id...");
				
				try {
					stmt = conn.prepareStatement(
						"select user_id from TutorFaculty " + //Selects all users who are marked as tutors
						"where userType = ? " +
						"and course_id = ? "
							);
					
					stmt.setInt(1, 2); //For pulling users who are tutors from the Users database, their userType will always be 2
					stmt.setInt(2, courseId);
					List<Integer> result = new ArrayList<Integer>();
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
						
						result.add(resultSet.getInt(1));
					}
					
				
					
					System.out.println("Returning list of tutors given course id...");
					
					return result;
					
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(conn);
				}
				
			}
		});
	}
	@Override
	public List<Session> getSession(int sessionId){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					List<Session> result = new ArrayList<Session>();
					
					stmt = conn.prepareStatement(
							"select * from Sessions " +
							" where session_id = ?"
							);
					
					stmt.setInt(1, sessionId);
					resultSet = stmt.executeQuery();
					
					
					
					while(resultSet.next()) {
						Session session = new Session();
						loadSession(session, resultSet, 1);
						result.add(session);
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	@Override
	public List<Integer> getSessionIdWithCourseId(int courseId) {
		return executeTransaction(new Transaction<List<Integer>>() {
			public List<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					List<Integer> result = new ArrayList<Integer>();
					
					stmt = conn.prepareStatement(
							"select * from Sessions " +
							" where course_id = ?" +
							" and type_id = ?"
							);
					
					stmt.setInt(1, courseId);
					stmt.setInt(2,  3);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {
						Session session = new Session();
						loadSession(session, resultSet, 1);
						result.add(session.getSessionId());
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	@Override
	public Session getSingleSession(int sessionId) {
		Session session = getSession(sessionId).get(0);
		if(session == null) {
			System.out.println(session.toString());
		}
		return session;
	}
	@Override
	public List<Session> getCourseSession(int courseId){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					List<Session> result = new ArrayList<Session>();
					
					stmt = conn.prepareStatement(
							"select * from Sessions " +
							" where course_id = ?" +
							" and type_id = ? "
							);
					
					stmt.setInt(1, courseId);
					stmt.setInt(2, 3);
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						System.out.println("Within while loop, see session id below:");
						Session session = new Session();
						loadSession(session, resultSet, 1);
						System.out.println("Session ID: " + session.getSessionId());
						result.add(session);
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	@Override
	public List<Session> getTutoringSession(int courseId){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					List<Session> result = new ArrayList<Session>();
					
					stmt = conn.prepareStatement(
							"select * from Sessions " +
							" where course_id = ?" +
							" and type_id = ? "
							);
					
					stmt.setInt(1, courseId);
					stmt.setInt(2, 1);
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						System.out.println("Within while loop, see session id below:");
						Session session = new Session();
						loadSession(session, resultSet, 1);
						System.out.println("Session ID: " + session.getSessionId());
						result.add(session);
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	@Override
	
	public List<String> getDayOfWeek(int sessionId){
		return executeTransaction(new Transaction<List<String>>() {
			public List<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				System.out.println("In getDayOfWeek, ");
				try {
					List<String> result = new ArrayList<String>();
					
					stmt = conn.prepareStatement(
							"select day_of_week from Sessions " +
							" where session_id = ?"
							);
					
					stmt.setInt(1, sessionId);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {
						int dayOfWeek = resultSet.getInt(1);
						
						if((dayOfWeek & 1) > 0) {
							result.add("Sunday");
						}
						if((dayOfWeek & 2) > 0) {
							result.add("Monday");
						}
						if((dayOfWeek & 4) > 0) {
							result.add("Tuesday");
						}
						if((dayOfWeek & 8) > 0) {
							result.add("Wednesday");
						}
						if((dayOfWeek & 16) > 0) {
							result.add("Thursday");
						}
						if((dayOfWeek & 32) > 0) {
							result.add("Friday");
						}
						if((dayOfWeek & 64) > 0) {
							result.add("Saturday");
						}
					}
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
			
		});
	}
	@Override
	public List<User> createAccount(final String email, final String password, final String name, final int userType){
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				/*
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
				System.out.println("name: "+ name +" userType: "+ userType);
				*/
				try {
					
					List<User> result = new ArrayList<User>();
					
					stmt1 = conn.prepareStatement(
							"insert into Users (email, password, name, userType)" +
							"values(?,?,?,?)"
					//sql to add an account to list
							);
					stmt1.setString(1, email);
					stmt1.setString(2, password);
					stmt1.setString(3, name);
					stmt1.setInt(4, userType);
					
					
					stmt1.executeUpdate();
					
				//	System.out.println("user created");
					
				
					
					stmt3 = conn.prepareStatement(
							"select * from Users" +
							" WHERE Users.email = ? and Users.password = ? and Users.name = ? and Users.userType = ?"
								
					//sql to add an account to list
							);
					
					stmt3.setString(1, email);
					stmt3.setString(2, password);
					stmt3.setString(3, name);
					stmt3.setInt(4, userType);
					
					resultSet = stmt3.executeQuery();
					Boolean found = false;

					while (resultSet.next()) {
						found = true;			
						User user = new User();
						loadUser(user,resultSet,1);
						result.add(user);
					
					}
					for (User temp : result) {
						System.out.println( temp.getUser_Id()+", "+temp.getEmail()+ ", " + temp.getPassword()+", " + temp.getName()+", " + temp.getUserType());
					}

					if(found = false) {
						System.out.println("<" + email + "> was not found in the user database");
					}
					
				//	System.out.println("user returned");
					return result;
			}finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt3);
					}
		
				}
			
		});
		
		
	}
	@Override
	public List<Student> createStudent(final String major, final String year, final int userid){
		return executeTransaction(new Transaction<List<Student>>() {
			@Override
			public List<Student> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				
				System.out.println("IN DERBY DATABASE");
				System.out.println("major: "+ major + " year: "+ year);
				System.out.println("userid: "+ userid);
				
				try {
					
					List<Student> result = new ArrayList<Student>();
					
					stmt1 = conn.prepareStatement(
							"insert into Students (major, gradYear, user_id)" +
							"values(?,?,?)"
					//sql to add an account to list
							);
					stmt1.setString(1, major);
					stmt1.setString(2, year);
					stmt1.setInt(3, userid);
					
					
					stmt1.executeUpdate();
					
					System.out.println("student created");
					
				
					
					stmt3 = conn.prepareStatement(
							"select * from Students" +
							" WHERE Students.major = ? and Students.gradYear = ? and Students.user_id = ?"
								
					//sql to add an account to list
							);
					
					stmt3.setString(1, major);
					stmt3.setString(2, year);
					stmt3.setInt(3, userid);
					
					resultSet = stmt3.executeQuery();
					Boolean found = false;

					while (resultSet.next()) {
						found = true;			
						Student stud = new Student();
						loadStudent(stud,resultSet,1);
						result.add(stud);
					}
					for (Student temp : result) {
						System.out.println(temp.getUser_Id()+", "+temp.getEmail()+ ", " + temp.getMajor()+ ", " + temp.getYear());
					}

					if(found = false) {
						System.out.println("<" + userid + "> was not found in the user database");
					}
					
					System.out.println("Student returned");
					return result;
			}finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt3);
					}
		
				}
			
		});
		
	}
	@Override
	public List<Course> getCourseFromCourseId(int courseId){
		return executeTransaction(new Transaction<List<Course>>() {
			public List<Course> execute(Connection conn) throws SQLException {
				System.out.println("*************************************");
				System.out.println("In DerbyDatabase, in getCourseFromCourseId");
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Course> result = new ArrayList<Course>();
				
				try {
					stmt = conn.prepareStatement(
							"select course.* " +
							" from Course " +
							" where course_id = ?"
					);
					
					stmt.setInt(1, courseId);
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
						Course course = new Course();
						loadCourse(course, resultSet, 1); //Alex...? Did you never make a loadCourse method?
						result.add(course);
						System.out.println("Course ID: " + course.getCourseId());
						System.out.println("Course Name: " + course.getTitle());
					}
					System.out.println("*************************************");
					
					return result;
					
				}finally{
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				
			}
		});
	}
	@Override
	public List<User> getAccount(final String email, final String password){
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * "+
							" from Users"+
							" where Users.email = ? and Users.password =?"
							);
					
					List<User> result = new ArrayList<User>();
					stmt.setString(1, email);
					stmt.setString(2, password);
					resultSet = stmt.executeQuery();
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;			
						User user = new User();
						loadUser(user,resultSet,1);
						result.add(user);
					
					}
					for (User temp : result) {
						System.out.println( temp.getUser_Id()+", "+temp.getEmail()+ ", " + temp.getPassword()+", " + temp.getName()+", " + temp.getUserType());
					}

					if(found = false) {
						System.out.println("<" + email + "> was not found in the user database");
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				
				}

			}
			});
		}
	@Override
	public Course getCourse(final int courseid){
		return executeTransaction(new Transaction<Course>() {
			@Override
			public Course execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * "+
							" from Course"+
							" where Course.course_Id = ? "
							);
					
					Course result = new Course();
					stmt.setInt(1, courseid);
					
					resultSet = stmt.executeQuery();
					Boolean found = false;
					
					if (resultSet.next()) {
						found = true;			
						Course course = new Course();
						loadCourse(course,resultSet,1);
						result = course;
					
					}
						System.out.println( result.getCourseId()+", "+result.getTitle()+ ", " + result.getSessionId());
					

					if(found = false) {
						System.out.println("<" + courseid + "> was not found in the user database");
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				
				}

			}
			});
		}
	@Override
	public List<Student> getStudent(final String email, final String password){
		return executeTransaction(new Transaction<List<Student>>() {
			@Override
			public List<Student> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				ResultSet resultSet1 = null;
				int userid = 0;
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
				try {
					stmt1 = conn.prepareStatement(
							"select * "+
							" from Users"+
							" where Users.email = ? and Users.password =?"
							);
					
					
					stmt1.setString(1, email);
					stmt1.setString(2, password);
					resultSet = stmt1.executeQuery();
					System.out.println("got user");
					Boolean found = false;
					
					while (resultSet.next()) {
					User user = new User();
					loadUser(user,resultSet,1);
					System.out.println("load user worked");
					userid = user.getUser_Id();
					System.out.println("user id: "+ userid);
					}
					
					stmt2 = conn.prepareStatement(
							"select * "+
							" from Students"+
							" where Students.user_id = ?"
							);
					
					List<Student> result = new ArrayList<Student>();
					stmt2.setInt(1, userid);
					resultSet1 = stmt2.executeQuery();
					
					while (resultSet1.next()) {
						found = true;			
						Student stud = new Student();
						loadStudent(stud,resultSet1,1);
						result.add(stud);
					
					}
					
				
					for (Student temp : result) {
						System.out.println( temp.getUser_Id()+", "+temp.getEmail()+ ", " + temp.getMajor()+ ", " + temp.getYear());
					}

					if(found = false) {
						System.out.println("<" + email + "> was not found in the Student database");
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				
				}

			}
			});
		}
	@Override
	public List<Integer> getUserIdbyCourseId(final int courseid){
		return executeTransaction(new Transaction<List<Integer>>() {
			@Override
			public List<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				int userid = 0;
				System.out.println("IN DERBY DATABASE");
				System.out.println("courseid: "+ courseid);
				try {
					stmt = conn.prepareStatement(
							"select TutorFaculty.user_id "+
							" from TutorFaculty" +
							" where TutorFaculty.course_id = ?"
							);
					
					List<Integer> result = new ArrayList<Integer>();
					stmt.setInt(1, courseid);
					
					resultSet = stmt.executeQuery();
					System.out.println("got id");
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;	
						found = true;	
						userid = resultSet.getInt(1);
						System.out.println("userid "  + userid);
						result.add(userid);
					
					}
					for (Integer temp : result) {
						System.out.println( temp);
					}

					if(found = false) {
						System.out.println("<" + courseid + "> Course was not found in the user database");
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				
				}

			}
			});
		}
	public List<Integer> getCourseidbyUserId(final int userid){
		return executeTransaction(new Transaction<List<Integer>>() {
			@Override
			public List<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				System.out.println("IN DERBY DATABASE");
				System.out.println("userid: "+ userid);
				try {
					stmt = conn.prepareStatement(
							"select TutorFaculty.course_id "+
							" from TutorFaculty"+
							" where  TutorFaculty.user_id  = ?"
							);
					
					List<Integer> result = new ArrayList<Integer>();
					stmt.setInt(1, userid);
					resultSet = stmt.executeQuery();
					System.out.println("got id");
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;	
						int courseid = resultSet.getInt(1);
						System.out.println("courseid "  + courseid);
						result.add(courseid);
					
					}
					for (Integer temp : result) {
						System.out.println( temp);
					}

					if(found = false) {
						System.out.println("<" + userid + "> user was not found in the user database");
					}
					return result;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				
				}

			}
			});
		}
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C/test/library.db;create=true");		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	
	}
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				//PreparedStatement stmt3 = null;				
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt8= null;

				System.out.println("Making Announcement table...");

					try {
						//create announcement table
						stmt1 = conn.prepareStatement(
							"create table Announcements (" +
							"	announcement_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	message varchar(500)," +
							"	date varchar(40)," +
							"	startTime varchar(40),"+
							"	endTime varchar(40)," +
							" announcementType integer,"+
							"typeId integer"+
							")"
						);	

					stmt1.executeUpdate();

					System.out.println("Announcements table created");

					
					//create user table
					stmt2 = conn.prepareStatement(
							"create table Users (" +
							"	user_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	email varchar(70)," +
							"	password varchar(70)," +
							"   name varchar(40)," +
							"	userType integer"+
							")"
					);

					stmt2.executeUpdate();

					System.out.println("Users table created");					


					//create session table
					stmt4 = conn.prepareStatement(
							"create table Sessions (" +
							"	session_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	date varchar(40)," +
							"	room varchar(40)," +
							"   start_time varchar(40)," +
							"	end_time varchar(40)," +
							"	day_of_week integer," +	
							"	admin_id integer," +
							"   course_id integer," +
							"	type_id integer" +
							")"
					);
					stmt4.executeUpdate();

					System.out.println("Sessions table created");	
					
					//create course table
					stmt5 = conn.prepareStatement(
						"create table Course (" +
						"	course_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	title varchar(70)," +
						"	session_id integer" +
						")"
					);	

					stmt5.executeUpdate();

					System.out.println("Announcements table created");
					
					//student table
					stmt6 = conn.prepareStatement(
							"create table TutorFaculty (" +
							"	admin_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"   user_id integer, " +
							"	course_id integer, " +
							"   userType integer" +
							")"
					);
					stmt6.executeUpdate();
					
					System.out.println("TutorFaculty table created");
					//student table
					stmt8 = conn.prepareStatement(
							"create table Students (" +
							"	student_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	major varchar(70), " +
							"	gradYear varchar(40), " +
							"   user_id integer" +
							")"
					);
					stmt8.executeUpdate();
					
					System.out.println("Students table created");	
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					//DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt8);
				}
			}
		});
	} 
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Announcement> announcementList;
				List<User> userList;
				List<Session> sessionList;
				List<TutorFaculty> tutorFacultyList;
				List<Course> courseList;
				//List<StudyGroup> studyGroupList;
				List<Student> studentList;
				
				try {
					announcementList	= InitialData.getAnnouncement();
					userList       		= InitialData.getUser();
					sessionList			= InitialData.getSession();
					tutorFacultyList	= InitialData.getTutorFaculty();
					courseList			= InitialData.getCourse();
					//studyGroupList 		= InitialData.getStudyGroup();		
					studentList 		= InitialData.getStudent();

				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAnnouncement     = null;
				PreparedStatement insertUser      		 = null;
				PreparedStatement insertSession    		= null;
				PreparedStatement insertTf		   		= null;
				PreparedStatement insertCourse	   		= null;
				//PreparedStatement insertStudyGroup = null;
				PreparedStatement insertStudent    = null;
				try {
					// populating announcement table
					insertAnnouncement = conn.prepareStatement("insert into Announcements (message, date, startTime, endTime, announcementType, typeId) "
							+ "values (?, ?, ?, ?, ?, ?)");
					for (Announcement announcement : announcementList) {
						insertAnnouncement.setString(1, announcement.getMessage());
						insertAnnouncement.setString(2, announcement.getDate().toString());
						insertAnnouncement.setString(3, announcement.getStartTime().toString());
						insertAnnouncement.setString(4, announcement.getEndTime().toString());
						insertAnnouncement.setInt(5, announcement.getAnnouncementType());
						insertAnnouncement.setInt(6, announcement.getTypeId());
						insertAnnouncement.addBatch();
					}
					insertAnnouncement.executeBatch();
					
					System.out.println("Announcement table populated");
					
					
					insertUser = conn.prepareStatement("insert into Users (email, password, name, userType) values (?, ?, ?, ?)");
					for (User user : userList) {
						insertUser.setString(1, user.getEmail());
						insertUser.setString(2, user.getPassword());
						insertUser.setString(3, user.getName());
						insertUser.setInt(4, user.getUserType());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					System.out.println("User table populated");					
					
					insertSession = conn.prepareStatement("insert into Sessions (date, room, start_time, end_time, day_of_week, admin_id, course_id, type_id) values (?, ?, ?, ?, ?, ?, ?, ?)");
					for (Session session : sessionList) {
						insertSession.setString(1, session.getDate().toString());
						insertSession.setString(2, session.getRoom());
						insertSession.setString(3, session.getStartTime().toString());
						insertSession.setString(4, session.getEndTime().toString());
						insertSession.setInt(5, session.getDayOfWeek());
						insertSession.setInt(6, session.getAdminId());
						insertSession.setInt(7, session.getCourseId());
						insertSession.setInt(8,  session.getTypeId());
						insertSession.addBatch();
					}
					insertSession.executeBatch();
					System.out.println("Session table populated");
					
					insertTf = conn.prepareStatement("insert into TutorFaculty (user_id, course_id, userType) values (?, ?, ?)");
					for (TutorFaculty tf : tutorFacultyList) {
						insertTf.setInt(1,tf.getUser_Id());
						insertTf.setInt(2,tf.getCourse_id());
						insertTf.setInt(3,tf.getUserType());
		
						insertTf.addBatch();
					}
					insertTf.executeBatch();
					
					System.out.println("TutorFaculty table populated");
					insertCourse = conn.prepareStatement("insert into Course (title, session_id) values (?, ?)");
					for (Course course : courseList) {
						insertCourse.setString(1, course.getTitle());
						insertCourse.setInt(2,course.getSessionId());
						insertCourse.addBatch();
					}
					insertCourse.executeBatch();
					
					System.out.println("Course table populated");
					
					
					insertStudent = conn.prepareStatement("insert into Students (major, gradYear, user_id) values (?, ?, ?)");
					for (Student stud : studentList) {
						insertStudent.setString(1,stud.getMajor());
						insertStudent.setString(2,stud.getYear());
						insertStudent.setInt(3,stud.getUser_Id());
						insertStudent.addBatch();
					}
					insertStudent.executeBatch();
					System.out.println("Students table populated");					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertAnnouncement);
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertSession);
					DBUtil.closeQuietly(insertTf);
					DBUtil.closeQuietly(insertCourse);
					//DBUtil.closeQuietly(insertStudyGroup);		
						DBUtil.closeQuietly(insertStudent);
					}
				}
			
		});
		
	}
}
