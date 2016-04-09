/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import models.vols.Agent;
import models.vols.BookingDetailsLink;
import models.vols.Carrier;
import models.vols.Currency;
import models.vols.FlightNumber;
import models.vols.Itinerary;
import models.vols.Leg;
import models.vols.Place;
import models.vols.PricingOption;
import models.vols.Segment;
import models.vols.VolReponseSearch;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author arnaudpuigserver
 */
public class VolParser {
    
    public String jsonParserStatut(JSONObject jsonObject){
        return (String) jsonObject.get("Status");
    }
    
    public VolReponseSearch jsonParserReponseVol(JSONObject jSONObject){
        VolReponseSearch volReponseSearch = new VolReponseSearch();
        // AJout de l'attribut Itineraries
        List<Itinerary> itineraries = new ArrayList<>();
        JSONArray itinerariesArray = (JSONArray) jSONObject.get("Itineraries");
        Iterator i = itinerariesArray.iterator();
	while (i.hasNext()) {
            JSONObject itineraryObject = (JSONObject) i.next();
            Itinerary itinerary = jsonParserItinerary(itineraryObject);
            itineraries.add(itinerary);
        }
        volReponseSearch.setItineraries(itineraries);
        // Ajout de l'attribut Legs
        Map<String, Leg> legs = new HashMap<>();
        JSONArray legsArray = (JSONArray) jSONObject.get("Legs");
        i = legsArray.iterator();
	while (i.hasNext()) {
            JSONObject legObject = (JSONObject) i.next();
            Leg leg = jsonParserLeg(legObject);
            legs.put(leg.getId(), leg);
        }
        volReponseSearch.setLegs(legs);
        // Ajout de l'attribut Segments
        Map<Long, Segment> segments = new HashMap<>();
        JSONArray segmentsArray = (JSONArray) jSONObject.get("Segments");
        i = segmentsArray.iterator();
	while (i.hasNext()) {
            JSONObject segmentObject = (JSONObject) i.next();
            Segment segment = jsonParserSegment(segmentObject);
            segments.put(segment.getId(), segment);
        }
        volReponseSearch.setSegments(segments);
        // Ajout de l'attribut Carriers
        Map<Long, Carrier> carriers = new HashMap<>();
        JSONArray carriersArray = (JSONArray) jSONObject.get("Carriers");
        i = carriersArray.iterator();
	while (i.hasNext()) {
            JSONObject carrierObject = (JSONObject) i.next();
             Carrier carrier = jsonParserCarrier(carrierObject);
            carriers.put(carrier.getId(), carrier);
        }
        volReponseSearch.setCarriers(carriers);
        // Ajout de l'attribut Agents
        Map<Long, Agent> agents = new HashMap<>();
        JSONArray agentsArray = (JSONArray) jSONObject.get("Agents");
        i = agentsArray.iterator();
	while (i.hasNext()) {
            JSONObject agentObject = (JSONObject) i.next();
             Agent agent = jsonParserAgent(agentObject);
            agents.put(agent.getId(), agent);
        }
        volReponseSearch.setAgents(agents);
        // Ajout de l'attribut Places
        Map<Long, Place> places = new HashMap<>();
        JSONArray placesArray = (JSONArray) jSONObject.get("Places");
        i = placesArray.iterator();
	while (i.hasNext()) {
            JSONObject placeObject = (JSONObject) i.next();
            Place place = jsonParserPlace(placeObject);
            places.put(place.getId(), place);
        }
        volReponseSearch.setPlaces(places);
        // Ajout de l'attribut Currencies
        List<Currency> currencies = new ArrayList<>();
        JSONArray currenciesArray = (JSONArray) jSONObject.get("Currencies");
        i = currenciesArray.iterator();
	while (i.hasNext()) {
            JSONObject currencyObject = (JSONObject) i.next();
             Currency currency = jsonParserCurrency(currencyObject);
            currencies.add(currency);
        }
        volReponseSearch.setCurrencies(currencies);
        
        return volReponseSearch;
    }
    
