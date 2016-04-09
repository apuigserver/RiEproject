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
public class Reservation {
    
    private String nameAgent;
    private Double prix;
    private String lien;

    public Reservation() {
        super();
    }
    
    public Reservation(String nameAgent, Double prix, String lien) {
        this.nameAgent = nameAgent;
        this.prix = prix;
        this.lien = lien;
    }

    public String getNameAgent() {
        return nameAgent;
    }

    public void setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
    
}
