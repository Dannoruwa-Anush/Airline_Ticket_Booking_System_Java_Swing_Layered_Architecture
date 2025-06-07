/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.PassengerProfileEntity;

/**
 *
 * @author HP
 */
public interface IPassengerProfileDao extends ICrudDAO<PassengerProfileEntity, Integer>{
    //custom quaries
    List<PassengerProfileEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    PassengerProfileEntity getByUserId(Integer userId)throws SQLException, ClassNotFoundException;
    
    boolean hasPassengerWithUser(Integer userId)throws SQLException, ClassNotFoundException;
}
