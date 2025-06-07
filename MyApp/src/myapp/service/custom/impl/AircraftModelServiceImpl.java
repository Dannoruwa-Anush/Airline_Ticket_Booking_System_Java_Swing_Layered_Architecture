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
import myapp.dao.custom.IAircraftModelDao;
import myapp.dao.custom.IFlightDao;
import myapp.dao.custom.IFlightScheduleDao;
import myapp.dto.aircraftModel.AircraftModelRequestDTO;
import myapp.dto.aircraftModel.AircraftModelResponseDTO;
import myapp.entity.AircraftModelEntity;
import myapp.mapper.AircraftModelMapper;
import myapp.service.custom.IAircraftModelService;

/**
 *
 * @author HP
 */
public class AircraftModelServiceImpl implements IAircraftModelService{

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(AircraftModelServiceImpl.class.getName());
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IAircraftModelDao aircraftModelDao;
    
    //For safe parent table delete
    private IFlightScheduleDao flightScheduleDao;
    
    //For safe parent table delete
    private IFlightDao flightDao;
    
    //constructor
    public AircraftModelServiceImpl(){
        ISuperDAO iSuperDAOAircraftModel = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.AIRCRAFT_MODEL);
        
        if(iSuperDAOAircraftModel instanceof IAircraftModelDao iAircraftModelDao){
           aircraftModelDao = iAircraftModelDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IAircraftModelDao");
        }
        
        ISuperDAO iSuperDAOFlightSchedule = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT_SCHEDULE);
        
        if(iSuperDAOFlightSchedule instanceof IFlightScheduleDao iFlightScheduleDao){
            flightScheduleDao = iFlightScheduleDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightScheduleDao");
        }
        
        ISuperDAO iSuperDAOFlight = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT);
        
        if(iSuperDAOFlight instanceof IFlightDao iFlightDao){
            flightDao = iFlightDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightDao");
        }
    }
    //------- [End : downcast] ------------------------------
    
    @Override
    public String addAircraftModel(AircraftModelRequestDTO aircraftModelRequestDTO) throws Exception {
        AircraftModelEntity aircraftModel = AircraftModelMapper.toSaveEntity(aircraftModelRequestDTO);
        boolean success = aircraftModelDao.save(aircraftModel);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateAircraftModel(Integer id, AircraftModelRequestDTO aircraftModelRequestDTO) throws Exception {
        AircraftModelEntity aircraftModel = AircraftModelMapper.toUpdateEntity(id, aircraftModelRequestDTO);
        if (aircraftModelDao.update(aircraftModel)) {
            LOGGER.log(Level.INFO, "Aircraft model with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update aircraft model with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteAircraftModel(Integer id) throws Exception {
       // Check if there are any flightSchedules associated with the aircraftModel before deleting it
        boolean hasFlightSchedulesWithAircraftModel = flightScheduleDao.hasFlightSchedulesWithAircraftModel(id);

        if (hasFlightSchedulesWithAircraftModel) {
            LOGGER.log(Level.INFO, "Cannot delete aircraft model with ID {0} because it has associated flight schedules", id);
            return "Cannot delete aircraft model; there are flight schedules associated with it";
        }

        
        // Check if there are any flights associated with the aircraftModel before deleting it
        boolean hasFlightsWithAircraftModel = flightDao.hasFlightsWithAircraftModel(id);

        if (hasFlightsWithAircraftModel) {
            LOGGER.log(Level.INFO, "Cannot delete aircraft model with ID {0} because it has associated flights", id);
            return "Cannot delete aircraft model; there are flights associated with it";
        }

        // Proceed with the delete operation if no flights are associated
        boolean deleteSuccessful = aircraftModelDao.delete(id);

        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Aircraft model with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete aircraft model with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public AircraftModelResponseDTO getAircraftModelById(Integer id) throws Exception {
        AircraftModelEntity aircraftModelEntity = aircraftModelDao.getById(id);
        if (aircraftModelEntity != null) {
            AircraftModelResponseDTO response = AircraftModelMapper.toDTO(aircraftModelEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<AircraftModelResponseDTO> getAllAircraftModels() throws Exception {
        List<AircraftModelResponseDTO> dtos = new ArrayList<>();
        List<AircraftModelEntity> aircraftModelEntities = aircraftModelDao.getAll();
        for (AircraftModelEntity entity : aircraftModelEntities) {
            dtos.add(AircraftModelMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<AircraftModelResponseDTO> getAllAircraftModelsBySearchKey(String searchKey) throws Exception {
        List<AircraftModelResponseDTO> dtos = new ArrayList<>();
        List<AircraftModelEntity> aircraftModelEntities = aircraftModelDao.getAllBySearchKey(searchKey);
        for (AircraftModelEntity entity : aircraftModelEntities) {
            dtos.add(AircraftModelMapper.toDTO(entity));
        }
        return dtos;
    }
    
}
