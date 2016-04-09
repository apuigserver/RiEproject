/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.Itineraire;
import models.Prix;
import models.Reservation;
import models.Vol;
import models.vols.Agent;
import models.vols.Carrier;
import models.vols.Itinerary;
import models.vols.Leg;
import models.vols.Place;
import models.vols.PricingOption;
import models.vols.VolReponseSearch;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.VolParser;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name = "volService")
@SessionScoped
public class VolService {

    private List<Vol> vols;

    private List<Place> listAeroportsAllerDepart = new ArrayList<>();
    private List<Place> listAeroportsAllerArrive = new ArrayList<>();
    private List<Place> listAeroportsRetourDepart = new ArrayList<>();
    private List<Place> listAeroportsRetourArrive = new ArrayList<>();

    private final HttpRequestService httpRequestService = new HttpRequestService();

    public VolService() {
    }

    public List<Vol> reseachVols(String departure, String destination, String dateDepart, String dateRetour) throws IOException, Exception {
        List<Vol> listVols = new ArrayList<>();
        VolParser volParser = new VolParser();
        JSONParser jsonParser = new JSONParser();
        HttpResponse httpResponsePostRequest = httpRequestService.sendPostVolRequest(departure, destination, dateDepart, dateRetour);
        if (httpResponsePostRequest.getStatusLine().getStatusCode() == 201) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            HttpResponse getResponse = httpRequestService.sendGetRequestAfterPostRequest(httpResponsePostRequest);
            if (getResponse.getStatusLine().getStatusCode() == 200) {
                String responseJson = EntityUtils.toString(getResponse.getEntity());
                JSONObject jsonObject = (JSONObject) jsonParser.parse(responseJson);
                String statutRechercheVol = volParser.jsonParserStatut(jsonObject);
                while (!"UpdatesComplete".equals(statutRechercheVol)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    getResponse = httpRequestService.sendGetRequestAfterGetRequest(getResponse);
                    if (getResponse.getStatusLine().getStatusCode() == 200) {
                        jsonObject = (JSONObject) jsonParser.parse(responseJson);
                        statutRechercheVol = volParser.jsonParserStatut(jsonObject);
                    } else {
                        break;
                    }
                }
                // Appel de la fonction pour enregister dans l'objet

                VolReponseSearch volResponseSearch = volParser.jsonParserReponseVol(jsonObject);
                listVols = creationVols(volResponseSearch);

            }
        }
        return listVols;
    }

    public List<Vol> creationVols(VolReponseSearch volReponseSearch) throws java.text.ParseException {
        List<Vol> listVols = new ArrayList<>();

        Map<String, Leg> legs = volReponseSearch.getLegs();
        Map<Long, Place> places = volReponseSearch.getPlaces();
        Map<Long, Carrier> carriers = volReponseSearch.getCarriers();
        Map<Long, Agent> agents = volReponseSearch.getAgents();

        for (Itinerary itinerary : volReponseSearch.getItineraries()) {
            Vol vol = new Vol();
            // Itineraire Aller :
            List<Object> sortieAller = creationItineraire(legs.get(itinerary.getOutboundLegId()), places, carriers);
            Itineraire itineraireAller = (Itineraire) sortieAller.get(0);
            vol.setItineraireAller(itineraireAller);
            listAeroportsAllerDepart.addAll((List<Place>) sortieAller.get(1));
            listAeroportsAllerArrive.addAll((List<Place>) sortieAller.get(2));
            // Itineraire Retour
            List<Object> sortieRetour = creationItineraire(legs.get(itinerary.getInboundLegId()), places, carriers);
            Itineraire itineraireRetour = (Itineraire) sortieRetour.get(0);
            vol.setItineraireRetour(itineraireRetour);
            listAeroportsRetourDepart.addAll((List<Place>) sortieRetour.get(1));
            listAeroportsRetourArrive.addAll((List<Place>) sortieRetour.get(2));
            // Prix
            double prix = itinerary.getPricingOptions().get(0).getPrice();
            String prixString = String.valueOf(prix);
            int indexPoint = prixString.indexOf('.');
            if (indexPoint == -1) {
                vol.setPrix(new Prix(prix, prixString, "00"));
            } else {
                vol.setPrix(new Prix(prix, prixString.substring(0, indexPoint), prixString.substring(indexPoint + 1)));
            }
            // Id du vol
            String tempsSurPlace = calculDureeSurPlace(itineraireAller, itineraireRetour);
            vol.setTempsSurPlace(tempsSurPlace);
            vol.setId(tempsSurPlace + " - " + vol.getPrix().getPrixNormal() + "€");
            // Reservations
            List<Reservation> reservations = createReservations(itinerary.getPricingOptions(), agents);
            vol.setReservations(reservations);
            // Ajout du vol à la liste
            listVols.add(vol);
        }

        return listVols;
    }

    public List<Object> creationItineraire(Leg leg, Map<Long, Place> places, Map<Long, Carrier> carriers) {
        Itineraire itineraire = new Itineraire();
        List<Place> aeroportsVilleDepart = new ArrayList<>();
        List<Place> aeroportsVilleArrivee = new ArrayList<>();
        // Aeroport Depart
        Place aeroportDepart = places.get(leg.getOriginStation());
        itineraire.setAeroportDepart(aeroportDepart.getCode());
        if (!aeroportsVilleDepart.contains(aeroportDepart)) {
            aeroportsVilleDepart.add(aeroportDepart);
        }
        // Aeroport arrivé
        Place aeroportArrive = places.get(leg.getDestinationStation());
        itineraire.setAeroportArrive(aeroportArrive.getCode());
        if (!aeroportsVilleArrivee.contains(aeroportArrive)) {
            aeroportsVilleArrivee.add(aeroportArrive);
        }
        // Durée du voyage
        int[] dureeVol = calculJoursHeuresMinutes(leg.getDuration());
        itineraire.setDuration(dureeVol[1] + "h" + dureeVol[2]);
        // Date
        String[] dateAndTime = leg.getDeparture().split("T");
        itineraire.setDate(dateAndTime[0]);
        // Heure Départ
        itineraire.setHeureDepart(dateAndTime[1].substring(0, 5));
        // Heure Arrivé
        dateAndTime = leg.getArrival().split("T");
        itineraire.setHeureArrive(dateAndTime[1].substring(0, 5));
        // Companie : Nom + imageUrl
        Carrier companie = carriers.get(leg.getCarriers()[0]);
        itineraire.setCompagnie(companie.getName());
        itineraire.setImageCompanie(companie.getImageUrl());
        // Type de vol : Direct ou Avec Escale
        int nbEscale = leg.getSegmentId().length - 1;
        itineraire.setNbEscale(String.valueOf(nbEscale));

        List<Object> sortie = new ArrayList<>();
        sortie.add(itineraire);
        sortie.add(aeroportsVilleDepart);
        sortie.add(aeroportsVilleArrivee);
        return sortie;
    }

    public List<Reservation> createReservations(List<PricingOption> pricingOptions, Map<Long, Agent> agents) {
        List<Reservation> reservations = new ArrayList<>();
        for (PricingOption pricingOption : pricingOptions) {
            for (Long idAgent : pricingOption.getAgent()) {
                Reservation reservation = new Reservation();
                Agent agent = agents.get(idAgent);
                reservation.setNameAgent(agent.getName());
                reservation.setPrix(pricingOption.getPrice());
                reservation.setLien(pricingOption.getDeeplinkUrl());
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public VolReponseSearch lectureEtParsingFichierVol() throws FileNotFoundException, IOException, ParseException {
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

    public String calculDureeSurPlace(Itineraire itineraireAller, Itineraire itineraireRetour) throws java.text.ParseException {
        int dureeMinutes = 0;
        LocalDate dateArrive = LocalDate.parse(itineraireAller.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate datePartir = LocalDate.parse(itineraireRetour.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        // Calcul jour d'arrivé
        String[] heureArrive = itineraireAller.getHeureArrive().split(":");
        int dureeFirstDay = Integer.valueOf(heureArrive[0]) * 60;
        dureeFirstDay += Integer.valueOf(heureArrive[1]);
        dureeMinutes = 1440 - dureeFirstDay;
        // calcul du nombre de jour
        int nbJours = 0;
        while (dateArrive.compareTo(datePartir) > 0) {
            dateArrive.plusDays(1L);
            nbJours += 1;
        }
        // calcul jour de départ
        String[] heureDepart = itineraireRetour.getHeureDepart().split(":");
        int dureeLastDay = Integer.valueOf(heureDepart[0]) * 60;
        dureeLastDay += Integer.valueOf(heureDepart[1]);
        dureeMinutes += dureeLastDay;
        int[] dureeTotal = calculJoursHeuresMinutes(dureeMinutes);
        nbJours += dureeTotal[0];

        StringBuilder dureeTotalBuilder = new StringBuilder();
        if (nbJours != 0) {
            dureeTotalBuilder.append(nbJours);
            dureeTotalBuilder.append("j ");
        }
        dureeTotalBuilder.append(dureeTotal[1]);
        dureeTotalBuilder.append("h");
        if (dureeTotal[2] != 0) {
            dureeTotalBuilder.append(dureeTotal[2]);
        } else {
            dureeTotalBuilder.append("00");
        }
        return dureeTotalBuilder.toString();
    }

    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }

    public int[] calculJoursHeuresMinutes(long minutes) {
        int temps[] = new int[3];
        int heures = 0;
        while (minutes > 59) {
            heures++;
            minutes -= 60;
        }
        int jours = 0;
        while (heures > 23) {
            jours++;
            heures -= 24;
        }
        temps[0] = jours;
        temps[1] = heures;
        temps[2] = ((Long) minutes).intValue();
        return temps;
    }

    public List<Vol> trieVolsSurAeroport(String aeroportCode, List<Vol> listVols) {
        List<Vol> vols = new ArrayList<>();
        for (Vol vol : listVols) {
            String allerAeroportDepart = vol.getItineraireAller().getAeroportDepart();
            String allerAeroportArrive = vol.getItineraireAller().getAeroportArrive();
            String retourAeroportDepart = vol.getItineraireRetour().getAeroportDepart();
            String retourAeroportArrive = vol.getItineraireRetour().getAeroportDepart();
            if (aeroportCode.equals(allerAeroportDepart) || aeroportCode.equals(allerAeroportArrive) || aeroportCode.equals(retourAeroportDepart) || aeroportCode.equals(retourAeroportArrive)) {
                vols.add(vol);
            }
        }
        return vols;
    }

    public List<Place> getListAeroportsAllerDepart() {
        return listAeroportsAllerDepart;
    }

    public void setListAeroportsAllerDepart(List<Place> listAeroportsAllerDepart) {
        this.listAeroportsAllerDepart = listAeroportsAllerDepart;
    }

    public List<Place> getListAeroportsAllerArrive() {
        return listAeroportsAllerArrive;
    }

    public void setListAeroportsAllerArrive(List<Place> listAeroportsAllerArrive) {
        this.listAeroportsAllerArrive = listAeroportsAllerArrive;
    }

    public List<Place> getListAeroportsRetourDepart() {
        return listAeroportsRetourDepart;
    }

    public void setListAeroportsRetourDepart(List<Place> listAeroportsRetourDepart) {
        this.listAeroportsRetourDepart = listAeroportsRetourDepart;
    }

    public List<Place> getListAeroportsRetourArrive() {
        return listAeroportsRetourArrive;
    }

    public void setListAeroportsRetourArrive(List<Place> listAeroportsRetourArrive) {
        this.listAeroportsRetourArrive = listAeroportsRetourArrive;
    }

}
