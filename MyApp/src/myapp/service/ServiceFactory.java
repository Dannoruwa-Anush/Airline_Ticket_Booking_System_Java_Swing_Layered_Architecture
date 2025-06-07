/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service;

import myapp.service.custom.impl.AircraftModelServiceImpl;
import myapp.service.custom.impl.AirportServiceImpl;
import myapp.service.custom.impl.BookingClassServiceImpl;
import myapp.service.custom.impl.FlightClassPriceServiceImpl;
import myapp.service.custom.impl.FlightScheduleServiceImpl;
import myapp.service.custom.impl.FlightServiceImpl;
import myapp.service.custom.impl.PassengerProfileServiceImpl;
import myapp.service.custom.impl.ReservationServiceImpl;
import myapp.service.custom.impl.RouteServiceImpl;
import myapp.service.custom.impl.UserRoleServiceImpl;
import myapp.service.custom.impl.UserServiceImpl;

/**
 *
 * @author HP
 */
public class ServiceFactory {

    //This variable holds the single instance of ServiceFactory
    //private – so it can’t be accessed or modified directly from outside the class
    //static – it belongs to the class itself
    private static ServiceFactory serviceFactory;

    //constructor - private
    private ServiceFactory() {

    }

    public static ServiceFactory getServiceFactory() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }

        return serviceFactory;
    }

    /*
    *By returning the parent interface ISuperService, 
    *the method doesn't need to know about the specific implementation details of each service.
     */
    //produces or returns service instances based on the ServiceType enum 
    public ISuperService getService(ServiceType type) {
        return switch (type) {
            case USER_ROLE ->
                new UserRoleServiceImpl();
            case USER ->
                new UserServiceImpl();
            case AIRPORT ->
                new AirportServiceImpl();
            case ROUTE ->
                new RouteServiceImpl();
            case AIRCRAFT_MODEL ->
                new AircraftModelServiceImpl();
            case FLIGHT_SCHEDULE ->
                new FlightScheduleServiceImpl();
            case FLIGHT ->
                new FlightServiceImpl();  
            case BOOKING_CLASS ->
                new BookingClassServiceImpl();
            case FLIGHT_CLASS_PRICE ->
                new FlightClassPriceServiceImpl();
            case PASSENFER_PROFILE ->
                new PassengerProfileServiceImpl();
            case RESERVATION ->
                new ReservationServiceImpl();
            default ->
                throw new IllegalArgumentException("Unsupported ServiceType: " + type);
        };
    }

    public enum ServiceType {
        USER_ROLE, USER, AIRPORT, ROUTE, AIRCRAFT_MODEL, FLIGHT, BOOKING_CLASS, FLIGHT_CLASS_PRICE, PASSENFER_PROFILE, RESERVATION, FLIGHT_SCHEDULE,
    }
}
