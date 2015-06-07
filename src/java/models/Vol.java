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
    private int prix;

    public Vol() {
    }

    public Vol(int id, String nom, int prix) {
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
  
}
