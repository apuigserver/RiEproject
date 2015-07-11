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
public class PricingOption {
    
    private long[] agent;
    private long quoteAgeInMinutes;
    private double price;
    private String deeplinkUrl;

    public long[] getAgent() {
        return agent;
    }

    public void setAgent(long[] agent) {
        this.agent = agent;
    }

    public long getQuoteAgeInMinutes() {
        return quoteAgeInMinutes;
    }

    public void setQuoteAgeInMinutes(long quoteAgeInMinutes) {
        this.quoteAgeInMinutes = quoteAgeInMinutes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }
    
}
