/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.user.UserLoginRequestDTO;
import myapp.dto.user.UserResponseDTO;
import myapp.dto.user.UserRequestDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IUserService extends ISuperService{
    String addUser(UserRequestDTO usersaveDTO) throws Exception;
    String updateUser(Integer id, UserRequestDTO userSaveDTO) throws Exception;
    String deleteUser(Integer id) throws Exception;
    UserResponseDTO getUserById(Integer id) throws Exception;
    List<UserResponseDTO> getAllUsers() throws Exception;
    
    UserResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO) throws Exception;
}
