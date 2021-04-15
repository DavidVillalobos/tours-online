-- File: FunctionsAndProcedures.sql
-- Date: 2021/04/04
-- Author: Luis Villalobos

USE [GYTModel]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- FUNCTION FOR FILTER TOURS BY PLACE AND DEPARTURES AND ARRIVAL
CREATE OR ALTER FUNCTION F_FilterTours
(
	@place varchar(100) = '', 
	@DepartureDate date = '01/01/2000',
	@ArrivalDate date = '12/31/9999'
)
RETURNS TABLE 
AS
RETURN 
(
	SELECT t.Id, t.Id_City, t.Name, t.Category, t.Description, t.StartDate, t.Quota, t.Reviews, t.Duration, t.Price, t.Rating , t.Includes, t.NotIncludes
	FROM Tour t inner join City ci ON t.Id_City = ci.Id inner join Country co ON ci.Id_Country = co.Id 
	WHERE (co.Name LIKE '%%'+@place+'%%' OR ci.Name LIKE '%%'+@place+'%%') AND (t.StartDate BETWEEN @DepartureDate AND @ArrivalDate) AND (t.Quota > 0)
)
GO

-- SELECT * FROM F_FilterTours(default, default, default);

-- TRIGGER FOR UPDATE THE RATING AND REVIEWS WHEN COMMENT IS INSERTED
CREATE OR ALTER TRIGGER T_Tour_Update_Rating_Reviews ON Comment_Tour 
AFTER INSERT AS 
DECLARE @last_rating float
DECLARE @new_rating float
DECLARE @last_reviews INT
DECLARE @id_tour INT
BEGIN
	SELECT @last_rating = t.Rating, @new_rating = i.Rating, @last_reviews = t.Reviews, @id_tour = i.Id_Tour FROM Tour as t JOIN INSERTED AS i ON t.Id = i.Id_Tour;
	IF(@last_reviews != 0)
		BEGIN
			UPDATE Tour SET Rating = (@last_rating + @new_rating) / 2.0 WHERE Tour.Id = @id_tour;
		END
	ELSE
		BEGIN
			UPDATE Tour SET Rating = @new_rating WHERE Tour.Id = @id_tour;
		END
	UPDATE Tour SET Reviews = @last_reviews + 1 WHERE Tour.Id = @id_tour;
END
GO
-- select * from Tour;
-- select * from Comment_Tour;



-- PROCEDURE FOR INSERT OR UPDATE THE USER
CREATE OR ALTER PROCEDURE updateInsertUser(
	@id int, 
	@id_country int,  
	@email VARCHAR(50) = '',  
	@password varchar(20) = '',  
	@name VARCHAR(30) = '',  
	@last_name VARCHAR(30) = '',
	@identification VARCHAR(20) = '',
	@birthday date = '',
	@admin Bit = '',
	@statement_type NVARCHAR(20))  
AS  
	BEGIN  
		IF @statement_type = 'Insert'  
		BEGIN  
			INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin)
			VALUES(@id_country, @email, @password, @name, @last_name, @identification, @birthday, @admin)
		END  
		ELSE IF @statement_type = 'Update'  
			BEGIN  
				UPDATE [User] 
				SET Id_Country = @id_country, Email = @email, Password = @password, 
				Name = @name, LastName = @last_name, Identification = @identification, 
				Birthday = @birthday, Admin = @admin WHERE id = @id  
			END  
	END   
GO
-- select * from [User];



-- PROCEDURE FOR INSERT OR UPDATE THE TOUR
CREATE OR ALTER PROCEDURE updateInsertTour(
	@id int,
	@id_city int,
	@name varchar(50) = '',
	@category varchar(50) = '',
	@description varchar(300) = '',
	@start_date date,
	@quota int = 0,
	@reviews int = 0,
	@duration time(0),
	@price float = 0,
	@rating float = 0,
	@includes varchar = '',
	@not_includes varchar(50) = '',
	@statement_type NVARCHAR(20))  
AS  
	BEGIN  
		IF @statement_type = 'Insert'  
		BEGIN  
			INSERT INTO Tour(Id_City, Name, Category, Description, StartDate, Quota, Reviews, 
			Duration, Price, Rating, Includes, NotIncludes) 
			VALUES(@id_city,@name, @category, @description, @start_date, @quota, @reviews, 
			@duration, @price, @rating, @includes, @not_includes)
		END  
		ELSE IF @statement_type = 'Update'  
			BEGIN  
				UPDATE Tour 
				SET Name=@name, Category=@category, Description=@description, StartDate=@start_date, 
				Quota=@quota, Reviews=@reviews, Duration=@duration, Price=@price, Rating=@rating, 
				Includes=@includes, NotIncludes=@not_includes
				WHERE Id= @id;
			END  
	END   
GO
-- select * from [Tour];