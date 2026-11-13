# Task 12 – Report notes

Changed requirements: e-bikes have a flat fee, delivery vans are charged at double the car rate during 09:00–17:00, visitors may buy a one-day pass, and the facility grows to five unequal floors. The jagged array already supports unequal floor sizes. A PricingRule abstraction or overridden fee method keeps rates out of allocation logic. OneDayPass implements Pass and is valid only for a visitor on its purchased date. The sample implementation assumes e-bike flat fee Rs 20 and car rate Rs 30/hour because the assignment does not specify amounts; confirm or replace these assumptions with faculty-approved rates. Delivery van billing in the sample is prorated by time segments; if faculty expects each started hour to be rounded up, adjust and document that rule. Defend the design by reporting how many existing classes changed, tests for new categories, extension effort and trade-offs.

## Evidence to add
Run the corresponding Java program in VS Code and save a genuine terminal screenshot in this folder. Add your actual observed output to the report after execution.
