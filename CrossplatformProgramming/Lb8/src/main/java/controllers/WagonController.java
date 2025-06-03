package controllers;

import db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WagonController {
    @FXML
    private TableView<Wagon> wagonTable;
    @FXML
    private TableColumn<Wagon, String> wagonNumberColumnWagon;
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

    private FilteredList<Wagon> filteredList;

    @FXML
    public void initialize() {
        wagonNumberColumnWagon.setCellValueFactory(new PropertyValueFactory<>("number"));
        wagonTypeColumnWagon.setCellValueFactory(new PropertyValueFactory<>("type"));
        seatCountColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));

        loadWagons();

        filteredList = new FilteredList<>(wagonList, p -> true);
        wagonTable.setItems(filteredList);

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
                        rs.getString("wagon_number"),
                        rs.getInt("train_id"),
                        Wagon.WagonType.valueOf(rs.getString("type")),
                        rs.getInt("seats")
                );
                wagonList.add(wagon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addWagon() {
        Wagon temp = showWagonDialog(null);
        if (temp != null) {
            String sql = "INSERT INTO wagons (wagon_number, train_id, type, seats) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, temp.getNumber());
                ps.setInt(2, temp.getTrainId());
                ps.setString(3, temp.getType().toString());
                ps.setInt(4, temp.getSeats());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    temp.setId(keys.getInt(1));
                }
                wagonList.add(temp);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося додати вагон");
            }
        }
    }
    private void editWagon() {
        Wagon wagon = wagonTable.getSelectionModel().getSelectedItem();
        if (wagon == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть вагон для редагування");
            return;
        }
        Wagon edited = showWagonDialog(wagon);
        if (edited != null) {
            String sql = "UPDATE wagons SET wagon_number=?, train_id=?, type=?, seats=? WHERE id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, edited.getNumber());
                ps.setInt(2, edited.getTrainId());
                ps.setString(3, edited.getType().toString());
                ps.setInt(4, edited.getSeats());
                ps.setInt(5, edited.getId());
                ps.executeUpdate();
                loadWagons();
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося оновити вагон");
            }
        }
    }
    private void deleteWagon() {
        Wagon wagon = wagonTable.getSelectionModel().getSelectedItem();
        if (wagon == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть вагон для видалення");
            return;
        }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                "Видалити вагон \"" + wagon.getId() + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = conf.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES) {
            String sql = "DELETE FROM wagons WHERE id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, wagon.getId());
                ps.executeUpdate();
                wagonList.remove(wagon);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося видалити вагон");
            }
        }
    }

    private Wagon showWagonDialog(Wagon wagon) {
        Dialog<Wagon> dialog = new Dialog<>();
        dialog.setTitle(wagon == null ? "Додати вагон" : "Редагувати вагон");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField wagonNumberField = new TextField(wagon == null ? "" : wagon.getNumber());

        ComboBox<Pair<Integer, String>> trainIdComboBox = new ComboBox<>(FXCollections.observableList(getTrainIdNumberPairs()));
        trainIdComboBox.setCellFactory(cell -> new ListCell<>(){
            @Override
            protected void updateItem(Pair<Integer, String> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : "ID: " + item.getKey() + "; Number: " + item.getValue());
            }
        });
        trainIdComboBox.setButtonCell(new ListCell<Pair<Integer, String>>() {
            @Override
            protected void updateItem(Pair<Integer, String> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : "ID: " + item.getKey() + "; Number: " + item.getValue());
            }
        });
        trainIdComboBox.setValue(wagon == null ? null : trainIdComboBox.getItems().stream().filter(
                p -> p.getKey().equals(wagon.getTrainId())).findFirst().get());

        ChoiceBox<Wagon.WagonType> wagonTypeChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(Wagon.WagonType.values()));
        wagonTypeChoiceBox.setValue(wagon == null ? Wagon.WagonType.COMPARTMENT : wagon.getType());

        TextField seatsField = new TextField(wagon == null ? "" : String.valueOf(wagon.getSeats()));

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.addRow(0, new Label("Номер:"), wagonNumberField);
        grid.addRow(1, new Label("ID поїзда:"), trainIdComboBox);
        grid.addRow(2, new Label("Тип:"), wagonTypeChoiceBox);
        grid.addRow(3, new Label("Кількість місць:"), seatsField);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                return new Wagon(
                        wagon == null ? 0 : wagon.getId(),
                        wagonNumberField.getText(),
                        trainIdComboBox.getValue().getKey(),
                        wagonTypeChoiceBox.getValue(),
                        Integer.parseInt(seatsField.getText())
                );
            }
            return null;
        });
        Optional<Wagon> res = dialog.showAndWait();
        return res.orElse(null);
    }

    private List<Pair<Integer, String>> getTrainIdNumberPairs() {
        List<Pair<Integer, String>> pairList = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT train_id, train_number FROM trains";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int trainId = rs.getInt("train_id");
                int trainNumber = rs.getInt("train_number");
                pairList.add(new Pair<>(trainId, String.valueOf(trainNumber)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pairList;
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
            return p.getNumber().toLowerCase().contains(lower)
                    || p.getType().toString().toLowerCase().contains(lower)
                    || String.valueOf(p.getId()).contains(lower);
        });
    }
}

