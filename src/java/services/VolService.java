/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import models.Vol;

/**
 *
 * @author arnaudpuigserver
 */
@ManagedBean(name="volService")
@ApplicationScoped
public class VolService {
    
    private List<Vol> vols;

    public VolService() {
    }

    public List<Vol> init(String departure, String destination) {
        vols = new ArrayList<>();
        int i;
        for(i = 0; i <= 50; i++) {
        vols.add(new Vol(i, "Vol "+ departure + '-' + destination + " " + i, 100+i));
        } 
        return vols;
    }
    
    public List<Vol> getVols() {
        return vols;
    }

    public void setVols(List<Vol> vols) {
        this.vols = vols;
    }
    
}
