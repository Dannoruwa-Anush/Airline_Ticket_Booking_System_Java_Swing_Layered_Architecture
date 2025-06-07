/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.FlightClassPriceEntity;
import myapp.entity.compositeKey.FlightBookingClassCompKey;

/**
 *
 * @author HP
 */
public interface IFlightClassPriceDao extends ICrudDAO<FlightClassPriceEntity, FlightBookingClassCompKey>{
    //Custom Queries
    List<FlightClassPriceEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    List<FlightClassPriceEntity> getAllByFlightId(Integer flightId)throws SQLException, ClassNotFoundException;
    
    boolean bulkSave(List<FlightClassPriceEntity> list) throws SQLException, ClassNotFoundException ;
    boolean bulkUpdate(List<FlightClassPriceEntity> list) throws SQLException, ClassNotFoundException;
    boolean deleteAllByFlightId(Integer flightId) throws SQLException, ClassNotFoundException ;
    
    Integer getAvailableSeatCapacityWithTransactionHandling(Connection connection, Integer flightId, Integer bookingClassId) throws SQLException, ClassNotFoundException;
    boolean updateAvailableSeatCapacityWithTransactionHandling(Connection connection, Integer flightId, Integer bookingClassId, Integer seatCapacityToUpdate) throws SQLException, ClassNotFoundException;

    boolean hasFlightClassPriceWithFlight(Integer flightId)throws SQLException, ClassNotFoundException;
    boolean hasFlightClassPriceWithBookingClass(Integer bookingClassId)throws SQLException, ClassNotFoundException;
}
