/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IRouteDao;
import myapp.entity.RouteEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import myapp.mapper.RouteMapper;

/**
 *
 * @author HP
 */
public class RouteDaoImpl implements IRouteDao {

    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(RouteEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO routes(route_origin_airport_id, route_destination_airport_id, route_distance_km, route_estimated_duration_minutes) VALUES (?, ?, ?, ?)";
        return CrudUtil.executeUpdate(
                query,
                t.getOriginAirport().getAirport_id(),
                t.getDestinationAirport().getAirport_id(),
                t.getRoute_distance_km(),
                t.getRoute_estimated_duration_minutes()
        );
    }

    @Override
    public boolean update(RouteEntity t) throws SQLException, ClassNotFoundException {

        String query = "UPDATE routes SET route_origin_airport_id = ?, route_destination_airport_id = ?, route_distance_km = ?, route_estimated_duration_minutes = ? WHERE route_id = ?";
        return CrudUtil.executeUpdate(
                query,
                t.getOriginAirport().getAirport_id(),
                t.getDestinationAirport().getAirport_id(),
                t.getRoute_distance_km(),
                t.getRoute_estimated_duration_minutes(),
                t.getRoute_id()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM routes WHERE route_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public RouteEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        // SQL query with proper aliasing for the columns to avoid ambiguity
        String query =
                "SELECT rts.*, "
                //**** [Start : Fk: origin_airport tbl columns] *****
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //**** [End : Fk: origin_airport tbl columns] *****

                //**** [Start : Fk: destination_airport tbl columns] *****
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country "
                //**** [End : Fk: destination_airport tbl columns] *****

                + "FROM routes rts "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "WHERE rts.route_id = ?";

        // Execute the query with the provided route ID
        ResultSet resultSet = CrudUtil.executeQuery(query, id);

        // If the result set contains a row, populate the RouteEntity
        if (resultSet.next()) {
            return RouteMapper.toEntityFromResultSet(resultSet, "");
        }

        // Return null if no matching route is found
        return null;
    }

    @Override
    public List<RouteEntity> getAll() throws SQLException, ClassNotFoundException {
        String query = 
                "SELECT rts.*, "
                //**** [Start : Fk: origin_airport tbl columns] *****
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //**** [End : Fk: origin_airport tbl columns] *****

                //**** [Start : Fk: destination_airport tbl columns] *****
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country "
                //**** [End : Fk: destination_airport tbl columns] *****

                + "FROM routes rts "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id ";

        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<RouteEntity> routeList = new ArrayList<>();

        while (resultSet.next()) {
            routeList.add(
                    RouteMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return routeList;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] --------------
    @Override
    public boolean hasRoutesWithAirport(Integer airportId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM routes r "
                + "WHERE r.route_origin_airport_id = ? OR r.route_destination_airport_id = ?";

        ResultSet resultSet = CrudUtil.executeQuery(query, airportId, airportId);

        // If the result set has data, check if the count is greater than 0
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;  // If count is greater than 0, there are routes for this airport
        }

        return false;
    }

    @Override
    public List<RouteEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query = 
                "SELECT rts.*, "
                //**** [Start : Fk: origin_airport tbl columns] *****
                + "orAp.airport_code AS origin_airport_code, "
                + "orAp.airport_name AS origin_airport_name, "
                + "orAp.airport_city AS origin_airport_city, "
                + "orAp.airport_country AS origin_airport_country, "
                //**** [End : Fk: origin_airport tbl columns] *******

                //**** [Start : Fk: destination_airport tbl columns] *****
                + "deAp.airport_code AS destination_airport_code, "
                + "deAp.airport_name AS destination_airport_name, "
                + "deAp.airport_city AS destination_airport_city, "
                + "deAp.airport_country AS destination_airport_country "
                //**** [End : Fk: destination_airport tbl columns] *******

                + "FROM routes rts "
                + "JOIN airports orAp ON rts.route_origin_airport_id = orAp.airport_id "
                + "JOIN airports deAp ON rts.route_destination_airport_id = deAp.airport_id "
                + "WHERE orAp.airport_name LIKE ? OR deAp.airport_name LIKE ?";

        List<RouteEntity> routeList = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%", "%" + searchKey + "%")) {
            while (resultSet.next()) {
                routeList.add(RouteMapper.toEntityFromResultSet(resultSet, ""));
            }
        }

        return routeList;
    }
    //---------- [End : Custom Query] --------------
}
