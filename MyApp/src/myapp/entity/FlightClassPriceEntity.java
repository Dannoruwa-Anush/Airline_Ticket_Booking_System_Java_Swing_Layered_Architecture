/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class FlightClassPriceEntity {
    //private FlightBookingClassCompKey flightClassPrice_id; //composite key
    private FlightEntity flight; // Using object reference for FK
    private BookingClassEntity booking_class;  // Using object reference for FK
    private BigDecimal base_price;
    private int seat_capacity;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightClassPriceEntity() {
    }
    
    public FlightClassPriceEntity(FlightEntity flight, BookingClassEntity booking_class, BigDecimal base_price, int seat_capacity) {
        setFlight(flight);
        setBookingClass(booking_class);
        setBase_price(base_price);
        setSeat_capacity(seat_capacity);
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public BookingClassEntity getBooking_class() {
        return booking_class;
    }

    public BigDecimal getBase_price() {
        return base_price;
    }

    public int getSeat_capacity() {
        return seat_capacity;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public void setBookingClass(BookingClassEntity booking_class) {
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
        return "FlightClassPriceEntity{" + ", flight=" + flight + ", booking_class=" + booking_class + ", base_price=" + base_price + ", seat_capacity=" + seat_capacity + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
