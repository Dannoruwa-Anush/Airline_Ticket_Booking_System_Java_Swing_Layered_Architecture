/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.user;

/**
 *
 * @author HP
 */
public class UserLoginRequestDTO {
    private String userEmail;
    private String userPassword;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------
    public UserLoginRequestDTO() {
    }

    public UserLoginRequestDTO(String userEmail, String userPassword) {
        setUserEmail(userEmail);
        setUserPassword(userPassword);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
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

    @Override
    public String toString() {
        return "UserLoginRequestDTO{" + "userEmail=" + userEmail + ", userPassword=" + userPassword + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
