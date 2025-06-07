/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.user;

/**
 *
 * @author HP
 */
public class UserRequestDTO {

    private String userName;
    private String userEmail;
    private String userPassword;
    private int role_id; //FK

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public UserRequestDTO() {
    }

    public UserRequestDTO(String userName, String userEmail, String userPassword, int role_id) {
        setUserName(userName);
        setUserEmail(userEmail);
        setUserPassword(userPassword);
        setRole_id(role_id);
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

    public int getRole_id() {
        return role_id;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("username cannot be null or empty.");
        }
        this.userName = userName.trim();
    }

    public void setUserEmail(String userEmail) {
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("user email cannot be null or empty.");
        }
        this.userEmail = userEmail.trim();
    }

    public void setUserPassword(String userPassword) {
        if (userPassword == null || userPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("user password cannot be null or empty.");
        }
        this.userPassword = userPassword.trim();
    }

    public void setRole_id(int role_id) {
        if (role_id <= 0) {
            throw new IllegalArgumentException("Role id must be a positive integer.");
        }
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" + "userName=" + userName + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", role_id=" + role_id + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
