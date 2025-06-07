/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import myapp.dto.flightSchedule.FlightScheduleRequestDTO;
import myapp.dto.flightSchedule.FlightScheduleResponseDTO;
import myapp.entity.AircraftModelEntity;
import myapp.entity.FlightScheduleEntity;
import myapp.entity.RouteEntity;
import myapp.util.query.ResultSetUtil;

/**
 *
 * @author HP
 */
public class FlightScheduleMapper {

    // DTO to Entity
    public static FlightScheduleEntity toSaveEntity(FlightScheduleRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("FlightScheduleRequestDTO must not be null");
        }

        FlightScheduleEntity entity = new FlightScheduleEntity();
        entity.setSchedule_flight_code(dto.getSchedule_flight_code());
        entity.setSchedule_airline_name(dto.getSchedule_airline_name());
        entity.setSchedule_departure_time(dto.getSchedule_departure_time());
        entity.setSchedule_arrival_time(dto.getSchedule_arrival_time());
        entity.setSchedule_days_of_week(dto.getSchedule_days_of_week());

        //Fk Object
        entity.setRoute(RouteMapper.toFkObjectEntity(dto.getSchedule_route_id()));

        //Fk Object
        entity.setAircraftModel(AircraftModelMapper.toFkObjectEntity(dto.getSchedule_aircraft_model_id()));

        return entity;
    }

    //DTO to entity conversion
    public static FlightScheduleEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        FlightScheduleEntity entity = new FlightScheduleEntity();
        entity.setSchedule_id(id);

        return entity;
    }

    // DTO to Entity
    public static FlightScheduleEntity toUpdateEntity(Integer id, FlightScheduleRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("FlightScheduleRequestDTO must not be null");
        }

        FlightScheduleEntity entity = new FlightScheduleEntity();
        entity.setSchedule_id(id);
        entity.setSchedule_flight_code(dto.getSchedule_flight_code());
        entity.setSchedule_airline_name(dto.getSchedule_airline_name());
        entity.setSchedule_departure_time(dto.getSchedule_departure_time());
        entity.setSchedule_arrival_time(dto.getSchedule_arrival_time());
        entity.setSchedule_days_of_week(dto.getSchedule_days_of_week());

        //Fk Object
        entity.setRoute(RouteMapper.toFkObjectEntity(dto.getSchedule_route_id()));

        //Fk Object
        entity.setAircraftModel(AircraftModelMapper.toFkObjectEntity(dto.getSchedule_aircraft_model_id()));

        return entity;
    }

    // Entity to DTO
    public static FlightScheduleResponseDTO toDTO(FlightScheduleEntity entity) {
        FlightScheduleResponseDTO dto = new FlightScheduleResponseDTO();

        if (entity != null) {
            dto.setSchedule_id(entity.getSchedule_id());
            dto.setSchedule_flight_code(entity.getSchedule_flight_code());
            dto.setSchedule_airline_name(entity.getSchedule_airline_name());
            dto.setSchedule_departure_time(entity.getSchedule_departure_time());
            dto.setSchedule_arrival_time(entity.getSchedule_arrival_time());
            dto.setSchedule_days_of_week(entity.getSchedule_days_of_week());

            //FK Object
            dto.setRoute(RouteMapper.toDTO(entity.getRoute()));

            //FK Object
            dto.setAircraftModel(AircraftModelMapper.toDTO(entity.getAircraftModel()));
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static FlightScheduleEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        FlightScheduleEntity entity = new FlightScheduleEntity();
        entity.setSchedule_id(rs.getInt(prefix + "schedule_id")); //prfeix only for PK);
        entity.setSchedule_flight_code(rs.getString("schedule_flight_code"));
        entity.setSchedule_airline_name(rs.getString("schedule_airline_name"));

        // Mapping SQL TIME to Java LocalTime
        Time depTime = rs.getTime("schedule_departure_time");
        if (depTime != null) {
            entity.setSchedule_departure_time(depTime.toLocalTime());
        }

        Time arrTime = rs.getTime("schedule_arrival_time");
        if (arrTime != null) {
            entity.setSchedule_arrival_time(arrTime.toLocalTime());
        }

        // Mapping SQL SET to Java Set<String>
        String daysOfWeekStr = rs.getString("schedule_days_of_week"); // e.g., "Mon,Tue,Wed"
        if (daysOfWeekStr != null && !daysOfWeekStr.isEmpty()) {
            Set<String> daysSet = new HashSet<>(Arrays.asList(daysOfWeekStr.split(",")));
            entity.setSchedule_days_of_week(daysSet);
        }

        if (ResultSetUtil.hasColumn(rs, "schedule_route_id")) {
            //FK object
            RouteEntity route = RouteMapper.toEntityFromResultSet(rs, "schedule_");
            entity.setRoute(route);
        }

        if (ResultSetUtil.hasColumn(rs, "schedule_aircraft_model_id")) {
            //FK object
            AircraftModelEntity aircraftModel = AircraftModelMapper.toEntityFromResultSet(rs, "schedule_");
            entity.setAircraftModel(aircraftModel);
        }
        
        return entity;
    }
}
