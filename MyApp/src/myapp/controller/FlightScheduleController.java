/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.flightSchedule.FlightScheduleRequestDTO;
import myapp.dto.flightSchedule.FlightScheduleResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IFlightScheduleService;

/**
 *
 * @author HP
 */
public class FlightScheduleController {
  
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IFlightScheduleService flightScheduleService;

    //constructor
    public FlightScheduleController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.FLIGHT_SCHEDULE);
        
        if (iSuperService instanceof IFlightScheduleService iFlightScheduleService) { 
            flightScheduleService = iFlightScheduleService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IFlightScheduleService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addFlightSchedule(FlightScheduleRequestDTO flightScheduleRequestDTO) throws Exception {
        return flightScheduleService.addFlightSchedule(flightScheduleRequestDTO);
    }

    public String updateFlightSchedule(Integer id, FlightScheduleRequestDTO flightScheduleRequestDTO) throws Exception {
        return flightScheduleService.updateFlightSchedule(id, flightScheduleRequestDTO);
    }

    public String deleteFlightSchedule(Integer id) throws Exception {
        return flightScheduleService.deleteFlightSchedule(id);
    }

    public FlightScheduleResponseDTO getFlightScheduleById(Integer id) throws Exception {
        return flightScheduleService.getFlightScheduleById(id);
    }

    public List<FlightScheduleResponseDTO> getAllFlightSchedules() throws Exception {
        return flightScheduleService.getAllFlightSchedules();
    }

    public List<FlightScheduleResponseDTO> getAllFlightSchedulesBySearchKey(String searchKey) throws Exception {
        return flightScheduleService.getAllFlightSchedulesBySearchKey(searchKey);
    }
}
