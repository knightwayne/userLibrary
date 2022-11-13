package apiData.fetchData;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

//External JREs
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class fetchArticleAPI {

    public static pairClass getResponse(String query)
    {
        pairClass res = new pairClass();
        try
        {
            String urlString="https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=headline:(\"" + query+ "\")&api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38";
            System.out.println("API URL: "+ urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            
            res.responseCode = conn.getResponseCode();
            if(res.responseCode==200)
            {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                // System.out.println("API Fetched Data: "+inline.toString());
                
                String parsed = inline.toString();
                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(parsed);
                
                JSONObject articleList = (JSONObject) data_obj.get("response");
                JSONArray articleArr= (JSONArray) articleList.get("docs");

                res.mediaArray=articleArr;
                
            }
            return res;
        }
        catch (Exception e)
		{
            e.printStackTrace();
            return res;
        }
    }   
}
