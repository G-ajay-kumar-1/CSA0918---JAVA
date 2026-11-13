package parking.model;

public class ParkingSlot {
    private final int floorNumber;
    private final int slotNumber;
    private String vehicleNumber;

    public ParkingSlot(int floorNumber, int slotNumber) {
        if (floorNumber < 0 || slotNumber < 0) throw new IllegalArgumentException("Invalid slot position");
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
    }
    public int getFloorNumber() { return floorNumber; }
    public int getSlotNumber() { return slotNumber; }
    public String getVehicleNumber() { return vehicleNumber; }
    public boolean isFree() { return vehicleNumber == null; }
    public void park(String vehicleNumber) {
        if (vehicleNumber == null || vehicleNumber.isBlank()) throw new IllegalArgumentException("Vehicle number required");
        if (!isFree()) throw new IllegalStateException("Slot is occupied");
        this.vehicleNumber = vehicleNumber;
    }
    public void release() {
        if (isFree()) throw new IllegalStateException("Slot is already free");
        vehicleNumber = null;
    }
}
