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
public class Segment {
    
    private long id;
    private long originStation;
    private long destinationStation;
    private String departureDatetime;
    private String arrivalDateTime;
    private long carrier;
    private long operatingCarrier;
    private long duration;
    private String flightNumber;
    private String journeyMode;
    private String directionality;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(String departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public long getCarrier() {
        return carrier;
    }

    public void setCarrier(long carrier) {
        this.carrier = carrier;
    }

    public long getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(long operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getJourneyMode() {
        return journeyMode;
    }

    public void setJourneyMode(String journeyMode) {
        this.journeyMode = journeyMode;
    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }
    
}
