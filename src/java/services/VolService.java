/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import models.Itineraire;
import models.Vol;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name="volService")
@ApplicationScoped
public class VolService {
    
    private List<Vol> vols;

    public VolService() {
    }

    public List<Vol> init(String departure, String destination, Date dateDepart, Date dateRetour) {
        vols = new ArrayList<>();
        int i;
        for(i = 0; i <= 50; i++) {
            Itineraire itineraireAller = new Itineraire(dateDepart, departure.substring(0, 3), destination.substring(0, 3), "Air France", "10:00", "12:00");
            Itineraire itineraireRetour = new Itineraire(dateRetour, destination.substring(0, 3), departure.substring(0, 3), "Air France", "16:00", "18:00");
            Vol vol = new Vol();
            vol.setId(i);
            vol.setItineraireAller(itineraireAller);
            vol.setItineraireRetour(itineraireRetour);
            vol.setPrix(100+i);
            vols.add(vol);
        } 
        return vols;
    }
    
    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }
    
}
