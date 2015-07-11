/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import models.Itineraire;
import models.Vol;
import models.vols.Carrier;
import models.vols.Itinerary;
import models.vols.Leg;
import models.vols.Place;
import models.vols.VolReponseSearch;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.VolParser;

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

    public List<Vol> init(String departure, String destination, String dateDepart, String dateRetour) throws IOException, FileNotFoundException, ParseException {
        VolReponseSearch volReponseSearch = lectureEtParsingFichier();    
        Map<String, Leg> legs = volReponseSearch.getLegs();
        Map<Long, Place> places = volReponseSearch.getPlaces();
        Map<Long, Carrier> carriers = volReponseSearch.getCarriers();
        int idVol =0;
        vols = new ArrayList<>();
        for (Itinerary itinerary : volReponseSearch.getItineraries()) {
            Vol vol = new Vol();
            // Id du vol
            vol.setId(idVol);
            idVol++;
            // Itineraire Aller :
            Itineraire itineraireAller = creationItineraire(legs.get(itinerary.getOutboundLegId()), places, carriers);
            vol.setItineraireAller(itineraireAller);
            // Itineraire Retour
            Itineraire itineraireRetour = creationItineraire(legs.get(itinerary.getInboundLegId()), places, carriers);
            vol.setItineraireRetour(itineraireRetour);
            // Prix
            double prix = itinerary.getPricingOptions().get(0).getPrice();
            vol.setPrix(String.valueOf(prix));
            
            // Ajout du vol à la liste
            vols.add(vol);
        }

        return vols;
    }
    
    public Itineraire creationItineraire(Leg leg, Map<Long, Place> places, Map<Long, Carrier> carriers){
        Itineraire itineraire = new Itineraire();
        // Aeroport Depart
        Place aeroportDepart = places.get(leg.getOriginStation());
        itineraire.setAeroportDepart(aeroportDepart.getName());
        // Aeroport arrivé
        Place aeroportArrive = places.get(leg.getDestinationStation());
        itineraire.setAeroportArrive(aeroportArrive.getName());
        // Durée du voyage
        itineraire.setDuration(leg.getDuration());
        // Date
        String[] dateAndTime = leg.getDeparture().split("T");
        itineraire.setDate(dateAndTime[0]);
        // Heure Départ
        itineraire.setHeureDepart(dateAndTime[1]);
        // Heure Arrivé
        dateAndTime = leg.getArrival().split("T");
        itineraire.setHeureArrive(dateAndTime[1]);
        // Companie : Nom + imageUrl
        Carrier companie = carriers.get(leg.getCarriers()[0]);
        itineraire.setCompagnie(companie.getName());
        itineraire.setImageCompanie(companie.getImageUrl());
        
        return itineraire;
    }
    
    public VolReponseSearch lectureEtParsingFichier() throws FileNotFoundException, IOException, ParseException{
        String filePath = "/Users/arnaudpuigserver/NetBeansProjects/RiE/test/parser/PARIS-LISBONNE.json";
        // read the json file
        FileReader reader = new FileReader(filePath);
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        // Appel de la fonction pour enregister dans l'objet
        VolParser volParser = new VolParser();
        return volParser.jsonParserReponseVol(jsonObject);
    }
    
    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }
    
}
