/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity.compositeKey;

/**
 *
 * @author HP
 */
public class FlightBookingClassCompKey {

    private int flightId;
    private int bookingClassId;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public FlightBookingClassCompKey() {
    }

    public FlightBookingClassCompKey(int flightId, int bookingClassId) {
        setFlightId(flightId);
        setBookingClassId(bookingClassId);
    }

    public int getFlightId() {
        return flightId;
    }

    public int getBookingClassId() {
        return bookingClassId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setBookingClassId(int bookingClassId) {
        this.bookingClassId = bookingClassId;
    }

    @Override
    public String toString() {
        return "FlightBookingClassKey{" + "flightId=" + flightId + ", bookingClassId=" + bookingClassId + '}';
    }

    //------- [End : constructor, getter(), setter(), toString()]-------
}
