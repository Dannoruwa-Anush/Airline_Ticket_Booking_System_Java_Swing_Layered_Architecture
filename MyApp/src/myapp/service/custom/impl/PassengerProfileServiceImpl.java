/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import java.util.ArrayList;
import java.util.List;
import myapp.dto.passengerProfile.PassengerProfileRequestDTO;
import myapp.dto.passengerProfile.PassengerProfileResponseDTO;
import myapp.service.custom.IPassengerProfileService;

import java.util.logging.Level;
import java.util.logging.Logger;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IPassengerProfileDao;
import myapp.dao.custom.IReservationDao;
import myapp.entity.PassengerProfileEntity;
import myapp.mapper.PassengerProfileMapper;

/**
 *
 * @author HP
 */
public class PassengerProfileServiceImpl implements IPassengerProfileService {

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(PassengerProfileServiceImpl.class.getName());

    //------- [Start : downcast] ------------------------------
    //reference of type ISuperService (parent class)
    private IPassengerProfileDao passengerProfileDao;

    //For safe parent table delete
    private IReservationDao reservationDao;

    //constructor
    public PassengerProfileServiceImpl() {
        ISuperDAO iSuperDAOPassengerProfile = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.PASSENFER_PROFILE);

        if (iSuperDAOPassengerProfile instanceof IPassengerProfileDao iPassengerProfileDao) {
            passengerProfileDao = iPassengerProfileDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IPassengerProfileDao");
        }

        ISuperDAO iSuperDAOReservation = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.RESERVATION);

        if (iSuperDAOReservation instanceof IReservationDao iReservationDao) {
            reservationDao = iReservationDao; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned dao is not an instance of IReservationDao");
        }
    }
    //------- [End : downcast] ------------------------------

    @Override
    public String addPassengerProfile(PassengerProfileRequestDTO passengerProfileRequestDTO) throws Exception {
        PassengerProfileEntity passengerProfile = PassengerProfileMapper.toSaveEntity(passengerProfileRequestDTO);
        boolean success = passengerProfileDao.save(passengerProfile);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updatePassengerProfile(Integer id, PassengerProfileRequestDTO passengerProfileRequestDTO) throws Exception {
        PassengerProfileEntity passengerProfile = PassengerProfileMapper.toUpdateEntity(id, passengerProfileRequestDTO);

        if (passengerProfileDao.update(passengerProfile)) {
            LOGGER.log(Level.INFO, "Passenger Profile with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update passenger profile with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deletePassengerProfile(Integer id) throws Exception {
        boolean hasReservationWithPassengerProfile = reservationDao.hasReservationWithPassengerProfile(id);

        if (hasReservationWithPassengerProfile) {
            LOGGER.log(Level.INFO, "Cannot delete passenger profile with ID {0} because it has associated reservations", id);
            return "Cannot delete passenger profile; there are reservations associated with it";
        }

        boolean deleteSuccessful = passengerProfileDao.delete(id);
        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "Passenger profile with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete passenger profile with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public PassengerProfileResponseDTO getPassengerProfileById(Integer id) throws Exception {
        PassengerProfileEntity passengerProfileEntity = passengerProfileDao.getById(id);
        if (passengerProfileEntity != null) {
            PassengerProfileResponseDTO response = PassengerProfileMapper.toDTO(passengerProfileEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<PassengerProfileResponseDTO> getAllPassengerProfiles() throws Exception {
        List<PassengerProfileResponseDTO> dtos = new ArrayList<>();
        List<PassengerProfileEntity> passengerProfileEntities = passengerProfileDao.getAll();
        for (PassengerProfileEntity entity : passengerProfileEntities) {
            dtos.add(PassengerProfileMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<PassengerProfileResponseDTO> getAllPassengerProfilesBySearchKey(String searchKey) throws Exception {
        List<PassengerProfileResponseDTO> dtos = new ArrayList<>();
        List<PassengerProfileEntity> passengerProfileEntities = passengerProfileDao.getAllBySearchKey(searchKey);
        for (PassengerProfileEntity entity : passengerProfileEntities) {
            dtos.add(PassengerProfileMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public PassengerProfileResponseDTO getPassengerProfileByUserId(Integer userId) throws Exception {
        PassengerProfileEntity passengerProfileEntity = passengerProfileDao.getByUserId(userId);
        if (passengerProfileEntity != null) {
            PassengerProfileResponseDTO response = PassengerProfileMapper.toDTO(passengerProfileEntity);
            return response;
        } else {
            return null;
        }
    }

}
