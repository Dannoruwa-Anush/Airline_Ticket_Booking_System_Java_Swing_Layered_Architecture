/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flight;

import java.time.LocalDateTime;
import myapp.dto.aircraftModel.AircraftModelResponseDTO;
import myapp.dto.flightSchedule.FlightScheduleResponseDTO;
import myapp.dto.route.RouteResponseDTO;

/**
 *
 * @author HP
 */
public class FlightResponseDTO {
    private int flight_id;
    private FlightScheduleResponseDTO flightSchedule; // Using object reference for FK
    private RouteResponseDTO route; // Using object reference for FK
    private AircraftModelResponseDTO aircraftModel; // Using object reference for FK
    private String flight_code;
    private LocalDateTime flight_departure_time;
    private LocalDateTime flight_arrival_time;
    private String flight_airline_name;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightResponseDTO() {
    }

    public FlightResponseDTO(int flight_id, FlightScheduleResponseDTO flightSchedule, RouteResponseDTO route, AircraftModelResponseDTO aircraftModel, String flight_code, LocalDateTime flight_departure_time, LocalDateTime flight_arrival_time, String flight_airline_name) {
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

    public FlightScheduleResponseDTO getFlightSchedule() {
        return flightSchedule;
    }

    public RouteResponseDTO getRoute() {
        return route;
    }

    public AircraftModelResponseDTO getAircraftModel() {
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

    public void setFlightSchedule(FlightScheduleResponseDTO flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    public void setRoute(RouteResponseDTO route) {
        this.route = route;
    }

    public void setAircraftModel(AircraftModelResponseDTO aircraftModel) {
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
        return "FlightResponseDTO{" + "flight_id=" + flight_id + ", flightSchedule=" + flightSchedule + ", route=" + route + ", aircraftModel=" + aircraftModel + ", flight_code=" + flight_code + ", flight_departure_time=" + flight_departure_time + ", flight_arrival_time=" + flight_arrival_time + ", flight_airline_name=" + flight_airline_name + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
    
    //------- [Start : Helper method for combox item display] ------------------
    //Helper method for combox item display
    public String getFlightInfo() {
        String code = (flight_code != null) ? flight_code : "Unknown";
        String airline = (flight_airline_name != null) ? flight_airline_name : "Unknown";
        return code + " - " + airline;
    }
    //------- [End : Helper method for combox item display] --------------------
}
