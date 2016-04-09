/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import models.Hotel;
import models.Prix;
import models.vols.AgentHotel;
import models.vols.Amenity;
import models.vols.HotelPrice;
import models.vols.HotelReponseSearch;
import models.vols.HotelResponse;
import models.vols.Place;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.HotelParser;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name = "hotelService", eager = true)
@ApplicationScoped
public class HotelService {

    private List<Hotel> hotels;

    private final HttpRequestService httpRequestService = new HttpRequestService();

    /**
     * Creates a new instance of HotelService
     */
    public HotelService() {
    }

    public List<Hotel> reseachHotel(String destination, String dateDepart, String dateRetour) throws IOException, Exception {
        List<Hotel> listHotels = new ArrayList<>();
        HotelParser hotelParser = new HotelParser();
        JSONParser jsonParser = new JSONParser();
        HttpResponse httpResponseAutoSuggestRequest = httpRequestService.sendGetAutoSugestHotelRequest(destination);
        if (httpResponseAutoSuggestRequest.getStatusLine().getStatusCode() == 200) {
            String responseJson = EntityUtils.toString(httpResponseAutoSuggestRequest.getEntity());
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseJson);
            List<String> listeIdHotels = hotelParser.jsonParsonAutoSuggestHotel(jsonObject);
            HttpResponse httpResponsePrice = httpRequestService.sendGetHotelPriceRequest(listeIdHotels.get(0), dateDepart, dateRetour);
            if (httpResponsePrice.getStatusLine().getStatusCode() == 200) {
                HttpResponse getResponse = httpRequestService.sendGetRequestPriceHotel(httpResponsePrice);
                responseJson = EntityUtils.toString(getResponse.getEntity());
                jsonObject = (JSONObject) jsonParser.parse(responseJson);
                String statutRechercheHotel = hotelParser.jsonParserStatut(jsonObject);
                while (!"COMPLETE".equals(statutRechercheHotel)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    getResponse = httpRequestService.sendGetRequestPriceHotel(getResponse);
                    if (getResponse.getStatusLine().getStatusCode() == 200) {
                        jsonObject = (JSONObject) jsonParser.parse(responseJson);
                        statutRechercheHotel = hotelParser.jsonParserStatut(jsonObject);
                    } else {
                        break;
                    }
                }
                // Appel de la fonction pour enregister dans l'objet

                HotelReponseSearch hotelResponseSearch = hotelParser.jsonParserHotelresponseSearch(jsonObject);
                listHotels = creationHotels(hotelResponseSearch);

            }
        }
        return listHotels;
    }

    public List<Hotel> creationHotels(HotelReponseSearch hotelReponseSearch) throws IOException, FileNotFoundException, ParseException {
        hotels = new ArrayList<>();

        Map<Long, HotelPrice> hotelPrices = hotelReponseSearch.getHotelPrices();
        Map<Long, Place> places = hotelReponseSearch.getPlaces();
        Map<Long, Amenity> amenities = hotelReponseSearch.getAmenities();
        Map<Long, AgentHotel> agents = hotelReponseSearch.getAgents();

        for (HotelResponse hotelResponse : hotelReponseSearch.getHotelResponse()) {
            Hotel hotel = new Hotel();
            // prix de l'hotel
            HotelPrice hotelPrice = hotelPrices.get(hotelResponse.getId());
            long prix = hotelPrice.getAgentPrices().get(0).getPrice();
            String prixString = String.valueOf(prix);
            int indexPoint = prixString.indexOf('.');
            if (indexPoint == -1) {
                hotel.setPrix(new Prix(prix, prixString, "00"));
            } else {
                hotel.setPrix(new Prix(prix, prixString.substring(0, indexPoint), prixString.substring(indexPoint + 1)));
            }
            // distance
            hotel.setDistance(hotelResponse.getDistnaceFromSearch());
            // longitude
            hotel.setLongitude(hotelResponse.getLongitude());
            // latitude
            hotel.setLatitude(hotelResponse.getLatitude());
            // Nom
            hotel.setNom(hotelResponse.getName());
            // Popularité
            hotel.setPopularite("" + String.valueOf(hotelResponse.getPopularity()).substring(0, 1) + "." + String.valueOf(hotelResponse.getPopularity()).substring(1, 2) + "/10 " + hotelResponse.getPopularityDescription());
            // services
            for (Long amenityId : hotelResponse.getAmenities()) {
                if (amenities.get(amenityId).getName().equalsIgnoreCase("Wi-Fi")) {
                    hotel.setWifiService(true);
                } else if (amenities.get(amenityId).getName().equalsIgnoreCase("Parking")) {
                    hotel.setParkingService(true);
                }
            }
            // note
            hotel.setNoteEtoile(hotelResponse.getStarRating());
            hotels.add(hotel);

        }

        return hotels;
    }

    public HotelReponseSearch lectureEtParsingFichierHotel() throws FileNotFoundException, IOException, ParseException {
        String filePath = "/Users/arnaudpuigserver/NetBeansProjects/RiE/test/parser/LONDRES-HOTEL.json";
        // read the json file
        FileReader reader = new FileReader(filePath);
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        // Appel de la fonction pour enregister dans l'objet
        HotelParser hotelParser = new HotelParser();
        return hotelParser.jsonParserHotelresponseSearch(jsonObject);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

}
