/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.aircraftModel.AircraftModelRequestDTO;
import myapp.dto.aircraftModel.AircraftModelResponseDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IAircraftModelService extends ISuperService{
    String addAircraftModel(AircraftModelRequestDTO aircraftModelRequestDTO) throws Exception;
    String updateAircraftModel(Integer id, AircraftModelRequestDTO aircraftModelRequestDTO) throws Exception;
    String deleteAircraftModel(Integer id) throws Exception;
    AircraftModelResponseDTO getAircraftModelById(Integer id) throws Exception;
    List<AircraftModelResponseDTO> getAllAircraftModels() throws Exception;
    
    List<AircraftModelResponseDTO> getAllAircraftModelsBySearchKey(String searchKey)throws Exception;
}
