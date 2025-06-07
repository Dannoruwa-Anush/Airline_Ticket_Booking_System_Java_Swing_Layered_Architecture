/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.FlightEntity;

/**
 *
 * @author HP
 */
public interface IFlightDao extends ICrudDAO<FlightEntity, Integer>{
    //custom quaries
    List<FlightEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    List<FlightEntity> getAllFlightsWithFlightClassPriceInfoBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    List<FlightEntity> getAllFlightsWithoutFlightClassPriceInfo() throws SQLException, ClassNotFoundException;
    List<FlightEntity> getAllFlightsWithFlightClassPriceInfo() throws SQLException, ClassNotFoundException;
    List<FlightEntity> getAllFlightsForRouteAndDepartureTime(Integer routeId, LocalDateTime departureTime)throws SQLException, ClassNotFoundException;
    List<FlightEntity> getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(Integer routeId, LocalDateTime departureTime)throws SQLException, ClassNotFoundException;

    
    boolean hasFlightsWithAircraftModel(Integer aircraftModelId)throws SQLException, ClassNotFoundException;
    boolean hasFlightsWithRoute(Integer routeId)throws SQLException, ClassNotFoundException;
    
    boolean saveWithTransactionHandling(Connection connection, FlightEntity t) throws SQLException, ClassNotFoundException;
    boolean hasFlightsWithFlightSchedule(Integer flightSheduleId)throws SQLException, ClassNotFoundException;
}
