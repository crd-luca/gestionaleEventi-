-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: gestioneeventi
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome_evento` varchar(100) DEFAULT NULL,
  `capienza` int DEFAULT NULL,
  `orario` time DEFAULT NULL,
  `giorno` date DEFAULT NULL,
  `prezzo` double DEFAULT NULL,
  `id_locale` int DEFAULT NULL,
  `immagine_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_locale` (`id_locale`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`id_locale`) REFERENCES `locale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (3,'Maratona di Cucina Italiana',150,'19:00:00','2024-11-12',30,9,'/images/cucian-italiana.png'),(4,'Festa della Birra Artigianale',400,'17:00:00','2024-10-28',12,3,'/images/BeerBagia_Festival_birre_artigianali_Sardegna.png'),(5,'Cinema Sotto le Stelle',300,'21:30:00','2024-08-20',8,5,'/images/cinema-sotto-le-stelle-ok.png'),(6,'VidaLoca',350,'22:00:00','2025-09-15',25,4,'/images/Z.png'),(7,'Clorophilla',1000,'23:30:00','2024-10-11',50,8,'/images/Z.png'),(8,'Random',75,'23:00:00','2024-11-03',25,7,'/images/Random_logo.png'),(9,'Cena con delitto',30,'19:00:00','2024-10-21',35,10,'/images/Tregnago_Cover_Evento_Cena_con_delitto_2022-1024x683.png');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-10 15:56:54
