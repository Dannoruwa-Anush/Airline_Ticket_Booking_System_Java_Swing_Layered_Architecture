/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.time.LocalDateTime;
import java.util.List;
import myapp.dto.flight.FlightRequestDTO;
import myapp.dto.flight.FlightResponseDTO;
import myapp.dto.flightsGeneration.FlightsGenerationRequestDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IFlightService extends ISuperService{
    String addFlight(FlightRequestDTO flightRequestDTO) throws Exception;
    String updateFlight(Integer id, FlightRequestDTO flightRequestDTO) throws Exception;
    String deleteFlight(Integer id) throws Exception;
    FlightResponseDTO getFlightById(Integer id) throws Exception;
    List<FlightResponseDTO> getAllFlights() throws Exception;
    
    List<FlightResponseDTO> getAllFlightsBySearchKey(String searchKey)throws Exception;
    List<FlightResponseDTO> getAllFlightsWithFlightClassPriceInfoBySearchKey(String searchKey)throws Exception;
    List<FlightResponseDTO> getAllFlightsWithoutFlightClassPriceInfo()throws Exception;
    List<FlightResponseDTO> getAllFlightsWithFlightClassPriceInfo()throws Exception;
    
    List<FlightResponseDTO> getAllFlightsForRouteAndDepartureTime(Integer routeId, LocalDateTime departureTime)throws Exception;
    List<FlightResponseDTO> getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(Integer routeId, LocalDateTime departureTime)throws Exception;

    String generateFlightsForWeek(FlightsGenerationRequestDTO request) throws Exception;
}
