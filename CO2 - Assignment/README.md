# CO2 – Assignment 2 (Java Collections)
Student: Gonugunta Ajay Kumar | Register No: 192372300
## Requirements
JDK 17 or later. Open this folder in VS Code. Install the Java Extension Pack.
## Run
Open a terminal in the project folder, then:
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out q1.StringBenchmark
```
Change the class after `-cp out` to run another question:
`q2.ListComparison`, `q3.StudentSetDemo`, `q4.MapComparison`,
`q5.LeaderboardDemo`, `q6.HospitalTriage`, `q7.RedesignBenchmark`.
Benchmark programs create CSV files in the project folder. Run them several times and retain your own results.
