import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Integrated baseline implementation for Tasks 1-11.
 * Assumptions: first started hour is charged in full; rates are Rs/hour;
 * monthly-pass holders who are students or staff pay zero parking fee.
 */
public class CampusParkingSystem {
    enum UserType { STUDENT, STAFF, VISITOR }
    enum VehicleType { TWO_WHEELER, CAR, ELECTRIC_CAR }

    static abstract class Vehicle {
        private final String registrationNumber;
        Vehicle(String registrationNumber) {
            if (registrationNumber == null || registrationNumber.isBlank())
                throw new IllegalArgumentException("Registration number required");
            this.registrationNumber = registrationNumber;
        }
        String getRegistrationNumber() { return registrationNumber; }
        abstract double ratePerHour();
        @Override public boolean equals(Object o) {
            return o instanceof Vehicle v && registrationNumber.equals(v.registrationNumber);
        }
        @Override public int hashCode() { return registrationNumber.hashCode(); }
    }
    interface Chargeable { double chargingFee(int kWh); }
    static class TwoWheeler extends Vehicle {
        TwoWheeler(String reg) { super(reg); }
        @Override double ratePerHour() { return 10; }
    }
    static class Car extends Vehicle {
        Car(String reg) { super(reg); }
        @Override double ratePerHour() { return 30; }
    }
    static class ElectricCar extends Car implements Chargeable {
        ElectricCar(String reg) { super(reg); }
        @Override public double chargingFee(int kWh) {
            if (kWh < 0) throw new IllegalArgumentException("Negative kWh");
            return kWh * 12.0;
        }
    }
    static class ParkingTicket {
        private static int nextId = 1000;
        private final int ticketId = ++nextId;
        private final Vehicle vehicle;
        private final int floor, slot;
        private final LocalDateTime entryTime;
        private final UserType userType;
        private final boolean monthlyPass;
        ParkingTicket(Vehicle vehicle, int floor, int slot, LocalDateTime entryTime,
                      UserType userType, boolean monthlyPass) {
            this.vehicle = vehicle; this.floor = floor; this.slot = slot;
            this.entryTime = entryTime; this.userType = userType; this.monthlyPass = monthlyPass;
        }
        int getTicketId() { return ticketId; }
        Vehicle getVehicle() { return vehicle; }
        int getFloor() { return floor; }
        int getSlot() { return slot; }
        LocalDateTime getEntryTime() { return entryTime; }
        UserType getUserType() { return userType; }
        boolean hasMonthlyPass() { return monthlyPass; }
    }
    static class ParkingFacility {
        private final String[][] slots;
        ParkingFacility(int[] counts) {
            slots = new String[counts.length][];
            for (int i=0;i<counts.length;i++) {
                if (counts[i] < 0) throw new IllegalArgumentException("Negative slot count");
                slots[i] = new String[counts[i]];
            }
        }
        int[] allocate(String reg) {
            for (int f=0;f<slots.length;f++)
                for (int s=0;s<slots[f].length;s++)
                    if (slots[f][s] == null) { slots[f][s]=reg; return new int[]{f,s}; }
            return null;
        }
        boolean release(int f,int s) {
            if (f<0 || f>=slots.length || s<0 || s>=slots[f].length || slots[f][s]==null) return false;
            slots[f][s]=null; return true;
        }
    }

    private final ParkingFacility facility;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();
    CampusParkingSystem(int[] slotsPerFloor) { facility = new ParkingFacility(slotsPerFloor); }

    ParkingTicket enter(Vehicle vehicle, UserType userType, boolean monthlyPass, LocalDateTime now) {
        String reg = vehicle.getRegistrationNumber();
        if (activeTickets.containsKey(reg)) throw new IllegalArgumentException("Duplicate registration: " + reg);
        int[] pos = facility.allocate(reg);
        if (pos == null) return null;
        ParkingTicket ticket = new ParkingTicket(vehicle,pos[0],pos[1],now,userType,monthlyPass);
        activeTickets.put(reg,ticket);
        return ticket;
    }

    double exit(String reg, LocalDateTime now) {
        ParkingTicket ticket = activeTickets.remove(reg);
        if (ticket == null) throw new IllegalArgumentException("No active ticket for " + reg);
        long minutes = Math.max(0, Duration.between(ticket.getEntryTime(),now).toMinutes());
        int hours = minutes == 0 ? 0 : (int)((minutes+59)/60);
        double fee = hours * ticket.getVehicle().ratePerHour();
        if (ticket.hasMonthlyPass() &&
            (ticket.getUserType()==UserType.STUDENT || ticket.getUserType()==UserType.STAFF)) fee=0;
        facility.release(ticket.getFloor(),ticket.getSlot());
        return fee;
    }

    public static void main(String[] args) {
        CampusParkingSystem system = new CampusParkingSystem(new int[]{2,1});
        LocalDateTime start = LocalDateTime.of(2026,11,12,9,0);
        ParkingTicket t1 = system.enter(new TwoWheeler("TN01A1"),UserType.VISITOR,false,start);
        ParkingTicket t2 = system.enter(new Car("TN02B2"),UserType.STUDENT,true,start);
        ParkingTicket t3 = system.enter(new ElectricCar("TN03C3"),UserType.VISITOR,false,start);
        System.out.println("Tickets: " + t1.getTicketId()+", "+t2.getTicketId()+", "+t3.getTicketId());
        System.out.println("Two-wheeler fee (65 min): Rs " +
            system.exit("TN01A1",start.plusMinutes(65)));
        System.out.println("Student monthly-pass fee (120 min): Rs " +
            system.exit("TN02B2",start.plusMinutes(120)));
        System.out.println("EV parking fee (130 min): Rs " +
            system.exit("TN03C3",start.plusMinutes(130)));
        try { system.exit("UNKNOWN",start.plusMinutes(10)); }
        catch (IllegalArgumentException ex) { System.out.println("Invalid exit handled: "+ex.getMessage()); }
    }
}
