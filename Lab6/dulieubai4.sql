USE WebShop;
GO

-- Categories (nếu chưa có)
IF NOT EXISTS (SELECT 1 FROM dbo.Categories WHERE id = '1000')
    INSERT dbo.Categories(id, name) VALUES
    ('1000', N'Đồng hồ'),
    ('1001', N'Laptop'),
    ('1002', N'Điện thoại');

-- Products: thêm 15 sản phẩm để có ít nhất 3 trang (size=5)
INSERT dbo.Products(name, image, price, Createdate, available, Categoryid) VALUES
(N'Đồng hồ A', NULL, 1500000, GETDATE(), 1, '1000'),
(N'Đồng hồ B', NULL, 1750000, GETDATE(), 1, '1000'),
(N'Đồng hồ C', NULL, 2100000, GETDATE(), 1, '1000'),

(N'Laptop A', NULL, 22000000, GETDATE(), 1, '1001'),
(N'Laptop B', NULL, 18500000, GETDATE(), 1, '1001'),
(N'Laptop C', NULL, 25990000, GETDATE(), 1, '1001'),
(N'Laptop D', NULL, 19990000, GETDATE(), 1, '1001'),

(N'Điện thoại A', NULL, 12000000, GETDATE(), 1, '1002'),
(N'Điện thoại B', NULL, 8990000, GETDATE(), 1, '1002'),
(N'Điện thoại C', NULL, 15990000, GETDATE(), 1, '1002'),
(N'Điện thoại D', NULL, 6990000, GETDATE(), 1, '1002'),
(N'Điện thoại E', NULL, 9990000, GETDATE(), 1, '1002'),

(N'Chuột không dây', NULL, 350000, GETDATE(), 1, '1001'),
(N'Bàn phím cơ', NULL, 950000, GETDATE(), 1, '1001'),
(N'Tai nghe chụp tai', NULL, 1250000, GETDATE(), 1, '1002');