/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.json.simple.parser.ParseException;
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
    private List<Vol> vols;
    private Vol vol;
// Résultat Hotels
    private Hotel hotel;
    private List<Hotel> hotels;
    
    
    @ManagedProperty("#{volService}")
    private VolService serviceVol = new VolService();
    
    @ManagedProperty("#{hotelService}")
    private HotelService serviceHotel;
    
    public void resultatRecherche(ActionEvent actionEvent) throws IOException, FileNotFoundException, ParseException {
        requestPost = new VolRequestPost(departure, destination, dateDepart.toString(), dateDepart.toString());
        vols = serviceVol.init(requestPost.getOriginplace(), requestPost.getDestinationplace(), requestPost.getOutbounddate(), requestPost.getInbounddate());
        hotels = serviceHotel.init(destination);
    }
    
    /**
     * Creates a new instance of RequestBean
     */
    public RequestBean() {
        vols = new ArrayList<>();
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

    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }

    public void setServiceVol(VolService service) {
        this.serviceVol = service;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
 
}
