use restaurant;

DROP TABLE IF EXISTS `Orders`;

CREATE TABLE `Orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `date` datetime NOT NULL,
  `receipt_no` int NOT NULL,
  `payment_type` varchar(50) NOT NULL,
   PRIMARY KEY (`order_id`),
   CONSTRAINT `fk__Orders__Customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;