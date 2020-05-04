package tutoringWebsite.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tutoringWebsite.model.*;


public interface RDatabase {
	 List<String> getEmails();
}