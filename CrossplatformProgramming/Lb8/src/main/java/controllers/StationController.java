package controllers;

import db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Station;
import models.Wagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationController {
    @FXML
    private TableView<Station> stationTable;
    @FXML
    private TableColumn<Station, String> stationIdColumn;
    @FXML
    private TableColumn<Station, String> stationNameColumn;
    @FXML
    private TableColumn<Station, String> stationLocationColumn;
    @FXML
    private Button addStationButton;
    @FXML
    private Button editStationButton;
    @FXML
    private Button deleteStationButton;

    private ObservableList<Station> stationList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        stationIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        stationNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stationLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        loadStations();

        addStationButton.setOnAction(e -> addUser());
        editStationButton.setOnAction(e -> editUser());
        deleteStationButton.setOnAction(e -> deleteUser());
    }
    // Метод завантаження даних
    public void loadStations() {
        stationList.clear();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM stations";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Station station = new Station(
                        rs.getInt("station_id"),
                        rs.getString("station_name"),
                        rs.getString("station_location")
                );
                stationList.add(station);
            }
            stationTable.setItems(stationList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUser() {
        System.out.println("Station adding");
    }
    private void editUser() {
        System.out.println("Station editing");
    }
    private void deleteUser() {
        Station selectedUser = stationTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            stationList.remove(selectedUser);
            System.out.println("Station has deleted");
        } else {
            System.out.println("Choose a station");
        }
    }
}
