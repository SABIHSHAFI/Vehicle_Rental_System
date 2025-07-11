import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private int vehicleId;
    private String customerName;
    private String contactNumber;
    private int durationSecs;
    private LocalDateTime bookingTime;

    public Booking(int bookingId, int vehicleId, String customerName, String contactNumber, int durationSecs) {
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.durationSecs = durationSecs;
        this.bookingTime = LocalDateTime.now();
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public int getVehicleId() { return vehicleId; }
    public String getCustomerName() { return customerName; }
    public String getContactNumber() { return contactNumber; }
    public int getDurationSecs() { return durationSecs; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Vehicle ID: " + vehicleId +
               ", Customer: " + customerName + ", Duration: " + durationSecs + "s";
    }
}
 
