-- File: Data.sql
-- Date: 2021/04/04
-- Author: Luis Villalobos
USE [GYTModel]
GO
-- Insert of Countries
insert into Country(Name) values ('Costa Rica');
insert into Country(Name) values ('Mexico');
insert into Country(Name) values ('Colombia');
insert into Country(Name) values ('Estados Unidos');
insert into Country(Name) values ('Francia');
-- Insert of Countries
insert into City(Id_Country, Name) values (1, 'San Jose');
insert into City(Id_Country, Name) values (1, 'Heredia');
insert into City(Id_Country, Name) values (2, 'Ciudad Mexico');
insert into City(Id_Country, Name) values (2, 'Monterrey');
insert into City(Id_Country, Name) values (3, 'Bogota');
insert into City(Id_Country, Name) values (3, 'Medellín');
insert into City(Id_Country, Name) values (4, 'New York');
insert into City(Id_Country, Name) values (4, 'San Francisco');
insert into City(Id_Country, Name) values (5, 'Paris');
insert into City(Id_Country, Name) values (5, 'Marsella');
-- Insert of User
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(1, '1', '1', 'David', 'Villalobos', '111111111', '09-07-1999', 1)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(1, '2', '2', 'Andres', 'Gonzalez', '222222222', '12-28-2004', 0)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(1, '3', '3', 'Giancarlo', 'Alvarado', '333333333', '01-01-1998', 0)
-- Insert of Tour
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (1, 'Visita Museo Nacional', 'Museo', 'Tour guiado por el museo nacional de Costa Rica',
'04-29-2021', 20, 0, '02:00', 10.5, 4, 'Almuerzo en el Museo', 'Transporte al lugar')
-- Insert of Images
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museo\museo1.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museo\museo2.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museo\museo3.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museo\museo4.jpg', SINGLE_BLOB) as T1), 0);
-- Insert of Comments
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 1, 5, 'Buen lugar para visitar');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 2, 5, 'Sitio con cosas historicas');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 3, 3, 'Falta de zonas para comer');
-- Insert of Likes
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (1, 1);
-- Insert of Reservation ...
-- Insert of DetailReservation ... 

-- SELECTS
-- SELECT * FROM Country;
-- SELECT * FROM City;
-- SELECT * FROM Tour;
-- SELECT * FROM [User];
-- SELECT * FROM Image_Tour;
-- SELECT * FROM Comment_Tour;
