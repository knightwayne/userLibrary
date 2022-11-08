package apiData;
//Libraries
// import java.net.HttpURLConnection;
// import java.net.URL;

// import java.io.File;
// import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.SQLWarning;

// //External JREs
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

public class initDB {
    public static void initDatabase()
	{
		Connection c;
	    Statement stmt = null;
	    String sql="";
		
		try
		{
			//Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:testDB.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			
			sql = "CREATE TABLE USERINFO " +
							"(ID INTEGER PRIMARY KEY	AUTOINCREMENT," +
							" NAME	TEXT    NOT NULL, " + 
							" EMAIL	TEXT     NOT NULL, " + 
							" PASSWORD	TEXT	NOT NULL, " + 
							" ADDRESS TEXT)"; 
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE ARTICLE " +  
					"(ARTICLE_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+  
					"HEADLINE TEXT NOT NULL, "+
					"ABSTRACT TEXT NOT NULL, "+
					"URL TEXT NOT NULL, "+
					"DATE TEXT NOT NULL, "+
					"RATING INT DEFAULT 0 NOT NULL, "+ 
					"USER_ID INTEGER, "+
					"FOREIGN KEY (USER_ID) REFERENCES USERINFO(ID))";
			stmt.executeUpdate(sql);
				
			sql = "CREATE TABLE MOVIEREVIEW " +  
				"(MOVIE_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+  
				"TITLE TEXT NOT NULL, "+
				"CRITIC TEXT NOT NULL, "+
				"SUMMARY TEXT NOT NULL, "+
				"URL TEXT NOT NULL, "+
				"DATE TEXT NOT NULL, "+
				"RATING INT DEFAULT 0 NOT NULL, "+
				"USER_ID INTEGER, "+
				"FOREIGN KEY (USER_ID) REFERENCES USERINFO(ID))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE BOOKREVIEW " +  
				"(BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+  
				"TITLE TEXT NOT NULL, "+
				"CRITIC TEXT NOT NULL, "+
				"SUMMARY TEXT NOT NULL, "+
				"URL TEXT NOT NULL, "+
				"DATE TEXT NOT NULL, "+
				"RATING INT DEFAULT 0 NOT NULL, "+
				"USER_ID INTEGER, "+
				"FOREIGN KEY (USER_ID) REFERENCES USERINFO(ID))";
			stmt.executeUpdate(sql);
			
			
			sql = "INSERT INTO USERINFO (ID,NAME,EMAIL,PASSWORD,ADDRESS) " +
					"VALUES (1, 'Alpha', 'alp@test.com', 'alpPwd', 'Left' );"; 
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO USERINFO (NAME,EMAIL,PASSWORD,ADDRESS) " +
					"VALUES ('Beta', 'bet@test.com', 'betPwd', 'Right' );"; 
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO USERINFO (NAME,EMAIL,PASSWORD,ADDRESS) " +
					"VALUES ('Gamma', 'gam@test.com', 'gamPwd', 'Down' );"; 
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO ARTICLE (ARTICLE_ID, HEADLINE, ABSTRACT, URL, DATE, RATING, USER_ID) " +
					"VALUES (1, 'xx', 'yy', 'zz', 'ww', 3, 2);";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO MOVIEREVIEW (TITLE, CRITIC, SUMMARY, URL, DATE, RATING, USER_ID) " +
					"VALUES ('xx1', 'yy1', 'zz1', 'vv1', 'ww1', 7, 3 );"; 
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO BOOKREVIEW (TITLE, CRITIC, SUMMARY, URL, DATE, RATING, USER_ID) " +
					"VALUES ('xx2', 'yy2', 'zz2', 'vv2', 'ww2', 6, 1 );"; 
			stmt.executeUpdate(sql);
			
			c.commit();
			MainApp.displayTables();
			
			stmt.close();
			c.close();
		}
		catch ( Exception e )
	     {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	     }
	     System.out.println("Table created successfully");
	}
	
	
}
