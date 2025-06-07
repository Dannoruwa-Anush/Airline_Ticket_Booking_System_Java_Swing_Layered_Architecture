/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.route;

import java.math.BigDecimal;
import myapp.dto.airport.AirportResponseDTO;

/**
 *
 * @author HP
 */
public class RouteResponseDTO {

    private int route_id;
    private AirportResponseDTO originAirport; // Using object reference for FK
    private AirportResponseDTO destinationAirport; // Using object reference for FK
    private BigDecimal route_distance_km;
    private int route_estimated_duration_minutes;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public RouteResponseDTO() {
    }

    public RouteResponseDTO(int route_id, AirportResponseDTO originAirport, AirportResponseDTO destinationAirport, BigDecimal route_distance_km, int route_estimated_duration_minutes) {
        setRoute_id(route_id);
        setOriginAirport(originAirport);
        setDestinationAirport(destinationAirport);
        setRoute_distance_km(route_distance_km);
        setRoute_estimated_duration_minutes(route_estimated_duration_minutes);
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public AirportResponseDTO getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(AirportResponseDTO originAirport) {
        this.originAirport = originAirport;
    }

    public AirportResponseDTO getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(AirportResponseDTO destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public BigDecimal getRoute_distance_km() {
        return route_distance_km;
    }

    public void setRoute_distance_km(BigDecimal route_distance_km) {
        this.route_distance_km = route_distance_km;
    }

    public int getRoute_estimated_duration_minutes() {
        return route_estimated_duration_minutes;
    }

    public void setRoute_estimated_duration_minutes(int route_estimated_duration_minutes) {
        this.route_estimated_duration_minutes = route_estimated_duration_minutes;
    }

    @Override
    public String toString() {
        return "RouteResponseDTO{" + "route_id=" + route_id + ", originAirport=" + originAirport + ", destinationAirport=" + destinationAirport + ", route_distance_km=" + route_distance_km + ", route_estimated_duration_minutes=" + route_estimated_duration_minutes + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------

    
    //------- [Start : Helper method for combox item display] ------------------
    public String getRouteInfo() {
        String originName = (originAirport != null) ? originAirport.getAirport_name() : "Unknown";
        String destinationName = (destinationAirport != null) ? destinationAirport.getAirport_name() : "Unknown";
        return originName + " - " + destinationName;
    }
    //------- [End : Helper method for combox item display] --------------------
}
