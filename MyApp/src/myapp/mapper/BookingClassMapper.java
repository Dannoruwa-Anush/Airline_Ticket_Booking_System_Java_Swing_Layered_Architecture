/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.bookingClass.BookingClassRequestDTO;
import myapp.dto.bookingClass.BookingClassResponseDTO;
import myapp.entity.BookingClassEntity;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class BookingClassMapper {

    //DTO to entity conversion
    public static BookingClassEntity toSaveEntity(BookingClassRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("BookingClassRequestDTO must not be null");
        }

        BookingClassEntity entity = new BookingClassEntity();
        entity.setBookingClass_name(dto.getBookingClass_name());

        return entity;
    }

    //DTO to entity conversion
    public static BookingClassEntity toUpdateEntity(Integer id, BookingClassRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("BookingClassRequestDTO must not be null");
        }

        BookingClassEntity entity = new BookingClassEntity();
        entity.setBookingClass_id(id);
        entity.setBookingClass_name(dto.getBookingClass_name());

        return entity;
    }

    //DTO to entity conversion
    public static BookingClassEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        BookingClassEntity entity = new BookingClassEntity();
        entity.setBookingClass_id(id);

        return entity;
    }

    //Entity to DTO conversion
    public static BookingClassResponseDTO toDTO(BookingClassEntity entity) {
        BookingClassResponseDTO dto = new BookingClassResponseDTO();

        if (entity != null) {
            dto.setBookingClass_id(entity.getBookingClass_id());
            dto.setBookingClass_name(entity.getBookingClass_name());
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static BookingClassEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        BookingClassEntity entity = new BookingClassEntity();
        entity.setBookingClass_id(rs.getInt(prefix + "booking_class_id")); //prfeix only for PK
        entity.setBookingClass_name(rs.getString("booking_class_name"));

        return entity;
    }

}
