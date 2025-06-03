package controllers;

import db.DBUtil;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private ObservableList<Ticket> ticketObservableList = FXCollections.observableArrayList();

    private FilteredList<Ticket> filteredList;

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

        filteredList = new FilteredList<>(ticketObservableList, p -> true);
        ticketTable.setItems(filteredList);

        addTicketButton.setOnAction(e -> addTicket());
        editTicketButton.setOnAction(e -> editTicket());
        deleteTicketButton.setOnAction(e -> deleteTicket());
    }
    // Метод завантаження даних
    public void loadTickets() {
        ticketObservableList.clear();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM tickets";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ticketId = rs.getInt("id");
                Train train = getTrain(rs.getInt("train_id"));
                Wagon wagon = getWagon(rs.getInt("wagon_id"));
                Route route = getRoute(rs.getInt("route_id"));
                int fromStationId = rs.getInt("from_station_id");
                int toStationId = rs.getInt("to_station_id");
                List<RouteStop> routeStopList = getRouteStopsByRouteId(route.getId());

                ticketObservableList.add(new Ticket(
                        ticketId,
                        train,
                        wagon,
                        rs.getInt("seat"),
                        getPassenger(rs.getInt("passenger_id")),
                        rs.getDate("dispatch_date"),
                        route,
                        routeStopList.getFirst(),
                        routeStopList.getLast(),
                        routeStopList.stream().filter(routeStop -> routeStop.getStation().getId() == fromStationId).findFirst().get(),
                        routeStopList.stream().filter(routeStop -> routeStop.getStation().getId() == toStationId).findFirst().get(),
                        rs.getFloat("ticket_price")
                ));
            }
<<<<<<< HEAD
=======
            ticketTable.setItems(ticketObservableList);
