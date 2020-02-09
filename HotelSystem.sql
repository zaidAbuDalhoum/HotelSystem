-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: hotel-system
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
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
  KEY `roomsIdInc_idx` (`roomsIdInc`),
  CONSTRAINT `usersId` FOREIGN KEY (`usersId`) REFERENCES `users` (`usersId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (27,15,1,'2020-02-28 12:00:00.000000','2020-02-29 11:59:59.000000',100,'single',100,'zaid  ahmad','');
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
INSERT INTO `rooms` VALUES (1,'204','single',100,'Pool View'),(2,'205','double',150,'Pool View'),(3,'206','double',155,'Garden View'),(4,'207','suite',550,'Pool View'),(5,'208','double',200,'Garden View'),(6,'211','suite',720,'Pool View');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('zaid','kaled','12','12','admin','48512','2019-07-01',1,NULL),('khaleel','ahmed','12','c20ad4d76fe97759aa27a0c99bff6710','admin','5614651','1992-02-02',11,NULL),('mahmoud','subhi','8585','e10adc3949ba59abbe56e057f20f883e','restaurant','5614651','1992-02-02',12,NULL),('khaleel','subhi','0000','4a7d1ed414474e4033ac29ccb8653d9b','dryCleaning','5614651','1992-02-02',13,NULL),('malek','samer','zkabudalhoum11@eng.just.edu.jo','202cb962ac59075b964b07152d234b70','user','5614651','1992-02-02',14,'Active'),('zaid ','ahmad','1212','202cb962ac59075b964b07152d234b70','user','84651325','1992-02-02',15,'Active');
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

-- Dump completed on 2020-02-09  2:51:36
