import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
// Завантаження FXML-файлу
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
// Створення сцени з кореневим елементом
        Scene scene = new Scene(root, 200, 250);
// Налаштування заголовку та сцени для основного вікна
        primaryStage.setTitle("Криворучко Ю.В. 205-ТН");
        primaryStage.setScene(scene);
// Відображення вікна (за замовчуванням воно прямокутне)
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}