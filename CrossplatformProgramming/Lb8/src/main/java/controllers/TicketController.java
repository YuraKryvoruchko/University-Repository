package controllers;

import db.DBUtil;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketController {
    @FXML
    private TableView<Ticket> ticketTable;
    @FXML
    private TableColumn<Ticket, Integer> ticketNumberColumn;
    @FXML
    private TableColumn<Ticket, Number> ticketTrainNumberColumn;
    @FXML
    private TableColumn<Ticket, Number> ticketWagonNumberColumn;
    @FXML
    private TableColumn<Ticket, Integer> seatNumberColumn;
    @FXML
    private TableColumn<Ticket, String> passportDataColumn;
    @FXML
    private TableColumn<Ticket, Date> departureDateColumn;
    @FXML
    private TableColumn<Ticket, String> firstRouteStationColumn;
    @FXML
    private TableColumn<Ticket, String> lastRouteStationColumn;
    @FXML
    private TableColumn<Ticket, String> boardingStationColumn;
    @FXML
    private TableColumn<Ticket, String> destinationStationColumn;
    @FXML
    private TableColumn<Ticket, String> wagonTypeColumn;
    @FXML
    private TableColumn<Ticket, Float> ticketPriceColumn;

    @FXML
    private Button addTicketButton;
    @FXML
    private Button editTicketButton;
    @FXML
    private Button deleteTicketButton;

    private ObservableList<Ticket> trainList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ticketNumberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketTrainNumberColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getTrain().getNumber());
        });
        ticketWagonNumberColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getWagon().getId());
        });
        seatNumberColumn.setCellValueFactory(new PropertyValueFactory<>("seat"));
        passportDataColumn.setCellValueFactory(cellData -> {
            Passenger passenger = cellData.getValue().getPassenger();
            String str = passenger.getFirstName() + " " + passenger.getSecondName() + "\nНомер: " + passenger.getPassportNumber();
            return new SimpleStringProperty(str);
        });
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("dispatchDate"));
        firstRouteStationColumn.setCellValueFactory(cellData -> {
            RouteStop startRouteStop = cellData.getValue().getStartRouteStop();
            String formatted = startRouteStop.getStation().getName() + "\n" + startRouteStop.getDepartureTime();
            return new SimpleStringProperty(formatted);
        });
        lastRouteStationColumn.setCellValueFactory(cellData -> {
            RouteStop lastRouteStop = cellData.getValue().getLastRouteStop();
            String formatted = lastRouteStop.getStation().getName() + "\n" + lastRouteStop.getArrivalTime();
            return new SimpleStringProperty(formatted);
        });
        boardingStationColumn.setCellValueFactory(cellData -> {
            RouteStop fromRouteStop = cellData.getValue().getFromRouteStop();
            String formatted = fromRouteStop.getStation().getName() + "\n" + fromRouteStop.getDepartureTime();
            return new SimpleStringProperty(formatted);
        });
        destinationStationColumn.setCellValueFactory(cellData -> {
            RouteStop toRouteStop = cellData.getValue().getToRouteStop();
            String formatted = toRouteStop.getStation().getName() + "\n" + toRouteStop.getArrivalTime();
            return new SimpleStringProperty(formatted);
        });
        wagonTypeColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getWagon().getType().toString());
        });
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadTickets();

        addTicketButton.setOnAction(e -> addTicket());
        editTicketButton.setOnAction(e -> editTicket());
        deleteTicketButton.setOnAction(e -> deleteTicket());
    }
    // Метод завантаження даних
    public void loadTickets() {
        trainList.clear();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM tickets";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ticketId = rs.getInt("id");
                Train train = getTrain(rs.getInt("train_id"), conn);
                Wagon wagon = getWagon(rs.getInt("wagon_id"), conn);
                Route route = getRoute(rs.getInt("route_id"), conn);
                int fromStationId = rs.getInt("from_station_id");
                int toStationId = rs.getInt("to_station_id");
                List<RouteStop> routeStopList = getRouteStopsByRouteId(route.getId(), conn);

                trainList.add(new Ticket(
                        ticketId,
                        train,
                        wagon,
                        rs.getInt("seat"),
                        getPassenger(rs.getInt("passenger_id"), conn),
                        rs.getDate("dispatch_date"),
                        route,
                        routeStopList.getFirst(),
                        routeStopList.getLast(),
                        routeStopList.stream().filter(routeStop -> routeStop.getStation().getId() == fromStationId).findFirst().get(),
                        routeStopList.stream().filter(routeStop -> routeStop.getStation().getId() == toStationId).findFirst().get(),
                        rs.getFloat("ticket_price")
                ));
            }
            ticketTable.setItems(trainList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Train getTrain(int trainId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM trains WHERE train_id = " + trainId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Train(
                rs.getInt("train_id"),
                rs.getInt("train_number"),
                Train.TrainType.valueOf(rs.getString("train_type"))
        );
    }
    private Wagon getWagon(int wagonId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM wagons WHERE id = " + wagonId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Wagon(
                rs.getInt("id"),
                rs.getInt("train_id"),
                Wagon.WagonType.valueOf(rs.getString("type")),
                rs.getInt("seats")
        );
    }
    private Route getRoute(int routeId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM routes WHERE id = " + routeId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Route(
                rs.getInt("id"),
                rs.getInt("train_id"),
                rs.getString("name")
        );
    }
    private Passenger getPassenger(int passengerId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM passengers WHERE id = " + passengerId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Passenger(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("second_name"),
                rs.getString("passport_number")
        );
    }
    private List<RouteStop> getRouteStopsByRouteId(int routeId, Connection conn) throws SQLException {
        List<RouteStop> routeStopList = new ArrayList<>();
        String sql = "SELECT * FROM route_stops WHERE route_id = " + routeId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            RouteStop routeStop = new RouteStop(
                    rs.getInt("id"),
                    rs.getInt("route_id"),
                    getRouteStation(rs.getInt("station_id"), conn),
                    rs.getTime("arrival_time"),
                    rs.getTime("departure_time"),
                    rs.getInt("stop_order")
            );
            routeStopList.add(routeStop);
        }
        return routeStopList;
    }
    private Station getRouteStation(int stationId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM stations WHERE station_id = " + stationId;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return new Station(
                rs.getInt("station_id"),
                rs.getString("station_name"),
                rs.getString("station_location")
        );
    }

    private void addTicket() {
        System.out.println("Ticket adding");
    }
    private void editTicket() {
        System.out.println("Ticket editing");
    }
    private void deleteTicket() {
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            trainList.remove(selectedTicket);
            System.out.println("Ticket has deleted");
        } else {
            System.out.println("Choose a ticket");
        }
    }
}
