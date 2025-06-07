/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IAircraftModelDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import myapp.entity.AircraftModelEntity;
import myapp.mapper.AircraftModelMapper;

/**
 *
 * @author HP
 */
public class AircraftModelDaoImpl implements IAircraftModelDao {

    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(AircraftModelEntity t) throws SQLException, ClassNotFoundException {
        System.out.println("Size Category: " + t.getAircraftModel_SizeCategory());
        String query = "INSERT INTO aircraft_models (aircraft_model_manufacturer, aircraft_model_name, aircraft_model_seating_capacity, aircraft_model_size_category, aircraft_model_range_km) VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(query, t.getAircraftModel_Manufacturer(), t.getAircraftModel_Name(), t.getAircraftModel_SeatCapacity(), t.getAircraftModel_SizeCategory(), t.getAircraftModel_Range_KM());
    }

    @Override
    public boolean update(AircraftModelEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE aircraft_models SET aircraft_model_manufacturer =?, aircraft_model_name=?, aircraft_model_seating_capacity=?, aircraft_model_size_category=?, aircraft_model_range_km=? WHERE aircraft_model_id = ?";
        return CrudUtil.executeUpdate(query, t.getAircraftModel_Manufacturer(), t.getAircraftModel_Name(), t.getAircraftModel_SeatCapacity(), t.getAircraftModel_SizeCategory(), t.getAircraftModel_Range_KM(), t.getAircraftModel_id());
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM aircraft_models WHERE aircraft_model_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public AircraftModelEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM aircraft_models where aircraft_model_id = ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, id);

        while (resultSet.next()) {
            return AircraftModelMapper.toEntityFromResultSet(resultSet, ""); //pefix is no needed for parent class 
        }
        return null;
    }

    @Override
    public List<AircraftModelEntity> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM aircraft_models";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<AircraftModelEntity> aircraftModelEntities = new ArrayList<>();
        while (resultSet.next()) {
            aircraftModelEntities.add(
                AircraftModelMapper.toEntityFromResultSet(resultSet, "") //pefix is no needed for parent class 
            );
        }
        return aircraftModelEntities;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] ----------------
    @Override
    public List<AircraftModelEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM aircraft_models WHERE aircraft_model_name LIKE ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");
        List<AircraftModelEntity> aircraftModelEntities = new ArrayList<>();
        while (resultSet.next()) {
            aircraftModelEntities.add(
                AircraftModelMapper.toEntityFromResultSet(resultSet, "") //pefix is no needed for parent class 
            );
        }
        return aircraftModelEntities;
    }
    //---------- [End : Custom Query] ----------------
}
