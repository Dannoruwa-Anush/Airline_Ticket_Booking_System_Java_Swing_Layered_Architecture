/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flightSchedule;

import java.time.LocalTime;
import java.util.Set;

/**
 *
 * @author HP
 */
public class FlightScheduleRequestDTO {

    private int schedule_route_id; //FK
    private int schedule_aircraft_model_id; //FK
    private String schedule_flight_code;
    private String schedule_airline_name;
    private LocalTime schedule_departure_time;
    private LocalTime schedule_arrival_time;
    private Set<String> schedule_days_of_week;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightScheduleRequestDTO() {
    }

    public FlightScheduleRequestDTO(int schedule_route_id, int schedule_aircraft_model_id, String schedule_flight_code, String schedule_airline_name, LocalTime schedule_departure_time, LocalTime schedule_arrival_time, Set<String> schedule_days_of_week) {
        setSchedule_route_id(schedule_route_id);
        setSchedule_aircraft_model_id(schedule_aircraft_model_id);
        setSchedule_flight_code(schedule_flight_code);
        setSchedule_airline_name(schedule_airline_name);
        setSchedule_departure_time(schedule_departure_time);
        setSchedule_arrival_time(schedule_arrival_time);
        setSchedule_days_of_week(schedule_days_of_week);
    }

    public int getSchedule_route_id() {
        return schedule_route_id;
    }

    public int getSchedule_aircraft_model_id() {
        return schedule_aircraft_model_id;
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

    public void setSchedule_route_id(int schedule_route_id) {
        if (schedule_route_id <= 0) {
            throw new IllegalArgumentException("Schedule route ID must be a positive integer.");
        }
        this.schedule_route_id = schedule_route_id;
    }

    public void setSchedule_aircraft_model_id(int schedule_aircraft_model_id) {
        if (schedule_aircraft_model_id <= 0) {
            throw new IllegalArgumentException("Schedule aircraft model ID must be a positive integer.");
        }
        this.schedule_aircraft_model_id = schedule_aircraft_model_id;
    }

    public void setSchedule_flight_code(String schedule_flight_code) {
        if (schedule_flight_code == null || schedule_flight_code.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule flight code must not be null or empty.");
        }
        this.schedule_flight_code = schedule_flight_code;
    }

    public void setSchedule_airline_name(String schedule_airline_name) {
        if (schedule_airline_name == null || schedule_airline_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule airline name must not be null or empty.");
        }
        this.schedule_airline_name = schedule_airline_name;
    }

    public void setSchedule_departure_time(LocalTime schedule_departure_time) {
        if (schedule_departure_time == null) {
            throw new IllegalArgumentException("Schedule departure time must not be null.");
        }
        this.schedule_departure_time = schedule_departure_time;
    }

    public void setSchedule_arrival_time(LocalTime schedule_arrival_time) {
        if (schedule_arrival_time == null) {
            throw new IllegalArgumentException("Schedule arrival time must not be null.");
        }
        this.schedule_arrival_time = schedule_arrival_time;
    }

    public void setSchedule_days_of_week(Set<String> schedule_days_of_week) {
        if (schedule_days_of_week == null || schedule_days_of_week.isEmpty()) {
            throw new IllegalArgumentException("Schedule days of week must not be null or empty.");
        }
        for (String day : schedule_days_of_week) {
            if (day == null || day.trim().isEmpty()) {
                throw new IllegalArgumentException("Schedule days of week must not contain null or empty strings.");
            }
        }
        this.schedule_days_of_week = schedule_days_of_week;
    }

    @Override
    public String toString() {
        return "FlightScheduleRequestDTO{" + "schedule_route_id=" + schedule_route_id + ", schedule_aircraft_model_id=" + schedule_aircraft_model_id + ", schedule_flight_code=" + schedule_flight_code + ", schedule_airline_name=" + schedule_airline_name + ", schedule_departure_time=" + schedule_departure_time + ", schedule_arrival_time=" + schedule_arrival_time + ", schedule_days_of_week=" + schedule_days_of_week + '}';
    }

    //------- [End : constructor, getter(), setter(), toString()]-------
}
