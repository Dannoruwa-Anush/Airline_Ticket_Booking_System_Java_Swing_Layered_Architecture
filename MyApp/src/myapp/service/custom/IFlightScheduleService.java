/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.flightSchedule.FlightScheduleRequestDTO;
import myapp.dto.flightSchedule.FlightScheduleResponseDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IFlightScheduleService extends ISuperService{
    String addFlightSchedule(FlightScheduleRequestDTO flightScheduleRequestDTO) throws Exception;
    String updateFlightSchedule(Integer id, FlightScheduleRequestDTO flightScheduleRequestDTO) throws Exception;
    String deleteFlightSchedule(Integer id) throws Exception;
    FlightScheduleResponseDTO getFlightScheduleById(Integer id) throws Exception;
    List<FlightScheduleResponseDTO> getAllFlightSchedules() throws Exception;
    
    List<FlightScheduleResponseDTO> getAllFlightSchedulesBySearchKey(String searchKey)throws Exception;
}
