package com.eroatta.dev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.eroatta.dev.models.Route;


/**
 * RoutesService stores and retrieves the configured routes.
 * 
 * @author eroatta
 *
 */
@Component
public class RoutesService {

    private List<Route> routes = new ArrayList<Route>();

    /**
     * Adds the new route to the list of routes.
     * 
     * @param route
     *            The route to be added.
     */
    public void addRoute(Route route) {
        if (route == null) {
            throw new IllegalArgumentException("Null route.");
        }

        routes.add(route);
    }

    /**
     * Looks for a direct route between the departure and the arrival stations.
     * 
     * @param departure
     *            The departure station ID.
     * @param arrival
     *            The arrival station ID.
     * @return the direct route between both stations.
     */
    public Route searchDirectRoute(int departure, int arrival) {
        if (departure == arrival) {
            throw new IllegalArgumentException(
                    "Same values for departure and arrival.");
        }

        Optional<Route> route = routes.parallelStream()
                .filter((Route r) -> r.connects(departure, arrival) == true)
                .findFirst();

        return route.isPresent() ? route.get() : null;
    }
}
