package models;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;

public class RouteAndRouteStopsRow implements QueryRow {
    private String routeName;
    private String stationName;
    private String stationLocation;
    private Time arrivalTime;
    private Time departureTime;
    private int stopOrder;

    public String getRouteName() {
        return routeName;
    }
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStationName() {
        return stationName;
    }
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationLocation() {
        return stationLocation;
    }
    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
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

    public static void buildColumns(TableView<QueryRow> table) {
        TableColumn<QueryRow, String> c1 = new TableColumn<>("Маршрут");
        c1.setCellValueFactory(new PropertyValueFactory<>("routeName"));
        TableColumn<QueryRow, Integer> c2 = new TableColumn<>("Порядковий номер станції");
        c2.setCellValueFactory(new PropertyValueFactory<>("stopOrder"));
        TableColumn<QueryRow, String> c3 = new TableColumn<>("Назва станції");
        c3.setCellValueFactory(new PropertyValueFactory<>("stationName"));
        TableColumn<QueryRow, String> c4 = new TableColumn<>("Місцезнаходження станції");
        c4.setCellValueFactory(new PropertyValueFactory<>("stationLocation"));
        TableColumn<QueryRow, Time> c5 = new TableColumn<>("Час прибуття");
        c5.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        TableColumn<QueryRow, Time> c6 = new TableColumn<>("Час відправлення");
        c6.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6);
    }
}
