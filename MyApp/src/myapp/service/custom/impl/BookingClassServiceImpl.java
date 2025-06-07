/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IBookingClassDao;
import myapp.dao.custom.IFlightClassPriceDao;
import myapp.dao.custom.IReservationDao;
import myapp.dto.bookingClass.BookingClassRequestDTO;
import myapp.dto.bookingClass.BookingClassResponseDTO;
import myapp.entity.BookingClassEntity;
import myapp.mapper.BookingClassMapper;
import myapp.service.custom.IBookingClassService;

/**
 *
 * @author HP
 */
public class BookingClassServiceImpl implements IBookingClassService {

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(BookingClassServiceImpl.class.getName());

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IBookingClassDao bookingClassDao;

    //For safe parent table delete
    private IReservationDao reservationDao;
    
    //For safe parent table delete
    private IFlightClassPriceDao flightClassPriceDao;

    //constructor
    public BookingClassServiceImpl() {
        ISuperDAO iSuperDAOBookingClass = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.BOOKING_CLASS);

        if (iSuperDAOBookingClass instanceof IBookingClassDao iBookingClassDao) {
            bookingClassDao = iBookingClassDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IBookingClassDao");
        }

        ISuperDAO iSuperDAOReservation = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.RESERVATION);

        if (iSuperDAOReservation instanceof IReservationDao iReservationDao) {
            reservationDao = iReservationDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IReservationDao");
        }
        
        ISuperDAO iSuperDAOFlightClassPrice = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT_CLASS_PRICE);

        if (iSuperDAOFlightClassPrice instanceof IFlightClassPriceDao iFlightClassPriceDao) {
            flightClassPriceDao = iFlightClassPriceDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightClassPriceDao");
        }
    }

    //IReservationDao
    //------- [End : downcast] ------------------------------
    @Override
    public String addBookingClass(BookingClassRequestDTO bookingClassRequestDTO) throws Exception {
        BookingClassEntity bookingClass = BookingClassMapper.toSaveEntity(bookingClassRequestDTO);
        boolean success = bookingClassDao.save(bookingClass);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateBookingClass(Integer id, BookingClassRequestDTO bookingClassRequestDTO) throws Exception {
        BookingClassEntity bookingClass = BookingClassMapper.toUpdateEntity(id, bookingClassRequestDTO);
        if (bookingClassDao.update(bookingClass)) {
            LOGGER.log(Level.INFO, "Booking class with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update booking class with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteBookingClass(Integer id) throws Exception {
        boolean hasReservationWithBookingClass = reservationDao.hasReservationWithBookingClass(id);
        if (hasReservationWithBookingClass) {
            LOGGER.log(Level.INFO, "Cannot delete booking class with ID {0} because it has associated reservations", id);
            return "Cannot delete booking class; there are reservations associated with it";
        }
        
        boolean hasFlightClassPriceWithBookingClass = flightClassPriceDao.hasFlightClassPriceWithBookingClass(id);
        if (hasFlightClassPriceWithBookingClass) {
            LOGGER.log(Level.INFO, "Cannot delete booking class with ID {0} because it has associated flight class prices", id);
            return "Cannot delete booking class; there are flight class prices associated with it";
        }

        boolean deleteSuccessful = bookingClassDao.delete(id);
        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Booking class with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete booking class with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public BookingClassResponseDTO getBookingClassById(Integer id) throws Exception {
        BookingClassEntity bookingClassEntity = bookingClassDao.getById(id);
        if (bookingClassEntity != null) {
            BookingClassResponseDTO response = BookingClassMapper.toDTO(bookingClassEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<BookingClassResponseDTO> getAllBookingClasses() throws Exception {
        List<BookingClassResponseDTO> dtos = new ArrayList<>();
        List<BookingClassEntity> bookingClassEntities = bookingClassDao.getAll();
        for (BookingClassEntity entity : bookingClassEntities) {
            dtos.add(BookingClassMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<BookingClassResponseDTO> getAllBookingClassesBySearchKey(String searchKey) throws Exception {
        List<BookingClassResponseDTO> dtos = new ArrayList<>();
        List<BookingClassEntity> bookingClassEntities = bookingClassDao.getAllBySearchKey(searchKey);
        for (BookingClassEntity entity : bookingClassEntities) {
            dtos.add(BookingClassMapper.toDTO(entity));
        }
        return dtos;
    }
}
