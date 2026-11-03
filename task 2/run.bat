@echo off
echo ========================================================
echo Compiling Student and Course OOP Management System...
echo ========================================================
if not exist "bin" mkdir bin
javac -d bin src\com\studentmanagement\exception\*.java src\com\studentmanagement\model\*.java src\com\studentmanagement\service\*.java src\com\studentmanagement\app\*.java
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed! Please ensure Java JDK is installed and in your PATH.
    pause
    exit /b %ERRORLEVEL%
)
echo Build successful! Launching Interactive System...
echo ========================================================
java -cp bin com.studentmanagement.app.Main
pause
