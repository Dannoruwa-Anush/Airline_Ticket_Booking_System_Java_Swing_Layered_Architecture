/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.airport.AirportRequestDTO;
import myapp.dto.airport.AirportResponseDTO;
import myapp.entity.AirportEntity;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class AirportMapper {

    //DTO to entity conversion
    public static AirportEntity toSaveEntity(AirportRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("AirportRequestDTO must not be null");
        }

        AirportEntity entity = new AirportEntity();
        entity.setAirport_code(dto.getAirport_code());
        entity.setAirport_name(dto.getAirport_name());
        entity.setAirport_city(dto.getAirport_city());
        entity.setAirport_country(dto.getAirport_country());

        return entity;
    }

    //DTO to entity conversion
    public static AirportEntity toUpdateEntity(Integer id, AirportRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("Airport must not be null");
        }

        AirportEntity entity = new AirportEntity();
        entity.setAirport_id(id);
        entity.setAirport_code(dto.getAirport_code());
        entity.setAirport_name(dto.getAirport_name());
        entity.setAirport_city(dto.getAirport_city());
        entity.setAirport_country(dto.getAirport_country());

        return entity;
    }

    //DTO to entity conversion
    public static AirportEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        AirportEntity  entity = new AirportEntity ();
        entity.setAirport_id(id);

        return entity;
    }
    
    //Entity to DTO conversion
    public static AirportResponseDTO toDTO(AirportEntity entity) {   
        AirportResponseDTO dto = new AirportResponseDTO();
        
        if (entity != null){
            dto.setAirport_id(entity.getAirport_id());
            dto.setAirport_code(entity.getAirport_code());
            dto.setAirport_name(entity.getAirport_name());
            dto.setAirport_city(entity.getAirport_city());
            dto.setAirport_country(entity.getAirport_country());
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static AirportEntity toEntityFromResultSet(ResultSet rs, String prefix_FK, String prefixTrargetAirport) throws SQLException {
        AirportEntity entity = new AirportEntity();
        entity.setAirport_id(rs.getInt(prefix_FK + "airport_id")); //prfeix only for PK
        entity.setAirport_code(rs.getString(prefixTrargetAirport + "airport_code"));
        entity.setAirport_name(rs.getString(prefixTrargetAirport + "airport_name"));
        entity.setAirport_city(rs.getString(prefixTrargetAirport + "airport_city"));
        entity.setAirport_country(rs.getString(prefixTrargetAirport + "airport_country"));

        return entity;
    }
}
