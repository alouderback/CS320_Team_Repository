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
				System.out.println("IN DERBY DATABASE");
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
					System.out.println("done");
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
				
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
		 
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
					
					System.out.println("user deleted");
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
					
					System.out.println("user gone");
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
	public Integer createAnnouncement(final String message, final LocalDate date, final LocalTime time, final int announcementType, final int typeId){
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
						"insert into Announcements(message, date, time, announcementType, typeId) "
							+"values(?, ?, ?, ?, ?)"
							);
					stmt.setString(1, message);
					stmt.setString(2,  date.toString());
					stmt.setString(3, time.toString());
					stmt.setInt(4, announcementType);
					stmt.setInt(5, typeId);
					
					stmt.executeUpdate();
					
					System.out.println("Announcement added");
					System.out.println("Retreiving announcement ID...");
					
					stmt2 = conn.prepareStatement(
						"select announcement_id from Announcements "+
						"where message = ? and date = ? and time = ?"
					);
					stmt2.setString(1, message);
					stmt2.setString(2, date.toString());
					stmt2.setString(3, time.toString());
					
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
					"where typeId = ? and announcementType = 1 "+
					"order by Announcements.date desc "
					);
					stmt.setInt(1, sessionId);
					
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
					"where typeId = ? and announcementType = 2 "+
					"order by Announcements.date desc"
					);
					stmt.setInt(1, studyGroupId);
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
					"order by Announcements.date desc "
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
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUser_Id((resultSet.getInt(index++)));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		user.setEmail((resultSet.getString(index++)));
		user.setPassword((resultSet.getString(index++)));
		user.setName((resultSet.getString(index++)));
		user.setUserType((resultSet.getInt(index++)));
		
	}
	private void loadAnnouncement(Announcement announcement, ResultSet resultSet, int index) throws SQLException {
		announcement.setAnnouncementId(resultSet.getInt(index++));
		announcement.setMessage(resultSet.getString(index++));
		LocalDate date = LocalDate.now();
		date = LocalDate.parse(resultSet.getString(index++));
		announcement.setDate(date);
		LocalTime time = LocalTime.now();
		time = LocalTime.parse(resultSet.getString(index++));
		announcement.setTime(time);
		announcement.setAnnouncementType(resultSet.getInt(index++));
		announcement.setTypeId(resultSet.getInt(index++));
	}
	public List<Session> getScheduleByDate(String timeframe){
		//Method currently doesn't use parameter timeframe; returns whole list of sessions
		return executeTransaction(new Transaction<List<Session>>() {
			
			public List<Session> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				List<Session> result = null;
				
				System.out.println("IN DERBY DATABASE");
				System.out.println("In getScheduleByDate");
				try {
					stmt = conn.prepareStatement(
							"select sessions.* " +
							" from Sessions "
					//Trying to get all sessions
							);
					
					resultSet = stmt.executeQuery();
					
					System.out.println("Query Executed");
					
					System.out.println("Got all sessions");
					
					result = new ArrayList<Session>();
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
			System.out.println("Result size:" + result.size());
			return result;
				}
			
		});
		
		
	}
	private void loadSession(Session session, ResultSet resultSet, int index) throws SQLException {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		session.setSessionId((resultSet.getInt(index++)));
		date = LocalDate.parse(resultSet.getString(index++));
		session.setDate(date);
		session.setRoom(resultSet.getString(index++));
		time = LocalTime.parse(resultSet.getString(index++));
		session.setTime(time);
		session.setTutorId(resultSet.getInt(index++));
		session.setCourse(resultSet.getString(index++));
		System.out.println("See below for course:");
		System.out.println("Course for session that is being loaded: " + session.getCourse());
	}
	
	public List<Session> createSession(final String room, final LocalDate date, final int tutorId, final LocalTime time, final String course){
		return executeTransaction(new Transaction<List<Session>>() {
			public List<Session> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				System.out.println("Currently in createSession. Here are the following details of the session being created and inserted:");
				System.out.println("Room: " + room);
				System.out.println("Date: " + date);
				System.out.println("Tutor ID: " + tutorId);
				System.out.println("Time: " + time);
				System.out.println("Creating new session...");
				
				try {
					stmt = conn.prepareStatement(
						"insert into Sessions (date, room, time, tutor_id, course) " + //Inserting new session
						"values(?, ?, ?, ?, ?)" //Where the parameters will be 'inserted'
							);
					stmt.setString(1, date.toString()); 	//Sends localDate to string and sets the first value as the parameter date
					stmt.setString(2, room); 				//Sets the second value to the parameter room
					stmt.setString(3, time.toString()); 	// Send LocalTime to string and sets the third value to the parameter time
					stmt.setInt(4, tutorId); 				//Sets the fourth value to the parameter tutorId
					stmt.setString(5, course); 				//Sets the fifth value to the parameter course
					
					List<Session> result = new ArrayList<Session>(); //This will be used later to add the new session to. Utilized after stmt1 is executed.
					
					stmt.executeUpdate();					//Executes the SQL statement and inserts new session
					
					System.out.println("Session created and inserted into table...");
					
					stmt1 = conn.prepareStatement(
						"select sessions from Sessions " +
						"where date = ? and room = ? and time = ? and tutor_id = ? and course = ?"
							);
							
					stmt1.setString(1, date.toString()); 	//Sends localDate to string and sets the first value as the parameter date
					stmt1.setString(2, room); 				//Sets the second value to the parameter room
					stmt1.setString(3, time.toString()); 	// Send LocalTime to string and sets the third value to the parameter time
					stmt1.setInt(4, tutorId); 				//Sets the fourth value to the parameter tutorId
					stmt1.setString(5, course); 			//Sets the fifth value to the parameter course
					
					resultSet = stmt1.executeQuery();		//Executes the SQL statement and inserts new session
					
					System.out.println("A session was found. Checking to see if it contains the correct data...");
					
					if(resultSet.next()) { //Iterates through the resultSet; in a perfect world, it should only return one session
						Session session = new Session();
						loadSession(session, resultSet, 1);
						result.add(session);
					}
					else {
						System.out.println("Session for course<" + course + ">, room<" + room + "> has not been found...");
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
						"select sessions from Sessions " + 		//Inserting new session
						"where session_id = ?"					//Where the parameters will be 'inserted'
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
					
					stmt1.executeQuery();						//Executes the delete
					
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
						"select users from Users " + //Selects all users who are marked as tutors
						"where userType = ?"
							);
					
					stmt.setInt(1, 2); //For pulling users who are tutors from the Users database, their userType will always be 2
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {
						User user = new User();
						loadUser(user, resultSet, 1);
						result.add(user);
					}
					
					else {
						System.out.println("That's all the tutors...");
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
	public List<User> createAccount(final String email, final String password, final String name, final int userType){
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
				System.out.println("name: "+ name +" userType: "+ userType);
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
					
					System.out.println("user created");
					
					stmt1 = conn.prepareStatement(
							"select user_id from Users " +
							"where email = ? and password = ? and name = ? and userType = ? "
							);
					
					
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
					
					System.out.println("user returned");
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
		//EDIT THIS
		private Connection connect() throws SQLException {
			Connection conn = DriverManager.getConnection("jdbc:derby:C/test/library.db;create=true");		
			// jdbc:mysql://localhost:8081/
			//jdbc:derby:DerbyDB;create=true
			//"jdbc:derby:C:/Users/isabe/Documents/Cs320/library.db;create=true"
			//jdbc:derby:C:/CS320-2019-LibraryExample-DB/library.db;create=true
			//both broken 
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
					PreparedStatement stmt3 = null;				

					PreparedStatement stmt4 = null;
					PreparedStatement stmt8= null;

					System.out.println("Making Announcement table...");

					try {
						//create announcement table
						stmt1 = conn.prepareStatement(
							"create table Announcements (" +
							"	announcement_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	message varchar(40)," +
							"	date varchar(40)," +
							"	time varchar(40),"+
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


	////////////////////EDIT STUDY GROUPS TABLE MUST BE JUNCTION\\\\\\\\\\\\\\\\\\\\\\\\\\\\
						/*stmt3 = conn.prepareStatement(
								"create table StudyGroups (" +
								"	book_id   integer constraint book_id references books, " +
								"	author_id integer constraint author_id references authors " +
								")"
						);

						stmt3.executeUpdate(); */

						
						//create study group table
						stmt3 = conn.prepareStatement(
								"create table Groups (" +
								"	group_id integer primary key " +
								"		generated always as identity (start with 1, increment by 1), " +
								"	course_id integer, " +
								"	session_id integer" +
								")"
						
						);
						stmt3.executeUpdate();
						System.out.println("Study Group table created");


						//create session table
						stmt4 = conn.prepareStatement(
								"create table Sessions (" +
								"	session_id integer primary key " +
								"		generated always as identity (start with 1, increment by 1), " +
								"	date varchar(40)," +
								"	room varchar(40)," +
								"   time varchar(40)," +
								"	tutor_id integer," +
								"   course varchar(40)" +
								")"
						);
						stmt4.executeUpdate();

						System.out.println("Sessions table created");	

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
						DBUtil.closeQuietly(stmt3);
						DBUtil.closeQuietly(stmt4);
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
					//List<StudyGroup> studyGroupList;
					List<Student> studentList;
					
					try {
						announcementList	= InitialData.getAnnouncement();
						userList       		= InitialData.getUser();
						sessionList			= InitialData.getSession();

						//studyGroupList 		= InitialData.getStudyGroup();		
						studentList 		= InitialData.getStudent();

					} catch (IOException e) {
						throw new SQLException("Couldn't read initial data", e);
					}

					PreparedStatement insertAnnouncement     = null;
					PreparedStatement insertUser       = null;
					PreparedStatement insertSession    = null;
					//PreparedStatement insertStudyGroup = null;
					PreparedStatement insertStudent    = null;
					try {
						// populating announcement table
						insertAnnouncement = conn.prepareStatement("insert into Announcements (message, date, time, announcementType, typeId) "
								+ "values (?, ?, ?, ?, ?)");
						for (Announcement announcement : announcementList) {
							insertAnnouncement.setString(1, announcement.getMessage());
							insertAnnouncement.setString(2, announcement.getDate().toString());
							insertAnnouncement.setString(3, announcement.getTime().toString());
							insertAnnouncement.setInt(4, announcement.getAnnouncementType());
							insertAnnouncement.setInt(5, announcement.getTypeId());
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
						

						//study group garbage

						/*insertStudyGroup = conn.prepareStatement("");
						for (StudyGroup sg: studyGroupList) {
							
						}	
						*/
						insertSession = conn.prepareStatement("insert into Sessions (date, room, time, tutor_id, course) values (?, ?, ?, ?, ?)");
						for (Session session : sessionList) {
							insertSession.setString(1, session.getDate().toString());
							insertSession.setString(2, session.getRoom());
							insertSession.setString(3, session.getTime().toString());
							insertSession.setInt(4, session.getTutorId());

							insertSession.setString(5, session.getCourse());
							insertSession.addBatch();
						}
						insertSession.executeBatch();
						System.out.println("Session table populated");	
						
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
						//DBUtil.closeQuietly(insertStudyGroup);		
						DBUtil.closeQuietly(insertStudent);
					}
				}
			});
		}

}
