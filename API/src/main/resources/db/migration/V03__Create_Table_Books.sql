CREATE TABLE  IF NOT EXISTS `books` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(60) NOT NULL,
  `launch_date` datetime(6) NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `title` varchar(180) DEFAULT NULL,
   PRIMARY KEY (`id`)
);