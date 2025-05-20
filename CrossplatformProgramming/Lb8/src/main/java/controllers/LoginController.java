package controllers;

import db.DBUtil;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private AnchorPane root1;
    @FXML
    private VBox vBox;

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    public void initialize() {
        playAnimations();

        loginButton.setOnAction(this::handleLogin);
        registerButton.setOnAction(this::handleRegister);
    }

    private void playAnimations(){
        FadeTransition fade = new FadeTransition(Duration.seconds(2), root1);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);

        RotateTransition rotate = new RotateTransition(Duration.seconds(2), vBox);
        rotate.setByAngle(360);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(2), vBox);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);

        ParallelTransition parallelAnimation = new ParallelTransition(fade, rotate, scale);
        parallelAnimation.play();
    }

    private void handleLogin(ActionEvent event) {
        String username = loginField.getText();
        String password = passwordField.getText();
        if (authenticateUser(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Успіх", "Авторизація успішна!");
            // Завантажуємо головне вікно (main.fxml)
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
                Scene scene = new Scene(root, 800, 600);
                scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Криворучко Ю.В. 205-ТН");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Неможливо завантажити головне вікно");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Помилка авторизації", "Невірний логін або пароль");
        }
    }

    private boolean authenticateUser(String username, String password) {
        try (Connection conn = DBUtil.getConnection()) {
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                // Порівнюємо в чистому вигляді; у реальному додатку використовуйте хешування
                return dbPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Неможливо встановити з'єднання з базою даних");
        }
        return false;
    }

    private void handleRegister(ActionEvent event) {
        // Завантажуємо вікно реєстрації (registration.fxml)
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/registration.fxml"));
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setTitle("Реєстрація");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Неможливо завантажити вікно реєстрації");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
