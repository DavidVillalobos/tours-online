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
	SELECT t.Id, t.Id_City, t.Name, t.Category, t.Description, t.StartDate, t.Quota, t.Reviews, t.Duration, t.Price, t.Rating 
	FROM Tour t inner join City ci ON t.Id_City = ci.Id inner join Country co ON ci.Id_Country = co.Id 
	WHERE (co.Name LIKE '%%'+@place+'%%' OR ci.Name LIKE '%%'+@place+'%%') AND (t.StartDate BETWEEN @DepartureDate AND @ArrivalDate)
)
GO

