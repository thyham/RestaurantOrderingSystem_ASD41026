-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurant
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `accesslogs`
--
use restaurant;

DROP TABLE IF EXISTS `accesslogs`;
DROP TABLE IF EXISTS `productlogs`;
DROP TABLE IF EXISTS `orderitems`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `staff`;
DROP TABLE IF EXISTS `users`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesslogs` (
  `user_id` int NOT NULL,
  `date` datetime NOT NULL,
  `desc` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`date`),
  CONSTRAINT `fk__AccessLogs__Users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesslogs`
--

LOCK TABLES `accesslogs` WRITE;
/*!40000 ALTER TABLE `accesslogs` DISABLE KEYS */;
INSERT INTO `accesslogs` VALUES (1,'2024-09-29 14:30:08','Successful Login'),(1,'2024-09-30 12:28:58','Successful Login'),(2,'2024-09-28 13:31:42','Successful Login'),(2,'2024-09-30 16:35:45','Successful Login'),(3,'2024-09-20 14:32:41','Successful Login'),(3,'2024-09-26 15:35:48','Successful Login'),(5,'2024-09-27 12:19:15','Successful Login'),(5,'2024-09-27 14:21:38','Successful Login');
/*!40000 ALTER TABLE `accesslogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `fk__Customers__Users` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (2),(3),(6),(1507),(8246),(8247),(8248),(8249),(8250),(8251),(8252),(8253),(8254),(8255),(8256),(8257),(8258),(8259),(8260);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitems` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `customisation` varchar(200) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `fk__OrderItems__Orders` (`order_id`),
  KEY `fk__OrderItems__Products` (`product_id`),
  CONSTRAINT `fk__OrderItems__Orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__OrderItems__Products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `date` datetime NOT NULL,
  `receipt_no` int NOT NULL,
  `payment_type` varchar(50) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk__Orders__Customers` (`customer_id`),
  CONSTRAINT `fk__Orders__Customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productlogs`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productlogs` (
  `staff_id` int NOT NULL,
  `date` datetime NOT NULL,
  `product_id` int NOT NULL,
  `desc` varchar(255) NOT NULL,
  PRIMARY KEY (`staff_id`,`date`),
  KEY `fk__ProductLogs__Products` (`product_id`),
  CONSTRAINT `fk__ProductLogs__Products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__ProductLogs__Staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productlogs`
--

LOCK TABLES `productlogs` WRITE;
/*!40000 ALTER TABLE `productlogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `productlogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `desc` varchar(255) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL,
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `fk__Staff__Users` FOREIGN KEY (`staff_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1),(5),(39),(8261),(8262),(8263),(8264),(8265);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `phoneno` varchar(12) DEFAULT NULL,
  `isactive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'m.lunn54@gmail.com','123','Michael','Lunn','0490000000',_binary ''),(2,'mickeymouse@gmail.com','123','Mickey','Mouse','0400000000',_binary ''),(3,'donaldduck@gmail.com','123','Donald','Duck','0400000000',_binary ''),(5,'pabloescobar@gmail.com','1','Pablo','Escobar','0400000000',_binary ''),(6,'daffyduck@gmail.com','123','Daffy','Duck','0400000000',_binary ''),(39,'jeffbezos@iotbay.com','iotbay','Jeff','Bezos','0412345678',_binary '\0'),(1507,'niktes@mail.com','12345678','Nicola','Tesla','0409908098',_binary '\0'),(8246,'alice.johnson@example.com','password123','Alice','Johnson','0412345678',_binary ''),(8247,'bob.smith@example.com','securepass','Bob','Smith','0412345679',_binary ''),(8248,'charlie.brown@example.com','mypassword','Charlie','Brown','0412345680',_binary '\0'),(8249,'diane.jones@example.com','diane2020','Diane','Jones','0412345681',_binary ''),(8250,'edward.taylor@example.com','edwardpass','Edward','Taylor','0412345682',_binary ''),(8251,'fiona.wilson@example.com','fionapass','Fiona','Wilson','0412345683',_binary '\0'),(8252,'george.moore@example.com','george1234','George','Moore','0412345684',_binary ''),(8253,'hannah.martin@example.com','hannahpass','Hannah','Martin','0412345685',_binary ''),(8254,'ian.lewis@example.com','ianpass','Ian','Lewis','0412345686',_binary '\0'),(8255,'julia.walker@example.com','julia2021','Julia','Walker','0412345687',_binary ''),(8256,'kevin.hall@example.com','kevinpass','Kevin','Hall','0412345688',_binary '\0'),(8257,'laura.king@example.com','laurapass','Laura','King','0412345689',_binary ''),(8258,'michael.scott@example.com','michael123','Michael','Scott','0412345690',_binary ''),(8259,'nancy.young@example.com','nancypass','Nancy','Young','0412345691',_binary '\0'),(8260,'oscar.green@example.com','oscar2022','Oscar','Green','0412345692',_binary ''),(8261,'peter.parker@iotbay.com','spiderpass','Peter','Parker','0412345693',_binary ''),(8262,'mary.jane@iotbay.com','mjpassword','Mary','Jane','0412345694',_binary ''),(8263,'tony.stark@iotbay.com','ironman','Tony','Stark','0412345695',_binary ''),(8264,'bruce.banner@iotbay.com','hulkpass','Bruce','Banner','0412345696',_binary '\0'),(8265,'natasha.romanoff@iotbay.com','blackwidow','Natasha','Romanoff','0412345697',_binary '');
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

-- Dump completed on 2024-10-04 16:03:28
