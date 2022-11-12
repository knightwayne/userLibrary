package apiData;
import apiData.crudOperations.*;
//Libraries
// import java.net.HttpURLConnection;
// import java.net.URL;

import java.io.File;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

//External JREs
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

public class MainApp{
	public static void displayTables()
	{
		Connection c = null;
	    Statement stmt = null;
		ResultSet rs=null;
	     try
	     {
	        //Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection("jdbc:sqlite:testDB.db");
	        c.setAutoCommit(false);
	        System.out.println("Opened database successfully");
			stmt = c.createStatement();
	     
        	rs = stmt.executeQuery( "SELECT * FROM " +  "USERINFO" + ";" );  
        	System.out.println("UserInfo Table");
	        while ( rs.next() ) {
	           int id = rs.getInt("id");
	           String name = rs.getString("name");
	           String email= rs.getString("email");
	           String password = rs.getString("password");
	           String address = rs.getString("address");
	           
	           System.out.println( "ID = " + id );
	           System.out.println( "NAME = " + name );
	           System.out.println( "EMAIL = " + email );
	           System.out.println( "PASSWORD = " + password );
	           System.out.println( "ADDRESS = " + address );
	           System.out.println();
	        }
	        rs.close();
	        
	        rs = stmt.executeQuery( "SELECT * FROM " +  "ARTICLE" + ";" );   
	        System.out.println("\nArticle Table");
	        while ( rs.next() ) {
		           
		           System.out.println( "ArticleId = " + rs.getInt("ARTICLE_ID") );
		           System.out.println( "Headline = " + rs.getString("HEADLINE") );
		           System.out.println( "Abstract = " + rs.getString("ABSTRACT") );
				   System.out.println( "URL = " + rs.getString("URL") );
		           System.out.println( "Date = " + rs.getString("DATE") );
		           System.out.println( "Rating = " + rs.getInt("RATING") );
				   System.out.println( "UserId = " + rs.getInt("USER_ID") );
	        }
	        rs.close();
	        
	        rs = stmt.executeQuery( "SELECT * FROM " +  "MOVIEREVIEW" + ";" );  
	        System.out.println("\nMovie Review Table");
	        while ( rs.next() ) {
		           
				System.out.println( "MovieId = " + rs.getInt("MOVIE_ID") );
				System.out.println( "TITLE = " + rs.getString("TITLE") );
				System.out.println( "CRITIC = " + rs.getString("CRITIC") );
				System.out.println( "SUMMARY = " + rs.getString("SUMMARY") );
				System.out.println( "URL = " + rs.getString("URL") );
				System.out.println( "Date = " + rs.getString("DATE") );
				System.out.println( "Rating = " + rs.getInt("RATING") );
				System.out.println( "UserId = " + rs.getInt("USER_ID") );
		 	}
	        rs.close();
	        
	        rs = stmt.executeQuery( "SELECT * FROM " +  "BOOKREVIEW" + ";" );
	        System.out.println("\nBook Review Table");
			while ( rs.next() ) {
		           
				System.out.println( "BOOK_ID = " + rs.getInt("BOOK_ID") );
				System.out.println( "TITLE = " + rs.getString("TITLE") );
				System.out.println( "CRITIC = " + rs.getString("CRITIC") );
				System.out.println( "SUMMARY = " + rs.getString("SUMMARY") );
				System.out.println( "URL = " + rs.getString("URL") );
				System.out.println( "Date = " + rs.getString("DATE") );
				System.out.println( "Rating = " + rs.getInt("RATING") );
				System.out.println( "UserId = " + rs.getInt("USER_ID") );
		 	}
	        rs.close();
        
	        stmt.close();
	        c.close();
	     }
	     catch ( Exception e ) 
	     {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	     }
	     System.out.println("Tables Read successfully");

	}
	
	public static void addUser(String NAME,String EMAIL,String PASSWORD,String ADDRESS)
	{
		try
		{
			Connection c = null;
			Statement stmt = null;
			String sql="";
			//Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:testDB.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			sql = "INSERT INTO USERINFO (NAME,EMAIL,PASSWORD,ADDRESS) "+
				"VALUES ('" + 
				NAME + "', '" +
				EMAIL + "', '" +
				PASSWORD + "', '" +
				ADDRESS +										
				"');";
			System.out.println("SQL: " + sql);
			stmt.executeUpdate(sql);

			//readData("USERINFO", "ID");

			stmt.close();
			c.commit();
			c.close();
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}
	//	4. Main App Switch-Case Loop
	public static void main(String[] args) throws SQLException, SQLWarning
	{
		//try{
			initDB.initDatabase();
			//fetchData();
			
			int input = 1;
			Scanner reader = new Scanner(System.in);
			do{
				System.out.println("Enter your Choice Input as a Number");
				input = reader.nextInt();
				System.out.println(input);
				
				switch (input) {
				case 0:
				{
					System.out.println("Display All Tables");
					displayTables();
					//break;
				}
				break;
				case 1:
				{
					System.out.println("Create a New Entry in Table. Enter UserId, TableAPI, Rating, Query");
					int userId=reader.nextInt();
					String Table=reader.next();
					int rating=reader.nextInt();
					String Query=reader.next();
					System.out.println(userId+Table+rating+Query);
					createData.createDataF(userId, Table, rating, Query);
					//break;
				}
				break;
				case 2:
				{
					System.out.println("Read From Table");
					String Table=reader.next();
					String Query=reader.next();
					System.out.println(Table+Query);
					readData.readDataF(Table, Query);
					//break;
				}
				break;
				case 3:
				{
					System.out.println("Update From Table");
					String Table=reader.next();
					String QueryValue=reader.next();
					String LHS=reader.next();
					String RHS=reader.next();
					//System.out.println(Table+Query);
					updateData.updateDataF(Table, QueryValue, LHS, RHS);
					//break;
				}
				break;
				case 4:
				{
					System.out.println("Delete From Table");
					String Table=reader.next();
					String LHS=reader.next();
					String RHS=reader.next();
					//System.out.println(Table+Query);
					deleteData.deleteDataF(Table, LHS, RHS);
					//break;
				}
				break;
				case 5:
				{
					System.out.println("Add New User");
					String NAME=reader.next();
					String EMAIL=reader.next();
					String PASSWORD=reader.next();
					String ADDRESS=reader.next();
					addUser(NAME, EMAIL, PASSWORD, ADDRESS);
					//break;
				}
				break;
				case 6:
				{
					System.out.println("Exit Case");
				}
				break;
				default:
					System.out.println("Wrong Input");
				}
				
			} while (!(input==6));
			
			reader.close();
			System.out.println("Program Exiting");
			
			File f= new File("D:\\CodeRepository\\1 Codebase\\Eclipse\\apiData\\testDB.db");           //file to be delete  
			if(f.delete())                      //returns Boolean value  
			{  
				System.out.println(f.getName() + " deleted");   //getting and printing the file name  
			}  
			else  
			{  
				System.out.println("failed");  
			}  
			
			return;
		// }
		// catch(Exception e)
		// {
		// 	System.out.println("Error");
		// 	File f= new File("D:\\CodeRepository\\1 Codebase\\Eclipse\\apiData\\testDB.db");           //file to be delete  
		// 	if(f.delete())                      //returns Boolean value  
		// 	{  
		// 		System.out.println(f.getName() + " deleted");   //getting and printing the file name  
		// 	}  
		// 	else  
		// 	{  
		// 		System.out.println("failed");  
		// 	}  
		// }
}
}
