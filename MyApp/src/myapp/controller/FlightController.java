/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import myapp.dto.flight.FlightRequestDTO;
import myapp.dto.flight.FlightResponseDTO;
import myapp.dto.flightsGeneration.FlightsGenerationRequestDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IFlightService;

/**
 *
 * @author HP
 */
public class FlightController {
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IFlightService flightService;

    //constructor
    public FlightController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.FLIGHT);
        
        if (iSuperService instanceof IFlightService iFlightService) { 
            flightService = iFlightService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IFlightService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addFlight(FlightRequestDTO flightRequestDTO) throws Exception {
        return flightService.addFlight(flightRequestDTO);
    }

    public String updateFlight(Integer id, FlightRequestDTO flightRequestDTO) throws Exception {
        return flightService.updateFlight(id, flightRequestDTO);
    }

    public String deleteFlight(Integer id) throws Exception {
        return flightService.deleteFlight(id);
    }

    public FlightResponseDTO getFlightById(Integer id) throws Exception {
        return flightService.getFlightById(id);
    }

    public List<FlightResponseDTO> getAllFlights() throws Exception {
        return flightService.getAllFlights();
    }

    public List<FlightResponseDTO> getAllFlightsBySearchKey(String searchKey) throws Exception {
        return flightService.getAllFlightsBySearchKey(searchKey);
    }
    
    public List<FlightResponseDTO> getAllFlightsWithoutFlightClassPriceInfo() throws Exception {
        return flightService.getAllFlightsWithoutFlightClassPriceInfo();
    }
    
    public List<FlightResponseDTO> getAllFlightsWithFlightClassPriceInfo() throws Exception {
        return flightService.getAllFlightsWithFlightClassPriceInfo();
    }
    
    public List<FlightResponseDTO> getAllFlightsForRouteAndDepartureTime(Integer routeId, LocalDateTime departureTime) throws Exception {
        return flightService.getAllFlightsForRouteAndDepartureTime(routeId, departureTime);
    }
    
    public String generateFlightsForWeek(FlightsGenerationRequestDTO request) throws Exception {
        return flightService.generateFlightsForWeek(request);
    }
    
    public List<FlightResponseDTO> getAllFlightsWithFlightClassPriceInfoBySearchKey(String searchKey) throws Exception {
        return flightService.getAllFlightsWithFlightClassPriceInfoBySearchKey(searchKey);
    }
    
    public List<FlightResponseDTO> getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(Integer routeId, LocalDateTime departureTime) throws Exception {
        return flightService.getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(routeId, departureTime);
    }
}
