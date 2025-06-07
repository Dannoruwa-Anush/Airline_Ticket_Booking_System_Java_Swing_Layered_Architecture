/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import myapp.dbConfig.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Locale;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IFlightClassPriceDao;
import myapp.dao.custom.IFlightDao;
import myapp.dao.custom.IFlightScheduleDao;
import myapp.dao.custom.IReservationDao;
import myapp.dto.flight.FlightRequestDTO;
import myapp.dto.flight.FlightResponseDTO;
import myapp.dto.flightsGeneration.FlightsGenerationRequestDTO;
import myapp.entity.FlightEntity;
import myapp.entity.FlightScheduleEntity;
import myapp.mapper.AircraftModelMapper;
import myapp.mapper.FlightMapper;
import myapp.mapper.FlightScheduleMapper;
import myapp.mapper.RouteMapper;
import myapp.service.custom.IFlightService;

/**
 *
 * @author HP
 */
public class FlightServiceImpl implements IFlightService {

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(FlightServiceImpl.class.getName());

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IFlightDao flightDao;

    //For generateFlightsForWeek
    private IFlightScheduleDao flightScheduleDao;

    //For safe parent table delete
    private IReservationDao reservationDao;

    //For safe parent table delete
    private IFlightClassPriceDao flightClassPriceDao;

    //constructor
    public FlightServiceImpl() {
        ISuperDAO iSuperDAOFlight = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT);

        if (iSuperDAOFlight instanceof IFlightDao iFlightDao) {
            flightDao = iFlightDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightDao");
        }

        ISuperDAO iSuperDAOFlightSchedule = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT_SCHEDULE);

        if (iSuperDAOFlightSchedule instanceof IFlightScheduleDao iFlightScheduleDao) {
            flightScheduleDao = iFlightScheduleDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightScheduleDao");
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
    //------- [End : downcast] ------------------------------

    @Override
    public String addFlight(FlightRequestDTO flightRequestDTO) throws Exception {
        FlightEntity flight = FlightMapper.toSaveEntity(flightRequestDTO);
        boolean success = flightDao.save(flight);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateFlight(Integer id, FlightRequestDTO flightRequestDTO) throws Exception {
        FlightEntity flight = FlightMapper.toUpdateEntity(id, flightRequestDTO);

        if (flightDao.update(flight)) {
            LOGGER.log(Level.INFO, "Flight with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update flight with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteFlight(Integer id) throws Exception {
        boolean hasReservationWithFlight = reservationDao.hasReservationWithFlight(id);
        if (hasReservationWithFlight) {
            LOGGER.log(Level.INFO, "Cannot delete flight with ID {0} because it has associated reservations", id);
            return "Cannot delete flight; there are reservations associated with it";
        }

        boolean hasFlightClassPriceWithFlight = flightClassPriceDao.hasFlightClassPriceWithFlight(id);
        if (hasFlightClassPriceWithFlight) {
            LOGGER.log(Level.INFO, "Cannot delete flight with ID {0} because it has associated flight class prices", id);
            return "Cannot delete flight; there are flight class prices associated with it";
        }

        boolean deleteSuccessful = flightDao.delete(id);
        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Flight with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete flight with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public FlightResponseDTO getFlightById(Integer id) throws Exception {
        FlightEntity fightEntity = flightDao.getById(id);
        if (fightEntity != null) {
            FlightResponseDTO response = FlightMapper.toDTO(fightEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<FlightResponseDTO> getAllFlights() throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAll();
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightResponseDTO> getAllFlightsBySearchKey(String searchKey) throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAllBySearchKey(searchKey);
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightResponseDTO> getAllFlightsWithoutFlightClassPriceInfo() throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAllFlightsWithoutFlightClassPriceInfo();
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightResponseDTO> getAllFlightsWithFlightClassPriceInfo() throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAllFlightsWithFlightClassPriceInfo();
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightResponseDTO> getAllFlightsForRouteAndDepartureTime(Integer routeId, LocalDateTime departureTime) throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAllFlightsForRouteAndDepartureTime(routeId, departureTime);
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public String generateFlightsForWeek(FlightsGenerationRequestDTO request) throws Exception {
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Begin transaction

            LocalDate start = request.getWeek_startAt();
            LocalDate end = request.getWeek_endAt();

            List<FlightScheduleEntity> schedules = flightScheduleDao.getAllWithTransactionHandling(connection);

            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                String dayAbbr = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH); // e.g., "Mon"

                for (FlightScheduleEntity schedule : schedules) {
                    if (schedule.getSchedule_days_of_week().contains(dayAbbr)) {

                        LocalDateTime departure = date.atTime(schedule.getSchedule_departure_time());
                        LocalDateTime arrival = date.atTime(schedule.getSchedule_arrival_time());

                        if (arrival.isBefore(departure)) {
                            arrival = arrival.plusDays(1); // Overnight flight
                        }
                        
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
                        String flightCode = schedule.getSchedule_flight_code() + formatter.format(date);

                        FlightEntity flight = new FlightEntity();
                        flight.setFlight_code(flightCode);
                        flight.setFlight_departure_time(departure);
                        flight.setFlight_arrival_time(arrival);
                        flight.setFlight_airline_name(schedule.getSchedule_airline_name());

                        //Fk Object
                        flight.setFlightSchedule(FlightScheduleMapper.toFkObjectEntity(schedule.getSchedule_id()));

                        //Fk Object
                        flight.setRoute(RouteMapper.toFkObjectEntity(schedule.getRoute().getRoute_id()));

                        //Fk Object
                        flight.setAircraftModel(AircraftModelMapper.toFkObjectEntity(schedule.getAircraftModel().getAircraftModel_id()));

                        boolean inserted = flightDao.saveWithTransactionHandling(connection, flight);
                        if (!inserted) {
                            connection.rollback();
                            return "Failed to insert flight on " + date;
                        }
                    }
                }
            }

            connection.commit();
            return "Flights generated successfully.";

        } catch (SQLException | ClassNotFoundException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            throw ex;
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
    public List<FlightResponseDTO> getAllFlightsWithFlightClassPriceInfoBySearchKey(String searchKey) throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAllFlightsWithFlightClassPriceInfoBySearchKey(searchKey);
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightResponseDTO> getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(Integer routeId, LocalDateTime departureTime) throws Exception {
        List<FlightResponseDTO> dtos = new ArrayList<>();
        List<FlightEntity> flightEntities = flightDao.getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(routeId, departureTime);
        for (FlightEntity entity : flightEntities) {
            dtos.add(FlightMapper.toDTO(entity));
        }
        return dtos;
    }
}
