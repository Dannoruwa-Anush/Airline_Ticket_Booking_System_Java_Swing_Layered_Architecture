/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.BookingClassEntity;

/**
 *
 * @author HP
 */
public interface IBookingClassDao extends ICrudDAO<BookingClassEntity, Integer>{
    //Custom Queries
    List<BookingClassEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
}
