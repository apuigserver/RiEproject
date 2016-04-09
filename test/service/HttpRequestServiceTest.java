/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import services.HttpRequestService;

/**
 *
 * @author arnaudpuigserver
 */
public class HttpRequestServiceTest {
    
    private static final HttpRequestService httpRequestService = new HttpRequestService();
    

    @Test
    public void testConnectionApiVol() throws IOException, Exception {
        String originStation = "PARI";
        String destinationStation = "LIS";
        String outboundDate = "2016-08-22";
        String inboundDate = "2016-08-30";
       HttpResponse postResponse = httpRequestService.sendPostVolRequest(originStation, destinationStation, outboundDate, inboundDate);
       Assert.assertEquals("Le statut code est incorrect", 201, postResponse.getStatusLine().getStatusCode());
       if(postResponse.getStatusLine().getStatusCode() == 201){
           HttpResponse getResponse= httpRequestService.sendGetRequestAfterPostRequest(postResponse);
           Assert.assertEquals("Le statut code est incorrect", 200, getResponse.getStatusLine().getStatusCode());
           
       }
    }
     
}

