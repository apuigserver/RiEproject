/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.vols.HotelReponseSearch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author arnaudpuigserver
 */
public class HotelParserTest {
    
    private static final HotelParser hotelParser = new HotelParser();

    @Test
    public void testJsonParserHotelReponseSearch() throws ParseException, FileNotFoundException, IOException {
        String filePath = "/Users/arnaudpuigserver/NetBeansProjects/RiE/test/parser/LONDRES-HOTEL.json";
        // read the json file
        FileReader reader = new FileReader(filePath);
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        // Appel de la fonction pour enregister dans l'objet
        HotelReponseSearch hotelReponseSearch = hotelParser.jsonParserHotelresponseSearch(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le nombre d'HotelResponse est incorrect",10, hotelReponseSearch.getHotelResponse().size());
        Assert.assertEquals("Le nombre de HotelPrices est incorrect",10, hotelReponseSearch.getHotelPrices().size());
        Assert.assertEquals("Le nombre de Amenities est incorrect",65, hotelReponseSearch.getAmenities().size());
        Assert.assertEquals("Le nombre d'Agents est incorrect",49, hotelReponseSearch.getAgents().size());
        Assert.assertEquals("Le nombre de Place est incorrect",0, hotelReponseSearch.getPlaces().size());
        Assert.assertEquals("Le statut est incorrect","PENDING", hotelReponseSearch.getStatus());
        Assert.assertEquals("Le nombre total d'hotel disponible est incorrect",612, hotelReponseSearch.getTotalHotalAvailable());
        
    }
     
}

