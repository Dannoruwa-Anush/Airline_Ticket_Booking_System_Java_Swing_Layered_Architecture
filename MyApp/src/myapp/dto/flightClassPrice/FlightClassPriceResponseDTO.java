/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flightClassPrice;

import java.math.BigDecimal;
import myapp.dto.bookingClass.BookingClassResponseDTO;
import myapp.dto.flight.FlightResponseDTO;
import myapp.entity.compositeKey.FlightBookingClassCompKey;

/**
 *
 * @author HP
 */
public class FlightClassPriceResponseDTO {
    private FlightBookingClassCompKey flightClassPrice_id; //composite key
    private FlightResponseDTO flight; // Using object reference for FK
    private BookingClassResponseDTO booking_class; // Using object reference for FK
    private BigDecimal base_price;
    private int seat_capacity;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightClassPriceResponseDTO() {
    }

    public FlightClassPriceResponseDTO(FlightBookingClassCompKey flightClassPrice_id, FlightResponseDTO flight, BookingClassResponseDTO booking_class, BigDecimal base_price, int seat_capacity) {
        setFlightClassPrice_id(flightClassPrice_id);
        setFlight(flight);
        setBookingClass(booking_class);
        setBase_price(base_price);
        setSeat_capacity(seat_capacity);
    }

    public FlightBookingClassCompKey getFlightClassPrice_id() {
        return flightClassPrice_id;
    }

    public FlightResponseDTO getFlight() {
        return flight;
    }

    public BookingClassResponseDTO getBookingClass() {
        return booking_class;
    }

    public BigDecimal getBase_price() {
        return base_price;
    }

    public int getSeat_capacity() {
        return seat_capacity;
    }

    public void setFlightClassPrice_id(FlightBookingClassCompKey flightClassPrice_id) {
        this.flightClassPrice_id = flightClassPrice_id;
    }

    public void setFlight(FlightResponseDTO flight) {
        this.flight = flight;
    }

    public void setBookingClass(BookingClassResponseDTO booking_class) {
        this.booking_class = booking_class;
    }

    public void setBase_price(BigDecimal base_price) {
        this.base_price = base_price;
    }

    public void setSeat_capacity(int seat_capacity) {
        this.seat_capacity = seat_capacity;
    }

    @Override
    public String toString() {
        return "FlightClassPriceResponseDTO{" + "flightClassPrice_id=" + flightClassPrice_id + ", flight=" + flight + ", bookingClass=" + booking_class + ", base_price=" + base_price + ", seat_capacity=" + seat_capacity + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
