/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import services.VolService;

/**
 *
 * @author arnaudpuigserver
 */
public class VolServiceTest {
    
    private static final VolService volService = new VolService();
    

    @Test
    public void testCalculJoursHeuresMinutes() throws IOException {
       int minutes = 40;
       int[] duree = volService.calculJoursHeuresMinutes(minutes);
       Assert.assertEquals("Le nombre de jours est incorrect", 0, duree[0]);
       Assert.assertEquals("Le nombre d'heures est incorrect", 0, duree[1]);
       Assert.assertEquals("Le nombre de minutes est incorrect", 40, duree[2]);
       
       minutes = 60;
       duree = volService.calculJoursHeuresMinutes(minutes);
       Assert.assertEquals("Le nombre de jours est incorrect", 0, duree[0]);
       Assert.assertEquals("Le nombre d'heures est incorrect", 1, duree[1]);
       Assert.assertEquals("Le nombre de minutes est incorrect", 0, duree[2]);
       
       minutes = 90;
       duree = volService.calculJoursHeuresMinutes(minutes);
       Assert.assertEquals("Le nombre de jours est incorrect", 0, duree[0]);
       Assert.assertEquals("Le nombre d'heures est incorrect", 1, duree[1]);
       Assert.assertEquals("Le nombre de minutes est incorrect", 30, duree[2]);
       
       minutes = 1440;
       duree = volService.calculJoursHeuresMinutes(minutes);
       Assert.assertEquals("Le nombre de jours est incorrect", 1, duree[0]);
       Assert.assertEquals("Le nombre d'heures est incorrect", 0, duree[1]);
       Assert.assertEquals("Le nombre de minutes est incorrect", 0, duree[2]);
    }
     
}

