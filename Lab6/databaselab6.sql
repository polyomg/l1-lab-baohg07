-- Tạo database
IF DB_ID('WebShop') IS NULL
BEGIN
    CREATE DATABASE WebShop;
END
GO

USE WebShop;
GO

-- Bảng Categories
IF OBJECT_ID('dbo.Categories', 'U') IS NOT NULL DROP TABLE dbo.Categories;
CREATE TABLE dbo.Categories (
    id        varchar(10)  NOT NULL PRIMARY KEY,
    name      nvarchar(100) NOT NULL
);

-- Bảng Products
IF OBJECT_ID('dbo.Products', 'U') IS NOT NULL DROP TABLE dbo.Products;
CREATE TABLE dbo.Products (
    id         int IDENTITY(1,1) PRIMARY KEY,
    name       nvarchar(200) NOT NULL,
    image      nvarchar(255) NULL,
    price      decimal(18,2) NOT NULL,
    Createdate date          NOT NULL CONSTRAINT DF_Products_CreateDate DEFAULT (getdate()),
    available  bit           NOT NULL CONSTRAINT DF_Products_Available DEFAULT (1),
    Categoryid varchar(10)   NOT NULL
        CONSTRAINT FK_Products_Categories REFERENCES dbo.Categories(id)
);

-- Bảng Accounts
IF OBJECT_ID('dbo.Accounts', 'U') IS NOT NULL DROP TABLE dbo.Accounts;
CREATE TABLE dbo.Accounts (
    username  varchar(50)    NOT NULL PRIMARY KEY,
    password  nvarchar(100)  NOT NULL,
    fullname  nvarchar(100)  NULL,
    email     nvarchar(100)  NULL,
    photo     nvarchar(255)  NULL,
    activated bit            NOT NULL CONSTRAINT DF_Accounts_Activated DEFAULT (1),
    admin     bit            NOT NULL CONSTRAINT DF_Accounts_Admin DEFAULT (0)
);

-- Bảng Orders
IF OBJECT_ID('dbo.Orders', 'U') IS NOT NULL DROP TABLE dbo.Orders;
CREATE TABLE dbo.Orders (
    id         bigint IDENTITY(1,1) PRIMARY KEY,
    address    nvarchar(255) NULL,
    Createdate date          NOT NULL CONSTRAINT DF_Orders_CreateDate DEFAULT (getdate()),
    Username   varchar(50)   NOT NULL
        CONSTRAINT FK_Orders_Accounts REFERENCES dbo.Accounts(username)
);

-- Bảng Orderdetails
IF OBJECT_ID('dbo.Orderdetails', 'U') IS NOT NULL DROP TABLE dbo.Orderdetails;
CREATE TABLE dbo.Orderdetails (
    id        bigint IDENTITY(1,1) PRIMARY KEY,
    price     decimal(18,2) NOT NULL,
    quantity  int           NOT NULL,
    Productid int           NOT NULL
        CONSTRAINT FK_Orderdetails_Products REFERENCES dbo.Products(id),
    Orderid   bigint        NOT NULL
        CONSTRAINT FK_Orderdetails_Orders REFERENCES dbo.Orders(id)
);

-- Dữ liệu mẫu
INSERT INTO dbo.Categories(id, name) VALUES
('1000', N'Đồng hồ đeo tay'),
('1001', N'Máy tính xách tay'),
('1002', N'Máy ảnh'),
('1003', N'Điện thoại');

INSERT INTO dbo.Accounts(username, password, fullname, email, admin)
VALUES ('admin', '123', N'Quản trị', 'admin@example.com', 1),
       ('user1', '123', N'Người dùng 1', 'user1@example.com', 0);

INSERT INTO dbo.Products(name, image, price, Categoryid)
VALUES (N'Đồng hồ A', NULL, 1500000, '1000'),
       (N'Laptop B', NULL, 22000000, '1001'),
       (N'Điện thoại C', NULL, 12000000, '1003');