/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IAirportDao;
import myapp.entity.AirportEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import myapp.mapper.AirportMapper;
/**
 *
 * @author HP
 */
public class AirportDaoImpl implements IAirportDao{
    
    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(AirportEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO airports (airport_code, airport_name, airport_city, airport_country) VALUES (?, ?, ?, ?)";
        return CrudUtil.executeUpdate(query, t.getAirport_code(), t.getAirport_name(), t.getAirport_city(), t.getAirport_country());
    }

    @Override
    public boolean update(AirportEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE airports SET airport_code =?, airport_name=?, airport_city=?, airport_country=? WHERE airport_id = ?";
        return CrudUtil.executeUpdate(query, t.getAirport_code(), t.getAirport_name(), t.getAirport_city(), t.getAirport_country(), t.getAirport_id());
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM airports WHERE airport_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public AirportEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM airports where airport_id = ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, id);
        
        while (resultSet.next()) {
            return AirportMapper.toEntityFromResultSet(resultSet, "", ""); //pefix is no needed for parent class 
        }
        return null;
    }

    @Override
    public List<AirportEntity> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM airports";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<AirportEntity> airportEntities = new ArrayList<>();
        while (resultSet.next()) {
            airportEntities.add(
                AirportMapper.toEntityFromResultSet(resultSet, "", "") //pefix is no needed for parent class 
            );
        }
        return airportEntities;
    }
    //---------- [End : CRUD Query] ------------------
    
    
    //---------- [Start : Custom Query] ----------------
    @Override
    public List<AirportEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM airports WHERE airport_name LIKE ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");

        List<AirportEntity> airportEntities = new ArrayList<>();
        while (resultSet.next()) {
            airportEntities.add(
                AirportMapper.toEntityFromResultSet(resultSet, "", "") //pefix is no needed for parent class 
            );
        }
        return airportEntities;
    }
    //---------- [End : Custom Query] ----------------
}
