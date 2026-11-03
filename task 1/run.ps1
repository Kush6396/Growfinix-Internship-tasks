Write-Host "===================================================" -ForegroundColor Cyan
Write-Host "    Compiling Java Fundamentals Mini-Projects...    " -ForegroundColor Cyan
Write-Host "===================================================" -ForegroundColor Cyan

if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

$javacCmd = Get-Command javac -ErrorAction SilentlyContinue
if (-not $javacCmd) {
    if (Test-Path "C:\Program Files\Java\jdk-23\bin\javac.exe") {
        $javacPath = "C:\Program Files\Java\jdk-23\bin\javac.exe"
        $javaPath = "C:\Program Files\Java\jdk-23\bin\java.exe"
    } else {
        Write-Host "[ERROR] javac not found in PATH or standard directory." -ForegroundColor Red
        Exit 1
    }
} else {
    $javacPath = "javac"
    $javaPath = "java"
}

& $javacPath -d bin (Get-ChildItem src\com\taskone\*.java)
if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Compilation failed!" -ForegroundColor Red
    Exit $LASTEXITCODE
}

Write-Host "[SUCCESS] Compilation complete. Launching Dashboard...`n" -ForegroundColor Green
& $javaPath -cp bin com.taskone.Main
