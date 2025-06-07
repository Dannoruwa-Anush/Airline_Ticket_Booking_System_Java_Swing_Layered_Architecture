/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.flight.FlightRequestDTO;
import myapp.dto.flight.FlightResponseDTO;
import myapp.entity.FlightEntity;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import myapp.entity.AircraftModelEntity;
import myapp.entity.FlightScheduleEntity;
import myapp.entity.RouteEntity;
import myapp.util.query.ResultSetUtil;

/**
 *
 * @author HP
 */
public class FlightMapper {

    // DTO to Entity
    public static FlightEntity toSaveEntity(FlightRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("FlightRequestDTO must not be null");
        }

        FlightEntity entity = new FlightEntity();
        entity.setFlight_code(dto.getFlight_code());
        entity.setFlight_departure_time(dto.getFlight_departure_time());
        entity.setFlight_arrival_time(dto.getFlight_arrival_time());
        entity.setFlight_airline_name(dto.getFlight_airline_name());

        //FK Object
        entity.setFlightSchedule(FlightScheduleMapper.toFkObjectEntity(dto.getFlight_schedule_Id()));

        //Fk Object
        entity.setRoute(RouteMapper.toFkObjectEntity(dto.getFlight_route_Id()));

        //Fk Object
        entity.setAircraftModel(AircraftModelMapper.toFkObjectEntity(dto.getFlight_aircraftModel_id()));

        return entity;
    }

    //DTO to entity conversion
    public static FlightEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        FlightEntity entity = new FlightEntity();
        entity.setFlight_id(id);

        return entity;
    }

    // DTO to Entity
    public static FlightEntity toUpdateEntity(Integer id, FlightRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("FlightRequestDTO must not be null");
        }

        FlightEntity entity = new FlightEntity();
        entity.setFlight_id(id);
        entity.setFlight_code(dto.getFlight_code());
        entity.setFlight_departure_time(dto.getFlight_departure_time());
        entity.setFlight_arrival_time(dto.getFlight_arrival_time());
        entity.setFlight_airline_name(dto.getFlight_airline_name());

        //Fk Object
        entity.setFlightSchedule(FlightScheduleMapper.toFkObjectEntity(dto.getFlight_schedule_Id()));

        //Fk Object
        entity.setRoute(RouteMapper.toFkObjectEntity(dto.getFlight_route_Id()));

        //Fk Object
        entity.setAircraftModel(AircraftModelMapper.toFkObjectEntity(dto.getFlight_aircraftModel_id()));

        return entity;
    }

    // Entity to DTO
    public static FlightResponseDTO toDTO(FlightEntity entity) {
        FlightResponseDTO dto = new FlightResponseDTO();

        if (entity != null) {
            dto.setFlight_id(entity.getFlight_id());
            dto.setFlight_code(entity.getFlight_code());
            dto.setFlight_departure_time(entity.getFlight_departure_time());
            dto.setFlight_arrival_time(entity.getFlight_arrival_time());
            dto.setFlight_airline_name(entity.getFlight_airline_name());

            //FK Object
            dto.setFlightSchedule(FlightScheduleMapper.toDTO(entity.getFlightSchedule()));

            //FK Object
            dto.setRoute(RouteMapper.toDTO(entity.getRoute()));

            //FK Object
            dto.setAircraftModel(AircraftModelMapper.toDTO(entity.getAircraftModel()));
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static FlightEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        FlightEntity entity = new FlightEntity();
        entity.setFlight_id(rs.getInt(prefix + "flight_id")); //prfeix only for PK
        entity.setFlight_code(rs.getString("flight_code"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime departureTime = LocalDateTime.parse(rs.getString("flight_departure_time"), formatter);
        LocalDateTime arrivalTime = LocalDateTime.parse(rs.getString("flight_arrival_time"), formatter);

        entity.setFlight_departure_time(departureTime);
        entity.setFlight_arrival_time(arrivalTime);

        entity.setFlight_airline_name(rs.getString("flight_airline_name"));

//        if (ResultSetUtil.hasColumn(rs, "flight_schedule_id")) {
//            //FK object
//            FlightScheduleEntity flightSchedule = FlightScheduleMapper.toEntityFromResultSet(rs, "flight_");
//            entity.setFlightSchedule(flightSchedule);
//        }

        if (ResultSetUtil.hasColumn(rs, "flight_route_id")) {
            //FK object
            RouteEntity route = RouteMapper.toEntityFromResultSet(rs, "flight_");
            entity.setRoute(route);
        }

        if (ResultSetUtil.hasColumn(rs, "flight_aircraft_model_id")) {
            //FK object
            AircraftModelEntity aircraftModel = AircraftModelMapper.toEntityFromResultSet(rs, "flight_");
            entity.setAircraftModel(aircraftModel);
        }
        return entity;
    }
}
