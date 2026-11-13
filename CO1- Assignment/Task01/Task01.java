import java.util.*;

public class Task01 {
    static class Vehicle {
        String registrationNumber;
        String type;
        Vehicle(String registrationNumber, String type) {
            this.registrationNumber = registrationNumber;
            this.type = type;
        }
    }
    static class ParkingSlot {
        int floor, slot;
        Vehicle vehicle;
        ParkingSlot(int floor, int slot) { this.floor = floor; this.slot = slot; }
        boolean isFree() { return vehicle == null; }
    }
    public static void main(String[] args) {
        Vehicle v = new Vehicle("TN01AB1111", "CAR");
        ParkingSlot s = new ParkingSlot(0, 0);
        s.vehicle = v;
        System.out.println("Vehicle: " + s.vehicle.registrationNumber);
        System.out.println("Slot free: " + s.isFree());
    }
}
