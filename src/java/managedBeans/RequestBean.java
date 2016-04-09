/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import models.Hotel;
import models.Vol;
import models.VolRequestPost;
import models.vols.Place;
import org.json.simple.parser.ParseException;
import org.primefaces.event.DragDropEvent;
import services.HotelService;
import services.VolService;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean
@SessionScoped
public final class RequestBean {
    // Champs Requette
    private String departure;
    private String destination;
    private Date dateDepart;
    private Date dateRetour;
    private VolRequestPost requestPost;
    //Resultat Vols
    private List<Vol> volsRecherche;
    private List<Vol> volsFiltres;
    private List<Vol> volsAffiches;
    private int nbVols;
    
    private List<String> listAeroportsVilleDepartDisponible = new ArrayList<>();
    private String[] listAeroportDepartPourTrie;
    private List<String> listAeroportsVilleArriveDisponible = new ArrayList<>();
    private String[] listAeroportArrivePourTrie;
    // Résultat Hotels
    private List<Hotel> hotels;
    private int nbHotels;
    
    
    private List<Vol> volSelected;
    private List<Hotel> hotelSelected;
    
    @ManagedProperty("#{volService}")
    private VolService serviceVol;
    
    @ManagedProperty("#{hotelService}")
    private HotelService serviceHotel;
    
    public void resultatRecherche(ActionEvent actionEvent) throws IOException, FileNotFoundException, ParseException, java.text.ParseException, Exception {
        String aeroport;
        String dateDepartString = new SimpleDateFormat("yyyy-MM-dd").format(dateDepart);
        String dateRetourString = new SimpleDateFormat("yyyy-MM-dd").format(dateRetour);
        requestPost = new VolRequestPost(departure, destination, dateDepartString, dateRetourString);
        volsRecherche = serviceVol.reseachVols(requestPost.getOriginplace(), requestPost.getDestinationplace(), requestPost.getOutbounddate(), requestPost.getInbounddate());
        List<Place> aeroportsVilleDepart = new ArrayList<>();
        aeroportsVilleDepart.addAll(serviceVol.getListAeroportsAllerDepart());
        aeroportsVilleDepart.addAll(serviceVol.getListAeroportsRetourArrive());
        for (Place place : aeroportsVilleDepart) { 
            aeroport = place.getName() + " - " + place.getCode();
            if(!listAeroportsVilleDepartDisponible.contains(aeroport)){
            listAeroportsVilleDepartDisponible.add(aeroport);
            }
        }
        List<Place> aeroportsVilleArrive = new ArrayList<>();
        aeroportsVilleArrive.addAll(serviceVol.getListAeroportsAllerArrive());
        aeroportsVilleArrive.addAll(serviceVol.getListAeroportsRetourDepart());
        for (Place place : aeroportsVilleArrive) {
            aeroport = place.getName() + " - " + place.getCode();
            if(!listAeroportsVilleArriveDisponible.contains(aeroport)){
            listAeroportsVilleArriveDisponible.add(aeroport);
            }
        }
        nbVols = volsRecherche.size();
        hotels = serviceHotel.reseachHotel(destination, dateDepartString, dateRetourString);
        nbHotels = hotels.size();
        volsAffiches = volsRecherche;
    }
    
    public void filtrageAeroportsVols(ActionEvent actionEvent){
        volsFiltres = volsRecherche;
        for(String aeroport : listAeroportDepartPourTrie){
            String[] nameCode = aeroport.split("-");
            String aeroportCode = nameCode[1].trim();
            volsFiltres = serviceVol.trieVolsSurAeroport(aeroportCode, volsFiltres);
        }
        volsAffiches = volsFiltres;
    }
    
    public void selectionHotel(DragDropEvent ddEvent) {
        hotelSelected = new ArrayList<>();
        if(ddEvent.getData() instanceof Hotel){
            Hotel hotel = ((Hotel) ddEvent.getData());
            if(hotelSelected.size() >= 1){
                hotelSelected = new ArrayList<>();
            }
            hotelSelected.add(hotel);
        }
    }
    
    public void selectionVol(DragDropEvent actionEvent) {
        volSelected = new ArrayList<>();
        if(actionEvent.getData() instanceof Vol){
            Vol vol = ((Vol) actionEvent.getData());
            if(volSelected.size() >= 1){
                volSelected = new ArrayList<>();
            }
            volSelected.add(vol);
        }
    }
    
    /**
     * Creates a new instance of RequestBean
     */
    public RequestBean() {
        volsRecherche = new ArrayList<>();
        hotels = new ArrayList<>();
    }    
    
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public List<Vol> getVolsAffiches() {
        return volsRecherche;
    }

    public void setVolsAffiches(List<Vol> volsAffiches) {
        this.volsRecherche = volsAffiches;
    }

    public void setServiceVol(VolService service) {
        this.serviceVol = service;
    }

    public List<Hotel> getHotelSelected() {
        return hotelSelected;
    }

    public void setHotelSelected(List<Hotel> hotelSelected) {
        this.hotelSelected = hotelSelected;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void setServiceHotel(HotelService serviceHotel) {
        this.serviceHotel = serviceHotel;
    }

    public VolRequestPost getRequestPost() {
        return requestPost;
    }

    public void setRequestPost(VolRequestPost requestPost) {
        this.requestPost = requestPost;
    }

    public int getNbVols() {
        return nbVols;
    }

    public void setNbVols(int nbVols) {
        this.nbVols = nbVols;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }

    public List<String> getListAeroportsVilleDepartDisponible() {
        return listAeroportsVilleDepartDisponible;
    }

    public void setListAeroportsVilleDepartDisponible(List<String> listAeroportsVilleDepartDisponible) {
        this.listAeroportsVilleDepartDisponible = listAeroportsVilleDepartDisponible;
    }

    public List<String> getListAeroportsVilleArriveDisponible() {
        return listAeroportsVilleArriveDisponible;
    }

    public void setListAeroportsVilleArriveDisponible(List<String> listeAeroportArriveDisponible) {
        this.listAeroportsVilleArriveDisponible = listeAeroportArriveDisponible;
    }

    public String[] getListAeroportDepartPourTrie() {
        return listAeroportDepartPourTrie;
    }

    public void setListAeroportDepartPourTrie(String[] listAeroportDepartPourTrie) {
        this.listAeroportDepartPourTrie = listAeroportDepartPourTrie;
    }

    public String[] getListAeroportArrivePourTrie() {
        return listAeroportArrivePourTrie;
    }

    public void setListAeroportArrivePourTrie(String[] listAeroportArrivePourTrie) {
        this.listAeroportArrivePourTrie = listAeroportArrivePourTrie;
    }

    public List<Vol> getVolsRecherche() {
        return volsRecherche;
    }

    public void setVolsRecherche(List<Vol> volsRecherche) {
        this.volsRecherche = volsRecherche;
    }

    public List<Vol> getVolsFiltres() {
        return volsFiltres;
    }

    public void setVolsFiltres(List<Vol> volsFiltres) {
        this.volsFiltres = volsFiltres;
    }

    public List<Vol> getVolSelected() {
        return volSelected;
    }

    public void setVolSelected(List<Vol> volSelected) {
        this.volSelected = volSelected;
    }
 
}
