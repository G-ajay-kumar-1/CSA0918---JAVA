# Task 11 – Report notes

The integrated program uses a jagged array for slots and a HashMap keyed by registration number for active tickets. Normal tests include different vehicle types, exits and monthly-pass users. Boundary tests should include zero minutes, 60 minutes, 61 minutes, the last free slot and full capacity. Invalid tests should include duplicate registration, unknown/missing ticket and invalid slot. For a hand calculation with 65-minute visitor two-wheeler parking: ceil(65/60)=2 hours; at Rs 10/hour the fee is Rs 20. For a 120-minute student car with valid monthly pass, fee is Rs 0. HashMap lookup is average O(1), worst-case O(n); array lookup is O(n); ArrayList lookup is O(n). All store O(n) active tickets, with map/list metadata overhead. Record actual output screenshots after running locally.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
