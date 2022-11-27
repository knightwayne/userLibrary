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

public class updateData {

    public static void updateDataFunc()
	{
		Scanner reader = new Scanner(System.in);
        System.out.println("Update From Table");
        System.out.println("1.Article\t2.Movie Review\t3.Book Review\t4.UserInfo Table");
        int table=reader.nextInt();
        if(table==1)
        {
        	System.out.println("Media Rating would be updated. Input Params as per Command Line");
            
            System.out.println("Update condition requires 2 values, a column name & a data value, and it updates rating for values, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("0.All(*)\t1.ARTICLE_ID\t2.HEADLINE\t3.ABSTRACT\t4.URL\t5.DATE\t6.RATING\t7.USER_ID");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            System.out.println("Enter the updated rating for article");
			String queryValue=reader.next();
            articleTdg TDG = new articleTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else if(table==2)
        {
        	System.out.println("Media Rating would be updated. Input Params as per Command Line");
           
            System.out.println("Update condition requires 2 values, a column name & a data value, and it updates rating for values, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("0.All\t1.MOVIE_ID\t2.TITLE\t3.CRITIC\t4.SUMMARY\t5.URL\t6.DATE\t7.RATING\t8.USER_ID");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            System.out.println("Enter the updated rating for movie review");
			String queryValue=reader.next();
            movieTdg TDG= new movieTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else if(table==3)
        {
        	System.out.println("Media Rating would be updated. Input Params as per Command Line");
            
            System.out.println("Update condition requires 2 values, a column name & a data value, and it updates rating for values, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("0.All\t1.BOOK_ID\t2.TITLE\t3.CRITIC\t4.SUMMARY\t5.URL\t6.DATE\t7.RATING\t8.USER_ID");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            System.out.println("Enter the updated rating for book review");
			String queryValue=reader.next();
            bookTdg TDG = new bookTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else if(table==4)
        {
        	System.out.println("User Info would be updated. Input Params as per Command Line");
            
            System.out.println("Update condition requires 2 values, a column name & a data value, and it updates rating for values, whose data value matches the specific column attribute");
            System.out.println("Enter the column name/attribute(lhs of condition: where lhs=rhs)");
            System.out.println("0.All\t1.NAME\t2.EMAIL\t3.PASSWORD\t4.ADDRESS");
            String lhs=reader.next();
            System.out.println("Enter the Data Value(rhs of condition: where lhs=rhs)");
            String rhs=reader.next();
            System.out.println("Enter the column name whose value will be updated");
            System.out.println("0.All\t1.NAME\t2.EMAIL\t3.PASSWORD\t4.ADDRESS");
            String queryValue1=reader.next();
            System.out.println("Enter the updated value for above column");
			String queryValue2=reader.next();
            userTdg TDG = new userTdg();
            TDG.update(queryValue1,queryValue2,lhs,rhs);
        }
        else{
            System.out.println("Wrong Input");
            table=reader.nextInt();
        }
        // reader.close();

	}

    
}
