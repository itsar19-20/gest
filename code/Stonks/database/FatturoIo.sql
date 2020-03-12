-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.11-MariaDB - Source distribution
-- Server OS:                    Linux
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for stonks
DROP DATABASE IF EXISTS `stonks`;
CREATE DATABASE IF NOT EXISTS `stonks` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `stonks`;

-- Dumping structure for table stonks.admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `codiceAdmin` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKcw6wu58wd86r5yw55wrxf6870` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.admin: ~0 rows (approximately)
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`codiceAdmin`, `id`) VALUES
	(123456, 3);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table stonks.articolo
DROP TABLE IF EXISTS `articolo`;
CREATE TABLE IF NOT EXISTS `articolo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `prezzo` float NOT NULL DEFAULT 0,
  `iva` float NOT NULL,
  `quantita` float NOT NULL DEFAULT 0,
  `parziale` float NOT NULL DEFAULT 0,
  `fattura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3xjhmi8adpqswu288f0eeqdwb` (`fattura_id`),
  CONSTRAINT `FK3xjhmi8adpqswu288f0eeqdwb` FOREIGN KEY (`fattura_id`) REFERENCES `fattura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.articolo: ~72 rows (approximately)
DELETE FROM `articolo`;
/*!40000 ALTER TABLE `articolo` DISABLE KEYS */;
INSERT INTO `articolo` (`id`, `descrizione`, `prezzo`, `iva`, `quantita`, `parziale`, `fattura_id`) VALUES
	(1, 'ciao', 34.6, 2, 7, 0, 1),
	(2, 'ciao2', 77.6, 6, 5, 0, 1),
	(3, 'ciao3', 88.6, 8, 4, 0, 1),
	(4, 'calzini', 4, 22, 2, 0, 2),
	(5, 'mutande', 6, 2, 13, 0, 3),
	(7, 'calzone ripeieno', 22222, 0.22, 22, 0, 9),
	(8, 'piadina', 7, 0.22, 3, 0, 79),
	(9, 'pita', 3, 0.22, 10, 0, 79),
	(10, 'kebab', 5, 0.22, 2, 0, 79),
	(11, 'piadina', 7, 0.22, 3, 0, 80),
	(12, 'pita', 3, 0.22, 10, 0, 80),
	(13, 'kebab', 5, 0.22, 2, 0, 80),
	(14, 'piadina', 7, 0.22, 3, 0, 81),
	(15, 'pita', 3, 0.22, 10, 0, 81),
	(16, 'kebab', 5, 0.22, 2, 0, 81),
	(17, 'piadina', 7, 0.22, 3, 0, 82),
	(18, 'pita', 3, 0.22, 10, 0, 82),
	(19, 'kebab', 5, 0.22, 2, 0, 82),
	(20, 'piadina', 7, 0.22, 3, 0, 83),
	(21, 'pita', 3, 0.22, 10, 0, 83),
	(22, 'kebab', 5, 0.22, 2, 0, 83),
	(23, 'piadina', 7, 0.22, 3, 0, 84),
	(24, 'pita', 3, 0.22, 10, 0, 84),
	(25, 'kebab', 5, 0.22, 2, 0, 84),
	(26, 'piadina', 7, 0.22, 3, 0, 97),
	(27, 'pita', 3, 0.22, 10, 0, 97),
	(28, 'kebab', 5, 0.22, 2, 0, 97),
	(29, 'piadina', 7, 0.22, 3, 0, 98),
	(30, 'pita', 3, 0.22, 10, 0, 98),
	(31, 'kebab', 5, 0.22, 2, 0, 98),
	(32, 'piadina', 7, 0.22, 3, 0, 99),
	(33, 'pita', 3, 0.22, 10, 0, 99),
	(34, 'kebab', 5, 0.22, 2, 0, 99),
	(35, 'piadina', 7, 0.22, 3, 0, 100),
	(36, 'pita', 3, 0.22, 10, 0, 100),
	(37, 'kebab', 5, 0.22, 2, 0, 100),
	(38, 'aaa', 0, 0.22, 1, 0, 103),
	(39, 'sss', 0, 0.22, 1, 0, 103),
	(40, 'ddd', 0, 0.22, 1, 0, 103),
	(41, '', 0, 0.22, 1, 0, 104),
	(42, '', 0, 0.22, 1, 0, 105),
	(43, '', 0, 0.22, 1, 0, 106),
	(44, '', 0, 0.22, 1, 0, 107),
	(45, '', 0, 0.22, 1, 0, 108),
	(46, '', 0, 0.22, 1, 0, 109),
	(47, '', 0, 0.22, 1, 0, 110),
	(48, '', 0, 0.22, 1, 0, 111),
	(49, 'ertwer', 0, 0.22, 1, 0, 112),
	(50, 'df', 0, 0.22, 1, 0, 112),
	(51, 'sss', 0, 0.22, 1, 0, 112),
	(52, 'fsdfsd', 0, 0.22, 1, 0, 113),
	(53, 'aaaa', 0, 0.22, 1, 0, 113),
	(54, 'sss', 0, 0.22, 1, 0, 113),
	(55, '', 0, 0.22, 1, 0, 114),
	(56, '', 0, 0.22, 1, 0, 115),
	(57, '', 0, 0.22, 1, 0, 116),
	(58, '', 78, 0.22, 1, 0, 118),
	(59, '', 0, 0.22, 1, 0, 119),
	(60, '', 0, 0.22, 1, 0, 120),
	(61, '', 0, 0.22, 1, 0, 121),
	(62, '', 0, 0.22, 1, 0, 122),
	(63, '', 0, 0.22, 1, 0, 123),
	(64, '', 0, 0.22, 1, 0, 124),
	(65, '', 0, 0.22, 1, 0, 125),
	(66, '', 0, 0.22, 1, 0, 126),
	(67, '', 0, 0.22, 1, 0, 127),
	(68, '', 0, 0.22, 1, 0, 128),
	(69, '', 0, 0.22, 1, 0, 129),
	(70, '', 81, 0.22, 187, 0, 132),
	(71, '', 1651, 0.22, 1, 0, 132),
	(72, '', 615, 0.22, 56, 0, 132),
	(73, '', 13.5, 0.22, 1, 0, 133),
	(74, '', 0.651816, 0.22, 1, 0, 139),
	(75, '', 0.5, 0.22, 1, 0.5, 141),
	(76, '', 0, 0.22, 1, 0, 142),
	(77, '', 0, 0.22, 1, 0, 142),
	(78, '', 0, 0.22, 1, 0, 142),
	(79, '', 0, 0.22, 1, 0, 142);
/*!40000 ALTER TABLE `articolo` ENABLE KEYS */;

-- Dumping structure for table stonks.conto
DROP TABLE IF EXISTS `conto`;
CREATE TABLE IF NOT EXISTS `conto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `saldoDisponibile` float NOT NULL DEFAULT 0,
  `saldoUtile` float NOT NULL DEFAULT 0,
  `utente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK11e25b6phyqq9o4vd9aat9gdo` (`utente_id`),
  CONSTRAINT `FK_conto_user` FOREIGN KEY (`utente_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.conto: ~6 rows (approximately)
DELETE FROM `conto`;
/*!40000 ALTER TABLE `conto` DISABLE KEYS */;
INSERT INTO `conto` (`id`, `nome`, `saldoDisponibile`, `saldoUtile`, `utente_id`) VALUES
	(1, 'pippo', 103.5, 123.3, 4),
	(2, 'tanto', 3000, 5000, 1),
	(3, 'Sormaflex', 10000, 15000, 8),
	(4, 'Naranja', 2500, 2500, 8),
	(5, 'Pippo', 645165, 645165, 58),
	(6, 'Pluto', 654, 645465, 58);
/*!40000 ALTER TABLE `conto` ENABLE KEYS */;

-- Dumping structure for table stonks.fattura
DROP TABLE IF EXISTS `fattura`;
CREATE TABLE IF NOT EXISTS `fattura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `scadenza` int(3) NOT NULL DEFAULT 0,
  `e_una_fattura_cliente` bit(1) NOT NULL,
  `persona_id` int(11) NOT NULL,
  `nota` varchar(256) DEFAULT NULL,
  `numero_fattura` varchar(64) NOT NULL,
  `lordo` float NOT NULL DEFAULT 0,
  `pagata` bit(1) NOT NULL,
  `conto_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.fattura: ~111 rows (approximately)
DELETE FROM `fattura`;
/*!40000 ALTER TABLE `fattura` DISABLE KEYS */;
INSERT INTO `fattura` (`id`, `data`, `scadenza`, `e_una_fattura_cliente`, `persona_id`, `nota`, `numero_fattura`, `lordo`, `pagata`, `conto_id`) VALUES
	(1, '2020-01-24', 0, b'1', 1, 'ciao', 'Fat-1', 12, b'0', 1),
	(2, '2020-01-24', 30, b'1', 4, 'bjsvdbjdqgbji57', '2', 1234, b'0', 1),
	(3, '2020-01-24', 30, b'0', 5, '', '12s', 323, b'0', 1),
	(4, '2020-01-31', 30, b'0', 3, 'buona', 'Fat-null', 0, b'0', 1),
	(5, '2020-01-31', 60, b'0', 3, '', 'Fat-qualcosa', 0, b'0', 1),
	(6, '2020-01-04', 0, b'0', 3, 'buona ma un po\' costosa', 'Fat-qualcosa', 0, b'1', 1),
	(7, '2020-01-31', 30, b'0', 3, 'ho un articolo?', 'Fat-qualcosa', 0, b'0', 1),
	(8, '2020-01-31', 30, b'0', 3, 'ho un articolo?', 'Fat-qualcosa', 0, b'0', 1),
	(9, '2020-01-31', 30, b'0', 3, 'shao', 'Fat-qualcosa', 0, b'0', 1),
	(10, '2020-01-03', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(11, '2020-01-03', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(12, '2020-01-03', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(13, '2020-01-03', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(14, '2020-01-03', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(15, '2020-01-03', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(16, '2020-01-02', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(17, '2020-01-21', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(18, '1999-01-07', 0, b'0', 3, '', 'Fat-qualcosa', 0, b'1', 1),
	(19, '1999-01-07', 0, b'0', 3, 'eliminata?', 'Fat-qualcosa', 0, b'1', 1),
	(20, '1999-01-06', 0, b'0', 3, 'eliminata?', 'Fat-qualcosa', 0, b'1', 1),
	(21, '1999-01-06', 0, b'0', 3, 'eliminata?', 'Fat-qualcosa', 0, b'1', 1),
	(22, '1999-01-06', 0, b'0', 3, 'eliminata?', 'Fat-qualcosa', 0, b'1', 1),
	(23, '2020-01-29', 0, b'0', 3, 'mi vedi?', 'Fat-qualcosa', 0, b'1', 1),
	(24, '2020-01-29', 0, b'0', 3, 'mi vedi?', 'Fat-qualcosa', 0, b'1', 1),
	(25, '2020-01-20', 0, b'0', 3, 'mi vedi?', 'Fat-qualcosa', 0, b'1', 1),
	(26, '2020-01-20', 0, b'0', 3, 'mi vedi?', 'Fat-qualcosa', 0, b'1', 1),
	(29, '2020-01-20', 0, b'0', 3, 'e adesso mi vedi?', 'Fat-qualcosa', 0, b'1', 1),
	(30, '2020-01-20', 0, b'0', 3, 'e adesso mi vedi?', 'Fat-qualcosa', 0, b'1', 1),
	(32, '2020-01-20', 0, b'0', 3, 'adesso?', 'Fat-qualcosa', 0, b'1', 1),
	(34, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-qualcosa', 0, b'1', 1),
	(43, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'bho', 0, b'1', 1),
	(44, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-44', 0, b'1', 1),
	(45, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-45', 0, b'1', 1),
	(46, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-46', 0, b'1', 1),
	(50, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-50', 0, b'1', 1),
	(51, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-51', 0, b'1', 1),
	(52, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-52', 0, b'1', 1),
	(53, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-53', 0, b'1', 1),
	(54, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-54', 0, b'1', 1),
	(55, '2020-01-20', 0, b'0', 3, 'ciao ciao ciao', 'Fat-55', 0, b'1', 1),
	(56, '2020-01-05', 120, b'0', 3, '18:00', 'Fat-56', 0, b'0', 1),
	(57, '2020-01-13', 120, b'0', 3, 'SCIARPA', 'Fat-57', 0, b'0', 1),
	(58, '2020-01-13', 120, b'0', 3, 'SCIARPA', 'Fat-58', 0, b'0', 1),
	(79, '2020-01-05', 0, b'1', 9, 'json', 'Fat-79', 0, b'1', 3),
	(80, '2020-01-05', 60, b'1', 10, 'd', 'Fat-80', 50, b'1', 4),
	(81, '2020-01-05', 60, b'1', 10, 'd', 'Fat-81', 100, b'0', 4),
	(82, '2020-01-05', 60, b'1', 10, 'd', 'Fat-82', 88, b'0', 4),
	(83, '2020-01-05', 0, b'1', 9, '', 'Fat-83', 0, b'1', 3),
	(84, '2020-01-05', 0, b'1', 9, 'data', 'Fat-84', 0, b'1', 3),
	(85, '2020-01-05', 0, b'1', 9, '', 'Fat-85', 0, b'1', 3),
	(86, '2020-01-05', 0, b'1', 9, '', 'Fat-86', 0, b'1', 3),
	(87, '2020-01-05', 0, b'1', 9, '', 'Fat-87', 0, b'1', 3),
	(88, '2020-01-05', 0, b'1', 9, '', 'Fat-88', 0, b'1', 3),
	(89, '2020-01-05', 0, b'1', 9, '', 'Fat-89', 0, b'1', 3),
	(90, '2020-01-05', 0, b'1', 9, '', 'Fat-90', 0, b'1', 3),
	(91, '2020-01-05', 0, b'1', 9, '', 'Fat-91', 0, b'1', 3),
	(92, '2020-01-05', 0, b'1', 9, '', 'Fat-92', 0, b'1', 3),
	(93, '2020-01-05', 0, b'1', 9, '', 'Fat-93', 0, b'1', 3),
	(94, '2020-01-06', 30, b'1', 10, 'tempo di spremute', 'Fat-94', 0, b'1', 4),
	(95, '2020-01-06', 0, b'1', 10, 'tempo di spremute', 'Fat-95', 0, b'1', 4),
	(96, '2020-01-06', 0, b'1', 9, '', 'Fat-96', 0, b'1', 3),
	(97, '2020-01-06', 0, b'1', 9, '', 'Fat-97', 0, b'1', 3),
	(98, '2020-01-06', 0, b'1', 9, '', 'Fat-98', 0, b'1', 3),
	(99, '2020-01-06', 0, b'1', 9, '', 'Fat-99', 0, b'1', 3),
	(100, '2020-01-06', 0, b'1', 9, '', 'Fat-100', 0, b'1', 3),
	(101, '2020-01-06', 30, b'0', 10, 'allora questa spremuta?', 'Fat-101', 0, b'1', 4),
	(103, '2020-01-06', 0, b'1', 9, '', 'Fat-103', 0, b'1', 3),
	(104, '2020-01-06', 0, b'1', 9, '', 'Fat-104', 0, b'1', 3),
	(105, '2020-02-06', 0, b'1', 9, '', 'Fat-105', 0, b'1', 3),
	(106, '2020-02-06', 0, b'1', 9, '', 'Fat-106', 0, b'1', 3),
	(107, '2020-02-06', 0, b'1', 9, '', 'Fat-107', 0, b'1', 3),
	(108, '2020-02-06', 0, b'1', 10, 'aaaaaa', 'Fat-108', 0, b'1', 3),
	(109, '2020-02-06', 0, b'1', 9, '', 'Fat-109', 0, b'1', 4),
	(110, '2020-02-06', 0, b'1', 9, '', 'Fat-110', 0, b'1', 3),
	(111, '2020-02-06', 0, b'1', 9, '', 'Fat-111', 0, b'1', 3),
	(112, '2020-02-06', 0, b'1', 9, 'ss', 'Fat-112', 0, b'1', 3),
	(113, '2020-02-06', 0, b'1', 9, '', 'Fat-113', 0, b'1', 3),
	(114, '2020-02-06', 0, b'1', 9, 'ultima', 'Fat-114', 0, b'1', 3),
	(115, '2020-02-07', 0, b'1', 9, '', 'Fat-115', 0, b'1', 3),
	(116, '2020-02-07', 30, b'1', 9, '', 'Fat-116', 100, b'0', 5),
	(117, '2020-02-07', 30, b'1', 9, NULL, 'Fat-117', 0, b'0', 5),
	(118, '2020-02-07', 60, b'1', 9, '', 'Fat-118', 0, b'0', 5),
	(119, '2020-02-10', 0, b'1', 9, '', 'Fat-119', 0, b'1', 3),
	(120, '2020-02-10', 0, b'1', 9, '', 'Fat-120', 0, b'1', 3),
	(121, '2020-02-10', 0, b'1', 9, '', 'Fat-121', 0, b'1', 3),
	(122, '2020-02-10', 0, b'1', 9, '', 'Fat-122', 0, b'1', 3),
	(123, '2020-02-10', 0, b'1', 9, '', 'Fat-123', 0, b'1', 3),
	(124, '2020-02-10', 0, b'1', 9, '', 'Fat-124', 0, b'1', 3),
	(125, '2020-02-10', 0, b'1', 9, '', 'Fat-125', 0, b'1', 3),
	(126, '2020-02-10', 0, b'1', 9, '', 'Fat-126', 0, b'1', 3),
	(127, '2020-02-10', 0, b'1', 9, '', 'Fat-127', 0, b'1', 3),
	(128, '2020-02-10', 0, b'1', 9, '', 'Fat-128', 0, b'1', 3),
	(129, '2020-02-10', 0, b'1', 9, '', 'Fat-129', 0, b'1', 3),
	(130, '2020-02-10', 0, b'1', 9, '', 'Fat-130', 0, b'1', 3),
	(131, '2020-02-10', 0, b'1', 9, '', 'Fat-131', 16812.6, b'1', 3),
	(132, '2020-02-10', 0, b'1', 9, '', 'Fat-132', 51238, b'1', 3),
	(133, '2020-02-11', 0, b'1', 9, '', 'Fat-133', 13.5, b'1', 3),
	(134, '2020-02-11', 0, b'1', 9, '', 'Fat-134', 0.654686, b'1', 3),
	(135, '2020-02-11', 0, b'1', 9, '', 'Fat-135', 0.26451, b'1', 3),
	(136, '2020-02-11', 0, b'1', 9, '', 'Fat-136', 0.55, b'1', 3),
	(137, '2020-02-11', 0, b'1', 9, '', 'Fat-137', 0.165, b'1', 3),
	(138, '2020-02-11', 0, b'1', 9, '', 'Fat-138', 0.156, b'1', 3),
	(139, '2020-02-11', 0, b'1', 9, '', 'Fat-139', 0.651816, b'1', 3),
	(140, '2020-02-11', 0, b'1', 9, '', 'Fat-140', 0.156, b'1', 3),
	(141, '2020-02-11', 0, b'1', 9, '', 'Fat-141', 0.5, b'1', 3),
	(142, '2020-02-11', 0, b'1', 9, '', 'Fat-142', 0, b'1', 3);
/*!40000 ALTER TABLE `fattura` ENABLE KEYS */;

-- Dumping structure for table stonks.metodo_di_pagamento
DROP TABLE IF EXISTS `metodo_di_pagamento`;
CREATE TABLE IF NOT EXISTS `metodo_di_pagamento` (
  `id` varchar(255) NOT NULL,
  `destinazione` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.metodo_di_pagamento: ~0 rows (approximately)
DELETE FROM `metodo_di_pagamento`;
/*!40000 ALTER TABLE `metodo_di_pagamento` DISABLE KEYS */;
INSERT INTO `metodo_di_pagamento` (`id`, `destinazione`, `nome`) VALUES
	('m1', 'destinazione1', 'metodo1');
/*!40000 ALTER TABLE `metodo_di_pagamento` ENABLE KEYS */;

-- Dumping structure for table stonks.OLDfattura
DROP TABLE IF EXISTS `OLDfattura`;
CREATE TABLE IF NOT EXISTS `OLDfattura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persona` int(11) NOT NULL DEFAULT 0,
  `eUnaFatturaCliente` bit(1) NOT NULL DEFAULT b'0',
  `dataFattura` varchar(255) NOT NULL,
  `importo` float NOT NULL DEFAULT 0,
  `nota` varchar(255) DEFAULT NULL,
  `numeroFattura` varchar(255) NOT NULL,
  `saldoDovuto` float NOT NULL,
  `scadenza` int(11) DEFAULT NULL,
  `conto_id` int(11) DEFAULT NULL,
  `destinatario_id` int(11) DEFAULT NULL,
  `mittente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKolxar4dr0xl2xw3khfinied1j` (`conto_id`),
  KEY `FKgra11xpme91hur1mrx5utshcw` (`destinatario_id`),
  KEY `FKmb9vx00a7hlubj1eo14fki1j5` (`mittente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.OLDfattura: ~0 rows (approximately)
DELETE FROM `OLDfattura`;
/*!40000 ALTER TABLE `OLDfattura` DISABLE KEYS */;
/*!40000 ALTER TABLE `OLDfattura` ENABLE KEYS */;

-- Dumping structure for table stonks.pagamento
DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataPagamento` date DEFAULT NULL,
  `giaPagato` float NOT NULL,
  `pagato` bit(1) NOT NULL,
  `fattura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoshl8ca41ee2jcc7bgfnv3lvo` (`fattura_id`),
  CONSTRAINT `FKoshl8ca41ee2jcc7bgfnv3lvo` FOREIGN KEY (`fattura_id`) REFERENCES `fattura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.pagamento: ~5 rows (approximately)
DELETE FROM `pagamento`;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` (`id`, `dataPagamento`, `giaPagato`, `pagato`, `fattura_id`) VALUES
	(1, '2020-01-24', 33.6, b'1', 1),
	(2, '2020-02-14', 0, b'1', 80),
	(3, '2020-02-14', 0, b'1', 101),
	(4, NULL, 76, b'0', 81),
	(6, '2020-02-14', 0, b'1', 94),
	(13, NULL, 30, b'0', 82);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;

-- Dumping structure for table stonks.persona
DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `pIVA` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `autore` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_persona_persona` (`autore`),
  CONSTRAINT `FK_persona_persona` FOREIGN KEY (`autore`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.persona: ~15 rows (approximately)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`id`, `nome`, `cognome`, `indirizzo`, `mail`, `pIVA`, `telefono`, `autore`) VALUES
	(1, 'pino2', 'totti2', 'Via dalle palle2', 'pino.mail2', '12342', '03911111112', NULL),
	(2, 'pino', 'totti', 'Via dalle palle', 'pino.mail', '1234', '0391111111', NULL),
	(3, 'pino', 'totti', 'Via dalle palle', 'pino.mail', '1234', '03911111113', NULL),
	(4, 'pino2u', 'totti2u', 'Via dalle palle2u', 'pino.mail2uu', '12342u', '039111111125u', NULL),
	(5, 'pinof', 'tottif', 'Via dalle palle f', 'pino.mailf', '1234f', '0391111111f', NULL),
	(6, 'tipo2', 'cognome2', 'via 2', 'mail2', 'fkck494', '123', NULL),
	(8, 'Alberto', 'Sormani', 'Milano', 'a.sormani@itsrizzoli.it', NULL, NULL, NULL),
	(9, 'Mickey', 'Mouse', 'Disneyland', 'mm@disney.com', '1234567890', 'aifon', 8),
	(10, 'Jack', 'Aubrey', 'UK', NULL, NULL, 'uauei', 8),
	(47, 'Giorgio', 'Nesci', 'Monza', 'gn@info.it', '', '456486456', 8),
	(49, 'Giacomo', 'Poretti', '', '', '', '', 8),
	(58, 'Luca', 'Donà', 'via da qua', 'mail@realmente.esistente', NULL, '645456156', NULL),
	(59, 'ciccio', 'pasticco', '', 'emai', '', '666', 58),
	(69, 'Davide', 'Grassalino', '', '', '', '486151346', 8);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

-- Dumping structure for table stonks.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `dataOraUltimoLogin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKfyoya0v2f2ecp7sl83vnfl0lq` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.user: ~5 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `password`, `id`, `dataOraUltimoLogin`) VALUES
	('UserPippo2', 'Pippo123', 1, NULL),
	('AdminUser', 'admin_pass', 3, NULL),
	('UserPippo2u', 'Pippo123u', 4, NULL),
	('albi', '123', 8, '2020-03-09 19:25:19'),
	('luca', 'pippo123', 58, '2020-02-14 16:52:47');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table stonks.utente
DROP TABLE IF EXISTS `utente`;
CREATE TABLE IF NOT EXISTS `utente` (
  `metodoDiRegistrazione` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKprrorkdeifpg2wxymqtnhxsqy` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.utente: ~0 rows (approximately)
DELETE FROM `utente`;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` (`metodoDiRegistrazione`, `id`) VALUES
	('metodo1', 4);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
