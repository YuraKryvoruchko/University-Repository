package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import javax.swing.*;

public class MainController {
    @FXML
    private BorderPane root1;
    @FXML
    private TabPane tabPane;
    @FXML
    private HBox bottomBox;
    @FXML
    private Button searchButton;

    @FXML
    public void initialize() {
        addHoverEffect(searchButton);

        playScreenFadeTransition();
        playTabPaneScaleTransition();
        playBottomScreenTranslateTransition();
    }

    private void playScreenFadeTransition(){
        FadeTransition fade = new FadeTransition(Duration.seconds(2), root1);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }
    private void playTabPaneScaleTransition(){
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(2), tabPane);
        scaleIn.setFromX(0);
        scaleIn.setFromY(0);
        scaleIn.setToX(1);
        scaleIn.setToY(1);
        scaleIn.play();
    }
    private void playBottomScreenTranslateTransition(){
        TranslateTransition move = new TranslateTransition(Duration.seconds(2), bottomBox);
        move.setToX(bottomBox.getTranslateX());
        move.setFromX(-500);
        move.play();
    }

    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        button.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }
}
