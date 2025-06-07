/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.aircraftModel;

import java.math.BigDecimal;
import myapp.util.enums.AircraftModelSizeCategoryEnum;

/**
 *
 * @author HP
 */
public class AircraftModelRequestDTO {

    // ---- [Start: Constants for classification] ----
    private static final int SMALL_SEAT_THRESHOLD = 50;
    private static final int MEDIUM_SEAT_THRESHOLD = 150;
    // ---- [End: Constants for classification] ----

    private String aircraftModel_Manufacturer;
    private String aircraftModel_Name;
    private int aircraftModel_SeatCapacity;
    private BigDecimal aircraftModel_Range_KM;
    private String aircraftModel_SizeCategory; 

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public AircraftModelRequestDTO() {
    }

    public AircraftModelRequestDTO(String aircraftModel_Manufacturer, String aircraftModel_Name, int aircraftModel_SeatCapacity, BigDecimal aircraftModel_Range_KM) {
        setAircraftModel_Manufacturer(aircraftModel_Manufacturer);
        setAircraftModel_Name(aircraftModel_Name);
        setAircraftModel_SeatCapacity(aircraftModel_SeatCapacity);
        setAircraftModel_Range_KM(aircraftModel_Range_KM);

        setAircraftModel_SizeCategory(classifyAircraftModelSize(aircraftModel_SeatCapacity));
    }

    public String getAircraftModel_Manufacturer() {
        return aircraftModel_Manufacturer;
    }

    public void setAircraftModel_Manufacturer(String aircraftModel_Manufacturer) {
        if (aircraftModel_Manufacturer == null || aircraftModel_Manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer name cannot be null or empty.");
        }
        this.aircraftModel_Manufacturer = aircraftModel_Manufacturer.trim();
    }

    public String getAircraftModel_Name() {
        return aircraftModel_Name;
    }

    public void setAircraftModel_Name(String aircraftModel_Name) {
        if (aircraftModel_Name == null || aircraftModel_Name.trim().isEmpty()) {
            throw new IllegalArgumentException("Aircraft model name cannot be null or empty.");
        }
        this.aircraftModel_Name = aircraftModel_Name.trim();
    }

    public int getAircraftModel_SeatCapacity() {
        return aircraftModel_SeatCapacity;
    }

    public void setAircraftModel_SeatCapacity(int aircraftModel_SeatCapacity) {
        if (aircraftModel_SeatCapacity <= 0) {
            throw new IllegalArgumentException("Seat capacity must be a positive integer.");
        }
        this.aircraftModel_SeatCapacity = aircraftModel_SeatCapacity;
    }

    public BigDecimal getAircraftModel_Range_KM() {
        return aircraftModel_Range_KM;
    }

    public void setAircraftModel_Range_KM(BigDecimal aircraftModel_Range_KM) {
        if (aircraftModel_Range_KM == null) {
            throw new IllegalArgumentException("Range cannot be null.");
        }
        if (aircraftModel_Range_KM.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Range must be a positive value.");
        }
        this.aircraftModel_Range_KM = aircraftModel_Range_KM;
    }

    public String getAircraftModel_SizeCategory() {
        return aircraftModel_SizeCategory;
    }

    public void setAircraftModel_SizeCategory(String aircraftModel_SizeCategory) {
        if (aircraftModel_SizeCategory == null || aircraftModel_SizeCategory.trim().isEmpty()) {
            throw new IllegalArgumentException("Size category cannot be null or empty");
        }
        this.aircraftModel_SizeCategory = aircraftModel_SizeCategory.trim();
    }

    @Override
    public String toString() {
        return "AircraftModelEnity{" + "aircraftModel_Manufacturer=" + aircraftModel_Manufacturer + ", aircraftModel_Name=" + aircraftModel_Name + ", aircraftModel_SeatCapacity=" + aircraftModel_SeatCapacity + ", aircraftModel_Range_KM=" + aircraftModel_Range_KM + ", aircraftModel_SizeCategory=" + aircraftModel_SizeCategory + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------

    // Helper method to classify size
    private String classifyAircraftModelSize(int seatCapacity) {
        if (seatCapacity > MEDIUM_SEAT_THRESHOLD) {
            return AircraftModelSizeCategoryEnum.LARGE.getSizeCategoryName();
        } else if (seatCapacity > SMALL_SEAT_THRESHOLD) {
            return AircraftModelSizeCategoryEnum.MEDIUM.getSizeCategoryName();
        } else {
            return AircraftModelSizeCategoryEnum.SMALL.getSizeCategoryName();
        }
    }
}
