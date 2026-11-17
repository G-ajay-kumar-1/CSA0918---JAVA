@echo off
setlocal
echo Compile file-I/O programs (JDK 11+ recommended)
if not exist out mkdir out
javac -encoding UTF-8 -d out Q_B1\*.java
if errorlevel 1 exit /b 1
echo File-I/O sources compiled. Run using: java -cp out BusPassApp
