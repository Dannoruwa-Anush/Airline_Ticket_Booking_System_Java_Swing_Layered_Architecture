/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IFlightClassPriceDao;
import myapp.dao.custom.IReservationDao;
import myapp.dbConfig.DbConnection;
import myapp.dto.reservation.ReservationRequestDTO;
import myapp.dto.reservation.ReservationResponseDTO;
import myapp.dto.reservation.ReservationStatusChangeRequestDTO;
import myapp.entity.ReservationEntity;
import myapp.mapper.ReservationMapper;
import myapp.service.custom.IReservationService;

/**
 *
 * @author HP
 */
public class ReservationServiceImpl implements IReservationService {

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(ReservationServiceImpl.class.getName());

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IReservationDao reservationDao;

    //For save and status update
    private IFlightClassPriceDao flightClassPriceDao;

    //constructor
    public ReservationServiceImpl() {
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
    //------- [End : downcast] ------------------------------

    @Override
    public String addReservation(ReservationRequestDTO reservationRequestDTO) throws Exception {
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Convert DTO to entity
            ReservationEntity reservation = ReservationMapper.toSaveEntity(reservationRequestDTO);

            // Step 1: Save reservation
            boolean saved = reservationDao.saveWithTransactionHandling(connection, reservation);
            if (!saved) {
                connection.rollback();
                return "Reservation failed: Could not save reservation.";
            }

            // Step 2: Check seat availability
            Integer flightId = reservation.getFlight().getFlight_id();
            Integer bookingClassId = reservation.getBookingClass().getBookingClass_id();

            int currentCapacity = flightClassPriceDao.getAvailableSeatCapacityWithTransactionHandling(connection, flightId, bookingClassId);
            if (currentCapacity <= 0) {
                connection.rollback();
                return "Reservation failed: No available seats.";
            }

            // Step 3: Update seat capacity
            boolean updated = flightClassPriceDao.updateAvailableSeatCapacityWithTransactionHandling(connection, flightId, bookingClassId, currentCapacity - 1);
            if (!updated) {
                connection.rollback();
                return "Reservation failed: Could not update seat capacity.";
            }

            connection.commit();
            return "Reservation successfully added.";

        } catch (ClassNotFoundException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Failed to reset auto-commit: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public String updateReservationStatus(Integer id, ReservationStatusChangeRequestDTO reservationStatusChangeRequestDTO) throws Exception {
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Step 1: Get reservation by ID
            ReservationEntity reservation = reservationDao.getByIdWithTransactionHandling(connection, id);
            if (reservation == null) {
                connection.rollback();
                return "Reservation not found.";
            }

            String currentStatus = reservation.getReservation_status();
            String newStatus = reservationStatusChangeRequestDTO.getReservation_status();

            // Log statuses (for debugging)
            System.out.println("Current status: " + currentStatus);
            System.out.println("Requested status: " + newStatus);

            // Step 2: Validate transition
            if (currentStatus.equalsIgnoreCase("cancelled")) {
                return "Reservation is already cancelled.";
            }

            if (!currentStatus.equalsIgnoreCase("confirmed") || !newStatus.equalsIgnoreCase("cancelled")) {
                return "Invalid status transition. Only 'confirmed' to 'cancelled' is allowed.";
            }

            // Step 3: Update reservation status
            reservation.setReservation_status("cancelled");
            boolean statusUpdated = reservationDao.updateReservationStatusWithTransactionHandling(connection, reservation);
            if (!statusUpdated) {
                connection.rollback();
                return "Failed to update reservation status.";
            }

            // Step 4: Increase seat capacity
            Integer flightId = reservation.getFlight().getFlight_id();
            Integer bookingClassId = reservation.getBookingClass().getBookingClass_id();

            int currentCapacity = flightClassPriceDao.getAvailableSeatCapacityWithTransactionHandling(connection, flightId, bookingClassId);
            boolean updated = flightClassPriceDao.updateAvailableSeatCapacityWithTransactionHandling(connection, flightId, bookingClassId, currentCapacity + 1);
            if (!updated) {
                connection.rollback();
                return "Failed to update seat capacity.";
            }

            connection.commit();
            return "Reservation status updated to 'cancelled'.";

        } catch (ClassNotFoundException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Failed to reset auto-commit: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public String deleteReservation(Integer id) throws Exception {
        if (reservationDao.delete(id)) {
            LOGGER.log(Level.INFO, "Reservation with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete reservation with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public ReservationResponseDTO getReservationById(Integer id) throws Exception {
        ReservationEntity reservationEntity = reservationDao.getById(id);
        if (reservationEntity != null) {
            ReservationResponseDTO response = ReservationMapper.toDTO(reservationEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations() throws Exception {
        List<ReservationResponseDTO> dtos = new ArrayList<>();
        List<ReservationEntity> reservationEntities = reservationDao.getAll();
        for (ReservationEntity entity : reservationEntities) {
            dtos.add(ReservationMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservationBySearchKey(String searchKey) throws Exception {
        List<ReservationResponseDTO> dtos = new ArrayList<>();
        List<ReservationEntity> reservationEntities = reservationDao.getAllBySearchKey(searchKey);
        for (ReservationEntity entity : reservationEntities) {
            dtos.add(ReservationMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservationByPassengerProfileId(Integer passengerProfileId) throws Exception {
        List<ReservationResponseDTO> dtos = new ArrayList<>();
        List<ReservationEntity> reservationEntities = reservationDao.getAllByPassengerProfileId(passengerProfileId);
        for (ReservationEntity entity : reservationEntities) {
            dtos.add(ReservationMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservationByStatus(String status) throws Exception {
        List<ReservationResponseDTO> dtos = new ArrayList<>();
        List<ReservationEntity> reservationEntities = reservationDao.getAllByStatus(status);
        for (ReservationEntity entity : reservationEntities) {
            dtos.add(ReservationMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservationBySearchKeyAndPassengerProfileId(Integer passengerId, String searchKey) throws Exception {
        List<ReservationResponseDTO> dtos = new ArrayList<>();
        List<ReservationEntity> reservationEntities = reservationDao.getAllBySearchKeyAndPassengerProfileId(passengerId, searchKey);
        for (ReservationEntity entity : reservationEntities) {
            dtos.add(ReservationMapper.toDTO(entity));
        }
        return dtos;
    }
}
