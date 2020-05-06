-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 02 May 2020, 02:27:17
-- Sunucu sürümü: 10.4.11-MariaDB
-- PHP Sürümü: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `kafe_otomasyon`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ordered_products`
--

CREATE TABLE `ordered_products` (
  `id` int(11) NOT NULL,
  `product` text NOT NULL,
  `number` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `ordered_products`
--

INSERT INTO `ordered_products` (`id`, `product`, `number`, `order_id`, `date`) VALUES
(1, 'Lahmacun', 1, 1, '2020-05-02'),
(2, 'Tavuk Döner', 1, 1, '2020-05-02'),
(3, 'Izgara Tavuk', 3, 1, '2020-05-02'),
(4, 'Etli Ekmek', 1, 1, '2020-05-02'),
(5, 'Pizza', 1, 1, '2020-05-02'),
(6, 'Izgara Köfte', 1, 1, '2020-05-02'),
(7, 'Izgara Köfte', 2, 2, '2020-05-02'),
(8, 'Et Döner', 2, 2, '2020-05-02'),
(9, 'Kadın Budu Köfte', 2, 2, '2020-05-02'),
(10, 'Mantı', 1, 2, '2020-05-02'),
(11, 'Çıtır Mantı', 1, 2, '2020-05-02'),
(12, 'Coca-Cola', 1, 2, '2020-05-02'),
(13, 'Sprite', 1, 2, '2020-05-02'),
(14, 'Sütlaç', 1, 2, '2020-05-02'),
(15, 'Lahmacun', 2, 3, '2020-05-02'),
(16, 'Izgara Tavuk', 1, 3, '2020-05-02'),
(17, 'Etli Ekmek', 1, 3, '2020-05-02'),
(18, 'Izgara Köfte', 1, 3, '2020-05-02'),
(19, 'Balık Ekmek', 2, 4, '2020-05-02'),
(20, 'Etli Ekmek', 3, 4, '2020-05-02'),
(21, 'Balık Ekmek', 3, 5, '2020-05-02'),
(22, 'Hamburger (150gr)', 2, 5, '2020-05-02'),
(23, 'Beyti', 2, 5, '2020-05-02'),
(24, 'Cacık', 2, 6, '2020-05-02'),
(25, 'Kadın Budu Köfte', 2, 6, '2020-05-02'),
(26, 'Pilav', 2, 6, '2020-05-02'),
(27, 'Lahmacun', 2, 7, '2020-05-02'),
(28, 'Kadın Budu Köfte', 4, 7, '2020-05-02'),
(29, 'Izgara Tavuk', 3, 8, '2020-05-02'),
(30, 'Kadın Budu Köfte', 2, 8, '2020-05-02'),
(31, 'Tarhana Çorbası', 3, 8, '2020-05-02'),
(32, 'Cacık', 3, 9, '2020-05-02'),
(33, 'Kadın Budu Köfte', 2, 9, '2020-05-02'),
(34, 'Çıtır Mantı', 2, 9, '2020-05-02'),
(35, 'Fettucini Alfredo', 2, 10, '2020-05-02'),
(36, 'Suffle', 2, 10, '2020-05-02'),
(37, 'Tarhana Çorbası', 2, 11, '2020-05-02'),
(38, 'Hamburger (150gr)', 2, 11, '2020-05-02'),
(39, 'Tavuk Döner', 2, 12, '2020-05-02'),
(40, 'Çıtır Mantı', 1, 12, '2020-05-02'),
(41, 'Tarhana Çorbası', 1, 12, '2020-05-02'),
(42, 'Pizza', 3, 13, '2020-05-02'),
(43, 'Tarhana Çorbası', 2, 13, '2020-05-02'),
(44, 'Balık Ekmek', 1, 14, '2020-05-02'),
(45, 'Et Döner', 1, 14, '2020-05-02'),
(46, 'Izgara Köfte', 1, 15, '2016-05-02'),
(47, 'Tavuk Döner', 2, 15, '2016-05-02'),
(48, 'Izgara Tavuk', 1, 16, '2017-05-02'),
(49, 'Hamburger (150gr)', 2, 16, '2017-05-02'),
(50, 'Tarhana Çorbası', 1, 17, '2018-05-02'),
(51, 'Pizza', 2, 17, '2018-05-02'),
(52, 'İskender', 2, 18, '2020-04-02'),
(53, 'Tavuk Döner', 2, 18, '2020-04-02'),
(54, 'Kadın Budu Köfte', 1, 18, '2020-04-02'),
(55, 'Hamburger (150gr)', 1, 18, '2020-04-02'),
(56, 'Ali Nazik', 1, 18, '2020-04-02'),
(57, 'Pilav', 1, 18, '2020-04-02'),
(58, 'Balık Ekmek', 2, 19, '2020-04-02'),
(59, 'Etli Ekmek', 2, 19, '2020-04-02'),
(60, 'Tarhana Çorbası', 1, 19, '2020-04-02'),
(61, 'Lahmacun', 2, 20, '2020-04-02'),
(62, 'Hamburger (150gr)', 1, 20, '2020-04-02'),
(63, 'Cacık', 1, 20, '2020-04-02'),
(64, 'Fettucini Alfredo', 1, 20, '2020-04-02'),
(65, 'Cacık', 1, 21, '2020-04-02'),
(66, 'Ali Nazik', 1, 21, '2020-04-02'),
(67, 'Mantı', 1, 21, '2020-04-02'),
(68, 'Sütlaç', 2, 21, '2020-04-02'),
(69, 'Pizza', 1, 22, '2020-04-02'),
(70, 'Sütlaç', 1, 22, '2020-04-02'),
(71, 'Profiterol', 1, 22, '2020-04-02'),
(72, 'Fettucini Alfredo', 1, 22, '2020-04-02'),
(73, 'Cacık', 1, 22, '2020-04-02'),
(74, 'Et Döner', 2, 23, '2020-04-02'),
(75, 'Pizza', 1, 23, '2020-04-02'),
(76, 'Sütlaç', 1, 23, '2020-04-02'),
(77, 'Çıtır Mantı', 1, 24, '2020-04-02'),
(78, 'Suffle', 2, 24, '2020-04-02'),
(79, 'Sprite', 1, 24, '2020-04-02'),
(80, 'Fettucini Alfredo', 1, 25, '2020-03-02'),
(81, 'Pizza', 1, 25, '2020-03-02'),
(82, 'Tavuk Döner', 1, 25, '2020-03-02'),
(83, 'Profiterol', 2, 25, '2020-03-02'),
(84, 'Suffle', 2, 25, '2020-03-02'),
(85, 'Hamburger (150gr)', 2, 26, '2020-03-02'),
(86, 'Çıtır Mantı', 2, 26, '2020-03-02'),
(87, 'Etli Ekmek', 3, 27, '2020-03-02'),
(88, 'Ali Nazik', 2, 27, '2020-03-02'),
(89, 'Çıtır Mantı', 2, 27, '2020-03-02'),
(90, 'Hamburger (220gr)', 1, 27, '2020-03-02'),
(91, 'Lahmacun', 2, 28, '2020-03-02'),
(92, 'Tavuk Döner', 2, 28, '2020-03-02'),
(93, 'Kadın Budu Köfte', 2, 28, '2020-03-02'),
(94, 'Fettucini Alfredo', 2, 29, '2020-03-02'),
(95, 'Etli Ekmek', 2, 29, '2020-03-02'),
(96, 'Beyti', 4, 29, '2020-03-02'),
(97, 'Profiterol', 2, 29, '2020-03-02'),
(98, 'Lahmacun', 2, 30, '2020-02-02'),
(99, 'Etli Ekmek', 2, 30, '2020-02-02'),
(100, 'Izgara Köfte', 2, 30, '2020-02-02'),
(101, 'Ali Nazik', 3, 31, '2020-02-02'),
(102, 'Tavuk Döner', 3, 31, '2020-02-02'),
(103, 'Tarhana Çorbası', 1, 32, '2020-01-02'),
(104, 'Etli Ekmek', 2, 32, '2020-01-02'),
(105, 'Hamburger (150gr)', 1, 32, '2020-01-02'),
(106, 'Lahmacun', 2, 33, '2020-01-02'),
(107, 'Pilav', 2, 33, '2020-01-02'),
(108, 'Balık Ekmek', 4, 34, '2020-01-02'),
(109, 'Hamburger (150gr)', 2, 34, '2020-01-02'),
(110, 'Beyti', 2, 34, '2020-01-02'),
(111, 'Lahmacun', 2, 35, '2018-05-02'),
(112, 'Pizza', 2, 35, '2018-05-02'),
(113, 'Izgara Tavuk', 3, 36, '2018-05-02'),
(114, 'Hamburger (150gr)', 3, 36, '2018-05-02'),
(115, 'Cacık', 2, 37, '2018-05-02'),
(116, 'Kadın Budu Köfte', 2, 37, '2018-05-02'),
(117, 'İskender', 3, 37, '2018-05-02');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `order_no` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `waiter_name` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `orders`
--

INSERT INTO `orders` (`id`, `order_no`, `total_price`, `waiter_name`, `date`) VALUES
(1, 1, 142, 'haktan', '2020-05-02'),
(2, 2, 192, 'haktan', '2020-05-02'),
(3, 3, 76, 'haktan', '2020-05-02'),
(4, 4, 99, 'enes', '2020-05-02'),
(5, 5, 130, 'enes', '2020-05-02'),
(6, 6, 62, 'enes', '2020-05-02'),
(7, 7, 97, 'enes', '2020-05-02'),
(8, 8, 117, 'enes', '2020-05-02'),
(9, 9, 111, 'enes', '2020-05-02'),
(10, 10, 69, 'enes', '2020-05-02'),
(11, 11, 51, 'enes', '2020-05-02'),
(12, 12, 60, 'enes', '2020-05-02'),
(13, 13, 89, 'enes', '2019-05-02'),
(14, 14, 33, 'haktan', '2020-05-02'),
(15, 15, 49, 'haktan', '2016-05-02'),
(16, 16, 53, 'haktan', '2017-05-02'),
(17, 17, 57, 'haktan', '2018-05-02'),
(18, 18, 134, 'haktan', '2020-04-02'),
(19, 19, 84, 'haktan', '2020-04-02'),
(20, 20, 60, 'haktan', '2020-04-02'),
(21, 21, 60, 'haktan', '2020-04-02'),
(22, 22, 71, 'haktan', '2020-04-02'),
(23, 23, 65, 'haktan', '2020-04-02'),
(24, 24, 52, 'haktan', '2020-04-02'),
(25, 25, 102, 'haktan', '2020-03-02'),
(26, 26, 90, 'haktan', '2020-03-02'),
(27, 27, 174, 'haktan', '2020-03-02'),
(28, 28, 81, 'haktan', '2020-03-02'),
(29, 29, 206, 'haktan', '2020-03-02'),
(30, 30, 104, 'enes', '2020-02-02'),
(31, 31, 81, 'enes', '2020-02-02'),
(32, 32, 70, 'haktan', '2020-01-02'),
(33, 33, 23, 'haktan', '2020-01-02'),
(34, 34, 146, 'haktan', '2020-01-02'),
(35, 35, 62, 'enes', '2018-05-02'),
(36, 36, 106, 'enes', '2018-05-02'),
(37, 37, 127, 'enes', '2018-05-02');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `product_name` text NOT NULL,
  `value` float NOT NULL,
  `stock_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `products`
