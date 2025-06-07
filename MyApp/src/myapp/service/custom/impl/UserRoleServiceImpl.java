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
import myapp.dao.custom.IUserDao;
import myapp.dao.custom.IUserRoleDao;
import myapp.dto.userRole.UserRoleResponseDTO;
import myapp.dto.userRole.UserRoleRequestDTO;
import myapp.entity.UserRoleEntity;
import myapp.mapper.UserRoleMapper;
import myapp.service.custom.IUserRoleService;

/**
 *
 * @author HP
 */
public class UserRoleServiceImpl implements IUserRoleService{
    
    //Log : for update, delete
    private static final Logger LOGGER = Logger.getLogger(UserRoleServiceImpl.class.getName());
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IUserRoleDao userRoleDao;
    
    //For safe parent table delete
    private IUserDao userDao;
    
    //constructor
    public UserRoleServiceImpl(){
        ISuperDAO iSuperDAOUserRole = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER_ROLE);
        
        if(iSuperDAOUserRole instanceof IUserRoleDao iUserRoleDao){
            userRoleDao = iUserRoleDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IUserRoleDao");
        }
        
        ISuperDAO iSuperDAOUser = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER);
        
        if(iSuperDAOUser instanceof IUserDao iUserDao){
            userDao = iUserDao; //downcast (parent class -> child class)
        }
        else {
            throw new IllegalStateException("Returned dao is not an instance of IUserDao");
        }
    }
    //------- [End : downcast] ------------------------------
    
    
    @Override
    public String addUserRole(UserRoleRequestDTO userRoleRequestDTO) throws Exception {
        UserRoleEntity userRole = UserRoleMapper.toSaveEntity(userRoleRequestDTO);
        boolean success = userRoleDao.save(userRole);
        if (success) {
            return "Successfully Added";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateUserRole(Integer id, UserRoleRequestDTO userRoleRequestDTO) throws Exception {
        UserRoleEntity userRole = UserRoleMapper.toUpdateEntity(id, userRoleRequestDTO);
        if (userRoleDao.update(userRole)) {
            LOGGER.log(Level.INFO, "User role with ID {0} updated successfully", id);
            return "Successfully Updated";
        } else {
            LOGGER.log(Level.INFO, "Failed to update user role with ID {0}", id);
            return "Fail";
        }
    }

    @Override
    public String deleteUserRole(Integer id) throws Exception {
        // Check if there are any users associated with the role before deleting it
        boolean hasUsersWithRole = userDao.hasUsersWithRole(id);

        if (hasUsersWithRole) {
            LOGGER.log(Level.INFO, "Cannot delete user role with ID {0} because it has associated users", id);
            return "Cannot delete role; there are users associated with it";
        }

        // Proceed with the delete operation if no users are associated
        boolean deleteSuccessful = userRoleDao.delete(id);

        if (deleteSuccessful) {
            LOGGER.log(Level.INFO, "User role with ID {0} deleted successfully", id);
            return "Successfully Deleted";
        } else {
            LOGGER.log(Level.INFO, "Failed to delete user role with ID {0}", id);
            return "Fail";
        }
    }


    @Override
    public UserRoleResponseDTO getUserRoleById(Integer id) throws Exception {
        UserRoleEntity userRoleEntity = userRoleDao.getById(id);
        if (userRoleEntity != null) {
            UserRoleResponseDTO response = UserRoleMapper.toDTO(userRoleEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<UserRoleResponseDTO> getAllUserRoles() throws Exception {
        List<UserRoleResponseDTO> dtos = new ArrayList<>();
        List<UserRoleEntity> userRoleEntities = userRoleDao.getAll();
        for (UserRoleEntity entity : userRoleEntities) {
            dtos.add(UserRoleMapper.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public UserRoleResponseDTO getUserRoleByName(String roleName) throws Exception {
         UserRoleEntity userRoleEntity = userRoleDao.getByRoleName(roleName);
        if (userRoleEntity != null) {
            UserRoleResponseDTO response = UserRoleMapper.toDTO(userRoleEntity);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<UserRoleResponseDTO> getAllUserRolesBySearchKey(String searchKey) throws Exception {
        List<UserRoleResponseDTO> dtos = new ArrayList<>();
        List<UserRoleEntity> userRoleEntities = userRoleDao.getAllBySearchKey(searchKey);
        for (UserRoleEntity entity : userRoleEntities) {
            dtos.add(UserRoleMapper.toDTO(entity));
        }
        return dtos;
    }
}
