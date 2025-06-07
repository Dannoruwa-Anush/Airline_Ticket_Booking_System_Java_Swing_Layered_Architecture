/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

/**
 *
 * @author HP
 */
public class PassengerProfileEntity {

    private int passengerId;
    private UserEntity user; // Using object reference for FK
    private String passengerFullName;
    private String passengerPassportNumber;
    private String passengerNationality;
    private String passengerContactNumber;
    private String passengerEmergencyContactNumber;

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public PassengerProfileEntity() {
    }

    public PassengerProfileEntity(int passengerId, UserEntity user, String passengerFullName, String passengerPassportNumber, String passengerNationality, String passengerContactNumber, String passengerEmergencyContactNumber) {
        setPassengerId(passengerId);
        setUser(user);
        setPassengerFullName(passengerFullName);
        setPassengerPassportNumber(passengerPassportNumber);
        setPassengerNationality(passengerNationality);
        setPassengerContactNumber(passengerContactNumber);
        setPassengerEmergencyContactNumber(passengerEmergencyContactNumber);
    }

    public int getPassengerId() {
        return passengerId;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getPassengerFullName() {
        return passengerFullName;
    }

    public String getPassengerPassportNumber() {
        return passengerPassportNumber;
    }

    public String getPassengerNationality() {
        return passengerNationality;
    }

    public String getPassengerContactNumber() {
        return passengerContactNumber;
    }

    public String getPassengerEmergencyContactNumber() {
        return passengerEmergencyContactNumber;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setPassengerFullName(String passengerFullName) {
        this.passengerFullName = passengerFullName;
    }

    public void setPassengerPassportNumber(String passengerPassportNumber) {
        this.passengerPassportNumber = passengerPassportNumber;
    }

    public void setPassengerNationality(String passengerNationality) {
        this.passengerNationality = passengerNationality;
    }

    public void setPassengerContactNumber(String passengerContactNumber) {
        this.passengerContactNumber = passengerContactNumber;
    }

    public void setPassengerEmergencyContactNumber(String passengerEmergencyContactNumber) {
        this.passengerEmergencyContactNumber = passengerEmergencyContactNumber;
    }

    @Override
    public String toString() {
        return "PassengerProfileEntity{" + "passengerId=" + passengerId + ", user=" + user + ", passengerFullName=" + passengerFullName + ", passengerPassportNumber=" + passengerPassportNumber + ", passengerNationality=" + passengerNationality + ", passengerContactNumber=" + passengerContactNumber + ", passengerEmergencyContactNumber=" + passengerEmergencyContactNumber + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------
}
