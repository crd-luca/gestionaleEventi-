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
-- Table structure for table `locale`
--

DROP TABLE IF EXISTS `locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `indirizzo` varchar(100) DEFAULT NULL,
  `P_iva` varchar(100) DEFAULT NULL,
  `id_gestore` int DEFAULT NULL,
  `immagine_url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_gestore` (`id_gestore`),
  CONSTRAINT `locale_ibfk_1` FOREIGN KEY (`id_gestore`) REFERENCES `gestore` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale`
--

LOCK TABLES `locale` WRITE;
/*!40000 ALTER TABLE `locale` DISABLE KEYS */;
INSERT INTO `locale` VALUES (1,'Bar all\'angolo','Via Roma 1, Pesaro','IT12345678901',10,NULL),(2,'Funk e pop','Via Milano 10, Pesaro','IT23456789012',10,NULL),(3,'CircoLove','Corso Garibaldi 25, Pesaro','IT34567890123',19,NULL),(4,'Masquendada','Via Dante Alighieri 5, Pesaro','IT45678901234',18,NULL),(5,'Tiki Bar','Piazza del Popolo 3, Pesaro','IT56789012345',15,NULL),(6,'Re Sole','Largo Aldo Moro 7, Pesaro','IT67890123456',4,NULL),(7,'Whats','Via delle Repubblica 21, Pesaro','IT78901234567',14,NULL),(8,'Rooftop','Viale della LibertÃ  15, Pesaro','IT89012345678',15,NULL),(9,'ScaloZero','Via San Francesco 4, Pesaro','IT90123456789',18,NULL),(10,'Zingara','Via Mazzini 9, Pesaro','IT01234567890',14,NULL);
/*!40000 ALTER TABLE `locale` ENABLE KEYS */;
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
