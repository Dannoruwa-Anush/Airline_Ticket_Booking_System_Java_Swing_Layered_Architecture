/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.controller;

import java.util.List;
import myapp.dto.route.RouteRequestDTO;
import myapp.dto.route.RouteResponseDTO;
import myapp.service.ISuperService;
import myapp.service.ServiceFactory;
import myapp.service.custom.IRouteService;

/**
 *
 * @author HP
 */
public class RouteController {
    //------- [Start : downcast] ------------------------------
    
    //reference of type ISuperService (parent class)
    private IRouteService routeService;

    //constructor
    public RouteController() {
        ISuperService iSuperService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ROUTE);
        
        if (iSuperService instanceof IRouteService iRouteService) { 
            routeService = iRouteService; //downcast (parent class -> child class)
        } else {
            throw new IllegalStateException("Returned service is not an instance of IRouteService");
        }
    }
    //------- [End : downcast] ------------------------------

    public String addRoute(RouteRequestDTO routeRequestDTO) throws Exception {
        return routeService.addRoute(routeRequestDTO);
    }

    public String updateRoute(Integer id, RouteRequestDTO routeRequestDTO) throws Exception {
        return routeService.updateRoute(id, routeRequestDTO);
    }

    public String deleteRoute(Integer id) throws Exception {
        return routeService.deleteRoute(id);
    }

    public RouteResponseDTO getRouteById(Integer id) throws Exception {
        return routeService.getRouteById(id);
    }

    public List<RouteResponseDTO> getAllRoutes() throws Exception {
        return routeService.getAllRoutes();
    }

    public List<RouteResponseDTO> getAllRoutesBySearchKey(String searchKey) throws Exception {
        return routeService.getAllRoutesBySearchKey(searchKey);
    }
}
