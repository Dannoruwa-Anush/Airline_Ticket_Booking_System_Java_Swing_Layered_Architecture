/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.passengerProfile.PassengerProfileRequestDTO;
import myapp.dto.passengerProfile.PassengerProfileResponseDTO;
import myapp.entity.PassengerProfileEntity;

import java.sql.SQLException;
import java.sql.ResultSet;
import myapp.entity.UserEntity;
import myapp.util.query.ResultSetUtil;

/**
 *
 * @author HP
 */
public class PassengerProfileMapper {

    //DTO to entity conversion
    public static PassengerProfileEntity toSaveEntity(PassengerProfileRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("PassengerProfileRequestDTO must not be null");
        }

        PassengerProfileEntity entity = new PassengerProfileEntity();
        entity.setPassengerFullName(dto.getPassengerFullName());
        entity.setPassengerPassportNumber(dto.getPassengerPassportNumber());
        entity.setPassengerNationality(dto.getPassengerNationality());
        entity.setPassengerContactNumber(dto.getPassengerContactNumber());
        entity.setPassengerEmergencyContactNumber(dto.getPassengerEmergencyContactNumber());

        //FK Object
        entity.setUser(UserMapper.toFkObjectEntity(dto.getUserId()));

        return entity;
    }

    public static PassengerProfileEntity toUpdateEntity(Integer id, PassengerProfileRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("PassengerProfileRequestDTO must not be null");
        }

        PassengerProfileEntity entity = new PassengerProfileEntity();
        entity.setPassengerId(id);
        entity.setPassengerFullName(dto.getPassengerFullName());
        entity.setPassengerPassportNumber(dto.getPassengerPassportNumber());
        entity.setPassengerNationality(dto.getPassengerNationality());
        entity.setPassengerContactNumber(dto.getPassengerContactNumber());
        entity.setPassengerEmergencyContactNumber(dto.getPassengerEmergencyContactNumber());

        //FK Object
        entity.setUser(UserMapper.toFkObjectEntity(dto.getUserId()));

        return entity;
    }

    //DTO to entity conversion
    public static PassengerProfileEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        PassengerProfileEntity entity = new PassengerProfileEntity();
        entity.setPassengerId(id);

        return entity;
    }

    //Entity to DTO conversion
    public static PassengerProfileResponseDTO toDTO(PassengerProfileEntity entity) {
        PassengerProfileResponseDTO dto = new PassengerProfileResponseDTO();

        if (entity != null) {
            dto.setPassengerId(entity.getPassengerId());
            dto.setPassengerFullName(entity.getPassengerFullName());
            dto.setPassengerPassportNumber(entity.getPassengerPassportNumber());
            dto.setPassengerNationality(entity.getPassengerNationality());
            dto.setPassengerContactNumber(entity.getPassengerContactNumber());
            dto.setPassengerEmergencyContactNumber(entity.getPassengerEmergencyContactNumber());

            //FK Object
            dto.setUser(UserMapper.toDTO(entity.getUser()));
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static PassengerProfileEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        PassengerProfileEntity entity = new PassengerProfileEntity();
        entity.setPassengerId(rs.getInt(prefix + "passenger_id")); //prfeix only for PK
        entity.setPassengerFullName(rs.getString("passenger_full_name"));
        entity.setPassengerPassportNumber(rs.getString("passenger_passport_no"));
        entity.setPassengerNationality(rs.getString("passenger_nationality"));
        entity.setPassengerContactNumber(rs.getString("passenger_contact_no"));
        entity.setPassengerEmergencyContactNumber(rs.getString("passenger_emergency_contact"));

        //FK object
        if (ResultSetUtil.hasColumn(rs, "passenger_user_id")) {
            UserEntity user = UserMapper.toEntityFromResultSet(rs, "passenger_");
            entity.setUser(user);
        }

        return entity;
    }
}
