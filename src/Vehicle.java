 public class Vehicle {
    private int id;
    private String type;
    private String model;
    private String numberPlate;
    private boolean isAvailable;

    public Vehicle(int id, String type, String model, String numberPlate, boolean isAvailable) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.numberPlate = numberPlate;
        this.isAvailable = isAvailable;
    }

    // Getters
    public int getId() { return id; }
    public String getType() { return type; }
    public String getModel() { return model; }
    public String getNumberPlate() { return numberPlate; }
    public boolean isAvailable() { return isAvailable; }

    // Setters
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + model + " (" + type + ") - " + numberPlate + 
               " - " + (isAvailable ? "Available" : "Not Available");
    }
}
