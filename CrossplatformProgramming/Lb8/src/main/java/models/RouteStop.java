package models;

import java.sql.Time;

public class RouteStop {
    private int id;
    private int routeId;
    private Station station;
    private Time arrivalTime;
    private Time departureTime;
    private int stopOrder;

    public RouteStop(int id, int routeId, Station station, Time arrivalTime, Time departureTime, int stopOrder) {
        this.id = id;
        this.routeId = routeId;
        this.station = station;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopOrder = stopOrder;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Station getStation() {
        return station;
    }
    public void setStationId(Station stationId) {
        this.station = stationId;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getStopOrder() {
        return stopOrder;
    }
    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
    }

    @Override
    public String toString() {
        String str = station.getName();
        if(arrivalTime != null){
            str += " | Прибуває: " + arrivalTime;
        }
        if(departureTime != null){
            str += " | Відправляється: " + departureTime;
        }

        return str;
    }
}

