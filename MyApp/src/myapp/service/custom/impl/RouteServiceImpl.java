/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IFlightDao;
import myapp.dao.custom.IFlightScheduleDao;
import myapp.dao.custom.IRouteDao;
import myapp.dto.route.RouteRequestDTO;
import myapp.dto.route.RouteResponseDTO;
import myapp.entity.RouteEntity;
import myapp.mapper.RouteMapper;
import myapp.service.custom.IRouteService;

/**
 *
 * @author HP
 */
public class RouteServiceImpl implements IRouteService{

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(RouteServiceImpl.class.getName());
    
    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IRouteDao routeDao;
    
    //For safe parent table delete
    private IFlightScheduleDao flightScheduleDao;
    
    //For safe parent table delete
    private IFlightDao flightDao;
    
    //constructor
    public RouteServiceImpl(){
        ISuperDAO iSuperDAORoute = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROUTE);
        
        if(iSuperDAORoute instanceof IRouteDao iRouteDao){
            routeDao = iRouteDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IRouteDao");
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
    public String addRoute(RouteRequestDTO routeRequestDTO) throws Exception {
        RouteEntity route = RouteMapper.toSaveEntity(routeRequestDTO);
        boolean success = routeDao.save(route);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateRoute(Integer id, RouteRequestDTO routeRequestDTO) throws Exception {
        RouteEntity route = RouteMapper.toUpdateEntity(id, routeRequestDTO);
   
        if (routeDao.update(route)) {
            LOGGER.log(Level.INFO, "Roure with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update route with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteRoute(Integer id) throws Exception {
        // Check if there are any flightSchedules associated with the route before deleting it
        boolean hasFlightSchedulesWithRoute = flightScheduleDao.hasFlightSchedulesWithRoute(id);

        if (hasFlightSchedulesWithRoute) {
            LOGGER.log(Level.INFO, "Cannot delete route with ID {0} because it has associated flight schedules", id);
            return "Cannot delete route; there are flight schedules associated with it";
        }
        
        // Check if there are any flights associated with the route before deleting it
        boolean hasFlightsWithRoute = flightDao.hasFlightsWithRoute(id);

        if (hasFlightsWithRoute) {
            LOGGER.log(Level.INFO, "Cannot delete route with ID {0} because it has associated flights", id);
            return "Cannot delete route; there are flights associated with it";
        }

        // Proceed with the delete operation if no flights are associated
        boolean deleteSuccessful = routeDao.delete(id);

        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Route with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete route with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public RouteResponseDTO getRouteById(Integer id) throws Exception {
        RouteEntity routeEntity = routeDao.getById(id);
        if (routeEntity != null) {
            RouteResponseDTO  response = RouteMapper.toDTO(routeEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<RouteResponseDTO> getAllRoutes() throws Exception {
        List<RouteResponseDTO> dtos = new ArrayList<>();
        List<RouteEntity> routeEntities = routeDao.getAll();
        for (RouteEntity entity : routeEntities) {
            dtos.add(RouteMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<RouteResponseDTO> getAllRoutesBySearchKey(String searchKey) throws Exception {
        List<RouteResponseDTO> dtos = new ArrayList<>();
        List<RouteEntity> routeEntities = routeDao.getAllBySearchKey(searchKey);
        for (RouteEntity entity : routeEntities) {
            dtos.add(RouteMapper.toDTO(entity));
        }
        return dtos;
    }
    
}
