use restaurant;

DROP TABLE IF EXISTS `Staff`;

CREATE TABLE `Staff` (
  `staff_id` int NOT NULL,
  PRIMARY KEY (`staff_id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Staff` WRITE;

INSERT INTO `Staff` VALUES (1),(5),(39),(8261),(8262),(8263),(8264),(8265);

UNLOCK TABLES;