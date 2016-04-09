/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author arnaudpuigserver
 */
public class Hotel {
    
    private int id;
    private String nom;
    private double distance;
    private List<String> images;
    private double longitude;
    private double latitude;
    private List<Reservation> reservations;
    private String popularite;
    private long noteEtoile;
    private Prix prix;
    private boolean wifiService = false;
    private boolean parkingService = false;
    
    public Hotel() {
    }

    public Hotel(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getPopularite() {
        return popularite;
    }

    public void setPopularite(String popularite) {
        this.popularite = popularite;
    }

    public long getNoteEtoile() {
        return noteEtoile;
    }

    public void setNoteEtoile(long noteEtoile) {
        this.noteEtoile = noteEtoile;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public boolean isWifiService() {
        return wifiService;
    }

    public void setWifiService(boolean wifiService) {
        this.wifiService = wifiService;
    }

    public boolean isParkingService() {
        return parkingService;
    }

    public void setParkingService(boolean parkingService) {
        this.parkingService = parkingService;
    }

}
