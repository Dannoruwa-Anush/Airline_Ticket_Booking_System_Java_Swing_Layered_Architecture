/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dao.custom.impl;

import java.sql.SQLException;
import java.util.List;
import myapp.dao.CrudUtil;
import myapp.dao.custom.IUserDao;
import myapp.entity.UserEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import myapp.mapper.UserMapper;
/**
 *
 * @author HP
 */
public class UserDaoImpl implements IUserDao{

    //---------- [Start : CRUD Query] ----------------
    @Override
    public boolean save(UserEntity t) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users(user_role_id, user_name, user_email, user_password) VALUES (?, ?, ?, ?)";
        return CrudUtil.executeUpdate(
            query,
            t.getUserRole().getRole_id(),
            t.getUserName(),
            t.getUserEmail(),
            t.getUserPassword()
        );
    }

    @Override
    public boolean update(UserEntity t) throws SQLException, ClassNotFoundException {
        String query = "UPDATE users SET user_email = ?, user_password = ? WHERE user_id = ?";
        return CrudUtil.executeUpdate(
            query,
            t.getUserEmail(),
            t.getUserPassword(),
            t.getUserId()
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM users WHERE user_id = ?";
        return CrudUtil.executeUpdate(query, id);
    }

    @Override
    public UserEntity getById(Integer id) throws SQLException, ClassNotFoundException {
        String query = 
                "SELECT u.user_id, u.user_role_id, u.user_name," //The user_email and user_password have not been selected in order to protect sensitive information.
                
                //**** [Start : Fk: user_roles tbl columns] *****
                + "r.role_name "
                //**** [End : Fk: user_roles tbl columns] *******
                
                + "FROM users u "
                + "JOIN user_roles r ON u.user_role_id = r.role_id "
                + "WHERE u.user_id = ?";
        
        ResultSet resultSet = CrudUtil.executeQuery(query, id);
        if (resultSet.next()) {    
            return UserMapper.toEntityFromResultSet(resultSet, "");
        }
        return null;
    }

    @Override
    public List<UserEntity> getAll() throws SQLException, ClassNotFoundException {
        String query = 
                "SELECT u.user_id, u.user_role_id, u.user_name,"  //The user_email and user_password have not been selected in order to protect sensitive information.
                
                //**** [Start : Fk: user_roles tbl columns] *****
                + "r.role_name "
                //**** [End : Fk: user_roles tbl columns] *******
                
                + "FROM users u "
                + "JOIN user_roles r ON u.user_role_id = r.role_id";
        
        ResultSet resultSet = CrudUtil.executeQuery(query);
        List<UserEntity> userList = new ArrayList<>();

        while (resultSet.next()) {
            userList.add(
                UserMapper.toEntityFromResultSet(resultSet, "")
            );
        }

        return userList;
    }
    //---------- [End : CRUD Query] ------------------
    
    //---------- [Start : Custom Query] --------------
    @Override
    public UserEntity loginUser(UserEntity t) throws SQLException, ClassNotFoundException {
        // Query to check if a user with the given email and password exists
        String query = 
                "SELECT u.user_id, u.user_role_id, u.user_name," //The user_email and user_password have not been selected in order to protect sensitive information.
                
                //**** [Start : Fk: user_roles tbl columns] *****
                + "r.role_name "
                //**** [End : FK - user_roles tbl columns] ******
                
                + "FROM users u JOIN user_roles r ON u.user_role_id = r.role_id "
                + "WHERE u.user_email = ? AND u.user_password = ?";
        
        ResultSet resultSet;
        resultSet = CrudUtil.executeQuery(
                query,
                t.getUserEmail(),
                t.getUserPassword()
        );

        if (resultSet.next()) {    
            return UserMapper.toEntityFromResultSet(resultSet, "");
        }

        // No user found
        return null;
    }
    
    @Override
    public boolean hasUsersWithRole(Integer roleId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM users WHERE user_role_id = ?";
        try (ResultSet resultSet = CrudUtil.executeQuery(query, roleId)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returns true if there are any users with that role
            }
            return false; // Return false if no results
        }
    }
    //---------- [End : Custom Query] ----------------
}
