# Programming in Java – CO1 Assignment 1
## Campus Smart-Parking System

**Student:** Gonugunta Ajay Kumar  
**Register Number:** 192372300  
**GitHub:** https://github.com/G-ajay-kumar-1/CSA0918---Java  
**Recommended Java version:** JDK 17 or later

## Repository structure
- `Task01` to `Task12`: one folder per assignment task.
- Each task folder contains Java source and a short task note.
- `report/`: report-ready content and diagrams.
- `evidence/`: place your genuine VS Code terminal screenshots here after running the programs.

## Run in VS Code
1. Install JDK 17 or later and the Java Extension Pack.
2. Open this entire folder in VS Code.
3. Open the terminal in the folder of the program you want to run.
4. Compile and run, for example:
   ```powershell
   javac Task03/ParkingFacility.java
   java -cp Task03 ParkingFacility
   ```
5. For a task with a single source file, replace the path and class name accordingly.
6. For the package example in Task10:
   ```powershell
   javac -d out Task10/parking/model/ParkingSlot.java Task10/EncapsulationDemo.java
   java -cp out EncapsulationDemo
   ```
7. For Task11:
   ```powershell
   javac Task11/CampusParkingSystem.java
   java -cp Task11 CampusParkingSystem
   ```
8. For Task12:
   ```powershell
   javac Task12/ExtendedCampusParkingSystem.java
   java -cp Task12 ExtendedCampusParkingSystem
   ```

## Important academic note
Run every program yourself in VS Code. Save screenshots of your own successful execution in the corresponding task folder or in `evidence/`. Do not submit placeholder screenshots as execution evidence. Check the actual output on your computer and record any differences.

## Suggested Git workflow
Make a meaningful commit after each task:
```bash
git add Task01
git commit -m "Complete Task 01 problem analysis and object model"
```
Repeat for each task. Before final submission:
```bash
git status
git log --oneline
git remote -v
```

## Assignment scope
The implementation models a multi-floor campus parking facility with two-wheelers, cars, electric cars, tickets, fee calculation, monthly passes, and the Day-4 extension for e-bikes, delivery vans and visitor one-day passes.
