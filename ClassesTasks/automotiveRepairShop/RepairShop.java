package automotiveRepairShop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepairShop {
    private int capacity;
    private List<Vehicle> vehicles;

    public RepairShop(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();

    }

    public void addVehicle(Vehicle vehicle) {
        if (this.capacity > this.vehicles.size()) {
            this.vehicles.add(vehicle);
        }

    }

    public boolean removeVehicle(String vin) {
        return this.vehicles.removeIf(v -> v.getVIN().equals(vin));
    }

    public int getCount() {
        return this.vehicles.size();

    }

    public Vehicle getLowestMileage() {
        return this.vehicles.stream().min(Comparator.comparing(Vehicle::getMileage)).orElse(null);
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vehicles in the preparatory:").append(System.lineSeparator());
        this.vehicles.forEach(v -> builder.append(v.toString()).append(String.format(System.lineSeparator())));
        return builder.toString().trim();
    }
}
