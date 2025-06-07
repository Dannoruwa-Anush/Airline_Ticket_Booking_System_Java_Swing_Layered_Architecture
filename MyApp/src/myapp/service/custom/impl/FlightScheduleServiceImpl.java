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
import myapp.dao.custom.IFlightDao;
import myapp.dao.custom.IFlightScheduleDao;
import myapp.dto.flightSchedule.FlightScheduleRequestDTO;
import myapp.dto.flightSchedule.FlightScheduleResponseDTO;
import myapp.entity.FlightScheduleEntity;
import myapp.mapper.FlightScheduleMapper;
import myapp.service.custom.IFlightScheduleService;

/**
 *
 * @author HP
 */
public class FlightScheduleServiceImpl implements IFlightScheduleService {

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(FlightScheduleServiceImpl.class.getName());

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IFlightScheduleDao flightScheduleDao;

    //For safe parent table delete
    private IFlightDao flightDao;
    
    //constructor
    public FlightScheduleServiceImpl() {
        ISuperDAO iSuperDAOFlightSchedule = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT_SCHEDULE);

        if (iSuperDAOFlightSchedule instanceof IFlightScheduleDao iFlightScheduleDao) {
            flightScheduleDao = iFlightScheduleDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightScheduleDao");
        }

        ISuperDAO iSuperDAOFlight = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT);

        if (iSuperDAOFlight instanceof IFlightDao iFlightDao) {
            flightDao = iFlightDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightDao");
        }
    }
    //------- [End : downcast] ------------------------------

    @Override
    public String addFlightSchedule(FlightScheduleRequestDTO flightScheduleRequestDTO) throws Exception {
        FlightScheduleEntity flightSchedule = FlightScheduleMapper.toSaveEntity(flightScheduleRequestDTO);
        boolean success = flightScheduleDao.save(flightSchedule);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateFlightSchedule(Integer id, FlightScheduleRequestDTO flightScheduleRequestDTO) throws Exception {
        FlightScheduleEntity flightSchedule = FlightScheduleMapper.toUpdateEntity(id, flightScheduleRequestDTO);

        if (flightScheduleDao.update(flightSchedule)) {
            LOGGER.log(Level.INFO, "Flight schedule with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update flight schedule with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteFlightSchedule(Integer id) throws Exception {
        boolean hasFlightWithFlightSchedule = flightDao.hasFlightsWithFlightSchedule(id);
        if (hasFlightWithFlightSchedule) {
            LOGGER.log(Level.INFO, "Cannot delete flight schedule with ID {0} because it has associated flights", id);
            return "Cannot delete flight schedule; there are flights associated with it";
        }

        boolean deleteSuccessful = flightScheduleDao.delete(id);
        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Flight schedule with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete flight schedule with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public FlightScheduleResponseDTO getFlightScheduleById(Integer id) throws Exception {
        FlightScheduleEntity fightScheduleEntity = flightScheduleDao.getById(id);
        if (fightScheduleEntity != null) {
            FlightScheduleResponseDTO response = FlightScheduleMapper.toDTO(fightScheduleEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<FlightScheduleResponseDTO> getAllFlightSchedules() throws Exception {
        List<FlightScheduleResponseDTO> dtos = new ArrayList<>();
        List<FlightScheduleEntity> flightScheduleEntities = flightScheduleDao.getAll();
        for (FlightScheduleEntity entity : flightScheduleEntities) {
            dtos.add(FlightScheduleMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightScheduleResponseDTO> getAllFlightSchedulesBySearchKey(String searchKey) throws Exception {
        List<FlightScheduleResponseDTO> dtos = new ArrayList<>();
        List<FlightScheduleEntity> flightScheduleEntities = flightScheduleDao.getAllBySearchKey(searchKey);
        for (FlightScheduleEntity entity : flightScheduleEntities) {
            dtos.add(FlightScheduleMapper.toDTO(entity));
        }
        return dtos;
    }

}
