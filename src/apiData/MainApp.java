package apiData;
import apiData.crudOperations.*;
//Libraries
// import java.net.HttpURLConnection;
// import java.net.URL;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// import java.sql.Connection;
// import java.sql.Statement;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

//External JREs
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

public class MainApp{
	public static void main(String[] args) throws IOException, SQLException, SQLWarning
	{
		try
		{
			initDB.initDatabase();

			int input = 0;
			Scanner reader = new Scanner(System.in);
			do{
				System.out.println("------------------------------------");
				System.out.println("Enter your Choice Input as a Number");
				System.out.println("1.Create new entry in table after fetching data from NewYorkTimes API.\n2.Read from Table\t3.Update from Table\t4.Delete from Table\t5.Exit Switch Case & Programme Termination");
				System.out.println("------------------------------------");
				input = reader.nextInt();
				System.out.println(input);
				
				switch (input) {
				// case 0:
				// {
				// 	System.out.println("Display All Tables");
				// 	//displayTables();
				// }
				// break;
				case 1:
				{
					System.out.println("Create a New Entry in Table");
					createData.createDataFunc();
				}
				break;
				case 2:
				{
					System.out.println("Read From Table");
					readData.readDataFunc();
				}
				break;
				case 3:
				{
					System.out.println("Update From Table");
					updateData.updateDataFunc();
				}
				break;
				case 4:
				{
					System.out.println("Delete From Table");
					deleteData.deleteDataFunc();
				}
				break;
				case 5:
				{
					System.out.println("Exit Programme");
					break;
				}
				//break;
				default:
					System.out.println("Incorrect Input. Please enter a Number between 0 to 6.");
				}
				
			} while (!(input==5));
			
			reader.close();
			System.out.println("Program Exiting");
			
			//Deleting Local Database
			String localDir = System.getProperty("user.dir");
			System.out.println(localDir);
			File f= new File(localDir+"\\testDB.db");    
			if(f.delete())                    
			{  
				System.out.println(f.getName() + " deleted");
			}  
			else  
			{  
				System.out.println("failed");  
			}  
			
			return;
		}
		catch(Exception e)
		{
			String localDir = System.getProperty("user.dir");
			System.out.println(localDir);
			File f= new File(localDir+"\\testDB.db");
			if(f.delete())
			{  
				System.out.println(f.getName() + "- File Deleted");
			}  
			else  
			{  
				System.out.println("Failed to Delete File");  
			}  
		}
}
}
