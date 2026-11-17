# CO5 – Assignment 5: Data Persistence Engineering
Student: Gonugunta Ajay Kumar | Register No: 192372300
JDK 17+. Part A pseudocode and design notes are in `PartA/PartA_Design.md`.
Compile/run the file and buffering programs:
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out qB1.BusPassApp
java -cp out qB1.LineCounter
```
JDBC tasks use MySQL. Create the schema with `sql/schema.sql`, add MySQL Connector/J to the VS Code project classpath, and set environment variables `DB_URL`, `DB_USER`, `DB_PASSWORD`. Never commit real credentials.
