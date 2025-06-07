/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.passengerProfile;

/**
 *
 * @author HP
 */
public class PassengerProfileRequestDTO {
    private int userId; //FK
    private String passengerFullName;
    private String passengerPassportNumber;
    private String passengerNationality;
    private String passengerContactNumber;
    private String passengerEmergencyContactNumber;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------

    public PassengerProfileRequestDTO() {
    }

    public PassengerProfileRequestDTO(int userId, String passengerFullName, String passengerPassportNumber, String passengerNationality, String passengerContactNumber, String passengerEmergencyContactNumber) {
        setUserId(userId);
        setPassengerFullName(passengerFullName);
        setPassengerPassportNumber(passengerPassportNumber);
        setPassengerNationality(passengerNationality);
        setPassengerContactNumber(passengerContactNumber);
        setPassengerEmergencyContactNumber(passengerEmergencyContactNumber);
    }

    public int getUserId() {
        return userId;
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

    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User id must be a positive integer.");
        }
        this.userId = userId;
    }

    public void setPassengerFullName(String passengerFullName) {
        if (passengerFullName == null || passengerFullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger full name cannot be null or empty.");
        }
        this.passengerFullName = passengerFullName.trim();
    }

    public void setPassengerPassportNumber(String passengerPassportNumber) {
        if (passengerPassportNumber == null || passengerPassportNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger passport no cannot be null or empty.");
        }
        this.passengerPassportNumber = passengerPassportNumber.trim();
    }

    public void setPassengerNationality(String passengerNationality) {
        if (passengerNationality == null || passengerNationality.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger nationality cannot be null or empty.");
        }
        this.passengerNationality = passengerNationality.trim();
    }

    public void setPassengerContactNumber(String passengerContactNumber) {
        if (passengerContactNumber == null || passengerContactNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger contact no cannot be null or empty.");
        }
        this.passengerContactNumber = passengerContactNumber.trim();
    }

    public void setPassengerEmergencyContactNumber(String passengerEmergencyContactNumber) {
        if (passengerEmergencyContactNumber == null || passengerEmergencyContactNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger emergency contact no cannot be null or empty.");
        }
        this.passengerEmergencyContactNumber = passengerEmergencyContactNumber.trim();
    }

    @Override
    public String toString() {
        return "PassengerProfileRequestDTO{" + ", userId=" + userId + ", passengerFullName=" + passengerFullName + ", passengerPassportNumber=" + passengerPassportNumber + ", passengerNationality=" + passengerNationality + ", passengerContactNumber=" + passengerContactNumber + ", passengerEmergencyContactNumber=" + passengerEmergencyContactNumber + '}';
    }  
    //------- [End : constructor, getter(), setter(), toString()]-------
}
