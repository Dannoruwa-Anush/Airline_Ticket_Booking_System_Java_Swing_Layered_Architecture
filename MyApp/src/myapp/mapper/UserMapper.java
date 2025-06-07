/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.user.UserLoginRequestDTO;
import myapp.dto.user.UserRequestDTO;
import myapp.dto.user.UserResponseDTO;
import myapp.entity.UserEntity;
import myapp.entity.UserRoleEntity;
import myapp.util.security.SecurityUtil;

import java.sql.SQLException;
import java.sql.ResultSet;
import myapp.util.query.ResultSetUtil;

/**
 *
 * @author HP
 */
public class UserMapper {

    //DTO to entity conversion
    public static UserEntity toSaveEntity(UserRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UserRequestDTO must not be null");
        }

        UserEntity entity = new UserEntity();
        entity.setUserName(dto.getUserName());
        entity.setUserEmail(dto.getUserEmail());

        //password hashing using SHA-256
        String hashedPassword = SecurityUtil.hashPassword(dto.getUserPassword());
        entity.setUserPassword(hashedPassword);

        //FK Object
        entity.setUserRole(UserRoleMapper.toFkObjectEntity(dto.getRole_id()));

        return entity;
    }

    public static UserEntity toUpdateEntity(Integer id, UserRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("UserRequestDTO must not be null");
        }

        UserEntity entity = new UserEntity();
        entity.setUserId(id);
        entity.setUserName(dto.getUserName());
        entity.setUserEmail(dto.getUserEmail());

        //FK Object
        entity.setUserRole(UserRoleMapper.toFkObjectEntity(dto.getRole_id()));

        return entity;
    }

    //DTO to entity conversion
    public static UserEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        UserEntity entity = new UserEntity();
        entity.setUserId(id);

        return entity;
    }

    //DTO to entity conversion
    public static UserEntity toLoginEntity(UserLoginRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UserRequestDTO must not be null");
        }

        UserEntity entity = new UserEntity();
        entity.setUserEmail(dto.getUserEmail());

        //password hashing using SHA-256
        String hashedPassword = SecurityUtil.hashPassword(dto.getUserPassword());
        entity.setUserPassword(hashedPassword);

        return entity;
    }

    //Entity to DTO conversion
    public static UserResponseDTO toDTO(UserEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("UserEntity must not be null");
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());

        //FK Object
        dto.setUserRole(UserRoleMapper.toDTO(entity.getUserRole()));

        return dto;
    }

    //SQL ResultSet to Entity
    public static UserEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        UserEntity entity = new UserEntity();
        entity.setUserId(rs.getInt(prefix + "user_id")); //prfeix only for PK
        entity.setUserName(rs.getString("user_name"));
        //The userEmail and userPassword have not been selected in order to protect sensitive information.

        //FK object
        if(ResultSetUtil.hasColumn(rs, "user_role_id")){
            UserRoleEntity userRole = UserRoleMapper.toEntityFromResultSet(rs, "user_");
            entity.setUserRole(userRole);
        }
               
        return entity;
    }
}
