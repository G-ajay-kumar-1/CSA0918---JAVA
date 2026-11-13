# CSA09-192372300-Assignment4
**Programming in Java – Assignment 4 (CO4): Event-Driven GUI Design**

- Student: Gonugunta Ajay Kumar
- Register Number: 192372300
- Course: Programming in Java (CSA09)
- Java: JDK 17 or later recommended
- Database: MySQL 8.x (Task 6)
- IDE: VS Code / IntelliJ IDEA / Eclipse

## Repository structure
- `Task01/` – Fee-window design repair and service separation
- `Task02/` – Hospital appointment event-driven design
- `Task03/` – Campus library search GUI and resizing
- `Task04/` – Mouse-event debugging and persistent drawing
- `Task05/` – Four event-handling styles and completed lambda program
- `Task06/` – Campus event registration Swing–JDBC application
- `docs/` – Assignment report and evidence checklist
- `database/` – MySQL schema and sample data

## Requirements
1. Install JDK 17+ and verify with `java -version` and `javac -version`.
2. For Task 6, install MySQL 8+, create the database using `database/schema.sql`.
3. Add MySQL Connector/J to the classpath for Task 6. Do not commit database passwords.
4. Compile and run each task using the commands below. Run commands from the relevant task folder.

## Compile and run
### Task 1
```bash
cd Task01
javac *.java
java FeeWindow
```
Task 1 demo uses a service-layer validation example. Database integration is intentionally isolated; Task 6 demonstrates JDBC.

### Task 3
```bash
cd Task03
javac LibrarySearchUI.java
java LibrarySearchUI
```

### Task 4
```bash
cd Task04
javac DrawPad.java
java DrawPad
```

### Task 5
```bash
cd Task05
javac -d out src/*.java
java -cp out ConverterFrameListener
java -cp out ConverterNamedInner
java -cp out ConverterAnonymous
java -cp out ConverterLambda
```

### Task 6
Set environment variables `DB_URL`, `DB_USER`, and `DB_PASSWORD`, and place MySQL Connector/J on the classpath.
```bash
cd Task06
javac -cp ".;lib/mysql-connector-j.jar" -d out src/*.java
java -cp "out;lib/mysql-connector-j.jar" EventRegistrationApp
```
On macOS/Linux replace `;` in the classpath with `:`. Never hard-code real credentials in source code.

## Evidence to capture yourself
Save genuine screenshots of your own executions in each task's `screenshots/` folder. Use the checklist in `docs/EVIDENCE_CHECKLIST.md`. Do not submit sample/mock screenshots as execution proof.

## Task results
- Task 1: Identifies event-handling, validation, threading, SQL security, feedback and resource-management problems; separates UI from service logic.
- Task 2: Models event sources, event objects, listeners, containment hierarchy, handler pseudocode and window lifecycle.
- Task 3: Uses nested Swing panels and BorderLayout/FlowLayout so the results area expands on resize.
- Task 4: Correctly overrides `mouseClicked`, updates the click count and redraws stored points in `paint`.
- Task 5: Demonstrates frame listener, named inner class, anonymous inner class and lambda approaches.
- Task 6: Provides a layered Swing/JDBC design with input validation, duplicate protection, paginated loading and background database work.

## Submission reminder
Add the GitHub URL to the report cover page. Share the repository with faculty. Commit progressively: at least one meaningful commit per task. Include the final report, SQL script and real screenshots. Do not commit `.class`, `out/`, passwords, or temporary files.
