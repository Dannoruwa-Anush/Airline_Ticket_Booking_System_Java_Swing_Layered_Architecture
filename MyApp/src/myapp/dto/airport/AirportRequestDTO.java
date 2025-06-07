/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.airport;

/**
 *
 * @author HP
 */
public class AirportRequestDTO {
    
    private String airport_code;
    private String airport_name;
    private String airport_city;
    private String airport_country;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------
    public AirportRequestDTO() {
    }

    public AirportRequestDTO(String airport_code, String airport_name, String airport_city, String airport_country) {
        setAirport_code(airport_code);
        setAirport_name(airport_name);
        setAirport_city(airport_city);
        setAirport_country(airport_country);
    }

    public String getAirport_code() {
        return airport_code;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public String getAirport_city() {
        return airport_city;
    }

    public String getAirport_country() {
        return airport_country;
    }

    public void setAirport_code(String airport_code) {
        if (airport_code == null || airport_code.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport code cannot be null or empty.");
        }
        this.airport_code = airport_code.trim();
    }

    public void setAirport_name(String airport_name) {
        if (airport_name == null || airport_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport name cannot be null or empty.");
        }
        this.airport_name = airport_name.trim();
    }

    public void setAirport_city(String airport_city) {
        if (airport_city == null || airport_city.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport city cannot be null or empty.");
        }
        this.airport_city = airport_city.trim();
    }

    public void setAirport_country(String airport_country) {
        if (airport_country == null || airport_country.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport country cannot be null or empty.");
        }
        this.airport_country = airport_country.trim();
    }

    @Override
    public String toString() {
        return "AirportRequestDTO{" + "airport_code=" + airport_code + ", airport_name=" + airport_name + ", airport_city=" + airport_city + ", airport_country=" + airport_country + '}';
    }
    
    //------- [End : constructor, getter(), setter(), toString()]---------   
}
