package com.example.database;

import com.example.exceptions.AlertMessage;
import com.example.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbSetRoom extends DbSet implements IDbSet<Room> {
    PreparedStatement ps;
    AlertMessage alert = new AlertMessage();
    ResultSet rs;
    private ObservableList<Room> roomDataList = FXCollections.observableArrayList();

    public String getRoomNumberbyRoomId(int roomId) {
        String roomNumber = null;
        ps = PrepareStatement("SELECT room_number FROM rooms WHERE room_id = ?");
        try {
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            if (rs.next()) {
                roomNumber = rs.getString("room_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomNumber;
    }

    public int getRoomIdByRoomNumber(String roomNumber) {
        int roomId = -1;

        ps = PrepareStatement("SELECT room_id FROM rooms WHERE room_number = ?");
        try {
            ps.setString(1, roomNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                roomId = rs.getInt("room_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomId;
    }

    public ObservableList<String> getRoomTypeByStatus() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT DISTINCT room_type\n" +
                "FROM Rooms\n" +
                "WHERE room_id NOT IN (\n" +
                "    SELECT room_id\n" +
                "    FROM Bookings\n" +
                "    WHERE checked_out = 'Not Checked-out'\n" +
                ");\n");
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("room_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ObservableList<String> getRoomNumberByRoomType(String roomType) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ps = PrepareStatement("SELECT room_number\n" +
                "FROM Rooms\n" +
                "WHERE room_type = ?\n" +
                "AND room_id NOT IN (\n" +
                "    SELECT room_id\n" +
                "    FROM Bookings\n" +
                "    WHERE checked_out = 'Not Checked-out'\n" +
                ");\n");
        try {
            ps.setString(1, roomType);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("room_number"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void add(Room model) {
        try {
            ps = PrepareStatement("SELECT room_number FROM rooms WHERE room_number = ?");
            ps.setString(1, model.getRoomNumber());
            rs = ps.executeQuery();
            if (rs.next()) {
                alert.errorMessage("Room #" + model.getRoomNumber() + " is already exists!");
            } else {
                ps = PrepareStatement("INSERT INTO rooms (room_number, room_type, base_price) VALUES (?, ?, ?)");
                ps.setString(1, model.getRoomNumber());
                ps.setString(2, model.getRoomType());
                ps.setDouble(3, model.getBasePrice());
                if (ps.executeUpdate() > 0) {
                    alert.successMessage("Room added successfully to the database!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Room model) {
        ps = PrepareStatement("UPDATE rooms SET room_number = ?, room_type = ?, base_price = ? WHERE room_id = ?");
        try {
            ps.setString(1, model.getRoomNumber());
            ps.setString(2, model.getRoomType());
            ps.setDouble(3, model.getBasePrice());
            ps.setInt(4, model.getRoomId());
            ps.executeUpdate();
            alert.successMessage("Room updated successfully to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        ps = PrepareStatement("DELETE FROM rooms WHERE room_id = ?");
        try {
            Optional<ButtonType> option = alert.confirmationMessage("Are you sure you want to delete this room?");
            if (option.get().equals(ButtonType.OK)) {
                ps.setInt(1, id);
                ps.executeUpdate();

                alert.successMessage("Room deleted successfully to the database!");
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Room> findAll() {
        ps = PrepareStatement("SELECT * FROM rooms");
        try {
            rs = ps.executeQuery();
            Room room;
            while (rs.next()) {
                room = new Room(rs.getInt("room_id"), rs.getString("room_number"), rs.getString("room_type"),
                        rs.getDouble("base_price"));
                roomDataList.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomDataList;
    }
}
