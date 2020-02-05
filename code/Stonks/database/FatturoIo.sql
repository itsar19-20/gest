-- --------------------------------------------------------
-- Host:                         192.168.203.219
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
  `quantita` int(11) NOT NULL,
  `fattura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3xjhmi8adpqswu288f0eeqdwb` (`fattura_id`),
  CONSTRAINT `FK3xjhmi8adpqswu288f0eeqdwb` FOREIGN KEY (`fattura_id`) REFERENCES `fattura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.articolo: ~5 rows (approximately)
DELETE FROM `articolo`;
/*!40000 ALTER TABLE `articolo` DISABLE KEYS */;
INSERT INTO `articolo` (`id`, `descrizione`, `prezzo`, `iva`, `quantita`, `fattura_id`) VALUES
	(1, 'ciao', 34.6, 2, 7, 1),
	(2, 'ciao2', 77.6, 6, 5, 1),
	(3, 'ciao3', 88.6, 8, 4, 1),
	(4, 'calzini', 4, 22, 2, 2),
	(5, 'mutande', 6, 2, 13, 3),
	(7, 'calzone ripeieno', 22222, 0.22, 22, 9),
	(8, 'piadina', 7, 0.22, 3, 79),
	(9, 'pita', 3, 0.22, 10, 79),
	(10, 'kebab', 5, 0.22, 2, 79),
	(11, 'piadina', 7, 0.22, 3, 80),
	(12, 'pita', 3, 0.22, 10, 80),
	(13, 'kebab', 5, 0.22, 2, 80),
	(14, 'piadina', 7, 0.22, 3, 81),
	(15, 'pita', 3, 0.22, 10, 81),
	(16, 'kebab', 5, 0.22, 2, 81),
	(17, 'piadina', 7, 0.22, 3, 82),
	(18, 'pita', 3, 0.22, 10, 82),
	(19, 'kebab', 5, 0.22, 2, 82),
	(20, 'piadina', 7, 0.22, 3, 83),
	(21, 'pita', 3, 0.22, 10, 83),
	(22, 'kebab', 5, 0.22, 2, 83),
	(23, 'piadina', 7, 0.22, 3, 84),
	(24, 'pita', 3, 0.22, 10, 84),
	(25, 'kebab', 5, 0.22, 2, 84);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.conto: ~4 rows (approximately)
DELETE FROM `conto`;
/*!40000 ALTER TABLE `conto` DISABLE KEYS */;
INSERT INTO `conto` (`id`, `nome`, `saldoDisponibile`, `saldoUtile`, `utente_id`) VALUES
	(1, 'pippo', 103.5, 123.3, 4),
	(2, 'tanto', 3000, 5000, 1),
	(3, 'Sormaflex', 10000, 15000, 8),
	(4, 'Naranja', 2500, 2500, 8);
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.fattura: ~40 rows (approximately)
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
	(59, '2020-01-05', 0, b'0', 3, 'panna', 'Fat-59', 0, b'1', 3),
	(60, '2020-01-05', 0, b'0', 3, 'Acqua Panna', 'Fat-60', 0, b'1', 3),
	(61, '2020-01-05', 0, b'0', 9, 'siiiiiiiiii', 'Fat-61', 0, b'1', 3),
	(62, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-62', 0, b'1', 3),
	(63, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-63', 0, b'1', 3),
	(64, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-64', 0, b'1', 3),
	(65, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-65', 0, b'1', 3),
	(66, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-66', 0, b'1', 3),
	(67, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-67', 0, b'1', 3),
	(68, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-68', 0, b'1', 3),
	(69, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-69', 0, b'1', 3),
	(70, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-70', 0, b'1', 3),
	(71, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-71', 0, b'1', 3),
	(72, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-72', 0, b'1', 3),
	(73, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-73', 0, b'1', 3),
	(74, '2020-01-05', 0, b'1', 9, 'siiiiiiiiii', 'Fat-74', 0, b'1', 3),
	(79, '2020-01-05', 0, b'1', 9, 'json', 'Fat-79', 0, b'1', 3),
	(80, '2020-01-05', 60, b'1', 10, 'd', 'Fat-80', 0, b'0', 4),
	(81, '2020-01-05', 60, b'1', 10, 'd', 'Fat-81', 0, b'0', 4),
	(82, '2020-01-05', 60, b'1', 10, 'd', 'Fat-82', 0, b'0', 4),
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
	(93, '2020-01-05', 0, b'1', 9, '', 'Fat-93', 0, b'1', 3);
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
  KEY `FKmb9vx00a7hlubj1eo14fki1j5` (`mittente_id`),
  CONSTRAINT `FKgra11xpme91hur1mrx5utshcw` FOREIGN KEY (`destinatario_id`) REFERENCES `fornitore_cliente` (`id`),
  CONSTRAINT `FKmb9vx00a7hlubj1eo14fki1j5` FOREIGN KEY (`mittente_id`) REFERENCES `fornitore_cliente` (`id`),
  CONSTRAINT `FKolxar4dr0xl2xw3khfinied1j` FOREIGN KEY (`conto_id`) REFERENCES `conto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.OLDfattura: ~2 rows (approximately)
