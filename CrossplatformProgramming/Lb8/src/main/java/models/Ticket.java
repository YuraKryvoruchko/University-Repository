package models;

import java.sql.Date;

public class Ticket {
    private int id;
    private Train train;
    private Wagon wagon;
    private int seat;
    private Passenger passenger;
    private Date dispatchDate;
    private Route route;
    private RouteStop startRouteStop;
    private RouteStop lastRouteStop;
    private RouteStop fromRouteStop;
    private RouteStop toRouteStop;
    private float price;

    public Ticket(int id, Train train, Wagon wagon, int seat, Passenger passenger,
                  Date dispatchDate, Route route, RouteStop startRouteStop, RouteStop lastRouteStop,
                  RouteStop fromRouteStop, RouteStop toRouteStop, float price) {
        this.id = id;
        this.train = train;
        this.wagon = wagon;
        this.seat = seat;
        this.passenger = passenger;
        this.dispatchDate = dispatchDate;
        this.route = route;
        this.startRouteStop = startRouteStop;
        this.lastRouteStop = lastRouteStop;
        this.fromRouteStop = fromRouteStop;
        this.toRouteStop = toRouteStop;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    public Wagon getWagon() {
        return wagon;
    }
    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }
    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public RouteStop getStartRouteStop() {
        return startRouteStop;
    }
    public void setStartRouteStop(RouteStop startRouteStop) {
        this.startRouteStop = startRouteStop;
    }

    public RouteStop getLastRouteStop() {
        return lastRouteStop;
    }
    public void setLastRouteStop(RouteStop lastRouteStop) {
        this.lastRouteStop = lastRouteStop;
    }

    public RouteStop getFromRouteStop() {
        return fromRouteStop;
    }
    public void setFromRouteStop(RouteStop fromRouteStop) {
        this.fromRouteStop = fromRouteStop;
    }

    public RouteStop getToRouteStop() {
        return toRouteStop;
    }
    public void setToRouteStop(RouteStop toRouteStop) {
        this.toRouteStop = toRouteStop;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
