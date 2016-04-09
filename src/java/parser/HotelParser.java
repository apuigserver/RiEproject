/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import models.vols.AgentHotel;
import models.vols.AgentPrice;
import models.vols.Amenity;
import models.vols.HotelPrice;
import models.vols.HotelResponse;
import models.vols.HotelReponseSearch;
import models.vols.Place;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author arnaudpuigserver
 */
public class HotelParser {
    
    public String jsonParserStatut(JSONObject jsonObject){
        return (String) jsonObject.get("status");
    }
    
    public HotelReponseSearch jsonParserHotelresponseSearch(JSONObject jsonObject){
        HotelReponseSearch hotelResponseSearch = new HotelReponseSearch();
        
        List<HotelResponse> hotelResponses = new ArrayList<>();
        JSONArray hotelResponsesArray = (JSONArray) jsonObject.get("hotels");
        Iterator i = hotelResponsesArray.iterator();
	while (i.hasNext()) {
            JSONObject hotelResponseObject = (JSONObject) i.next();
            HotelResponse hotelresponse = jsonParserHotelResponse(hotelResponseObject);
            hotelResponses.add(hotelresponse);
        }
        hotelResponseSearch.setHotelResponse(hotelResponses);
        
        Map<Long, HotelPrice> hotelPrices = new HashMap<>();
        JSONArray hotelPriceArray = (JSONArray) jsonObject.get("hotels_prices");
        i = hotelPriceArray.iterator();
	while (i.hasNext()) {
            JSONObject HotelPriceObject = (JSONObject) i.next();
            HotelPrice hotelPrice = jsonParserHotelPrices(HotelPriceObject);
            hotelPrices.put(hotelPrice.getId(), hotelPrice);
        }
        hotelResponseSearch.setHotelPrices(hotelPrices);
        
        Map<Long, AgentHotel> agentHotels = new HashMap<>();
        JSONArray agentHotelArray = (JSONArray) jsonObject.get("agents");
        i = agentHotelArray.iterator();
	while (i.hasNext()) {
            JSONObject agentHotelObject = (JSONObject) i.next();
            AgentHotel agantHotel = jsonParserAgent(agentHotelObject);
            agentHotels.put(agantHotel.getId(), agantHotel);
        }
        hotelResponseSearch.setAgents(agentHotels);
        
        Map<Long, Amenity> amenities = new HashMap<>();
        JSONArray amenityArray = (JSONArray) jsonObject.get("amenities");
        i = amenityArray.iterator();
	while (i.hasNext()) {
            JSONObject amenityObject = (JSONObject) i.next();
            Amenity amenity = jsonParserAmenity(amenityObject);
            amenities.put(amenity.getId(), amenity);
        }
        hotelResponseSearch.setAmenities(amenities);
        
        Map<Long, Place> places = new HashMap<>();
        JSONArray placesArray = (JSONArray) jsonObject.get("places");
        i = placesArray.iterator();
	while (i.hasNext()) {
            JSONObject placeObject = (JSONObject) i.next();
            Place place = jsonParserPlace(placeObject);
            places.put(place.getId(), place);
        }
        hotelResponseSearch.setPlaces(places);
        
        String status = (String) jsonObject.get("status");
        hotelResponseSearch.setStatus(status);
        
        long totalHotel = (long) jsonObject.get("total_hotels");
        hotelResponseSearch.setTotalHotels(totalHotel);
        
        long totalHotelAvailable = (long) jsonObject.get("total_available_hotels");
        hotelResponseSearch.setTotalHotalAvailable(totalHotelAvailable);
        
        long lastUpdate = (long) jsonObject.get("last_update");
        hotelResponseSearch.setLastUpdate(lastUpdate);
        
        String imageHostUrl = (String) jsonObject.get("image_host_url");
        hotelResponseSearch.setImageHostUrl(imageHostUrl);
        
        return hotelResponseSearch;
    }
    
    
    public HotelResponse jsonParserHotelResponse(JSONObject jsonObject){
        HotelResponse hotelResponse = new HotelResponse();
        
        String name = (String) jsonObject.get("name");
        hotelResponse.setName(name);
        
        long hotelId = (long) jsonObject.get("hotel_id");
        hotelResponse.setId(hotelId);
        
        long district = (long) jsonObject.get("district");
        hotelResponse.setDistrict(district);
        
        long nbRooms = (long) jsonObject.get("number_of_rooms");
        hotelResponse.setNbRooms(nbRooms);
        
        if(jsonObject.get("popularity") != null){
        long popularity = (long) jsonObject.get("popularity");
        hotelResponse.setPopularity(popularity);
        }
        
        if(jsonObject.get("popularity_desc") != null){
        String popularityDescription = (String) jsonObject.get("popularity_desc");
        hotelResponse.setPopularityDescription(popularityDescription);
        }
        
        JSONArray amenities= (JSONArray) jsonObject.get("amenities");
        long[] amenityList = new long[amenities.size()];
        for(int i=0; i<amenities.size(); i++){
            amenityList[i] = (long) amenities.get(i);
        }
        hotelResponse.setAmenities(amenityList);
        
        double latitude = (double) jsonObject.get("latitude");
        hotelResponse.setLatitude(latitude);
        
        double longitude = (double) jsonObject.get("longitude");
        hotelResponse.setLongitude(longitude);
        
        if(jsonObject.get("score") != null){
        long score = (long) jsonObject.get("score");
        hotelResponse.setScore(score);
        }
        
        if(jsonObject.get("tag") != null){
        String tag = (String) jsonObject.get("tag");
        hotelResponse.setTag(tag);
        }
        
        long starRating = (long) jsonObject.get("star_rating");
        hotelResponse.setStarRating(starRating);
        
        double distanceFromSearch = (double) jsonObject.get("distance_from_search");
        hotelResponse.setDistnaceFromSearch(distanceFromSearch);
        
        JSONArray imagesUrl= (JSONArray) jsonObject.get("image_urls");
        String[] imagesUrlsList = new String[imagesUrl.size()];
        for(int i=0; i<imagesUrl.size(); i++){
            imagesUrlsList[i] = (String) imagesUrl.get(i);
        }
        hotelResponse.setHotelImages(imagesUrlsList);
        
        return hotelResponse;
    }
    
