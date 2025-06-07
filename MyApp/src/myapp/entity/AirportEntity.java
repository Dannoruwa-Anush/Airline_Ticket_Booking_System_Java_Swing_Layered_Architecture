/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.entity;

/**
 *
 * @author HP
 */
public class AirportEntity {
    private int airport_id;
    private String airport_code;
    private String airport_name;
    private String airport_city;
    private String airport_country;
    
    //------- [Start : constructor, getter(), setter(), toString()]-------
    public AirportEntity() {
    }

    public AirportEntity(int airport_id, String airport_code, String airport_name, String airport_city, String airport_country) {
        setAirport_id(airport_id);
        setAirport_code(airport_code);
        setAirport_name(airport_name);
        setAirport_city(airport_city);
        setAirport_country(airport_country);
    }

    public int getAirport_id() {
        return airport_id;
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

    public void setAirport_id(int airport_id) {
        this.airport_id = airport_id;
    }

    public void setAirport_code(String airport_code) {
        this.airport_code = airport_code;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public void setAirport_city(String airport_city) {
        this.airport_city = airport_city;
    }

    public void setAirport_country(String airport_country) {
        this.airport_country = airport_country;
    }

    @Override
    public String toString() {
        return "AirportEntity{" + "airport_id=" + airport_id + ", airport_code=" + airport_code + ", airport_name=" + airport_name + ", airport_city=" + airport_city + ", airport_country=" + airport_country + '}';
    }
    
    //------- [End : constructor, getter(), setter(), toString()]-------
}
