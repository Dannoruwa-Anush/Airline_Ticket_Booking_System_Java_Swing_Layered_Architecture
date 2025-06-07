/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.mapper;

import myapp.dto.userRole.UserRoleRequestDTO;
import myapp.dto.userRole.UserRoleResponseDTO;
import myapp.entity.UserRoleEntity;

import java.sql.SQLException;
import java.sql.ResultSet;
import myapp.util.query.ResultSetUtil;

/**
 *
 * @author HP
 */
public class UserRoleMapper {

    //DTO to entity conversion
    public static UserRoleEntity toSaveEntity(UserRoleRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UserRoleRequestDTO must not be null");
        }

        UserRoleEntity entity = new UserRoleEntity();
        entity.setRole_name(dto.getRole_name());

        return entity;
    }

    //DTO to entity conversion
    public static UserRoleEntity toUpdateEntity(Integer id, UserRoleRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }

        if (dto == null) {
            throw new IllegalArgumentException("UserRoleRequestDTO must not be null");
        }

        String roleName = dto.getRole_name();
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Role name must not be null or empty");
        }

        UserRoleEntity entity = new UserRoleEntity();
        entity.setRole_id(id);
        entity.setRole_name(roleName.trim());

        return entity;
    }

    //DTO to entity conversion
    public static UserRoleEntity toFkObjectEntity(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: must be a positive non-null integer.");
        }

        UserRoleEntity entity = new UserRoleEntity();
        entity.setRole_id(id);

        return entity;
    }

    //Entity to DTO conversion
    public static UserRoleResponseDTO toDTO(UserRoleEntity entity) {
        UserRoleResponseDTO dto = new UserRoleResponseDTO();

        if (entity != null) {
            dto.setRole_id(entity.getRole_id());
            dto.setRole_name(entity.getRole_name());
        }

        return dto;
    }

    //SQL ResultSet to Entity
    public static UserRoleEntity toEntityFromResultSet(ResultSet rs, String prefix) throws SQLException {
        UserRoleEntity entity = new UserRoleEntity();
        entity.setRole_id(rs.getInt(prefix + "role_id")); //prfeix only for PK
        entity.setRole_name(rs.getString("role_name"));
        
        return entity;
    }
}
