@echo off
setlocal
echo ===================================================
echo     Student Management System - Test Suite Runner
echo ===================================================

if exist "C:\Program Files\Java\jdk-23\bin\java.exe" (
    set "JAVA_CMD=C:\Program Files\Java\jdk-23\bin\java.exe"
    set "JAVAC_CMD=C:\Program Files\Java\jdk-23\bin\javac.exe"
) else if defined JAVA_HOME (
    set "JAVA_CMD=%JAVA_HOME%\bin\java.exe"
    set "JAVAC_CMD=%JAVA_HOME%\bin\javac.exe"
) else (
    set "JAVA_CMD=java"
    set "JAVAC_CMD=javac"
)

if not exist bin (
    mkdir bin
)

echo Compiling Java source files...
"%JAVAC_CMD%" -d bin src\com\sms\model\*.java src\com\sms\exception\*.java src\com\sms\util\*.java src\com\sms\service\*.java src\com\sms\test\*.java src\com\sms\*.java
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo Executing Automated Test Suite...
"%JAVA_CMD%" -cp bin com.sms.test.StudentManagerTestSuite

endlocal
