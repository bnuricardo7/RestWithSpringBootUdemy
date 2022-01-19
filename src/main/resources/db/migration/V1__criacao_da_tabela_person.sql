CREATE TABLE `tb_person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
)