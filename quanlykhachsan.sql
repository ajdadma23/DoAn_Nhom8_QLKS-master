-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 22, 2020 lúc 05:45 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlykhachsan`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `macthd` int(10) NOT NULL,
  `mahd` int(10) NOT NULL,
  `maptp` int(10) NOT NULL,
  `mapdv` int(10) NOT NULL,
  `thanhtien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvu`
--

CREATE TABLE `dichvu` (
  `madv` int(10) NOT NULL,
  `tendv` varchar(32) NOT NULL,
  `mota` varchar(255) NOT NULL,
  `gia` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dichvu`
--

INSERT INTO `dichvu` (`madv`, `tendv`, `mota`, `gia`) VALUES
(1, 'nước suối', 'thức uống', 10),
(2, 'nước coca', 'thức uống', 15),
(3, 'nước 7up', 'thức uống', 15),
(4, 'bia lon', 'thức uống', 25),
(5, 'mỳ ly', 'thức ăn', 30),
(6, 'bánh mi ngọt', 'thức ăn', 25),
(7, 'khăn lạnh', 'vệ sinh cá nhân', 8);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` int(10) NOT NULL,
  `makh` int(10) NOT NULL,
  `manv` int(10) NOT NULL,
  `ngaylap` varchar(10) NOT NULL,
  `tongtien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(10) NOT NULL,
  `ho` varchar(28) COLLATE utf8_unicode_ci NOT NULL,
  `ten` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `gioitinh` int(1) NOT NULL,
  `ngaysinh` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `sdt` int(10) NOT NULL,
  `email` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `cmnd` int(9) NOT NULL,
  `quoctich` varchar(32) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `ho`, `ten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `cmnd`, `quoctich`) VALUES
(1, 'Trần Đức', 'An', 0, '12/06/1985', 988665522, 'tranducan85@gmail.com', 123456789, 'Việt Nam'),
(2, 'Trần Văn', 'Khai', 0, '16/11/1988', 988663311, 'khaitran88@gmail.com', 987654321, 'Việt Nam'),
(3, 'Nguyễn Thị', 'Sen', 1, '12/11/1993', 833551122, 'sennguyen1993@gmail.com', 135792468, 'Trung Quốc'),
(4, 'Lê Thị', 'Cẩm', 1, '06/09/1990', 833441166, 'camle1990@gmail.com', 197382649, 'Thái Lan');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaiphong`
--

CREATE TABLE `loaiphong` (
  `maloaiphg` int(10) NOT NULL,
  `loaiphong` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `mota` text COLLATE utf8_unicode_ci NOT NULL,
  `gia` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaiphong`
--

INSERT INTO `loaiphong` (`maloaiphg`, `loaiphong`, `mota`, `gia`) VALUES
(1, 'đơn', 'nhỏ', 100),
(2, 'đôi', 'trung bình', 200),
(3, 'vip', 'lớn', 300);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `ho` varchar(28) COLLATE utf8_unicode_ci NOT NULL,
  `ten` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `gioitinh` int(1) NOT NULL,
  `ngaysinh` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `sdt` int(10) NOT NULL,
  `email` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ngayvao` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `chucvu` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `luong` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `ho`, `ten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `ngayvao`, `chucvu`, `luong`) VALUES
(1, 'Hoàng Văn', 'An', 0, '02-02-2000', 799665233, 'Ahoang2000@gmail.com', '12-12-2018', 'Admin', 15000),
(2, 'Lê Thị ', 'Đào', 1, '05-05-1997', 799552299, 'daole97@gmail.com', '12-12-2018', 'Lễ tân', 10000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieudichvu`
--

CREATE TABLE `phieudichvu` (
  `mapdv` int(10) NOT NULL,
  `madv` int(10) NOT NULL,
  `ngaydat` varchar(10) NOT NULL,
  `soluong` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuthuephong`
--

CREATE TABLE `phieuthuephong` (
  `maptp` int(10) NOT NULL,
  `maphg` int(10) NOT NULL,
  `ngayden` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ngaydi` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phong`
--

CREATE TABLE `phong` (
  `maphg` int(10) NOT NULL,
  `maloaiphg` int(10) NOT NULL,
  `tinhtrang` varchar(32) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phong`
--

INSERT INTO `phong` (`maphg`, `maloaiphg`, `tinhtrang`) VALUES
(1, 1, 'trống'),
(2, 1, 'trống'),
(3, 1, 'trống'),
(4, 2, 'trống'),
(5, 2, 'trống'),
(6, 2, 'trống'),
(7, 3, 'trống'),
(8, 3, 'trống'),
(9, 3, 'trống');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `matk` int(10) NOT NULL,
  `manv` int(10) NOT NULL,
  `tentk` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `matkhau` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `quyen` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`matk`, `manv`, `tentk`, `matkhau`, `quyen`) VALUES
(1, 1, 'Admin', 'admin', 1),
(8, 2, 'Letan001', '123', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`macthd`);

--
-- Chỉ mục cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  ADD PRIMARY KEY (`madv`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  ADD PRIMARY KEY (`maloaiphg`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Chỉ mục cho bảng `phieudichvu`
--
ALTER TABLE `phieudichvu`
  ADD PRIMARY KEY (`mapdv`);

--
-- Chỉ mục cho bảng `phieuthuephong`
--
ALTER TABLE `phieuthuephong`
  ADD PRIMARY KEY (`maptp`);

--
-- Chỉ mục cho bảng `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`maphg`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`matk`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `macthd` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  MODIFY `madv` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahd` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  MODIFY `maloaiphg` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `phieudichvu`
--
ALTER TABLE `phieudichvu`
  MODIFY `mapdv` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `phieuthuephong`
--
ALTER TABLE `phieuthuephong`
  MODIFY `maptp` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `phong`
--
ALTER TABLE `phong`
  MODIFY `maphg` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `matk` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
