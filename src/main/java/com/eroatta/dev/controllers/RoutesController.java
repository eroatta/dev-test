package com.eroatta.dev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroatta.dev.exceptions.BadRequestException;
import com.eroatta.dev.response.RouteResponse;
import com.eroatta.dev.services.RoutesService;

@RestController
public class RoutesController {

    @Autowired
    private RoutesService routesService;

    @RequestMapping(value = "/api/direct", method = RequestMethod.GET)
    public RouteResponse searchDirectRoute(
            @RequestParam("dep_sid") Integer departureStation,
            @RequestParam("arr_sid") Integer arrivalStation)
            throws BadRequestException {
        if (departureStation == arrivalStation) {
            throw new BadRequestException(
                    "Departure and Arrival IDs should be different.");
        }

        boolean directRoute = false;
        if (routesService.searchDirectRoute(departureStation, arrivalStation) != null) {
            directRoute = true;
        }

        return new RouteResponse(departureStation, arrivalStation, directRoute);
    }

}
