import parking.model.ParkingSlot;

public class EncapsulationDemo {
    public static void main(String[] args) {
        ParkingSlot slot = new ParkingSlot(0, 2);
        slot.park("TN09XY9999");
        System.out.println("Occupied by: " + slot.getVehicleNumber());
        slot.release();
        System.out.println("Slot free: " + slot.isFree());
    }
}
