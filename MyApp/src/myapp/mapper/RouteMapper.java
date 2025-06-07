/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.route.RouteRequestDTO;
import myapp.dto.route.RouteResponseDTO;
import myapp.entity.AirportEntity;
import myapp.entity.RouteEntity;

import java.sql.SQLException;
import java.sql.ResultSet;
import myapp.util.query.ResultSetUtil;

/**
 *
 * @author HP
 */
public class RouteMapper {

    // DTO to entity conversion
    public static RouteEntity toSaveEntity(RouteRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("RouteRequestDTO must not be null");
        }

        RouteEntity entity = new RouteEntity();
        entity.setRoute_distance_km(dto.getRoute_distance_km());
        entity.setRoute_estimated_duration_minutes(dto.getRoute_estimated_duration_minutes());

        //FK Object
        entity.setOriginAirport(AirportMapper.toFkObjectEntity(dto.getRoute_origin_airport_id()));

        //FK Object
        entity.setDestinationAirport(AirportMapper.toFkObjectEntity(dto.getRoute_destination_airport_id()));

        return entity;
    }

    // DTO to entity conversion
    public static RouteEntity toUpdateEntity(Integer id, RouteRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("RouteRequestDTO must not be null");
        }

        RouteEntity entity = new RouteEntity();
        entity.setRoute_id(id);
        entity.setRoute_distance_km(dto.getRoute_distance_km());
        entity.setRoute_estimated_duration_minutes(dto.getRoute_estimated_duration_minutes());

        //FK Object
        entity.setOriginAirport(AirportMapper.toFkObjectEntity(dto.getRoute_origin_airport_id()));

        //FK Object
        entity.setDestinationAirport(AirportMapper.toFkObjectEntity(dto.getRoute_destination_airport_id()));

        return entity;
    }

    //DTO to entity conversion
    public static RouteEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        RouteEntity entity = new RouteEntity();
        entity.setRoute_id(id);

        return entity;
    }

    // Entity to DTO conversion
    public static RouteResponseDTO toDTO(RouteEntity entity) {
        RouteResponseDTO dto = new RouteResponseDTO();

        if (entity != null) {
            dto.setRoute_id(entity.getRoute_id());
            dto.setRoute_distance_km(entity.getRoute_distance_km());
            dto.setRoute_estimated_duration_minutes(entity.getRoute_estimated_duration_minutes());

            //Fk Object
            dto.setOriginAirport(AirportMapper.toDTO(entity.getOriginAirport()));
            dto.setDestinationAirport(AirportMapper.toDTO(entity.getDestinationAirport()));
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static RouteEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        RouteEntity entity = new RouteEntity();
        entity.setRoute_id(rs.getInt(prefix + "route_id")); //prfeix only for PK
        entity.setRoute_distance_km(rs.getBigDecimal("route_distance_km"));
        entity.setRoute_estimated_duration_minutes(rs.getInt("route_estimated_duration_minutes"));

        if (ResultSetUtil.hasColumn(rs, "route_origin_airport_id")) {
            //FK object
            AirportEntity originAirport = AirportMapper.toEntityFromResultSet(rs, "route_origin_", "origin_");
            entity.setOriginAirport(originAirport);
        }

        if (ResultSetUtil.hasColumn(rs, "route_destination_airport_id")) {
            //FK object
            AirportEntity destinationAirport = AirportMapper.toEntityFromResultSet(rs, "route_destination_", "destination_");
            entity.setDestinationAirport(destinationAirport);
        }

        return entity;
    }
}
