/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IFlightScheduleDao;
import myapp.entity.FlightScheduleEntity;
import myapp.mapper.FlightScheduleMapper;

/**
 *
 * @author HP
 */
public class FlightScheduleDaoImpl implements IFlightScheduleDao {

    //---------- [Start : CRUD Query] ----------------
    @Override
    public boolean save(FlightScheduleEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO flight_schedules(schedule_route_id, schedule_aircraft_model_id, schedule_flight_code, schedule_airline_name, schedule_departure_time, schedule_arrival_time, schedule_days_of_week) VALUES (?, ?, ?, ?, ?, ?, ?)";

        return CrudUtil.executeUpdate(
                query,
                t.getRoute().getRoute_id(),
                t.getAircraftModel().getAircraftModel_id(),
                t.getSchedule_flight_code(),
                t.getSchedule_airline_name(),
                Time.valueOf(t.getSchedule_departure_time()), // Convert LocalTime to java.sql.Time
                Time.valueOf(t.getSchedule_arrival_time()), // Convert LocalTime to java.sql.Time
                String.join(",", t.getSchedule_days_of_week()) // Convert Set<String> to comma-separated string
        );
    }

    @Override
    public boolean update(FlightScheduleEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE flight_schedules SET schedule_route_id = ?, schedule_aircraft_model_id = ?, schedule_flight_code = ?, schedule_airline_name = ?, schedule_departure_time = ?, schedule_arrival_time = ?, schedule_days_of_week = ? WHERE schedule_id = ?";

        return CrudUtil.executeUpdate(
                query,
                t.getRoute().getRoute_id(),
                t.getAircraftModel().getAircraftModel_id(),
                t.getSchedule_flight_code(),
                t.getSchedule_airline_name(),
                Time.valueOf(t.getSchedule_departure_time()),
                Time.valueOf(t.getSchedule_arrival_time()),
                String.join(",", t.getSchedule_days_of_week()),
                t.getSchedule_id()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM flight_schedules WHERE schedule_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public FlightScheduleEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fsh.*, "
                //**** [Start : Fk: route tbl columns] *****
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //*||| [Start : route tbl FK: origin_airport tbl columns] ***||| 
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //*||| [End : route tbl FK : origin_airport tbl columns] ***||| 

                //*||| [Start : route tbl FK: destination_airport tbl columns] ***||| 
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //*||| [End : route tbl FK: destination_airport tbl columns] ***||| 
                //**** [End : Fk: route tbl columns] ******

                //**** [Start : Fk: aircraft tbl columns] *****
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km "
                //**** [End : Fk: aircraft tbl columns] ******

                + "FROM flight_schedules fsh "
                + "JOIN routes rts ON fsh.schedule_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON fsh.schedule_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE fsh.schedule_id = ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, id);

        if (resultSet.next()) {
            return (FlightScheduleMapper.toEntityFromResultSet(resultSet, ""));
        }

        return null;
    }

    @Override
    public List<FlightScheduleEntity> getAll() throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fsh.*, "
                //**** [Start : Fk: route tbl columns] *****
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //*||| [Start : route tbl FK: origin_airport tbl columns] ***||| 
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //*||| [End : route tbl FK : origin_airport tbl columns] ***||| 

                //*||| [Start : route tbl FK: destination_airport tbl columns] ***||| 
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //*||| [End : route tbl FK: destination_airport tbl columns] ***||| 
                //**** [End : Fk: route tbl columns] ******

                //**** [Start : Fk: aircraft tbl columns] *****
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km "
                //**** [End : Fk: aircraft tbl columns] ******

                + "FROM flight_schedules fsh "
                + "JOIN routes rts ON fsh.schedule_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON fsh.schedule_aircraft_model_id = acm.aircraft_model_id ";

        ResultSet resultSet = CrudUtil.executeQuery(query);

        List<FlightScheduleEntity> flightScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            flightScheduleList.add(FlightScheduleMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightScheduleList;
    }

    @Override
    public List<FlightScheduleEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fsh.*, "
                //**** [Start : Fk: route tbl columns] *****
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //*||| [Start : route tbl FK: origin_airport tbl columns] ***||| 
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //*||| [End : route tbl FK : origin_airport tbl columns] ***||| 

                //*||| [Start : route tbl FK: destination_airport tbl columns] ***||| 
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //*||| [End : route tbl FK: destination_airport tbl columns] ***||| 
                //**** [End : Fk: route tbl columns] ******

                //**** [Start : Fk: aircraft tbl columns] *****
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km "
                //**** [End : Fk: aircraft tbl columns] ******

                + "FROM flight_schedules fsh "
                + "JOIN routes rts ON fsh.schedule_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON fsh.schedule_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE fsh.schedule_flight_code LIKE ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");

        List<FlightScheduleEntity> flightScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            flightScheduleList.add(FlightScheduleMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightScheduleList;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] --------------
    @Override
    public boolean hasFlightSchedulesWithAircraftModel(Integer aircraftModelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flight_schedules WHERE schedule_aircraft_model_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, aircraftModelId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returns true if there are any users with that role
            }
            return false; // Return false if no results
        }
    }

    @Override
    public boolean hasFlightSchedulesWithRoute(Integer routeId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flight_schedules WHERE schedule_route_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, routeId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returns true if there are any users with that role
            }
            return false; // Return false if no results
        }
    }
    
    @Override
    public List<FlightScheduleEntity> getAllWithTransactionHandling(Connection connection) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fsh.*, "
                //**** [Start : Fk: route tbl columns] *****
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //*||| [Start : route tbl FK: origin_airport tbl columns] ***||| 
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //*||| [End : route tbl FK : origin_airport tbl columns] ***||| 

                //*||| [Start : route tbl FK: destination_airport tbl columns] ***||| 
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //*||| [End : route tbl FK: destination_airport tbl columns] ***||| 
                //**** [End : Fk: route tbl columns] ******

                //**** [Start : Fk: aircraft tbl columns] *****
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km "
                //**** [End : Fk: aircraft tbl columns] ******

                + "FROM flight_schedules fsh "
                + "JOIN routes rts ON fsh.schedule_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON fsh.schedule_aircraft_model_id = acm.aircraft_model_id ";

        ResultSet resultSet = CrudUtil.executeQuery(connection,query);

        List<FlightScheduleEntity> flightScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            flightScheduleList.add(FlightScheduleMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightScheduleList;
    }
    //---------- [End : Custom Query] --------------
}
