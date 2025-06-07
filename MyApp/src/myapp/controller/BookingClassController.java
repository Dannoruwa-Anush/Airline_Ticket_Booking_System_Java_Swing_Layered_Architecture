/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.bookingClass.BookingClassRequestDTO;
import myapp.dto.bookingClass.BookingClassResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IBookingClassService;

/**
 *
 * @author HP
 */
public class BookingClassController {
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IBookingClassService bookingClassService;

    //constructor
    public BookingClassController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.BOOKING_CLASS);
        
        if (iSuperService instanceof IBookingClassService iBookingClassService) { 
            bookingClassService = iBookingClassService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IBookingClassService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addBookingClass(BookingClassRequestDTO bookingClassRequestDTO) throws Exception {
        return bookingClassService.addBookingClass(bookingClassRequestDTO);
    }

    public String updateBookingClass(Integer id, BookingClassRequestDTO bookingClassRequestDTO) throws Exception {
        return bookingClassService.updateBookingClass(id, bookingClassRequestDTO);
    }

    public String deleteBookingClass(Integer id) throws Exception {
        return bookingClassService.deleteBookingClass(id);
    }

    public BookingClassResponseDTO getBookingClassById(Integer id) throws Exception {
        return bookingClassService.getBookingClassById(id);
    }

    public List<BookingClassResponseDTO> getAllBookingClasses() throws Exception {
        return bookingClassService.getAllBookingClasses();
    }
    
    public List<BookingClassResponseDTO> getAllBookingClassesBySearchKey(String searchKey) throws Exception{
        return bookingClassService.getAllBookingClassesBySearchKey(searchKey);
    }
}
