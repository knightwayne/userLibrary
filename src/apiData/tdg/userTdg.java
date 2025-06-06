package apiData.tdg;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class userTdg {

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

    public void create(String NAME,String EMAIL,String PASSWORD,String ADDRESS)
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

			sql="SELECT " + query + " FROM USERINFO;" ;
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

    public void update(String queryValue1, String queryValue2, String lhs, String rhs)
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

			sql = "UPDATE USERINFO SET " + queryValue1 + " = '" + queryValue2 + "' WHERE " + lhs + " = '" + rhs + "';" ;
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
			
			sql="SELECT ID FROM USERINFO WHERE "+ lhs + " = '" + rhs + "';" ;
			ResultSet rs = stmt.executeQuery(sql);
			int id=0;
			while ( rs.next() ) {
				id = rs.getInt("ID");
			}
			rs.close();
			String idStr = Integer.toString(id);
			
			sql = "DELETE FROM USERINFO WHERE " + lhs + " = '" + rhs + "';" ;		
			if(true)
			{
				articleTdg tdg1=new articleTdg();
				tdg1.delete(idStr,rhs);
				movieTdg tdg2=new movieTdg();
				tdg2.delete(idStr,rhs);
				bookTdg tdg3=new bookTdg();
				tdg3.delete(idStr,rhs);
			}
			
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
    
	public int returnUserId(String query)
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

			sql="SELECT " + query + " FROM USERINFO;" ;
			System.out.println("SQL: " + sql + "\n" + query + "\n");
			ResultSet rs = stmt.executeQuery(sql);
			
			int lastKey=0;
			while ( rs.next() ) {
				lastKey = rs.getInt("ID");
				//System.out.println("For Column " + cnt + ": " + "Value " + q);
			}
			rs.close();

			stmt.close();
			c.commit();
			c.close();
			return lastKey;
		}
		catch (Exception e)
		{
            e.printStackTrace();
			return 0;
        }
    }
}
