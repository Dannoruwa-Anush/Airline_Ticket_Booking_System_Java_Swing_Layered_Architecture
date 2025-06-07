/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flight;

import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class FlightRequestDTO {

    private int flight_route_Id; //FK
    private int flight_schedule_Id; // Using object reference for FK
    private int flight_aircraftModel_id; //FK
    private String flight_code;
    private LocalDateTime flight_departure_time;
    private LocalDateTime flight_arrival_time;
    private String flight_airline_name;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightRequestDTO() {
    }

    public FlightRequestDTO(int flight_route_Id, int flight_schedule_Id, int flight_aircraftModel_id, String flight_code, LocalDateTime flight_departure_time, LocalDateTime flight_arrival_time, String flight_airline_name) {
        setFlight_route_Id(flight_route_Id);
        setFlight_schedule_Id(flight_schedule_Id);
        setFlight_aircraftModel_id(flight_aircraftModel_id);
        setFlight_code(flight_code);
        setFlight_departure_time(flight_departure_time);
        setFlight_arrival_time(flight_arrival_time);
        setFlight_airline_name(flight_airline_name);
    }

    public int getFlight_route_Id() {
        return flight_route_Id;
    }

    public int getFlight_schedule_Id() {
        return flight_schedule_Id;
    }

    public int getFlight_aircraftModel_id() {
        return flight_aircraftModel_id;
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

    public void setFlight_schedule_Id(int flight_schedule_Id) {
        if (flight_schedule_Id <= 0) {
            throw new IllegalArgumentException("Flight shedule id must be a positive integer.");
        }

        this.flight_schedule_Id = flight_schedule_Id;
    }

    public void setFlight_route_Id(int flight_route_Id) {
        if (flight_route_Id <= 0) {
            throw new IllegalArgumentException("Flight route id must be a positive integer.");
        }
        this.flight_route_Id = flight_route_Id;
    }

    public void setFlight_aircraftModel_id(int flight_aircraftModel_id) {
        if (flight_aircraftModel_id <= 0) {
            throw new IllegalArgumentException("Flight aircraft model id must be a positive integer.");
        }
        this.flight_aircraftModel_id = flight_aircraftModel_id;
    }

    public void setFlight_code(String flight_code) {
        if (flight_code == null || flight_code.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight code cannot be null or empty.");
        }
        this.flight_code = flight_code.trim();
    }

    public void setFlight_departure_time(LocalDateTime flight_departure_time) {
        if (flight_departure_time == null) {
            throw new IllegalArgumentException("Flight departure time cannot be null.");
        }
        this.flight_departure_time = flight_departure_time;

        // If arrival is already set, ensure it's after departure
        if (this.flight_arrival_time != null && flight_departure_time.isAfter(this.flight_arrival_time)) {
            throw new IllegalArgumentException("Flight departure time must be before arrival time.");
        }
    }

    public void setFlight_arrival_time(LocalDateTime flight_arrival_time) {
        if (flight_arrival_time == null) {
            throw new IllegalArgumentException("Flight arrival time cannot be null.");
        }
        this.flight_arrival_time = flight_arrival_time;

        // If departure is already set, ensure it's before arrival
        if (this.flight_departure_time != null && flight_arrival_time.isBefore(this.flight_departure_time)) {
            throw new IllegalArgumentException("Flight arrival time must be after departure time.");
        }
    }

    public void setFlight_airline_name(String flight_airline_name) {
        if (flight_airline_name == null || flight_airline_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight airline name cannot be null or empty.");
        }
        this.flight_airline_name = flight_airline_name.trim();
    }

    @Override
    public String toString() {
        return "FlightRequestDTO{" + "flight_route_Id=" + flight_route_Id + ", flight_aircraftModel_id=" + flight_aircraftModel_id + ", flight_code=" + flight_code + ", flight_departure_time=" + flight_departure_time + ", flight_arrival_time=" + flight_arrival_time + ", flight_airline_name=" + flight_airline_name + '}';
    }

    //------- [End : constructor, getter(), setter(), toString()]-------
}
