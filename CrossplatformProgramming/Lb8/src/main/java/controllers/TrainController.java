package controllers;

import db.DBUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import models.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private FilteredList<Train> filteredList;


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

        filteredList = new FilteredList<>(trainList, p -> true);
        trainTable.setItems(filteredList);

        addTrainButton.setOnAction(e -> addTrain());
        editTrainButton.setOnAction(e -> editTrain());
        deleteTrainButton.setOnAction(e -> deleteTrain());
    }

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

    private void addTrain() {
        Train temp = showTrainDialog(null);
        if (temp != null) {
            String sql = "INSERT INTO trains (train_number, train_type) VALUES (?, ?)";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, temp.getNumber());
                ps.setString(2, temp.getType().toString());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    temp.setId(keys.getInt(1));
                    String selectSQL = "UPDATE routes SET train_id=? WHERE id=?";
                    try (PreparedStatement ps2 = conn.prepareStatement(selectSQL)) {
                        ps2.setInt(1, temp.getId());
                        ps2.setInt(2, temp.getPassingStations().getFirst().getRouteId());
                        ps2.executeUpdate();
                    }
                }
                trainList.add(temp);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося додати потяг");
            }
        }
    }
    private void editTrain() {
        Train train = trainTable.getSelectionModel().getSelectedItem();
        if (train == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть потяг для редагування");
            return;
        }
        Train edited = showTrainDialog(train);
        if (edited != null) {
            String sql = "UPDATE trains SET train_number=?, train_type=? WHERE train_id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, edited.getNumber());
                ps.setString(2, edited.getType().toString());
                ps.setInt(3, edited.getId());
                String selectSQL = "UPDATE routes SET train_id=? WHERE id=?";
                try (PreparedStatement ps2 = conn.prepareStatement(selectSQL)) {
                    ps2.setInt(1, edited.getId());
                    ps2.setInt(2, edited.getPassingStations().getFirst().getRouteId());
                    ps2.executeUpdate();
                }
                ps.executeUpdate();
                loadTrains();
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося оновити потяг");
            }
        }
    }
    private void deleteTrain() {
        Train train = trainTable.getSelectionModel().getSelectedItem();
        if (train == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть потяг для видалення");
            return;
        }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                "Видалити потяг \"" + train.getNumber() + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = conf.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES) {
            String sql = "DELETE FROM trains WHERE train_id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, train.getId());
                ps.executeUpdate();
                trainList.remove(train);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося видалити потяг");
            }
        }
    }

    private Train showTrainDialog(Train train) {
        Dialog<Train> dialog = new Dialog<>();
        dialog.setTitle(train == null ? "Додати потяг" : "Редагувати потяг");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField trainNumberField = new TextField(train == null ? "" : String.valueOf(train.getNumber()));
        ChoiceBox<Route> routeChoiceBox = new ChoiceBox<>(FXCollections.observableList(getAllRoutes()));
        routeChoiceBox.setValue(train == null || train.getPassingStations() == null ? null : routeChoiceBox.getItems().stream().filter(
                i -> i.getId() == train.getPassingStations().getFirst().getRouteId()).findFirst().get());
        ChoiceBox<Train.TrainType> typeChoiceBox= new ChoiceBox<>(FXCollections.observableArrayList(Train.TrainType.values()));
        typeChoiceBox.setValue(train == null ? null : train.getType());

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.addRow(0, new Label("Номер потяга:"), trainNumberField);
        grid.addRow(1, new Label("Маршрут:"), routeChoiceBox);
        grid.addRow(3, new Label("Тип:"), typeChoiceBox);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Train newTrain = new Train(
                        train == null ? 0 : train.getId(),
                        Integer.parseInt(trainNumberField.getText()),
                        typeChoiceBox.getValue()
                );
                try{
                    addRouteToTrain(routeChoiceBox.getValue(), newTrain, DBUtil.getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return newTrain;
            }
            return null;
        });
        Optional<Train> res = dialog.showAndWait();
        return res.orElse(null);
    }

    private List<Route> getAllRoutes(){
        List<Route> list = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM routes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Route(
                        rs.getInt("id"),
                        rs.getInt("train_id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }

    public void search(String text) {
        String lower = text == null ? "" : text.trim().toLowerCase();
        filteredList.setPredicate(p -> {
            if (lower.isEmpty()) return true;
            return String.valueOf(p.getNumber()).toLowerCase().contains(lower)
                    || p.getType().toString().toLowerCase().contains(lower)
                    || String.valueOf(p.getId()).contains(lower);
        });
    }
}
