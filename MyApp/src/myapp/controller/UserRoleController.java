/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.userRole.UserRoleResponseDTO;
import myapp.dto.userRole.UserRoleRequestDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IUserRoleService;

/**
 *
 * @author HP
 */
public class UserRoleController {
  
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IUserRoleService userRoleService;

    //constructor
    public UserRoleController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.USER_ROLE);
        
        if (iSuperService instanceof IUserRoleService iUserRoleService) { 
            userRoleService = iUserRoleService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IUserRoleService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addUserRole(UserRoleRequestDTO userRoleRequestDTO) throws Exception {
        return userRoleService.addUserRole(userRoleRequestDTO);
    }

    public String updateUserRole(Integer id, UserRoleRequestDTO userRoleRequestDTO) throws Exception {
        return userRoleService.updateUserRole(id, userRoleRequestDTO);
    }

    public String deleteUserRole(Integer id) throws Exception {
        return userRoleService.deleteUserRole(id);
    }

    public UserRoleResponseDTO getUserRoleById(Integer id) throws Exception {
        return userRoleService.getUserRoleById(id);
    }

    public List<UserRoleResponseDTO> getAllUserRoles() throws Exception {
        return userRoleService.getAllUserRoles();
    }
    
    public UserRoleResponseDTO getUserRoleByName(String roleName) throws Exception {
        return userRoleService.getUserRoleByName(roleName);
    }
    
    public List<UserRoleResponseDTO> getAllUserRolesBySearchKey(String searchKey) throws Exception{
        return userRoleService.getAllUserRolesBySearchKey(searchKey);
    }
}
