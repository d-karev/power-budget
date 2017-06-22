CREATE DATABASE  IF NOT EXISTS `powerbudgetdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `powerbudgetdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: powerbudgetdb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `budgetentry`
--

DROP TABLE IF EXISTS `budgetentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgetentry` (
  `idbudgetentry` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `nomenid` int(11) DEFAULT NULL,
  `periodicalid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `entrydate` datetime DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL,
  `moneyvalue` decimal(11,11) DEFAULT NULL,
  PRIMARY KEY (`idbudgetentry`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `budgetnomen`
--

DROP TABLE IF EXISTS `budgetnomen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgetnomen` (
  `idbudgetnomens` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbudgetnomens`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `budgetperiodic`
--

DROP TABLE IF EXISTS `budgetperiodic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgetperiodic` (
  `idbudgetperiodic` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `nomenid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL,
  `initialdate` datetime DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `periodtype` int(11) DEFAULT NULL,
  `repeatcountmax` int(11) DEFAULT NULL,
  `repeatcountcurrent` int(11) DEFAULT NULL,
  `moneyvalue` decimal(11,11) DEFAULT NULL,
  PRIMARY KEY (`idbudgetperiodic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(36) DEFAULT NULL,
  `realnamefirst` varchar(45) DEFAULT NULL,
  `realnamelast` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `picturelink` varchar(256) DEFAULT NULL,
  `googleid` varchar(128) DEFAULT NULL,
  `locale` varchar(8) DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `registerdate` datetime DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'powerbudgetdb'
--

--
-- Dumping routines for database 'powerbudgetdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-08  3:21:40
