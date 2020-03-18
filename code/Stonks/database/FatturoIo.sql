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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4;

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
	(79, '', 0, 0.22, 1, 0, 142),
	(80, '', 0, 0.22, 1, 0, 143);
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
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4;

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
	(142, '2020-02-11', 0, b'1', 9, '', 'Fat-142', 0, b'1', 3),
	(143, '2020-03-18', 30, b'1', 9, '', 'Fat-143', 0, b'0', 3);
/*!40000 ALTER TABLE `fattura` ENABLE KEYS */;

-- Dumping structure for table stonks.jesu
DROP TABLE IF EXISTS `jesu`;
CREATE TABLE IF NOT EXISTS `jesu` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `authType` varchar(50) DEFAULT NULL,
  `characterEncoding` varchar(50) DEFAULT NULL,
  `contentType` varchar(50) DEFAULT NULL,
  `contextPath` varchar(50) DEFAULT NULL,
  `localAddr` varchar(50) DEFAULT NULL,
  `localName` varchar(50) DEFAULT NULL,
  `method` varchar(50) DEFAULT NULL,
  `pathInfo` varchar(50) DEFAULT NULL,
  `pathTranslated` varchar(50) DEFAULT NULL,
  `protocol` varchar(50) DEFAULT NULL,
  `queryString` varchar(500) DEFAULT NULL,
  `remoteAddr` varchar(50) DEFAULT NULL,
  `remoteHost` varchar(50) DEFAULT NULL,
  `remoteUser` varchar(50) DEFAULT NULL,
  `requestSessionId` varchar(50) DEFAULT NULL,
  `requestURI` varchar(50) DEFAULT NULL,
  `scheme` varchar(50) DEFAULT NULL,
  `serverName` varchar(50) DEFAULT NULL,
  `servletPath` varchar(50) DEFAULT NULL,
  `requestToString` varchar(500) DEFAULT NULL,
  `contentLength` int(50) DEFAULT NULL,
  `localPort` int(50) DEFAULT NULL,
  `remotePort` int(50) DEFAULT NULL,
  `serverPort` int(50) DEFAULT NULL,
  `requestHashCode` int(50) DEFAULT NULL,
  `attributeNames` varchar(500) DEFAULT NULL,
  `requestClass` varchar(500) DEFAULT NULL,
  `cookies` varchar(500) DEFAULT NULL,
  `responseBufferSize` int(50) DEFAULT NULL,
  `responseCharacterEncoding` varchar(50) DEFAULT NULL,
  `responseContentType` varchar(50) DEFAULT NULL,
  `responseStatus` int(50) DEFAULT NULL,
  `responseHashCode` int(50) DEFAULT NULL,
  `responseToString` varchar(500) DEFAULT NULL,
  `numberOfCharactersInTheResponseString` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.jesu: ~0 rows (approximately)
