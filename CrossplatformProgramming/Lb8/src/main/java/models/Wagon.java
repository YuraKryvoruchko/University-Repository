package models;

public class Wagon {
    private int id;
    private int trainId;
    private WagonType type;
    private int seats;

    public Wagon(int id, int trainId, WagonType type, int seats) {
        this.id = id;
        this.trainId = trainId;
        this.type = type;
        this.seats = seats;
    }

    public enum WagonType {
        RESERVED,
        COMPARTMENT
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }
    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public WagonType getType() {
        return type;
    }
    public void setType(WagonType type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
}

