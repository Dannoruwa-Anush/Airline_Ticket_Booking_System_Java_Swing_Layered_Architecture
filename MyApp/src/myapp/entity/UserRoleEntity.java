/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

/**
 *
 * @author HP
 */
public class UserRoleEntity {
    
    private int role_id;
    private String role_name;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------
    //No arg constructor
    public UserRoleEntity() {

    }
    
    //constructor
    public UserRoleEntity(int role_id, String role_name) {
        setRole_id(role_id);
        setRole_name(role_name);
    }

    public int getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_id(int role_id) {
        if (role_id < 0) {
            throw new IllegalArgumentException("Role ID must be positive.");
        }
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) {
        if (role_name == null || role_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Role name must not be null or empty.");
        }

        if (!role_name.matches("^[A-Za-z]+$")) {
           throw new IllegalArgumentException("Role name must contain only letters (A-Z or a-z).");
        }

        this.role_name = role_name.trim();
   }

    @Override
    public String toString() {
        return "UserRoleEntity{" + "role_id=" + role_id + ", role_name=" + role_name + '}';
    }    
    //------- [End : constructor, getter(), setter(), toString()]-------
}
