$ErrorActionPreference = "Stop"

if (-not $env:JAVA_HOME -or -not (Test-Path $env:JAVA_HOME)) {
    $javaCmd = Get-Command java -ErrorAction SilentlyContinue
    if (-not $javaCmd) {
        Write-Error "Java not found. Install JDK 17+ and set JAVA_HOME."
        exit 1
    }

    $settings = cmd /c "java -XshowSettings:properties -version 2>&1"
    $javaHomeLine = $settings | Where-Object { $_ -match "^\s*java\.home\s*=" } | Select-Object -First 1
    if (-not $javaHomeLine) {
        Write-Error "Cannot detect JAVA_HOME automatically. Set JAVA_HOME manually."
        exit 1
    }

    $javaHome = ($javaHomeLine -split "=", 2)[1].Trim()
    if (-not (Test-Path $javaHome)) {
        Write-Error "Detected JAVA_HOME is invalid: $javaHome"
        exit 1
    }

    $env:JAVA_HOME = $javaHome
    Write-Host "JAVA_HOME set to: $env:JAVA_HOME"
}

& .\mvnw.cmd spring-boot:run
