/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.airport.AirportRequestDTO;
import myapp.dto.airport.AirportResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IAirportService;

/**
 *
 * @author HP
 */
public class AirportController {
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IAirportService airportService;

    //constructor
    public AirportController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.AIRPORT);
        
        if (iSuperService instanceof IAirportService iAirportService) { 
            airportService = iAirportService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IAirportService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addAirport(AirportRequestDTO airportRequestDTO) throws Exception {
        return airportService.addAirport(airportRequestDTO);
    }

    public String updateAirport(Integer id, AirportRequestDTO airportRequestDTO) throws Exception {
        return airportService.updateAirport(id, airportRequestDTO);
    }

    public String deleteAirport(Integer id) throws Exception {
        return airportService.deleteAirport(id);
    }
    
    public AirportResponseDTO getAirportById(Integer id) throws Exception {
        return airportService.getAirportById(id);
    }

    public List<AirportResponseDTO> getAllAirports() throws Exception {
        return airportService.getAllAirports();
    }
   
    public List<AirportResponseDTO> getAllAirportsBySearchKey(String searchKey) throws Exception{
        return airportService.getAllAirportsBySearchKey(searchKey);
    }
}
