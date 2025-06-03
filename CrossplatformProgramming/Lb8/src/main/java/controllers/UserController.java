package controllers;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import models.User;
import db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.Optional;

public class UserController {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> userIdColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Integer> roleColumn;
    @FXML
    private Button addUserButton;
    @FXML
    private Button editUserButton;
    @FXML
    private Button deleteUserButton;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    private FilteredList<User> filteredList;

    @FXML
    public void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        loadUsers();

        filteredList = new FilteredList<>(userList, p -> true);
        userTable.setItems(filteredList);
        
        addUserButton.setOnAction(e -> addUser());
        editUserButton.setOnAction(e -> editUser());
        deleteUserButton.setOnAction(e -> deleteUser());
    }
    // Метод завантаження даних
    public void loadUsers() {
        userList.clear();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        User.UserCategory.valueOf(rs.getString("category"))
                );
                userList.add(user);
            }
            //userTable.setItems(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUser() {
        User temp = showUserDialog(null);
        if (temp != null) {
            String sql = "INSERT INTO users (username, password, email, category) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, temp.getUsername());
                ps.setString(2, temp.getPassword());
                ps.setString(3, temp.getEmail());
                ps.setString(4, temp.getCategory().toString());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    temp.setUserId(keys.getInt(1));
                    String selectSQL = "SELECT created_at FROM users WHERE user_id = ?";
                    try (PreparedStatement ps2 = conn.prepareStatement(selectSQL)) {
                        ps2.setInt(1, temp.getUserId());
                        try (ResultSet rs = ps2.executeQuery()) {
                            if (rs.next()) {
                                temp.setCreatedAtDate(rs.getDate("created_at"));
                            }
                        }
                    }
                }
                userList.add(temp);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося додати користувача");
            }
        }
    }
    private void editUser() {
        User user = userTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть користувача для редагування");
            return;
        }
        User edited = showUserDialog(user);
        if (edited != null) {
            String sql = "UPDATE users SET username=?, password=?, email=?, category=? WHERE user_id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, edited.getUsername());
                ps.setString(2, edited.getPassword());
                ps.setString(3, edited.getEmail());
                ps.setString(4, edited.getCategory().toString());
                ps.setInt(5, edited.getUserId());
                ps.executeUpdate();
                loadUsers();
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося оновити користувача");
            }
        }
    }
    private void deleteUser() {
        User user = userTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть користувача для видалення");
            return;
        }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                "Видалити користувача \"" + user.getUserId() + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = conf.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES) {
            String sql = "DELETE FROM users WHERE user_id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getUserId());
                ps.executeUpdate();
                userList.remove(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося видалити користувача");
            }
        }
    }

    private User showUserDialog(User user) {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle(user == null ? "Додати користувача" : "Редагувати користувача");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField usernameField = new TextField(user == null ? "" : user.getUsername());
        TextField passwordField = new TextField(user == null ? "" : user.getPassword());
        TextField emailField = new TextField(user == null ? "" : user.getEmail());
        ChoiceBox<User.UserCategory> categoryChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(User.UserCategory.values()));
        categoryChoiceBox.setValue(user == null ? User.UserCategory.PASSENGER : user.getCategory());

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.addRow(0, new Label("Username:"), usernameField);
        grid.addRow(1, new Label("Пароль:"), passwordField);
        grid.addRow(2, new Label("Email:"), emailField);
        grid.addRow(3, new Label("Категорія:"), categoryChoiceBox);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                return new User(
                        user == null ? 0 : user.getUserId(),
                        usernameField.getText(),
                        passwordField.getText(),
                        emailField.getText(),
                        new Date(0),
                        categoryChoiceBox.getValue()
                );
            }
            return null;
        });
        Optional<User> res = dialog.showAndWait();
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
            return p.getUsername().toLowerCase().contains(lower)
                    || p.getEmail().toLowerCase().contains(lower)
                    || p.getCategory().toString().toLowerCase().contains(lower)
                    || String.valueOf(p.getUserId()).toLowerCase().contains(lower);
        });
    }
}
