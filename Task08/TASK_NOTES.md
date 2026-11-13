# Task 8 – Report notes

Overloading is compile-time polymorphism, e.g. calculateFee(int minutes) and calculateFee(LocalDateTime in, LocalDateTime out). Runtime polymorphism occurs when Vehicle v refers to an ElectricCar and v.calculateFee(...) invokes the most specific override. The declared reference type controls accessible methods; the actual object type selects overridden implementation. FeeDemo output expected by the assignment: TwoWheeler TN01A1 -> Rs 30.0; Car TN02B2 -> Rs 90.0; ElectricCar TN03C3 -> Rs 210.0; true. The last true follows because equals compares registration only; this is a design choice and may be too broad if vehicle category must be part of identity. Dynamic dispatch is effectively O(1); repeated instanceof branches can require up to k checks and make extensions harder.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
