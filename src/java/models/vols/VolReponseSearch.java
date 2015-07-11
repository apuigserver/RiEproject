/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.vols;

import java.util.List;
import java.util.Map;

/**
 *
 * @author arnaudpuigserver
 */
public class VolReponseSearch {
    
    private List<Itinerary> itineraries;
    private Map<String, Leg> legs;
    private Map<Long, Segment> segments;
    private Map<Long, Place> places;
    private Map<Long, Carrier> carriers;
    private Map<Long, Agent> agents;
    private List<Currency> currencies;

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public Map<String, Leg> getLegs() {
        return legs;
    }

    public void setLegs(Map<String, Leg> legs) {
        this.legs = legs;
    }

    public Map<Long, Segment> getSegments() {
        return segments;
    }

    public void setSegments(Map<Long, Segment> segments) {
        this.segments = segments;
    }

    public Map<Long, Place> getPlaces() {
        return places;
    }

    public void setPlaces(Map<Long, Place> places) {
        this.places = places;
    }

    public Map<Long, Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(Map<Long, Carrier> carriers) {
        this.carriers = carriers;
    }

    public Map<Long, Agent> getAgents() {
        return agents;
    }

    public void setAgents(Map<Long, Agent> agents) {
        this.agents = agents;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
    
    
}
