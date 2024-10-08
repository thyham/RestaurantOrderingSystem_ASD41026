use restaurant;

DROP TABLE IF EXISTS `Customers`;

CREATE TABLE `Customers` (
  `customer_id` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `fk__Customers__Users` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Customers` WRITE;

INSERT INTO `Customers` VALUES (2),(3),(6),(1507),(8246),(8247),(8248),(8249),(8250),(8251),(8252),(8253),(8254),(8255),(8256),(8257),(8258),(8259),(8260);

UNLOCK TABLES;