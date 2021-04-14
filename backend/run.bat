@echo off
REM Run project
IF EXIST msedge.exe msedge http://localhost:8001
java -jar build/libs/backend-1.0-SNAPSHOT.jar