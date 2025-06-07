/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.userRole.UserRoleResponseDTO;
import myapp.dto.userRole.UserRoleRequestDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IUserRoleService extends ISuperService{
    String addUserRole(UserRoleRequestDTO userRoleRequestDTO) throws Exception;
    String updateUserRole(Integer id, UserRoleRequestDTO userRoleRequestDTO) throws Exception;
    String deleteUserRole(Integer id) throws Exception;
    UserRoleResponseDTO getUserRoleById(Integer id) throws Exception;
    List<UserRoleResponseDTO> getAllUserRoles() throws Exception;
    
    UserRoleResponseDTO getUserRoleByName(String roleName) throws Exception;
    List<UserRoleResponseDTO> getAllUserRolesBySearchKey(String searchKey)throws Exception;
}
