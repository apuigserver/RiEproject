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
public class HotelPrice {
    private Long id;
    private List<AgentPrice> agentPrices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AgentPrice> getAgentPrices() {
        return agentPrices;
    }

    public void setAgentPrices(List<AgentPrice> agantPrices) {
        this.agentPrices = agantPrices;
    }
    
    
}
