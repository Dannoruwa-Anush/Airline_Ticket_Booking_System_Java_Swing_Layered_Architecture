/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.SQLException;
import myapp.dao.ICrudDAO;
import myapp.entity.UserEntity;

/**
 *
 * @author HP
 */
public interface IUserDao extends ICrudDAO<UserEntity, Integer>{
    //Custom Quaries
    UserEntity loginUser(UserEntity t)throws SQLException, ClassNotFoundException;
    boolean hasUsersWithRole(Integer roleId)throws SQLException, ClassNotFoundException;
}
