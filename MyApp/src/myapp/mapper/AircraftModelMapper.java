/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.aircraftModel.AircraftModelRequestDTO;
import myapp.dto.aircraftModel.AircraftModelResponseDTO;
import myapp.entity.AircraftModelEntity;

import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
public class AircraftModelMapper {
    // DTO to entity conversion
    public static AircraftModelEntity toSaveEntity(AircraftModelRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("AircraftModelRequestDTO must not be null");
        }

        AircraftModelEntity entity = new AircraftModelEntity();
        entity.setAircraftModel_Manufacturer(dto.getAircraftModel_Manufacturer());
        entity.setAircraftModel_Name(dto.getAircraftModel_Name());
        entity.setAircraftModel_SeatCapacity(dto.getAircraftModel_SeatCapacity());
        entity.setAircraftModel_Range_KM(dto.getAircraftModel_Range_KM());
        entity.setAircraftModel_SizeCategory(dto.getAircraftModel_SizeCategory());
        
        return entity;
    }

    // DTO to entity conversion
    public static AircraftModelEntity toUpdateEntity(Integer id, AircraftModelRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        
        if (dto == null) {
            throw new IllegalArgumentException("AircraftModelRequestDTO must not be null");
        }

        AircraftModelEntity entity = new AircraftModelEntity();
        entity.setAircraftModel_id(id);
        entity.setAircraftModel_Manufacturer(dto.getAircraftModel_Manufacturer());
        entity.setAircraftModel_Name(dto.getAircraftModel_Name());
        entity.setAircraftModel_SeatCapacity(dto.getAircraftModel_SeatCapacity());
        entity.setAircraftModel_Range_KM(dto.getAircraftModel_Range_KM());
        entity.setAircraftModel_SizeCategory(dto.getAircraftModel_SizeCategory());
        
        return entity;
    }
    
    // DTO to entity conversion
    public static AircraftModelEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        AircraftModelEntity entity = new AircraftModelEntity();
        entity.setAircraftModel_id(id);

        return entity;
    }
    
    // Entity to DTO conversion
    public static AircraftModelResponseDTO toDTO(AircraftModelEntity entity) {
        AircraftModelResponseDTO dto = new AircraftModelResponseDTO();

        if(entity != null){
            dto.setAircraftModel_id(entity.getAircraftModel_id());
            dto.setAircraftModel_Manufacturer(entity.getAircraftModel_Manufacturer());
            dto.setAircraftModel_Name(entity.getAircraftModel_Name());
            dto.setAircraftModel_SeatCapacity(entity.getAircraftModel_SeatCapacity());
            dto.setAircraftModel_Range_KM(entity.getAircraftModel_Range_KM());
            dto.setAircraftModel_SizeCategory(entity.getAircraftModel_SizeCategory());        
        }

        return dto;
    }
    
    //SQL ResultSet to Entity
    public static AircraftModelEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        AircraftModelEntity entity = new AircraftModelEntity();
        entity.setAircraftModel_id(rs.getInt(prefix + "aircraft_model_id")); //prfeix only for PK
        entity.setAircraftModel_Manufacturer(rs.getString("aircraft_model_manufacturer"));
        entity.setAircraftModel_Name(rs.getString("aircraft_model_name"));
        entity.setAircraftModel_SeatCapacity(rs.getInt("aircraft_model_seating_capacity"));
        entity.setAircraftModel_SizeCategory(rs.getString("aircraft_model_size_category"));
        entity.setAircraftModel_Range_KM(rs.getBigDecimal("aircraft_model_range_km"));
        
        return entity;
    }
}
