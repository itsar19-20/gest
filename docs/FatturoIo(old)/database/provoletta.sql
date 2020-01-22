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


-- Dumping database structure for provola
DROP DATABASE IF EXISTS `provola`;
CREATE DATABASE IF NOT EXISTS `provola` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `provola`;

-- Dumping structure for table provola.Cacio
DROP TABLE IF EXISTS `Cacio`;
CREATE TABLE IF NOT EXISTS `Cacio` (
  `parmigiano` int(11) DEFAULT NULL,
  `pecorino` int(11) DEFAULT NULL,
  `stracchino` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table provola.Cacio: ~0 rows (approximately)
DELETE FROM `Cacio`;
/*!40000 ALTER TABLE `Cacio` DISABLE KEYS */;
INSERT INTO `Cacio` (`parmigiano`, `pecorino`, `stracchino`) VALUES
	(1, 215664, 'ugvvugvu'),
	(2, 3215, 'njvnj');
/*!40000 ALTER TABLE `Cacio` ENABLE KEYS */;

-- Dumping structure for table provola.Pomodoro
DROP TABLE IF EXISTS `Pomodoro`;
CREATE TABLE IF NOT EXISTS `Pomodoro` (
  `fragola` int(11) DEFAULT NULL,
  `cigliegia` varchar(50) DEFAULT NULL,
  `peperone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table provola.Pomodoro: ~0 rows (approximately)
DELETE FROM `Pomodoro`;
/*!40000 ALTER TABLE `Pomodoro` DISABLE KEYS */;
INSERT INTO `Pomodoro` (`fragola`, `cigliegia`, `peperone`) VALUES
	(23, NULL, 'qrgqgas'),
	(120, 'vasdvsa', 'vad525a');
/*!40000 ALTER TABLE `Pomodoro` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
