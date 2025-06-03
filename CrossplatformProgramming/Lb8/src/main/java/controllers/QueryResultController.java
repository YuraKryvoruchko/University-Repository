package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import models.QueryRow;
import util.ExportUtil;
import java.io.File;
import java.util.function.Consumer;

public class QueryResultController {
    @FXML
    private TableView<QueryRow> resultTable;

    private ObservableList<QueryRow> data;

    public void setData(ObservableList<QueryRow> data, Consumer<TableView<QueryRow>> buildColumnsConsumer) {
        this.data = data;
        resultTable.getColumns().clear();
        buildColumnsConsumer.accept(resultTable);
        resultTable.setItems(data);
    }

    @FXML
    private void onClose() {
        ((Stage) resultTable.getScene().getWindow()).close();
    }

    @FXML
    private void onSave() {
        FileChooser chooser = new FileChooser();
        chooser.setInitialFileName("query_result");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("Access (*.accdb)", "*.accdb")
        );
        File file = chooser.showSaveDialog(resultTable.getScene().getWindow());
        if (file == null) return;
        try {
            if (file.getName().endsWith(".xlsx")) {
                ExportUtil.exportToExcel(resultTable, file);
            } else if (file.getName().endsWith(".accdb")) {
                ExportUtil.exportToAccess(resultTable, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}