package models;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TrainWagonRow implements QueryRow {
    private String trainNumber;
    private Train.TrainType trainType;
    private String wagonNumber;
    private int wagonSeats;
    private Wagon.WagonType wagonType;

    public String getTrainNumber(){
        return trainNumber;
    }
    public void setTrainNumber(String trainNumber){
        this.trainNumber = trainNumber;
    }

    public Train.TrainType getTrainType(){
        return trainType;
    }
    public void setTrainType(Train.TrainType trainType){
        this.trainType = trainType;
    }

    public String getWagonNumber(){
        return wagonNumber;
    }
    public void setWagonNumber(String wagonNumber){
        this.wagonNumber = wagonNumber;
    }

    public int getWagonSeats(){
        return wagonSeats;
    }
    public void setWagonSeats(int wagonSeats){
        this.wagonSeats = wagonSeats;
    }

    public Wagon.WagonType getWagonType(){
        return wagonType;
    }
    public void setWagonType(Wagon.WagonType wagonType){
        this.wagonType = wagonType;
    }

    public static void buildColumns(TableView<QueryRow> table) {
        TableColumn<QueryRow, String> c1 = new TableColumn<>("Номер поїзда");
        c1.setCellValueFactory(new PropertyValueFactory<>("trainNumber"));
        TableColumn<QueryRow, String> c2 = new TableColumn<>("Тип поїзда");
        c2.setCellValueFactory(new PropertyValueFactory<>("trainType"));
        TableColumn<QueryRow, String> c3 = new TableColumn<>("Номер вагона");
        c3.setCellValueFactory(new PropertyValueFactory<>("wagonNumber"));
        TableColumn<QueryRow, String> c4 = new TableColumn<>("Кількість місць");
        c4.setCellValueFactory(new PropertyValueFactory<>("wagonSeats"));
        TableColumn<QueryRow, String> c5 = new TableColumn<>("Тип вагона");
        c5.setCellValueFactory(new PropertyValueFactory<>("wagonType"));
        table.getColumns().addAll(c1,c2,c3,c4,c5);
    }
}

