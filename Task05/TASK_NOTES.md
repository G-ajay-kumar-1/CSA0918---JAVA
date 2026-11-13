# Task 5 – Report notes

The ticket sequence is class-wide and therefore static. Per-ticket entry time and registration remain instance fields. Static methods can expose the current ticket sequence or validate class-wide configuration; a static initializer is appropriate only for fixed shared setup. After exit, a ticket is eligible for garbage collection only when no reachable reference points to it. With one static counter, storage is O(1) regardless of k facilities and n tickets. An instance counter uses O(k) counters for k facilities and can generate duplicate IDs across facilities unless facility identity is included in the ticket key.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
