/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.flightClassPrice.FlightClassPriceRequestDTO;
import myapp.dto.flightClassPrice.FlightClassPriceResponseDTO;
import myapp.entity.compositeKey.FlightBookingClassCompKey;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IFlightClassPriceService;

/**
 *
 * @author HP
 */
public class FlightClassPriceController {
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IFlightClassPriceService flightClassPriceService;

    //constructor
    public FlightClassPriceController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.FLIGHT_CLASS_PRICE);
        
        if (iSuperService instanceof IFlightClassPriceService iFlightClassPriceService) { 
            flightClassPriceService = iFlightClassPriceService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IFlightClassPriceService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addFlightClassPrice(FlightClassPriceRequestDTO flightClassPriceRequestDTO) throws Exception {
        return flightClassPriceService.addFlightClassPrice(flightClassPriceRequestDTO);
    }

    public String updateFlightClassPrice(FlightBookingClassCompKey compId, FlightClassPriceRequestDTO flightClassPriceRequestDTO) throws Exception {
        return flightClassPriceService.updateFlightClassPrice(compId, flightClassPriceRequestDTO);
    }

    public String deleteFlightClassPrice(FlightBookingClassCompKey compId) throws Exception {
        return flightClassPriceService.deleteFlightClassPrice(compId);
    }

    public FlightClassPriceResponseDTO getFlightClassPriceById(FlightBookingClassCompKey compId) throws Exception {
        return flightClassPriceService.getFlightClassPriceById(compId);
    }

    public List<FlightClassPriceResponseDTO> getAllFlightClassPrices() throws Exception {
        return flightClassPriceService.getAllFlightClassPrices();
    }

    public List<FlightClassPriceResponseDTO> getAllFlightClassPricesBySearchKey(String searchKey) throws Exception {
        return flightClassPriceService.getAllFlightClassPricesBySearchKey(searchKey);
    }
    
    public String addBulkFlightClassPrice(List<FlightClassPriceRequestDTO> flightClassPriceRequestDTOList) throws Exception {
        return flightClassPriceService.addBulkFlightClassPrice(flightClassPriceRequestDTOList);
    }
    
    public String updateBulkFlightClassPrice(List<FlightClassPriceRequestDTO> flightClassPriceRequestDTOList) throws Exception {
        return flightClassPriceService.updateBulkFlightClassPrice(flightClassPriceRequestDTOList);
    }
    
    public List<FlightClassPriceResponseDTO> getAllFlightClassPriceByFlightId(Integer flightId) throws Exception {
        return flightClassPriceService.getAllFlightClassPriceByFlightId(flightId);
    }
    
    public String deleteAllFlightClassPriceByFlightId(Integer flightId) throws Exception {
        return flightClassPriceService.deleteAllFlightClassPriceByFlightId(flightId);
    }
}
