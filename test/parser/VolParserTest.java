/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.vols.Agent;
import models.vols.BookingDetailsLink;
import models.vols.Carrier;
import models.vols.Currency;
import models.vols.Itinerary;
import models.vols.Leg;
import models.vols.Place;
import models.vols.PricingOption;
import models.vols.Segment;
import models.vols.VolReponseSearch;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author arnaudpuigserver
 */
public class VolParserTest {
    
    private static final VolParser volParser = new VolParser();

    @Test
    public void testJsonParserVolReponseSearch() throws ParseException, FileNotFoundException, IOException {
        String filePath = "/Users/arnaudpuigserver/NetBeansProjects/RiE/test/parser/PARIS-LISBONNE.json";
        // read the json file
        FileReader reader = new FileReader(filePath);
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        // Appel de la fonction pour enregister dans l'objet
        VolReponseSearch volReponseSearch = volParser.jsonParserReponseVol(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le nombre d'Itineraries est incorrect",86, volReponseSearch.getItineraries().size());
        Assert.assertEquals("Le nombre de Legs est incorrect",158, volReponseSearch.getLegs().size());
        Assert.assertEquals("Le nombre de Segments est incorrect",225, volReponseSearch.getSegments().size());
        Assert.assertEquals("Le nombre de Carriers est incorrect",27, volReponseSearch.getCarriers().size());
        Assert.assertEquals("Le nombre d'Agents est incorrect",32, volReponseSearch.getAgents().size());
        Assert.assertEquals("Le nombre de Places est incorrect",59, volReponseSearch.getPlaces().size());
        Assert.assertEquals("Le nombre de Currencies est incorrect",5, volReponseSearch.getCurrencies().size());
        
    }
    
    /**
     * Test of jsonParserPrincingOption method, of class VolParser.
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserItinerary() throws ParseException {
        // String en entrée
        String inJson = "{\"OutboundLegId\":\"10223-1507031035-FR-0-13577-1507031210\"" +
"        \"InboundLegId\":\"13577-1507080945-HV-0-15083-1507081300\"" +
"        \"PricingOptions\":" +
"            [{\"Agents\":[4498498],\"QuoteAgeInMinutes\":1,\"Price\":183.07,\"DeeplinkUrl\":\"http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=U3JSea2mKUEEs96eepPrHZhTJvxklImVgOia5xL%2bFQ47EB3asHYgslgEPX%2b%2b0FxE&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fFR%2ffr-fr%2fEUR%2fxpfr%2f2%2f6073.13577.2015-07-03%2c13577.6073.2015-07-08%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31915%7c1084%7c10223%7c2015-07-03T10%3a35%7c13577%7c2015-07-03T12%3a10%2cflight%7c-31748%7c3413%7c13577%7c2015-07-08T09%3a45%7c15083%7c2015-07-08T13%3a00%26carriers%3d-31915%26passengers%3d1%2c0%2c0%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d183.07%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_website%26request_id%3d563f0fe2-0271-4043-88f4-9662e9c9a6c9%26deeplink_ids%3dfc6bd821cff465d33a58a1a7cef72972%26q_datetime_utc%3d2015-06-25T17%3a00%3a15\"}," +
"            {\"Agents\":[3503170],\"QuoteAgeInMinutes\":1,\"Price\":191.99,\"DeeplinkUrl\":\"http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=U3JSea2mKUEEs96eepPrHZhTJvxklImVgOia5xL%2bFQ47EB3asHYgslgEPX%2b%2b0FxE&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fFR%2ffr-fr%2fEUR%2fopfr%2f2%2f6073.13577.2015-07-03%2c13577.6073.2015-07-08%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31915%7c1084%7c10223%7c2015-07-03T10%3a35%7c13577%7c2015-07-03T12%3a10%2cflight%7c-31748%7c3413%7c13577%7c2015-07-08T09%3a45%7c15083%7c2015-07-08T13%3a00%26carriers%3d-31915%26passengers%3d1%2c0%2c0%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d191.99%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_website%26request_id%3d563f0fe2-0271-4043-88f4-9662e9c9a6c9%26deeplink_ids%3db736b0d9cf654c6424bf1f9bce504194%26q_datetime_utc%3d2015-06-25T16%3a59%3a59\"}," +
"            {\"Agents\":[2616130],\"QuoteAgeInMinutes\":1,\"Price\":191.99,\"DeeplinkUrl\":\"http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=U3JSea2mKUEEs96eepPrHZhTJvxklImVgOia5xL%2bFQ47EB3asHYgslgEPX%2b%2b0FxE&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fFR%2ffr-fr%2fEUR%2fgofr%2f2%2f6073.13577.2015-07-03%2c13577.6073.2015-07-08%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31915%7c1084%7c10223%7c2015-07-03T10%3a35%7c13577%7c2015-07-03T12%3a10%2cflight%7c-31748%7c3413%7c13577%7c2015-07-08T09%3a45%7c15083%7c2015-07-08T13%3a00%26carriers%3d-31915%26passengers%3d1%2c0%2c0%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d191.99%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_website%26request_id%3d563f0fe2-0271-4043-88f4-9662e9c9a6c9%26deeplink_ids%3d3bef27e4d4f4dae1661be080c0c0b3fe%26q_datetime_utc%3d2015-06-25T16%3a59%3a57\"}," +
"            {\"Agents\":[2369602],\"QuoteAgeInMinutes\":1,\"Price\":191.99,\"DeeplinkUrl\":\"http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=U3JSea2mKUEEs96eepPrHZhTJvxklImVgOia5xL%2bFQ47EB3asHYgslgEPX%2b%2b0FxE&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fFR%2ffr-fr%2fEUR%2fedfr%2f2%2f6073.13577.2015-07-03%2c13577.6073.2015-07-08%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31915%7c1084%7c10223%7c2015-07-03T10%3a35%7c13577%7c2015-07-03T12%3a10%2cflight%7c-31748%7c3413%7c13577%7c2015-07-08T09%3a45%7c15083%7c2015-07-08T13%3a00%26carriers%3d-31915%26passengers%3d1%2c0%2c0%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d191.99%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_website%26request_id%3d563f0fe2-0271-4043-88f4-9662e9c9a6c9%26deeplink_ids%3d9fe7b96bdb4f4ff81e272e39865440b5%26q_datetime_utc%3d2015-06-25T16%3a59%3a57\"}," +
"            {\"Agents\":[2042434],\"QuoteAgeInMinutes\":1,\"Price\":194.39,\"DeeplinkUrl\":\"http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=U3JSea2mKUEEs96eepPrHZhTJvxklImVgOia5xL%2bFQ47EB3asHYgslgEPX%2b%2b0FxE&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fFR%2ffr-fr%2fEUR%2fbffr%2f2%2f6073.13577.2015-07-03%2c13577.6073.2015-07-08%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31915%7c1084%7c10223%7c2015-07-03T10%3a35%7c13577%7c2015-07-03T12%3a10%2cflight%7c-31748%7c3413%7c13577%7c2015-07-08T09%3a45%7c15083%7c2015-07-08T13%3a00%26carriers%3d-31915%26passengers%3d1%2c0%2c0%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d194.39%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_website%26request_id%3d563f0fe2-0271-4043-88f4-9662e9c9a6c9%26deeplink_ids%3dfefdc8808c46d51a7e102520d12b8e1a%26q_datetime_utc%3d2015-06-25T16%3a59%3a58\"}]" +
"        \"BookingDetailsLink\":" +
"            {\"Uri\":\"/apiservices/pricing/v1.0/ea849b6abfdd423a8378320ef2e19433_ecilpojl_A6F5CAD2092EDD9721A9A911E9C2534E/booking\",\"Body\":\"OutboundLegId=10223-1507031035-FR-0-13577-1507031210&InboundLegId=13577-1507080945-HV-0-15083-1507081300\",\"Method\":\"PUT\"}}"; 
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        Itinerary itinerary = volParser.jsonParserItinerary(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le OutboundLegId enregistré est incorrect","10223-1507031035-FR-0-13577-1507031210", itinerary.getOutboundLegId());
        Assert.assertEquals("Le InboundLegId enregistré est incorrect","13577-1507080945-HV-0-15083-1507081300", itinerary.getInboundLegId());
        Assert.assertEquals("Le nombre de pricingOption enregistré est incorrect",5, itinerary.getPricingOptions().size());
        Assert.assertEquals("La Méthode du BookingDetailsLink enregistré est incorrect","PUT", itinerary.getBookingDetailsLink().getMethod());
    }
    
    /**
     * Test of jsonParserPrincingOption method, of class VolParser.
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserPrincingOption() throws ParseException {
        // String en entrée
        String inJson = "{\"Agents\":[3503170],\"QuoteAgeInMinutes\":1,\"Price\":195.13,\"DeeplinkUrl\":\"http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=U3JSea2mKUEEs96eepPrHZhTJvxklImVgOia5xL%2bFQ47EB3asHYgslgEPX%2b%2b0FxE&url=http%3a%2f%2fwww.apideeplink.com%2ftransport_deeplink%2f4.0%2fFR%2ffr-fr%2fEUR%2fopfr%2f2%2f10223.13577.2015-07-03%2c13577.10223.2015-07-08%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-31915%7c1084%7c10223%7c2015-07-03T10%3a35%7c13577%7c2015-07-03T12%3a10%2cflight%7c-31915%7c1083%7c13577%7c2015-07-08T06%3a10%7c10223%7c2015-07-08T09%3a40%26carriers%3d-31915%26passengers%3d1%2c0%2c0%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d195.13%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_website%26request_id%3d563f0fe2-0271-4043-88f4-9662e9c9a6c9%26deeplink_ids%3d8a53e40dc0b0c1b48ecbe9726a275eee%26q_datetime_utc%3d2015-06-25T16%3a59%3a59\"}"; 
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        PricingOption pricingOption = volParser.jsonParserPrincingOption(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le prix enregistré est incorrect","195.13", String.valueOf(pricingOption.getPrice()));
        Assert.assertEquals("Le QuoteAgeInMinutes enregistré est incorrect","1", String.valueOf(pricingOption.getQuoteAgeInMinutes()));
    }
    
    /**
     * Test of jsonParserBookingDetailsLink method, of class VolParser.
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserBookingDetailsLink() throws ParseException {
        // String en entrée
        String inJson = "{\"Uri\":\"/apiservices/pricing/v1.0/ea849b6abfdd423a8378320ef2e19433_ecilpojl_A6F5CAD2092EDD9721A9A911E9C2534E/booking\",\"Body\":\"OutboundLegId=10223-1507031035-FR-0-13577-1507031210&InboundLegId=13577-1507080610-FR-0-10223-1507080940\",\"Method\":\"PUT\"}"; 
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        BookingDetailsLink bookingDetailsLink = volParser.jsonParserBookingDetailsLink(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("La method enregistré est incorrect","PUT", bookingDetailsLink.getMethod());
        Assert.assertNotNull("L'attribu Body ne doit pas être null",bookingDetailsLink.getBody());
        Assert.assertNotNull("L'attribu Uri ne doit pas être null",bookingDetailsLink.getUri());
    }
    
    /**
     * Test of jsonParserPlace method, of class VolParser.
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserPlace() throws ParseException {
        // String en entrée
        String inJson = "{\"Id\":10223,\"ParentId\":6073,\"Code\":\"BVA\",\"Type\":\"Airport\",\"Name\":\"Paris Beauvais\"}";
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        Place place = volParser.jsonParserPlace(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("L'Id enregistré est incorrect",10223l, place.getId());
        Assert.assertEquals("Le ParentId enregistré est incorrect",6073l, place.getParentId());
        Assert.assertEquals("Le code enregistré est incorrect","BVA", place.getCode());
        Assert.assertEquals("Le type enregistré est incorrect","Airport", place.getType());
        Assert.assertEquals("Le nom enregistré est incorrect","Paris Beauvais", place.getName());
    }
    
    /**
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserListPlace() throws ParseException {
        // String en entrée
        String inJson = "[{\"Id\":10223,\"ParentId\":6073,\"Code\":\"BVA\",\"Type\":\"Airport\",\"Name\":\"Paris Beauvais\"},{\"Id\":13577,\"ParentId\":4609,\"Code\":\"LIS\",\"Type\":\"Airport\",\"Name\":\"Lisbonne\"},{\"Id\":15083,\"ParentId\":6073,\"Code\":\"ORY\",\"Type\":\"Airport\",\"Name\":\"Paris Orly\"},{\"Id\":10413,\"ParentId\":6073,\"Code\":\"CDG\",\"Type\":\"Airport\",\"Name\":\"Paris Charles-de-Gaulle\"},{\"Id\":13870,\"ParentId\":4854,\"Code\":\"MAD\",\"Type\":\"Airport\",\"Name\":\"Madrid\"},{\"Id\":9772,\"ParentId\":782,\"Code\":\"BCN\",\"Type\":\"Airport\",\"Name\":\"Barcelone\"},{\"Id\":14385,\"ParentId\":5363,\"Code\":\"MUC\",\"Type\":\"Airport\",\"Name\":\"Munich\"},{\"Id\":9451,\"ParentId\":509,\"Code\":\"AMS\",\"Type\":\"Airport\",\"Name\":\"Amsterdam Schiphol\"},{\"Id\":18563,\"ParentId\":9168,\"Code\":\"ZRH\",\"Type\":\"Airport\",\"Name\":\"Zurich\"},{\"Id\":17517,\"ParentId\":8222,\"Code\":\"VIE\",\"Type\":\"Airport\",\"Name\":\"Vienne\"},{\"Id\":10141,\"ParentId\":1178,\"Code\":\"BRU\",\"Type\":\"Airport\",\"Name\":\"Bruxelles International\"},{\"Id\":10001,\"ParentId\":1101,\"Code\":\"BLQ\",\"Type\":\"Airport\",\"Name\":\"Bologne\"},{\"Id\":15055,\"ParentId\":6359,\"Code\":\"OPO\",\"Type\":\"Airport\",\"Name\":\"Porto\"},{\"Id\":11616,\"ParentId\":2687,\"Code\":\"FRA\",\"Type\":\"Airport\",\"Name\":\"Francfort-sur-le-Main\"},{\"Id\":11493,\"ParentId\":6781,\"Code\":\"FCO\",\"Type\":\"Airport\",\"Name\":\"Rome Fiumicino\"},{\"Id\":14476,\"ParentId\":5072,\"Code\":\"MXP\",\"Type\":\"Airport\",\"Name\":\"Milan Malpensa\"},{\"Id\":13572,\"ParentId\":5072,\"Code\":\"LIN\",\"Type\":\"Airport\",\"Name\":\"Milan Linate\"}]";
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONArray jsonarray = (JSONArray) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        List<Place> places = new ArrayList();
        Iterator i = jsonarray.iterator();
	while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Place place = volParser.jsonParserPlace(innerObj);
            places.add(place);
        }
        // Vérification des valeurs
        int NbPlaces = places.size();
        Assert.assertEquals("Le nombre de Place est incorrect",17 , NbPlaces);
        Place place = places.get(5);
        Assert.assertEquals("L'Id enregistré est incorrect",9772l, place.getId());
        Assert.assertEquals("Le ParentId enregistré est incorrect",782l, place.getParentId());
        Assert.assertEquals("Le code enregistré est incorrect","BCN", place.getCode());
        Assert.assertEquals("Le type enregistré est incorrect","Airport", place.getType());
        Assert.assertEquals("Le nom enregistré est incorrect","Barcelone", place.getName());
    }
    
    /**
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserAgent() throws ParseException {
        // String en entrée
        String inJson = "{\"Id\":2032127,\"Name\":\"British Airways\",\"ImageUrl\":\"http://s1.apideeplink.com/images/websites/ba__.png\",\"Status\":\"UpdatesComplete\",\"OptimisedForMobile\":true,\"BookingNumber\":\"0825825400\",\"Type\":\"Airline\"}";
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        // Appel de la fonction pour enregister dans l'objet
        Agent agent = volParser.jsonParserAgent(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("L'Id enregistré est incorrect",2032127l, agent.getId());
        Assert.assertEquals("L'optimiseForMobile enregistré est incorrect",true, agent.isOptimiseForMobile());
        Assert.assertEquals("Le name enregistré est incorrect","British Airways", agent.getName());
        Assert.assertEquals("Le status enregistré est incorrect","UpdatesComplete", agent.getStatus());
        Assert.assertEquals("Le type enregistré est incorrect","Airline", agent.getType());
        Assert.assertEquals("Le Booking Number enregistré est incorrect","0825825400", agent.getBookingNumber());
    }
    
    /**
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserCurrency() throws ParseException {
        // String en entrée
        String inJson = "{\"Code\":\"TRY\",\"Symbol\":\"TL\",\"ThousandsSeparator\":\".\",\"DecimalSeparator\":\",\",\"SymbolOnLeft\":false,\"SpaceBetweenAmountAndSymbol\":true,\"RoundingCoefficient\":0,\"DecimalDigits\":2}";
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        // Appel de la fonction pour enregister dans l'objet
        Currency currency = volParser.jsonParserCurrency(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le code enregistré est incorrect","TRY", currency.getCode());
        Assert.assertEquals("Le symbol enregistré est incorrect","TL", currency.getSymbol());
        Assert.assertEquals("Le ThousandSeparator enregistré est incorrect",".", currency.getThousandSeparator());
        Assert.assertEquals("Le DecimalSeparator enregistré est incorrect",",", currency.getDecimalSeparator());
        Assert.assertEquals("Le SymbolOnLeft enregistré est incorrect",false, currency.isSymbolOnLeft());
        Assert.assertEquals("Le SpaceAmountSymbol enregistré est incorrect",true, currency.isSpaceBetweenAmountAndSymbol());
        Assert.assertEquals("Le RoundingCoefficient enregistré est incorrect",0, currency.getRoundingCoeffcient());
        Assert.assertEquals("Le DecimalDigits  enregistré est incorrect",2 , currency.getDecimalDigits());
    }
    
    /**
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserCarrier() throws ParseException {
        // String en entrée
        String inJson = "{\"Id\":1090,\"Code\":\"FR\",\"Name\":\"Ryanair\",\"ImageUrl\":\"http://s1.apideeplink.com/images/airlines/FR.png\",\"DisplayCode\":\"FR\"}";
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        // Appel de la fonction pour enregister dans l'objet
        Carrier carrier = volParser.jsonParserCarrier(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le ID enregistré est incorrect",1090l, carrier.getId());
        Assert.assertEquals("Le Code enregistré est incorrect","FR", carrier.getCode());
        Assert.assertEquals("Le Name enregistré est incorrect","Ryanair", carrier.getName());
        Assert.assertEquals("Le ImageUrl enregistré est incorrect","http://s1.apideeplink.com/images/airlines/FR.png", carrier.getImageUrl());
        Assert.assertEquals("Le DisplayCode enregistré est incorrect","FR", carrier.getDisplayCode());
    }
    
    /**
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserSegment() throws ParseException {
        // String en entrée
        String inJson = "{\"Id\":1,\"OriginStation\":10223,\"DestinationStation\":13577,\"DepartureDateTime\":\"2015-07-03T10:35:00\",\"ArrivalDateTime\":\"2015-07-03T12:10:00\",\"Carrier\":1090,\"OperatingCarrier\":1090,\"Duration\":155,\"FlightNumber\":\"1084\",\"JourneyMode\":\"Flight\",\"Directionality\":\"Outbound\"}";
        // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        // Appel de la fonction pour enregister dans l'objet
        Segment segment = volParser.jsonParserSegment(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le ID enregistré est incorrect",1l, segment.getId());
        Assert.assertEquals("Le OriginStation enregistré est incorrect",10223l, segment.getOriginStation());
        Assert.assertEquals("Le DestinationStation est incorrect",13577l, segment.getDestinationStation());
        Assert.assertEquals("Le DepartureDateTime enregistré est incorrect","2015-07-03T10:35:00", segment.getDepartureDatetime());
        Assert.assertEquals("Le ArrivalDateTime enregistré est incorrect","2015-07-03T12:10:00", segment.getArrivalDateTime());
        Assert.assertEquals("Le Carrier enregistré est incorrect",1090l, segment.getCarrier());
        Assert.assertEquals("Le OperatingCarrier enregistré est incorrect",1090l, segment.getOperatingCarrier());
        Assert.assertEquals("Le Duration enregistré est incorrect",155l, segment.getDuration());
        Assert.assertEquals("Le FlightNumber enregistré est incorrect","1084", segment.getFlightNumber());
        Assert.assertEquals("Le JourneyMode enregistré est incorrect","Flight", segment.getJourneyMode());
        Assert.assertEquals("Le Directionality enregistré est incorrect","Outbound", segment.getDirectionality());
    }
    
    /**
     * @throws org.json.simple.parser.ParseException
     */
    @Test
    public void testJsonParserLeg() throws ParseException {
        // String en entrée
        String inJson = "{\"Id\":\"10223-1507031035-FR-0-13577-1507031210\",\"SegmentIds\":[1],\"OriginStation\":10223,\"DestinationStation\":13577,\"Departure\":\"2015-07-03T10:35:00\",\"Arrival\":\"2015-07-03T12:10:00\",\"Duration\":155,\"JourneyMode\":\"Flight\",\"Stops\":[],\"Carriers\":[1090],\"OperatingCarriers\":[1090],\"Directionality\":\"Outbound\",\"FlightNumbers\":[{\"FlightNumber\":\"1084\",\"CarrierId\":1090}]}";
       // Parcing Json
        JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(inJson);
        // Appel de la fonction pour enregister dans l'objet
        // Appel de la fonction pour enregister dans l'objet
        Leg leg = volParser.jsonParserLeg(jsonObject);
        
        // Vérification des valeurs
        Assert.assertEquals("Le ID enregistré est incorrect","10223-1507031035-FR-0-13577-1507031210", leg.getId());
        Assert.assertEquals("Le nombre de SegmentIds enregistré est incorrect",1 , leg.getSegmentId().length);
        Assert.assertEquals("Le OriginStation est incorrect",10223l, leg.getOriginStation());
        Assert.assertEquals("Le DestinationStation enregistré est incorrect",13577l, leg.getDestinationStation());
        Assert.assertEquals("Le Departure enregistré est incorrect","2015-07-03T10:35:00", leg.getDeparture());
        Assert.assertEquals("Le Arrival enregistré est incorrect","2015-07-03T12:10:00", leg.getArrival());
        Assert.assertEquals("Le Duration enregistré est incorrect",155l, leg.getDuration());
        Assert.assertEquals("Le JourneyMode enregistré est incorrect","Flight", leg.getJourneyMode());
        Assert.assertEquals("Le nombre de Stops enregistré est incorrect",0, leg.getStops().length);
        Assert.assertEquals("Le nombre de Carrier enregistré est incorrect",1, leg.getCarriers().length);
        Assert.assertEquals("Le nombre de OperatingCarrier enregistré est incorrect",1, leg.getOperatingCarriers().length);
        Assert.assertEquals("Le Directionality enregistré est incorrect","Outbound", leg.getDirectioality());
        Assert.assertEquals("Le nombre de FlightNumber enregistré est incorrect",1, leg.getFlightNumbers().size());
    }
    
     
}

