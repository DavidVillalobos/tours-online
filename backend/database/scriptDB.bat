@ECHO off

ECHO Drop Model
sqlcmd -S DESKTOP-UPQBQ3T\SQLEXPRESS -i C:\Users\luisd\repos\tours_online\backend\database\DropModel.sql
ECHO Create Model
sqlcmd -S DESKTOP-UPQBQ3T\SQLEXPRESS -i C:\Users\luisd\repos\tours_online\backend\database\CreateModel.sql
ECHO Create Funcions and Procedures
sqlcmd -S DESKTOP-UPQBQ3T\SQLEXPRESS -i C:\Users\luisd\repos\tours_online\backend\database\FunctionsAndProcedures.sql
ECHO Insert Data
sqlcmd -S DESKTOP-UPQBQ3T\SQLEXPRESS -i C:\Users\luisd\repos\tours_online\backend\database\Data.sql
