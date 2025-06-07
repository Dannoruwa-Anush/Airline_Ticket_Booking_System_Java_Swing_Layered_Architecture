/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class ReservationEntity {
    private int reservation_id;
    private PassengerProfileEntity passenger; // Using object reference for FK
    private FlightEntity flight; // Using object reference for FK
    private BookingClassEntity bookingClass; // Using object reference for FK
    private LocalDateTime reservation_date;
    private String reservation_seat_number; //flightCode + int
    private BigDecimal reservation_total_price;
    private String reservation_status;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------

    public ReservationEntity() {
    }

    public ReservationEntity(int reservation_id, PassengerProfileEntity passenger, FlightEntity flight, BookingClassEntity bookingClass, LocalDateTime reservation_date, String reservation_seat_number, BigDecimal reservation_total_price, String reservation_status) {
        setReservation_id(reservation_id);
        setPassenger(passenger);
        setFlight(flight);
        setBookingClass(bookingClass);
        setReservation_date(reservation_date);
        setReservation_seat_number(reservation_seat_number);
        setReservation_total_price(reservation_total_price);
        setReservation_status(reservation_status);
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public PassengerProfileEntity getPassenger() {
        return passenger;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public BookingClassEntity getBookingClass() {
        return bookingClass;
    }

    public LocalDateTime getReservation_date() {
        return reservation_date;
    }

    public String getReservation_seat_number() {
        return reservation_seat_number;
    }

    public BigDecimal getReservation_total_price() {
        return reservation_total_price;
    }

    public String getReservation_status() {
        return reservation_status;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public void setPassenger(PassengerProfileEntity passenger) {
        this.passenger = passenger;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public void setBookingClass(BookingClassEntity bookingClass) {
        this.bookingClass = bookingClass;
    }

    public void setReservation_date(LocalDateTime reservation_date) {
        this.reservation_date = reservation_date;
    }

    public void setReservation_seat_number(String reservation_seat_number) {
        this.reservation_seat_number = reservation_seat_number;
    }

    public void setReservation_total_price(BigDecimal reservation_total_price) {
        this.reservation_total_price = reservation_total_price;
    }

    public void setReservation_status(String reservation_status) {
        this.reservation_status = reservation_status;
    }
    
    @Override
    public String toString() {
        return "ReservationEntity{" + "reservation_id=" + reservation_id + ", passenger=" + passenger + ", flight=" + flight + ", bookingClass=" + bookingClass + ", reservation_date=" + reservation_date + ", reservation_seat_number=" + reservation_seat_number + ", reservation_total_price=" + reservation_total_price + ", reservation_status=" + reservation_status + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
