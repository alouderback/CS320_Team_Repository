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

public class readOnlyDatabase implements RDatabase{
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
	public List<String> getEmails(){ 
		//This method only returns a list of users (with type User) and not tutors; will probably change when the tutor database is implemented 
		return executeTransaction(new Transaction<List<String>>() {
			public List<String> execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				System.out.println("Currently getting list of all Emails...");
				
				try {
					stmt = conn.prepareStatement(
						"select * from ycpEmails " //Selects all users who are marked as tutors
							);
					
					
					List<String> result = new ArrayList<String>();
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
						String email= null;
						email= ((resultSet.getString(2)));
						result.add(email);
					//	System.out.println(email);					works!!
						}
					
						System.out.println("That's all the emails...");
					
					
					System.out.println("Returning list of emails...");
					
					return result;
					
				}finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(conn);
				}
				
			}
		});
	}
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
		Connection conn = DriverManager.getConnection("jdbc:derby:R/test/library.db;create=true");		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		readOnlyDatabase db = new readOnlyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("readOnly DB successfully initialized!");
		//db.getEmails(); test if getting emails 
	}

public void createTables() {
	executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement stmt1 = null;
			try {
				//create announcement table
				stmt1 = conn.prepareStatement(
					"create table ycpEmails (" +
					"	email_id integer primary key " +
					"		generated always as identity (start with 1, increment by 1), " +									
					"	email varchar(40)" +
					")"
				);	

				stmt1.executeUpdate();

				System.out.println("ycpEmails table created");
				
				return true;
			} finally {
				DBUtil.closeQuietly(stmt1);
			}
		}
	});
	
	
} 
public void loadInitialData() {
	executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			List<String> emailList;
			try {
				emailList	= InitialData.getycpEmails();
			} catch (IOException e) {
				throw new SQLException("Couldn't read initial data", e);
			}

			PreparedStatement insertEmails     = null;
			try {
				// populating announcement table
				insertEmails = conn.prepareStatement("insert into ycpEmails (email) "
						+ "values (?)");
				for (String email : emailList) {
					insertEmails.setString(1, email);
					//System.out.println(email);			works!!!
					insertEmails.addBatch();
				}
				insertEmails.executeBatch();
				
				System.out.println("emailList table populated");
				return true;
			} finally {
				DBUtil.closeQuietly(insertEmails);
			}
		}
	});
}
}