DELETE FROM `OLDfattura`;
/*!40000 ALTER TABLE `OLDfattura` DISABLE KEYS */;
INSERT INTO `OLDfattura` (`id`, `persona`, `eUnaFatturaCliente`, `dataFattura`, `importo`, `nota`, `numeroFattura`, `saldoDovuto`, `scadenza`, `conto_id`, `destinatario_id`, `mittente_id`) VALUES
	(1, 0, b'0', '12/12/2000', 34.5, 'nota1', 'numero fattura', 23, 3, 1, 5, 6),
	(2, 0, b'1', '22/01/2020', 100, 'jidfsjisdfgjibgsffg', 'fat02', 122, 30, 1, 6, 6),
	(3, 0, b'0', '06/07/1999', 5, 'menu kebab', 'Fat-03', 5, 0, 1, 5, 6);
/*!40000 ALTER TABLE `OLDfattura` ENABLE KEYS */;

-- Dumping structure for table stonks.pagamento
DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataPagamento` varchar(255) DEFAULT NULL,
  `mancante` float NOT NULL,
  `pagato` bit(1) NOT NULL,
  `fattura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoshl8ca41ee2jcc7bgfnv3lvo` (`fattura_id`),
  CONSTRAINT `FKoshl8ca41ee2jcc7bgfnv3lvo` FOREIGN KEY (`fattura_id`) REFERENCES `OLDfattura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.pagamento: ~0 rows (approximately)
DELETE FROM `pagamento`;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` (`id`, `dataPagamento`, `mancante`, `pagato`, `fattura_id`) VALUES
	(1, '23', 33.6, b'1', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table stonks.persona: ~9 rows (approximately)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`id`, `nome`, `cognome`, `indirizzo`, `mail`, `pIVA`, `telefono`, `autore`) VALUES
	(1, 'pino2', 'totti2', 'Via dalle palle2', 'pino.mail2', '12342', '03911111112', NULL),
	(2, 'pino', 'totti', 'Via dalle palle', 'pino.mail', '1234', '0391111111', NULL),
	(3, 'pino', 'totti', 'Via dalle palle', 'pino.mail', '1234', '03911111113', NULL),
	(4, 'pino2u', 'totti2u', 'Via dalle palle2u', 'pino.mail2uu', '12342u', '039111111125u', NULL),
	(5, 'pinof', 'tottif', 'Via dalle palle f', 'pino.mailf', '1234f', '0391111111f', NULL),
	(6, 'tipo2', 'cognome2', 'via 2', 'mail2', 'fkck494', '123', NULL),
	(7, NULL, 'yysyh', 'eyweywe', NULL, NULL, NULL, NULL),
	(8, 'Alberto', 'Sormani', 'Milano', 'a.sormani@itsrizzoli.it', NULL, NULL, NULL),
	(9, 'Mickey', 'Mouse', 'Disneyland', 'mm@disney.com', '1234567890', 'aifon', 8),
	(10, 'Jack', 'Aubrey', 'UK', NULL, NULL, 'uauei', 8);
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

-- Dumping data for table stonks.user: ~3 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `password`, `id`, `dataOraUltimoLogin`) VALUES
	('UserPippo2', 'Pippo123', 1, NULL),
	('AdminUser', 'admin_pass', 3, NULL),
	('UserPippo2u', 'Pippo123u', 4, NULL),
	('albi', '123', 8, '2020-02-05 11:46:43');
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
