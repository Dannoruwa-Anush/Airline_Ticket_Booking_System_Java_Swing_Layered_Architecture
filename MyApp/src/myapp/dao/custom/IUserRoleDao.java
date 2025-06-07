/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.UserRoleEntity;

/**
 *
 * @author HP
 */
public interface IUserRoleDao extends ICrudDAO<UserRoleEntity, Integer>{
    //Custom Queries
    UserRoleEntity getByRoleName(String roleName)throws SQLException, ClassNotFoundException;
    List<UserRoleEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
}
