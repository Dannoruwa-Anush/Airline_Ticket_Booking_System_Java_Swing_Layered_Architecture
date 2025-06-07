/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.user.UserLoginRequestDTO;
import myapp.dto.user.UserRequestDTO;
import myapp.dto.user.UserResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IUserService;

/**
 *
 * @author HP
 */
public class UserController {
    
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IUserService userService;

    //constructor
    public UserController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.USER);
        
        if (iSuperService instanceof IUserService iUserService) { 
            userService = iUserService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IUserService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addUser(UserRequestDTO userRequestDTO) throws Exception {
        return userService.addUser(userRequestDTO);
    }

    public String updateUser(Integer id, UserRequestDTO userRequestDTO) throws Exception {
        return userService.updateUser(id, userRequestDTO);
    }

    public String deleteUser(Integer id) throws Exception {
        return userService.deleteUser(id);
    }

    public UserResponseDTO getUserById(Integer id) throws Exception {
        return userService.getUserById(id);
    }

    public List<UserResponseDTO> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }
    
    public UserResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO) throws Exception {
        return userService.loginUser(userLoginRequestDTO);
    }
}
