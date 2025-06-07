/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class AircraftModelEntity {
    private int aircraftModel_id;
    private String aircraftModel_Manufacturer;
    private String aircraftModel_Name;
    private int aircraftModel_SeatCapacity;
    private BigDecimal aircraftModel_Range_KM;
    private String aircraftModel_SizeCategory; //ENUM

    //------- [Start : constructor, getter(), setter(), toString()]-------
    public AircraftModelEntity() {
    }

    public AircraftModelEntity(int aircraftModel_id, String aircraftModel_Manufacturer, String aircraftModel_Name, int aircraftModel_SeatCapacity, BigDecimal aircraftModel_Range_KM, String aircraftModel_SizeCategory) {
        setAircraftModel_id(aircraftModel_id);
        setAircraftModel_Manufacturer(aircraftModel_Manufacturer);
        setAircraftModel_Name(aircraftModel_Name);
        setAircraftModel_SeatCapacity(aircraftModel_SeatCapacity);
        setAircraftModel_Range_KM(aircraftModel_Range_KM);
        setAircraftModel_SizeCategory(aircraftModel_SizeCategory);
    }

    public int getAircraftModel_id() {
        return aircraftModel_id;
    }

    public void setAircraftModel_id(int aircraftModel_id) {
        this.aircraftModel_id = aircraftModel_id;
    }

    public String getAircraftModel_Manufacturer() {
        return aircraftModel_Manufacturer;
    }

    public void setAircraftModel_Manufacturer(String aircraftModel_Manufacturer) {
        this.aircraftModel_Manufacturer = aircraftModel_Manufacturer;
    }

    public String getAircraftModel_Name() {
        return aircraftModel_Name;
    }

    public void setAircraftModel_Name(String aircraftModel_Name) {
        this.aircraftModel_Name = aircraftModel_Name;
    }

    public int getAircraftModel_SeatCapacity() {
        return aircraftModel_SeatCapacity;
    }

    public void setAircraftModel_SeatCapacity(int aircraftModel_SeatCapacity) {
        this.aircraftModel_SeatCapacity = aircraftModel_SeatCapacity;
    }

    public BigDecimal getAircraftModel_Range_KM() {
        return aircraftModel_Range_KM;
    }

    public void setAircraftModel_Range_KM(BigDecimal aircraftModel_Range_KM) {
        this.aircraftModel_Range_KM = aircraftModel_Range_KM;
    }

    public String getAircraftModel_SizeCategory() {
        return aircraftModel_SizeCategory;
    }

    public void setAircraftModel_SizeCategory(String aircraftModel_SizeCategory) {
        this.aircraftModel_SizeCategory = aircraftModel_SizeCategory;
    }

    @Override
    public String toString() {
        return "AircraftModelEnity{" + "aircraftModel_id=" + aircraftModel_id + ", aircraftModel_Manufacturer=" + aircraftModel_Manufacturer + ", aircraftModel_Name=" + aircraftModel_Name + ", aircraftModel_SeatCapacity=" + aircraftModel_SeatCapacity + ", aircraftModel_Range_KM=" + aircraftModel_Range_KM + ", aircraftModel_SizeCategory=" + aircraftModel_SizeCategory + '}';
    } 
    //------- [End : constructor, getter(), setter(), toString()]-------
}
