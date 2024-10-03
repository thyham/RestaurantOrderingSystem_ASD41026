use restaurant;

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `phoneno` varchar(12) DEFAULT NULL,
  `isactive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12283 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Users` WRITE;

INSERT INTO `Users` VALUES (1,'m.lunn54@gmail.com','123','Michael','Lunn','0490000000',_binary ''),(2,'mickeymouse@gmail.com','123','Mickey','Mouse','0400000000',_binary ''),(3,'donaldduck@gmail.com','123','Donald','Duck','0400000000',_binary ''),(5,'pabloescobar@gmail.com','1','Pablo','Escobar','0400000000',_binary ''),(6,'daffyduck@gmail.com','123','Daffy','Duck','0400000000',_binary ''),(39,'jeffbezos@iotbay.com','iotbay','Jeff','Bezos','0412345678',_binary '\0'),(1507,'niktes@mail.com','12345678','Nicola','Tesla','0409908098',_binary '\0'),(8246,'alice.johnson@example.com','password123','Alice','Johnson','0412345678',_binary ''),(8247,'bob.smith@example.com','securepass','Bob','Smith','0412345679',_binary ''),(8248,'charlie.brown@example.com','mypassword','Charlie','Brown','0412345680',_binary '\0'),(8249,'diane.jones@example.com','diane2020','Diane','Jones','0412345681',_binary ''),(8250,'edward.taylor@example.com','edwardpass','Edward','Taylor','0412345682',_binary ''),(8251,'fiona.wilson@example.com','fionapass','Fiona','Wilson','0412345683',_binary '\0'),(8252,'george.moore@example.com','george1234','George','Moore','0412345684',_binary ''),(8253,'hannah.martin@example.com','hannahpass','Hannah','Martin','0412345685',_binary ''),(8254,'ian.lewis@example.com','ianpass','Ian','Lewis','0412345686',_binary '\0'),(8255,'julia.walker@example.com','julia2021','Julia','Walker','0412345687',_binary ''),(8256,'kevin.hall@example.com','kevinpass','Kevin','Hall','0412345688',_binary '\0'),(8257,'laura.king@example.com','laurapass','Laura','King','0412345689',_binary ''),(8258,'michael.scott@example.com','michael123','Michael','Scott','0412345690',_binary ''),(8259,'nancy.young@example.com','nancypass','Nancy','Young','0412345691',_binary '\0'),(8260,'oscar.green@example.com','oscar2022','Oscar','Green','0412345692',_binary ''),(8261,'peter.parker@iotbay.com','spiderpass','Peter','Parker','0412345693',_binary ''),(8262,'mary.jane@iotbay.com','mjpassword','Mary','Jane','0412345694',_binary ''),(8263,'tony.stark@iotbay.com','ironman','Tony','Stark','0412345695',_binary ''),(8264,'bruce.banner@iotbay.com','hulkpass','Bruce','Banner','0412345696',_binary '\0'),(8265,'natasha.romanoff@iotbay.com','blackwidow','Natasha','Romanoff','0412345697',_binary '');

UNLOCK TABLES;