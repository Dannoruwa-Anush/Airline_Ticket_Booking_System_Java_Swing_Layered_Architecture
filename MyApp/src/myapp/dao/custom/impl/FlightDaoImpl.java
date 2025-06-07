/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IFlightDao;
import myapp.entity.FlightEntity;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import myapp.mapper.FlightMapper;

/**
 *
 * @author HP
 */
public class FlightDaoImpl implements IFlightDao {

    //---------- [Start : CRUD Query] -----------------
    @Override
    public boolean save(FlightEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO flights(flight_schedule_id, flight_route_id, flight_aircraft_model_id, flight_code, flight_departure_time, flight_arrival_time, flight_airline_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(
                query,
                t.getFlightSchedule().getSchedule_id(),
                t.getRoute().getRoute_id(),
                t.getAircraftModel().getAircraftModel_id(),
                t.getFlight_code(),
                t.getFlight_departure_time(),
                t.getFlight_arrival_time(),
                t.getFlight_airline_name()
        );
    }

    @Override
    public boolean update(FlightEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE flights SET flight_schedule_id = ?, flight_route_id = ?, flight_aircraft_model_id = ?, flight_code = ?, flight_departure_time = ?, flight_arrival_time = ?, flight_airline_name = ?  WHERE flight_id = ?";
        return CrudUtil.executeUpdate(
                query,
                t.getFlightSchedule().getSchedule_id(),
                t.getRoute().getRoute_id(),
                t.getAircraftModel().getAircraftModel_id(),
                t.getFlight_code(),
                t.getFlight_departure_time(),
                t.getFlight_arrival_time(),
                t.getFlight_airline_name(),
                t.getFlight_id()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM flights WHERE flight_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public FlightEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
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

                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE f.flight_id = ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, id);

        if (resultSet.next()) {
            return (FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return null;
    }

    @Override
    public List<FlightEntity> getAll() throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
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

                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id ";

        ResultSet resultSet = CrudUtil.executeQuery(query);

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] --------------
    @Override
    public List<FlightEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
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

                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE f.flight_code LIKE ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }

    @Override
    public boolean hasFlightsWithAircraftModel(Integer aircraftModelId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flights WHERE flight_aircraft_model_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, aircraftModelId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returns true if there are any users with that role
            }
            return false; // Return false if no results
        }
    }

    @Override
    public boolean hasFlightsWithRoute(Integer routeId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flights WHERE flight_route_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, routeId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returns true if there are any users with that role
            }
            return false; // Return false if no results
        }
    }

    @Override
    public List<FlightEntity> getAllFlightsWithoutFlightClassPriceInfo() throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
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

                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE NOT EXISTS ("
                + "    SELECT 1 FROM flight_class_prices fcp "
                + "    WHERE fcp.flight_class_price_flight_id = f.flight_id"
                + ")";

        ResultSet resultSet = CrudUtil.executeQuery(query);

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }

    @Override
    public List<FlightEntity> getAllFlightsWithFlightClassPriceInfo() throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
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

                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE EXISTS ("
                + "    SELECT 1 FROM flight_class_prices fcp "
                + "    WHERE fcp.flight_class_price_flight_id = f.flight_id"
                + ")";

        ResultSet resultSet = CrudUtil.executeQuery(query);

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }

    @Override
    public List<FlightEntity> getAllFlightsForRouteAndDepartureTime(Integer routeId, LocalDateTime departureTime)
            throws SQLException, ClassNotFoundException {

        String query
                = "SELECT f.*, "
                + // [Route columns]
                "rts.route_id, rts.route_distance_km, rts.route_estimated_duration_minutes, "
                + // [Origin Airport columns]
                "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                + // [Destination Airport columns]
                "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                + // [Aircraft columns]
                "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km "
                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE f.flight_route_id = ? "
                + "AND DATE(f.flight_departure_time) = ?";

        // Use just the date part of the departureTime
        LocalDate departureDate = departureTime.toLocalDate();

        ResultSet resultSet = CrudUtil.executeQuery(query, routeId, departureDate);

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }

    @Override
    public boolean saveWithTransactionHandling(Connection connection, FlightEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO flights(flight_schedule_id, flight_route_id, flight_aircraft_model_id, flight_code, flight_departure_time, flight_arrival_time, flight_airline_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(
                connection, // use the shared connection
                query,
                t.getFlightSchedule().getSchedule_id(),
                t.getRoute().getRoute_id(),
                t.getAircraftModel().getAircraftModel_id(),
                t.getFlight_code(),
                t.getFlight_departure_time(),
                t.getFlight_arrival_time(),
                t.getFlight_airline_name()
        );
    }

    @Override
    public List<FlightEntity> getAllFlightsWithFlightClassPriceInfoBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
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

                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE EXISTS ("
                + "    SELECT 1 FROM flight_class_prices fcp "
                + "    WHERE fcp.flight_class_price_flight_id = f.flight_id"
                + ") "
                + "AND f.flight_code LIKE ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }

    @Override
    public boolean hasFlightsWithFlightSchedule(Integer flightSheduleId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flights WHERE flight_schedule_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, flightSheduleId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returns true if there are any users with that role
            }
            return false; // Return false if no results
        }
    }
    //---------- [End : Custom Query] --------------

    @Override
    public List<FlightEntity> getAllFlightsForRouteAndDepartureTimeWithFlightClassPriceInfo(Integer routeId, LocalDateTime departureTime) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT f.*, "
                + // [Route columns]
                "rts.route_id, rts.route_distance_km, rts.route_estimated_duration_minutes, "
                + // [Origin Airport columns]
                "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                + // [Destination Airport columns]
                "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                + // [Aircraft columns]
                "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km "
                
                + "FROM flights f "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE EXISTS ("
                + "    SELECT 1 FROM flight_class_prices fcp "
                + "    WHERE fcp.flight_class_price_flight_id = f.flight_id"
                + ") "
                + "AND f.flight_route_id = ? "
                + "AND f.flight_departure_time >= ? "
                + "AND f.flight_departure_time < ?";

        // Use just the date part of the departureTime
        LocalDate departureDate = departureTime.toLocalDate();

        ResultSet resultSet = CrudUtil.executeQuery(query, routeId, departureDate);

        List<FlightEntity> flightList = new ArrayList<>();

        while (resultSet.next()) {
            flightList.add(FlightMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightList;
    }
}
