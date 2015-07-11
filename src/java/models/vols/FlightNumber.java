/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.vols;

/**
 *
 * @author arnaudpuigserver
 */
public class FlightNumber {
    
    private String flightNunber;
    private long carrierId;

    public String getFlightNunber() {
        return flightNunber;
    }

    public void setFlightNunber(String flightNunber) {
        this.flightNunber = flightNunber;
    }

    public long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(long carrierId) {
        this.carrierId = carrierId;
    }
    
}
