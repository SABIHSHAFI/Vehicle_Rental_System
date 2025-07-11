import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        VehicleRentalService rentalService = new VehicleRentalService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Vehicle Rental System ===");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. View Active Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    rentalService.displayAvailableVehicles();
                    break;
                case 2:
                    rentalService.rentVehicle(sc);
                    break;
                case 3:
                    rentalService.showActiveBookings();
                    break;
                case 4:
                    System.out.println("Exiting. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
