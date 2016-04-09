/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.vols;

import java.util.List;
import java.util.Map;

/**
 *
 * @author arnaudpuigserver
 */
public class HotelReponseSearch {
    
    private List<HotelResponse> hotelResponse;
    private Map<Long, HotelPrice> hotelPrices;
    private Map<Long, Place> places;
    private Map<Long, Amenity> amenities;
    private Map<Long, AgentHotel> agents;
    private String[] urls;
    private String status;
    private long totalHotels;
    private long totalHotalAvailable;
    private long lastUpdate;
    private String imageHostUrl;

    public List<HotelResponse> getHotelResponse() {
        return hotelResponse;
    }

    public void setHotelResponse(List<HotelResponse> hotelResponse) {
        this.hotelResponse = hotelResponse;
    }

    public Map<Long, HotelPrice> getHotelPrices() {
        return hotelPrices;
    }

    public void setHotelPrices(Map<Long, HotelPrice> hotelPrices) {
        this.hotelPrices = hotelPrices;
    }

    public Map<Long, Place> getPlaces() {
        return places;
    }

    public void setPlaces(Map<Long, Place> places) {
        this.places = places;
    }

    public Map<Long, Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Map<Long, Amenity> amenities) {
        this.amenities = amenities;
    }

    public Map<Long, AgentHotel> getAgents() {
        return agents;
    }

    public void setAgents(Map<Long, AgentHotel> agents) {
        this.agents = agents;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotalHotels() {
        return totalHotels;
    }

    public void setTotalHotels(long totalHotels) {
        this.totalHotels = totalHotels;
    }

    public long getTotalHotalAvailable() {
        return totalHotalAvailable;
    }

    public void setTotalHotalAvailable(long totalHotalAvailable) {
        this.totalHotalAvailable = totalHotalAvailable;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getImageHostUrl() {
        return imageHostUrl;
    }

    public void setImageHostUrl(String imageHostUrl) {
        this.imageHostUrl = imageHostUrl;
    }

    
}
