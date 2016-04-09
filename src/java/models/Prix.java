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
public class Prix {
    private double prixNormal;
    private String partieEntiere;
    private String partieDecimal;

    public Prix(double prix, String partieEntiere, String partieDecimal) {
        this.prixNormal = prix;
        this.partieEntiere = partieEntiere;
        this.partieDecimal = partieDecimal;
    }

    public Prix() {
    }

    public double getPrixNormal() {
        return prixNormal;
    }

    public void setPrixNormal(double prixNormal) {
        this.prixNormal = prixNormal;
    }

    public String getPartieEntiere() {
        return partieEntiere;
    }

    public void setPartieEntiere(String partieEntiere) {
        this.partieEntiere = partieEntiere;
    }

    public String getPartieDecimal() {
        return partieDecimal;
    }

    public void setPartieDecimal(String partieDecimal) {
        this.partieDecimal = partieDecimal;
    }
    
    
}