    public Itinerary jsonParserItinerary(JSONObject jsonObject){
        Itinerary itinerary = new Itinerary();
        // Alimentation de l'attribu OutboundLegId
        String outboundLegId = (String) jsonObject.get("OutboundLegId");
        itinerary.setOutboundLegId(outboundLegId);
        // Alimentation de l'attribu InboundLegId
        String inboundLegId = (String) jsonObject.get("InboundLegId");
        itinerary.setInboundLegId(inboundLegId);
        // Alimentation de l'attribu PricingOptions
        JSONArray pricingOptionArray = (JSONArray) jsonObject.get("PricingOptions");
        List<PricingOption> pricingOptions = new ArrayList();
        Iterator i = pricingOptionArray.iterator();
	while (i.hasNext()) {
            JSONObject pricingOptionObject = (JSONObject) i.next();
            PricingOption pricingOption = jsonParserPrincingOption(pricingOptionObject);
            pricingOptions.add(pricingOption);
        }
        itinerary.setPricingOptions(pricingOptions);
        // Alimentation de l'attribu BookingDetailsLink
        JSONObject bookingDetailsLinkObject = (JSONObject) jsonObject.get("BookingDetailsLink");
        BookingDetailsLink bookingDetailsLink = jsonParserBookingDetailsLink(bookingDetailsLinkObject);
        itinerary.setBookingDetailsLink(bookingDetailsLink);
              
        return itinerary;
    }
    
    public PricingOption jsonParserPrincingOption(JSONObject jsonObject){
        PricingOption pricingOption = new PricingOption();
        // Alimentation de l'attribu Agents
        JSONArray agents= (JSONArray) jsonObject.get("Agents");
        long[] agentsList = new long[agents.size()];
        for(int i=0; i<agents.size(); i++){
            agentsList[i] = (long) agents.get(i);
        }
        pricingOption.setAgent(agentsList);
        // Alimentation de l'attribu Price
        double price = (double) jsonObject.get("Price");
        pricingOption.setPrice(price);
        // Alimentation de l'attribu QuoteAgeInMinutes
        long quote = (long) jsonObject.get("QuoteAgeInMinutes");
        pricingOption.setQuoteAgeInMinutes(quote);
        // Alimentaiton de l'attribu DeepLinkUrl
        String deepLink = (String) jsonObject.get("DeeplinkUrl");
        pricingOption.setDeeplinkUrl(deepLink);
              
        return pricingOption;
    }
    
    public BookingDetailsLink jsonParserBookingDetailsLink(JSONObject jsonObject){
        BookingDetailsLink bookingDetailsLink = new BookingDetailsLink();
        // Alimentation de l'attribu Uri
        String uri = (String) jsonObject.get("Uri");
        bookingDetailsLink.setUri(uri);
        // Alimentaiton de l'attribu Body
        String body = (String) jsonObject.get("Body");
        bookingDetailsLink.setBody(body);
        // Alimentation de l'attribu Method
        String method = (String) jsonObject.get("Method");
        bookingDetailsLink.setMethod(method);
        
        return bookingDetailsLink;
    }
    
    public Place jsonParserPlace(JSONObject jsonObject){
        Place place = new Place();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("Id");
        place.setId(id);
        // Alimentaiton de l'attribu ParentId
        if(jsonObject.get("ParentId") != null){
            long parentId = (long) jsonObject.get("ParentId");
            place.setParentId(parentId);
        }
        // Alimentation de l'attribu Code
        String code = (String) jsonObject.get("Code");
        place.setCode(code);
        // Alimentation de l'attribu Type
        String type = (String) jsonObject.get("Type");
        place.setType(type);
        // Alimentation de l'attribu Name
        String name = (String) jsonObject.get("Name");
        place.setName(name);
        
        return place;
    }
    
    public Agent jsonParserAgent(JSONObject jsonObject){
        Agent agent = new Agent();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("Id");
        agent.setId(id);
        // Alimentation de l'attribu Name
        String name = (String) jsonObject.get("Name");
        agent.setName(name);
        // Alimentation de l'attribu ImageUrl
        String imageUrl = (String) jsonObject.get("ImageUrl");
        agent.setImageUrl(imageUrl);
        // Alimentation de l'attribu Status
        String status = (String) jsonObject.get("Status");
        agent.setStatus(status);
        // Alimentation de l'attribu OptimisedForMobile
        boolean optimisedForMobile = (boolean) jsonObject.get("OptimisedForMobile");
        agent.setOptimiseForMobile(optimisedForMobile);
        // Alimentation de l'attribu BookingNumber
        String bookingNumber = (String) jsonObject.get("BookingNumber");
        agent.setBookingNumber(bookingNumber);
        // Alimentation de l'attribu Type
        String type = (String) jsonObject.get("Type");
        agent.setType(type);
        
        return agent;
    }
    
