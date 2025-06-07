/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

/**
 *
 * @author HP
 */
public class BookingClassEntity {
    private int bookingClass_id;
    private String bookingClass_name;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------

    public BookingClassEntity() {
    }

    public BookingClassEntity(int bookingClass_id, String bookingClass_name) {
        setBookingClass_id(bookingClass_id); 
        setBookingClass_name(bookingClass_name);
    }

    public int getBookingClass_id() {
        return bookingClass_id;
    }

    public String getBookingClass_name() {
        return bookingClass_name;
    }

    public void setBookingClass_id(int bookingClass_id) {
        this.bookingClass_id = bookingClass_id;
    }

    public void setBookingClass_name(String bookingClass_name) { 
        this.bookingClass_name = bookingClass_name;
    }

    @Override
    public String toString() {
        return "BookingClassEntity{" + "bookingClass_id=" + bookingClass_id + ", bookingClass_name=" + bookingClass_name + '}';
    }
    
    
}
