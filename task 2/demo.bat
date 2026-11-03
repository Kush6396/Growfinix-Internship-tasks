@echo off
echo ========================================================
echo Running Automated OOP Principles Deep Dive Showcase...
echo ========================================================
if not exist "bin\com\studentmanagement\app\Main.class" (
    echo Compiling source files first...
    if not exist "bin" mkdir bin
    javac -d bin src\com\studentmanagement\exception\*.java src\com\studentmanagement\model\*.java src\com\studentmanagement\service\*.java src\com\studentmanagement\app\*.java
)
java -cp bin com.studentmanagement.app.Main --demo
pause
