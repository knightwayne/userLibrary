package apiData.crudOperations;
import apiData.tdg.*;
import apiData.objects.*;

//Libraries
// import java.net.HttpURLConnection;
// import java.net.URL;

// import java.io.File;
import java.util.Scanner;

// import java.sql.Connection;
// import java.sql.Statement;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.SQLWarning;

//External JREs
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

public class createData {

    public static void createDataFunc()
	{
		Scanner reader = new Scanner(System.in);
        System.out.println("Create From Table");
        System.out.println("1.Article\t2.Movie Review\t3.Book Review\t4.UserInfo Table");
        int table=reader.nextInt();
        if(table==1)
        {
            System.out.println("Enter User Id");
			int userId=reader.nextInt();
            System.out.println("Enter Article Query String");
            reader.nextLine();
            String query=reader.nextLine();
            System.out.println("Enter Rating for Article");
			int rating=reader.nextInt();
            articleTdg TDG = new articleTdg();
            TDG.create(userId, rating, query);
        }
        else if(table==2)
        {
            System.out.println("Enter User Id");
			int userId=reader.nextInt();
            System.out.println("Enter Movie Query String");
            reader.nextLine();
            String query=reader.nextLine();
            System.out.println("Enter Rating for Movie");
			int rating=reader.nextInt();
            movieTdg TDG= new movieTdg();
            TDG.create(userId, rating, query);
        }
        else if(table==3)
        {
            System.out.println("Enter User Id");
			int userId=reader.nextInt();
            System.out.println("Enter Book Query String");
            reader.nextLine();
            String query=reader.nextLine();
            System.out.println("Enter Rating for Book");
			int rating=reader.nextInt();
            bookTdg TDG = new bookTdg();
            TDG.create(userId, rating, query);
        }
        else if(table==4)
        {
            System.out.println("Enter User Name without space");
            reader.nextLine();
            String NAME=reader.nextLine();
            System.out.println("Enter User Email as a single string");
            reader.nextLine();
            String EMAIL=reader.nextLine();
            System.out.println("Enter User Password as a single string");
            reader.nextLine();
            String PASSWORD=reader.nextLine();
            System.out.println("Enter User Address without space");
            reader.nextLine();
            String ADDRESS=reader.nextLine();
            userTdg TDG = new userTdg();
            TDG.create(NAME, EMAIL, PASSWORD, ADDRESS);

            User user = new User();
			user.setName(NAME); user.setEmail(EMAIL);
			user.setPassword(PASSWORD); user.setAddress(ADDRESS);
			user.setId(TDG.returnUserId("ID"));

        }
        else{
            System.out.println("Wrong Input");
            table=reader.nextInt();
        }
        // reader.close();
	
	}
    
}
