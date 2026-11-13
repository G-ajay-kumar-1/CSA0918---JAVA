# Task 6 – Report notes

Vehicle is the superclass; TwoWheeler and Car are subclasses; ElectricCar extends Car and implements Chargeable. This demonstrates hierarchical and multilevel inheritance. Subclasses inherit registration identity and add specialized rate/capability. super(...) invokes the parent constructor first. Constructor trace: Vehicle -> Car -> ElectricCar. UML: Vehicle (abstract) <|-- TwoWheeler; Vehicle <|-- Car; Car <|-- ElectricCar; ElectricCar ..|> Chargeable. Composition alternative: Vehicle has a VehicleType/PricingStrategy object. Adding a new type can be done by adding a strategy rather than subclassing. Fee lookup is O(1) in either approach; per-object space includes one strategy reference in composition, while inheritance uses the runtime class identity and object header. Composition is more flexible when pricing changes independently of vehicle identity.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
