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
	public List<Login> useLogin(final String email, final String password){
		return executeTransaction(new Transaction<List<Login>>() {
			@Override
			public List<Login> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select Users.email, Users.password"+
							" from Users"+
							"where Users.email = ? and Users.password = ?"
							);

				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			return null;
			}
				});
	}
	@Override
	public List<Login> signIntoAccount(final String email, final String password){
		return executeTransaction(new Transaction<List<Login>>() {
			@Override
			public List<Login> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							""
							);

					stmt.setString(1, email);
					stmt.setString(2, password);
					
					resultSet = stmt.executeQuery();
					

				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			return null;
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
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				List<Announcement> result;
				int resultId = -1;
				
				try {
					System.out.println("Adding Announcement...");
					stmt = conn.prepareStatement(
						"insert into Announcements(message, date, time, announcementType) "
							+"values(?, ?, ?, ?)"
							);
					stmt.setString(1, message);
					stmt.setString(2,  date.toString());
					stmt.setString(3, time.toString());
					stmt.setInt(4, announcementType);
					
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
					
					if (resultSet.next())
					{
						resultId = resultSet.getInt(1);
						System.out.println("New announcement <" + message + "> ID: " + resultId);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New announcement <" + message + "> not found in Books table (ID: " + resultId);
					}
					
					//check if its session or study group
					if(announcementType == 1) {//session type
						stmt3 = conn.prepareStatement(
								"insert into SessionAnnouncement (announcement_id) " +
								"  values(?) "
								);
						//stmt3.setInt(1, typeId);
						stmt3.setInt(1, resultId);
						
						stmt3.executeUpdate();
						System.out.println("Announcement ID added to SessionAnnouncement table");
					}
					else if(announcementType == 2) {//study group type
						stmt3 = conn.prepareStatement(
								"insert into StudyGroupAnnouncement (announcement_id) " +
								"  values(?) "
								);
						//stmt3.setInt(1, typeId);
						stmt3.setInt(1, resultId);
						
						stmt3.executeUpdate();
						System.out.println("Announcement ID added to StudyGroupAnnouncement table");
					}
					else {
						System.out.println("Incorrect announcementType");
					}
					
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
				System.out.println("Returning announcement");
			return resultId;
			}
			});
		
	}
	@Override
	public List<Announcement> removeAnnouncement(final int announcementId, final int announcementType) {
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
					
						if(announcementType==1) {
							stmt3 = conn.prepareStatement(
								"delete from SessionAnnouncement "+
								"where announcement_id = ?"
							);
							stmt3.setInt(1, announcementId);
							stmt3.executeUpdate();
						}
						else if(announcementType == 2) {
							stmt3 = conn.prepareStatement(
								"delete from StudyGroupAnnouncement "+
								"where announcement_id = ?"
							);
							stmt3.setInt(1, announcementId);
							stmt3.executeUpdate();
						}
						else {
							System.out.println("Incorrect announcementType");
						}
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
					stmt = conn.prepareStatement(
						"select announcements.* "+
					"from Announcements, Sessions, SessionAnnouncement "+
					"where session_id = ? and Sessions.session_id = SessionAnnouncement.session_id "+
					"order by Sessions.date desc"
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
					"from Announcements, StudyGroups, StudyGroupAnnouncement "+
					"where studyGroup_id = ? and StudyGroups.studyGroup_id = StudyGroupAnnouncement.studyGroup_id "+
					"order by StudyGroups.date desc"
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
		announcement.setAnnouncementType(resultSet.getInt(index++));
		LocalDate date = LocalDate.now();
		date = LocalDate.parse(resultSet.getString(index++));
		announcement.setDate(date);
		LocalTime time = LocalTime.now();
		time = LocalTime.parse(resultSet.getString(index++));
		announcement.setTime(time);
	}
	
	@Override
	public List<User> createAccount(final String email, final String password, final String name, final int userType){
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				System.out.println("IN DERBY DATABASE");
				System.out.println("email: "+ email + " password: "+ password);
				System.out.println("name: "+ name +" userType: "+ userType);
				try {
					stmt = conn.prepareStatement(
							"insert into Users (email, password, name, userType) " +
							"values(?,?,?,?)"
					//sql to add an account to list
							);
					stmt.setString(1, email);
					stmt.setString(2, password);
					stmt.setString(3, name);
					stmt.setInt(4, userType);
					List<User> result = new ArrayList<User>();
					
					stmt.executeUpdate();
					
					System.out.println("user created");
					
					
					stmt1 = conn.prepareStatement(
							"select user_id from Users " +
							"where email = ? and password = ? and name = ? and userType = ? "
							);
					
					stmt1.setString(1, email);
					stmt1.setString(2, password);
					stmt1.setString(3, name);
					stmt1.setInt(4, userType);
					
					resultSet = stmt1.executeQuery();
				
					System.out.println("user found");
					
					
					if (resultSet.next()) {
						
						User user = new User();
						loadUser(user,resultSet,1);
						result.add(user);
					}
					
					// check if the title was found
					else {
						System.out.println("<" + email + "> was not found in the user database");
					}
					
					System.out.println("user returned");
					return result;
			}finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt1);
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
					//PreparedStatement stmt3 = null;	
					PreparedStatement stmt4 = null;
					PreparedStatement stmt5 = null;
					PreparedStatement stmt6 = null; 
					System.out.println("Making Announcement table...");
					try {
						stmt1 = conn.prepareStatement(
							"create table Announcements (" +
							"	announcement_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	message varchar(40)," +
							"	date varchar(40)," +
							"	time varchar(40),"+
							" announcementType integer"+
							")"
						);	

						stmt1.executeUpdate();

						System.out.println("Announcements table created");

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

						stmt4 = conn.prepareStatement(
								"create table Sessions (" +
								"	session_id integer primary key " +
								"		generated always as identity (start with 1, increment by 1), " +
								"	date varchar(40)," +
								"	room varchar(40)," +
								"   time varchar(40)," +
								"	tutor_id integer"+
								")"
						);
						stmt4.executeUpdate();
						
						System.out.println("Sessions table created");	
						
						stmt5 = conn.prepareStatement(
							"create table SessionAnnouncement ( "+
							"session_id integer primary key " +
							"generated always as identity (start with 1, increment by 1), " +
							"announcement_id integer "+
							")"
						);
						stmt5.executeUpdate();
						
						System.out.println("SessionAnnouncement table created");
						
						stmt6 = conn.prepareStatement(
								"create table StudyGroupAnnouncement ( "+
								"studyGroup_id integer primary key " +
								"generated always as identity (start with 1, increment by 1), " +
								"announcement_id integer "+
								")"
							);
							stmt6.executeUpdate();
							
							System.out.println("StudyGroupAnnouncement table created");

						return true;
					} finally {
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt2);
						//DBUtil.closeQuietly(stmt3);
						DBUtil.closeQuietly(stmt4);
						DBUtil.closeQuietly(stmt5);
						DBUtil.closeQuietly(stmt6);
						
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
					
					try {
						announcementList	= InitialData.getAnnouncement();
						userList       		= InitialData.getUser();
						//sessionList			= InitialData.getSession();
						//studyGroupList 		= InitialData.getStudyGroup();					
					} catch (IOException e) {
						throw new SQLException("Couldn't read initial data", e);
					}

					PreparedStatement insertAnnouncement     = null;
					PreparedStatement insertUser       = null;
					//PreparedStatement insertStudyGroup = null;

					try {
						// populating announcement table
						insertAnnouncement = conn.prepareStatement("insert into Announcements (message, date, time) values (?, ?, ?)");
						for (Announcement announcement : announcementList) {
							insertAnnouncement.setString(1, announcement.getMessage());
							insertAnnouncement.setString(2, announcement.getDate().toString());
							insertAnnouncement.setString(3, announcement.getTime().toString());
							insertAnnouncement.addBatch();
						}
						insertAnnouncement.executeBatch();
						
						System.out.println("Annoucement table populated");
						
						// must completely populate Books table before populating BookAuthors table because of primary keys
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
									
						
						return true;
					} finally {
						DBUtil.closeQuietly(insertAnnouncement);
						DBUtil.closeQuietly(insertUser);
						//DBUtil.closeQuietly(insertStudyGroup);				
					}
				}
			});
		}

}
