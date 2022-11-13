package apiData.tdg;
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

public class bookTdg {

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
            recv=fetchBookAPI.getResponse(query);

            if (recv.responseCode == 200)
            {
                JSONArray bookArr=recv.mediaArray;
				System.out.println("Size of Array for Articles: "+bookArr.size());
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
                //End Book
			}
            else
            {
                throw new RuntimeException("HttpResponseCode: " + recv.responseCode);
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

			sql="SELECT " + query + " FROM BOOK;" ;
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

			sql = "UPDATE BOOK SET RATING = " + queryValue + " WHERE " + lhs + " = " + rhs + ";" ;
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

			sql = "DELETE FROM BOOK WHERE " + lhs + " = " + rhs + ";" ;
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
