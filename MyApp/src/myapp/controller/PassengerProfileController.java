/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.passengerProfile.PassengerProfileRequestDTO;
import myapp.dto.passengerProfile.PassengerProfileResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IPassengerProfileService;

/**
 *
 * @author HP
 */
public class PassengerProfileController {

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IPassengerProfileService passengerProfileService;

    //constructor
    public PassengerProfileController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.PASSENFER_PROFILE);

        if (iSuperService instanceof IPassengerProfileService iPassengerProfileService) {
            passengerProfileService = iPassengerProfileService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IPassengerProfileService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addPassengerProfile(PassengerProfileRequestDTO passengerProfileRequestDTO) throws Exception {
        return passengerProfileService.addPassengerProfile(passengerProfileRequestDTO);
    }

    public String updatePassengerProfile(Integer id, PassengerProfileRequestDTO passengerProfileRequestDTO) throws Exception {
        return passengerProfileService.updatePassengerProfile(id, passengerProfileRequestDTO);
    }

    public String deletePassengerProfile(Integer id) throws Exception {
        return passengerProfileService.deletePassengerProfile(id);
    }

    public PassengerProfileResponseDTO getPassengerProfileById(Integer id) throws Exception {
        return passengerProfileService.getPassengerProfileById(id);
    }

    public List<PassengerProfileResponseDTO> getAllPassengerProfiles() throws Exception {
        return passengerProfileService.getAllPassengerProfiles();
    }

    public List<PassengerProfileResponseDTO> getAllPassengerProfilesBySearchKey(String searchKey) throws Exception {
        return passengerProfileService.getAllPassengerProfilesBySearchKey(searchKey);
    }
    
    public PassengerProfileResponseDTO getPassengerProfileByUserId(Integer id) throws Exception {
        return passengerProfileService.getPassengerProfileByUserId(id);
    }
}
