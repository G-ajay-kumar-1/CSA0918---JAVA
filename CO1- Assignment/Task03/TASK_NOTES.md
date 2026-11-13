# Task 3 – Report notes

Allocation pseudocode: FOR each floor; FOR each slot; IF slot is empty, store registration and RETURN floor/slot; END loops; RETURN no-space. Release pseudocode: validate floor; validate slot against that floor; if empty return false; otherwise set null and return true. Fee pseudocode: validate duration; compute billable hours with ceiling division; choose rate/slab; apply eligible pass; return fee. The provided ParkingFacility program is completed and its expected output is the sequence [0,0], [0,1], [1,0], null, true false false, [0,1]. Scan allocation is O(T) worst case where T is total slots; release is O(1). A stack/list of free positions can make allocation/release amortized O(1), with O(T) extra storage for free-position records and care needed to prevent duplicate stack entries.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
