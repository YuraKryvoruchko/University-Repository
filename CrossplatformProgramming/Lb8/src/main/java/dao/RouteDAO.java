package dao;

import db.DBUtil;
import javafx.scene.control.TableView;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RouteDAO {
    private Connection conn;

    public RouteDAO() {
        try{
            this.conn = DBUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Route> findAll() throws SQLException {
        String sql = "SELECT * FROM routes";
        List<Route> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Route route = new Route(
                        rs.getInt("id"),
                        rs.getInt("train_id"),
                        rs.getString("name")
                );
                list.add(route);
            }
        }
        return list;
    }
    public Route findById(int id) throws SQLException {
        String sql = "SELECT * FROM routes WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Route route = new Route(
                            rs.getInt("id"),
                            rs.getInt("train_id"),
                            rs.getString("name")
                    );
                    return route;
                }
            }
        }
        return null;
    }
    public void create(Route route) throws SQLException {
        String sql = "INSERT INTO routes (train_id, name) VALUES (?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, route.getTrainId());
            st.setString(2, route.getName());
            st.executeUpdate();
        }
    }
    public void update(Route route) throws SQLException {
        String sql = "UPDATE routes SET train_id=?, name=? WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, route.getTrainId());
            st.setString(2, route.getName());
            st.setInt(3, route.getId());
            st.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM routes WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
    public List<QueryRow> joinRouteStops() throws SQLException {
        String sql =
                "SELECT r.id, r.train_id, r.name, rs.route_id, rs.station_id, rs.arrival_time, rs.departure_time, rs.stop_order, " +
                "s.station_id, s.station_name, s.station_location " +
                "FROM routes r " +
                "JOIN route_stops rs ON r.id = rs.route_id " +
                "JOIN stations s ON rs.station_id = s.station_id " +
                "ORDER BY r.name, rs.stop_order";
        List<QueryRow> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                RouteAndRouteStopsRow row = new RouteAndRouteStopsRow();
                row.setRouteName(rs.getString("r.name"));
                row.setStopOrder(rs.getInt("rs.stop_order"));
                row.setArrivalTime(rs.getTime("rs.arrival_time"));
                row.setDepartureTime(rs.getTime("rs.departure_time"));
                row.setStationName(rs.getString("s.station_name"));
                row.setStationLocation(rs.getString("s.station_location"));
                result.add(row);
            }
        }
        return result;
    }
    public Consumer<TableView<QueryRow>> getBuildColumnsConsumer() throws Exception {
        return RouteAndRouteStopsRow::buildColumns;
    }
}
