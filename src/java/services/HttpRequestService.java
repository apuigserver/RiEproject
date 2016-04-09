/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import models.Constant;
import models.VolRequestPost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name = "httpRequestService")
@ApplicationScoped
public class HttpRequestService {

    private final String USER_AGENT = "Mozilla/5.0";

    private static final String baseVolRequest = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0?";
    private static final String baseHotelAutoSuggestRequest = "http://partners.api.skyscanner.net/apiservices/hotels/autosuggest/v2/";
    private static final String baseHotelPriceRequest = "http://partners.api.skyscanner.net/apiservices/hotels/liveprices/v2/";

    public HttpResponse sendPostVolRequest(String originStation, String destinationStation, String outboundDate, String inboundDate) throws UnsupportedEncodingException, IOException {
        String url = baseVolRequest;

        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        List<NameValuePair> urlParameters = volUrlParameters(originStation, destinationStation, outboundDate, inboundDate);
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        return response;
    }

    public List<NameValuePair> volUrlParameters(String originStation, String destinationStation, String outboundDate, String inboundDate) {
        VolRequestPost volrequestPost = new VolRequestPost(originStation, destinationStation, outboundDate, inboundDate);
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

        return urlParameters;
    }

    public HttpResponse sendGetRequestAfterPostRequest(HttpResponse responsePost) throws Exception {

        String[] urls = Arrays.toString(responsePost.getHeaders("location")).split(": ");
        int indexOf = urls[1].indexOf("]");
        String url = urls[1].substring(0, indexOf);
        url += "?apiKey=";
        url += Constant.API_KEY;

        HttpGet get = new HttpGet(url);

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        return response;
    }
    
    public HttpResponse sendGetRequestPriceHotel(HttpResponse getResponse) throws IOException{
        String[] urls = Arrays.toString(getResponse.getHeaders("location")).split(": ");
        int indexOf = urls[1].indexOf("]");
        String url = urls[1].substring(0, indexOf);
        url = "http://partners.api.skyscanner.net" + url;
        HttpGet get = new HttpGet(url);

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        return response;
    }

    public HttpResponse sendGetRequestAfterGetRequest(HttpResponse responseGet) throws Exception {

        String[] urls = Arrays.toString(responseGet.getHeaders("location")).split(": ");
        int indexOf = urls[1].indexOf("]");
        String url = urls[1].substring(0, indexOf);

        HttpGet get = new HttpGet(url);

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        return response;
    }

    public HttpResponse sendGetAutoSugestHotelRequest(String destination) throws IOException {
    
        String addRequest = "/"; 
        String url = baseHotelAutoSuggestRequest + addRequest + Constant.MARKET 
                + addRequest + Constant.CURENCY + addRequest 
                + Constant.LOCALE + addRequest + destination
                + "?apikey=" + Constant.API_KEY;
        
        HttpGet get = new HttpGet(url);

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        return response;
    }
    
    public HttpResponse sendGetHotelPriceRequest(String entityId, String outboundDate, String inboundDate ) throws IOException {
    
        String addRequest = "/"; 
        String url = baseHotelPriceRequest 
                + addRequest + Constant.MARKET 
                + addRequest + Constant.CURENCY 
                + addRequest + Constant.LOCALE 
                + addRequest + entityId
                + addRequest + outboundDate
                + addRequest + inboundDate
                + addRequest + Constant.GUEST
                + addRequest + Constant.ROOMS
                + "?apikey=" + Constant.API_KEY;
        
        HttpGet get = new HttpGet(url);

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        return response;
    }
    
}