DELETE FROM `jesu`;
/*!40000 ALTER TABLE `jesu` DISABLE KEYS */;
INSERT INTO `jesu` (`id`, `date`, `authType`, `characterEncoding`, `contentType`, `contextPath`, `localAddr`, `localName`, `method`, `pathInfo`, `pathTranslated`, `protocol`, `queryString`, `remoteAddr`, `remoteHost`, `remoteUser`, `requestSessionId`, `requestURI`, `scheme`, `serverName`, `servletPath`, `requestToString`, `contentLength`, `localPort`, `remotePort`, `serverPort`, `requestHashCode`, `attributeNames`, `requestClass`, `cookies`, `responseBufferSize`, `responseCharacterEncoding`, `responseContentType`, `responseStatus`, `responseHashCode`, `responseToString`, `numberOfCharactersInTheResponseString`) VALUES
	(4, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@538365f9', -1, 8080, 51308, 8080, 1401120249, 'java.util.Collections$3@1917d593', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@3cf50c27', 32768, 'utf-8', 'application/json', 200, 1191436186, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(5, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=x', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=x)@2162517c', -1, 8080, 51314, 8080, 560091516, 'java.util.Collections$3@d724ad7', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@7046cdd1', 32768, 'utf-8', 'application/json', 200, 1336108496, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 35),
	(6, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=aaa&bravo=bbb&charlie3', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=aaa&bravo=bbb&charlie3)@213109f7', -1, 8080, 51316, 8080, 556861943, 'java.util.Collections$3@5c4f4c1d', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59fc3d02', 32768, 'utf-8', 'application/json', 200, 222812440, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 36),
	(7, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=aaa&bravo=bbb&charlie=3', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=aaa&bravo=bbb&charlie=3)@213109f7', -1, 8080, 51316, 8080, 556861943, 'java.util.Collections$3@3acb332b', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59fc3d02', 32768, 'utf-8', 'application/json', 200, 222812440, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 36),
	(8, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/archivio/getAllMineInvoices', 'http', '192.168.1.71', '/archivio/getAllMineInvoices', 'Request(POST //192.168.1.71:8080/archivio/getAllMineInvoices)@213109f7', 6, 8080, 51316, 8080, 556861943, 'java.util.Collections$3@2013ab1c', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59fc3d02', 32768, 'utf-8', 'application/json', 200, 222812440, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 39659),
	(9, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@2a462299', -1, 8080, 51361, 8080, 709239449, 'java.util.Collections$3@366a3bd0', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@e626a5d', 32768, 'utf-8', 'application/json', 200, 938185998, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(10, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@4886407', -1, 8080, 51381, 8080, 76047367, 'java.util.Collections$3@3ce9dc5a', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@1eb78993', 32768, 'utf-8', 'application/json', 200, 642878996, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(11, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@9087590', -1, 8080, 51394, 8080, 151549328, 'java.util.Collections$3@23835e6a', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@4140bab7', 32768, 'utf-8', 'application/json', 200, 1297736452, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(12, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@c19927', -1, 8080, 51404, 8080, 12687655, 'java.util.Collections$3@2194a69a', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@533f904f', 32768, 'utf-8', 'application/json', 200, 794021016, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(13, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@223f1f62', -1, 8080, 51409, 8080, 574562146, 'java.util.Collections$3@67605c0f', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@61224ebf', 32768, 'utf-8', 'application/json', 200, 1369172524, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(14, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=x', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=x)@223f1f62', -1, 8080, 51409, 8080, 574562146, 'java.util.Collections$3@7000a361', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@61224ebf', 32768, 'utf-8', 'application/json', 200, 1369172524, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 35),
	(15, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=x', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=x)@250fbd7c', -1, 8080, 51413, 8080, 621788540, 'java.util.Collections$3@78a7e50f', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@aef7601', 32768, 'utf-8', 'application/json', 200, 1411483810, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 35),
	(16, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=x', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=x)@250fbd7c', -1, 8080, 51413, 8080, 621788540, 'java.util.Collections$3@39ed6b4', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@aef7601', 32768, 'utf-8', 'application/json', 200, 1411483810, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 35),
	(17, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=x', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=x)@250fbd7c', -1, 8080, 51413, 8080, 621788540, 'java.util.Collections$3@3e81d38f', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@aef7601', 32768, 'utf-8', 'application/json', 200, 1411483810, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 35),
	(18, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@250fbd7c', -1, 8080, 51413, 8080, 621788540, 'java.util.Collections$3@5ddeeb3f', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@aef7601', 32768, 'utf-8', 'application/json', 200, 1411483810, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(19, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@250fbd7c', -1, 8080, 51413, 8080, 621788540, 'java.util.Collections$3@5d033a2c', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@aef7601', 32768, 'utf-8', 'application/json', 200, 1411483810, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(20, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@6a0e395e', -1, 8080, 51420, 8080, 1779317086, 'java.util.Collections$3@13876a12', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@55cbd49d', 32768, 'utf-8', 'application/json', 200, 1642751934, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(21, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@6a0e395e', -1, 8080, 51420, 8080, 1779317086, 'java.util.Collections$3@7a9c621c', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@55cbd49d', 32768, 'utf-8', 'application/json', 200, 1642751934, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(22, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@6a0e395e', -1, 8080, 51420, 8080, 1779317086, 'java.util.Collections$3@63ccb92a', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@55cbd49d', 32768, 'utf-8', 'application/json', 200, 1642751934, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(23, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@6a0e395e', -1, 8080, 51420, 8080, 1779317086, 'java.util.Collections$3@64c9b6d3', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@55cbd49d', 32768, 'utf-8', 'application/json', 200, 1642751934, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(24, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'alfa=x', '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test?alfa=x)@6a0e395e', -1, 8080, 51420, 8080, 1779317086, 'java.util.Collections$3@375e2d44', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@55cbd49d', 32768, 'utf-8', 'application/json', 200, 1642751934, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 35),
	(25, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/test', 'http', '192.168.1.71', '/test', 'Request(GET //192.168.1.71:8080/test)@46bb117d', -1, 8080, 51428, 8080, 1186664829, 'java.util.Collections$3@229d1567', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@a9281c1', 32768, 'utf-8', 'application/json', 200, 1488526562, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 47),
	(26, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '19m9h1rq6fdf9m49mhs97983n', '/login', 'http', '192.168.1.71', '/login', 'Request(POST //192.168.1.71:8080/login)@46bb117d', 26, 8080, 51428, 8080, 1186664829, 'java.util.Collections$3@189d0113', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@a9281c1', 32768, 'utf-8', 'application/json', 200, 1488526562, 'HTTP/1.1 200 \nSet-Cookie: JSESSIONID=1d0pebhyyyvgcb3djhth90ypv;Path=/\r\nExpires: Thu, 01 Jan 1970 00:00:00 GMT\r\nContent-Type: application/json\r\n\r\n', 194),
	(27, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'listaNotifica=&user=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/notifica', 'http', '192.168.1.71', '/notifica', 'Request(GET //192.168.1.71:8080/notifica?listaNotifica=&user=8)@46bb117d', -1, 8080, 51428, 8080, 1186664829, 'java.util.Collections$3@15415bb0', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@105f0404', 32768, 'utf-8', 'application/json', 200, 1488526562, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 2),
	(28, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/fattura/crea', 'http', '192.168.1.71', '/fattura/crea', 'Request(POST //192.168.1.71:8080/fattura/crea)@57eb9a30', 22, 8080, 51441, 8080, 1475058224, 'java.util.Collections$3@14c4dec3', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@619e70ec', 32768, 'utf-8', 'application/json', 200, 834840372, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', NULL),
	(29, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/fattura/crea', 'http', '192.168.1.71', '/fattura/crea', 'Request(POST //192.168.1.71:8080/fattura/crea)@57eb9a30', 24, 8080, 51441, 8080, 1475058224, 'java.util.Collections$3@5e0e8d4', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@619e70ec', 32768, 'utf-8', 'application/json', 200, 834840372, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', NULL),
	(30, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/fattura/crea', 'http', '192.168.1.71', '/fattura/crea', 'Request(POST //192.168.1.71:8080/fattura/crea)@57eb9a30', 23, 8080, 51441, 8080, 1475058224, 'java.util.Collections$3@6663e0a5', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@619e70ec', 32768, 'utf-8', 'application/json', 200, 834840372, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', NULL),
	(31, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/fattura/aggiungi-persona', 'http', '192.168.1.71', '/fattura/aggiungi-persona', 'Request(POST //192.168.1.71:8080/fattura/aggiungi-persona)@660bf5ee', 233, 8080, 51445, 8080, 1712059886, 'java.util.Collections$3@78a5e0f', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@123a85c5', 32768, 'iso-8859-1', NULL, 200, 1868868705, 'HTTP/1.1 200 \n\r\n', 99),
	(32, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/fattura/salva', 'http', '192.168.1.71', '/fattura/salva', 'Request(POST //192.168.1.71:8080/fattura/salva)@660bf5ee', 732, 8080, 51445, 8080, 1712059886, 'java.util.Collections$3@2dcdc09a', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@123a85c5', 32768, 'iso-8859-1', NULL, 200, 1868868705, 'HTTP/1.1 200 \n\r\n', NULL),
	(33, '2020-03-18', NULL, 'utf-8', 'application/x-www-form-urlencoded; charset=UTF-8', '', '192.168.1.71', '192.168.1.71', 'POST', NULL, NULL, 'HTTP/1.1', NULL, '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/archivio/getAllMineInvoices', 'http', '192.168.1.71', '/archivio/getAllMineInvoices', 'Request(POST //192.168.1.71:8080/archivio/getAllMineInvoices)@660bf5ee', 6, 8080, 51445, 8080, 1712059886, 'java.util.Collections$3@276a3f32', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@123a85c5', 32768, 'utf-8', 'application/json', 200, 1868868705, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 40287),
	(34, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'numMesi=null&numSettimane=null&entrataUscita=null&user=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/scadenza', 'http', '192.168.1.71', '/scadenza', 'Request(GET //192.168.1.71:8080/scadenza?numMesi=null&numSettimane=null&entrataUscita=null&user=8)@69d8c347', -1, 8080, 51455, 8080, 1775813447, 'java.util.Collections$3@683f990c', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59afad62', 32768, 'utf-8', 'application/json', 200, 1223980518, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 2602),
	(35, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'numMesi=null&numSettimane=null&entrataUscita=null&user=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/scadenza', 'http', '192.168.1.71', '/scadenza', 'Request(GET //192.168.1.71:8080/scadenza?numMesi=null&numSettimane=null&entrataUscita=null&user=8)@69d8c347', -1, 8080, 51455, 8080, 1775813447, 'java.util.Collections$3@665dcadf', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59afad62', 32768, 'utf-8', 'application/json', 200, 1223980518, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 2602),
	(36, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'numMesi=null&numSettimane=null&entrataUscita=null&user=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/scadenza', 'http', '192.168.1.71', '/scadenza', 'Request(GET //192.168.1.71:8080/scadenza?numMesi=null&numSettimane=null&entrataUscita=null&user=8)@69d8c347', -1, 8080, 51455, 8080, 1775813447, 'java.util.Collections$3@546edbd9', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59afad62', 32768, 'utf-8', 'application/json', 200, 1223980518, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 2602),
	(37, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'userID=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/allPagamenti', 'http', '192.168.1.71', '/allPagamenti', 'Request(GET //192.168.1.71:8080/allPagamenti?userID=8)@69d8c347', -1, 8080, 51455, 8080, 1775813447, 'java.util.Collections$3@8ef57c8', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59afad62', 32768, 'utf-8', 'application/json', 200, 1223980518, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 4219),
	(38, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'numMesi=null&numSettimane=null&entrataUscita=null&user=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/scadenza', 'http', '192.168.1.71', '/scadenza', 'Request(GET //192.168.1.71:8080/scadenza?numMesi=null&numSettimane=null&entrataUscita=null&user=8)@69d8c347', -1, 8080, 51455, 8080, 1775813447, 'java.util.Collections$3@675a4878', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59afad62', 32768, 'utf-8', 'application/json', 200, 1223980518, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 2602),
	(39, '2020-03-18', NULL, NULL, NULL, '', '192.168.1.71', '192.168.1.71', 'GET', NULL, NULL, 'HTTP/1.1', 'numMesi=null&numSettimane=null&entrataUscita=null&user=8', '192.168.1.122', '192.168.1.122', NULL, '1d0pebhyyyvgcb3djhth90ypv', '/scadenza', 'http', '192.168.1.71', '/scadenza', 'Request(GET //192.168.1.71:8080/scadenza?numMesi=null&numSettimane=null&entrataUscita=null&user=8)@69d8c347', -1, 8080, 51455, 8080, 1775813447, 'java.util.Collections$3@1321e10c', 'class org.eclipse.jetty.server.Request', '[Ljavax.servlet.http.Cookie;@59afad62', 32768, 'utf-8', 'application/json', 200, 1223980518, 'HTTP/1.1 200 \nContent-Type: application/json\r\n\r\n', 2602);
/*!40000 ALTER TABLE `jesu` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4;

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
	(69, 'Davide', 'Grassalino', '', '', '', '486151346', 8),
	(125, 'm', 'm', 'm', 'm', 'm', 'm', 8);
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
	('albi', '123', 8, '2020-03-18 15:40:10'),
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
