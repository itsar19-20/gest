-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.4.8-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database stonks
DROP DATABASE IF EXISTS `stonks`;
CREATE DATABASE IF NOT EXISTS `stonks` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `stonks`;

-- Dump della struttura di tabella stonks.admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `codiceAdmin` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKcw6wu58wd86r5yw55wrxf6870` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.articolo
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.conto
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

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.fattura
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
  `notaDiCredito` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.jesu
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
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.metodo_di_pagamento
DROP TABLE IF EXISTS `metodo_di_pagamento`;
CREATE TABLE IF NOT EXISTS `metodo_di_pagamento` (
  `id` varchar(255) NOT NULL,
  `destinazione` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.oldfattura
DROP TABLE IF EXISTS `oldfattura`;
CREATE TABLE IF NOT EXISTS `oldfattura` (
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

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.pagamento
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

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.persona
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

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `dataOraUltimoLogin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKfyoya0v2f2ecp7sl83vnfl0lq` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella stonks.utente
DROP TABLE IF EXISTS `utente`;
CREATE TABLE IF NOT EXISTS `utente` (
  `metodoDiRegistrazione` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKprrorkdeifpg2wxymqtnhxsqy` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- L’esportazione dei dati non era selezionata.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
