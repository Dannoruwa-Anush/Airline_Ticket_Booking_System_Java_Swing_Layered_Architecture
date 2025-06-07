/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.reservation;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class ReservationTabInteractionDTO {

    private int passengerId;

    private int flightId;
    private String flightCode;

    private int bookingClassId;
    private String bookingClassName;
    private int availableSeatCount;
    private BigDecimal totalPrice;

    //------- [Start : constructor, getter(), setter(), toString()]------
    public ReservationTabInteractionDTO() {
    }

    public ReservationTabInteractionDTO(int passengerId, int flightId, String flightCode, int bookingClassId, String bookingClassName, int availableSeatCount, BigDecimal totalPrice) {
        setPassengerId(passengerId);
        setFlightId(flightId);
        setFlightCode(flightCode);
        setBookingClassId(bookingClassId);
        setBookingClassName(bookingClassName);
        setAvailableSeatCount(availableSeatCount);
        setTotalPrice(totalPrice);
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public int getBookingClassId() {
        return bookingClassId;
    }

    public String getBookingClassName() {
        return bookingClassName;
    }

    public int getAvailableSeatCount() {
        return availableSeatCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public void setBookingClassId(int bookingClassId) {
        this.bookingClassId = bookingClassId;
    }

    public void setBookingClassName(String bookingClassName) {
        this.bookingClassName = bookingClassName;
    }

    public void setAvailableSeatCount(int availableSeatCount) {
        this.availableSeatCount = availableSeatCount;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ReservationTabInteractionDTO{" + "passengerId=" + passengerId + ", flightId=" + flightId + ", flightCode=" + flightCode + ", bookingClassId=" + bookingClassId + ", bookingClassName=" + bookingClassName + ", availableSeatCount=" + availableSeatCount + ", totalPrice=" + totalPrice + '}';
    }

    //------- [End : constructor, getter(), setter(), toString()]------
}
