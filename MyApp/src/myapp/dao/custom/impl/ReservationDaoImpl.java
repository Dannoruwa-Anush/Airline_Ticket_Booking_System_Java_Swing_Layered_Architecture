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
import myapp.entity.ReservationEntity;
import myapp.dao.custom.IReservationDao;
import myapp.mapper.ReservationMapper;

/**
 *
 * @author HP
 */
public class ReservationDaoImpl implements IReservationDao {

    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(ReservationEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO reservations (reservation_passenger_id, reservation_flight_id, reservation_booking_class_id, reservation_seat_number, reservation_total_price, reservation_status) VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(
                query,
                t.getPassenger().getPassengerId(),
                t.getFlight().getFlight_id(),
                t.getBookingClass().getBookingClass_id(),
                t.getReservation_seat_number(),
                t.getReservation_total_price(),
                t.getReservation_status()
        );
    }

    @Override
    public boolean saveWithTransactionHandling(Connection connection, ReservationEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO reservations (reservation_passenger_id, reservation_flight_id, reservation_booking_class_id, reservation_seat_number, reservation_total_price, reservation_status) VALUES (?, ?, ?, ?, ?, ?)";

        return CrudUtil.executeUpdate(
                connection, // use the shared connection
                query,
                t.getPassenger().getPassengerId(),
                t.getFlight().getFlight_id(),
                t.getBookingClass().getBookingClass_id(),
                t.getReservation_seat_number(),
                t.getReservation_total_price(),
                t.getReservation_status()
        );
    }

