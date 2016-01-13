CREATE DATABASE  IF NOT EXISTS `web_engineering` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `web_engineering`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: aagid2mfgguv80.cp8slgariplu.eu-west-1.rds.amazonaws.com    Database: web_engineering
-- ------------------------------------------------------
-- Server version	5.5.37-log

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
-- Table structure for table `FlashcardSets`
--

DROP TABLE IF EXISTS `FlashcardSets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FlashcardSets` (
  `flashcardSetId` int(11) NOT NULL AUTO_INCREMENT,
  `ownerId` int(11) NOT NULL,
  `title` varchar(45) DEFAULT '',
  PRIMARY KEY (`flashcardSetId`),
  KEY `fk_FlashcardSets_Users1_idx` (`ownerId`),
  CONSTRAINT `owner` FOREIGN KEY (`ownerId`) REFERENCES `Users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FlashcardSets`
--

LOCK TABLES `FlashcardSets` WRITE;
/*!40000 ALTER TABLE `FlashcardSets` DISABLE KEYS */;
INSERT INTO `FlashcardSets` VALUES (4,2,'Geschichte'),(34,2,'Biologie'),(35,2,'Französisch 2'),(36,2,'Französisch 1'),(37,2,'Englisch');
/*!40000 ALTER TABLE `FlashcardSets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Flashcards`
--

DROP TABLE IF EXISTS `Flashcards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Flashcards` (
  `flashcardId` int(11) NOT NULL AUTO_INCREMENT,
  `flashcardSetId` int(11) NOT NULL,
  `question` text,
  `answer` text,
  `correctAnswerDate` datetime DEFAULT NULL,
  `correctAnswerTimes` int(10) unsigned DEFAULT NULL,
  `falseAnswerTimes` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`flashcardId`),
  KEY `fk_Flashcards_FlashcardSets_idx` (`flashcardSetId`),
  CONSTRAINT `fk_Flashcards_FlashcardSets` FOREIGN KEY (`flashcardSetId`) REFERENCES `FlashcardSets` (`flashcardSetId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Flashcards`
--

LOCK TABLES `Flashcards` WRITE;
/*!40000 ALTER TABLE `Flashcards` DISABLE KEYS */;
INSERT INTO `Flashcards` VALUES (8,4,'<p>frage522</p>','<p>antwort5</p>',NULL,NULL,NULL),(9,34,'Klicke auf Antwort anzeigen und anschließend auf den Stift oder drücke E um die Karte zu editieren.','Klicke auf den Stift rechts oben um die Karte zu bearbeiten.',NULL,NULL,NULL),(10,35,'Klicke auf Antwort anzeigen und anschließend auf den Stift oder drücke E um die Karte zu editieren.','Klicke auf den Stift rechts oben um die Karte zu bearbeiten.',NULL,NULL,NULL),(11,36,'<p>ein Computer<br></p>','<p>un ordinateur<br></p>',NULL,NULL,NULL),(15,37,'<p>ein Computer<br></p>','<p>un ordinateur<br></p>',NULL,NULL,NULL),(21,4,'frage1','antwort1',NULL,NULL,NULL);
/*!40000 ALTER TABLE `Flashcards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `salt` varchar(256) NOT NULL DEFAULT '',
  `pwhash` varchar(256) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'halli@hall.de','2%YV&RX3W8!:T/$`+AzV','70b3ac970a4ab12d3ee4d010179da777efcbb5ac'),(2,'test@test.de','uLc$;whAVk1\"%AJx!ork','e5041c31be98fd661b9d1037d4e03c30fee4a98c');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-09 17:42:32
