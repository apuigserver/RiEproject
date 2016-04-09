/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.VolRequestPost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();
		
		System.out.println("\nTesting 1 - Send Http POST request to Creating the Session");
		HttpResponse response = http.sendPost();
                
                System.out.println("Testing 1 - Send Http GET request to Polling the Session");
		http.sendGet(response);

	}

	// HTTP GET request
	private void sendGet(HttpResponse responsePost) throws Exception {

		String[] urls = Arrays.toString(responsePost.getHeaders("location")).split(": ");
            int indexOf = urls[1].indexOf("]");
                String url = urls[1].substring(0, indexOf);
                url += "?apiKey=prtl6749387986743898559646983194";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	private HttpResponse sendPost() throws Exception {

		String url = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0?";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);
                VolRequestPost volrequestPost = new VolRequestPost("PARI", "LIS", "2016-10-01", "2016-10-04");
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair("apiKey", volrequestPost.getApiKey()));
		urlParameters.add(new BasicNameValuePair("country", volrequestPost.getCountry()));
		urlParameters.add(new BasicNameValuePair("currency", volrequestPost.getCurrency()));
		urlParameters.add(new BasicNameValuePair("locale", volrequestPost.getLocale()));
		urlParameters.add(new BasicNameValuePair("originplace", volrequestPost.getOriginplace()));
                urlParameters.add(new BasicNameValuePair("destinationplace", volrequestPost.getDestinationplace()));
                urlParameters.add(new BasicNameValuePair("outbounddate", volrequestPost.getOutbounddate()));
                urlParameters.add(new BasicNameValuePair("inbounddate", volrequestPost.getInbounddate()));
                urlParameters.add(new BasicNameValuePair("locationschema", volrequestPost.getLocationschema()));
                urlParameters.add(new BasicNameValuePair("cabinclass", volrequestPost.getCabinclass()));
                urlParameters.add(new BasicNameValuePair("adults", String.valueOf(volrequestPost.getAdults())));
                urlParameters.add(new BasicNameValuePair("groupPricing", String.valueOf(volrequestPost.isGroupPricing())));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());
                System.out.println(Arrays.toString(response.getHeaders("location")));

                return response;
	}
        
        public String creationPostRequest(String originStation, String destinationStation, String outboundDate, String inboundDate){
        
        Map parametres = new HashMap();
        
        VolRequestPost volrequestPost = new VolRequestPost(originStation, destinationStation, outboundDate, inboundDate);
        parametres.put("apiKey", volrequestPost.getApiKey());
        parametres.put("country", volrequestPost.getCountry());
        parametres.put("currency", volrequestPost.getCurrency());
        parametres.put("local", volrequestPost.getLocale());
        parametres.put("originplace", volrequestPost.getOriginplace());
        parametres.put("destinationplace", volrequestPost.getDestinationplace());
        parametres.put("outbounddate", volrequestPost.getOutbounddate());
        parametres.put("inbounddate", volrequestPost.getInbounddate());
        parametres.put("locationschema", volrequestPost.getLocationschema());
        parametres.put("cabinclass", volrequestPost.getCabinclass());
        parametres.put("adults", volrequestPost.getAdults());
        parametres.put("groupPricing", volrequestPost.isGroupPricing());
        
        StringBuilder requestBuilder = new StringBuilder();
        // Ajout des paramètres à la requete
        Iterator i = parametres.keySet().iterator();
        while (i.hasNext()) {
            requestBuilder.append("&");
            requestBuilder.append(i.toString());
            requestBuilder.append("=");
            requestBuilder.append(parametres.get(i));
        }
        
        return requestBuilder.toString();
    }

}
