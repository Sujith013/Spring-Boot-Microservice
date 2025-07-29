@echo off
echo Starting Fruit Month Service instances on multiple ports...

echo Starting instance on port 8000...
start "Fruit Month Service - Port 8000" cmd /k "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"-Dserver.port=8000\""

echo Starting instance on port 8001...
start "Fruit Month Service - Port 8001" cmd /k "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"-Dserver.port=8001\""

echo Both instances are starting...
echo.
echo Test URLs:
echo - Port 8000: http://localhost:8000/fruit-month-price/fruit/banana/month/jan
echo - Port 8001: http://localhost:8001/fruit-month-price/fruit/apple/month/feb
echo.
pause 