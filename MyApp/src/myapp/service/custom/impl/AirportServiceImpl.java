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
import myapp.dao.custom.IAirportDao;
import myapp.dao.custom.IRouteDao;
import myapp.dto.airport.AirportRequestDTO;
import myapp.dto.airport.AirportResponseDTO;
import myapp.entity.AirportEntity;
import myapp.mapper.AirportMapper;
import myapp.service.custom.IAirportService;

/**
 *
 * @author HP
 */
public class AirportServiceImpl implements IAirportService{
    
    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(AirportServiceImpl.class.getName());
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IAirportDao airportDao;
    
    //For safe parent table delete
    private IRouteDao routeDao;
    
    //constructor
    public AirportServiceImpl(){
        ISuperDAO iSuperDAOAirport = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.AIRPORT);
        
        if(iSuperDAOAirport instanceof IAirportDao iAirportDao){
           airportDao = iAirportDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IAirportDao");
        }
        
        ISuperDAO iSuperDAORoute = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROUTE);
        
        if(iSuperDAORoute instanceof IRouteDao iRouteDao){
           routeDao = iRouteDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IRouteDao");
        }
    }
    //------- [End : downcast] ------------------------------
    
    @Override
    public String addAirport(AirportRequestDTO airportRequestDTO) throws Exception {
        AirportEntity airport = AirportMapper.toSaveEntity(airportRequestDTO);
        boolean success = airportDao.save(airport);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateAirport(Integer id, AirportRequestDTO airportRequestDTO) throws Exception {
        AirportEntity airport = AirportMapper.toUpdateEntity(id, airportRequestDTO);
        if (airportDao.update(airport)) {
            LOGGER.log(Level.INFO, "Airport with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update user role with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteAirport(Integer id) throws Exception {
        // Check if there are any routes associated with the airport before deleting it
        boolean hasRoutesWithAirport = routeDao.hasRoutesWithAirport(id);

        if (hasRoutesWithAirport) {
            LOGGER.log(Level.INFO, "Cannot delete airport with ID {0} because it has associated routes", id);
            return "Cannot delete airport; there are routes associated with it";
        }

        // Proceed with the delete operation if no routes are associated
        boolean deleteSuccessful = airportDao.delete(id);

        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Airport with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete airport with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public AirportResponseDTO getAirportById(Integer id) throws Exception {
        AirportEntity airportEntity = airportDao.getById(id);
        if (airportEntity != null) {
            AirportResponseDTO response = AirportMapper.toDTO(airportEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<AirportResponseDTO> getAllAirports() throws Exception {
        List<AirportResponseDTO> dtos = new ArrayList<>();
        List<AirportEntity> airportEntities = airportDao.getAll();
        for (AirportEntity entity : airportEntities) {
            dtos.add(AirportMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<AirportResponseDTO> getAllAirportsBySearchKey(String searchKey) throws Exception {
        List<AirportResponseDTO> dtos = new ArrayList<>();
        List<AirportEntity> airportEntities = airportDao.getAllBySearchKey(searchKey);
        for (AirportEntity entity : airportEntities) {
            dtos.add(AirportMapper.toDTO(entity));
        }
        return dtos;
    }
}
