/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myapp.dao;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author HP
 * @param <T>
 * @param <ID>
 */
public interface ICrudDAO<T, ID> extends ISuperDAO{
    //Generic Interface (Basic CRUD operations have been defined here)
    //T represents the entity type
    //ID represents the type of the entity's identifier (primary key).
    
    boolean save(T t) throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    T getById(ID id) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
}
