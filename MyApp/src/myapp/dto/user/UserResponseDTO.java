/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.user;

import myapp.dto.userRole.UserRoleResponseDTO;

/**
 *
 * @author HP
 */
public class UserResponseDTO {
    private int userId;
    private String userName;
    private UserRoleResponseDTO userRole; // Using object reference for FK

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public UserResponseDTO() {
    }

    public UserResponseDTO(int userId, String userName, UserRoleResponseDTO userRole) {
        setUserId(userId);
        setUserName(userName);
        setUserRole(userRole);
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserRoleResponseDTO getUserRole() {
        return userRole;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserRole(UserRoleResponseDTO userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" + "userId=" + userId + ", userName=" + userName + ", userRole=" + userRole + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