    @Override
    public boolean update(ReservationEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE reservations SET reservation_status = ? WHERE reservation_id = ?";
        return CrudUtil.executeUpdate(
                query,
                t.getReservation_status(),
                t.getReservation_id()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM reservations WHERE reservation_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public ReservationEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name, "
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE rsv.reservation_id = ? ";

        ResultSet resultSet = CrudUtil.executeQuery(query, id);

        if (resultSet.next()) {
            return (ReservationMapper.toEntityFromResultSet(resultSet, ""));
        }

        return null;
    }

    @Override
    public List<ReservationEntity> getAll() throws SQLException, ClassNotFoundException {
        String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name, "
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id ";

        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<ReservationEntity> reservationList = new ArrayList<>();

        while (resultSet.next()) {
            reservationList.add(
                    ReservationMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return reservationList;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] --------------
    @Override
    public List<ReservationEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name,"
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE f.flight_code LIKE ? "
                + "OR pf.passenger_passport_no LIKE ? "
                + "OR rsv.reservation_date LIKE ? "
                + "OR orAp.airport_name LIKE ? "
                + "OR deAp.airport_name LIKE ?";

        String wildcardKey = "%" + searchKey + "%";

        ResultSet resultSet = CrudUtil.executeQuery(query, wildcardKey, wildcardKey, wildcardKey, wildcardKey, wildcardKey);
        List<ReservationEntity> reservationList = new ArrayList<>();

        while (resultSet.next()) {
            reservationList.add(
                    ReservationMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return reservationList;
    }

    @Override
    public List<ReservationEntity> getAllByStatus(String status) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name, "
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE rsv.reservation_status = ? ";

        ResultSet resultSet = CrudUtil.executeQuery(query, status);
        List<ReservationEntity> reservationList = new ArrayList<>();

        while (resultSet.next()) {
            reservationList.add(
                    ReservationMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return reservationList;
    }

    @Override
    public List<ReservationEntity> getAllByPassengerProfileId(Integer passengerProfileId) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name, "
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE rsv.reservation_passenger_id = ? ";

        ResultSet resultSet = CrudUtil.executeQuery(query, passengerProfileId);
        List<ReservationEntity> reservationList = new ArrayList<>();

        while (resultSet.next()) {
            reservationList.add(
                    ReservationMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return reservationList;
    }

    @Override
    public boolean updateReservationStatusWithTransactionHandling(Connection connection, ReservationEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE reservations SET reservation_status = ? WHERE reservation_id = ?";
        return CrudUtil.executeUpdate(
                connection, // use the shared connection
                query,
                t.getReservation_status(),
                t.getReservation_id()
        );
    }

    @Override
    public ReservationEntity getByIdWithTransactionHandling(Connection connection, Integer id) throws SQLException, ClassNotFoundException {
        String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name, "
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE rsv.reservation_id = ? ";

        ResultSet resultSet = CrudUtil.executeQuery(connection, query, id);

        if (resultSet.next()) {
            return (ReservationMapper.toEntityFromResultSet(resultSet, ""));
        }

        return null;
    }

    @Override
    public boolean hasReservationWithPassengerProfile(Integer passengerId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM reservations WHERE reservation_passenger_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, passengerId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
            return false;
        }
    }

    @Override
    public boolean hasReservationWithFlight(Integer flightId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM reservations WHERE reservation_flight_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, flightId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
            return false;
        }
    }

    @Override
    public boolean hasReservationWithBookingClass(Integer bookingClassId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM reservations WHERE reservation_booking_class_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, bookingClassId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
            return false;
        }
    }
    
    @Override
    public List<ReservationEntity> getAllBySearchKeyAndPassengerProfileId(Integer passengerId, String searchKey) throws SQLException, ClassNotFoundException {
           String query
                = "SELECT rsv.*, "
                //**** [1.0 Start : Fk: passenger_profiles tbl columns] ****
                + "pf.passenger_id, "
                + "pf.passenger_user_id, "
                + "pf.passenger_full_name, "
                + "pf.passenger_passport_no, "
                + "pf.passenger_nationality, "
                + "pf.passenger_contact_no, "
                + "pf.passenger_emergency_contact, "
                //**** [1.1 Start : Fk: user tbl columns] ****
                + "u.user_id, "
                + "u.user_role_id, "
                + "u.user_name, "
                + "u.user_email, "
                //**** [1.1.1 Start : Fk: userRole tbl columns] ****
                + "r.role_id, "
                + "r.role_name,"
                //**** [1.1.1 End : Fk: userRole tbl columns] ******
                //**** [1.1 End : Fk: user tbl columns] ******
                //**** [1.0 End : Fk: passenger_profiles tbl columns] ******

                //**** [2.0 Start : Fk: flight tbl columns] ****
                + "f.flight_id, "
                + "f.flight_route_id, "
                + "f.flight_aircraft_model_id, "
                + "f.flight_code, "
                + "f.flight_departure_time, "
                + "f.flight_arrival_time, "
                + "f.flight_airline_name, "
                //*[2.1 Start : route tbl columns] *******||| 
                + "rts.route_id, "
                + "rts.route_distance_km, "
                + "rts.route_estimated_duration_minutes, "
                //---- [2.1.1 Start : origin_airport tbl columns] -------
                + "orAp.airport_id AS route_origin_airport_id, "
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //---- [2.1.1 End : origin_airport tbl columns] ---------

                //---- [2.1.2 Start : destination_airport tbl columns] ---
                + "deAp.airport_id AS route_destination_airport_id, "
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country, "
                //---- [2.1.2 End : destination_airport tbl columns] -----
                //*[2.1 End : route tbl columns] *******||| 

                //*[2.2 Start : aircraft_model tbl columns] *******||| 
                + "acm.aircraft_model_id, "
                + "acm.aircraft_model_manufacturer, "
                + "acm.aircraft_model_name, "
                + "acm.aircraft_model_seating_capacity, "
                + "acm.aircraft_model_size_category, "
                + "acm.aircraft_model_range_km, "
                //*[2.2 End   : aircraft_model tbl columns] ********|||                 
                //**** [2.0 Start : Fk: flight tbl columns] ****

                //**** [3.0 Start : Fk: booking_class tbl columns] ****
                + "bc.booking_class_id, "
                + "bc.booking_class_name "
                //**** [3.0 End : Fk: booking_class tbl columns] ******       

                + "FROM reservations rsv "
                + "JOIN passenger_profiles pf ON rsv.reservation_passenger_id = pf.passenger_id "
                + "JOIN flights f ON rsv.reservation_flight_id = f.flight_id "
                + "JOIN booking_classes bc ON rsv.reservation_booking_class_id = bc.booking_class_id "
                + "JOIN users u ON pf.passenger_user_id = u.user_id "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "JOIN routes rts ON f.flight_route_id = rts.route_id "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "JOIN aircraft_models acm ON f.flight_aircraft_model_id = acm.aircraft_model_id "
                + "WHERE rsv.reservation_passenger_id = ? "
                + "AND (f.flight_code LIKE ? "
                + "OR pf.passenger_passport_no LIKE ? "
                + "OR rsv.reservation_date LIKE ? "
                + "OR orAp.airport_name LIKE ? "
                + "OR deAp.airport_name LIKE ?)";

        String wildcardKey = "%" + searchKey + "%";

        ResultSet resultSet = CrudUtil.executeQuery(query, passengerId, wildcardKey, wildcardKey, wildcardKey, wildcardKey, wildcardKey);
        List<ReservationEntity> reservationList = new ArrayList<>();

        while (resultSet.next()) {
            reservationList.add(
                    ReservationMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return reservationList;
    }
    //---------- [End : Custom Query] ----------------
}
