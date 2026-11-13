# Task 2 – Report notes

Use int for floor/slot/count/minutes, String for registration, LocalDateTime for timestamps, long for elapsed minutes, and double or BigDecimal for currency (BigDecimal is preferable in a production billing system). A jagged String[][] represents floors with different slot counts. Use Scanner for console input. Integer division 95/60 gives 1, which undercharges if every started hour is billed; ceiling division for non-negative minutes is (minutes+59)/60. Jagged arrays use O(total slots) space and slot lookup by known indexes is O(1). A flattened one-dimensional array needs a prefix-offset mapping when floors differ in size; a fixed slotsPerFloor formula is invalid for unequal floors. ArrayList<String[]> retains per-floor sizing with dynamic outer-list growth.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
