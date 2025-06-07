/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.route;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class RouteRequestDTO {
 
    private int route_origin_airport_id; //FK
    private int route_destination_airport_id; //FK
    private BigDecimal route_distance_km;
    private int route_estimated_duration_minutes;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public RouteRequestDTO() {
    }

    public RouteRequestDTO(int route_origin_airport_id, int route_destination_airport_id, BigDecimal route_distance_km, int route_estimated_duration_minutes) {
        setRoute_origin_airport_id(route_origin_airport_id);
        setRoute_destination_airport_id(route_destination_airport_id);
        setRoute_distance_km(route_distance_km);
        setRoute_estimated_duration_minutes(route_estimated_duration_minutes);
    }

    public int getRoute_origin_airport_id() {
        return route_origin_airport_id;
    }

    public int getRoute_destination_airport_id() {
        return route_destination_airport_id;
    }

    public BigDecimal getRoute_distance_km() {
        return route_distance_km;
    }

    public int getRoute_estimated_duration_minutes() {
        return route_estimated_duration_minutes;
    }

    public void setRoute_origin_airport_id(int route_origin_airport_id) {
        if(route_origin_airport_id <=0){
            throw new IllegalArgumentException("Route origin airport ID must be a positive integer");
        }
        this.route_origin_airport_id = route_origin_airport_id;
    }

    public void setRoute_destination_airport_id(int route_destination_airport_id) {
        if(route_destination_airport_id <=0){
            throw new IllegalArgumentException("Destination airport ID must be a positive integer");
        }
        this.route_destination_airport_id = route_destination_airport_id;
    }

    public void setRoute_distance_km(BigDecimal route_distance_km) {
        if (route_distance_km == null) {
            throw new IllegalArgumentException("Route distance cannot be null.");
        }
        
        if (route_distance_km.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Route distance must be a positive value.");
        }
        this.route_distance_km = route_distance_km;
    }

    public void setRoute_estimated_duration_minutes(int route_estimated_duration_minutes) {
        if(route_estimated_duration_minutes <=0){
            throw new IllegalArgumentException("Estimated duration must be a positive integer");
        }
        this.route_estimated_duration_minutes = route_estimated_duration_minutes;
    }

    @Override
    public String toString() {
        return "RouteRequestDTO{" + "route_origin_airport_id=" + route_origin_airport_id + ", route_destination_airport_id=" + route_destination_airport_id + ", route_distance_km=" + route_distance_km + ", route_estimated_duration_minutes=" + route_estimated_duration_minutes + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
