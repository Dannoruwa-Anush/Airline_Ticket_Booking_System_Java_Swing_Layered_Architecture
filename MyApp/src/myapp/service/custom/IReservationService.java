/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.reservation.ReservationRequestDTO;
import myapp.dto.reservation.ReservationResponseDTO;
import myapp.dto.reservation.ReservationStatusChangeRequestDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IReservationService extends ISuperService{
    String addReservation(ReservationRequestDTO reservationRequestDTO) throws Exception;
    String updateReservationStatus(Integer id, ReservationStatusChangeRequestDTO reservationStatusChangeRequestDTO) throws Exception;
    String deleteReservation(Integer id) throws Exception;
    ReservationResponseDTO getReservationById(Integer id) throws Exception;
    List<ReservationResponseDTO> getAllReservations() throws Exception;
    
    List<ReservationResponseDTO> getAllReservationBySearchKey(String searchKey)throws Exception;
    List<ReservationResponseDTO> getAllReservationBySearchKeyAndPassengerProfileId(Integer passengerId, String searchKey)throws Exception;
    List<ReservationResponseDTO> getAllReservationByPassengerProfileId(Integer passengerProfileId)throws Exception;
    List<ReservationResponseDTO> getAllReservationByStatus(String status)throws Exception;
}
