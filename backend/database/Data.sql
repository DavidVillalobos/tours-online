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
VALUES(1, 'admin@hotmail.com', 'admin1234!', 'Juan', 'Perez',  '111111111', '09-07-1999', 1)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(2, '2', 'Andres1234!', 'Andres', 'Gonzalez Villalobos', '222222222', '12-28-2004', 0)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(3, '3', 'Giancarlo1234!', 'Giancarlo', 'Alvarado Sanchez', '333333333', '01-01-1998', 0)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(3, '4', 'Maria1234!', 'Maria', 'Arias Rodriguez', '4444444444', '01-01-2000', 0)
-- Insert of Tour
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (1, 'Visita Museo Nacional', 'Museo', 'Tour guiado por el museo nacional de Costa Rica',
'04-29-2021', 20, 0, '02:00', 10.5, 0, 'Almuerzo en el Museo', 'Transporte al lugar');
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (9, 'Visita al Museo de Louvre', 'Museo', 'Tour guiado con un historiador atraves del museo de louvre en paris',
'05-14-2021', 18, 0, '03:45', 15.5, 0, 'Transporte al lugar y fotos en la plaza', 'Alimentacion');

-- Insert of Images
-- Museo nacional
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museonacional\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museonacional\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museonacional\C.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museonacional\D.jpg', SINGLE_BLOB) as T1), 0);
-- Museo louvre
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (2, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museolouvre\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (2, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museolouvre\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (2, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\museolouvre\C.jpg', SINGLE_BLOB) as T1), 0);

-- Insert of Comments
-- Museo Nacional
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 1, 4.5, 'Buen lugar para visitar en familia');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 2, 4, 'Sitio con cosas historicas sobre el pais');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 3, 3.5, 'No hay zonas para comer');
-- Museo Louvre
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (2, 3, 4.9, 'Hermosas pinturas historicas');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (2, 2, 3.5, 'La entrada es algo costosa');
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
-- SELECT * FROM Reservation_Tour;
-- SELECT * FROM Detail_Reservation_Tour;
