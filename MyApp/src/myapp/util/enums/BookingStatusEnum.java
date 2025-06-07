/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.enums;

/**
 *
 * @author HP
 */
public enum BookingStatusEnum {
    CONFIRMED("confirmed"),
    CANCELLED("cancelled");

    private final String bookingStatus;

    // Constructor
    BookingStatusEnum(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public static BookingStatusEnum fromString(String bookingStatus) {
        for (BookingStatusEnum status : BookingStatusEnum.values()) {
            if (status.bookingStatus.equalsIgnoreCase(bookingStatus)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for booking status: " + bookingStatus);
    }
}
