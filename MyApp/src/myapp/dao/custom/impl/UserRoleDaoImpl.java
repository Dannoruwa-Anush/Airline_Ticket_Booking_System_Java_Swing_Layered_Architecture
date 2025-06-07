/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IUserRoleDao;
import myapp.entity.UserRoleEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import myapp.mapper.UserRoleMapper;

/**
 *
 * @author HP
 */
public class UserRoleDaoImpl implements IUserRoleDao{
    
    //---------- [Start : CRUD Query] ------------------
    @Override
    public boolean save(UserRoleEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO user_roles (role_name) VALUES (?)";
        return CrudUtil.executeUpdate(query, t.getRole_name());
    }

    @Override
    public boolean update(UserRoleEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE user_roles SET role_name =? WHERE role_id = ?";
        return CrudUtil.executeUpdate(query, t.getRole_name(), t.getRole_id());
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM user_roles WHERE role_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public UserRoleEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM user_roles where role_id = ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, id);
        
        while (resultSet.next()) {
            return UserRoleMapper.toEntityFromResultSet(resultSet, ""); //pefix is no needed for parent class 
        }
        return null;
    }

    @Override
    public List<UserRoleEntity> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM user_roles";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        while (resultSet.next()) {
            userRoleEntities.add(
                UserRoleMapper.toEntityFromResultSet(resultSet, "") //pefix is no needed for parent class 
            );
        }
        return userRoleEntities;
    }
    //---------- [End : CRUD Query] ------------------
    
    
    //---------- [Start : Custom Query] --------------
    @Override
    public UserRoleEntity getByRoleName(String roleName) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM user_roles where role_name = ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, roleName);
        
        while (resultSet.next()) {
            return UserRoleMapper.toEntityFromResultSet(resultSet, ""); //pefix is no needed for parent class 
        }
        return null;
    }
    
    @Override
    public List<UserRoleEntity> getAllBySearchKey(String searchKey) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM user_roles WHERE role_name LIKE ?";
        ResultSet resultSet = CrudUtil.executeQuery(query, "%" + searchKey + "%");

        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        while (resultSet.next()) {
            userRoleEntities.add(
                UserRoleMapper.toEntityFromResultSet(resultSet, "") //pefix is no needed for parent class 
            );
        }
        return userRoleEntities;
    }
    //---------- [End : Custom Query] ----------------
}
