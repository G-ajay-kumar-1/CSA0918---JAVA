class ParkingTicket {
    private static int nextNumber = 1000;
    private final int ticketNumber;
    private final String registrationNumber;

    ParkingTicket() { this("UNKNOWN"); }
    ParkingTicket(String registrationNumber) {
        this.ticketNumber = ++nextNumber;
        this.registrationNumber = registrationNumber;
    }
    ParkingTicket(ParkingTicket other) {
        this(other.registrationNumber); // copy data, but issue a fresh unique ticket number
    }
    public String toString() { return ticketNumber + " : " + registrationNumber; }
}
public class ConstructorsDemo {
    public static void main(String[] args) {
        ParkingTicket first = new ParkingTicket("TN01AB1111");
        ParkingTicket copy = new ParkingTicket(first);
        System.out.println(first);
        System.out.println(copy);
    }
}
