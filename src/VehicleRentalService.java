import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VehicleRentalService {
    private final VehicleDAO vehicleDAO = new VehicleDAO();
    private final BookingDAO bookingDAO = new BookingDAO();
    private final Map<Integer, Booking> activeBookings = new HashMap<>();
    private final AtomicInteger bookingIdGenerator = new AtomicInteger(1000);

    public void displayAvailableVehicles() {
        List<Vehicle> all = vehicleDAO.getAllVehicles();
        System.out.println("\n--- Available Vehicles ---");
        for (Vehicle v : all) {
            if (v.isAvailable()) {
                System.out.println(v);
            }
        }
    }

    public void rentVehicle(Scanner sc) {
        displayAvailableVehicles();

        System.out.print("Enter Vehicle ID to rent: ");
        int vehicleId = sc.nextInt(); sc.nextLine();

        Vehicle vehicle = vehicleDAO.getVehicleById(vehicleId);
        if (vehicle == null || !vehicle.isAvailable()) {
            System.out.println("Vehicle not available.");
            return;
        }

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter contact number: ");
        String number = sc.nextLine();
        System.out.print("Enter duration in seconds: ");
        int duration = sc.nextInt(); sc.nextLine();

        int bookingId = bookingIdGenerator.incrementAndGet();
        Booking booking = new Booking(bookingId, vehicleId, name, number, duration);

        // Update DB and memory
        if (vehicleDAO.updateAvailability(vehicleId, false) && bookingDAO.addBooking(booking)) {
            activeBookings.put(vehicleId, booking);
            System.out.println("Vehicle booked successfully! Booking ID: " + bookingId);

            // Start auto-return thread
            Thread t = new BookingThread(vehicleId, duration, this);
            t.start();
        } else {
            System.out.println("Booking failed.");
        }
    }

    public void autoReturnVehicle(int vehicleId) {
        Booking booking = activeBookings.remove(vehicleId);
        if (booking != null) {
            bookingDAO.removeBooking(booking.getBookingId());
            vehicleDAO.updateAvailability(vehicleId, true);
            System.out.println("\n[Auto-Return] Vehicle ID " + vehicleId + " is now available.");
        }
    }

    public void showActiveBookings() {
        System.out.println("\n--- Active Bookings ---");
        for (Booking b : activeBookings.values()) {
            System.out.println(b);
        }
    }
}
 
