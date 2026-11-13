class Vehicle {
    protected final String registrationNumber;
    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        System.out.println("Vehicle constructor");
    }
    double ratePerHour() { return 0; }
}
class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
        System.out.println("Car constructor");
    }
    @Override double ratePerHour() { return 30; }
}
class ElectricCar extends Car {
    ElectricCar(String registrationNumber) {
        super(registrationNumber);
        System.out.println("ElectricCar constructor");
    }
}
public class InheritanceDemo {
    public static void main(String[] args) {
        ElectricCar ev = new ElectricCar("TN03C3");
        System.out.println("Rate: " + ev.ratePerHour());
    }
}
