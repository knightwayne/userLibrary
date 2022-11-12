// package apiData;

// import static org.junit.Assert.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;


// import java.io.IOException;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.Scanner;

// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;
// import org.junit.jupiter.api.Test;

// class ApiTest {
// 	//To test the database inserts
// 	@Test
// 	public void createUser() {
//     try(Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/revabalasundaram/documents/Project/testDB.db");)
//     {
//         try(Statement stCheck= c.createStatement())
//         {
//             c.setAutoCommit(false);
//             stCheck.executeUpdate("DELETE FROM userinfo");
//             String name = "aaa";
//             String address = "bbb";
//             String email = "ccc@foo.com";
//             String password = "password";
//             String sql = "INSERT INTO USERINFO (NAME,EMAIL,PASSWORD,ADDRESS) "+
//     				"VALUES ('" + 
//     				name + "', '" +
//     				email + "', '" +
//     				password + "', '" +
//     				address +										
//     				"');";
//     			stCheck.executeUpdate(sql);
//             int id;

//             // Check the Person table contains one row with the expected values:
//             try(ResultSet rs=stCheck.executeQuery("SELECT * FROM USERINFO"))
//             {
//                 assertTrue(rs.next());
//                 id=rs.getInt("id");
//                 assertEquals(name, rs.getString("name"));
//                 assertEquals(address, rs.getString("address"));
                
// //               System.out.println( "ID = " + id );
// // 	           System.out.println( "NAME = " + name );
// // 	           System.out.println( "EMAIL = " + email );
// // 	           System.out.println( "PASSWORD = " + password );
// // 	           System.out.println( "ADDRESS = " + address );
// // 	           System.out.println();
 	           
//  	          assertFalse(rs.next());
//             }

//             // Check the Contractor table contains one row with the expected values:
//             try(ResultSet rs=stCheck.executeQuery("SELECT * FROM USERINFO WHERE id="+id))
//             {
//                 assertTrue(rs.next());
//                 assertEquals("password", rs.getString("password"));
//                 assertFalse(rs.next());
//             }
//         }
//         finally
//         {
//              c.rollback();
//         }
//     }
//     catch (SQLException e)
//     {
//         fail(e.toString());
//     }
// 	}
	
// 	@Test
// 	public void APIcallcheck()
// 	  throws IOException, ParseException {
// 		//to check if API is being called
// 		String movie = "Inception";
// 		String urlString="https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=" + movie+ "&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
// 		URL url = new URL(urlString);
// 		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 		conn.setRequestMethod("GET");
// 		conn.connect();
// 		int responsecode = conn.getResponseCode();
// 		assertEquals(responsecode,200);
		
// 		//to check if return type is JSON
// 		String jsonType = "application/json";
// 		assertEquals(conn.getContentType().split(";")[0],jsonType);
		
// 		//check if the response is correct
// 		String inline = "";
// 		Scanner scanner = new Scanner(url.openStream());
// 		while (scanner.hasNext()) {
// 			inline += scanner.nextLine();
// 		}
// 		scanner.close();
// 		String parsed = inline.toString();
// 		JSONParser parser = new JSONParser();
// 		JSONObject data_obj = (JSONObject) parser.parse(parsed);
// 		JSONArray movieArr= (JSONArray) data_obj.get("results");
// 		for (int i = 0; i < movieArr.size(); i++)
// 		{
// 			JSONObject new_obj = (JSONObject) movieArr.get(i);
			
// 			String display_title=new_obj.get("display_title").toString().replaceAll("[^a-zA-Z0-9\\s+]", "");
// 			assertEquals(movie,display_title);
// 		}
		
		
	    
// 	}
// }
