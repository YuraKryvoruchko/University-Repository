package dao;

import db.DBUtil;
import javafx.scene.control.TableView;
import models.QueryRow;
import models.Train;
import models.TrainWagonRow;
import models.Wagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TrainDAO {
    private Connection conn;

    public TrainDAO() {
        try{
            this.conn = DBUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Train> findAll() throws SQLException {
        String sql = "SELECT * FROM trains";
        List<Train> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Train train = new Train(
                        rs.getInt("train_id"),
                        rs.getInt("train_number"),
                        Train.TrainType.valueOf(rs.getString("train_type"))
                );
                list.add(train);
            }
        }
        return list;
    }
    public Train findById(int id) throws SQLException {
        String sql = "SELECT * FROM trains WHERE train_id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Train train = new Train(
                            rs.getInt("train_id"),
                            rs.getInt("train_number"),
                            Train.TrainType.valueOf(rs.getString("train_type"))
                    );
                    return train;
                }
            }
        }
        return null;
    }
    public void create(Train train) throws SQLException {
        String sql = "INSERT INTO trains (train_number, train_type) VALUES (?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, train.getNumber());
            st.setString(2, train.getType().toString());
            st.executeUpdate();
        }
    }
    public void update(Train train) throws SQLException {
        String sql = "UPDATE trains SET train_number=?, train_type=? WHERE train_id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, train.getNumber());
            st.setString(2, train.getType().toString());
            st.setInt(3, train.getId());
            st.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM trains WHERE train_id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
    public List<QueryRow> joinWagons() throws SQLException {
        String sql =
                "SELECT t.train_id, t.train_number, t.train_type, w.wagon_number, w.seats, w.type FROM trains t " +
                "JOIN wagons w ON t.train_id = w.train_id " +
                "ORDER BY t.train_number";
        List<QueryRow> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                TrainWagonRow row = new TrainWagonRow();
                row.setTrainNumber(rs.getString("t.train_number"));
                row.setTrainType(Train.TrainType.valueOf(rs.getString("t.train_type")));
                row.setWagonNumber(rs.getString("w.wagon_number"));
                row.setWagonSeats(rs.getInt("w.seats"));
                row.setWagonType(Wagon.WagonType.valueOf(rs.getString("w.type")));
                result.add(row);
            }
        }
        return result;
    }
    public Consumer<TableView<QueryRow>> getBuildColumnsConsumer() throws Exception {
        return TrainWagonRow::buildColumns;
    }
}
