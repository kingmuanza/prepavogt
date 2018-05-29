CREATE DATABASE  IF NOT EXISTS `prepa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `prepa`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prepa
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `annee_scolaire`
--

DROP TABLE IF EXISTS `annee_scolaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annee_scolaire` (
  `idannee_scolaire` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(45) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`idannee_scolaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annee_scolaire`
--

LOCK TABLES `annee_scolaire` WRITE;
/*!40000 ALTER TABLE `annee_scolaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `annee_scolaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `badge` (
  `idbadge` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idbadge`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cours`
--

DROP TABLE IF EXISTS `cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cours` (
  `idcours` int(11) NOT NULL AUTO_INCREMENT,
  `idmatiere` int(11) DEFAULT NULL,
  `idfiliere` int(11) DEFAULT NULL,
  `idniveau_etude` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcours`),
  KEY `fk_cours_matiere_idx` (`idmatiere`),
  KEY `fk_cours_filiere_idx` (`idfiliere`),
  KEY `fk_cours_niveau_etude_idx` (`idniveau_etude`),
  CONSTRAINT `fk_cours_filiere` FOREIGN KEY (`idfiliere`) REFERENCES `filiere` (`idfiliere`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cours_matiere` FOREIGN KEY (`idmatiere`) REFERENCES `matiere` (`idmatiere`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cours_niveau_etude` FOREIGN KEY (`idniveau_etude`) REFERENCES `niveau_etude` (`idniveau_etude`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cours`
--

LOCK TABLES `cours` WRITE;
/*!40000 ALTER TABLE `cours` DISABLE KEYS */;
/*!40000 ALTER TABLE `cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cours_enseignant`
--

DROP TABLE IF EXISTS `cours_enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cours_enseignant` (
  `idcours_enseignant` int(11) NOT NULL AUTO_INCREMENT,
  `idcours` int(11) DEFAULT NULL,
  `idenseignant` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcours_enseignant`),
  KEY `fk_ce_cours_idx` (`idcours`),
  KEY `fk_ce_enseignant_idx` (`idenseignant`),
  CONSTRAINT `fk_ce_cours` FOREIGN KEY (`idcours`) REFERENCES `cours` (`idcours`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ce_enseignant` FOREIGN KEY (`idenseignant`) REFERENCES `enseignant` (`idenseignant`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cours_enseignant`
--

LOCK TABLES `cours_enseignant` WRITE;
/*!40000 ALTER TABLE `cours_enseignant` DISABLE KEYS */;
/*!40000 ALTER TABLE `cours_enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cours_enseignant_date`
--

DROP TABLE IF EXISTS `cours_enseignant_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cours_enseignant_date` (
  `idcours_enseignant_date` int(11) NOT NULL AUTO_INCREMENT,
  `idcours_enseignant` int(11) DEFAULT NULL,
  `jour_semaine` int(11) DEFAULT NULL,
  `heure_debut` datetime DEFAULT NULL,
  `heure_fin` datetime DEFAULT NULL,
  PRIMARY KEY (`idcours_enseignant_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cours_enseignant_date`
--

LOCK TABLES `cours_enseignant_date` WRITE;
/*!40000 ALTER TABLE `cours_enseignant_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `cours_enseignant_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employe`
--

DROP TABLE IF EXISTS `employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employe` (
  `idemploye` int(11) NOT NULL AUTO_INCREMENT,
  `idindividu` int(11) DEFAULT NULL,
  `idposte` int(11) DEFAULT NULL,
  PRIMARY KEY (`idemploye`),
  KEY `fk_employe_individu_idx` (`idindividu`),
  KEY `fk_employe_poste_idx` (`idposte`),
  CONSTRAINT `fk_employe_individu` FOREIGN KEY (`idindividu`) REFERENCES `individu` (`idindividu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employe_poste` FOREIGN KEY (`idposte`) REFERENCES `poste` (`idposte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employe`
--

LOCK TABLES `employe` WRITE;
/*!40000 ALTER TABLE `employe` DISABLE KEYS */;
/*!40000 ALTER TABLE `employe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enseignant` (
  `idenseignant` int(11) NOT NULL AUTO_INCREMENT,
  `idindividu` int(11) DEFAULT NULL,
  PRIMARY KEY (`idenseignant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enseignant`
--

LOCK TABLES `enseignant` WRITE;
/*!40000 ALTER TABLE `enseignant` DISABLE KEYS */;
/*!40000 ALTER TABLE `enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entree`
--

DROP TABLE IF EXISTS `entree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entree` (
  `identree` int(11) NOT NULL AUTO_INCREMENT,
  `idvisite` int(11) DEFAULT NULL,
  `idindividu` int(11) DEFAULT NULL,
  `nom_complet` varchar(255) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `commentaire` longtext,
  `date_entree` datetime DEFAULT NULL,
  `date_sortie` datetime DEFAULT NULL,
  `idbadge` int(11) DEFAULT NULL,
  PRIMARY KEY (`identree`),
  KEY `fk_entree_individu_idx` (`idindividu`),
  KEY `fk_entree_visite_idx` (`idvisite`),
  KEY `fk_entree_badge_idx` (`idbadge`),
  CONSTRAINT `fk_entree_badge` FOREIGN KEY (`idbadge`) REFERENCES `badge` (`idbadge`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entree_individu` FOREIGN KEY (`idindividu`) REFERENCES `individu` (`idindividu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entree_visite` FOREIGN KEY (`idvisite`) REFERENCES `visite` (`idvisite`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entree`
--

LOCK TABLES `entree` WRITE;
/*!40000 ALTER TABLE `entree` DISABLE KEYS */;
/*!40000 ALTER TABLE `entree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etudiant` (
  `idetudiant` int(11) NOT NULL AUTO_INCREMENT,
  `idindividu` int(11) DEFAULT NULL,
  `idfiliere` int(11) DEFAULT NULL,
  `idniveau_etude` int(11) DEFAULT NULL,
  `idannee_scolaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`idetudiant`),
  KEY `fk_etudiant_individu_idx` (`idindividu`),
  KEY `fk_etudiant_filiere_idx` (`idfiliere`),
  KEY `fk_etudiant_niveau_etude_idx` (`idniveau_etude`),
  KEY `fk_etudiant_annee_scolaire_idx` (`idannee_scolaire`),
  CONSTRAINT `fk_etudiant_annee_scolaire` FOREIGN KEY (`idannee_scolaire`) REFERENCES `annee_scolaire` (`idannee_scolaire`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_etudiant_filiere` FOREIGN KEY (`idfiliere`) REFERENCES `filiere` (`idfiliere`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_etudiant_individu` FOREIGN KEY (`idindividu`) REFERENCES `individu` (`idindividu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_etudiant_niveau_etude` FOREIGN KEY (`idniveau_etude`) REFERENCES `niveau_etude` (`idniveau_etude`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etudiant`
--

LOCK TABLES `etudiant` WRITE;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filiere`
--

DROP TABLE IF EXISTS `filiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filiere` (
  `idfiliere` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idfiliere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filiere`
--

LOCK TABLES `filiere` WRITE;
/*!40000 ALTER TABLE `filiere` DISABLE KEYS */;
/*!40000 ALTER TABLE `filiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individu`
--

DROP TABLE IF EXISTS `individu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individu` (
  `idindividu` int(11) NOT NULL AUTO_INCREMENT,
  `noms` varchar(45) DEFAULT NULL,
  `prenoms` varchar(45) DEFAULT NULL,
  `genre` tinyint(1) DEFAULT NULL,
  `civilite` varchar(45) DEFAULT NULL,
  `datenaiss` date DEFAULT NULL,
  `lieunaiss` varchar(45) DEFAULT NULL,
  `tel1` varchar(45) DEFAULT NULL,
  `tel2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `residence` varchar(255) DEFAULT NULL,
  `matricule` varchar(45) DEFAULT NULL,
  `idindividu_photo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idindividu`),
  KEY `fk_individu_photo_idx` (`idindividu_photo`),
  CONSTRAINT `fk_individu_photo` FOREIGN KEY (`idindividu_photo`) REFERENCES `individu_photo` (`idindividu_photo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individu`
--

LOCK TABLES `individu` WRITE;
/*!40000 ALTER TABLE `individu` DISABLE KEYS */;
/*!40000 ALTER TABLE `individu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individu_photo`
--

DROP TABLE IF EXISTS `individu_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individu_photo` (
  `idindividu_photo` int(11) NOT NULL AUTO_INCREMENT,
  `src` longblob,
  `statut` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idindividu_photo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individu_photo`
--

LOCK TABLES `individu_photo` WRITE;
/*!40000 ALTER TABLE `individu_photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `individu_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matiere` (
  `idmatiere` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmatiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiere`
--

LOCK TABLES `matiere` WRITE;
/*!40000 ALTER TABLE `matiere` DISABLE KEYS */;
/*!40000 ALTER TABLE `matiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveau_etude`
--

DROP TABLE IF EXISTS `niveau_etude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveau_etude` (
  `idniveau_etude` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(45) DEFAULT NULL,
  `valeur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idniveau_etude`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveau_etude`
--

LOCK TABLES `niveau_etude` WRITE;
/*!40000 ALTER TABLE `niveau_etude` DISABLE KEYS */;
/*!40000 ALTER TABLE `niveau_etude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periode_creuse`
--

DROP TABLE IF EXISTS `periode_creuse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periode_creuse` (
  `idperiode_creuse` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(45) DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  PRIMARY KEY (`idperiode_creuse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periode_creuse`
--

LOCK TABLES `periode_creuse` WRITE;
/*!40000 ALTER TABLE `periode_creuse` DISABLE KEYS */;
/*!40000 ALTER TABLE `periode_creuse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pointage`
--

DROP TABLE IF EXISTS `pointage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pointage` (
  `idpointage` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(45) DEFAULT NULL,
  `machine` varchar(45) DEFAULT NULL,
  `matricule` varchar(45) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `iomd` varchar(45) DEFAULT NULL,
  `heure` datetime DEFAULT NULL,
  PRIMARY KEY (`idpointage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pointage`
--

LOCK TABLES `pointage` WRITE;
/*!40000 ALTER TABLE `pointage` DISABLE KEYS */;
/*!40000 ALTER TABLE `pointage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poste`
--

DROP TABLE IF EXISTS `poste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poste` (
  `idposte` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `libelle` varchar(45) DEFAULT NULL,
  `idposte_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`idposte`),
  KEY `fk_poste_poste_idx` (`idposte_parent`),
  CONSTRAINT `fk_poste_poste` FOREIGN KEY (`idposte_parent`) REFERENCES `poste` (`idposte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poste`
--

LOCK TABLES `poste` WRITE;
/*!40000 ALTER TABLE `poste` DISABLE KEYS */;
/*!40000 ALTER TABLE `poste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `idutilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `passe` longtext,
  `idindividu` int(11) DEFAULT NULL,
  `idutilisateur_profil` int(11) DEFAULT NULL,
  PRIMARY KEY (`idutilisateur`),
  KEY `fk_utilisateur_individu_idx` (`idindividu`),
  KEY `fk_utilisateur_profil_idx` (`idutilisateur_profil`),
  CONSTRAINT `fk_utilisateur_individu` FOREIGN KEY (`idindividu`) REFERENCES `individu` (`idindividu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_utilisateur_profil` FOREIGN KEY (`idutilisateur_profil`) REFERENCES `utilisateur_profil` (`idutilisateur_profil`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur_profil`
--

DROP TABLE IF EXISTS `utilisateur_profil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur_profil` (
  `idutilisateur_profil` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `voir_employe` tinyint(1) DEFAULT NULL,
  `voir_enseignant` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idutilisateur_profil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur_profil`
--

LOCK TABLES `utilisateur_profil` WRITE;
/*!40000 ALTER TABLE `utilisateur_profil` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilisateur_profil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur_profil_filiere`
--

DROP TABLE IF EXISTS `utilisateur_profil_filiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur_profil_filiere` (
  `idutilisateur_profil_filiere` int(11) NOT NULL AUTO_INCREMENT,
  `idutilisateur_profil` int(11) DEFAULT NULL,
  `idfiliere` int(11) DEFAULT NULL,
  PRIMARY KEY (`idutilisateur_profil_filiere`),
  KEY `fk_upf_up_idx` (`idutilisateur_profil`),
  KEY `fk_upf_filiere_idx` (`idfiliere`),
  CONSTRAINT `fk_upf_filiere` FOREIGN KEY (`idfiliere`) REFERENCES `filiere` (`idfiliere`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_upf_up` FOREIGN KEY (`idutilisateur_profil`) REFERENCES `utilisateur_profil` (`idutilisateur_profil`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur_profil_filiere`
--

LOCK TABLES `utilisateur_profil_filiere` WRITE;
/*!40000 ALTER TABLE `utilisateur_profil_filiere` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilisateur_profil_filiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visite`
--

DROP TABLE IF EXISTS `visite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visite` (
  `idvisite` int(11) NOT NULL AUTO_INCREMENT,
  `idindividu` int(11) DEFAULT NULL,
  `nom_complet` varchar(255) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `commentaire` longtext,
  PRIMARY KEY (`idvisite`),
  KEY `fk_visite_individu_idx` (`idindividu`),
  CONSTRAINT `fk_visite_individu` FOREIGN KEY (`idindividu`) REFERENCES `individu` (`idindividu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visite`
--

LOCK TABLES `visite` WRITE;
/*!40000 ALTER TABLE `visite` DISABLE KEYS */;
/*!40000 ALTER TABLE `visite` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-29 15:11:11
