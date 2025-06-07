/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.entity.PassengerProfileEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import myapp.mapper.PassengerProfileMapper;
import myapp.dao.custom.IPassengerProfileDao;

/**
 *
 * @author HP
 */
public class PassengerProfileDaoImpl implements IPassengerProfileDao {

    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(PassengerProfileEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO passenger_profiles(passenger_user_id, passenger_full_name, passenger_passport_no, passenger_nationality, passenger_contact_no, passenger_emergency_contact) VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(
                query,
                t.getUser().getUserId(),
                t.getPassengerFullName(),
                t.getPassengerPassportNumber(),
                t.getPassengerNationality(),
                t.getPassengerContactNumber(),
                t.getPassengerEmergencyContactNumber()
        );
    }

    @Override
    public boolean update(PassengerProfileEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE passenger_profiles SET passenger_full_name = ?, passenger_passport_no = ?, passenger_nationality = ?, passenger_contact_no = ?, passenger_emergency_contact = ? WHERE passenger_id = ?";
        return CrudUtil.executeUpdate(
                query,
                t.getPassengerFullName(),
                t.getPassengerPassportNumber(),
                t.getPassengerNationality(),
                t.getPassengerContactNumber(),
                t.getPassengerEmergencyContactNumber(),
                t.getPassengerId()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM passenger_profiles WHERE passenger_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public PassengerProfileEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query =
                "SELECT pf.*, "
                
                //**** [Start : Fk: user tbl columns] *****
                + "u.user_id, "
                + "u.user_name, "
                + "u.user_email "
                //**** [End : Fk: user tbl columns] *******
                
                + "FROM passenger_profiles pf "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "WHERE pf.passenger_id = ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, id);
        if (resultSet.next()) {
            return PassengerProfileMapper.toEntityFromResultSet(resultSet, "");
        }
        return null;
    }

    @Override
    public List<PassengerProfileEntity> getAll() throws SQLException, ClassNotFoundException {
        String query =
                "SELECT pf.*, "
                
                //**** [Start : Fk: user tbl columns] *****
                + "u.user_id, "
                + "u.user_name, "
                + "u.user_email "
                //**** [End : Fk: user tbl columns] *****
                
                + "FROM passenger_profiles pf "
                + "JOIN users u ON pf.passenger_user_id = u.user_id ";
        
        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<PassengerProfileEntity> passengerList = new ArrayList<>();

        while (resultSet.next()) {
            passengerList.add(
                PassengerProfileMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return passengerList;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] -------------
    @Override
    public List<PassengerProfileEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
         String query =
                "SELECT pf.*, "
                
                //**** [Start : Fk: user tbl columns] *****
                + "u.user_id, "
                + "u.user_name, "
                + "u.user_email "
                //**** [End : Fk: user tbl columns] *****
                 
                + "FROM passenger_profiles pf "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "WHERE pf.passenger_passport_no LIKE ?";
        
        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");
        List<PassengerProfileEntity> passengerList = new ArrayList<>();

        while (resultSet.next()) {
            passengerList.add(
                PassengerProfileMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return passengerList;
    }

    @Override
    public PassengerProfileEntity getByUserId(Integer userId) throws SQLException, ClassNotFoundException {
        String query =
                "SELECT pf.*, "
                
                //**** [Start : Fk: user tbl columns] *****
                + "u.user_id, "
                + "u.user_name, "
                + "u.user_email "
                //**** [End : Fk: user tbl columns] *****
                
                + "FROM passenger_profiles pf "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "WHERE pf.passenger_user_id = ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, userId);
        if (resultSet.next()) {
            return PassengerProfileMapper.toEntityFromResultSet(resultSet, "");
        }
        return null;
    }

    @Override
    public boolean hasPassengerWithUser(Integer userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM passenger_profiles WHERE passenger_user_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, userId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        }
    }
    //---------- [End : Custom Query] --------------
}
