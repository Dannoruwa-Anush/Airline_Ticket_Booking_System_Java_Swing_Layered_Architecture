/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myapp.dao.DaoFactory;
import myapp.dao.ISuperDAO;
import myapp.dao.custom.IPassengerProfileDao;
import myapp.dao.custom.IUserDao;
import myapp.dto.user.UserLoginRequestDTO;
import myapp.dto.user.UserResponseDTO;
import myapp.dto.user.UserRequestDTO;
import myapp.entity.UserEntity;
import myapp.mapper.UserMapper;
import myapp.service.custom.IUserService;

/**
 *
 * @author HP
 */
public class UserServiceImpl implements IUserService{

    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
     //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IUserDao userDao;
    
    //For safe parent table delete
    private IPassengerProfileDao passengerProfileDao; 
    
    //constructor
    public UserServiceImpl(){
        ISuperDAO iSuperDAOUser = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER);
        
        if(iSuperDAOUser instanceof IUserDao iUserDao){
            userDao = iUserDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IUserDao");
        }
        
        ISuperDAO iSuperDAOPassengerProfile = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.PASSENFER_PROFILE);
        
        if(iSuperDAOPassengerProfile instanceof IPassengerProfileDao iPassengerProfileDao){
            passengerProfileDao = iPassengerProfileDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IPassengerProfileDao");
        }
    }
    //------- [End : downcast] ------------------------------

    @Override
    public String addUser(UserRequestDTO userRequestDTO) throws Exception {
        UserEntity user = UserMapper.toSaveEntity(userRequestDTO);
        boolean success = userDao.save(user);
        if (success) {
            return "Successfully Registered";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateUser(Integer id, UserRequestDTO userSaveDTO) throws Exception {
        UserEntity user = UserMapper.toUpdateEntity(id, userSaveDTO);
   
        if (userDao.update(user)) {
            LOGGER.log(Level.INFO, "User with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update user with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteUser(Integer id) throws Exception {        
        boolean hasPassengerWithUser = passengerProfileDao.hasPassengerWithUser(id);

        if (hasPassengerWithUser) {
            LOGGER.log(Level.INFO, "Cannot delete user with ID {0} because it has associated passenger_profile", id);
            return "Cannot delete user; there are passenger_profile associated with it";
        }	

        boolean deleteSuccessful = userDao.delete(id);
        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "User with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete user with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public UserResponseDTO getUserById(Integer id) throws Exception {
        UserEntity userEntity = userDao.getById(id);
        if (userEntity != null) {
            UserResponseDTO  response = UserMapper.toDTO(userEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() throws Exception {
        List<UserResponseDTO> dtos = new ArrayList<>();
        List<UserEntity> userEntities = userDao.getAll();
        for (UserEntity entity : userEntities) {
            dtos.add(UserMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public UserResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO) throws Exception {
        UserEntity user = UserMapper.toLoginEntity(userLoginRequestDTO);
        
        UserEntity userEntity = userDao.loginUser(user);
        if (userEntity != null) {
            UserResponseDTO  response = UserMapper.toDTO(userEntity);
            return response;
        } else {
            return null;
        }
    }
    
}
