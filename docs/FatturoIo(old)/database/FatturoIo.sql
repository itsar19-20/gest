-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.4.10-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database provafattura3
DROP DATABASE IF EXISTS `provafattura3`;
CREATE DATABASE IF NOT EXISTS `provafattura3` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `provafattura3`;

-- Dump della struttura di tabella provafattura3.admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `codiceAdmin` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKcw6wu58wd86r5yw55wrxf6870` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.admin: ~0 rows (circa)
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`codiceAdmin`, `id`) VALUES
	(123456, 3);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.articolo
DROP TABLE IF EXISTS `articolo`;
CREATE TABLE IF NOT EXISTS `articolo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `importoParziale` double NOT NULL,
  `iva` float NOT NULL,
  `quantita` int(11) NOT NULL,
  `fattura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3xjhmi8adpqswu288f0eeqdwb` (`fattura_id`),
  CONSTRAINT `FK3xjhmi8adpqswu288f0eeqdwb` FOREIGN KEY (`fattura_id`) REFERENCES `fattura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.articolo: ~3 rows (circa)
DELETE FROM `articolo`;
/*!40000 ALTER TABLE `articolo` DISABLE KEYS */;
INSERT INTO `articolo` (`id`, `descrizione`, `importoParziale`, `iva`, `quantita`, `fattura_id`) VALUES
	(1, 'ciao', 34.6, 2, 7, 1),
	(2, 'ciao2', 77.6, 6, 5, 1),
	(3, 'ciao3', 88.6, 8, 4, 1);
/*!40000 ALTER TABLE `articolo` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.conto
DROP TABLE IF EXISTS `conto`;
CREATE TABLE IF NOT EXISTS `conto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saldoDisponibile` double NOT NULL,
  `saldoUtile` double NOT NULL,
  `metodo_di_pagamento_id` varchar(255) DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKijchw6mbp43uml7gwtxf60hv3` (`metodo_di_pagamento_id`),
  KEY `FK11e25b6phyqq9o4vd9aat9gdo` (`persona_id`),
  CONSTRAINT `FK11e25b6phyqq9o4vd9aat9gdo` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKijchw6mbp43uml7gwtxf60hv3` FOREIGN KEY (`metodo_di_pagamento_id`) REFERENCES `metodo_di_pagamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.conto: ~0 rows (circa)
DELETE FROM `conto`;
/*!40000 ALTER TABLE `conto` DISABLE KEYS */;
INSERT INTO `conto` (`id`, `saldoDisponibile`, `saldoUtile`, `metodo_di_pagamento_id`, `persona_id`) VALUES
	(1, 103.5, 123.3, 'm1', 4);
/*!40000 ALTER TABLE `conto` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.fattura
DROP TABLE IF EXISTS `fattura`;
CREATE TABLE IF NOT EXISTS `fattura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IVA` float NOT NULL,
  `dataFattura` varchar(255) DEFAULT NULL,
  `importo` double NOT NULL,
  `nota` varchar(255) DEFAULT NULL,
  `numeroFattura` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.fattura: ~0 rows (circa)
DELETE FROM `fattura`;
/*!40000 ALTER TABLE `fattura` DISABLE KEYS */;
INSERT INTO `fattura` (`id`, `IVA`, `dataFattura`, `importo`, `nota`, `numeroFattura`, `saldoDovuto`, `scadenza`, `conto_id`, `destinatario_id`, `mittente_id`) VALUES
	(1, 2, '12/12/3000', 34.5, 'nota1', 'numero fattura', 23, 3, 1, 5, 6);
/*!40000 ALTER TABLE `fattura` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.fornitore_cliente
DROP TABLE IF EXISTS `fornitore_cliente`;
CREATE TABLE IF NOT EXISTS `fornitore_cliente` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKh7m3snmwducfcjukanoo3plq2` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.fornitore_cliente: ~2 rows (circa)
DELETE FROM `fornitore_cliente`;
/*!40000 ALTER TABLE `fornitore_cliente` DISABLE KEYS */;
INSERT INTO `fornitore_cliente` (`id`) VALUES
	(5),
	(6);
/*!40000 ALTER TABLE `fornitore_cliente` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.metodo_di_pagamento
DROP TABLE IF EXISTS `metodo_di_pagamento`;
CREATE TABLE IF NOT EXISTS `metodo_di_pagamento` (
  `id` varchar(255) NOT NULL,
  `destinazione` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.metodo_di_pagamento: ~0 rows (circa)
DELETE FROM `metodo_di_pagamento`;
/*!40000 ALTER TABLE `metodo_di_pagamento` DISABLE KEYS */;
INSERT INTO `metodo_di_pagamento` (`id`, `destinazione`, `nome`) VALUES
	('m1', 'destinazione1', 'metodo1');
/*!40000 ALTER TABLE `metodo_di_pagamento` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.pagamento
DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataPagamento` varchar(255) DEFAULT NULL,
  `mancante` float NOT NULL,
  `pagato` bit(1) NOT NULL,
  `fattura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoshl8ca41ee2jcc7bgfnv3lvo` (`fattura_id`),
  CONSTRAINT `FKoshl8ca41ee2jcc7bgfnv3lvo` FOREIGN KEY (`fattura_id`) REFERENCES `fattura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.pagamento: ~0 rows (circa)
DELETE FROM `pagamento`;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` (`id`, `dataPagamento`, `mancante`, `pagato`, `fattura_id`) VALUES
	(1, '23', 33.6, b'1', 1);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.persona
DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cognome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `pIVA` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.persona: ~6 rows (circa)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`id`, `cognome`, `indirizzo`, `mail`, `nome`, `pIVA`, `telefono`) VALUES
	(1, 'totti2', 'Via dalle palle2', 'pino.mail2', 'pino2', '12342', '03911111112'),
	(2, 'totti', 'Via dalle palle', 'pino.mail', 'pino', '1234', '0391111111'),
	(3, 'totti', 'Via dalle palle', 'pino.mail', 'pino', '1234', '03911111113'),
	(4, 'totti2u', 'Via dalle palle2u', 'pino.mail2uu', 'pino2u', '12342u', '039111111125u'),
	(5, 'tottif', 'Via dalle palle f', 'pino.mailf', 'pinof', '1234f', '0391111111f'),
	(6, 'cognome2', 'via 2', 'mail2', 'tipo2', 'fkck494', '123');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKfyoya0v2f2ecp7sl83vnfl0lq` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.user: ~2 rows (circa)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`password`, `username`, `id`) VALUES
	('Pippo123', 'UserPippo2', 1),
	('admin_pass', 'AdminUser', 3),
	('Pippo123u', 'UserPippo2u', 4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dump della struttura di tabella provafattura3.utente
DROP TABLE IF EXISTS `utente`;
CREATE TABLE IF NOT EXISTS `utente` (
  `metodoRegistrazione` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKprrorkdeifpg2wxymqtnhxsqy` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dump dei dati della tabella provafattura3.utente: ~0 rows (circa)
DELETE FROM `utente`;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` (`metodoRegistrazione`, `id`) VALUES
	('metodo1', 4);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;information_schemainformation_schemaprovafattura3
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
