/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.passengerProfile.PassengerProfileRequestDTO;
import myapp.dto.passengerProfile.PassengerProfileResponseDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IPassengerProfileService extends ISuperService{
    String addPassengerProfile(PassengerProfileRequestDTO passengerProfileRequestDTO) throws Exception;
    String updatePassengerProfile(Integer id, PassengerProfileRequestDTO passengerProfileRequestDTO) throws Exception;
    String deletePassengerProfile(Integer id) throws Exception;
    PassengerProfileResponseDTO getPassengerProfileById(Integer id) throws Exception;
    List<PassengerProfileResponseDTO> getAllPassengerProfiles() throws Exception;
    
    List<PassengerProfileResponseDTO> getAllPassengerProfilesBySearchKey(String searchKey)throws Exception;
    PassengerProfileResponseDTO getPassengerProfileByUserId(Integer userId)throws Exception;
}
