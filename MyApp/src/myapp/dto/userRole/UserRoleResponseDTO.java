/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.userRole;

/**
 *
 * @author HP
 */
public class UserRoleResponseDTO {
    private int role_id;
    private String role_name;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    //No arg constructor
    public UserRoleResponseDTO() {
        
    }

    //constructor
    public UserRoleResponseDTO(int role_id, String role_name) {
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
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) { 
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" + "role_id=" + role_id + ", role_name=" + role_name + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
