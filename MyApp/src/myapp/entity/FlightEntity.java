/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class FlightEntity {

    private int flight_id;
    private FlightScheduleEntity flightSchedule; // Using object reference for FK
    private RouteEntity route; // Using object reference for FK
    private AircraftModelEntity aircraftModel; // Using object reference for FK
    private String flight_code;
    private LocalDateTime flight_departure_time;
    private LocalDateTime flight_arrival_time;
    private String flight_airline_name;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightEntity() {
    }

    public FlightEntity(int flight_id, FlightScheduleEntity flightSchedule, RouteEntity route, AircraftModelEntity aircraftModel, String flight_code, LocalDateTime flight_departure_time, LocalDateTime flight_arrival_time, String flight_airline_name) {
        setFlight_id(flight_id);
        setFlightSchedule(flightSchedule);
        setRoute(route);
        setAircraftModel(aircraftModel);
        setFlight_code(flight_code);
        setFlight_departure_time(flight_departure_time);
        setFlight_arrival_time(flight_arrival_time);
        setFlight_airline_name(flight_airline_name);
    }

    public int getFlight_id() {
        return flight_id;
    }

    public FlightScheduleEntity getFlightSchedule() {
        return flightSchedule;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public AircraftModelEntity getAircraftModel() {
        return aircraftModel;
    }

    public String getFlight_code() {
        return flight_code;
    }

    public LocalDateTime getFlight_departure_time() {
        return flight_departure_time;
    }

    public LocalDateTime getFlight_arrival_time() {
        return flight_arrival_time;
    }

    public String getFlight_airline_name() {
        return flight_airline_name;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public void setFlightSchedule(FlightScheduleEntity flightSchedule) {
        this.flightSchedule = flightSchedule;
    }
    
    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public void setAircraftModel(AircraftModelEntity aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public void setFlight_code(String flight_code) {
        this.flight_code = flight_code;
    }

    public void setFlight_departure_time(LocalDateTime flight_departure_time) {
        this.flight_departure_time = flight_departure_time;
    }

    public void setFlight_arrival_time(LocalDateTime flight_arrival_time) {
        this.flight_arrival_time = flight_arrival_time;
    }

    public void setFlight_airline_name(String flight_airline_name) {
        this.flight_airline_name = flight_airline_name;
    }

    @Override
    public String toString() {
        return "FlightEntity{" + "flight_id=" + flight_id + ", flightSchedule=" + flightSchedule + ", route=" + route + ", aircraftModel=" + aircraftModel + ", flight_code=" + flight_code + ", flight_departure_time=" + flight_departure_time + ", flight_arrival_time=" + flight_arrival_time + ", flight_airline_name=" + flight_airline_name + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
