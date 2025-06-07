/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

/**
 *
 * @author HP
 */
public class UserEntity {
    private int user_Id;
    private UserRoleEntity userRole; // Using object reference for FK
    private String userName;
    private String userEmail;
    private String userPassword;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public UserEntity() {
    }

    public UserEntity(int userId, UserRoleEntity userRole, String userName, String userEmail, String userPassword) {
        setUserId(userId);
        setUserRole(userRole);
        setUserName(userName);
        setUserEmail(userEmail);
        setUserPassword(userPassword);
    }
    
    public int getUserId() {
        return user_Id;
    }

    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserId(int userId) {
        this.user_Id = userId;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "userId=" + user_Id + ", userRole=" + userRole + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword=" + userPassword + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
