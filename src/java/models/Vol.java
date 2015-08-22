/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author arnaudpuigserver
 */
public class Vol {
    
    private int id;
    private String nom;
    private String prix;
    private Itineraire itineraireAller;
    private Itineraire itineraireRetour;

    public Vol() {
    }

    public Vol(int id, String nom, String prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
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

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
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
    
}
