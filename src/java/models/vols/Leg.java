/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.vols;

import java.util.List;

/**
 *
 * @author arnaudpuigserver
 */
public class Leg {
    
    private String id;
    private long[] segmentId;
    private long originStation;
    private long destinationStation;
    private String departure;
    private String arrival;
    private long duration;
    private String journeyMode;
    private long[] stops;
    private long[] carriers;
    private long[] operatingCarriers;
    private String directioality;
    private List<FlightNumber> flightNumbers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long[] getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(long[] segmentId) {
        this.segmentId = segmentId;
    }

    public long getOriginStation() {
        return originStation;
    }

    public void setOriginStation(long originStation) {
        this.originStation = originStation;
    }

    public long getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(long destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getJourneyMode() {
        return journeyMode;
    }

    public void setJourneyMode(String journeyMode) {
        this.journeyMode = journeyMode;
    }

    public long[] getStops() {
        return stops;
    }

    public void setStops(long[] stops) {
        this.stops = stops;
    }

    public long[] getCarriers() {
        return carriers;
    }

    public void setCarriers(long[] carriers) {
        this.carriers = carriers;
    }

    public long[] getOperatingCarriers() {
        return operatingCarriers;
    }

    public void setOperatingCarriers(long[] operatingCarriers) {
        this.operatingCarriers = operatingCarriers;
    }

    public String getDirectioality() {
        return directioality;
    }

    public void setDirectioality(String directioality) {
        this.directioality = directioality;
    }

    public List<FlightNumber> getFlightNumbers() {
        return flightNumbers;
    }

    public void setFlightNumbers(List<FlightNumber> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }
    
}
