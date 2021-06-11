-- File: Data.sql
-- Date: 2021/06/11
-- Author: Luis Villalobos
USE [GYTModel]

GO
-- Insert of Countries
insert into Country(Name) values ('Costa Rica');
insert into Country(Name) values ('Mexico');
insert into Country(Name) values ('Colombia');
insert into Country(Name) values ('Estados Unidos');
insert into Country(Name) values ('Francia');
insert into Country(Name) values ('Italia');
insert into Country(Name) values ('Otro');
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
insert into City(Id_Country, Name) values (6, 'Roma');
insert into City(Id_Country, Name) values (6, 'Venecia');
-- Insert of User
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(1, 'admin@hotmail.com', 'Admin1234!', 'Juan', 'Perez',  '111111111', '09-07-1999', 1)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(2, 'andres@hotmail.com', 'Andres1234!', 'Andres', 'Gonzalez Villalobos', '222222222', '12-28-2004', 0)
INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin) 
VALUES(3, 'maria@hotmail.com', 'Maria1234!', 'Maria', 'Arias Rodriguez', '4444444444', '01-01-2000', 0)
-- Insert of Tour
-- Museo nacional
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (1, 'Visita Museo Nacional', 'Museo', 'Tour guiado por el museo nacional de Costa Rica',
'2021-07-10', 20, 0, '02:00', 10.5, 0, 'Almuerzo en el Museo', 'Transporte al lugar');
-- Museo Louvre
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (9, 'Visita al Museo de Louvre', 'Museo', 'Tour guiado con un historiador atraves del museo de louvre en paris',
'2021-07-14', 18, 0, '03:45', 15.5, 0, 'Transporte al lugar y fotos en la plaza', 'Alimentacion');
-- bicicleta Bogota
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (5, 'Paseo en bicicleta por bogota', 'Paseo bicicleta', 'Paseo en bicicleta guiado atraves del centro de bogota',
'2021-07-03', 20, 0, '02:30', 5.5, 0, 'Alimentacion y refrigerio', 'Bicicleta, debe traer su bicicleta');
-- Coliseo romano
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (11, 'Tour coliseo romano', 'Tour Coliseo', 'Tour guiado atraves del coliseo romano',
'2021-07-10', 25, 0, '02:00', 10.5, 0, 'Alimentacion', 'Transporte al lugar');
-- Piramides Teotihuacan
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (3, 'Piramides de Teotihuacan', 'Piramides', 'Paseo por las piramides de teotihuacan',
'2021-07-03', 30, 0, '03:30', 8.3, 0, 'Transporte al lugar', 'Alimentacion');
-- Central Park
INSERT INTO [Tour](Id_City, Name, Category, Description, StartDate, Quota, 
Reviews, Duration, Price, Rating, Includes, NotIncludes) 
VALUES (7, 'Recorrido por central park', 'Paseo', 'Paseo guiado por central park',
'2021-07-08', 20, 0, '01:30', 6.5, 0, 'Refrigerio al medio dia', 'Transporte al lugar');

-- Insert of Images
-- Museo nacional
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\costarica\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\costarica\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\costarica\C.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (1, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\costarica\D.jpg', SINGLE_BLOB) as T1), 0);
-- Museo louvre
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (2, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\francia\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (2, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\francia\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (2, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\francia\C.jpg', SINGLE_BLOB) as T1), 0);
-- Paseo Bogota
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (3, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\colombia\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (3, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\colombia\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (3, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\colombia\C.jpg', SINGLE_BLOB) as T1), 0);
-- Coliseo Romano
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (4, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\italia\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (4, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\italia\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (4, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\italia\C.jpg', SINGLE_BLOB) as T1), 0);
-- Piramides Teotihuacan
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (5, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\mexico\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (5, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\mexico\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (5, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\mexico\C.jpg', SINGLE_BLOB) as T1), 0);
-- Central Park
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (6, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\estadosunidos\A.jpg', SINGLE_BLOB) as T1), 1);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (6, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\estadosunidos\B.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (6, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\estadosunidos\C.jpg', SINGLE_BLOB) as T1), 0);
INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES (6, 
(SELECT * FROM OPENROWSET(BULK N'C:\img\estadosunidos\D.jpg', SINGLE_BLOB) as T1), 0);

-- Insert of Comments
-- Costa Rica
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 1, 4.5, 'Buen lugar para visitar en familia');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 2, 4, 'Sitio con cosas historicas sobre el pais');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (1, 3, 3.5, 'No hay zonas para comer');
-- Francia
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (2, 3, 4.9, 'Hermosas pinturas historicas');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (2, 2, 3.5, 'La entrada es algo costosa');
-- Colombia
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (3, 3, 4.0, 'Agradable tour');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (3, 2, 3.0, 'El refrigerio es bueno');
-- Italia
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (4, 3, 5, 'Antigua roma es excelente');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (4, 2, 3, 'La entrada es algo costosa');
-- Mexico
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (5, 3, 4, 'Senderos geniales');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (5, 2, 4, 'Historia interesante');
-- Central Park
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (6, 3, 3, 'El refrigerio es poco');
INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES (6, 2, 4.5, 'El guia del tour es amable');
-- Insert of Likes
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (1, 1);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (1, 2);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (2, 3);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (2, 1);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (3, 2);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (3, 3);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (4, 1);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (4, 2);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (5, 3);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (5, 1);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (6, 2);
INSERT INTO Like_Tour(Id_Tour, Id_User) VALUES (6, 3);

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