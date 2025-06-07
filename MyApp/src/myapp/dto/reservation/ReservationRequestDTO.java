/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.reservation;

import java.math.BigDecimal;
import myapp.util.enums.BookingStatusEnum;

/**
 *
 * @author HP
 */
public class ReservationRequestDTO {

    private int reservation_passenger_id; // FK
    private int reservation_flight_id; // FK
    private int reservation_booking_class_id; // FK
    private String reservation_seat_number;
    private BigDecimal reservation_total_price;
    private String reservation_status;

    // Helper properties (not exposed with getters)
    private int availableSeatCount;
    private String flightCode;
    private String bookingClassName;

    //------- [Start : constructor, getter(), setter(), toString()]------
    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(int reservation_passenger_id, int reservation_flight_id, int reservation_booking_class_id, BigDecimal reservation_total_price, int availableSeatCount, String flightCode, String bookingClassName) {
        setReservation_passenger_id(reservation_passenger_id);
        setReservation_flight_id(reservation_flight_id);
        setReservation_booking_class_id(reservation_booking_class_id);
        setReservation_total_price(reservation_total_price);
        setAvailableSeatCount(availableSeatCount);
        setFlightCode(flightCode);
        setBookingClassName(bookingClassName);

        this.reservation_status = BookingStatusEnum.CONFIRMED.getBookingStatus(); //In this request status is set to "confirmed" as default
        this.reservation_seat_number = generateSeatNumber();
    }

    //******** [Start : helper methods] *************
    private String generateSeatNumber() {
        char classInitial = bookingClassName != null && !bookingClassName.isEmpty() ? bookingClassName.charAt(0) : 'X'; // Default fallback
        return flightCode + "-" + classInitial + "-" + availableSeatCount;
    }
    //******** [End : helper methods] *************

    public int getReservation_passenger_id() {
        return reservation_passenger_id;
    }

    public int getReservation_flight_id() {
        return reservation_flight_id;
    }

    public int getReservation_booking_class_id() {
        return reservation_booking_class_id;
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

    public void setReservation_passenger_id(int reservation_passenger_id) {
        if (reservation_passenger_id <= 0) {
            throw new IllegalArgumentException("Reservation passenger id must be a positive integer.");
        }
        this.reservation_passenger_id = reservation_passenger_id;
    }

    public void setReservation_flight_id(int reservation_flight_id) {
        if (reservation_flight_id <= 0) {
            throw new IllegalArgumentException("Reservation flight id must be a positive integer.");
        }
        this.reservation_flight_id = reservation_flight_id;
    }

    public void setReservation_booking_class_id(int reservation_booking_class_id) {
        if (reservation_booking_class_id <= 0) {
            throw new IllegalArgumentException("Reservation booking class id must be a positive integer.");
        }
        this.reservation_booking_class_id = reservation_booking_class_id;
    }

    public void setReservation_total_price(BigDecimal reservation_total_price) {
        if (reservation_total_price == null) {
            throw new IllegalArgumentException("Reservation total price cannot be null.");
        }
        if (reservation_total_price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Reservation total price cannot be negative.");
        }
        this.reservation_total_price = reservation_total_price;
    }

    public void setAvailableSeatCount(int availableSeatCount) {
        if(availableSeatCount <= 0){
            throw new IllegalArgumentException("Available seat count must be a positive integer");
        }

        this.availableSeatCount = availableSeatCount;
    }

    public void setFlightCode(String flightCode) {
        if (flightCode == null || flightCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight code cannot be null or empty.");
        }
        this.flightCode = flightCode.trim();
    }

    public void setBookingClassName(String bookingClassName) {
        if (bookingClassName == null || bookingClassName.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking class name cannot be null or empty.");
        }
        this.bookingClassName = bookingClassName.trim();
    }

    @Override
    public String toString() {
        return "ReservationRequestDTO{" + "reservation_passenger_id=" + reservation_passenger_id + ", reservation_flight_id=" + reservation_flight_id + ", reservation_class_id=" + reservation_booking_class_id + ", reservation_seat_number=" + reservation_seat_number + ", reservation_total_price=" + reservation_total_price + ", reservation_status=" + reservation_status + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