    public Place jsonParserPlace(JSONObject jsonObject){
        Place place = new Place();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("Id");
        place.setId(id);
        // Alimentaiton de l'attribu ParentId
        if(jsonObject.get("ParentId") != null){
            long parentId = (long) jsonObject.get("ParentId");
            place.setParentId(parentId);
        }
        // Alimentation de l'attribu Code
        String code = (String) jsonObject.get("Code");
        place.setCode(code);
        // Alimentation de l'attribu Type
        String type = (String) jsonObject.get("Type");
        place.setType(type);
        // Alimentation de l'attribu Name
        String name = (String) jsonObject.get("Name");
        place.setName(name);
        
        return place;
    }
    
    public AgentHotel jsonParserAgent(JSONObject jsonObject){
        AgentHotel agent = new AgentHotel();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("id");
        agent.setId(id);
        // Alimentation de l'attribu Name
        String name = (String) jsonObject.get("name");
        agent.setName(name);
        // Alimentation de l'attribu ImageUrl
        String imageUrl = (String) jsonObject.get("image_url");
        agent.setImageUrl(imageUrl);
        // Alimentation de l'attribu Status
        Boolean inProgress = (Boolean) jsonObject.get("in_progress");
        agent.setInProgress(inProgress);
        
        return agent;
    }
    
    public Amenity jsonParserAmenity(JSONObject jsonObject){
        Amenity amenity = new Amenity();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("id");
        amenity.setId(id);
        // Alimentation de l'attribu Name
        String name = (String) jsonObject.get("name");
        amenity.setName(name);
        // Alimentation de l'attribu ImageUrl
        String key = (String) jsonObject.get("key");
        amenity.setKey(key);
        
        return amenity;
    }
    
    public HotelPrice jsonParserHotelPrices(JSONObject jsonObject){
        HotelPrice hotelPrice = new HotelPrice();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("id");
        hotelPrice.setId(id);
        
        JSONArray agentPricesArray = (JSONArray) jsonObject.get("agent_prices");
        List<AgentPrice> agentPrices = new ArrayList();
        Iterator i = agentPricesArray.iterator();
	while (i.hasNext()) {
            JSONObject agentPriceObject = (JSONObject) i.next();
            AgentPrice agentPrice = jsonParserAgentPrice(agentPriceObject);
            agentPrices.add(agentPrice);
        }
        hotelPrice.setAgentPrices(agentPrices);
        
        return hotelPrice;
    }
    
    public AgentPrice jsonParserAgentPrice(JSONObject jsonObject){
        AgentPrice agentPrice = new AgentPrice();
        
        long id = (long) jsonObject.get("id");
        agentPrice.setId(id);
        
        long price = (long) jsonObject.get("price_total");
        agentPrice.setPrice(price);

        return agentPrice;
    }
    
    public List<String> jsonParsonAutoSuggestHotel(JSONObject jsonObject){
        List<String> listIndividualId = new ArrayList<>();
        JSONArray hotelResponsesArray = (JSONArray) jsonObject.get("results");
        Iterator i = hotelResponsesArray.iterator();
	while (i.hasNext()) {
            JSONObject hotelResultsObject = (JSONObject) i.next();
            String individualId = jsonParserHotelResult(hotelResultsObject);
            if(individualId != null){
                listIndividualId.add(individualId);
            }
        }
        return listIndividualId;
    }
    
    public String jsonParserHotelResult(JSONObject jsonObject){
        String individualId = null;
        
        Long placeId = (Long) jsonObject.get("parent_place_id");
        if(placeId == 1L || placeId== 2L){
            individualId = (String) jsonObject.get("individual_id");
        }
        return individualId;
    }
    
}
