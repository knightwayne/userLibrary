package apiData;

import java.io.BufferedInputStream;
// import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
// import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class testa {
	public static void MyGETRequest() throws IOException, JSONException {
		//Article Search API
		//"https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=yourkey"
		//Movie Review API
		//"https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=godfather&api-key=yourkey"
		//Book Review API
		//"https://api.nytimes.com/svc/books/v3/reviews.json?author=Stephen+King&api-key=yourkey"
		
	    URL urlForGetRequest = new URL("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=4sPrzYTdpJ9MHG1S6SG57GYYCZOcBV38");
	    // String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    conection.setRequestProperty("userId", "usernae1"); // set userId its a sample here
	    int responseCode = conection.getResponseCode();

	    

	    if (responseCode == HttpURLConnection.HTTP_OK) {
//	        BufferedReader in = new BufferedReader(
//	            new InputStreamReader(conection.getInputStream()));
//	        StringBuffer response = new StringBuffer();
//	        while ((readLine = in .readLine()) != null) {
//	            response.append(readLine);
//	        } in .close();
	    	InputStream in = new BufferedInputStream(conection.getInputStream());
	           String response = IOUtils.toString(in, "UTF-8");
	           System.out.println(response.toString());
	           System.out.println("result after Reading JSON Response");
	           JSONObject myResponse = new JSONObject(response);
//	           System.out.println("jsonrpc- "+myResponse.getString("jsonrpc"));
//	           System.out.println("id- "+myResponse.getInt("id"));
	           System.out.println("result- "+myResponse.getJSONObject("results").getJSONArray("books").getJSONObject(0).getString("title"));
	           

	           in.close();
	           conection.disconnect();
	        // print result
	        //JSONObject myResponse = new JSONObject(response);
	        //System.out.println("JSON String Result " + myResponse.getString("results"));
	        //System.out.println("JSON String Result " + response.toString());
	        //GetAndPost.POSTRequest(response.toString());
	    } else {
	        System.out.println("GET NOT WORKED");
	    }

	}
	

	public static void main(String[] args) throws IOException, JSONException {
		testa.MyGETRequest();
	}
}