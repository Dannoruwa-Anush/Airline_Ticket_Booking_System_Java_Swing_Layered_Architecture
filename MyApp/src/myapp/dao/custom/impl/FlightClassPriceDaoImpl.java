/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.entity.FlightClassPriceEntity;
import myapp.entity.compositeKey.FlightBookingClassCompKey;
import myapp.dao.custom.IFlightClassPriceDao;
import myapp.mapper.FlightClassPriceMapper;

/**
 *
 * @author HP
 */
public class FlightClassPriceDaoImpl implements IFlightClassPriceDao {

    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(FlightClassPriceEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO flight_class_prices (flight_class_price_flight_id, flight_class_price_booking_class_id, flight_class_price_base_price, flight_class_price_seat_capacity) VALUES (?, ? ,?, ?)";
        return CrudUtil.executeUpdate(query, t.getFlight().getFlight_id(), t.getBooking_class().getBookingClass_id(), t.getBase_price(), t.getSeat_capacity());
    }

    @Override
    public boolean update(FlightClassPriceEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE flight_class_prices SET flight_class_price_base_price = ?, flight_class_price_seat_capacity = ? WHERE flight_class_price_flight_id = ? AND flight_class_price_booking_class_id = ?";
        return CrudUtil.executeUpdate(query, t.getBase_price(), t.getSeat_capacity(), t.getFlight().getFlight_id(), t.getBooking_class().getBookingClass_id());
    }

