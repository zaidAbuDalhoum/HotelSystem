CREATE DATABASE  IF NOT EXISTS `hotel-system` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hotel-system`;
-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: hotel-system
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `Items`
--

DROP TABLE IF EXISTS `Items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Items` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `itemDescription` varchar(255) DEFAULT NULL,
  `itemPrice` float DEFAULT NULL,
  `itemType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Items`
--

LOCK TABLES `Items` WRITE;
/*!40000 ALTER TABLE `Items` DISABLE KEYS */;
INSERT INTO `Items` VALUES (1,'shawerma',5,'restaurant'),(3,'shirt',5,'dryCleaning'),(4,'Pasta',7.5,'restaurant');
/*!40000 ALTER TABLE `Items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaints` (
  `complaintId` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `roomId` varchar(63) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `message` tinytext,
  `dateOfComplaint` datetime(6) DEFAULT NULL,
  `reply` tinytext,
  `dateOfReply` datetime(6) DEFAULT NULL,
  `typeOfComplaint` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`complaintId`),
  KEY `userId_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`usersId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES (1,11,NULL,'khaleel ahmed','','2019-08-20 04:12:56.000000','hala ahala','2019-08-23 18:22:28.000000','restaurant'),(2,11,NULL,'khaleel ahmed','hellooooo','2019-08-20 04:20:20.000000','no no no ','2019-08-23 18:28:19.000000','other'),(3,11,NULL,'khaleel ahmed','helloooooo','2019-08-20 04:45:06.000000',NULL,NULL,'restaurant');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `roomId` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `billDescription` tinytext,
  `billAmount` float DEFAULT NULL,
  `billAmountWithTax` float DEFAULT NULL,
  `tax` float DEFAULT NULL,
  `orderDate` datetime(6) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `orderType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`usersId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,1,NULL,NULL,'Item	Quantity	Price\nshawerma	5	5.0\npepsi	4	2.0\n',33,38.28,0.16,NULL,NULL,NULL),(3,1,NULL,NULL,'Item	Quantity	Price\nshawerma	5	5.0\npepsi	4	2.0\n',33,38.28,0.16,NULL,NULL,NULL),(4,1,NULL,'','Item	Quantity	Price\nshawerma	4	5.0\npepsi	5	2.0\n',30,34.8,0.16,'2019-08-17 00:00:00.000000','delivered',NULL),(6,1,NULL,'zaid kaled','Item	Quantity	Price\nshawerma	3	5.0\npepsi	3	2.0\n',21,24.36,0.16,'2019-08-17 00:00:00.000000','delivered',NULL),(7,1,NULL,'zaid kaled','Item	Quantity	Price\npepsi	2	2.0\ntotal = 4.0\ntotal with added tax 4.64',4,4.64,0.16,'2019-08-17 21:26:13.000000','delivered','Restaurant'),(8,1,NULL,'zaid kaled','Item	Quantity	Price\npepsi	2	2.0\ntotal = 4.0\ntotal with added tax 4.64',4,4.64,0.16,'2019-08-17 21:29:10.000000','delivered','Restaurant'),(9,1,NULL,'zaid kaled','Item	Quantity	Price\ntotal = 0.0\ntotal with added tax 0.0',0,0,0.16,'2019-08-17 21:45:23.000000','delivered','Restaurant'),(10,1,NULL,'zaid kaled','Item	Quantity	Price\nshawerma	6	5.0\npepsi	6	2.0\ntotal = 42.0\ntotal with added tax 48.72',42,48.72,0.16,'2019-08-17 21:46:21.000000','delivered','Restaurant'),(11,1,NULL,'zaid kaled','Item	Quantity	Price\nshawerma	6	5.0\npepsi	6	2.0\ntotal = 42.0\ntotal with added tax 48.72',42,48.72,0.16,'2019-08-17 21:51:27.000000','Active/Pending','Restaurant'),(12,1,NULL,'zaid kaled','Item	Quantity	Price\nshawerma	6	5.0\npepsi	6	2.0\ntotal = 42.0\ntotal with added tax 48.72',42,48.72,0.16,'2019-08-17 21:52:20.000000','Active/Pending','Restaurant'),(14,1,NULL,'zaid kaled','Item	Quantity	Price\nshawerma	4	5.0\npepsi	4	2.0\ntotal = 28.0\ntotal with added tax 32.48',28,32.48,0.16,'2019-08-17 22:15:01.000000','Active/Pending','Restaurant'),(15,1,NULL,'zaid kaled','Item	Quantity	Price\nshawerma	4	5.0\ntotal = 20.0\ntotal with added tax 23.2',20,23.2,0.16,'2019-08-18 03:36:17.000000','delivered','restaurant'),(16,1,NULL,'zaid kaled','Item	Quantity	Price\nshirt	4	5.0\ntotal = 20.0\ntotal with added tax 23.2',20,23.2,0.16,'2019-08-18 03:46:33.000000','delivered','dryCleaning');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `reservationsId` int(11) NOT NULL AUTO_INCREMENT,
  `usersId` int(11) NOT NULL,
  `roomsIdInc` int(11) NOT NULL,
  `checkIn` datetime(6) DEFAULT NULL,
  `checkOut` datetime(6) DEFAULT NULL,
  `invoiceValue` float DEFAULT NULL,
  `roomsSize` varchar(15) DEFAULT NULL,
  `pricePerNight` float DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `roomId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reservationsId`),
  KEY `usersId_idx` (`usersId`),
  KEY `roomsIdInc_idx` (`roomsIdInc`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (18,1,2,'2019-08-21 12:00:00.000000','2019-08-31 11:59:59.000000',1350,'double',150,NULL,NULL),(19,1,1,'2019-08-02 12:00:00.000000','2019-08-10 11:59:59.000000',700,'single',100,NULL,NULL),(20,11,1,'2019-08-31 12:00:00.000000','2019-09-13 11:59:59.000000',1200,'single',100,NULL,NULL),(21,11,2,'2019-09-13 12:00:00.000000','2019-09-20 11:59:59.000000',900,'double',150,NULL,NULL),(22,11,3,'2019-09-18 12:00:00.000000','2019-10-17 11:59:59.000000',4340,'double',155,NULL,NULL),(23,11,4,'2019-08-31 12:00:00.000000','2019-09-04 11:59:59.000000',1650,'suite',550,NULL,NULL),(24,11,2,'2019-08-10 12:00:00.000000','2019-08-16 11:59:59.000000',750,'double',150,'khaleel ahmed',''),(25,11,2,'2019-10-02 12:00:00.000000','2019-08-09 11:59:59.000000',8250,'double',150,'khaleel ahmed','');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `roomsIdInc` int(11) NOT NULL AUTO_INCREMENT,
  `roomsId` varchar(15) DEFAULT NULL,
  `roomsSize` varchar(31) DEFAULT NULL,
  `pricePerNight` float DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roomsIdInc`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'204','single',100,'Pool View'),(2,'205','double',150,'Pool View'),(3,'206','double',155,'Garden View'),(4,'207','suite',550,'PoolView'),(5,NULL,'double',0,NULL),(6,'211','suite',720,'Pool View');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `usersFirstName` varchar(127) DEFAULT NULL,
  `usersLastName` varchar(127) DEFAULT NULL,
  `usersEmail` varchar(127) NOT NULL,
  `usersPassword` varchar(527) DEFAULT NULL,
  `usersPermission` varchar(15) DEFAULT NULL,
  `usersPhoneNum` varchar(31) DEFAULT NULL,
  `usersDateOfBirth` date DEFAULT NULL,
  `usersId` int(11) NOT NULL AUTO_INCREMENT,
  `usersStatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usersId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('zaid','kaled','12','12','admin','48512','2019-07-01',1,NULL),('khaleel','ahmed','12','c20ad4d76fe97759aa27a0c99bff6710','admin','5614651','1992-02-02',11,NULL),('zozo','subhi','8585','e10adc3949ba59abbe56e057f20f883e','restaurant','5614651','1992-02-02',12,NULL),('khaleel','subhi','0000','4a7d1ed414474e4033ac29ccb8653d9b','dryCleaning','5614651','1992-02-02',13,NULL),('hi','khokho','zkabudalhoum11@eng.just.edu.jo','202cb962ac59075b964b07152d234b70','zaid','5614651','1992-02-02',14,'Active');
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

-- Dump completed on 2019-08-26  4:56:18
