-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: paymybuddy
-- ------------------------------------------------------

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
DROP TABLE IF EXISTS `user_connections`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `user`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sender` int(11) UNSIGNED DEFAULT NULL,
  `receiver` int(11) UNSIGNED DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `transaction_user_FK` (`sender`),
  KEY `transaction_user_FK_1` (`receiver`),
  CONSTRAINT `transaction_user_FK` FOREIGN KEY (`sender`) REFERENCES `user` (`user_id`),
  CONSTRAINT `transaction_user_FK_1` FOREIGN KEY (`receiver`) REFERENCES `user` (`user_id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `id_user` int(11) UNSIGNED DEFAULT NULL,
  `id_user_connection` int(11) UNSIGNED DEFAULT NULL,
  KEY `user_connections_user_fk` (`id_user`),
  KEY `user_connections_user_fk_1` (`id_user_connection`),
  CONSTRAINT `user_connections_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_connections_user_fk_1` FOREIGN KEY (`id_user_connection`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;