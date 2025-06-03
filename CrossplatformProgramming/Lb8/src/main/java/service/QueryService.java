package service;

import dao.RouteDAO;
import dao.TrainDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import models.QueryRow;

import java.util.List;
import java.util.function.Consumer;

public class QueryService {
    private final TrainDAO trainDAO = new TrainDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    public ObservableList<QueryRow> executeQuery(int number) throws Exception {
        List<QueryRow> list;
        switch (number) {
            case 1:
                list = trainDAO.joinWagons();
                break;
            case 2:
                list = routeDAO.joinRouteStops();
                break;
            default:
                throw new IllegalArgumentException("Невідомий номер запиту");
        }
        return FXCollections.observableArrayList(list);
    }

    public Consumer<TableView<QueryRow>> getBuildColumnsConsumer(int number) throws Exception {
        switch (number) {
            case 1:
                return trainDAO.getBuildColumnsConsumer();
            case 2:
                return routeDAO.getBuildColumnsConsumer();
            default:
                throw new IllegalArgumentException("Невідомий номер запиту");
        }
    }
}