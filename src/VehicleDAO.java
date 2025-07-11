import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String model = rs.getString("model");
                String numberPlate = rs.getString("number_plate");
                boolean isAvailable = rs.getBoolean("is_available");

                vehicles.add(new Vehicle(id, type, model, numberPlate, isAvailable));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public Vehicle getVehicleById(int id) {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("model"),
                        rs.getString("number_plate"),
                        rs.getBoolean("is_available")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateAvailability(int vehicleId, boolean isAvailable) {
        String sql = "UPDATE vehicles SET is_available = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isAvailable);
            ps.setInt(2, vehicleId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
