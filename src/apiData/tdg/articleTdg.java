package apiData.tdg;
import apiData.objects.*;

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

public class articleTdg implements mediaInterface{

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
            recv=fetchArticleAPI.getResponse(query);
            

            if (recv.responseCode == 200)
            {
                JSONArray articleArr=recv.mediaArray;
                System.out.println("Size of Array for Articles: "+articleArr.size());
                for (int i = 0; i < articleArr.size(); i++)
                {
                    System.out.println("Loop Iter: " + i);
                    JSONObject new_obj = (JSONObject) articleArr.get(i);
                    //System.out.println(new_obj.toString());
                    JSONObject r = (JSONObject) new_obj.get("headline");
                    String headline=r.get("main").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
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
                //End Article
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

			sql="SELECT " + query + " FROM ARTICLE;" ;
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

			sql = "UPDATE ARTICLE SET RATING = " + queryValue + " WHERE " + lhs + " = " + rhs + ";" ;
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

			sql = "DELETE FROM ARTICLE WHERE " + lhs + " = '" + rhs + "';" ;
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
