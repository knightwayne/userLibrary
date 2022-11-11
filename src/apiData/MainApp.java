package apiData;
//Libraries
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.File;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

//External JREs
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	
	public static void fetchData(int userId, String table, int rating, String query)
	{
		try
		{
			Connection c = null;
			Statement stmt = null;
			
			//Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:testDB.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			if(table.equals("article"))
			{
				String urlString="https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=headline:(\"" + query+ "\")&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
				System.out.println("API URL: "+ urlString);
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				if (responsecode != 200)
				{
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				}
				else
				{

					String inline = "";
					Scanner scanner = new Scanner(url.openStream());
					while (scanner.hasNext()) {
						inline += scanner.nextLine();
					}
					scanner.close();
					// System.out.println("API Fetched Data: "+inline.toString());
					
					String parsed = inline.toString();
					JSONParser parser = new JSONParser();
					JSONObject data_obj = (JSONObject) parser.parse(parsed);
					// System.out.println(data_obj.toString());
					
					// String ss=data_obj.get("num_results").toString();
					// int nn= Integer.parseInt(ss);
					// System.out.println(nn);
					
					JSONObject articleList = (JSONObject) data_obj.get("response");
					JSONArray articleArr= (JSONArray) articleList.get("docs");
					System.out.println("Size of Array for Articles: "+articleArr.size());
					for (int i = 0; i < articleArr.size(); i++)
					{
						System.out.println("Loop Iter: " + i);
						JSONObject new_obj = (JSONObject) articleArr.get(i);
						//System.out.println(new_obj.toString());
						JSONObject r = (JSONObject) new_obj.get("headline");
						String headline=r.get("main").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");	//Rectify
						String abstractA=new_obj.get("abstract").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						String urlA=new_obj.get("web_url").toString();
						String date=new_obj.get("pub_date").toString();
						String sql = "INSERT INTO ARTICLE (HEADLINE, ABSTRACT, URL, DATE, RATING, USER_ID) "+
							"VALUES ('" + 
								headline + "', '" +
								abstractA + "', '" +
								urlA + "', '" +
								date + "', " +
								rating + ", " +										
								userId +											
								");";

							System.out.println("SQL stmt: "+sql);
							stmt.executeUpdate(sql);
					}
					
					System.out.println("End of Insert Data into Articles");
				}
				//End Article
			}
			else if(table.equals("movie"))
			{
				String urlString="https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=" + query+ "&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
				System.out.println("API URL: "+ urlString);
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				if (responsecode != 200)
				{
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				}
				else
				{

					String inline = "";
					Scanner scanner = new Scanner(url.openStream());
					while (scanner.hasNext()) {
						inline += scanner.nextLine();
					}
					scanner.close();
					// System.out.println("API Fetched Data: "+inline.toString());
					
					String parsed = inline.toString();
					JSONParser parser = new JSONParser();
					JSONObject data_obj = (JSONObject) parser.parse(parsed);
					// System.out.println(data_obj.toString());
					
					JSONArray movieArr= (JSONArray) data_obj.get("results");
					System.out.println(data_obj.get("num_results").toString() + "Size of Array for Movies: "+movieArr.size());
					for (int i = 0; i < movieArr.size(); i++)
					{
						System.out.println("Loop Iter: " + i);
						JSONObject new_obj = (JSONObject) movieArr.get(i);
						//System.out.println(new_obj.toString());
						
						String display_title=new_obj.get("display_title").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						String critic=new_obj.get("byline").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						String summary=new_obj.get("summary_short").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						JSONObject r = (JSONObject) new_obj.get("link");
						String urlA=r.get("url").toString();
						String date=new_obj.get("publication_date").toString();
						String sql = "INSERT INTO MOVIEREVIEW (TITLE, CRITIC, SUMMARY, URL, DATE, RATING, USER_ID) "+
							"VALUES ('" + 
								display_title + "', '" +
								critic + "', '" +
								summary + "', '" +
								urlA + "', '" +
								date + "', " +
								rating + ", " +										
								userId +											
								");";

							System.out.println("SQL stmt: "+sql);
							stmt.executeUpdate(sql);
					}
					
					System.out.println("End of Insert Data into Movies");
				}
				//End Movie
			}
			else if(table.equals("book"))
			{
				String urlString="https://api.nytimes.com/svc/books/v3/reviews.json?title=" + query+ "&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
				System.out.println("API URL: "+ urlString);
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				if (responsecode != 200)
				{
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				}
				else
				{

					String inline = "";
					Scanner scanner = new Scanner(url.openStream());
					while (scanner.hasNext()) {
						inline += scanner.nextLine();
					}
					scanner.close();
					// System.out.println("API Fetched Data: "+inline.toString());
					
					String parsed = inline.toString();
					JSONParser parser = new JSONParser();
					JSONObject data_obj = (JSONObject) parser.parse(parsed);
					// System.out.println(data_obj.toString());
					
					JSONArray bookArr= (JSONArray) data_obj.get("results");
					System.out.println(data_obj.get("num_results").toString() + "Size of Array for Books: "+bookArr.size());
					for (int i = 0; i < bookArr.size(); i++)
					{
						System.out.println("Loop Iter: " + i);
						JSONObject new_obj = (JSONObject) bookArr.get(i);
						//System.out.println(new_obj.toString());
						
						String book_title=new_obj.get("book_title").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						String critic=new_obj.get("byline").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						String summary=new_obj.get("summary").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
						String urlA=new_obj.get("url").toString();
						String date=new_obj.get("publication_dt").toString();
						String sql = "INSERT INTO BOOKREVIEW (TITLE, CRITIC, SUMMARY, URL, DATE, RATING, USER_ID) "+
							"VALUES ('" + 
								book_title + "', '" +
								critic + "', '" +
								summary + "', '" +
								urlA + "', '" +
								date + "', " +
								rating + ", " +										
								userId +											
								");";

							System.out.println("SQL stmt: "+sql);
							stmt.executeUpdate(sql);
					}
					
					System.out.println("End of Insert Data into Book");
				}
				//End Book
			}
			else
			{
				System.out.println("Not Appropriate Response");
			}	
			stmt.close();
			c.commit();
			c.close();
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}
	
	public static void readData(String table, String query)
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

			sql="SELECT " + query + " FROM " + table + ";" ;
			System.out.println("SQL: " + sql + "\n" + query + "\n");
			ResultSet rs = stmt.executeQuery(sql);
			
			int cnt=1;
			while ( rs.next() ) {
				String q = rs.getString(query);
				System.out.println("For Column " + cnt + ": " + "Value " + q);
				cnt++;
			}
			rs.close();

			stmt.close();
			c.commit();
			c.close();
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
	
	}
	
	public static void updateData(String table, String queryValue, String lhs, String rhs)
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

			sql = "UPDATE " + table + " SET RATING = " + queryValue + " WHERE " + lhs + " = " + rhs + ";" ;
			// UPDATE BOOKREVIEW set RATING = 88 where Date=ww2;
			// UPDATE COMPANY set SALARY = 25000.00 where ID=1;
			System.out.println("SQL: " + sql);
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}

	public static void deleteData(String table, String lhs, String rhs)
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

			sql = "DELETE FROM " + table + " WHERE " + lhs + " = " + rhs + ";" ;
			// UPDATE BOOKREVIEW set RATING = 88 where Date=ww2;
			// UPDATE COMPANY set SALARY = 25000.00 where ID=1;
			System.out.println("SQL: " + sql);
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
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
					fetchData(userId, Table, rating, Query);
					//break;
				}
				break;
				case 2:
				{
					System.out.println("Read From Table");
					String Table=reader.next();
					String Query=reader.next();
					System.out.println(Table+Query);
					readData(Table, Query);
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
					updateData(Table, QueryValue, LHS, RHS);
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
					deleteData(Table, LHS, RHS);
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