    @Override
    public boolean delete(FlightBookingClassCompKey key) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM flight_class_prices WHERE flight_class_price_flight_id = ? AND flight_class_price_booking_class_id = ?";
        return CrudUtil.executeUpdate(query, key.getFlightId(), key.getBookingClassId());
    }

    @Override
    public FlightClassPriceEntity getById(FlightBookingClassCompKey key) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fcp.*, "
                //**** [1.0 Start : Fk: flights tbl columns] ******
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*||| [1.1 Start : flight tbl FK: route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [1.1.1 Start : route tbl FK: origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [1.1.1 End : route tbl FK: origin_airport tbl columns] ---------

                //---- [1.1.1 Start : route tbl FK: destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [1.1.1 End : route tbl FK: destination_airport tbl columns] -----
                //*||| [1.1 End :   flight tbl FK: route tbl columns] *******||| 

                //*||| [1.2 Start : flight tbl FK: aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*||| [1.2 End   : route tbl FK: aircraft_model tbl columns] ********|||                 
                //**** [1.0 End : Fk: flights tbl columns] ********                

                //**** [2.0 Start : Fk: booking_classes tbl columns] ********
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [2.0 End : Fk: booking_classes tbl columns] **********

                + "FROM flight_class_prices fcp "
                + "JOIN flights f ON fcp.flight_class_price_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON fcp.flight_class_price_booking_class_id = bc.booking_class_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE fcp.flight_class_price_flight_id = ? AND fcp.flight_class_price_booking_class_id = ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, key.getFlightId(), key.getBookingClassId());

        if (resultSet.next()) {
            return (FlightClassPriceMapper.toEntityFromResultSet(resultSet, ""));
        }

        return null; // No record found for the given key
    }

    @Override
    public List<FlightClassPriceEntity> getAll() throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fcp.*, "
                //**** [1.0 Start : Fk: flights tbl columns] ******
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*||| [1.1 Start : flight tbl FK: route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [1.1.1 Start : route tbl FK: origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [1.1.1 End : route tbl FK: origin_airport tbl columns] ---------

                //---- [1.1.1 Start : route tbl FK: destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [1.1.1 End : route tbl FK: destination_airport tbl columns] -----
                //*||| [1.1 End :   flight tbl FK: route tbl columns] *******||| 

                //*||| [1.2 Start : flight tbl FK: aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*||| [1.2 End   : route tbl FK: aircraft_model tbl columns] ********|||                 
                //**** [1.0 End : Fk: flights tbl columns] ********                

                //**** [2.0 Start : Fk: booking_classes tbl columns] ********
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [2.0 End : Fk: booking_classes tbl columns] **********

                + "FROM flight_class_prices fcp "
                + "JOIN flights f ON fcp.flight_class_price_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON fcp.flight_class_price_booking_class_id = bc.booking_class_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id ";

        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<FlightClassPriceEntity> flightClassPriceEntities = new ArrayList<>();

        while (resultSet.next()) {
            flightClassPriceEntities.add(FlightClassPriceMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightClassPriceEntities;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] --------------
    @Override
    public List<FlightClassPriceEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fcp.*, "
                //**** [1.0 Start : Fk: flights tbl columns] ******
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*||| [1.1 Start : flight tbl FK: route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [1.1.1 Start : route tbl FK: origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [1.1.1 End : route tbl FK: origin_airport tbl columns] ---------

                //---- [1.1.1 Start : route tbl FK: destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [1.1.1 End : route tbl FK: destination_airport tbl columns] -----
                //*||| [1.1 End :   flight tbl FK: route tbl columns] *******||| 

                //*||| [1.2 Start : flight tbl FK: aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*||| [1.2 End   : route tbl FK: aircraft_model tbl columns] ********|||                 
                //**** [1.0 End : Fk: flights tbl columns] ********                

                //**** [2.0 Start : Fk: booking_classes tbl columns] ********
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [2.0 End : Fk: booking_classes tbl columns] **********

                + "FROM flight_class_prices fcp "
                + "JOIN flights f ON fcp.flight_class_price_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON fcp.flight_class_price_booking_class_id = bc.booking_class_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE f.flight_code LIKE ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");
        List<FlightClassPriceEntity> flightClassPriceEntities = new ArrayList<>();

        while (resultSet.next()) {
            flightClassPriceEntities.add(FlightClassPriceMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightClassPriceEntities;
    }

    //bulkSave
    @Override
    public boolean bulkSave(List<FlightClassPriceEntity> list) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO flight_class_prices (flight_class_price_flight_id, flight_class_price_booking_class_id, flight_class_price_base_price, flight_class_price_seat_capacity) VALUES (?, ?, ?, ?)";

        List<Object[]> batchParams = new ArrayList<>();
        for (FlightClassPriceEntity t : list) {
            batchParams.add(new Object[]{
                t.getFlight().getFlight_id(),
                t.getBooking_class().getBookingClass_id(),
                t.getBase_price(),
                t.getSeat_capacity()
            });
        }

        int[] result = CrudUtil.executeBatchUpdate(query, batchParams);
        return result.length == list.size();
    }

    //bulkUpdate
    @Override
    public boolean bulkUpdate(List<FlightClassPriceEntity> list) throws SQLException, ClassNotFoundException {
        String query = "UPDATE flight_class_prices SET flight_class_price_base_price = ?, flight_class_price_seat_capacity = ? WHERE flight_class_price_flight_id = ? AND flight_class_price_booking_class_id = ?";

        List<Object[]> batchParams = new ArrayList<>();
        for (FlightClassPriceEntity t : list) {
            batchParams.add(new Object[]{
                t.getBase_price(),
                t.getSeat_capacity(),
                t.getFlight().getFlight_id(),
                t.getBooking_class().getBookingClass_id()
            });
        }

        int[] result = CrudUtil.executeBatchUpdate(query, batchParams);
        return result.length == list.size();
    }

    @Override
    public List<FlightClassPriceEntity> getAllByFlightId(Integer flightId) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT fcp.*, "
                //**** [1.0 Start : Fk: flights tbl columns] ******
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*||| [1.1 Start : flight tbl FK: route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [1.1.1 Start : route tbl FK: origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [1.1.1 End : route tbl FK: origin_airport tbl columns] ---------

                //---- [1.1.1 Start : route tbl FK: destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [1.1.1 End : route tbl FK: destination_airport tbl columns] -----
                //*||| [1.1 End :   flight tbl FK: route tbl columns] *******||| 

                //*||| [1.2 Start : flight tbl FK: aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*||| [1.2 End   : route tbl FK: aircraft_model tbl columns] ********|||                 
                //**** [1.0 End : Fk: flights tbl columns] ********                

                //**** [2.0 Start : Fk: booking_classes tbl columns] ********
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [2.0 End : Fk: booking_classes tbl columns] **********

                + "FROM flight_class_prices fcp "
                + "JOIN flights f ON fcp.flight_class_price_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON fcp.flight_class_price_booking_class_id = bc.booking_class_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE fcp.flight_class_price_flight_id = ? ";

        ResultSet resultSet = CrudUtil.executeQuery(query, flightId);
        List<FlightClassPriceEntity> flightClassPriceEntities = new ArrayList<>();

        while (resultSet.next()) {
            flightClassPriceEntities.add(FlightClassPriceMapper.toEntityFromResultSet(resultSet, ""));
        }

        return flightClassPriceEntities;
    }

    @Override
    public boolean deleteAllByFlightId(Integer flightId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM flight_class_prices WHERE flight_class_price_flight_id = ?";
        return CrudUtil.executeUpdate(query, flightId);
    }

    @Override
    public boolean updateAvailableSeatCapacityWithTransactionHandling(Connection connection, Integer flightId, Integer bookingClassId, Integer seatCapacityToUpdate) throws SQLException, ClassNotFoundException {
        String query = "UPDATE flight_class_prices SET flight_class_price_seat_capacity = ? WHERE flight_class_price_flight_id = ? AND flight_class_price_booking_class_id = ?";
        return CrudUtil.executeUpdate(connection, query, seatCapacityToUpdate, flightId, bookingClassId);
    }

    @Override
    public Integer getAvailableSeatCapacityWithTransactionHandling(Connection connection, Integer flightId, Integer bookingClassId) throws SQLException, ClassNotFoundException {
        String query = "SELECT flight_class_price_seat_capacity FROM flight_class_prices WHERE flight_class_price_flight_id = ? AND flight_class_price_booking_class_id = ?";
        ResultSet rs = CrudUtil.executeQuery(connection, query, flightId, bookingClassId);
        if (rs.next()) {
            return rs.getInt("flight_class_price_seat_capacity");
        }
        return null;
    }    

    @Override
    public boolean hasFlightClassPriceWithFlight(Integer flightId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flight_class_prices WHERE flight_class_price_flight_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, flightId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
            return false;
        }
    }

    @Override
    public boolean hasFlightClassPriceWithBookingClass(Integer bookingClassId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM flight_class_prices WHERE flight_class_price_booking_class_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, bookingClassId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
            return false;
        }
    }
    //---------- [End : Custom Query] --------------
}
