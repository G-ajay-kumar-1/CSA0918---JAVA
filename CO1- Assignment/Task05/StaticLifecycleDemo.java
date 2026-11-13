class TicketCounter {
    private static int nextTicket = 5000;
    private final int ticketNumber;
    TicketCounter() { ticketNumber = ++nextTicket; }
    int getTicketNumber() { return ticketNumber; }
    static int getLastIssuedNumber() { return nextTicket; }
}
public class StaticLifecycleDemo {
    public static void main(String[] args) {
        TicketCounter north = new TicketCounter();
        TicketCounter south = new TicketCounter();
        System.out.println(north.getTicketNumber());
        System.out.println(south.getTicketNumber());
        System.out.println("Last issued: " + TicketCounter.getLastIssuedNumber());
        north = null; // object can become eligible for GC if no other reference points to it
    }
}