    public Currency jsonParserCurrency(JSONObject jsonObject){
        Currency currency = new Currency();
        // Alimentation de l'attribu Code
        String code = (String) jsonObject.get("Code");
        currency.setCode(code);
        // Alimentation de l'attribu Symbol
        String symbol = (String) jsonObject.get("Symbol");
        currency.setSymbol(symbol);
        // Alimentation de l'attribu ThousandSeparator
        String thousandSeparator = (String) jsonObject.get("ThousandsSeparator");
        currency.setThousandSeparator(thousandSeparator);
        // Alimentation de l'attribu DecimalSeparator
        String decimalSeparator = (String) jsonObject.get("DecimalSeparator");
        currency.setDecimalSeparator(decimalSeparator);
        // Alimentation de l'attribu SymbolOnLeft
        boolean symbolOnLeft = (boolean) jsonObject.get("SymbolOnLeft");
        currency.setSymbolOnLeft(symbolOnLeft);
        // Alimentation de l'attribu SpacceBetweenAmountAndSymbol
        boolean SpaceAmountSymbol = (boolean) jsonObject.get("SpaceBetweenAmountAndSymbol");
        currency.setSpaceBetweenAmountAndSymbol(SpaceAmountSymbol);
        // Alimentation de l'attribu RoundingCoefficient
        long roundingCoef = (long) jsonObject.get("RoundingCoefficient");
        currency.setRoundingCoeffcient(roundingCoef);
        // Alimentation de l'attribu DecimalDigits
        long decimalDigits = (long) jsonObject.get("DecimalDigits");
        currency.setDecimalDigits(decimalDigits);
        
        return currency;
    }
    
    public Carrier jsonParserCarrier(JSONObject jsonObject){
        Carrier carrier = new Carrier();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("Id");
        carrier.setId(id);
        // Alimentation de l'attribu Code
        String code = (String) jsonObject.get("Code");
        carrier.setCode(code);
        // Alimentation de l'attribu Name
        String name = (String) jsonObject.get("Name");
        carrier.setName(name);
        // Alimentation de l'attribu ImageUrl
        String imageUrl = (String) jsonObject.get("ImageUrl");
        carrier.setImageUrl(imageUrl);
        // Alimentation de l'attribu DisplayCode
        String displayCode = (String) jsonObject.get("DisplayCode");
        carrier.setDisplayCode(displayCode);
        
        return carrier;
    }
    
    public Segment jsonParserSegment(JSONObject jsonObject){
        Segment segment = new Segment();
        // Alimentation de l'attribu Id
        long id = (long) jsonObject.get("Id");
        segment.setId(id);
        // Alimentation de l'attribu OriginStation
        long originStation = (long) jsonObject.get("OriginStation");
        segment.setOriginStation(originStation);
        // Alimentation de l'attribu destinationStation
        long destinationStation = (long) jsonObject.get("DestinationStation");
        segment.setDestinationStation(destinationStation);
        // Alimentation de l'attribu DepartureDateTime
        String depertureDateTime = (String) jsonObject.get("DepartureDateTime");
        segment.setDepartureDatetime(depertureDateTime);
        // Alimentation de l'attribu arrivalDateTime
        String arrivalDateTime = (String) jsonObject.get("ArrivalDateTime");
        segment.setArrivalDateTime(arrivalDateTime);
        // Alimentation de l'attribu Carrier
        long carrier = (long) jsonObject.get("Carrier");
        segment.setCarrier(carrier);
        // Alimentation de l'attribu OperatingCarrier
        long operatingCarrier = (long) jsonObject.get("OperatingCarrier");
        segment.setOperatingCarrier(operatingCarrier);
        // Alimentation de l'attribu Duration
        long duration = (long) jsonObject.get("Duration");
        segment.setDuration(duration);
        // Alimentation de l'attribu FlightNumber
        String flightNumber = (String) jsonObject.get("FlightNumber");
        segment.setFlightNumber(flightNumber);
        // Alimentation de l'attribu JourneyMode
        String journeyMode = (String) jsonObject.get("JourneyMode");
        segment.setJourneyMode(journeyMode);
        // Alimentation de l'attribu Directionality
        String directionality = (String) jsonObject.get("Directionality");
        segment.setDirectionality(directionality);
        
        return segment;
    }
    
