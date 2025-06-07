/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IFlightClassPriceDao;
import myapp.dto.flightClassPrice.FlightClassPriceRequestDTO;
import myapp.dto.flightClassPrice.FlightClassPriceResponseDTO;
import myapp.entity.FlightClassPriceEntity;
import myapp.entity.compositeKey.FlightBookingClassCompKey;
import myapp.mapper.FlightClassPriceMapper;
import myapp.service.custom.IFlightClassPriceService;

/**
 *
 * @author HP
 */
public class FlightClassPriceServiceImpl implements IFlightClassPriceService{
    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(FlightClassPriceServiceImpl.class.getName());

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IFlightClassPriceDao flightClassPriceDao;

    //constructor
    public FlightClassPriceServiceImpl() {
        ISuperDAO iSuperDAO = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FLIGHT_CLASS_PRICE);

        if (iSuperDAO instanceof IFlightClassPriceDao iFlightClassPriceDao) {
            flightClassPriceDao = iFlightClassPriceDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IFlightClassPriceDao");
        }
    }
    //------- [End : downcast] ------------------------------

    @Override
    public String addFlightClassPrice(FlightClassPriceRequestDTO flightClassPriceRequestDTO) throws Exception {
        FlightClassPriceEntity flightClassPrice = FlightClassPriceMapper.toSaveEntity(flightClassPriceRequestDTO);
        boolean success = flightClassPriceDao.save(flightClassPrice);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateFlightClassPrice(FlightBookingClassCompKey compId, FlightClassPriceRequestDTO flightClassPriceRequestDTO) throws Exception {
        FlightClassPriceEntity flightClassPrice = FlightClassPriceMapper.toUpdateEntity(flightClassPriceRequestDTO);

        if (flightClassPriceDao.update(flightClassPrice)) {
            LOGGER.log(Level.INFO, "Flight class price with ID {0} updated successfully", compId);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update flight class price with ID {0}", compId);
            return "Fail";
        }
    }

    @Override
    public String deleteFlightClassPrice(FlightBookingClassCompKey compId) throws Exception {
        if (flightClassPriceDao.delete(compId)) {
            LOGGER.log(Level.INFO, "Flight class price with ID {0} deleted successfully", compId);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete flight class price with ID {0}", compId);
            return "Fail";
        }
    }

    @Override
    public FlightClassPriceResponseDTO getFlightClassPriceById(FlightBookingClassCompKey compId) throws Exception {
        FlightClassPriceEntity flightClassPriceEntity = flightClassPriceDao.getById(compId);
        if (flightClassPriceEntity != null) {
            FlightClassPriceResponseDTO response = FlightClassPriceMapper.toDTO(flightClassPriceEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<FlightClassPriceResponseDTO> getAllFlightClassPrices() throws Exception {
        List<FlightClassPriceResponseDTO> dtos = new ArrayList<>();
        List<FlightClassPriceEntity> flightClassPriceEntities = flightClassPriceDao.getAll();
        for (FlightClassPriceEntity entity : flightClassPriceEntities) {
            dtos.add(FlightClassPriceMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<FlightClassPriceResponseDTO> getAllFlightClassPricesBySearchKey(String searchKey) throws Exception {
        List<FlightClassPriceResponseDTO> dtos = new ArrayList<>();
        List<FlightClassPriceEntity> flightClassPriceEntities = flightClassPriceDao.getAllBySearchKey(searchKey);
        for (FlightClassPriceEntity entity : flightClassPriceEntities) {
            dtos.add(FlightClassPriceMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public String addBulkFlightClassPrice(List<FlightClassPriceRequestDTO> flightClassPriceRequestDTOList) throws Exception {
        List<FlightClassPriceEntity> entityList = new ArrayList<>();
        for (FlightClassPriceRequestDTO dto : flightClassPriceRequestDTOList) {
            entityList.add(FlightClassPriceMapper.toSaveEntity(dto));
        }
        
        boolean success = flightClassPriceDao.bulkSave(entityList);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateBulkFlightClassPrice(List<FlightClassPriceRequestDTO> flightClassPriceRequestDTOList) throws Exception {
        List<FlightClassPriceEntity> entityList = new ArrayList<>();
        for (FlightClassPriceRequestDTO dto : flightClassPriceRequestDTOList) {
            entityList.add(FlightClassPriceMapper.toUpdateEntity(dto));
        }
        
        if (flightClassPriceDao.bulkUpdate(entityList)) {
            //LOGGER.log(Level.INFO, "Flight class price with flight ID {0} updated successfully",);
            return "Successfully Updated";
        } else {
            //LOGGER.log(Level.INFO, "Failed to update flight class price with ID {0}", compId);
            return "Fail";
        }
    }    

    @Override
    public List<FlightClassPriceResponseDTO> getAllFlightClassPriceByFlightId(Integer flightId) throws Exception {
         List<FlightClassPriceResponseDTO> dtos = new ArrayList<>();
        List<FlightClassPriceEntity> flightClassPriceEntities = flightClassPriceDao.getAllByFlightId(flightId);
        for (FlightClassPriceEntity entity : flightClassPriceEntities) {
            dtos.add(FlightClassPriceMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public String deleteAllFlightClassPriceByFlightId(Integer flightId) throws Exception {
        if (flightClassPriceDao.deleteAllByFlightId(flightId)) {
            LOGGER.log(Level.INFO, "All flight class price associated with flight ID {0} deleted successfully", flightId);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete all flight class price associated with flight ID {0}", flightId);
            return "Fail";
        }
    }
}
