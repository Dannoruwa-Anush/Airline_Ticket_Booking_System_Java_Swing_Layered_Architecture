/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao.custom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import myapp.dao.ICrudDAO;
import myapp.entity.ReservationEntity;

/**
 *
 * @author HP
 */
public interface IReservationDao extends ICrudDAO<ReservationEntity, Integer>{
    //Custom Quaries
    List<ReservationEntity> getAllBySearchKey(String searchKey)throws SQLException, ClassNotFoundException;
    List<ReservationEntity> getAllBySearchKeyAndPassengerProfileId(Integer passengerId, String searchKey)throws SQLException, ClassNotFoundException;
    List<ReservationEntity> getAllByPassengerProfileId(Integer passengerProfileId)throws SQLException, ClassNotFoundException;
    List<ReservationEntity> getAllByStatus(String status)throws SQLException, ClassNotFoundException;
    
    boolean saveWithTransactionHandling(Connection connection, ReservationEntity t) throws SQLException, ClassNotFoundException;
    boolean updateReservationStatusWithTransactionHandling(Connection connection, ReservationEntity t) throws SQLException, ClassNotFoundException;
    ReservationEntity getByIdWithTransactionHandling(Connection connection, Integer id) throws SQLException, ClassNotFoundException;

    boolean hasReservationWithPassengerProfile(Integer passengerId)throws SQLException, ClassNotFoundException;
    boolean hasReservationWithFlight(Integer flightId)throws SQLException, ClassNotFoundException;
    boolean hasReservationWithBookingClass(Integer bookingClassId)throws SQLException, ClassNotFoundException;
}
