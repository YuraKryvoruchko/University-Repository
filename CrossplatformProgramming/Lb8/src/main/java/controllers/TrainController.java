package controllers;

import db.DBUtil;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainController {
    @FXML
    private TableView<Train> trainTable;
    @FXML
    private TableColumn<Train, Integer> trainNumberColumn;
    @FXML
    private TableColumn<Train, Station> finalStationColumn;
    @FXML
    private TableColumn<Train, String> passingStationsColumn;
    @FXML
    private TableColumn<Train, Time> departureTimeColumn;
    @FXML
    private TableColumn<Train, LocalTime> travelTimeColumn;
    @FXML
    private TableColumn<Train, Time> arrivalTimeColumn;
    @FXML
    private TableColumn<Train, LocalTime> stopDurationColumn;
    @FXML
    private TableColumn<Train, Train.TrainType> trainTypeColumn;

    @FXML
    private Button addTrainButton;
    @FXML
    private Button editTrainButton;
    @FXML
    private Button deleteTrainButton;

    private ObservableList<Train> trainList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        trainNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        finalStationColumn.setCellValueFactory(new PropertyValueFactory<>("finalStation"));
        passingStationsColumn.setCellValueFactory(cellData -> {
            List<RouteStop> routeStopList = cellData.getValue().getPassingStations();
            if(routeStopList == null){
                return new SimpleStringProperty();
            }

            String formatted = routeStopList.stream().map(RouteStop::toString).collect(Collectors.joining("\n"));
            return new SimpleStringProperty(formatted);
        });
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        travelTimeColumn.setCellValueFactory(new PropertyValueFactory<>("travelTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        stopDurationColumn.setCellValueFactory(new PropertyValueFactory<>("stopDuration"));
        trainTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        loadTrains();

        addTrainButton.setOnAction(e -> addWagon());
        editTrainButton.setOnAction(e -> editWagon());
        deleteTrainButton.setOnAction(e -> deleteWagon());
    }
    // Метод завантаження даних
    public void loadTrains() {
        trainList.clear();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM trains";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int trainId = rs.getInt("train_id");
                int trainNumber = rs.getInt("train_number");
                Train.TrainType trainType = Train.TrainType.valueOf(rs.getString("train_type"));

                ResultSet routeResultSet = getRouteResultSet(trainId, conn);
                if (routeResultSet.next()) {
                    do {
                        Train train = new Train(trainId, trainNumber, trainType);
                        Route route = new Route(routeResultSet.getInt("id"), trainId, routeResultSet.getString("name"));
                        addRouteToTrain(route, train, conn);
                        trainList.add(train);
                    } while (routeResultSet.next());
                }
                else{
                    trainList.add(new Train(trainId, trainNumber, trainType));
                }
            }
            trainTable.setItems(trainList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getRouteResultSet(int trainId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM routes WHERE train_id = " + trainId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }
    private void addRouteToTrain(Route route, Train train, Connection conn) throws SQLException{
        List<RouteStop> routeStopList = getRouteStopsByRouteId(route.getId(), conn);

        train.setDepartureTime(routeStopList.getFirst().getDepartureTime());
        train.setArrivalTime(routeStopList.getLast().getArrivalTime());

        Duration duration = Duration.between(routeStopList.getFirst().getDepartureTime().toLocalTime(),
                routeStopList.getLast().getArrivalTime().toLocalTime());
        train.setTravelTime(LocalTime.of(duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart()));

        if (routeStopList.size() > 2){
            Duration stopDuration = Duration.between(routeStopList.get(1).getArrivalTime().toLocalTime(),
                    routeStopList.get(1).getDepartureTime().toLocalTime());
            train.setStopDuration(LocalTime.of(stopDuration.toHoursPart(), stopDuration.toMinutesPart(), stopDuration.toSecondsPart()));
        }

        train.setFinalStation(routeStopList.getLast().getStation());
        train.setPassingStations(routeStopList);
    }
    private List<RouteStop> getRouteStopsByRouteId(int routeId, Connection conn) throws SQLException {
        List<RouteStop> routeStopList = new ArrayList<>();
        String sql = "SELECT * FROM route_stops WHERE route_id = " + routeId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            RouteStop routeStop = new RouteStop(
                    rs.getInt("id"),
                    rs.getInt("route_id"),
                    getRouteStation(rs.getInt("station_id"), conn),
                    rs.getTime("arrival_time"),
                    rs.getTime("departure_time"),
                    rs.getInt("stop_order")
            );
            routeStopList.add(routeStop);
        }
        return routeStopList;
    }
    private Station getRouteStation(int stationId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM stations WHERE station_id = " + stationId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Station(
                rs.getInt("station_id"),
                rs.getString("station_name"),
                rs.getString("station_location")
        );
    }

    private void addWagon() {
        System.out.println("Train adding");
    }
    private void editWagon() {
        System.out.println("Train editing");
    }
    private void deleteWagon() {
        Train selectedTrain = trainTable.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            trainList.remove(selectedTrain);
            System.out.println("Train has deleted");
        } else {
            System.out.println("Choose a train");
        }
    }
}
