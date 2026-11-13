abstract class FeeVehicle {
    private final String registrationNumber;
    FeeVehicle(String registrationNumber) { this.registrationNumber = registrationNumber; }
    String getRegistrationNumber() { return registrationNumber; }
    abstract double ratePerHour();
    double calculateFee(int minutes) {
        int hours = minutes <= 0 ? 0 : (minutes + 59) / 60;
        return hours * ratePerHour();
    }
    @Override public boolean equals(Object obj) {
        if (!(obj instanceof FeeVehicle)) return false;
        return registrationNumber.equals(((FeeVehicle)obj).registrationNumber);
    }
    @Override public int hashCode() { return registrationNumber.hashCode(); }
    @Override public String toString() { return getClass().getSimpleName() + "[" + registrationNumber + "]"; }
}
class FeeCar extends FeeVehicle {
    FeeCar(String reg) { super(reg); }
    @Override double ratePerHour() { return 30; }
}
class FeeBike extends FeeVehicle {
    FeeBike(String reg) { super(reg); }
    @Override double ratePerHour() { return 10; }
}
public class OverridingDemo {
    public static void main(String[] args) {
        FeeVehicle car = new FeeCar("TN02B2");
        System.out.println(car + " fee for 130 minutes = Rs " + car.calculateFee(130));
        System.out.println(new FeeCar("TN02B2").equals(new FeeBike("TN02B2")));
    }
}
