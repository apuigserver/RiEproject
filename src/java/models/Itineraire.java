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
public class Itineraire {
    
    private String date;
    private String aeroportDepart;
    private String aeroportArrive;
    private String compagnie;
    private String imageCompanie;
    private String heureDepart;
    private String heureArrive;
    private String duration;
    private String nbEscale;

    public Itineraire(){
        
    }
    
    public Itineraire(String date, String aeroportDepart, String aeroportArrive, String compagnie, String heureDepart, String heureArrive) {
        this.date = date;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrive = aeroportArrive;
        this.compagnie = compagnie;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
    }
    
    public Itineraire(String date, String aeroportDepart, String aeroportArrive, String compagnie, String imageCompanie, String heureDepart, String heureArrive) {
        this.date = date;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrive = aeroportArrive;
        this.compagnie = compagnie;
        this.imageCompanie = imageCompanie;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(String aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public String getAeroportArrive() {
        return aeroportArrive;
    }

    public void setAeroportArrive(String aeroportArrive) {
        this.aeroportArrive = aeroportArrive;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(String heureArrive) {
        this.heureArrive = heureArrive;
    }

    public String getImageCompanie() {
        return imageCompanie;
    }

    public void setImageCompanie(String imageCompanie) {
        this.imageCompanie = imageCompanie;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNbEscale() {
        return nbEscale;
    }

    public void setNbEscale(String nbEscale) {
        this.nbEscale = nbEscale;
    }
    
}
