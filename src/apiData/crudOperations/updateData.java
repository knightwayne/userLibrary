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
        System.out.println("Delete From Table");
        int table=reader.nextInt();
        if(table==1)
        {
			String queryValue=reader.next();
            String lhs=reader.next();
            String rhs=reader.next();
            articleTdg TDG = new articleTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else if(table==2)
        {
			String queryValue=reader.next();
            String lhs=reader.next();
            String rhs=reader.next();
            movieTdg TDG= new movieTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else if(table==3)
        {
			String queryValue=reader.next();
            String lhs=reader.next();
            String rhs=reader.next();
            bookTdg TDG = new bookTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else if(table==4)
        {
			String queryValue=reader.next();
            String lhs=reader.next();
            String rhs=reader.next();
            userTdg TDG = new userTdg();
            TDG.update(queryValue,lhs,rhs);
        }
        else{
            System.out.println("Wrong Input");
            table=reader.nextInt();
        }
        reader.close();

		
	}

    
}
