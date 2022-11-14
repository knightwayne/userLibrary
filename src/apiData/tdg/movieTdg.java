package apiData.tdg;
//import apiData.objects.*;

import apiData.fetchData.*;

// import java.util.Scanner;
// import java.net.HttpURLConnection;
// import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.SQLWarning;

//External JREs
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

public class movieTdg implements mediaInterface{

	public void displayTable()
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

    public void create(int userId, int rating, String query)
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

			pairClass recv = new pairClass();
            recv=fetchMovieAPI.getResponse(query);
            
            
            if (recv.responseCode == 200)
            {
                JSONArray movieArr=recv.mediaArray;
				System.out.println("Size of Array for Articles: "+movieArr.size());
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
			    //End Movie
			}
            else
            {
                System.out.println("HttpResponseCode: " + recv.responseCode);
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

    public void read(String query)
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

			sql="SELECT " + query + " FROM MOVIE;" ;
			System.out.println("SQL: " + sql + "\n" + query + "\n");
			if(query.equals("All"))
			{
				displayTable();
				return;
			}
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

    public void update(String queryValue, String lhs, String rhs)
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

			sql = "UPDATE MOVIE SET RATING = " + queryValue + " WHERE " + lhs + " = " + rhs + ";" ;
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

    public void delete(String lhs, String rhs)
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

			sql = "DELETE FROM MOVIEREVIEW WHERE " + lhs + " = '" + rhs + "';" ;
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
    
}
