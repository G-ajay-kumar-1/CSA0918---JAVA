import java.time.*;
import java.util.*;

/**
 * Day-4 extension. Rates are illustrative assumptions because the assignment
 * does not prescribe exact amounts for e-bikes, vans or one-day passes.
 */
public class ExtendedCampusParkingSystem {
    enum UserType { STUDENT, STAFF, VISITOR }
    interface Pass { boolean isValidFor(UserType userType, LocalDate date); }
    static class OneDayPass implements Pass {
        private final LocalDate validDate;
        OneDayPass(LocalDate validDate) { this.validDate = validDate; }
        public boolean isValidFor(UserType userType, LocalDate date) {
            return userType == UserType.VISITOR && validDate.equals(date);
        }
    }
    static abstract class Vehicle {
        private final String reg;
        Vehicle(String reg) {
            if (reg==null || reg.isBlank()) throw new IllegalArgumentException("Registration required");
            this.reg=reg;
        }
        String getReg() { return reg; }
        abstract double calculateFee(LocalDateTime in, LocalDateTime out);
    }
    static class Car extends Vehicle {
        Car(String reg) { super(reg); }
        double calculateFee(LocalDateTime in, LocalDateTime out) {
            long mins=Math.max(0,Duration.between(in,out).toMinutes());
            return (mins==0?0:(mins+59)/60)*30.0;
        }
    }
    static class ElectricBike extends Vehicle {
        ElectricBike(String reg) { super(reg); }
        // Assumption: flat parking fee Rs 20 per visit.
        double calculateFee(LocalDateTime in, LocalDateTime out) { return 20.0; }
    }
    static class DeliveryVan extends Vehicle {
        DeliveryVan(String reg) { super(reg); }
        // Double the car rate for each started hour overlapping 09:00–17:00;
        // ordinary car rate outside that window. Hourly segments are counted.
        double calculateFee(LocalDateTime in, LocalDateTime out) {
            if (out.isBefore(in)) throw new IllegalArgumentException("Exit before entry");
            double total=0;
            LocalDateTime cursor=in;
            while (cursor.isBefore(out)) {
                LocalDateTime next=cursor.plusHours(1);
                if (next.isAfter(out)) next=out;
                LocalTime time=cursor.toLocalTime();
                boolean peak=!time.isBefore(LocalTime.of(9,0)) && time.isBefore(LocalTime.of(17,0));
                long minutes=Duration.between(cursor,next).toMinutes();
                double fraction=minutes/60.0;
                total += fraction * 30.0 * (peak?2:1);
                cursor=next;
            }
            return total;
        }
    }
    static class Facility {
        private final String[][] slots;
        Facility(int[] perFloor) {
            slots=new String[perFloor.length][];
            for(int i=0;i<perFloor.length;i++) slots[i]=new String[perFloor[i]];
        }
        int[] allocate(String reg) {
            for(int f=0;f<slots.length;f++) for(int s=0;s<slots[f].length;s++)
                if(slots[f][s]==null){slots[f][s]=reg;return new int[]{f,s};}
            return null;
        }
        void release(int f,int s){slots[f][s]=null;}
    }
    static class Ticket {
        private static int next=2000;
        final int id=++next;
        final Vehicle vehicle; final int floor,slot;
        final LocalDateTime entry; final UserType user; final Pass pass;
        Ticket(Vehicle v,int f,int s,LocalDateTime e,UserType u,Pass p){
            vehicle=v;floor=f;slot=s;entry=e;user=u;pass=p;
        }
    }
    private final Facility facility;
    private final Map<String,Ticket> active=new HashMap<>();
    ExtendedCampusParkingSystem(int[] perFloor){facility=new Facility(perFloor);}
    Ticket enter(Vehicle v,UserType user,Pass pass,LocalDateTime time){
        if(active.containsKey(v.getReg())) throw new IllegalArgumentException("Duplicate registration");
        int[] p=facility.allocate(v.getReg()); if(p==null)return null;
        Ticket t=new Ticket(v,p[0],p[1],time,user,pass);active.put(v.getReg(),t);return t;
    }
    double exit(String reg,LocalDateTime time){
        Ticket t=active.remove(reg);if(t==null)throw new IllegalArgumentException("Ticket not found");
        double fee=t.vehicle.calculateFee(t.entry,time);
        if(t.pass!=null && t.pass.isValidFor(t.user,t.entry.toLocalDate()))fee=0;
        facility.release(t.floor,t.slot);return fee;
    }
    public static void main(String[] args){
        // Different slot counts across five floors demonstrate jagged-array support.
        ExtendedCampusParkingSystem p=new ExtendedCampusParkingSystem(new int[]{10,12,8,15,9});
        LocalDateTime entry=LocalDateTime.of(2026,11,12,10,0);
        p.enter(new ElectricBike("EB01"),UserType.VISITOR,null,entry);
        p.enter(new DeliveryVan("DV01"),UserType.VISITOR,null,entry);
        p.enter(new Car("VC01"),UserType.VISITOR,new OneDayPass(entry.toLocalDate()),entry);
        System.out.println("E-bike flat fee: Rs "+p.exit("EB01",entry.plusHours(3)));
        System.out.println("Delivery van fee: Rs "+p.exit("DV01",entry.plusHours(2)));
        System.out.println("Visitor one-day-pass fee: Rs "+p.exit("VC01",entry.plusHours(4)));
    }
}
