interface Chargeable {
    double chargingFee(int kWh);
    default String chargingStatus() { return "Charging capability available"; }
    static boolean validEnergy(int kWh) { return kWh >= 0; }
}
abstract class Vehicle {
    private final String regNo;
    Vehicle(String regNo) { this.regNo = regNo; }
    String getRegNo() { return regNo; }
    abstract double ratePerHour();
}
class ElectricCar extends Vehicle implements Chargeable {
    ElectricCar(String regNo) { super(regNo); }
    @Override double ratePerHour() { return 30; }
    @Override public double chargingFee(int kWh) {
        if (!Chargeable.validEnergy(kWh)) throw new IllegalArgumentException("Invalid kWh");
        return 12.0 * kWh;
    }
}
public class AbstractionDemo {
    public static void main(String[] args) {
        ElectricCar ev = new ElectricCar("TN03C3");
        System.out.println(ev.getRegNo() + " rate=" + ev.ratePerHour());
        System.out.println(ev.chargingStatus());
        System.out.println("Charging fee=" + ev.chargingFee(5));
    }
}
