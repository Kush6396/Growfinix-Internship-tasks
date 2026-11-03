@echo off
setlocal
echo ===================================================
echo     Compiling Java Fundamentals Mini-Projects...
echo ===================================================

if not exist bin mkdir bin

javac -d bin src\com\taskone\*.java
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed! Please ensure JDK is installed and in PATH.
    pause
    exit /b %ERRORLEVEL%
)

echo [SUCCESS] Compilation complete. Launching Application...
echo.
java -cp bin com.taskone.Main
pause
