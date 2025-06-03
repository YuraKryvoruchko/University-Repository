package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.QueryService;

public class MainController {
    @FXML
    private BorderPane root1;
    @FXML
    private TabPane tabPane;
    @FXML
    private HBox bottomBox;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    @FXML
    private MenuItem query1MenuItem;
    @FXML
    private MenuItem query2MenuItem;

    @FXML
    private TrainController trainController;
    @FXML
    private TicketController ticketController;
    @FXML
    private WagonController wagonController;
    @FXML
    private StationController stationController;
    @FXML
    private UserController userController;

    private QueryService queryService = new QueryService();


    @FXML
    public void initialize() {
        addHoverEffect(searchButton);

        playScreenFadeTransition();
        playTabPaneScaleTransition();
        playBottomScreenTranslateTransition();

        searchButton.setOnAction(e -> onSearch());
    }

    @FXML
    private void onQuery1() {
        showQueryWindow(1);
    }
    @FXML
    private void onQuery2() {
        showQueryWindow(2);
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

    private void showQueryWindow(int queryNumber) {
        try {
            FXMLLoader loader = new
                    FXMLLoader(getClass().getResource("/query_result.fxml"));
            Pane pane = loader.load();
            QueryResultController ctrl = loader.getController();
            ctrl.setData(queryService.executeQuery(queryNumber), queryService.getBuildColumnsConsumer(queryNumber));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Результат запиту " + queryNumber);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void onSearch(){
        String q = searchField.getText();
        String tab = tabPane.getSelectionModel().getSelectedItem().getText();
        if (tab.equals("Потяги")) trainController.search(q);
        else if (tab.equals("Квитки")) ticketController.search(q);
        else if (tab.equals("Вагони")) wagonController.search(q);
        else if (tab.equals("Станції")) stationController.search(q);
        else if (tab.equals("Користувачі")) userController.search(q);
    }
}
