import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (booking_id, vehicle_id, customer_name, contact_number, duration_secs, booking_time) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getBookingId());
            ps.setInt(2, booking.getVehicleId());
            ps.setString(3, booking.getCustomerName());
            ps.setString(4, booking.getContactNumber());
            ps.setInt(5, booking.getDurationSecs());
            ps.setTimestamp(6, Timestamp.valueOf(booking.getBookingTime()));

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean removeBooking(int bookingId) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("vehicle_id"),
                    rs.getString("customer_name"),
                    rs.getString("contact_number"),
                    rs.getInt("duration_secs")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
}
