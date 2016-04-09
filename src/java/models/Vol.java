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
public class Vol {
    
    private String id;
    private Prix prix;
    private Itineraire itineraireAller;
    private Itineraire itineraireRetour;
    private List<Reservation> reservations;
    private String tempsSurPlace;
    
    public Vol() {
    }

    public Vol(String id, Prix prix) {
        this.id = id;
        this.prix = prix;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public Itineraire getItineraireAller() {
        return itineraireAller;
    }

    public void setItineraireAller(Itineraire itineraireAller) {
        this.itineraireAller = itineraireAller;
    }

    public Itineraire getItineraireRetour() {
        return itineraireRetour;
    }

    public void setItineraireRetour(Itineraire itineraireRetour) {
        this.itineraireRetour = itineraireRetour;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getTempsSurPlace() {
        return tempsSurPlace;
    }

    public void setTempsSurPlace(String tempsSurPlace) {
        this.tempsSurPlace = tempsSurPlace;
    }
    
}
