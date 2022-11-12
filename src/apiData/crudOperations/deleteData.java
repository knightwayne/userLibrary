package apiData.crudOperations;
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

//External JREs
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

public class deleteData {
    
    public static void deleteDataF(String table, String lhs, String rhs)
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
	
}
