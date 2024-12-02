--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES 
    (1,'Sophie12','sophie@hotmail.fr','$2a$10$AezsXDoNRvkAND31hgn7R.Zd8b0WVCm9NvyufbwQT6IaRfX.aa7hK'),
    (2,'Aurélie40','aurelie@hotmail.fr','$2a$10$5HUp7ZPYzGWlyalqWmSHEOSLq3z3ZsyMFUwVKvDCdodu0LhicI90u'),
    (3,'Margot425','margot@hotmail.com','$2a$10$8JCTpSoSQGF8PoYd2S1xTegrjX49eg5QURe6a4BcSzhEOsCiFe/mm'),
    (4,'FredLec','fredlec@hotmail.com','$2a$10$oIxrceABR.mhTiDF3W0Z/u33sZsEzaDFpIhBmle.LwvRsmcZhEMR6');

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` VALUES 
    (1,1,3,'Vacance',50),
    (2,2,1,'Restaurant',25),
    (3,4,2,'Billet de cinéma',5.5),
    (4,3,1,'Courses',17.5),
    (5,4,3,'Musée',8),
    (6,1,2,'Restaurant',30);

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` VALUES 
    (1,2),
    (1,3),
    (2,1),
    (2,4),
    (3,1),
    (3,4),
    (4,2),
    (4,3);