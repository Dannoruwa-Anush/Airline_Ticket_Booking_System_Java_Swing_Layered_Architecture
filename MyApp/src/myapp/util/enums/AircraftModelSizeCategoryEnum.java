/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.enums;

/**
 *
 * @author HP
 */
public enum AircraftModelSizeCategoryEnum {
    //Constructor is called
    SMALL("Samll"), MEDIUM("Medium"), LARGE("Large"); //enum constant list
    
    private final String sizeCategoryName;
    
    //constructor
    AircraftModelSizeCategoryEnum(String sizeCategoryName){
        this.sizeCategoryName = sizeCategoryName;
    }

    public String getSizeCategoryName() {
        return sizeCategoryName;
    }
    
    public static AircraftModelSizeCategoryEnum fromString(String sizeCategoryName) {
        for (AircraftModelSizeCategoryEnum sizeCategory : AircraftModelSizeCategoryEnum.values()) {
            if (sizeCategory.sizeCategoryName.equalsIgnoreCase(sizeCategoryName)) {
                return sizeCategory;
            }
        }
        throw new IllegalArgumentException("No enum constant for size category name: " + sizeCategoryName);
    }
}
