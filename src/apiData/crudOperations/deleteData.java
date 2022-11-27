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

public class deleteData {
    
    public static void deleteDataFunc()
	{
        Scanner reader = new Scanner(System.in);
        System.out.println("Delete From Table");
        System.out.println("1.Article\t2.Movie Review\t3.Book Review\t4.UserInfo Table");
        int table=reader.nextInt();
        if(table==1)
        {
            System.out.println("Delete condition requires 2 values, a column name & a data value, and it deletes rows, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("1.ARTICLE_ID\t2.HEADLINE\t3.ABSTRACT\t4.URL\t5.DATE\t6.RATING\t7.USER_ID");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            articleTdg TDG = new articleTdg();
            TDG.delete(lhs,rhs);
        }
        else if(table==2)
        {
            System.out.println("Delete condition requires 2 values, a column name & a data value, and it deletes rows, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("1.MOVIE_ID\t2.TITLE\t3.CRITIC\t4.SUMMARY\t5.URL\t6.DATE\t7.RATING\t8.USER_ID");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            movieTdg TDG= new movieTdg();
            TDG.delete(lhs,rhs);
        }
        else if(table==3)
        {
            System.out.println("Delete condition requires 2 values, a column name & a data value, and it deletes rows, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("1.BOOK_ID\t2.TITLE\t3.CRITIC\t4.SUMMARY\t5.URL\t6.DATE\t7.RATING\t8.USER_ID");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            bookTdg TDG = new bookTdg();
            TDG.delete(lhs,rhs);
        }
        else if(table==4)
        {
            System.out.println("Delete condition requires 2 values, a column name & a data value, and it deletes rows, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("1.ID\t2.NAME\t3.EMAIL\t4.PASSWORD\t5.ADDRESS");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            userTdg TDG = new userTdg();
            TDG.delete(lhs,rhs);
        }
        else{
            System.out.println("Wrong Input");
            table=reader.nextInt();
        }
        // reader.close();
        
	}
	
}
