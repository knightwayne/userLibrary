package apiData.crudOperations;
//Libraries
import java.net.HttpURLConnection;
import java.net.URL;

// import java.io.File;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.SQLWarning;

//External JREs
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class createData {

    public static void createDataF(int userId, String table, int rating, String query)
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
    
}
