/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.RouteEntity;

/**
 *
 * @author HP
 */
public interface IRouteDao extends ICrudDAO<RouteEntity, Integer>{
    //custom quaries
    List<RouteEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    boolean hasRoutesWithAirport(Integer airportId)throws SQLException, ClassNotFoundException;
}
