/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flightSchedule;

import java.time.LocalTime;
import java.util.Set;
import myapp.dto.aircraftModel.AircraftModelResponseDTO;
import myapp.dto.route.RouteResponseDTO;

/**
 *
 * @author HP
 */
public class FlightScheduleResponseDTO {

    private int schedule_id;
    private RouteResponseDTO route; // Using object reference for FK
    private AircraftModelResponseDTO aircraftModel; // Using object reference for FK
    private String schedule_flight_code;
    private String schedule_airline_name;
    private LocalTime schedule_departure_time;
    private LocalTime schedule_arrival_time;
    private Set<String> schedule_days_of_week;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightScheduleResponseDTO() {
    }

    public FlightScheduleResponseDTO(int schedule_id, RouteResponseDTO route, AircraftModelResponseDTO aircraftModel, String schedule_flight_code, String schedule_airline_name, LocalTime schedule_departure_time, LocalTime schedule_arrival_time, Set<String> schedule_days_of_week) {
        setSchedule_id(schedule_id);
        setRoute(route);
        setAircraftModel(aircraftModel);
        setSchedule_flight_code(schedule_flight_code);
        setSchedule_airline_name(schedule_airline_name);
        setSchedule_departure_time(schedule_departure_time);
        setSchedule_arrival_time(schedule_arrival_time);
        setSchedule_days_of_week(schedule_days_of_week);
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public RouteResponseDTO getRoute() {
        return route;
    }

    public AircraftModelResponseDTO getAircraftModel() {
        return aircraftModel;
    }

    public String getSchedule_flight_code() {
        return schedule_flight_code;
    }

    public String getSchedule_airline_name() {
        return schedule_airline_name;
    }

    public LocalTime getSchedule_departure_time() {
        return schedule_departure_time;
    }

    public LocalTime getSchedule_arrival_time() {
        return schedule_arrival_time;
    }

    public Set<String> getSchedule_days_of_week() {
        return schedule_days_of_week;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public void setRoute(RouteResponseDTO route) {
        this.route = route;
    }

    public void setAircraftModel(AircraftModelResponseDTO aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public void setSchedule_flight_code(String schedule_flight_code) {
        this.schedule_flight_code = schedule_flight_code;
    }

    public void setSchedule_airline_name(String schedule_airline_name) {
        this.schedule_airline_name = schedule_airline_name;
    }

    public void setSchedule_departure_time(LocalTime schedule_departure_time) {
        this.schedule_departure_time = schedule_departure_time;
    }

    public void setSchedule_arrival_time(LocalTime schedule_arrival_time) {
        this.schedule_arrival_time = schedule_arrival_time;
    }

    public void setSchedule_days_of_week(Set<String> schedule_days_of_week) {
        this.schedule_days_of_week = schedule_days_of_week;
    }

    @Override
    public String toString() {
        return "FlightScheduleResponseDTO{" + "schedule_id=" + schedule_id + ", route=" + route + ", aircraftModel=" + aircraftModel + ", schedule_flight_code=" + schedule_flight_code + ", schedule_airline_name=" + schedule_airline_name + ", schedule_departure_time=" + schedule_departure_time + ", schedule_arrival_time=" + schedule_arrival_time + ", schedule_days_of_week=" + schedule_days_of_week + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
