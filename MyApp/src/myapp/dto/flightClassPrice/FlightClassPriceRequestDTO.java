/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flightClassPrice;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class FlightClassPriceRequestDTO {

    private int flight_id; //FK
    private int booking_class_id; //FK
    private BigDecimal base_price;
    private int seat_capacity;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightClassPriceRequestDTO() {
    }

    public FlightClassPriceRequestDTO(int flight_id, int class_id, BigDecimal base_price, int seat_capacity) {
        setFlight_id(flight_id);
        setBookingClass_id(class_id);
        setBase_price(base_price);
        setSeat_capacity(seat_capacity);
    }

    public int getFlight_id() {
        return flight_id;
    }

    public int getBookingClass_id() {
        return booking_class_id;
    }

    public BigDecimal getBase_price() {
        return base_price;
    }

    public int getSeat_capacity() {
        return seat_capacity;
    }

    public void setFlight_id(int flight_id) {
        if (flight_id <= 0) {
            throw new IllegalArgumentException("Flight id must be a positive integer.");
        }
        this.flight_id = flight_id;
    }

    public void setBookingClass_id(int class_id) {
        if (class_id <= 0) {
            throw new IllegalArgumentException("Class id must be a positive integer.");
        }
        this.booking_class_id = class_id;
    }

    public void setBase_price(BigDecimal base_price) {
        if (base_price == null) {
            throw new IllegalArgumentException("Base price cannot be null.");
        }
        
        if (base_price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Base price cannot be negative.");
        }
        this.base_price = base_price;
    }

    public void setSeat_capacity(int seat_capacity) {
        if (seat_capacity <= 0) {
            throw new IllegalArgumentException("Seat capacity must be a positive integer.");
        }
        this.seat_capacity = seat_capacity;
    }

    @Override
    public String toString() {
        return "FlightClassPricingRequestDTO{" + "flight_id=" + flight_id + ", class_id=" + booking_class_id + ", base_price=" + base_price + ", seat_capacity=" + seat_capacity + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
