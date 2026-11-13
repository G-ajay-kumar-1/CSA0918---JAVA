# Task 9 – Report notes

Vehicle is abstract because a generic vehicle has shared identity but no single universal rate. Chargeable is an interface because charging is a capability that only some vehicles support. A class can implement multiple interfaces. Interface default methods provide shared optional behaviour; static interface methods hold related utility functions. Reversing the roles would make it harder for Vehicle to hold shared instance state and constructors because interfaces have no ordinary instance constructors or per-object instance fields. Java class inheritance is single; interfaces support multiple type inheritance. Method dispatch time is generally O(1); per-object fields are determined by the concrete class.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
