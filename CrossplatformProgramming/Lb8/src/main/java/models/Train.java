package models;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Train {
    private int id;
    private int number;
    private Station finalStation;
    private List<RouteStop> passingStations;
    private Time departureTime;
    private LocalTime travelTime;
    private Time arrivalTime;
    private LocalTime stopDuration;
    private TrainType type;

    public enum TrainType {
        PASSENGER,
        CORPORATE,
        FAST
    }

    public Train(int id, int number, TrainType type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public Station getFinalStation() {
        return finalStation;
    }
    public void setFinalStation(Station finalStation) {
        this.finalStation = finalStation;
    }

    public List<RouteStop> getPassingStations() {
        return passingStations;
    }
    public void setPassingStations(List<RouteStop> passingStations) {
        this.passingStations = passingStations;
    }

    public Time getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }
    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getStopDuration() {
        return stopDuration;
    }
    public void setStopDuration(LocalTime stopDuration) {
        this.stopDuration = stopDuration;
    }

    public TrainType getType() {
        return type;
    }
    public void setType(TrainType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return number + " : " + id;
    }
}
