use restaurant;

DROP TABLE IF EXISTS `AccessLogs`;

CREATE TABLE `AccessLogs` (
  `user_id` int NOT NULL,
  `date` datetime NOT NULL,
  `desc` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`, `date`),
  CONSTRAINT `fk__AccessLogs__Users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `AccessLogs` WRITE;

INSERT INTO `AccessLogs` VALUES (1,'2024-09-29 14:30:08','Successful Login'),(1,'2024-09-30 12:28:58','Successful Login'),(2,'2024-09-28 13:31:42','Successful Login'),(2,'2024-09-30 16:35:45','Successful Login'),(3,'2024-09-20 14:32:41','Successful Login'),(3,'2024-09-26 15:35:48','Successful Login'),(5,'2024-09-27 14:21:38','Successful Login'),(5,'2024-09-27 12:19:15','Successful Login');

UNLOCK TABLES;