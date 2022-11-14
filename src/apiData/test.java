package apiData;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class test {
	
	//To test the database inserts
	@Test
	public void createDisplayUpdateUser() {
    try(Connection c = DriverManager.getConnection("jdbc:sqlite:testDB.db");)
    {
        try(Statement stCheck= c.createStatement())
        {
            c.setAutoCommit(false);
            stCheck.executeUpdate("DELETE FROM userinfo");
            String name = "aaa";
            String address = "bbb";
            String email = "ccc@foo.com";
            String password = "password";
            String sql = "INSERT INTO USERINFO (NAME,EMAIL,PASSWORD,ADDRESS) "+
    	"VALUES ('" + 
    	name + "', '" +
    	email + "', '" +
    	password + "', '" +
    	address +	
    	"');";
    	stCheck.executeUpdate(sql);
            int id;

            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM USERINFO"))
            {
                assertTrue(rs.next());
                id=rs.getInt("id");
                assertEquals(name, rs.getString("name"));
                assertEquals(address, rs.getString("address")); 
                assertEquals(email, rs.getString("email")); 
                assertEquals(password, rs.getString("password")); 
 	            assertFalse(rs.next());
            }

           

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM USERINFO WHERE id="+id))
            {
                assertTrue(rs.next());
                assertEquals("password", rs.getString("password"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("UPDATE USERINFO SET PASSWORD = 'new' WHERE id = " + id + ";");
            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM USERINFO WHERE id="+id))
            {
                assertTrue(rs.next());
                assertEquals("new", rs.getString("password"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("DELETE FROM USERINFO WHERE id= " + id + ";");
            

            try(ResultSet r=stCheck.executeQuery("SELECT * FROM USERINFO WHERE id="+id))
            {
                assertFalse(r.next());
                

            }
          

        }
        finally
        {
             c.rollback();
        }
    }
    catch (SQLException e)
    {
        fail(e.toString());
    }
	}
	

	@Test
	public void createDisplayUpdateArticle() {
    try(Connection c = DriverManager.getConnection("jdbc:sqlite:testDB.db");)
    {
        try(Statement stCheck= c.createStatement())
        {
            c.setAutoCommit(false);
            stCheck.executeUpdate("DELETE FROM ARTICLE");
            // int articleId = 944545;
            String headline = "here is this";
            String abstractA = "abs go here";
            String urlA = "https://aaa";
            String date = "27/10/2000";
            int rating = 3;
            int userId = 123;
            

            String sql = "INSERT INTO ARTICLE (HEADLINE, ABSTRACT, URL, DATE, RATING, USER_ID) "+
	"VALUES ('" + 
	headline + "','" +
	abstractA + "','" +
	urlA + "','" +
	date + "'," +
	rating + "," +	
	userId +	
	");";
            System.out.println(sql);
    	stCheck.executeUpdate(sql);
            int id;

            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM ARTICLE"))
            {
                assertTrue(rs.next());
                id=rs.getInt("article_id");
                assertEquals(headline, rs.getString("headline"));
                assertEquals(abstractA, rs.getString("abstract")); 
                assertEquals(urlA, rs.getString("url")); 
                assertEquals(date, rs.getString("date")); 
                assertEquals(rating, rs.getInt("rating")); 
                assertEquals(userId, rs.getInt("user_id")); 
 	            assertFalse(rs.next());
            }

           

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM ARTICLE WHERE article_id="+id))
            {
                assertTrue(rs.next());
                assertEquals("here is this", rs.getString("headline"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("UPDATE ARTICLE SET headline = 'new headline' WHERE article_id = " + id + ";");
            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM ARTICLE WHERE article_id="+id))
            {
                assertTrue(rs.next());
                assertEquals("new headline", rs.getString("headline"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("DELETE FROM ARTICLE WHERE article_id= " + id + ";");
            

            try(ResultSet r=stCheck.executeQuery("SELECT * FROM ARTICLE WHERE article_id="+id))
            {
                assertFalse(r.next());
                

            }
        }
        finally
        {
             c.rollback();
        }
    }
    catch (SQLException e)
    {
        fail(e.toString());
    }
	}
	

	@Test
	public void createDisplayUpdateMovie() {
    try(Connection c = DriverManager.getConnection("jdbc:sqlite:testDB.db");)
    {
        try(Statement stCheck= c.createStatement())
        {
            c.setAutoCommit(false);
            stCheck.executeUpdate("DELETE FROM MOVIEREVIEW");
            // int movieId = 944545;
            String display_title = "Watch is this";
            String critic = "John Doe";
            String summary = "This happened";
            String urlA = "https://bbb";
            String date = "27/10/2002";
            int rating = 3;
            int userId = 123456;
            

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
    	stCheck.executeUpdate(sql);
            int id;

            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM MOVIEREVIEW"))
            {
                assertTrue(rs.next());
                id=rs.getInt("movie_id");
                assertEquals(display_title, rs.getString("title"));
                assertEquals(critic, rs.getString("critic")); 
                assertEquals(summary, rs.getString("summary"));
                assertEquals(urlA, rs.getString("url")); 
                assertEquals(date, rs.getString("date")); 
                assertEquals(rating, rs.getInt("rating")); 
                assertEquals(userId, rs.getInt("user_id")); 
 	            assertFalse(rs.next());
            }

           

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM MOVIEREVIEW WHERE movie_id="+id))
            {
                assertTrue(rs.next());
                assertEquals("Watch is this", rs.getString("title"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("UPDATE MOVIEREVIEW SET summary = 'new summary' WHERE movie_id = " + id + ";");
            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM MOVIEREVIEW WHERE movie_id="+id))
            {
                assertTrue(rs.next());
                assertEquals("new summary", rs.getString("summary"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("DELETE FROM MOVIEREVIEW WHERE movie_id= " + id + ";");
            

            try(ResultSet r=stCheck.executeQuery("SELECT * FROM MOVIEREVIEW WHERE movie_id="+id))
            {
                assertFalse(r.next());
                

            }
        }
        finally
        {
             c.rollback();
        }
    }
    catch (SQLException e)
    {
        fail(e.toString());
    }
	}
	

	@Test
	public void createDisplayUpdateBook() {
    try(Connection c = DriverManager.getConnection("jdbc:sqlite:testDB.db");)
    {
        try(Statement stCheck= c.createStatement())
        {
            c.setAutoCommit(false);
            stCheck.executeUpdate("DELETE FROM BOOKREVIEW");
            // int bookId = 944545;
            String book_title = "Read is this";
            String critic = "John Doe";
            String summary = "This happened";
            String urlA = "https://bbb";
            String date = "27/10/2002";
            int rating = 3;
            int userId = 123456;
            

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
    	stCheck.executeUpdate(sql);
            int id;

            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM BOOKREVIEW"))
            {
                assertTrue(rs.next());
                id=rs.getInt("book_id");
                assertEquals(book_title, rs.getString("title"));
                assertEquals(critic, rs.getString("critic")); 
                assertEquals(summary, rs.getString("summary"));
                assertEquals(urlA, rs.getString("url")); 
                assertEquals(date, rs.getString("date")); 
                assertEquals(rating, rs.getInt("rating")); 
                assertEquals(userId, rs.getInt("user_id")); 
 	            assertFalse(rs.next());
            }

           

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM BOOKREVIEW WHERE book_id="+id))
            {
                assertTrue(rs.next());
                assertEquals("Read is this", rs.getString("title"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("UPDATE BOOKREVIEW SET summary = 'new summary' WHERE book_id = " + id + ";");
            

            try(ResultSet rs=stCheck.executeQuery("SELECT * FROM BOOKREVIEW WHERE book_id="+id))
            {
                assertTrue(rs.next());
                assertEquals("new summary", rs.getString("summary"));
                assertFalse(rs.next());
            }
            

            stCheck.executeUpdate("DELETE FROM BOOKREVIEW WHERE book_id= " + id + ";");
            

            try(ResultSet r=stCheck.executeQuery("SELECT * FROM BOOKREVIEW WHERE book_id="+id))
            {
                assertFalse(r.next());
                

            }
        }
        finally
        {
             c.rollback();
        }
    }
    catch (SQLException e)
    {
        fail(e.toString());
    }
	}
	

	@Test
	public void articleAPIcallcheck()
	  throws IOException, ParseException {
	//to check if API is being called
	String headline = "Environment";
	String urlString="https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=headline:(\"" + headline+ "\")&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
	URL url = new URL(urlString);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();
	int responsecode = conn.getResponseCode();
	assertEquals(responsecode,200);
	

	//to check if return type is JSON
	String jsonType = "application/json";
	assertEquals(conn.getContentType().split(";")[0],jsonType);
	

	//check if the response is correct
	String inline = "";
	Scanner scanner = new Scanner(url.openStream());
	while (scanner.hasNext()) {
	inline += scanner.nextLine();
	}
	scanner.close();
	String parsed = inline.toString();
	JSONParser parser = new JSONParser();
	JSONObject data_obj = (JSONObject) parser.parse(parsed);
	JSONObject response_obj = (JSONObject) data_obj.get("response");
	JSONArray articleArr= (JSONArray) response_obj.get("docs");
	for (int i = 0; i < articleArr.size(); i++)
	{
	JSONObject new_obj = (JSONObject) articleArr.get(i);
	

	String article_check=new_obj.get("document_type").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
	assertEquals("article",article_check);
	}
	

	

	    

	}
	

	@Test
	public void movieAPIcallcheck()
	  throws IOException, ParseException {
	//to check if API is being called
	String movie = "Inception";
	String urlString="https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=" + movie+ "&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
	URL url = new URL(urlString);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();
	int responsecode = conn.getResponseCode();
	assertEquals(responsecode,200);
	

	//to check if return type is JSON
	String jsonType = "application/json";
	assertEquals(conn.getContentType().split(";")[0],jsonType);
	

	//check if the response is correct
	String inline = "";
	Scanner scanner = new Scanner(url.openStream());
	while (scanner.hasNext()) {
	inline += scanner.nextLine();
	}
	scanner.close();
	String parsed = inline.toString();
	JSONParser parser = new JSONParser();
	JSONObject data_obj = (JSONObject) parser.parse(parsed);
	JSONArray movieArr= (JSONArray) data_obj.get("results");
	for (int i = 0; i < movieArr.size(); i++)
	{
	JSONObject new_obj = (JSONObject) movieArr.get(i);
	

	String display_title=new_obj.get("display_title").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
	assertEquals(movie,display_title);
	}
	

	

	    

	}
	

	@Test
	public void bookAPIcallcheck()
	  throws IOException, ParseException {
	//to check if API is being called
	String book = "Becoming";
	String urlString="https://api.nytimes.com/svc/books/v3/reviews.json?title=" + book+ "&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
	URL url = new URL(urlString);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();
	int responsecode = conn.getResponseCode();
	assertEquals(responsecode,200);
	

	//to check if return type is JSON
	String jsonType = "application/json";
	assertEquals(conn.getContentType().split(";")[0],jsonType);
	

	//check if the response is correct
	String inline = "";
	Scanner scanner = new Scanner(url.openStream());
	while (scanner.hasNext()) {
	inline += scanner.nextLine();
	}
	scanner.close();
	String parsed = inline.toString();
	JSONParser parser = new JSONParser();
	

	

	

	

	JSONObject data_obj = (JSONObject) parser.parse(parsed);
	JSONArray bookArr= (JSONArray) data_obj.get("results");
	for (int i = 0; i < bookArr.size(); i++)
	{
	JSONObject new_obj = (JSONObject) bookArr.get(i);
	

	String book_title=new_obj.get("book_title").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
	assertEquals(book,book_title);
	}
	

	

	    

	}


}
