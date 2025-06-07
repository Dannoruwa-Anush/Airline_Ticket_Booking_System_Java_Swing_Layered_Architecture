/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.flightClassPrice.FlightClassPriceRequestDTO;
import myapp.dto.flightClassPrice.FlightClassPriceResponseDTO;
import myapp.entity.compositeKey.FlightBookingClassCompKey;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IFlightClassPriceService extends ISuperService{
    String addFlightClassPrice(FlightClassPriceRequestDTO flightClassPriceRequestDTO) throws Exception;
    String updateFlightClassPrice(FlightBookingClassCompKey compId, FlightClassPriceRequestDTO flightClassPriceRequestDTO) throws Exception;
    String deleteFlightClassPrice(FlightBookingClassCompKey compId) throws Exception;
    FlightClassPriceResponseDTO getFlightClassPriceById(FlightBookingClassCompKey compId) throws Exception;
    List<FlightClassPriceResponseDTO> getAllFlightClassPrices() throws Exception;
    
    List<FlightClassPriceResponseDTO> getAllFlightClassPricesBySearchKey(String searchKey)throws Exception;   
    String addBulkFlightClassPrice(List<FlightClassPriceRequestDTO> flightClassPriceRequestDTOList) throws Exception;
    
    String updateBulkFlightClassPrice(List<FlightClassPriceRequestDTO> flightClassPriceRequestDTOList) throws Exception;
    
    List<FlightClassPriceResponseDTO> getAllFlightClassPriceByFlightId(Integer flightId)throws Exception;
    String deleteAllFlightClassPriceByFlightId(Integer flightId) throws Exception;
}
