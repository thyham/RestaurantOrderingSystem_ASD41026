use restaurant;

DROP TABLE IF EXISTS `OrderItems`;

CREATE TABLE `OrderItems` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `customisation` varchar(200) NOT NULL,
  `quantity` int NOT NULL,
   PRIMARY KEY (`order_item_id`),
   CONSTRAINT `fk__OrderItems__Orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
   CONSTRAINT `fk__OrderItems__Products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;