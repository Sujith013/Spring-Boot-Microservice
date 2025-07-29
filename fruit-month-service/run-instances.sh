#!/bin/bash
echo "Starting Fruit Month Service instances on multiple ports..."

echo "Starting instance on port 8000..."
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8000" &
PID1=$!

echo "Starting instance on port 8001..."
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8001" &
PID2=$!

echo "Both instances are starting..."
echo ""
echo "Test URLs:"
echo "- Port 8000: http://localhost:8000/fruit-month-price/fruit/banana/month/jan"
echo "- Port 8001: http://localhost:8001/fruit-month-price/fruit/apple/month/feb"
echo ""
echo "Press Ctrl+C to stop both instances"
echo "Process IDs: $PID1 (port 8000), $PID2 (port 8001)"

# Wait for user to stop
trap "echo 'Stopping instances...'; kill $PID1 $PID2; exit" INT
wait 