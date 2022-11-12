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
        System.out.println("Delete From Table");
        int table=reader.nextInt();
        if(table==1)
        {
            String query=reader.next();
            articleTdg TDG = new articleTdg();
            TDG.read(query);
        }
        else if(table==2)
        {
            String query=reader.next();
            movieTdg TDG= new movieTdg();
            TDG.read(query);
        }
        else if(table==3)
        {
            String query=reader.next();
            bookTdg TDG = new bookTdg();
            TDG.read(query);
        }
        else if(table==4)
        {
            String query=reader.next();
            userTdg TDG = new userTdg();
            TDG.read(query);
        }
        else{
            System.out.println("Wrong Input");
            table=reader.nextInt();
        }
        reader.close();

		
	
	}
	
    
}
