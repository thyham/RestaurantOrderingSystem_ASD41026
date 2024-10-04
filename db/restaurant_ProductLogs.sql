use restaurant;

DROP TABLE IF EXISTS `ProductLogs`;

CREATE TABLE `ProductLogs` (
  `staff_id` int NOT NULL,
  `date` datetime NOT NULL,
  `product_id` int NOT NULL,
  `desc` varchar(255) NOT NULL,
   PRIMARY KEY (`staff_id`, `date`),
   CONSTRAINT `fk__ProductLogs__Staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE,
   CONSTRAINT `fk__ProductLogs__Products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;