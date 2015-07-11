/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


/**
 *
 * @author arnaudpuigserver
 */
public class VolRequestPost {
    
    private String apiKey = "apiKey";
    private String country = "FR";
    private String currency = "EUR";
    private String locale = "fr-FR";
    private String originplace;
    private String destinationplace;
    private String outbounddate;
    private String inbounddate;
    private String locationschema = "sky";
    private String cabinclass = "Economy";
    private int adults = 2;
    private int children = 0;
    private int infants = 0;
    private boolean groupPricing = true;

    public VolRequestPost(String originplace, String destinationplace, String outbounddate, String inbounddate) {
        this.originplace = originplace;
        this.destinationplace = destinationplace;
        this.outbounddate = outbounddate;
        this.inbounddate = inbounddate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getOriginplace() {
        return originplace;
    }

    public void setOriginplace(String originplace) {
        this.originplace = originplace;
    }

    public String getDestinationplace() {
        return destinationplace;
    }

    public void setDestinationplace(String destinationplace) {
        this.destinationplace = destinationplace;
    }

    public String getOutbounddate() {
        return outbounddate;
    }

    public void setOutbounddate(String outbounddate) {
        this.outbounddate = outbounddate;
    }

    public String getInbounddate() {
        return inbounddate;
    }

    public void setInbounddate(String inbounddate) {
        this.inbounddate = inbounddate;
    }

    public String getLocationschema() {
        return locationschema;
    }

    public void setLocationschema(String locationschema) {
        this.locationschema = locationschema;
    }

    public String getCabinclass() {
        return cabinclass;
    }

    public void setCabinclass(String cabinclass) {
        this.cabinclass = cabinclass;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public boolean isGroupPricing() {
        return groupPricing;
    }

    public void setGroupPricing(boolean groupPricing) {
        this.groupPricing = groupPricing;
    }
   
}
