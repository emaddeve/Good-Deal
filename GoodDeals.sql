-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: GoodDeals
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `image` varchar(4000) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `magasin` varchar(255) NOT NULL,
  `datefin` datetime DEFAULT NULL,
  `userid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'whatever','2/30/2016','clothes','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',-0.357421,49.156094,'carrefour','2016-03-30 00:00:00',1),(2,'whatever','2/30/2016','clothes','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',-0.369669,49.193993,'carrefour','2016-03-30 00:00:00',1),(3,'whatever','some sold','other','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',-0.272498,49.103195,'carrefour','2016-09-30 00:00:00',1),(4,'whatever','some sold','other','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',-0.480722,49.165169,'carrefour','2016-09-30 00:00:00',2),(5,'whatever','some sold','grocery','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',0.062293,49.214268,'carrefour','2016-09-30 00:00:00',2),(6,'offer3','2/31/2016','clothes','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',0.000000,0.000000,'offer3','2016-03-31 00:00:00',1),(7,'offer4','2/31/2016','clothes','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',0.000000,0.000000,'offer4','2016-03-31 00:00:00',1),(8,'offer6','offer6','clothes','iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAIAAAD6wG44AAAAA3NCSVQICAjb4U/gAAABLElEQVR4\nnO3RAQkAIBDAwNea9u9hChHGXYLB1pwhbP8O4C2D4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO\n4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD\n4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyOMzjO4DiD4wyO\nMzjO4DiD4wyOu8GQAUuXHXQaAAAAAElFTkSuQmCC\n',0.000000,0.000000,'offers6','2016-03-31 00:00:00',15);
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'emad','emad','emad@gmail.com',NULL,''),(2,'emad2','emad2','emad2@gmail.com',NULL,''),(3,'emad','emad','emad3@gmail.com',NULL,'rifai'),(4,'emad4','emad','emad4@gmail.com',NULL,'rifai4'),(5,'emad5','emad','emad5@gmail.com',NULL,'rifai5'),(6,'emad6','emad','emad6@gmail.com',NULL,'rifai6'),(7,'emad','emad','emad3@gmail.com',NULL,'rifai'),(8,'emad7','emad','emad7@gmail.com',NULL,'rifai7'),(9,'emad9','emad','emad9@gmail.com',NULL,'rifai9'),(10,'emad10','emad','emad10@gmail.com',NULL,'rifai10'),(11,'emad11','emad','emad11@gmail.com',NULL,'rifai11'),(12,'emad12','emad','emad12@gmail.com',NULL,'rifai12'),(13,'emado','emad','emado@gmail.com',NULL,'emado'),(14,'client','client','client@gmail.com',NULL,'client'),(15,'Emad','facebook','emad.m.refai@gmail.com','10153467136966868','Rifai');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-22 21:02:17