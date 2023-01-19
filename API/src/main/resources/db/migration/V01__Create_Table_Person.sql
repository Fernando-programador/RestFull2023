CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(150) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `last_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
); 
