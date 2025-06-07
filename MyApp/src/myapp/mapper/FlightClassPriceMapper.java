/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.flightClassPrice.FlightClassPriceRequestDTO;
import myapp.dto.flightClassPrice.FlightClassPriceResponseDTO;
import myapp.entity.FlightClassPriceEntity;
import java.sql.SQLException;
import java.sql.ResultSet;
import myapp.entity.BookingClassEntity;
import myapp.entity.FlightEntity;

/**
 *
 * @author HP
 */
public class FlightClassPriceMapper {

    // DTO to Entity
    public static FlightClassPriceEntity toSaveEntity(FlightClassPriceRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("FlightClassPriceRequestDTO must not be null");
        }

        FlightClassPriceEntity entity = new FlightClassPriceEntity();
        entity.setBase_price(dto.getBase_price());
        entity.setSeat_capacity(dto.getSeat_capacity());

        //Fk Object
        entity.setFlight(FlightMapper.toFkObjectEntity(dto.getFlight_id()));

        //Fk Object
        entity.setBookingClass(BookingClassMapper.toFkObjectEntity(dto.getBookingClass_id()));

        return entity;
    }

    // DTO to Entity
    public static FlightClassPriceEntity toUpdateEntity(FlightClassPriceRequestDTO dto) {
        //In here primary key is a composite key (flight_id, booking_class_id)
        //What is planning to do is update by flight_id and booking_class_id
        if (dto == null) {
            throw new IllegalArgumentException("FlightClassPriceRequestDTO must not be null");
        }

        FlightClassPriceEntity entity = new FlightClassPriceEntity();
        entity.setBase_price(dto.getBase_price());
        entity.setSeat_capacity(dto.getSeat_capacity());

        //Fk Object
        entity.setFlight(FlightMapper.toFkObjectEntity(dto.getFlight_id()));

        //Fk Object
        entity.setBookingClass(BookingClassMapper.toFkObjectEntity(dto.getBookingClass_id()));

        return entity;
    }

    // Entity to DTO
    public static FlightClassPriceResponseDTO toDTO(FlightClassPriceEntity entity) {
        FlightClassPriceResponseDTO dto = new FlightClassPriceResponseDTO();
        
        if (entity != null) {
            //dto.setFlightClassPrice_id(entity.getFlightClassPrice_id());
            dto.setBase_price(entity.getBase_price());
            dto.setSeat_capacity(entity.getSeat_capacity());

            //FK Object
            dto.setFlight(FlightMapper.toDTO(entity.getFlight()));

            //FK Object
            dto.setBookingClass(BookingClassMapper.toDTO(entity.getBooking_class()));
        }
        
       return dto;
    }

    //SQL ResultSet to Entity
    public static FlightClassPriceEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        FlightClassPriceEntity entity = new FlightClassPriceEntity();

        //******[Start : composite key] ********
//        entity.setFlightClassPrice_id(new FlightBookingClassCompKey(
//                        (rs.getInt(prefix + "flight_id")), 
//                        (rs.getInt(prefix + "booking_class_id")))
//        ); //prfeix only for PK
        //******[End : composite key] **********
        entity.setBase_price(rs.getBigDecimal("flight_class_price_base_price"));
        entity.setSeat_capacity(rs.getInt("flight_class_price_seat_capacity"));

        //FK object
        FlightEntity flight = FlightMapper.toEntityFromResultSet(rs, "flight_class_price_");

        //FK object
        BookingClassEntity bookingClass = BookingClassMapper.toEntityFromResultSet(rs, "flight_class_price_");

        entity.setFlight(flight);
        entity.setBookingClass(bookingClass);

        return entity;
    }
}
