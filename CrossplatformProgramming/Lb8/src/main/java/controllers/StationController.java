package controllers;

import db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import models.Station;

import java.sql.*;
import java.util.Optional;

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

    private FilteredList<Station> filteredList;

    @FXML
    public void initialize() {
        stationIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        stationNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stationLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        loadStations();

        filteredList = new FilteredList<>(stationList, p -> true);
        stationTable.setItems(filteredList);

        addStationButton.setOnAction(e -> addStation());
        editStationButton.setOnAction(e -> editStation());
        deleteStationButton.setOnAction(e -> deleteStation());
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStation() {
        Station temp = showStationDialog(null);
        if (temp != null) {
            String sql = "INSERT INTO stations (station_name, station_location) VALUES (?, ?)";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, temp.getName());
                ps.setString(2, temp.getLocation());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    temp.setId(keys.getInt(1));
                }
                stationList.add(temp);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося додати станцію");
            }
        }
    }
    private void editStation() {
        Station station = stationTable.getSelectionModel().getSelectedItem();
        if (station == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть станцію для редагування");
            return;
        }
        Station edited = showStationDialog(station);
        if (edited != null) {
            String sql = "UPDATE stations SET station_name=?, station_location=? WHERE station_id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, edited.getName());
                ps.setString(2, edited.getLocation());
                ps.setInt(3, edited.getId());
                ps.executeUpdate();
                loadStations();
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося оновити станцію");
            }
        }
    }
    private void deleteStation() {
        Station station = stationTable.getSelectionModel().getSelectedItem();
        if (station == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть станцію для видалення");
            return;
        }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                "Видалити станцію \"" + station.getId() + ":" + station.getName()  + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = conf.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES) {
            String sql = "DELETE FROM stations WHERE station_id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, station.getId());
                ps.executeUpdate();
                stationList.remove(station);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося видалити станцію");
            }
        }
    }

    private Station showStationDialog(Station station) {
        Dialog<Station> dialog = new Dialog<>();
        dialog.setTitle(station == null ? "Додати станцію" : "Редагувати станцію");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField stationNameField = new TextField(station == null ? "" : station.getName());
        TextField stationLocationFiled = new TextField(station == null ? "" : station.getLocation());

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.addRow(0, new Label("Назва станції:"), stationNameField);
        grid.addRow(1, new Label("Місце розташування:"), stationLocationFiled);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                return new Station(
                        station == null ? 0 : station.getId(),
                        stationNameField.getText(),
                        stationLocationFiled.getText()
                );
            }
            return null;
        });
        Optional<Station> res = dialog.showAndWait();
        return res.orElse(null);
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
            return p.getName().toLowerCase().contains(lower)
<<<<<<< HEAD
                    || p.getLocation().toLowerCase().contains(lower)
=======
                    || p.getLocation().contains(lower)
>>>>>>> e920fdb28acb29e05902aa96a4be1882e5164f88
                    || String.valueOf(p.getId()).contains(lower);
        });
    }
}