>>>>>>> e920fdb28acb29e05902aa96a4be1882e5164f88
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Train getTrain(int trainId) {
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM trains WHERE train_id = " + trainId;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Train(
                    rs.getInt("train_id"),
                    rs.getInt("train_number"),
                    Train.TrainType.valueOf(rs.getString("train_type"))
            );
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    private Wagon getWagon(int wagonId) {
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM wagons WHERE id = " + wagonId;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Wagon(
                    rs.getInt("id"),
                    rs.getString("wagon_number"),
                    rs.getInt("train_id"),
                    Wagon.WagonType.valueOf(rs.getString("type")),
                    rs.getInt("seats")
            );
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    private Route getRoute(int routeId) {
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM routes WHERE id = " + routeId;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Route(
                    rs.getInt("id"),
                    rs.getInt("train_id"),
                    rs.getString("name")
            );
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    private Passenger getPassenger(int passengerId) {
        try {
            Connection conn = DBUtil.getConnection();
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
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    private List<RouteStop> getRouteStopsByRouteId(int routeId) {
        try {
            Connection conn = DBUtil.getConnection();
            List<RouteStop> routeStopList = new ArrayList<>();
            String sql = "SELECT * FROM route_stops WHERE route_id = " + routeId;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                RouteStop routeStop = new RouteStop(
                        rs.getInt("id"),
                        rs.getInt("route_id"),
                        getRouteStation(rs.getInt("station_id")),
                        rs.getTime("arrival_time"),
                        rs.getTime("departure_time"),
                        rs.getInt("stop_order")
                );
                routeStopList.add(routeStop);
            }
            return routeStopList;
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    private Station getRouteStation(int stationId) {
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM stations WHERE station_id = " + stationId;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Station(
                    rs.getInt("station_id"),
                    rs.getString("station_name"),
                    rs.getString("station_location")
            );
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void addTicket() {
        Ticket temp = showTicketDialog(null);
        if (temp != null) {
            String sql = "INSERT INTO passengers (first_name, second_name, passport_number) VALUES (?, ?, ?)";

            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, temp.getPassenger().getFirstName());
                ps.setString(2, temp.getPassenger().getSecondName());
                ps.setString(3, temp.getPassenger().getPassportNumber());
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    temp.getPassenger().setId(keys.getInt(1));
                }

                sql = "INSERT INTO tickets (train_id, wagon_id, seat, passenger_id, dispatch_date, route_id, " +
                        "from_station_id, to_station_id, ticket_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps2.setInt(1, temp.getTrain().getId());
                    ps2.setInt(2, temp.getWagon().getId());
                    ps2.setInt(3, temp.getSeat());
                    ps2.setInt(4, temp.getPassenger().getId());
                    ps2.setDate(5, temp.getDispatchDate());
                    ps2.setInt(6, temp.getRoute().getId());
                    ps2.setInt(7, temp.getFromRouteStop().getStation().getId());
                    ps2.setInt(8, temp.getToRouteStop().getStation().getId());
                    ps2.setFloat(9, temp.getPrice());
                    ps2.executeUpdate();
                    keys = ps2.getGeneratedKeys();
                    if (keys.next()) {
                        temp.setId(keys.getInt(1));
                    }
                }
                ticketObservableList.add(temp);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося додати квиток");
            }
        }
    }
    private void editTicket() {
        Ticket ticket = ticketTable.getSelectionModel().getSelectedItem();
        if (ticket == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть квиток для редагування");
            return;
        }
        Ticket edited = showTicketDialog(ticket);
        if (edited != null) {
            String sql = "UPDATE passengers SET first_name=?, second_name=?, passport_number=? WHERE id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, edited.getPassenger().getFirstName());
                ps.setString(2, edited.getPassenger().getSecondName());
                ps.setString(3, edited.getPassenger().getPassportNumber());
                ps.setInt(4, edited.getPassenger().getId());
                ps.executeUpdate();

                sql = "UPDATE tickets SET train_id=?, wagon_id=?, seat=?, passenger_id=?, dispatch_date=?, route_id=?, " +
                        "from_station_id=?, to_station_id=?, ticket_price=? WHERE id=?";
                try (PreparedStatement ps2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps2.setInt(1, edited.getTrain().getId());
                    ps2.setInt(2, edited.getWagon().getId());
                    ps2.setInt(3, edited.getSeat());
                    ps2.setInt(4, edited.getPassenger().getId());
                    ps2.setDate(5, edited.getDispatchDate());
                    ps2.setInt(6, edited.getRoute().getId());
                    ps2.setInt(7, edited.getFromRouteStop().getStation().getId());
                    ps2.setInt(8, edited.getToRouteStop().getStation().getId());
                    ps2.setFloat(9, edited.getPrice());
                    ps2.setInt(10, edited.getId());
                    ps2.executeUpdate();
                }
                loadTickets();
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося оновити квиток");
            }
        }
    }
    private void deleteTicket() {
        Ticket ticket = ticketTable.getSelectionModel().getSelectedItem();
        if (ticket == null) {
            showAlert(Alert.AlertType.WARNING, "Увага", "Оберіть квиток для видалення");
            return;
        }
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION,
                "Видалити квиток \"" + ticket.getId() + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = conf.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES) {
            String sql = "DELETE FROM tickets WHERE id=?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, ticket.getId());
                ps.executeUpdate();
                ticketObservableList.remove(ticket);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Помилка", "Не вдалося видалити квиток");
            }
        }
    }

    private Ticket showTicketDialog(Ticket ticket) {
        Dialog<Ticket> dialog = new Dialog<>();
        dialog.setTitle(ticket == null ? "Додати квиток" : "Редагувати квиток");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField passengerNameField = new TextField(ticket == null || ticket.getPassenger() == null ? null :
                ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getSecondName());
        TextField passportNumberField = new TextField(ticket == null || ticket.getPassenger() == null ? null :
                ticket.getPassenger().getPassportNumber());

        ChoiceBox<Train> trainIdChoiceBox = new ChoiceBox<>(FXCollections.observableList(getAllTrains()));
        trainIdChoiceBox.setValue(ticket == null ? null : ticket.getTrain());

<<<<<<< HEAD
        ChoiceBox<Route> routeIdChoiceBox = new ChoiceBox<>();
        if(ticket != null) routeIdChoiceBox.setItems(FXCollections.observableList(getAllRoutesForTrainId(trainIdChoiceBox.getValue().getId())));

        routeIdChoiceBox.setValue(ticket == null ? null : ticket.getRoute());

        ChoiceBox<Wagon> wagonChoiceBox = new ChoiceBox<>();
        if(ticket != null) wagonChoiceBox.setItems(FXCollections.observableList(getAllWagonsByTrainId(trainIdChoiceBox.getValue().getId())));
=======
        ChoiceBox<Route> routeIdChoiceBox = new ChoiceBox<>(ticket == null ? null :
                FXCollections.observableList(getAllRoutesForTrainId(trainIdChoiceBox.getValue().getId())));
        routeIdChoiceBox.setValue(ticket == null ? null : ticket.getRoute());

        ChoiceBox<Wagon> wagonChoiceBox = new ChoiceBox<>(ticket == null ? null :
                FXCollections.observableList(getAllWagonsByTrainId(trainIdChoiceBox.getValue().getId())));
>>>>>>> e920fdb28acb29e05902aa96a4be1882e5164f88
        wagonChoiceBox.setValue(ticket == null ? null : ticket.getWagon());

        TextField seatNumberField = new TextField(ticket == null ? null : String.valueOf(ticket.getSeat()));
        DatePicker datePicker = new DatePicker(ticket == null ? null : ticket.getDispatchDate().toLocalDate());
<<<<<<< HEAD
        ChoiceBox<RouteStop> boardingStationChoiceBox = new ChoiceBox<>();
        if(ticket != null) boardingStationChoiceBox.setItems(FXCollections.observableList(getRouteStopsByRouteId(routeIdChoiceBox.getValue().getId())));
        boardingStationChoiceBox.setValue(ticket == null ? null : ticket.getFromRouteStop());
        ChoiceBox<RouteStop> destinationStationChoicebox = new ChoiceBox<>();
        if(ticket != null) destinationStationChoicebox.setItems(FXCollections.observableList(getRouteStopsByRouteId(routeIdChoiceBox.getValue().getId())));
=======
        ChoiceBox<RouteStop> boardingStationChoiceBox = new ChoiceBox<>(ticket == null ? null :
                FXCollections.observableList(getRouteStopsByRouteId(routeIdChoiceBox.getValue().getId())));
        boardingStationChoiceBox.setValue(ticket == null ? null : ticket.getFromRouteStop());
        ChoiceBox<RouteStop> destinationStationChoicebox = new ChoiceBox<>(ticket == null ? null :
                FXCollections.observableList(getRouteStopsByRouteId(routeIdChoiceBox.getValue().getId())));
>>>>>>> e920fdb28acb29e05902aa96a4be1882e5164f88
        destinationStationChoicebox.setValue(ticket == null ? null : ticket.getToRouteStop());

        trainIdChoiceBox.setOnAction(e -> {
            if(trainIdChoiceBox.getValue() == null){
                routeIdChoiceBox.setItems(null);
                wagonChoiceBox.setItems(null);
            }
            else{
                routeIdChoiceBox.setItems(FXCollections.observableList(getAllRoutesForTrainId(trainIdChoiceBox.getValue().getId())));
                wagonChoiceBox.setItems(FXCollections.observableList(getAllWagonsByTrainId(trainIdChoiceBox.getValue().getId())));
            }
        });
        routeIdChoiceBox.setOnAction(e -> {
            if(routeIdChoiceBox.getValue() == null) {
                boardingStationChoiceBox.setItems(null);
                destinationStationChoicebox.setItems(null);
            }
            else{
                boardingStationChoiceBox.setItems(FXCollections.observableList(getRouteStopsByRouteId(
                        routeIdChoiceBox.getValue().getId())));
                destinationStationChoicebox.setItems(FXCollections.observableList(getRouteStopsByRouteId(
                        routeIdChoiceBox.getValue().getId())));
            }
        });

        TextField priceField = new TextField(ticket == null ? null : String.valueOf(ticket.getPrice()));

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.setPrefWidth(600);
        grid.addRow(0, new Label("Ім'я та прізвище пасажира:"), passengerNameField);
        grid.addRow(1, new Label("Номер паспорта:"), passportNumberField);
        grid.addRow(2, new Label("Потяг:"), trainIdChoiceBox);
        grid.addRow(3, new Label("Вагон:"), wagonChoiceBox);
        grid.addRow(4, new Label("Номер місця:"), seatNumberField);
        grid.addRow(5, new Label("Маршрут:"), routeIdChoiceBox);
        grid.addRow(6, new Label("Дата:"), datePicker);
        grid.addRow(7, new Label("Місце посадки:"), boardingStationChoiceBox);
        grid.addRow(8, new Label("Місце призначення:"), destinationStationChoicebox);
        grid.addRow(9, new Label("Ціна квитка:"), priceField);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                String[] parts = passengerNameField.getText().split(" ", 2);
                Passenger passenger = new Passenger(ticket == null ? 0 : ticket.getPassenger().getId(),
                        parts[0], parts[1], passportNumberField.getText());
                Ticket newTicket = new Ticket(
                        ticket == null ? 0 : ticket.getId(),
                        trainIdChoiceBox.getValue(),
                        wagonChoiceBox.getValue(),
                        Integer.parseInt(seatNumberField.getText()),
                        passenger,
                        Date.valueOf(datePicker.getValue()),
                        routeIdChoiceBox.getValue(),
                        boardingStationChoiceBox.getItems().getFirst(),
                        destinationStationChoicebox.getItems().getLast(),
                        boardingStationChoiceBox.getValue(),
                        destinationStationChoicebox.getValue(),
                        Float.parseFloat(priceField.getText())
                );
                return newTicket;
            }
            return null;
        });
        Optional<Ticket> res = dialog.showAndWait();
        return res.orElse(null);
    }

    private List<Train> getAllTrains(){
        List<Train> list = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM trains";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Train(
                        rs.getInt("train_id"),
                        rs.getInt("train_number"),
                        Train.TrainType.valueOf(rs.getString("train_type"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<Route> getAllRoutesForTrainId(int trainId){
        List<Route> list = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM routes WHERE train_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, trainId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Route(
                        rs.getInt("id"),
                        rs.getInt("train_id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    private List<Wagon> getAllWagonsByTrainId(int trainId){
        List<Wagon> list = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM wagons WHERE train_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, trainId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Wagon(
                        rs.getInt("id"),
                        rs.getString("wagon_number"),
                        rs.getInt("train_id"),
                        Wagon.WagonType.valueOf(rs.getString("type")),
                        rs.getInt("seats"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert a = new Alert(type);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }

    public void search(String text) {
        String lower = text == null ? "" : text.trim().toLowerCase();
        filteredList.setPredicate(p -> {
            if (lower.isEmpty()) return true;
            return String.valueOf(p.getTrain().getNumber()).toLowerCase().contains(lower)
                    || p.getWagon().getNumber().toLowerCase().contains(lower)
                    || (p.getPassenger().getFirstName() + " " + p.getPassenger().getSecondName()).toLowerCase().contains(lower)
                    || p.getPassenger().getPassportNumber().toLowerCase().contains(lower)
                    || p.getFromRouteStop().getStation().getName().toLowerCase().contains(lower)
                    || p.getToRouteStop().getStation().getName().toLowerCase().contains(lower)
                    || p.getStartRouteStop().getStation().getName().toLowerCase().contains(lower)
                    || p.getLastRouteStop().getStation().getName().toLowerCase().contains(lower)
                    || p.getRoute().toString().toLowerCase().contains(lower)
                    || String.valueOf(p.getId()).contains(lower);
        });
    }
}
