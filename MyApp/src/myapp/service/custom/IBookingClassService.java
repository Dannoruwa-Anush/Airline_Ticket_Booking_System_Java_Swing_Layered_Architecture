/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.bookingClass.BookingClassRequestDTO;
import myapp.dto.bookingClass.BookingClassResponseDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IBookingClassService extends ISuperService{
    String addBookingClass(BookingClassRequestDTO bookingClassRequestDTO) throws Exception;
    String updateBookingClass(Integer id, BookingClassRequestDTO bookingClassRequestDTO) throws Exception;
    String deleteBookingClass(Integer id) throws Exception;
    BookingClassResponseDTO getBookingClassById(Integer id) throws Exception;
    List<BookingClassResponseDTO> getAllBookingClasses() throws Exception;

    List<BookingClassResponseDTO> getAllBookingClassesBySearchKey(String searchKey)throws Exception;
}
