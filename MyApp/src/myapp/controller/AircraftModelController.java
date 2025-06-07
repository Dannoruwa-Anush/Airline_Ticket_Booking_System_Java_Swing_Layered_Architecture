/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.aircraftModel.AircraftModelRequestDTO;
import myapp.dto.aircraftModel.AircraftModelResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IAircraftModelService;

/**
 *
 * @author HP
 */
public class AircraftModelController {
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IAircraftModelService aircraftModelService;

    //constructor
    public AircraftModelController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.AIRCRAFT_MODEL);
        
        if (iSuperService instanceof IAircraftModelService iAircraftModelService) { 
            aircraftModelService = iAircraftModelService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IAircraftModelService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addAircraftModel(AircraftModelRequestDTO aircraftModelRequestDTO) throws Exception {
        return aircraftModelService.addAircraftModel(aircraftModelRequestDTO);
    }

    public String updateAircraftModel(Integer id, AircraftModelRequestDTO aircraftModelRequestDTO) throws Exception {
        return aircraftModelService.updateAircraftModel(id, aircraftModelRequestDTO);
    }

    public String deleteAircraftModel(Integer id) throws Exception {
        return aircraftModelService.deleteAircraftModel(id);
    }
    
    public AircraftModelResponseDTO getAircraftModelById(Integer id) throws Exception {
        return aircraftModelService.getAircraftModelById(id);
    }

    public List<AircraftModelResponseDTO> getAllAircraftModels() throws Exception {
        return aircraftModelService.getAllAircraftModels();
    }
   
    public List<AircraftModelResponseDTO> getAllAircraftModelsBySearchKey(String searchKey) throws Exception{
        return aircraftModelService.getAllAircraftModelsBySearchKey(searchKey);
    }
}
