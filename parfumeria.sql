-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: parfumeria
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `perfume`
--

DROP TABLE IF EXISTS `perfume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfume` (
  `code` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `manufacturer` varchar(20) NOT NULL,
  `price` float NOT NULL,
  `discount` float NOT NULL,
  `description` varchar(50) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfume`
--

LOCK TABLES `perfume` WRITE;
/*!40000 ALTER TABLE `perfume` DISABLE KEYS */;
INSERT INTO `perfume` VALUES ('p01','Black Opium','YSL',500,0,'YSL - good','F'),('p02','Stronger with you','Armani',320,15,'Armani- good','F'),('p03','Armani Code','Armani',420,20,'Armani- good','M'),('p04','Joy','Dior',420,5,'Dior - good ','F'),('p05','Scandal','Jean Paul Gaultier',500,5,'JPGaultier - nice','F'),('p06','Sauvage','Dior',600,10,'Dior - good','M'),('p07','Miss Dior','Dior',600,20,'Dior- amazing','F'),('p08','L>homme','Prada',6000,0,'Prada- perfect','M'),('p09','Eros','Versace',320,15,'Versace - ok','M'),('p10','Daisy','Marc Jacobs',320,10,'MJ- nice','F');
/*!40000 ALTER TABLE `perfume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfumeinshop`
--

DROP TABLE IF EXISTS `perfumeinshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfumeinshop` (
  `code` char(10) NOT NULL,
  `codePerfume` char(10) DEFAULT NULL,
  `codeShop` char(20) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `codePerfume` (`codePerfume`),
  KEY `codeShop` (`codeShop`),
  CONSTRAINT `perfumeinshop_ibfk_1` FOREIGN KEY (`codePerfume`) REFERENCES `perfume` (`code`),
  CONSTRAINT `perfumeinshop_ibfk_2` FOREIGN KEY (`codeShop`) REFERENCES `perfumeshop` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfumeinshop`
--

LOCK TABLES `perfumeinshop` WRITE;
/*!40000 ALTER TABLE `perfumeinshop` DISABLE KEYS */;
INSERT INTO `perfumeinshop` VALUES ('ps01','p02','s01',150),('ps02','p04','s01',350),('ps03','p07','s02',300),('ps04','p01','s02',300),('ps05','p03','s01',300),('ps06','p04','s01',350),('ps07','p04','s01',500),('ps08','p06','s03',500),('ps09','p06','s02',500),('ps10','p02','s01',250),('ps11','p08','s03',250),('ps12','p08','s03',500);
/*!40000 ALTER TABLE `perfumeinshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfumeshop`
--

DROP TABLE IF EXISTS `perfumeshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfumeshop` (
  `code` char(10) NOT NULL,
  `location` char(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfumeshop`
--

LOCK TABLES `perfumeshop` WRITE;
/*!40000 ALTER TABLE `perfumeshop` DISABLE KEYS */;
INSERT INTO `perfumeshop` VALUES ('s01','Brasov'),('s02','Iasi'),('s03','Bucuresti');
/*!40000 ALTER TABLE `perfumeshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` varchar(13) NOT NULL,
  `name` varchar(10) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(15) NOT NULL,
  `job` enum('Employee','Admin','Manager') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('u01','John','Smith','johnsmith@gmail.com','12345','Employee'),('u02','Jane','Joe','janejoe@hotmail.uk.co','24680','Employee'),('u03','Bob','Dylan','bobdylan@gmail.com','abcde','Manager'),('u04','Alice','Gold','alicegold@gmail.com','fghij','Admin');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-30 10:55:15
