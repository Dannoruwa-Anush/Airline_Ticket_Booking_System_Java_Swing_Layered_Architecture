/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class RouteEntity {
    private int route_id;
    private AirportEntity originAirport; // Using object reference for FK
    private AirportEntity destinationAirport; // Using object reference for FK
    private BigDecimal route_distance_km;
    private int route_estimated_duration_minutes;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public RouteEntity() {
    }

    public RouteEntity(int route_id, AirportEntity originAirport, AirportEntity destinationAirport, BigDecimal route_distance_km, int route_estimated_duration_minutes) {
        setRoute_id(route_id);
        setOriginAirport(originAirport);
        setDestinationAirport(destinationAirport);
        setRoute_distance_km(route_distance_km);
        setRoute_estimated_duration_minutes(route_estimated_duration_minutes);
    }

    public int getRoute_id() {
        return route_id;
    }

    public AirportEntity getOriginAirport() {
        return originAirport;
    }

    public AirportEntity getDestinationAirport() {
        return destinationAirport;
    }

    public BigDecimal getRoute_distance_km() {
        return route_distance_km;
    }

    public int getRoute_estimated_duration_minutes() {
        return route_estimated_duration_minutes;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public void setOriginAirport(AirportEntity originAirport) {
        this.originAirport = originAirport;
    }

    public void setDestinationAirport(AirportEntity destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public void setRoute_distance_km(BigDecimal route_distance_km) {
        this.route_distance_km = route_distance_km;
    }

    public void setRoute_estimated_duration_minutes(int route_estimated_duration_minutes) {
        this.route_estimated_duration_minutes = route_estimated_duration_minutes;
    }

    @Override
    public String toString() {
        return "RouteEntity{" + "route_id=" + route_id + ", originAirport=" + originAirport + ", destinationAirport=" + destinationAirport + ", route_distance_km=" + route_distance_km + ", route_estimated_duration_minutes=" + route_estimated_duration_minutes + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
