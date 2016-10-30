package com.eroatta.dev.models;

import java.util.Arrays;

/**
 * Represents a bus route, including the ID and the list of stations.
 * 
 * @author eroatta
 *
 */
public class Route {

    private final int id;
    private final int[] stations;

    /**
     * Constructs a route, setting the bus route ID and the included stations.
     * 
     * @param id
     *            The bus route ID.
     * @param stations
     *            The list of stations that are part of the route.
     */
    public Route(int id, int[] stations) {
        this.id = id;
        this.stations = stations;

        Arrays.sort(this.stations);
    }

    public long getId() {
        return id;
    }

    public int[] getStations() {
        return stations;
    }

    /**
     * Checks if the route connects the departure and the arrival stations.
     * 
     * @param departure
     *            The departure station ID.
     * @param arrival
     *            The arrival station ID.
     * @return true if both stations are part of the route; false otherwise.
     */
    public boolean connects(int departure, int arrival) {
        boolean connects = false;

        int length = this.stations.length;
        int idx = Arrays.binarySearch(this.stations, departure);
        if (idx > 0 && idx < length) {
            idx = Arrays.binarySearch(this.stations, arrival);
            if (idx > 0 && idx < length) {
                connects = true;
            }
        }

        return connects;
    }
}
