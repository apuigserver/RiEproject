/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import models.VolRequestPost;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name="volService")
@ApplicationScoped
public class VolRequestPostService {
    
    private static final String baseRequest = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0?";
    private static final Character AJOUT_PARAMETRE = '&';
    private static final Character VALEUR_PARAMETRE = '=';
    
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
        //initialisation de la requette
        requestBuilder.append(baseRequest);
        // Ajout des paramètres à la requete
        Iterator i = parametres.keySet().iterator();
        while (i.hasNext()) {
            requestBuilder.append(AJOUT_PARAMETRE);
            requestBuilder.append(i.toString());
            requestBuilder.append(VALEUR_PARAMETRE);
            requestBuilder.append(parametres.get(i));
        }
        
        return requestBuilder.toString();
    }
}
