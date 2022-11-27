package apiData.crudOperations;
import apiData.tdg.*;
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

public class readData {

    public static void readDataFunc()
	{
		Scanner reader = new Scanner(System.in);
        System.out.println("Read From Table");
        System.out.println("1.Article\t2.Movie Review\t3.Book Review\t4.UserInfo Table");
        int table=reader.nextInt();
        if(table==1)
        {
        	System.out.println("Enter the attribute whose value will be read. Enter All for the whole table");
            System.out.println("Enter the attribute name, Eg. DATE, RATING whose value will be read. Enter All for the whole table");
            System.out.println("0.All(*)\t1.ARTICLE_ID\t2.HEADLINE\t3.ABSTRACT\t4.URL\t5.DATE\t6.RATING\t7.USER_ID");
            //System.out.println("Enter multiple attribute names as Comma Separated Value, Eg. Date, Rating");
            reader.nextLine();
            String query=reader.nextLine();
            articleTdg TDG = new articleTdg();
            TDG.read(query);
        }
        else if(table==2)
        {
            System.out.println("Enter the attribute whose value will be read. Enter All for the whole table");
            System.out.println("Enter the attribute name, Eg. DATE, RATING whose value will be read. Enter All for the whole table");
            System.out.println("0.All\t1.MOVIE_ID\t2.TITLE\t3.CRITIC\t4.SUMMARY\t5.URL\t6.DATE\t7.RATING\t8.USER_ID");
            //System.out.println("Enter multiple attribute names as Comma Separated Value, Eg. Date, Rating");
            reader.nextLine();
            String query=reader.nextLine();
            movieTdg TDG= new movieTdg();
            TDG.read(query);
        }
        else if(table==3)
        {
            System.out.println("Enter the attribute whose value will be read. Enter All for the whole table");
            System.out.println("Enter the attribute name, Eg. DATE, RATING whose value will be read. Enter All for the whole table");
            System.out.println("0.All\t1.BOOK_ID\t2.TITLE\t3.CRITIC\t4.SUMMARY\t5.URL\t6.DATE\t7.RATING\t8.USER_ID");
            //System.out.println("Enter multiple attribute names as Comma Separated Value, Eg. Date, Rating");
            reader.nextLine();
            String query=reader.nextLine();
            bookTdg TDG = new bookTdg();
            TDG.read(query);
        }
        else if(table==4)
        {
            System.out.println("Enter the attribute whose value will be read. Enter All for the whole table");
            System.out.println("Enter the attribute name, Eg. EMAIL, NAME whose value will be read. Enter All for the whole table");
            System.out.println("0.All\t1.ID\t2.NAME\t3.EMAIL\t4.PASSWORD\t5.ADDRESS");
            //System.out.println("Enter multiple attribute names as Comma Separated Value, Eg. ADDRESS, EMAIL");
            reader.nextLine();
            String query=reader.nextLine();
            userTdg TDG = new userTdg();
            TDG.read(query);
        }
        else{
            System.out.println("Wrong Input");
            table=reader.nextInt();
        }
        // reader.close();
        
	}
	
    
}
