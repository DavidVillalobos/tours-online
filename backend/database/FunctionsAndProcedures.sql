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
	WHERE (co.Name LIKE '%%'+@place+'%%' OR ci.Name LIKE '%%'+@place+'%%') AND (t.StartDate BETWEEN @DepartureDate AND @ArrivalDate)
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

-- select * from Tour;
-- select * from Comment_Tour;
