/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IBookingClassDao;
import myapp.entity.BookingClassEntity;
import myapp.mapper.BookingClassMapper;

/**
 *
 * @author HP
 */
public class BookingClassDaoImpl implements IBookingClassDao {

    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(BookingClassEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO booking_classes (booking_class_name) VALUES (?)";
        return CrudUtil.executeUpdate(query, t.getBookingClass_name());
    }

    @Override
    public boolean update(BookingClassEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE booking_classes SET booking_class_name =? WHERE booking_class_id = ?";
        return CrudUtil.executeUpdate(query, t.getBookingClass_name(), t.getBookingClass_id());
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM booking_classes WHERE booking_class_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public BookingClassEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM booking_classes where booking_class_id = ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, id);

        while (resultSet.next()) {
            return BookingClassMapper.toEntityFromResultSet(resultSet, "");
        }
        return null;
    }

    @Override
    public List<BookingClassEntity> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM booking_classes";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<BookingClassEntity> bookingClassEntities = new ArrayList<>();
        while (resultSet.next()) {
            bookingClassEntities.add(
                BookingClassMapper.toEntityFromResultSet(resultSet, "")
            );
        }
        return bookingClassEntities;
    }
    //---------- [End : CRUD Query] ------------------

    //---------- [Start : Custom Query] --------------
    @Override
    public List<BookingClassEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM booking_classes WHERE booking_class_name LIKE ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");

        List<BookingClassEntity> bookingClassEntities = new ArrayList<>();
        while (resultSet.next()) {
            bookingClassEntities.add(
                BookingClassMapper.toEntityFromResultSet(resultSet, "")
            );
        }
        return bookingClassEntities;
    }
    //---------- [End : Custom Query] ----------------
}
