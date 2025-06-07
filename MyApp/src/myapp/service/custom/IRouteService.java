/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.service.custom;

import java.util.List;
import myapp.dto.route.RouteRequestDTO;
import myapp.dto.route.RouteResponseDTO;
import myapp.service.ISuperService;

/**
 *
 * @author HP
 */
public interface IRouteService extends ISuperService{
    
    String addRoute(RouteRequestDTO routeRequestDTO) throws Exception;
    String updateRoute(Integer id, RouteRequestDTO routeRequestDTO) throws Exception;
    String deleteRoute(Integer id) throws Exception;
    RouteResponseDTO getRouteById(Integer id) throws Exception;
    List<RouteResponseDTO> getAllRoutes() throws Exception;
    
    List<RouteResponseDTO> getAllRoutesBySearchKey(String searchKey)throws Exception;
}