    public Leg jsonParserLeg(JSONObject jsonObject){
        Leg leg = new Leg();
        // Alimentation de l'attribu Id
        String id = (String) jsonObject.get("Id");
        leg.setId(id);
        // Alimentation de l'attribu SegmentIds
        JSONArray segmentIdsArray= (JSONArray) jsonObject.get("SegmentIds");
        long[] segmentIdsList = new long[segmentIdsArray.size()];
        for(int i=0; i<segmentIdsArray.size(); i++){
            segmentIdsList[i] = (long) segmentIdsArray.get(i);
        }
        leg.setSegmentId(segmentIdsList);
        // Alimentation de l'attribu OriginStation
        long originStation = (long) jsonObject.get("OriginStation");
        leg.setOriginStation(originStation);
        // Alimentation de l'attribu DestinationStation
        long destinationStation = (long) jsonObject.get("DestinationStation");
        leg.setDestinationStation(destinationStation);
        // Alimentation de l'attribu Departure
        String departure = (String) jsonObject.get("Departure");
        leg.setDeparture(departure);
        // Alimentation de l'attribu Arrival
        String arrival = (String) jsonObject.get("Arrival");
        leg.setArrival(arrival);
        // Alimentation de l'attribu Duration
        long duration = (long) jsonObject.get("Duration");
        leg.setDuration(duration);
        // Alimentation de l'attribu JourneyMode
        String journeyMode = (String) jsonObject.get("JourneyMode");
        leg.setJourneyMode(journeyMode);
        // Alimentation de l'attribu Stops
        JSONArray stopsArray= (JSONArray) jsonObject.get("Stops");
        long[] stopsList = new long[stopsArray.size()];
        for(int i=0; i<stopsArray.size(); i++){
            stopsList[i] = (long) stopsArray.get(i);
        }
        leg.setStops(stopsList);
        // Alimentation de l'attribu Carrier
        JSONArray carriersArray= (JSONArray) jsonObject.get("Carriers");
        long[] carriersList = new long[carriersArray.size()];
        for(int i=0; i<carriersArray.size(); i++){
            carriersList[i] = (long) carriersArray.get(i);
        }
        leg.setCarriers(carriersList);
        // Alimentation de l'attribu OperatingCarrier
        JSONArray operatingCarriersArray= (JSONArray) jsonObject.get("OperatingCarriers");
        long[] operatingCarriersList = new long[operatingCarriersArray.size()];
        for(int i=0; i<operatingCarriersArray.size(); i++){
            operatingCarriersList[i] = (long) operatingCarriersArray.get(i);
        }
        leg.setOperatingCarriers(operatingCarriersList);
        // Alimentation de l'attribu Directionality
        String directionality = (String) jsonObject.get("Directionality");
        leg.setDirectioality(directionality);
        // Alimentation de l'attribu FlightNumbers
        JSONArray flightNumbersArray = (JSONArray) jsonObject.get("FlightNumbers");
        List<FlightNumber> flightNumbers = new ArrayList();
        Iterator i = flightNumbersArray.iterator();
	while (i.hasNext()) {
            JSONObject flightNumberObject = (JSONObject) i.next();
            FlightNumber flightNumber = jsonParserfFlightNumber(flightNumberObject);
            flightNumbers.add(flightNumber);
        }
        leg.setFlightNumbers(flightNumbers);
        
        return leg;
    }
    
    public FlightNumber jsonParserfFlightNumber(JSONObject jsonObject){
        FlightNumber flightNumber = new FlightNumber();
        // Alimentation de l'attribu Uri
        String flightNumberParam = (String) jsonObject.get("FlightNumber");
        flightNumber.setFlightNunber(flightNumberParam);
        // Alimentaiton de l'attribu Body
        long carrierId = (long) jsonObject.get("CarrierId");
        flightNumber.setCarrierId(carrierId);
        
        return flightNumber;
    }
    
}
