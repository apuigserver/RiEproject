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
public class Itinerary {
    
    private String outboundLegId;
    private String inboundLegId;
    private List<PricingOption> pricingOptions;
    private BookingDetailsLink bookingDetailsLink;

    public String getOutboundLegId() {
        return outboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        this.outboundLegId = outboundLegId;
    }

    public String getInboundLegId() {
        return inboundLegId;
    }

    public void setInboundLegId(String inboundLegId) {
        this.inboundLegId = inboundLegId;
    }

    public List<PricingOption> getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(List<PricingOption> pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public BookingDetailsLink getBookingDetailsLink() {
        return bookingDetailsLink;
    }

    public void setBookingDetailsLink(BookingDetailsLink bookingDetailsLink) {
        this.bookingDetailsLink = bookingDetailsLink;
    }
    
}
