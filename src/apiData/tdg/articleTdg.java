package apiData.tdg;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.SQLWarning;

//External JREs
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class articleTdg {

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
                //End Article
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

			sql = "DELETE FROM ARTICLE WHERE " + lhs + " = " + rhs + ";" ;
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
