package controllers;

import models.Station;
import models.User;
import db.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @FXML
    public void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        loadUsers();

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
                User flight = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        User.UserCategory.valueOf(rs.getString("category"))
                );
                userList.add(flight);
            }
            userTable.setItems(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUser() {
        System.out.println("User adding");
    }
    private void editUser() {
        System.out.println("User editing");
    }
    private void deleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userList.remove(selectedUser);
            System.out.println("User has deleted");
        } else {
            System.out.println("Choose an user");
        }
    }
}
