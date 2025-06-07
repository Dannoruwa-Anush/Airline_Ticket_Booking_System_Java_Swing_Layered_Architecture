/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.airport.AirportRequestDTO;
import myapp.dto.airport.AirportResponseDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IAirportService extends ISuperService{
    String addAirport(AirportRequestDTO airportRequestDTO) throws Exception;
    String updateAirport(Integer id, AirportRequestDTO airportRequestDTO) throws Exception;
    String deleteAirport(Integer id) throws Exception;
    AirportResponseDTO getAirportById(Integer id) throws Exception;
    List<AirportResponseDTO> getAllAirports() throws Exception;
    
    List<AirportResponseDTO> getAllAirportsBySearchKey(String searchKey)throws Exception;
}
