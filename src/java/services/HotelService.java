/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import models.Hotel;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name="hotelService", eager = true)
@ApplicationScoped
public class HotelService {

    private List<Hotel> hotels;
    /**
     * Creates a new instance of HotelService
     */
    public HotelService() {
    }
    
    public List<Hotel> init(String destination) {
        hotels = new ArrayList<Hotel>();
        int i = 1;
        for(i = 1; i <= 50; i++) {
        hotels.add(new Hotel(i, "Hotel "+ destination + ' ' + i));
        }
        return hotels;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
    
}
