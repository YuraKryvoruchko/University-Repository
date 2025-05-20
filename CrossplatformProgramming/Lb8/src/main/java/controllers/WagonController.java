package controllers;

import db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WagonController {
    @FXML
    private TableView<Wagon> wagonTable;
    @FXML
    private TableColumn<Wagon, Integer> wagonNumberColumnWagon;
    @FXML
    private TableColumn<Wagon, Wagon.WagonType> wagonTypeColumnWagon;
    @FXML
    private TableColumn<Wagon, Integer> seatCountColumn;
    @FXML
    private Button addWagonButton;
    @FXML
    private Button editWagonButton;
    @FXML
    private Button deleteWagonButton;

    private ObservableList<Wagon> wagonList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        wagonNumberColumnWagon.setCellValueFactory(new PropertyValueFactory<>("id"));
        wagonTypeColumnWagon.setCellValueFactory(new PropertyValueFactory<>("type"));
        seatCountColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));

        loadWagons();

        addWagonButton.setOnAction(e -> addWagon());
        editWagonButton.setOnAction(e -> editWagon());
        deleteWagonButton.setOnAction(e -> deleteWagon());
    }
    // Метод завантаження даних
    public void loadWagons() {
        wagonList.clear();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM wagons";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Wagon wagon = new Wagon(
                        rs.getInt("id"),
                        rs.getInt("train_id"),
                        Wagon.WagonType.valueOf(rs.getString("type")),
                        rs.getInt("seats")
                );
                wagonList.add(wagon);
            }
            wagonTable.setItems(wagonList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addWagon() {
        System.out.println("Wagon adding");
    }
    private void editWagon() {
        System.out.println("Wagon editing");
    }
    private void deleteWagon() {
        Wagon selectedWagon = wagonTable.getSelectionModel().getSelectedItem();
        if (selectedWagon != null) {
            wagonList.remove(selectedWagon);
            System.out.println("Wagon has deleted");
        } else {
            System.out.println("Choose a wagon");
        }
    }
}

