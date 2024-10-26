--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES 
    (1,1,3,'Vacance',50),
    (2,2,1,'Restaurant',25),
    (3,4,2,'Billet de cinéma',5.5),
    (4,3,1,'Courses',17.5),
    (5,4,3,'Musée',8),
    (6,1,2,'Restaurant',30);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES 
    (1,'Sophie12','sophie@hotmail.fr','123456'),
    (2,'Aurélie40','aurelie@hotmail.fr','654321'),
    (3,'Margot425','margot@hotmail.com','azerty'),
    (4,'FredLec','fredlec@hotmail.com','azertyuiop');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_connections`
--

LOCK TABLES `user_connections` WRITE;
/*!40000 ALTER TABLE `user_connections` DISABLE KEYS */;
INSERT INTO `user_connections` VALUES 
    (1,2),
    (1,3),
    (2,1),
    (2,4),
    (3,1),
    (3,4),
    (4,2),
    (4,3);
/*!40000 ALTER TABLE `user_connections` ENABLE KEYS */;
UNLOCK TABLES;