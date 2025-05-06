package controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private AnchorPane root1;
    @FXML
    private VBox vBox;

    @FXML
    public void initialize() {
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
}
