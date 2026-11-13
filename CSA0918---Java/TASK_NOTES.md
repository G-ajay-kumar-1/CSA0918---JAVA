# Task 10 – Report notes

Suggested packages: parking.model for Vehicle, ParkingSlot and ParkingTicket; parking.service for allocation, billing and validation; parking.app for the entry point. Fields are private; constructors and public service methods are public; protected is reserved for subclass hooks; package-private is suitable for internal helpers. Setters validate non-negative durations and valid slot positions. Wrapper types support collections and nullable values, but Integer objects should be compared with equals rather than == because == may compare references. Encapsulation centralizes validation; validation per update is O(1), whereas public fields force repeated checks at every caller and increase defect risk.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
