/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.FlightScheduleEntity;

/**
 *
 * @author HP
 */
public interface IFlightScheduleDao extends ICrudDAO<FlightScheduleEntity, Integer>{
    //custom quaries
    List<FlightScheduleEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    
    boolean hasFlightSchedulesWithAircraftModel(Integer aircraftModelId)throws SQLException, ClassNotFoundException;
    boolean hasFlightSchedulesWithRoute(Integer routeId)throws SQLException, ClassNotFoundException;
    
    List<FlightScheduleEntity> getAllWithTransactionHandling(Connection connection)throws SQLException, ClassNotFoundException;
}
