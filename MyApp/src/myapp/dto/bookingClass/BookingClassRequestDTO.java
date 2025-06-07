/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.bookingClass;

/**
 *
 * @author HP
 */
public class BookingClassRequestDTO {
    private String bookingClass_name;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------

    public BookingClassRequestDTO() {
    }

    public BookingClassRequestDTO(String bookingClass_name) {
        setBookingClass_name(bookingClass_name);
    }

    public String getBookingClass_name() {
        return bookingClass_name;
    }

    public void setBookingClass_name(String bookingClass_name) {
        if (bookingClass_name == null || bookingClass_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking class name cannot be null or empty.");
        }
        this.bookingClass_name = bookingClass_name.trim();
    }

    @Override
    public String toString() {
        return "BookingClassRequestDTO{" + "bookingClass_name=" + bookingClass_name + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
