/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.reservation.ReservationRequestDTO;
import myapp.dto.reservation.ReservationResponseDTO;
import myapp.dto.reservation.ReservationStatusChangeRequestDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IReservationService;

/**
 *
 * @author HP
 */
public class ReservationController {
    
    //------- [Start : downcast] ------------------------------

    //reference of type ISuperService (parent class)
    private IReservationService reservationService;

    //constructor
    public ReservationController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.RESERVATION);

        if (iSuperService instanceof IReservationService iReservationService) {
            reservationService = iReservationService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IReservationService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addReservation(ReservationRequestDTO reservationRequestDTO) throws Exception {
        return reservationService.addReservation(reservationRequestDTO);
    }

    public String updateReservationStatus(Integer id, ReservationStatusChangeRequestDTO reservationStatusChangeRequestDTO) throws Exception {
        return reservationService.updateReservationStatus(id, reservationStatusChangeRequestDTO);
    }

    public String deleteReservation(Integer id) throws Exception {
        return reservationService.deleteReservation(id);
    }

    public ReservationResponseDTO getReservationById(Integer id) throws Exception {
        return reservationService.getReservationById(id);
    }

    public List<ReservationResponseDTO> getAllReservations() throws Exception {
        return reservationService.getAllReservations();
    }

    public List<ReservationResponseDTO> getAllReservationBySearchKey(String searchKey) throws Exception {
        return reservationService.getAllReservationBySearchKey(searchKey);
    }

    public List<ReservationResponseDTO> getAllReservationByPassengerProfileId(Integer passengerProfileId) throws Exception {
        return reservationService.getAllReservationByPassengerProfileId(passengerProfileId);
    }

    public List<ReservationResponseDTO> getAllReservationByStatus(String status) throws Exception {
        return reservationService.getAllReservationByStatus(status);
    }
    
    public List<ReservationResponseDTO> getAllReservationBySearchKeyAndPassengerProfileId(Integer passengerId, String searchKey) throws Exception {
        return reservationService.getAllReservationBySearchKeyAndPassengerProfileId(passengerId, searchKey);
    }
}
