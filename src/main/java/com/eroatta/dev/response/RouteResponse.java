package com.eroatta.dev.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines the structure for the output of a direct bus route search.
 * 
 * @author eroatta
 *
 */
public class RouteResponse {

    @JsonProperty("dep_sid")
    private final int departureStation;

    @JsonProperty("arr_sid")
    private final int arrivalStation;

    @JsonProperty("direct_bus_route")
    private final boolean directRoute;

    /**
     * Constructs a route response based on input parameters.
     * 
     * @param depSid
     *            The departure station ID.
     * @param arrSid
     *            The arrival station ID.
     * @param directRoute
     *            The direct bus route indicator.
     */
    public RouteResponse(int depSid, int arrSid, boolean directRoute) {
        this.departureStation = depSid;
        this.arrivalStation = arrSid;
        this.directRoute = directRoute;
    }

    public long getDepartureStation() {
        return this.departureStation;
    }

    public long getArrivalStation() {
        return this.arrivalStation;
    }

    public boolean getDirectRoute() {
        return this.directRoute;
    }
}
