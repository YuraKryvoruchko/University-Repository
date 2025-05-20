package controllers;

import db.DBUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        registerButton.setOnAction(this::handleRegister);
        cancelButton.setOnAction(this::handleCancel);
    }

    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Помилка", "Логін, email або пароль не можуть бути порожніми.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Помилка", "Паролі не співпадають.");
            return;
        }
        if (userExists(username)) {
            showAlert(Alert.AlertType.ERROR, "Помилка", "Користувач з таким логіном вже існує.");
            return;
        }
        if (emailExists(email)) {
            showAlert(Alert.AlertType.ERROR, "Помилка", "Користувач з таким email вже існує.");
            return;
        }
        if (registerUser(username, password, email)) {
            showAlert(Alert.AlertType.INFORMATION, "Успіх", "Реєстрація пройшла успішно!");
// Закриваємо вікно реєстрації
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();
        } else {
            showAlert(Alert.AlertType.ERROR, "Помилка", "Сталася помилка під час реєстрації.");
        }
    }

    private boolean userExists(String username) {
        try (Connection conn = DBUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Неможливо перевірити наявність користувача");
        }
        return false;
    }
    private boolean emailExists(String email) {
        try (Connection conn = DBUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Неможливо перевірити наявність користувача");
        }
        return false;
    }

    private boolean registerUser(String username, String password, String email) {
        try (Connection conn = DBUtil.getConnection()) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password); // У реальному додатку пароль слід зберігати як хеш
            stmt.setString(3, email);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Неможливо зареєструвати користувача");
        }
        return false;
    }

    private void handleCancel(ActionEvent event) {
        // Закриваємо вікно реєстрації
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}