--

INSERT INTO `products` (`id`, `product_name`, `value`, `stock_number`) VALUES
(1, 'İskender', 25, 95),
(2, 'Fettucini Alfredo', 24.5, 93),
(3, 'Lahmacun', 6.5, 85),
(4, 'Balık Ekmek', 15.9, 88),
(5, 'Tarhana Çorbası', 7.5, 89),
(6, 'Izgara Köfte', 22.99, 93),
(7, 'Izgara Tavuk', 17.5, 89),
(8, 'Cacık', 5, 90),
(9, 'Et Döner', 16.99, 95),
(10, 'Tavuk Döner', 12.99, 87),
(11, 'Etli Ekmek', 22.5, 84),
(12, 'Pizza', 24.5, 89),
(13, 'Hamburger (220gr)', 24.5, 99),
(14, 'Hamburger (150gr)', 17.99, 84),
(15, 'Kadın Budu Köfte', 21, 83),
(16, 'Ali Nazik', 14, 93),
(17, 'Beyti', 23, 92),
(18, 'Pilav', 5, 95),
(19, 'Mantı', 27, 98),
(20, 'Çıtır Mantı', 27, 91),
(21, 'Coca-Cola', 4.5, 99),
(22, 'Sprite', 4.5, 98),
(23, 'Sütlaç', 7, 95),
(24, 'Suffle', 10, 94),
(25, 'Profiterol', 10, 95);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `username` text NOT NULL,
  `position` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`id`, `name`, `surname`, `username`, `position`, `password`) VALUES
(8, 'hızlı', 'giriş', '', 'staff', ''),
(10, 'Enes', 'Dedeoğlu', 'enes', 'employee', '1234'),
(11, 'Haktan', 'Sarıtepe', 'haktan', 'employee', '1234'),
(12, 'Ömer Faruk', 'Cebeci', 'cebeci', 'staff', '1234'),
(13, 'Mehmet Doğukan', 'Deniz', 'dogukan', 'staff', '1234');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `ordered_products`
--
ALTER TABLE `ordered_products`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `ordered_products`
--
ALTER TABLE `ordered_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- Tablo için AUTO_INCREMENT değeri `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Tablo için AUTO_INCREMENT değeri `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
