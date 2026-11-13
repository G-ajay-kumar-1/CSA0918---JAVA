# Task 4 – Report notes

ParkingTicket uses a parameterized constructor to establish required state, a no-argument constructor can delegate using this(...), and a copy constructor should copy ticket data only if a new unique ticket ID is issued. Constructor chaining avoids duplicate validation. Factory methods can name creation intent and validate before returning. Setter-based construction may temporarily expose an invalid object. Creating n objects is O(n) total time and O(n) object storage for all approaches; the principal difference is validation safety and maintainability, not asymptotic complexity.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
