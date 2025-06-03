package util;
import com.healthmarketscience.jackcess.*;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.QueryRow;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class ExportUtil {
    public static void exportToExcel(TableView<QueryRow> tableView, File file) throws
            Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sh = wb.createSheet("QueryResult");

        int rowIndex = 0;

        Row headerRow = sh.createRow(rowIndex++);
        int colIndex = 0;
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(column.getText());
        }

        for (QueryRow item : tableView.getItems()) {
            Row row = sh.createRow(rowIndex++);
            colIndex = 0;
            for (TableColumn<QueryRow, ?> column : tableView.getColumns()) {
                Object cellData = column.getCellObservableValue(item).getValue();
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(colIndex++);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                }
            }
        }

        try (FileOutputStream out = new FileOutputStream(file)) {
            wb.write(out);
        }
    }
    public static void exportToAccess(TableView<QueryRow> tableView, File file)
            throws Exception {
        Database db = DatabaseBuilder.create(Database.FileFormat.V2016, file);
        TableBuilder tb = new TableBuilder("QueryResult");

        for(TableColumn<QueryRow, ?> column : tableView.getColumns()){
            String colName = column.getText();
            tb.addColumn(new ColumnBuilder(colName, DataType.TEXT));
        }

        var table = tb.toTable(db);

        for (QueryRow qr : tableView.getItems()) {
            Object[] rowData = new Object[tableView.getColumns().size()];
            int i = 0;
            for (TableColumn<QueryRow, ?> column : tableView.getColumns()) {
                Object cellValue = column.getCellObservableValue(qr).getValue();
                rowData[i++] = cellValue != null ? cellValue.toString() : null;
            }
            table.addRow(rowData);
        }
        db.close();
    }
}