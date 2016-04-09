/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.vols;

import java.util.List;

/**
 *
 * @author arnaudpuigserver
 */
public class HotelResponse {
    private long id;
    private String name;
    private String adresse;
    private double distnaceFromSearch;
    private String[] hotelImages;
    private double latitude;
    private double longitude;
    private long nbRooms;
    private long[] amenities;
    private String tag;
    private long popularity;
    private String popularityDescription;
    private long starRating;
    private long score;
    private long district;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getDistnaceFromSearch() {
        return distnaceFromSearch;
    }

    public void setDistnaceFromSearch(double distnaceFromSearch) {
        this.distnaceFromSearch = distnaceFromSearch;
    }

    public String[] getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(String[] hotelImages) {
        this.hotelImages = hotelImages;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getNbRooms() {
        return nbRooms;
    }

    public void setNbRooms(long nbRooms) {
        this.nbRooms = nbRooms;
    }

    public long[] getAmenities() {
        return amenities;
    }

    public void setAmenities(long[] amenities) {
        this.amenities = amenities;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    public String getPopularityDescription() {
        return popularityDescription;
    }

    public void setPopularityDescription(String popularityDescription) {
        this.popularityDescription = popularityDescription;
    }

    public long getStarRating() {
        return starRating;
    }

    public void setStarRating(long starRating) {
        this.starRating = starRating;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getDistrict() {
        return district;
    }

    public void setDistrict(long district) {
        this.district = district;
    }

    
}
