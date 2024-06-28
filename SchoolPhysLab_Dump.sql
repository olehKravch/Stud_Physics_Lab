-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: schoolphysicslab_db
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `labs`
--

DROP TABLE IF EXISTS `labs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `labs` (
  `id_lab` int NOT NULL AUTO_INCREMENT,
  `lab_name` varchar(45) NOT NULL,
  `task1_correctAnswear` varchar(45) NOT NULL,
  `task2_correctAnswear` varchar(45) NOT NULL,
  `task3_correctAnswear` varchar(45) NOT NULL,
  `task4_correctAnswear` varchar(45) NOT NULL,
  `task5_correctAnswear` varchar(45) NOT NULL,
  `task6_correctAnswear` varchar(45) NOT NULL,
  PRIMARY KEY (`id_lab`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labs`
--

LOCK TABLES `labs` WRITE;
/*!40000 ALTER TABLE `labs` DISABLE KEYS */;
INSERT INTO `labs` VALUES (71,'Сила тертя, пружності, тяжіння','У правий бік','0,98','1568','0,049','μ1 більший за μ2 у 3 рази','K1 менша за K2 у 2 рази'),(81,'Теплові явища','0,1','-4000','7,61 12,4','100,06 0,06','42000 664000 84000','2,88'),(91,'Світло. Хвилі. Сила всесвітнього тяжіння','0,01','0,5 3','0,17 17','1403,51','16,7','1,5 * 10^11');
/*!40000 ALTER TABLE `labs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marks` (
  `id_mark` int NOT NULL AUTO_INCREMENT,
  `id_student` int NOT NULL,
  `student_surname` varchar(45) NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `id_lab` int NOT NULL,
  `lab_name` varchar(45) NOT NULL,
  `mark` int NOT NULL,
  PRIMARY KEY (`id_mark`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marks`
--

LOCK TABLES `marks` WRITE;
/*!40000 ALTER TABLE `marks` DISABLE KEYS */;
INSERT INTO `marks` VALUES (1,1,'lol','kek',91,'Світло. Хвилі. Сила всесвітнього тяжіння',9),(2,4,'Кравченко','Олег',71,'Сила тертя, пружності, тяжіння',7),(3,5,'Кравченко','Олег',81,'Теплові явища',10);
/*!40000 ALTER TABLE `marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results` (
  `id_result` int NOT NULL AUTO_INCREMENT,
  `id_student` int NOT NULL,
  `id_teacher` int NOT NULL,
  `id_lab` int NOT NULL,
  `task1_stAnswear` varchar(45) NOT NULL,
  `task2_stAnswear` varchar(45) NOT NULL,
  `task3_stAnswear` varchar(45) NOT NULL,
  `task4_stAnswear` varchar(45) NOT NULL,
  `task5_stAnswear` varchar(45) NOT NULL,
  `task6_stAnswear` varchar(45) NOT NULL,
  PRIMARY KEY (`id_result`),
  KEY `fk_results_students_idx` (`id_student`),
  KEY `fk_results_labs1_idx` (`id_lab`),
  KEY `fk_results_teachers1_idx` (`id_teacher`),
  CONSTRAINT `fk_results_labs1` FOREIGN KEY (`id_lab`) REFERENCES `labs` (`id_lab`),
  CONSTRAINT `fk_results_students` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`),
  CONSTRAINT `fk_results_teachers1` FOREIGN KEY (`id_teacher`) REFERENCES `teachers` (`id_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,1,3,91,'0,01','0,5 3','0,17 17','1403,51','16,7','1,4 * 10^11'),(2,4,1,71,'У правий бік','0,98','1467','0,049','μ1 менший за μ2 у 3 рази','K1 менша за K2 у 2 рази'),(3,5,1,81,'0,1','-4000','8,65 13','100,06 0,06','42000 664000 84000','2,88');
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id_student` int NOT NULL AUTO_INCREMENT,
  `student_surname` varchar(45) NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `student_patronymic` varchar(45) DEFAULT NULL,
  `student_class` varchar(10) NOT NULL,
  `student_email` varchar(45) NOT NULL,
  `student_password` varchar(45) NOT NULL,
  PRIMARY KEY (`id_student`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'lol','kek','eshkere','9 - Г','example@gmail.com','12435687'),(2,'stud','phys','trololo','8 - Г','ex@gmail.com','87654321'),(3,'Студент','7-Г','Тест','7 - Г','7dstud@gmail.com','10203040'),(4,'Кравченко','Олег','Максимович','7 - А','meolgert@gmail.com','olehkrav'),(5,'Кравченко','Олег','М','8 - Г','fakk640@gmail.com','12435687');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `id_teacher` int NOT NULL AUTO_INCREMENT,
  `teacher_surname` varchar(45) NOT NULL,
  `teacher_name` varchar(45) NOT NULL,
  `teacher_patronymic` varchar(45) NOT NULL,
  `teacher_email` varchar(45) NOT NULL,
  `teacher_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'Даниленко','Дмитро','Іванович','di1@gmail.com','teacher1'),(2,'Морозова','Тетяна','Олексіївна','to1@gmail.com','teacher2'),(3,'Петренко','Іван','Сегрійович','is1@gmail.com','teacher3');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'schoolphysicslab_db'
--

--
-- Dumping routines for database 'schoolphysicslab_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-28 16:13:49
