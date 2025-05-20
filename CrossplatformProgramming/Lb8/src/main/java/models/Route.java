package models;

public class Route {
    private int id;
    private int trainId;
    private String name;

    public Route(int id, int trainId, String name) {
        this.id = id;
        this.trainId = trainId;
        this.name = name;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
