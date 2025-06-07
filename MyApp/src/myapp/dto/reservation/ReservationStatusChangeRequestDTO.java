/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.reservation;

/**
 *
 * @author HP
 */
public class ReservationStatusChangeRequestDTO {
    private String reservation_status;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------

    public ReservationStatusChangeRequestDTO() {
    }

    public ReservationStatusChangeRequestDTO(String reservation_status) {  
        setReservation_status(reservation_status);
    }

    public String getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(String reservation_status) {
        if (reservation_status == null || reservation_status.trim().isEmpty()) {
            throw new IllegalArgumentException("Reservation status cannot be null or empty.");
        }
        this.reservation_status = reservation_status.trim();
    }

    @Override
    public String toString() {
        return "ReservationStatusChangeRequestDTO{" + ", reservation_status=" + reservation_status + '}';
    }
    
    //------- [End : constructor, getter(), setter(), toString()]-------
}
