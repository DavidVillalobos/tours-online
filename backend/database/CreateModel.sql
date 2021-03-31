-- File: CreateModel.sql
-- Date: 2021/03/30
-- Author: Luis Villalobos


USE [master]
GO
/* CREANDO LA BASE GYTModel */
CREATE DATABASE [GYTModel]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'GYTModel_dat', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\GYTModel.mdf' , SIZE = 10240KB , MAXSIZE = 51200KB , FILEGROWTH = 5120KB )
 LOG ON 
( NAME = N'GYTModel_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\GYTModellog.ldf' , SIZE = 5120KB , MAXSIZE = 25600KB , FILEGROWTH = 5120KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [GYTModel] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [GYTModel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [GYTModel] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [GYTModel] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [GYTModel] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [GYTModel] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [GYTModel] SET ARITHABORT OFF 
GO
ALTER DATABASE [GYTModel] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [GYTModel] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [GYTModel] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [GYTModel] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [GYTModel] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [GYTModel] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [GYTModel] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [GYTModel] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [GYTModel] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [GYTModel] SET  ENABLE_BROKER 
GO
ALTER DATABASE [GYTModel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [GYTModel] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [GYTModel] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [GYTModel] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [GYTModel] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [GYTModel] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [GYTModel] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [GYTModel] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [GYTModel] SET  MULTI_USER 
GO
ALTER DATABASE [GYTModel] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [GYTModel] SET DB_CHAINING OFF 
GO
ALTER DATABASE [GYTModel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [GYTModel] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [GYTModel] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [GYTModel] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [GYTModel] SET QUERY_STORE = OFF
GO
USE [GYTModel]
GO

/* COMENTARIOS DE TOUR */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment_Tour](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_Tour] [int] NOT NULL,
	[Id_User] [int] NOT NULL,
	[Rating] [tinyint] NOT NULL,
	[Description] [varchar](200) NULL,
 CONSTRAINT check_Rating_Comment CHECK ([Rating] >= 0 AND [Rating] <= 5),
 CONSTRAINT [PK_Comment_Tour] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/* PAIS */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Country](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Country] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/* CIUDAD */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[City](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_Country] [int] NOT NULL,
	[Name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_City] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/* IMAGEN DE TOUR */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image_Tour](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_Tour] [int] NOT NULL,
	[Photo] [varbinary](max) NOT NULL,
 CONSTRAINT [PK_Image_Tour] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

/* ME GUSTA DE TOURS*/

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Like_Tour](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_User] [int] NOT NULL,
	[Id_Tour] [int] NOT NULL,
 CONSTRAINT [PK_Like_Tour] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/* RESERVACION DE TOUR */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reservation_Tour](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_User] [int] NOT NULL,
	[SubTotal] [float] NOT NULL,
	[Total] [float] NOT NULL,
 CONSTRAINT [PK_Reservation_Tour] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/* DETALLE RESERVACION DE TOUR */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Detail_Reservation_Tour](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_Reservation_Tour] [int] NOT NULL,
	[Id_Tour] [int] NOT NULL,
	[Tickets] [int] NOT NULL,
 CONSTRAINT [PK_Detail_Reservation_Tour] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/* TOUR */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tour](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_Country] [int] NULL,
	[Name] [varchar](50) NOT NULL,
	[Category] [varchar](50) NULL,
	[Description] [varchar](300) NOT NULL,
	[Quota] [int] NOT NULL,
	[Duration] [time](0) NULL,
	[Price] [float] NOT NULL,
	[Rating] [tinyint] NULL,
	[Includes] [varchar](50) NULL,
	[NotIncludes] [varchar](50) NULL,
 CONSTRAINT check_Rating_Tour CHECK ([Rating] >= 0 AND [Rating] <= 5),
 CONSTRAINT [PK_Tour] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/* USUARIO */

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Id_Country] [int] NULL,
	[Email] [varchar](50) NOT NULL,
	[Password] [varchar](20) NOT NULL,
	[Name] [nchar](30) NOT NULL,
	[LastName] [varchar](30) NOT NULL,
	[Identification] [int] NULL,
	[Birthday] [date] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


-- CHECK CONSTRAINT FOR FOREIGN KEY
ALTER TABLE [dbo].[Comment_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Tour] FOREIGN KEY([Id_Tour])
REFERENCES [dbo].[Tour] ([Id])
GO
ALTER TABLE [dbo].[Comment_Tour] CHECK CONSTRAINT [FK_Comment_Tour]
GO
ALTER TABLE [dbo].[Comment_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Comment_User] FOREIGN KEY([Id_User])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Comment_Tour] CHECK CONSTRAINT [FK_Comment_User]
GO
ALTER TABLE [dbo].[Image_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Image_Tour] FOREIGN KEY([Id_Tour])
REFERENCES [dbo].[Tour] ([Id])
GO
ALTER TABLE [dbo].[Image_Tour] CHECK CONSTRAINT [FK_Image_Tour]
GO
ALTER TABLE [dbo].[Like_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Like_Tour] FOREIGN KEY([Id_Tour])
REFERENCES [dbo].[Tour] ([Id])
GO
ALTER TABLE [dbo].[Like_Tour] CHECK CONSTRAINT [FK_Like_Tour]
GO
ALTER TABLE [dbo].[Like_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Like_User] FOREIGN KEY([Id_User])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Like_Tour] CHECK CONSTRAINT [FK_Like_User]
GO
ALTER TABLE [dbo].[Reservation_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Reservation_User] FOREIGN KEY([Id_User])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Reservation_Tour] CHECK CONSTRAINT [FK_Reservation_User]
GO
ALTER TABLE [dbo].[City]  WITH CHECK ADD  CONSTRAINT [FK_City_Country] FOREIGN KEY([Id_Country])
REFERENCES [dbo].[Country] ([Id])
GO
ALTER TABLE [dbo].[City] CHECK CONSTRAINT [FK_City_Country]
GO
ALTER TABLE [dbo].[Detail_Reservation_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Detail_Reservation_Reservation_Tour] FOREIGN KEY([Id_Reservation_Tour])
REFERENCES [dbo].[Reservation_Tour] ([Id])
GO
ALTER TABLE [dbo].[Detail_Reservation_Tour] CHECK CONSTRAINT [FK_Detail_Reservation_Reservation_Tour]
GO
ALTER TABLE [dbo].[Detail_Reservation_Tour]  WITH CHECK ADD  CONSTRAINT [FK_Detail_Reservation_Tour] FOREIGN KEY([Id_Tour])
REFERENCES [dbo].[Tour] ([Id])
GO
ALTER TABLE [dbo].[Detail_Reservation_Tour] CHECK CONSTRAINT [FK_Detail_Reservation_Tour]
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD  CONSTRAINT [FK_Tour_Country] FOREIGN KEY([Id_Country])
REFERENCES [dbo].[Country] ([id])
GO
ALTER TABLE [dbo].[Tour] CHECK CONSTRAINT [FK_Tour_Country]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Country] FOREIGN KEY([Id_Country])
REFERENCES [dbo].[Country] ([id])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Country]
GO

USE [master]
GO
ALTER DATABASE [GYTModel] SET  READ_WRITE 
GO
