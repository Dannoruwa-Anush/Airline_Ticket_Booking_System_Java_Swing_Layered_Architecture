/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.reservation.ReservationRequestDTO;
import myapp.dto.reservation.ReservationResponseDTO;
import myapp.entity.ReservationEntity;

import java.sql.SQLException;
import java.sql.ResultSet;
import myapp.entity.BookingClassEntity;
import myapp.entity.FlightEntity;
import myapp.entity.PassengerProfileEntity;

/**
 *
 * @author HP
 */
public class ReservationMapper {

    //DTO to entity conversion
    public static ReservationEntity toSaveEntity(ReservationRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ReservationRequestDTO must not be null");
        }

        ReservationEntity entity = new ReservationEntity();
        entity.setReservation_seat_number(dto.getReservation_seat_number());
        entity.setReservation_total_price(dto.getReservation_total_price());
        entity.setReservation_status(dto.getReservation_status());

        //FK Object
        entity.setPassenger(PassengerProfileMapper.toFkObjectEntity(dto.getReservation_passenger_id()));

        //FK Object
        entity.setFlight(FlightMapper.toFkObjectEntity(dto.getReservation_flight_id()));

        //FK Object
        entity.setBookingClass(BookingClassMapper.toFkObjectEntity(dto.getReservation_booking_class_id()));

        return entity;
    }

    public static ReservationEntity toUpdateEntity(Integer id, ReservationRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("ReservationRequestDTO must not be null");
        }

        ReservationEntity entity = new ReservationEntity();
        entity.setReservation_id(id);
        entity.setReservation_seat_number(dto.getReservation_seat_number());
        entity.setReservation_total_price(dto.getReservation_total_price());
        entity.setReservation_status(dto.getReservation_status());

        //FK Object
        entity.setPassenger(PassengerProfileMapper.toFkObjectEntity(dto.getReservation_passenger_id()));

        //FK Object
        entity.setFlight(FlightMapper.toFkObjectEntity(dto.getReservation_flight_id()));

        //FK Object
        entity.setBookingClass(BookingClassMapper.toFkObjectEntity(dto.getReservation_booking_class_id()));

        return entity;
    }

    //DTO to entity conversion
    public static ReservationEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        ReservationEntity entity = new ReservationEntity();
        entity.setReservation_id(id);

        return entity;
    }

    //Entity to DTO conversion
    public static ReservationResponseDTO toDTO(ReservationEntity entity) {
        ReservationResponseDTO dto = new ReservationResponseDTO();

        if (entity != null) {
            dto.setReservation_id(entity.getReservation_id());
            dto.setReservation_seat_number(entity.getReservation_seat_number());
            dto.setReservation_total_price(entity.getReservation_total_price());
            dto.setReservation_status(entity.getReservation_status());

            //FK Object
            dto.setPassenger(PassengerProfileMapper.toDTO(entity.getPassenger()));

            //FK Object
            dto.setFlight(FlightMapper.toDTO(entity.getFlight()));

            //FK Object
            dto.setBookingClass(BookingClassMapper.toDTO(entity.getBookingClass()));
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static ReservationEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        ReservationEntity entity = new ReservationEntity();

        entity.setReservation_id(rs.getInt(prefix + "reservation_id")); //prfeix only for PK
        entity.setReservation_seat_number(rs.getString("reservation_seat_number"));
        entity.setReservation_total_price(rs.getBigDecimal("reservation_total_price"));
        entity.setReservation_status(rs.getString("reservation_status"));

        //FK Object
        PassengerProfileEntity passenger = PassengerProfileMapper.toEntityFromResultSet(rs, "reservation_");
        entity.setPassenger(passenger);

        //FK Object
        FlightEntity flight = FlightMapper.toEntityFromResultSet(rs, "reservation_");
        entity.setFlight(flight);

        //FK Object
        BookingClassEntity bookingClass = BookingClassMapper.toEntityFromResultSet(rs, "reservation_");
        entity.setBookingClass(bookingClass);

        return entity;
    }
}
