@echo off
setlocal
for %%F in (Q1 Q2 Q3 Q4 Q5 Q6 Q7) do (
  echo ==== Compiling %%F ====
  if not exist out\%%F mkdir out\%%F
  javac -encoding UTF-8 -d out\%%F %%F\*.java
  if errorlevel 1 exit /b 1
)
echo All CO3 source files compiled successfully.
