CREATE DATABASE  IF NOT EXISTS `sigf_v2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sigf_v2`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: sigf_v2
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `abono_bus`
--

DROP TABLE IF EXISTS `abono_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abono_bus` (
  `abono_bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `abono_bus_id_tipo_abono` int(11) unsigned NOT NULL,
  `abono_bus_id_bus` int(11) unsigned NOT NULL,
  `abono_bus_fecha_inicio` date NOT NULL,
  `abono_bus_fecha_termino` date NOT NULL,
  `abono_bus_cuota_actual` int(11) unsigned NOT NULL,
  `abono_bus_total_cuotas` int(11) unsigned NOT NULL DEFAULT '0',
  `abono_bus_monto_fijo` int(11) unsigned NOT NULL,
  `abono_bus_descripcion` varchar(100) DEFAULT NULL,
  `abono_bus_activo` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `abono_bus_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`abono_bus_id`),
  KEY `fk_abono_bus_id_tipo_abono_idx` (`abono_bus_id_tipo_abono`),
  KEY `fk_abono_bus_id_bus_idx` (`abono_bus_id_bus`),
  CONSTRAINT `fk_abono_bus_id_bus` FOREIGN KEY (`abono_bus_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_abono_bus_id_tipo_abono` FOREIGN KEY (`abono_bus_id_tipo_abono`) REFERENCES `tipo_abono` (`tipo_abono_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abono_bus`
--

LOCK TABLES `abono_bus` WRITE;
/*!40000 ALTER TABLE `abono_bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `abono_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abono_liquidacion`
--

DROP TABLE IF EXISTS `abono_liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abono_liquidacion` (
  `abono_liquidacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `abono_liquidacion_id_abono` int(11) unsigned NOT NULL,
  `abono_liquidacion_id_liquidacion_empresa` int(11) unsigned NOT NULL,
  `abono_liquidacion_monto` int(11) unsigned NOT NULL,
  `abono_liquidacion_descripcion` varchar(100) NOT NULL DEFAULT ' ',
  `abono_liquidacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`abono_liquidacion_id`),
  KEY `fk_abono_liquidacion_id_cargo_idx` (`abono_liquidacion_id_abono`),
  KEY `fk_abl_id_liquidacion_empresa_idx` (`abono_liquidacion_id_liquidacion_empresa`),
  CONSTRAINT `fk_abl_id_abono` FOREIGN KEY (`abono_liquidacion_id_abono`) REFERENCES `abono_bus` (`abono_bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_abl_id_liquidacion_empresa` FOREIGN KEY (`abono_liquidacion_id_liquidacion_empresa`) REFERENCES `liquidacion_empresa` (`liquidacion_empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abono_liquidacion`
--

LOCK TABLES `abono_liquidacion` WRITE;
/*!40000 ALTER TABLE `abono_liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `abono_liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administracion_mensual`
--

DROP TABLE IF EXISTS `administracion_mensual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administracion_mensual` (
  `administracion_mensual_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `administracion_mensual_fecha` date NOT NULL,
  `administracion_mensual_valor` int(11) unsigned NOT NULL,
  `administracion_mensual_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`administracion_mensual_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administracion_mensual`
--

LOCK TABLES `administracion_mensual` WRITE;
/*!40000 ALTER TABLE `administracion_mensual` DISABLE KEYS */;
/*!40000 ALTER TABLE `administracion_mensual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignacion_familiar`
--

DROP TABLE IF EXISTS `asignacion_familiar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignacion_familiar` (
  `asignacion_familiar_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `asignacion_familiar_codigo` varchar(45) NOT NULL,
  `asignacion_familiar_tramo` varchar(255) NOT NULL,
  `asignacion_familiar_monto` int(11) NOT NULL,
  `asignacion_familiar_desde` int(11) NOT NULL,
  `asignacion_familiar_hasta` int(11) NOT NULL,
  PRIMARY KEY (`asignacion_familiar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion_familiar`
--

LOCK TABLES `asignacion_familiar` WRITE;
/*!40000 ALTER TABLE `asignacion_familiar` DISABLE KEYS */;
INSERT INTO `asignacion_familiar` VALUES (1,'SIN INFORMACIÓN ','0',0,0,0);
/*!40000 ALTER TABLE `asignacion_familiar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banco`
--

DROP TABLE IF EXISTS `banco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banco` (
  `banco_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `banco_nombre` varchar(45) NOT NULL,
  `banco_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`banco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banco`
--

LOCK TABLES `banco` WRITE;
/*!40000 ALTER TABLE `banco` DISABLE KEYS */;
INSERT INTO `banco` VALUES (18,'Sin Datos',NULL);
/*!40000 ALTER TABLE `banco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto` (
  `boleto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `boleto_nombre` varchar(45) NOT NULL,
  `boleto_activo` tinyint(1) NOT NULL DEFAULT '1',
  `boleto_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`boleto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
INSERT INTO `boleto` VALUES (1,'Directo',1,'2017-04-20 12:57:36'),(2,'Plan Viña',1,'2017-04-20 12:57:44'),(3,'Local',1,'2017-04-20 12:57:51'),(4,'Escolar Directo',1,'2017-04-20 12:57:56'),(5,'Escolar Local',1,'2017-04-20 12:58:11'),(6,'Boleto de Prueba',1,'2017-04-26 15:26:18');
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus` (
  `bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bus_id_unidad_negocio` int(11) unsigned NOT NULL,
  `bus_id_terminal` int(11) unsigned NOT NULL,
  `bus_id_empresa` int(11) unsigned NOT NULL,
  `bus_id_flota` int(11) unsigned NOT NULL,
  `bus_id_proceso_recaudacion` int(11) unsigned NOT NULL,
  `bus_id_estado_bus` int(11) unsigned NOT NULL,
  `bus_id_modelo` int(11) unsigned NOT NULL,
  `bus_numero` int(11) unsigned NOT NULL,
  `bus_patente` varchar(45) NOT NULL,
  `bus_administrador` varchar(100) DEFAULT NULL,
  `bus_anio` int(11) unsigned NOT NULL,
  `bus_fecha_revision_tecnica` date DEFAULT NULL,
  `bus_numero_motor` varchar(45) DEFAULT NULL,
  `bus_numero_chasis` varchar(45) DEFAULT NULL,
  `bus_carroceria` varchar(45) DEFAULT NULL,
  `bus_activo` tinyint(1) DEFAULT '1',
  `bus_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bus_id`),
  KEY `fk_bus_id_unidad_negocio_idx` (`bus_id_unidad_negocio`),
  KEY `fk_bus_id_empresa_idx` (`bus_id_empresa`),
  KEY `fk_bus_id_flota_idx` (`bus_id_flota`),
  KEY `fk_bus_id_modelo_idx` (`bus_id_modelo`),
  KEY `fk_bus_id_terminal_idx` (`bus_id_terminal`),
  KEY `fk_bus_id_estado_bus_idx` (`bus_id_estado_bus`),
  KEY `fk_bus_id_proceso_recaudacion_idx` (`bus_id_proceso_recaudacion`),
  CONSTRAINT `fk_bus_id_empresa` FOREIGN KEY (`bus_id_empresa`) REFERENCES `empresa` (`empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_id_estado_bus` FOREIGN KEY (`bus_id_estado_bus`) REFERENCES `estado_bus` (`estado_bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_id_flota` FOREIGN KEY (`bus_id_flota`) REFERENCES `flota` (`flota_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_id_modelo` FOREIGN KEY (`bus_id_modelo`) REFERENCES `modelo_marca_bus` (`modelo_marca_bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_id_proceso_recaudacion` FOREIGN KEY (`bus_id_proceso_recaudacion`) REFERENCES `proceso_recaudacion` (`proceso_recaudacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_id_terminal` FOREIGN KEY (`bus_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_id_unidad_negocio` FOREIGN KEY (`bus_id_unidad_negocio`) REFERENCES `unidad_negocio` (`unidad_negocio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=756 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (752,4,1,1138,107,1,1,40,3,'FRTY56','SIN ADMINISTRADOR',2014,NULL,'1','','1',1,NULL),(753,4,1,1138,107,1,1,40,1,'CTVG34','SIN ADMINISTRADOR',2010,'2017-04-26','1','1','1',1,NULL),(754,4,1,1138,107,1,2,40,2,'FGVB72','con ADMINISTRADOR',2014,'2017-04-26','1','1','1',1,NULL),(755,4,1,1138,107,1,1,40,151,'DSFR45','SIN ADMINISTRADOR',2012,'1986-12-19','1234567','1234567','Metalpar',1,NULL);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_servicio`
--

DROP TABLE IF EXISTS `bus_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus_servicio` (
  `bus_servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bus_servicio_id_bus` int(11) unsigned NOT NULL,
  `bus_servicio_id_servicio` int(11) unsigned NOT NULL,
  `bus_servicio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bus_servicio_id`),
  KEY `fk_bus_servicio_id_bus_idx` (`bus_servicio_id_bus`),
  KEY `fk_bus_servicio_id_servicio_idx` (`bus_servicio_id_servicio`),
  CONSTRAINT `fk_bus_servicio_id_bus` FOREIGN KEY (`bus_servicio_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_servicio_id_servicio` FOREIGN KEY (`bus_servicio_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_servicio`
--

LOCK TABLES `bus_servicio` WRITE;
/*!40000 ALTER TABLE `bus_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caja_compensacion`
--

DROP TABLE IF EXISTS `caja_compensacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caja_compensacion` (
  `caja_compensacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `caja_compensacion_nombre` varchar(255) NOT NULL,
  `caja_compensacion_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`caja_compensacion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja_compensacion`
--

LOCK TABLES `caja_compensacion` WRITE;
/*!40000 ALTER TABLE `caja_compensacion` DISABLE KEYS */;
INSERT INTO `caja_compensacion` VALUES (1,'SIN CAAF','2017-04-20 13:00:40');
/*!40000 ALTER TABLE `caja_compensacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caja_terminal`
--

DROP TABLE IF EXISTS `caja_terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caja_terminal` (
  `caja_terminal_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `caja_terminal_id_terminal` int(11) unsigned NOT NULL,
  `caja_terminal_id_usuario` int(11) unsigned NOT NULL,
  `caja_terminal_nombre_caja` varchar(45) NOT NULL,
  `caja_terminal_activa` tinyint(1) NOT NULL DEFAULT '1',
  `caja_terminal_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`caja_terminal_id`),
  KEY `fk_proceso_recaudacion_id_usuario_idx` (`caja_terminal_id_usuario`),
  KEY `fk_caja_terminal_id_terminal_idx` (`caja_terminal_id_terminal`),
  CONSTRAINT `fk_caja_terminal_id_terminal` FOREIGN KEY (`caja_terminal_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_caja_terminal_id_usuario` FOREIGN KEY (`caja_terminal_id_usuario`) REFERENCES `usuario` (`usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja_terminal`
--

LOCK TABLES `caja_terminal` WRITE;
/*!40000 ALTER TABLE `caja_terminal` DISABLE KEYS */;
INSERT INTO `caja_terminal` VALUES (38,1,1,'Caja N° 1',1,'2017-04-20 16:58:05'),(39,24,1,'Caja Combustible',1,'2017-04-29 12:54:52'),(40,24,1,'Caja Minutos',1,'2017-04-29 13:58:29');
/*!40000 ALTER TABLE `caja_terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calle`
--

DROP TABLE IF EXISTS `calle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calle` (
  `calle_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `calle_id_comuna` int(11) unsigned NOT NULL,
  `calle_nombre` varchar(45) NOT NULL,
  `calle_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`calle_id`),
  KEY `fk_calle_id_comuna_idx` (`calle_id_comuna`),
  CONSTRAINT `fk_calle_id_comuna` FOREIGN KEY (`calle_id_comuna`) REFERENCES `comuna` (`comuna_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calle`
--

LOCK TABLES `calle` WRITE;
/*!40000 ALTER TABLE `calle` DISABLE KEYS */;
/*!40000 ALTER TABLE `calle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calle_servicio`
--

DROP TABLE IF EXISTS `calle_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calle_servicio` (
  `calle_servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `calle_servicio_id_calle` int(11) unsigned NOT NULL,
  `calle_servicio_id_servicio` int(11) unsigned NOT NULL,
  `calle_servicio_numero_orden` int(11) unsigned NOT NULL,
  `calle_servicio_sentido` tinyint(1) DEFAULT NULL,
  `calle_servicio_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`calle_servicio_id`),
  KEY `fk_calle_servicio_id_calle_idx` (`calle_servicio_id_calle`),
  KEY `fk_calle_servicio_id_servicio_idx` (`calle_servicio_id_servicio`),
  CONSTRAINT `fk_calle_servicio_id_calle` FOREIGN KEY (`calle_servicio_id_calle`) REFERENCES `calle` (`calle_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_calle_servicio_id_servicio` FOREIGN KEY (`calle_servicio_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calle_servicio`
--

LOCK TABLES `calle_servicio` WRITE;
/*!40000 ALTER TABLE `calle_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `calle_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carga_retroactiva`
--

DROP TABLE IF EXISTS `carga_retroactiva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carga_retroactiva` (
  `carga_retroactiva_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `carga_retroactiva_id_trabajador` int(11) unsigned NOT NULL,
  `carga_retroactiva_id_carga_trabajador` int(11) unsigned NOT NULL,
  `carga_retroactiva_monto` int(11) NOT NULL,
  `carga_retroactiva_fecha` date NOT NULL,
  `carga_retroactiva_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`carga_retroactiva_id`),
  KEY `fk_carga_retroactiva_id_trabajador_idx` (`carga_retroactiva_id_trabajador`),
  KEY `fk_carga_retroactiva_id_carga_idx` (`carga_retroactiva_id_carga_trabajador`),
  CONSTRAINT `fk_carga_retroactiva_id_carga` FOREIGN KEY (`carga_retroactiva_id_carga_trabajador`) REFERENCES `carga_trabajador` (`carga_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_carga_retroactiva_id_trabajador` FOREIGN KEY (`carga_retroactiva_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carga_retroactiva`
--

LOCK TABLES `carga_retroactiva` WRITE;
/*!40000 ALTER TABLE `carga_retroactiva` DISABLE KEYS */;
/*!40000 ALTER TABLE `carga_retroactiva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carga_trabajador`
--

DROP TABLE IF EXISTS `carga_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carga_trabajador` (
  `carga_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `carga_trabajador_id_trabajador` int(11) unsigned NOT NULL,
  `carga_trabajador_id_tipo_carga` int(11) unsigned NOT NULL,
  `carga_trabajador_id_parentesco_carga` int(11) unsigned NOT NULL,
  `carga_trabajador_apellidos` varchar(45) NOT NULL,
  `carga_trabajador_nombres` varchar(45) NOT NULL,
  `carga_trabajador_rut` varchar(9) NOT NULL,
  `carga_trabajador_fecha_nacimiento` date DEFAULT NULL,
  `carga_trabajador_activa` tinyint(1) NOT NULL,
  `carga_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`carga_trabajador_id`),
  KEY `fk_cargas_trabajador_idtipo_carga_idx` (`carga_trabajador_id_tipo_carga`),
  KEY `fk_cargas_trabajador_idparentesco_carga_idx` (`carga_trabajador_id_parentesco_carga`),
  KEY `fk_cargas_trabajador_idtrabajador_idx` (`carga_trabajador_id_trabajador`),
  CONSTRAINT `fk_cargas_trabajador_idparentesco_carga` FOREIGN KEY (`carga_trabajador_id_parentesco_carga`) REFERENCES `parentesco_carga` (`parentesco_carga_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cargas_trabajador_idtipo_carga` FOREIGN KEY (`carga_trabajador_id_tipo_carga`) REFERENCES `tipo_carga_familiar` (`tipo_carga_familiar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cargas_trabajador_idtrabajador` FOREIGN KEY (`carga_trabajador_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carga_trabajador`
--

LOCK TABLES `carga_trabajador` WRITE;
/*!40000 ALTER TABLE `carga_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `carga_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_bus`
--

DROP TABLE IF EXISTS `cargo_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_bus` (
  `cargo_bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cargo_bus_id_tipo_cargo` int(11) unsigned NOT NULL,
  `cargo_bus_id_bus` int(11) unsigned NOT NULL,
  `cargo_bus_fecha_inicio` date NOT NULL,
  `cargo_bus_fecha_termino` date NOT NULL,
  `cargo_bus_cuota_actual` int(11) unsigned DEFAULT '0',
  `cargo_bus_total_cuotas` int(11) unsigned DEFAULT '0',
  `cargo_bus_monto_fijo` int(11) unsigned NOT NULL,
  `cargo_bus_descripcion` varchar(100) DEFAULT ' ',
  `cargo_bus_activo` tinyint(1) NOT NULL DEFAULT '1',
  `cargo_bus_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cargo_bus_id`),
  KEY `fk_cargo_bus_id_tipo_cargo_idx` (`cargo_bus_id_tipo_cargo`),
  KEY `fk_cargo_bus_id_bus_idx` (`cargo_bus_id_bus`),
  CONSTRAINT `fk_cargo_bus_id_bus` FOREIGN KEY (`cargo_bus_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cargo_bus_id_tipo_cargo` FOREIGN KEY (`cargo_bus_id_tipo_cargo`) REFERENCES `tipo_cargo` (`tipo_cargo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_bus`
--

LOCK TABLES `cargo_bus` WRITE;
/*!40000 ALTER TABLE `cargo_bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_liquidacion`
--

DROP TABLE IF EXISTS `cargo_liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_liquidacion` (
  `cargo_liquidacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cargo_liquidacion_id_cargo` int(11) unsigned NOT NULL,
  `cargo_liquidacion_id_liquidacion_empresa` int(11) unsigned NOT NULL,
  `cargo_liquidacion_monto` int(11) NOT NULL,
  `cargo_liquidacion_descripcion` varchar(100) NOT NULL DEFAULT ' ',
  `cargo_liquidacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cargo_liquidacion_id`),
  KEY `fk_carl_id_cargo_bus_idx` (`cargo_liquidacion_id_cargo`),
  KEY `fk_carl_id_liquidacion_empresa_idx` (`cargo_liquidacion_id_liquidacion_empresa`),
  CONSTRAINT `fk_carl_id_cargo_bus` FOREIGN KEY (`cargo_liquidacion_id_cargo`) REFERENCES `cargo_bus` (`cargo_bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_carl_id_liquidacion_empresa` FOREIGN KEY (`cargo_liquidacion_id_liquidacion_empresa`) REFERENCES `liquidacion_empresa` (`liquidacion_empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_liquidacion`
--

LOCK TABLES `cargo_liquidacion` WRITE;
/*!40000 ALTER TABLE `cargo_liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo_liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `causal_finiquito`
--

DROP TABLE IF EXISTS `causal_finiquito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `causal_finiquito` (
  `causal_finiquito_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `causal_finiquito_codigo` varchar(45) NOT NULL,
  `causal_finiquito_descripcion` varchar(250) NOT NULL,
  PRIMARY KEY (`causal_finiquito_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `causal_finiquito`
--

LOCK TABLES `causal_finiquito` WRITE;
/*!40000 ALTER TABLE `causal_finiquito` DISABLE KEYS */;
/*!40000 ALTER TABLE `causal_finiquito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centro_costo`
--

DROP TABLE IF EXISTS `centro_costo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centro_costo` (
  `centro_costo_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `centro_costo_nombre` varchar(100) NOT NULL,
  `centro_costo_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`centro_costo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centro_costo`
--

LOCK TABLES `centro_costo` WRITE;
/*!40000 ALTER TABLE `centro_costo` DISABLE KEYS */;
INSERT INTO `centro_costo` VALUES (1,'ADMINISTRACIÓN ','2017-04-29 12:17:53');
/*!40000 ALTER TABLE `centro_costo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `ciudad_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ciudad_id_region` int(11) unsigned NOT NULL,
  `ciudad_nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ciudad_id`),
  KEY `fk_ciudad_id_region_idx` (`ciudad_id_region`),
  CONSTRAINT `fk_ciudad_id_region` FOREIGN KEY (`ciudad_id_region`) REFERENCES `region` (`region_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,1,'Villa Alemana');
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_boleto`
--

DROP TABLE IF EXISTS `compra_boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_boleto` (
  `compra_boleto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `compra_boleto_folio_factura` int(11) unsigned NOT NULL,
  `compra_boleto_total` int(11) NOT NULL,
  `compra_boleto_fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`compra_boleto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_boleto`
--

LOCK TABLES `compra_boleto` WRITE;
/*!40000 ALTER TABLE `compra_boleto` DISABLE KEYS */;
INSERT INTO `compra_boleto` VALUES (1,12311,105000,'2017-04-28 00:00:00'),(2,1234,12000,'2017-04-29 00:00:00'),(3,123123,100000,'2017-04-29 00:00:00'),(4,113211,70000,'2017-05-02 00:00:00'),(5,1234,70000,'2017-05-02 00:00:00'),(6,1234,45000,'2017-05-02 00:00:00');
/*!40000 ALTER TABLE `compra_boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_combustible`
--

DROP TABLE IF EXISTS `compra_combustible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_combustible` (
  `compra_combustible_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `compra_combustible_id_tipo` int(11) unsigned NOT NULL,
  `compra_combustible_fecha_emision_factura` date NOT NULL,
  `compra_combustible_fecha_pago` date NOT NULL,
  `compra_combustible_cantidad_litros` int(11) unsigned NOT NULL,
  `compra_combustible_pbase_siu` float(6,4) unsigned NOT NULL,
  `compra_combustible_iev_u` float(6,4) unsigned NOT NULL,
  `compra_combustible_ief_u` float(6,4) unsigned NOT NULL,
  `compra_combustible_dia_vig_ie` date NOT NULL,
  `compra_combustible_base_afecta` int(11) unsigned NOT NULL,
  `compra_combustible_feep` int(11) unsigned NOT NULL,
  `compra_combustible_iev` int(11) unsigned NOT NULL,
  `compra_combustible_ief` int(11) unsigned NOT NULL,
  `compra_combustible_iva` int(11) unsigned NOT NULL,
  `compra_combustible_total_compra` int(11) unsigned NOT NULL,
  `compra_combustible_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`compra_combustible_id`),
  KEY `fk_compra_combustible_id_tipo_idx` (`compra_combustible_id_tipo`),
  CONSTRAINT `fk_compra_combustible_id_tipo` FOREIGN KEY (`compra_combustible_id_tipo`) REFERENCES `tipo_combustible` (`tipo_combustible_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_combustible`
--

LOCK TABLES `compra_combustible` WRITE;
/*!40000 ALTER TABLE `compra_combustible` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra_combustible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comuna`
--

DROP TABLE IF EXISTS `comuna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comuna` (
  `comuna_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `comuna_id_ciudad` int(11) unsigned NOT NULL,
  `comuna_nombre` varchar(45) NOT NULL,
  `comuna_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comuna_id`),
  KEY `fk_comuna_id_ciudad_idx` (`comuna_id_ciudad`),
  CONSTRAINT `fk_comuna_id_ciudad` FOREIGN KEY (`comuna_id_ciudad`) REFERENCES `ciudad` (`ciudad_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comuna`
--

LOCK TABLES `comuna` WRITE;
/*!40000 ALTER TABLE `comuna` DISABLE KEYS */;
INSERT INTO `comuna` VALUES (1,1,'Villa Alemana','2017-04-20 10:27:48');
/*!40000 ALTER TABLE `comuna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato_unidad`
--

DROP TABLE IF EXISTS `contrato_unidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato_unidad` (
  `contrato_unidad_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `contrato_unidad_id_unidad_negocio` int(11) unsigned NOT NULL,
  `contrato_unidad_identificador` varchar(45) NOT NULL,
  `contrato_unidad_desde` date NOT NULL,
  `contrato_unidad_hasta` date NOT NULL,
  `contrato_unidad_nombre` varchar(100) NOT NULL,
  `contrato_unidad_descripcion` varchar(400) DEFAULT NULL,
  `contrato_unidad_fecha_ingreso` datetime NOT NULL,
  PRIMARY KEY (`contrato_unidad_id`),
  KEY `fk_contrato_unidad_id_unidad_idx` (`contrato_unidad_id_unidad_negocio`),
  CONSTRAINT `fk_contrato_unidad_id_unidad` FOREIGN KEY (`contrato_unidad_id_unidad_negocio`) REFERENCES `unidad_negocio` (`unidad_negocio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato_unidad`
--

LOCK TABLES `contrato_unidad` WRITE;
/*!40000 ALTER TABLE `contrato_unidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato_unidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control`
--

DROP TABLE IF EXISTS `control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `control` (
  `control_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `control_id_tipo_control` int(11) unsigned NOT NULL,
  `control_nombre` varchar(45) NOT NULL,
  `control_latitud` double(12,8) NOT NULL,
  `control_longitud` double(12,8) NOT NULL,
  `control_radio` int(11) NOT NULL,
  `control_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`control_id`),
  KEY `fk_control_id_tipo_control_idx` (`control_id_tipo_control`),
  CONSTRAINT `fk_control_id_tipo_control` FOREIGN KEY (`control_id_tipo_control`) REFERENCES `tipo_control` (`tipo_control_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control`
--

LOCK TABLES `control` WRITE;
/*!40000 ALTER TABLE `control` DISABLE KEYS */;
/*!40000 ALTER TABLE `control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_asistencia`
--

DROP TABLE IF EXISTS `control_asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `control_asistencia` (
  `control_asistencia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `control_asistencia_id_trabajador` int(11) unsigned NOT NULL,
  `control_asistencia_horario_salida` datetime DEFAULT NULL,
  `control_asistencia_horario_entrada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`control_asistencia_id`),
  KEY `fk_control_asistencia_id_trabajador_idx` (`control_asistencia_id_trabajador`),
  CONSTRAINT `fk_control_asistencia_id_trabajador` FOREIGN KEY (`control_asistencia_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_asistencia`
--

LOCK TABLES `control_asistencia` WRITE;
/*!40000 ALTER TABLE `control_asistencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_horario_servicio`
--

DROP TABLE IF EXISTS `control_horario_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `control_horario_servicio` (
  `control_horario_servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `control_horario_servicio_id_horario_servicio` int(11) unsigned NOT NULL,
  `control_horario_servicio_id_control_servicio` int(11) unsigned NOT NULL,
  `control_horario_servicio_tiempo` int(2) NOT NULL,
  `control_horario_servicio_multa` int(5) NOT NULL,
  `control_horario_servicio_peso` int(11) NOT NULL,
  PRIMARY KEY (`control_horario_servicio_id`),
  KEY `fk_control_horario_servicio_id_horario_idx` (`control_horario_servicio_id_horario_servicio`),
  KEY `fk_control_horario_servicio_id_control_idx` (`control_horario_servicio_id_control_servicio`),
  CONSTRAINT `fk_control_horario_servicio_id_control` FOREIGN KEY (`control_horario_servicio_id_control_servicio`) REFERENCES `control_servicio` (`control_servicio_id_control_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_control_horario_servicio_id_horario` FOREIGN KEY (`control_horario_servicio_id_horario_servicio`) REFERENCES `horario_servicio` (`horario_servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_horario_servicio`
--

LOCK TABLES `control_horario_servicio` WRITE;
/*!40000 ALTER TABLE `control_horario_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_horario_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_servicio`
--

DROP TABLE IF EXISTS `control_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `control_servicio` (
  `control_servicio_id_control_servicio` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `control_servicio_id_control` int(11) unsigned NOT NULL,
  `control_servicio_id_servicio` int(11) unsigned NOT NULL,
  `control_servicio_tiempo` time NOT NULL,
  `control_servicio_numero_orden` int(11) NOT NULL,
  `control_servicio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`control_servicio_id_control_servicio`),
  KEY `fk_control_servicio_id_control_idx` (`control_servicio_id_control`),
  KEY `fk_control_servicio_id_servicio_idx` (`control_servicio_id_servicio`),
  CONSTRAINT `fk_control_servicio_id_control` FOREIGN KEY (`control_servicio_id_control`) REFERENCES `control` (`control_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_control_servicio_id_servicio` FOREIGN KEY (`control_servicio_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_servicio`
--

LOCK TABLES `control_servicio` WRITE;
/*!40000 ALTER TABLE `control_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `control_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctv_resumen`
--

DROP TABLE IF EXISTS `ctv_resumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctv_resumen` (
  `ctv_resumen_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ctv_resumen_id_resumen_recaudacion` int(11) unsigned NOT NULL,
  `ctv_resumen_numero` int(11) unsigned NOT NULL,
  `ctv_resumen_cantidad_bolsas` int(11) unsigned NOT NULL,
  `ctv_resumen_total_transportado` int(11) unsigned NOT NULL,
  `ctv_resumen_fecha_hora_retiro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ctv_resumen_id`),
  UNIQUE KEY `numero_ctv_UNIQUE` (`ctv_resumen_numero`),
  KEY `fk_ctv_resumen_id_resumen_idx` (`ctv_resumen_id_resumen_recaudacion`),
  CONSTRAINT `fk_ctv_resumen_id_resumen` FOREIGN KEY (`ctv_resumen_id_resumen_recaudacion`) REFERENCES `resumen_recaudacion` (`resumen_recaudacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctv_resumen`
--

LOCK TABLES `ctv_resumen` WRITE;
/*!40000 ALTER TABLE `ctv_resumen` DISABLE KEYS */;
/*!40000 ALTER TABLE `ctv_resumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_bancaria`
--

DROP TABLE IF EXISTS `cuenta_bancaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_bancaria` (
  `cuenta_bancaria_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cuenta_bancaria_id_banco` int(11) unsigned NOT NULL,
  `cuenta_bancaria_id_tipo_cuenta` int(11) unsigned NOT NULL,
  `cuenta_bancaria_numero` int(11) unsigned NOT NULL,
  `cuenta_bancaria_nombre_titular` varchar(250) NOT NULL,
  `cuenta_bancaria_rut_titular` varchar(9) NOT NULL,
  `cuenta_bancaria_activa` tinyint(1) NOT NULL DEFAULT '1',
  `cuenta_bancaria_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cuenta_bancaria_id`),
  UNIQUE KEY `numero_cuenta_bancaria_UNIQUE` (`cuenta_bancaria_numero`),
  KEY `fk_cuenta_bancaria_id_banco_idx` (`cuenta_bancaria_id_banco`),
  KEY `fk_cuenta_bancaria_id_tipo_cuenta_idx` (`cuenta_bancaria_id_tipo_cuenta`),
  CONSTRAINT `fk_cuenta_bancaria_id_banco` FOREIGN KEY (`cuenta_bancaria_id_banco`) REFERENCES `banco` (`banco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_bancaria_id_tipo_cuenta` FOREIGN KEY (`cuenta_bancaria_id_tipo_cuenta`) REFERENCES `tipo_cuenta_banco` (`tipo_cuenta_banco_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_bancaria`
--

LOCK TABLES `cuenta_bancaria` WRITE;
/*!40000 ALTER TABLE `cuenta_bancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_bancaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_banco_empresa`
--

DROP TABLE IF EXISTS `cuenta_banco_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_banco_empresa` (
  `cuenta_banco_empresa_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cuenta_banco_empresa_id_empresa` int(11) unsigned NOT NULL,
  `cuenta_banco_empresa_id_cuenta` int(11) unsigned NOT NULL,
  PRIMARY KEY (`cuenta_banco_empresa_id`),
  KEY `fk_cuenta_empresa_empresa_idx` (`cuenta_banco_empresa_id_empresa`),
  KEY `fk_cuenta_empresa_cuenta_idx` (`cuenta_banco_empresa_id_cuenta`),
  CONSTRAINT `fk_cuenta_empresa_cuenta` FOREIGN KEY (`cuenta_banco_empresa_id_cuenta`) REFERENCES `cuenta_bancaria` (`cuenta_bancaria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_empresa_empresa` FOREIGN KEY (`cuenta_banco_empresa_id_empresa`) REFERENCES `empresa` (`empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_banco_empresa`
--

LOCK TABLES `cuenta_banco_empresa` WRITE;
/*!40000 ALTER TABLE `cuenta_banco_empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_banco_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_banco_proceso`
--

DROP TABLE IF EXISTS `cuenta_banco_proceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_banco_proceso` (
  `cuenta_banco_proceso_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cuenta_banco_proceso_id_banco` int(11) unsigned NOT NULL,
  `cuenta_banco_proceso_id_proceso` int(11) unsigned NOT NULL,
  PRIMARY KEY (`cuenta_banco_proceso_id`),
  KEY `fk_cuenta_banco_idx` (`cuenta_banco_proceso_id_banco`),
  KEY `fk_cuenta_banco_proceso_idx` (`cuenta_banco_proceso_id_proceso`),
  CONSTRAINT `fk_cuenta_banco_banco` FOREIGN KEY (`cuenta_banco_proceso_id_banco`) REFERENCES `cuenta_bancaria` (`cuenta_bancaria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_banco_proceso` FOREIGN KEY (`cuenta_banco_proceso_id_proceso`) REFERENCES `proceso_recaudacion` (`proceso_recaudacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_banco_proceso`
--

LOCK TABLES `cuenta_banco_proceso` WRITE;
/*!40000 ALTER TABLE `cuenta_banco_proceso` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_banco_proceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_banco_trabajador`
--

DROP TABLE IF EXISTS `cuenta_banco_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_banco_trabajador` (
  `cuenta_banco_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cuenta_banco_trabajador_trabajador` int(11) unsigned NOT NULL,
  `cuenta_banco_trabajador_cuenta` int(11) unsigned NOT NULL,
  PRIMARY KEY (`cuenta_banco_trabajador_id`),
  KEY `fk_cuenta_trabajador_trabajador_idx` (`cuenta_banco_trabajador_trabajador`),
  KEY `fk_cuenta_trabajador_cuenta_idx` (`cuenta_banco_trabajador_cuenta`),
  CONSTRAINT `fk_cuenta_trabajador_cuenta` FOREIGN KEY (`cuenta_banco_trabajador_cuenta`) REFERENCES `cuenta_bancaria` (`cuenta_bancaria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_trabajador_trabajador` FOREIGN KEY (`cuenta_banco_trabajador_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_banco_trabajador`
--

LOCK TABLES `cuenta_banco_trabajador` WRITE;
/*!40000 ALTER TABLE `cuenta_banco_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_banco_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `departamento_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `departamento_nombre` varchar(45) NOT NULL,
  `departamento_activo` tinyint(1) NOT NULL,
  `departamento_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`departamento_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuento_liquidacion`
--

DROP TABLE IF EXISTS `descuento_liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descuento_liquidacion` (
  `descuento_liquidacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `descuento_liquidacion_id_descuento_trabajador` int(11) unsigned NOT NULL,
  `descuento_liquidacion_id_liquidacion_sueldo` int(11) unsigned NOT NULL,
  `descuento_liquidacion_id_relacion_laboral` int(11) unsigned NOT NULL,
  `descuento_liquidacion_fecha` date NOT NULL,
  `descuento_liquidacion_monto` int(11) NOT NULL DEFAULT '0',
  `descuento_liquidacion_descripcion` varchar(45) NOT NULL DEFAULT ' ',
  `descuento_liquidacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`descuento_liquidacion_id`),
  KEY `fk_dscl_id_id_descuento_idx` (`descuento_liquidacion_id_descuento_trabajador`),
  KEY `fk_dscl_id_liquidacion_trabajador_idx` (`descuento_liquidacion_id_liquidacion_sueldo`),
  CONSTRAINT `fk_dscl_id_descuento` FOREIGN KEY (`descuento_liquidacion_id_descuento_trabajador`) REFERENCES `descuento_trabajador` (`descuento_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dscl_id_liquidacion_trabajador` FOREIGN KEY (`descuento_liquidacion_id_liquidacion_sueldo`) REFERENCES `liquidacion_sueldo` (`liquidacion_sueldo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuento_liquidacion`
--

LOCK TABLES `descuento_liquidacion` WRITE;
/*!40000 ALTER TABLE `descuento_liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `descuento_liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuento_trabajador`
--

DROP TABLE IF EXISTS `descuento_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descuento_trabajador` (
  `descuento_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `descuento_trabajador_id_descuento` int(11) unsigned NOT NULL,
  `descuento_trabajador_id_trabajador` int(11) unsigned NOT NULL,
  `descuento_trabajador_fecha_inicio` date NOT NULL,
  `descuento_trabajador_fecha_termino` date NOT NULL,
  `descuento_trabajador_cuota_actual` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Actualizable cada vez que se cobre el descuento en liquidación',
  `descuento_trabajador_total_cuotas` int(11) unsigned NOT NULL,
  `descuento_trabajador_monto_fijo` int(11) NOT NULL,
  `descuento_trabajador_descripcion` varchar(45) DEFAULT NULL,
  `descuento_trabajador_activo` tinyint(1) NOT NULL DEFAULT '1',
  `descuento_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`descuento_trabajador_id`),
  KEY `fk_haber_trabajador_trabajador_idx` (`descuento_trabajador_id_trabajador`),
  KEY `fk_descuento_trabajador_tipo_descuento_idx` (`descuento_trabajador_id_descuento`),
  CONSTRAINT `fk_descuento_trabajador_tipo_descuento` FOREIGN KEY (`descuento_trabajador_id_descuento`) REFERENCES `tipo_descuento_trabajador` (`tipo_descuento_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_descuento_trabajador_trabajador` FOREIGN KEY (`descuento_trabajador_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuento_trabajador`
--

LOCK TABLES `descuento_trabajador` WRITE;
/*!40000 ALTER TABLE `descuento_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `descuento_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `despacho`
--

DROP TABLE IF EXISTS `despacho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `despacho` (
  `despacho_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `despacho_id_bus` int(11) unsigned NOT NULL,
  `despacho_id_servicio` int(11) unsigned NOT NULL,
  `despacho_id_inspector` int(11) unsigned NOT NULL,
  `despacho_id_trabajador` int(11) unsigned NOT NULL,
  `despacho_fecha_hora` datetime NOT NULL,
  `despacho_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`despacho_id`),
  KEY `fk_despacho_id_bus_idx` (`despacho_id_bus`),
  KEY `fk_despacho_id_servicio_idx` (`despacho_id_servicio`),
  KEY `fk_despacho_id_inspector_idx` (`despacho_id_inspector`),
  KEY `fk_despacho_id_conductor_idx` (`despacho_id_trabajador`),
  CONSTRAINT `fk_despacho_id_bus` FOREIGN KEY (`despacho_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_despacho_id_conductor` FOREIGN KEY (`despacho_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_despacho_id_inspector` FOREIGN KEY (`despacho_id_inspector`) REFERENCES `usuario` (`usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_despacho_id_servicio` FOREIGN KEY (`despacho_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `despacho`
--

LOCK TABLES `despacho` WRITE;
/*!40000 ALTER TABLE `despacho` DISABLE KEYS */;
/*!40000 ALTER TABLE `despacho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_billetes_ctv`
--

DROP TABLE IF EXISTS `detalle_billetes_ctv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_billetes_ctv` (
  `detalle_billetes_ctv_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `detalle_billetes_ctv_id_ctv_resumen` int(11) unsigned NOT NULL,
  `detalle_billetes_ctv_numero_sello` int(11) unsigned NOT NULL DEFAULT '0',
  `detalle_billetes_ctv_numero_bolsa` int(11) unsigned NOT NULL DEFAULT '0',
  `detalle_billetes_ctv_documentos` int(11) unsigned NOT NULL DEFAULT '0',
  `detalle_billetes_ctv_efectivo` int(11) unsigned NOT NULL,
  `detalle_billetes_ctv_total_bolsa` int(11) unsigned NOT NULL,
  PRIMARY KEY (`detalle_billetes_ctv_id`),
  KEY `fk_detalle_billete_id_ctv_idx` (`detalle_billetes_ctv_id_ctv_resumen`),
  CONSTRAINT `fk_detalle_billete_id_ctv` FOREIGN KEY (`detalle_billetes_ctv_id_ctv_resumen`) REFERENCES `ctv_resumen` (`ctv_resumen_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_billetes_ctv`
--

LOCK TABLES `detalle_billetes_ctv` WRITE;
/*!40000 ALTER TABLE `detalle_billetes_ctv` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_billetes_ctv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compra_boleto`
--

DROP TABLE IF EXISTS `detalle_compra_boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_compra_boleto` (
  `detalle_compra_boleto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `detalle_compra_boleto_id_compra_boleto` int(11) unsigned NOT NULL,
  `detalle_compra_boleto_id_boleto` int(11) unsigned NOT NULL,
  `detalle_compra_boleto_identificador` varchar(45) NOT NULL,
  `detalle_compra_boleto_serie` varchar(45) NOT NULL,
  `detalle_compra_boleto_cantidad_rollos` int(11) unsigned NOT NULL,
  `detalle_compra_boleto_total` int(11) unsigned NOT NULL,
  PRIMARY KEY (`detalle_compra_boleto_id`),
  KEY `fk_detalle_compra_boleto_id_compra_idx` (`detalle_compra_boleto_id_compra_boleto`),
  KEY `fk_detalle_compra_boleto_id_boleto_idx` (`detalle_compra_boleto_id_boleto`),
  CONSTRAINT `fk_detalle_compra_boleto_id_boleto` FOREIGN KEY (`detalle_compra_boleto_id_boleto`) REFERENCES `boleto` (`boleto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_compra_boleto_id_compra` FOREIGN KEY (`detalle_compra_boleto_id_compra_boleto`) REFERENCES `compra_boleto` (`compra_boleto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compra_boleto`
--

LOCK TABLES `detalle_compra_boleto` WRITE;
/*!40000 ALTER TABLE `detalle_compra_boleto` DISABLE KEYS */;
INSERT INTO `detalle_compra_boleto` VALUES (1,1,1,'ASD','123001',100,35000),(2,1,2,'ASE','1234001',100,35000),(3,1,4,'WER','4455001',100,35000),(4,2,4,'ESC','7896001',10,4000),(5,2,5,'ESL','1134001',20,8000),(6,3,3,'PPP','111001',100,50000),(7,3,6,'QQQ','222001',100,50000),(8,4,2,'ASD','123001',100,35000),(9,4,2,'EWR','11441101',100,35000),(10,5,1,'WWW','123001',100,35000),(11,5,2,'QWE','12201',100,35000),(12,6,1,'ASD','123001',100,10000),(13,6,2,'PIO','123001',100,35000);
/*!40000 ALTER TABLE `detalle_compra_boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_intervalos_mensual`
--

DROP TABLE IF EXISTS `detalle_intervalos_mensual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_intervalos_mensual` (
  `detalle_intervalos_mensual_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `detalle_intervalos_mensual_id_administracion` int(11) unsigned NOT NULL,
  `detalle_intervalos_mensual_id_intervalo_administracion` int(11) unsigned NOT NULL,
  `detalle_intervalos_mensual_numero_buses` int(11) unsigned NOT NULL,
  PRIMARY KEY (`detalle_intervalos_mensual_id`),
  KEY `fk_detalle_intervalos_mensual_administracion_idx` (`detalle_intervalos_mensual_id_administracion`),
  KEY `fk_detalle_intervalos_mensual_id_intervalo_idx` (`detalle_intervalos_mensual_id_intervalo_administracion`),
  CONSTRAINT `fk_detalle_intervalos_mensual_administracion` FOREIGN KEY (`detalle_intervalos_mensual_id_administracion`) REFERENCES `administracion_mensual` (`administracion_mensual_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_intervalos_mensual_id_intervalo` FOREIGN KEY (`detalle_intervalos_mensual_id_intervalo_administracion`) REFERENCES `intervalos_administracion` (`intervalos_administracion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_intervalos_mensual`
--

LOCK TABLES `detalle_intervalos_mensual` WRITE;
/*!40000 ALTER TABLE `detalle_intervalos_mensual` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_intervalos_mensual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_metalico_ctv`
--

DROP TABLE IF EXISTS `detalle_metalico_ctv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_metalico_ctv` (
  `detalle_metalico_ctv_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `detalle_metalico_ctv_id_efectivo_caja` int(11) unsigned NOT NULL,
  `detalle_metalico_ctv_id_ctv_resumen` int(11) unsigned NOT NULL,
  `detalle_metalico_ctv_cantidad_bolsas` int(11) unsigned NOT NULL DEFAULT '0',
  `detalle_metalico_ctv_total_efectivo` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`detalle_metalico_ctv_id`),
  KEY `fk_detalle_metalico_id_ctv_idx` (`detalle_metalico_ctv_id_ctv_resumen`),
  KEY `fk_detalle_metalico_id_efectivo2` (`detalle_metalico_ctv_id_efectivo_caja`),
  CONSTRAINT `fk_detalle_metalico_id_ctv` FOREIGN KEY (`detalle_metalico_ctv_id_ctv_resumen`) REFERENCES `ctv_resumen` (`ctv_resumen_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_metalico_id_efectivo2` FOREIGN KEY (`detalle_metalico_ctv_id_efectivo_caja`) REFERENCES `efectivo_caja` (`efectivo_caja_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_metalico_ctv`
--

LOCK TABLES `detalle_metalico_ctv` WRITE;
/*!40000 ALTER TABLE `detalle_metalico_ctv` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_metalico_ctv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_resumen_recaudacion`
--

DROP TABLE IF EXISTS `detalle_resumen_recaudacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_resumen_recaudacion` (
  `detalle_resumen_recaudacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `detalle_resumen_recaudacion_id_resumen` int(11) unsigned NOT NULL,
  `detalle_resumen_recaudacion_id_efectivo_caja` int(11) unsigned NOT NULL,
  `detalle_resumen_recaudacion_cantidad_efectivo` int(11) unsigned NOT NULL,
  `detalle_resumen_recaudacion_total_efectivo` int(11) unsigned NOT NULL,
  PRIMARY KEY (`detalle_resumen_recaudacion_id`),
  KEY `fk_detalle_resumen_id_resumen_idx` (`detalle_resumen_recaudacion_id_resumen`),
  KEY `fk_detalle_resumen_id_efectivo_idx` (`detalle_resumen_recaudacion_id_efectivo_caja`),
  CONSTRAINT `fk_detalle_resumen_id_efectivo` FOREIGN KEY (`detalle_resumen_recaudacion_id_efectivo_caja`) REFERENCES `efectivo_caja` (`efectivo_caja_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_resumen_id_resumen` FOREIGN KEY (`detalle_resumen_recaudacion_id_resumen`) REFERENCES `resumen_recaudacion` (`resumen_recaudacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_resumen_recaudacion`
--

LOCK TABLES `detalle_resumen_recaudacion` WRITE;
/*!40000 ALTER TABLE `detalle_resumen_recaudacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_resumen_recaudacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia_festivo`
--

DROP TABLE IF EXISTS `dia_festivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dia_festivo` (
  `dia_festivo_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dia_festivo_nombre` varchar(45) NOT NULL,
  `dia_festivo_fecha` date NOT NULL,
  `dia_festivo_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dia_festivo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia_festivo`
--

LOCK TABLES `dia_festivo` WRITE;
/*!40000 ALTER TABLE `dia_festivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dia_festivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `efectivo_caja`
--

DROP TABLE IF EXISTS `efectivo_caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `efectivo_caja` (
  `efectivo_caja_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `efectivo_caja_id_metodo_pago` int(11) unsigned NOT NULL,
  `efectivo_caja_valor_efectivo` int(11) unsigned NOT NULL,
  `efectivo_caja_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`efectivo_caja_id`),
  KEY `fk_efectivo_caja_id_metodo_idx` (`efectivo_caja_id_metodo_pago`),
  CONSTRAINT `fk_efectivo_caja_id_metodo` FOREIGN KEY (`efectivo_caja_id_metodo_pago`) REFERENCES `metodo_pago` (`metodo_pago_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `efectivo_caja`
--

LOCK TABLES `efectivo_caja` WRITE;
/*!40000 ALTER TABLE `efectivo_caja` DISABLE KEYS */;
/*!40000 ALTER TABLE `efectivo_caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso`
--

DROP TABLE IF EXISTS `egreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso` (
  `egreso_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `egreso_nombre_egreso` varchar(45) NOT NULL,
  `egreso_obligatorio` tinyint(1) DEFAULT '0',
  `egreso_activo` tinyint(1) NOT NULL,
  `egreso_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`egreso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso`
--

LOCK TABLES `egreso` WRITE;
/*!40000 ALTER TABLE `egreso` DISABLE KEYS */;
INSERT INTO `egreso` VALUES (19,'Administración',1,1,'2017-04-20 16:20:42'),(20,'Licitación',1,1,'2017-04-22 13:43:50');
/*!40000 ALTER TABLE `egreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso_bus`
--

DROP TABLE IF EXISTS `egreso_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso_bus` (
  `egreso_bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `egreso_bus_id_egreso` int(11) unsigned NOT NULL,
  `egreso_bus_id_bus` int(11) unsigned NOT NULL,
  `egreso_bus_valor_defecto` int(11) NOT NULL DEFAULT '0',
  `egreso_bus_porcentaje` decimal(10,2) NOT NULL DEFAULT '0.00',
  `egreso_bus_numero_orden` int(11) NOT NULL,
  `egreso_bus_activo` tinyint(1) NOT NULL,
  `egreso_bus_fecha_ingreso` datetime NOT NULL,
  `egreso_bus_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`egreso_bus_id`),
  KEY `fk_egreso_servicio_id_egreso_idx` (`egreso_bus_id_egreso`),
  KEY `fk_egreso_flota_id_bus_idx` (`egreso_bus_id_bus`),
  CONSTRAINT `fk_egreso_bus_id_egreso` FOREIGN KEY (`egreso_bus_id_egreso`) REFERENCES `egreso` (`egreso_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_egreso_flota_id_bus` FOREIGN KEY (`egreso_bus_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso_bus`
--

LOCK TABLES `egreso_bus` WRITE;
/*!40000 ALTER TABLE `egreso_bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso_caja_terminal`
--

DROP TABLE IF EXISTS `egreso_caja_terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso_caja_terminal` (
  `egreso_caja_terminal_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `egreso_caja_terminal_id_egreso` int(11) unsigned NOT NULL,
  `egreso_caja_terminal_id_caja_terminal` int(11) unsigned NOT NULL,
  `egreso_caja_terminal_valor_defecto` int(11) NOT NULL DEFAULT '0',
  `egreso_caja_terminal_porcentaje` decimal(10,2) NOT NULL DEFAULT '0.00',
  `egreso_caja_terminal_numero_orden` int(11) NOT NULL,
  `egreso_caja_activo` tinyint(1) NOT NULL,
  `egreso_caja_terminal_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `egreso_caja_terminal_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`egreso_caja_terminal_id`),
  KEY `fk_egreso_servicio_id_egreso_idx` (`egreso_caja_terminal_id_egreso`),
  KEY `fk_egreso_recaudacion_id_recaudacion_idx` (`egreso_caja_terminal_id_caja_terminal`),
  CONSTRAINT `fk_egreso_recaudacion_id_egreso` FOREIGN KEY (`egreso_caja_terminal_id_egreso`) REFERENCES `egreso` (`egreso_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_egreso_recaudacion_id_recaudacion` FOREIGN KEY (`egreso_caja_terminal_id_caja_terminal`) REFERENCES `caja_terminal` (`caja_terminal_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso_caja_terminal`
--

LOCK TABLES `egreso_caja_terminal` WRITE;
/*!40000 ALTER TABLE `egreso_caja_terminal` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso_caja_terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso_flota`
--

DROP TABLE IF EXISTS `egreso_flota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso_flota` (
  `egreso_flota_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `egreso_flota_id_egreso` int(11) unsigned NOT NULL,
  `egreso_flota_id_flota` int(11) unsigned NOT NULL,
  `egreso_flota_valor_defecto` int(11) NOT NULL DEFAULT '0',
  `egreso_flota_porcentaje` decimal(10,2) NOT NULL DEFAULT '0.00',
  `egreso_flota_numero_orden` int(11) NOT NULL,
  `egreso_flota_activo` tinyint(1) NOT NULL DEFAULT '1',
  `egreso_flota_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `egreso_flota_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`egreso_flota_id`),
  KEY `fk_egreso_servicio_id_egreso_idx` (`egreso_flota_id_egreso`),
  KEY `fk_egreso_flota_id_flota_idx` (`egreso_flota_id_flota`),
  CONSTRAINT `fk_egreso_flota_id_egreso` FOREIGN KEY (`egreso_flota_id_egreso`) REFERENCES `egreso` (`egreso_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_egreso_flota_id_flota` FOREIGN KEY (`egreso_flota_id_flota`) REFERENCES `flota` (`flota_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso_flota`
--

LOCK TABLES `egreso_flota` WRITE;
/*!40000 ALTER TABLE `egreso_flota` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso_flota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso_guia`
--

DROP TABLE IF EXISTS `egreso_guia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso_guia` (
  `egreso_guia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `egreso_guia_id_guia` int(11) unsigned NOT NULL,
  `egreso_guia_id_egreso` int(11) unsigned NOT NULL,
  `egreso_guia_monto` int(11) unsigned NOT NULL,
  `egreso_guia_recaudado` tinyint(1) NOT NULL DEFAULT '1',
  `egreso_guia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`egreso_guia_id`),
  KEY `fk_egresos_guia_id_guia_idx` (`egreso_guia_id_guia`),
  KEY `fk_egresos_guia_id_egreso_idx` (`egreso_guia_id_egreso`),
  CONSTRAINT `fk_egresos_guia_id_egreso` FOREIGN KEY (`egreso_guia_id_egreso`) REFERENCES `egreso` (`egreso_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_egresos_guia_id_guia` FOREIGN KEY (`egreso_guia_id_guia`) REFERENCES `guia` (`guia_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso_guia`
--

LOCK TABLES `egreso_guia` WRITE;
/*!40000 ALTER TABLE `egreso_guia` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso_guia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso_recaudacion`
--

DROP TABLE IF EXISTS `egreso_recaudacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso_recaudacion` (
  `egreso_recaudacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `egreso_recaudacion_id_resumen_recaudacion` int(11) unsigned NOT NULL,
  `egreso_recaudacion_id_egreso` int(11) unsigned NOT NULL,
  `egreso_recaudacion_total_egreso` int(11) NOT NULL,
  PRIMARY KEY (`egreso_recaudacion_id`),
  KEY `fk_egreso_recaudacion_id_resumen_idx` (`egreso_recaudacion_id_resumen_recaudacion`),
  KEY `fk_egreso_recaudacion_id_egreso_idx` (`egreso_recaudacion_id_egreso`),
  CONSTRAINT `fk_egreso_recaudacion_id_egreso2` FOREIGN KEY (`egreso_recaudacion_id_egreso`) REFERENCES `egreso` (`egreso_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_egreso_recaudacion_id_resumen2` FOREIGN KEY (`egreso_recaudacion_id_resumen_recaudacion`) REFERENCES `resumen_recaudacion` (`resumen_recaudacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso_recaudacion`
--

LOCK TABLES `egreso_recaudacion` WRITE;
/*!40000 ALTER TABLE `egreso_recaudacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `egreso_recaudacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `empresa_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `empresa_id_mutual` int(11) unsigned NOT NULL,
  `empresa_id_caja_compensacion` int(11) unsigned NOT NULL,
  `empresa_rut` varchar(9) NOT NULL,
  `empresa_nombre` varchar(255) NOT NULL,
  `empresa_giro` varchar(255) NOT NULL,
  `empresa_direccion` varchar(255) DEFAULT NULL,
  `empresa_telefono` varchar(255) DEFAULT NULL,
  `empresa_celular` varchar(100) DEFAULT NULL,
  `empresa_email` varchar(100) DEFAULT NULL,
  `empresa_porcentaje_mutual` float(5,2) NOT NULL DEFAULT '0.00',
  `empresa_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`empresa_id`),
  UNIQUE KEY `rut_empresa_UNIQUE` (`empresa_rut`),
  KEY `fk_empresa_id_mutual_idx` (`empresa_id_mutual`),
  KEY `fk_empresa_id_caja_compensacion_idx` (`empresa_id_caja_compensacion`),
  CONSTRAINT `fk_empresa_id_caja_compensacion` FOREIGN KEY (`empresa_id_caja_compensacion`) REFERENCES `caja_compensacion` (`caja_compensacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_empresa_id_mutual` FOREIGN KEY (`empresa_id_mutual`) REFERENCES `mutual` (`mutual_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1139 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1138,1,1,'165015096','PEREZ CASTO','Sin Información','Sin Información','Sin Información','Sin Información','Sin Información',0.00,'2017-04-20 16:56:41');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_bus`
--

DROP TABLE IF EXISTS `estado_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_bus` (
  `estado_bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `estado_bus_nombre` varchar(100) NOT NULL COMMENT '1. Trabajando\n2. Talller\n3. Sin Trabajar\n4. Dado de baja\n5. Venta',
  `estado_bus_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`estado_bus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_bus`
--

LOCK TABLES `estado_bus` WRITE;
/*!40000 ALTER TABLE `estado_bus` DISABLE KEYS */;
INSERT INTO `estado_bus` VALUES (1,'Operativo','2017-04-20 16:01:38'),(2,'Suspendido','2017-04-22 12:08:28');
/*!40000 ALTER TABLE `estado_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_civil`
--

DROP TABLE IF EXISTS `estado_civil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_civil` (
  `estado_civil_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `estado_civil_nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`estado_civil_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_civil`
--

LOCK TABLES `estado_civil` WRITE;
/*!40000 ALTER TABLE `estado_civil` DISABLE KEYS */;
INSERT INTO `estado_civil` VALUES (1,'CASADO/A'),(2,'SOLTERO/A'),(3,'SEPARADO/A'),(4,'VIUDO/A');
/*!40000 ALTER TABLE `estado_civil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_guia`
--

DROP TABLE IF EXISTS `estado_guia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_guia` (
  `estado_guia_id` int(11) unsigned NOT NULL,
  `estado_guia_nombre` varchar(45) NOT NULL,
  `estado_guia_activo` tinyint(1) NOT NULL,
  `estado_guia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`estado_guia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_guia`
--

LOCK TABLES `estado_guia` WRITE;
/*!40000 ALTER TABLE `estado_guia` DISABLE KEYS */;
INSERT INTO `estado_guia` VALUES (1,'NORMAL ',1,'2017-04-29 12:22:11'),(2,'DESCANSO',1,'2017-04-29 12:22:11');
/*!40000 ALTER TABLE `estado_guia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fecha`
--

DROP TABLE IF EXISTS `fecha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fecha` (
  `fecha_id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  PRIMARY KEY (`fecha_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fecha`
--

LOCK TABLES `fecha` WRITE;
/*!40000 ALTER TABLE `fecha` DISABLE KEYS */;
/*!40000 ALTER TABLE `fecha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feriado_legal`
--

DROP TABLE IF EXISTS `feriado_legal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feriado_legal` (
  `feriado_legal_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `feriado_legal_id_trabajador` int(11) unsigned NOT NULL,
  `feriado_legal_fecha_desde` date NOT NULL,
  `feriado_legal_fecha_hasta` date NOT NULL,
  `feriado_legal_dias_habiles` int(11) unsigned DEFAULT '0',
  `feriado_legal_vacaciones_progresivas` int(11) unsigned DEFAULT '0',
  `feriado_legal_domingos_inhabiles` int(11) unsigned DEFAULT '0',
  `feriado_legal_feriado_fraccionado` int(11) unsigned DEFAULT '0',
  `feriado_legal_saldo_pendiente` int(11) unsigned DEFAULT '0',
  `feriado_legal_valor_feriado` int(11) unsigned NOT NULL DEFAULT '0',
  `feriado_legal_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`feriado_legal_id`),
  KEY `fk_feriado_legal_id_trabajador_idx` (`feriado_legal_id_trabajador`),
  CONSTRAINT `fk_feriado_legal_id_trabajador` FOREIGN KEY (`feriado_legal_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feriado_legal`
--

LOCK TABLES `feriado_legal` WRITE;
/*!40000 ALTER TABLE `feriado_legal` DISABLE KEYS */;
/*!40000 ALTER TABLE `feriado_legal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finiquito_relacion_laboral`
--

DROP TABLE IF EXISTS `finiquito_relacion_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finiquito_relacion_laboral` (
  `finiquito_relacion_laboral_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `finiquito_relacion_laboral_id_relacion_laboral` int(11) unsigned NOT NULL,
  `finiquito_relacion_laboral_id_causal_finiquito` int(11) unsigned NOT NULL,
  `finiquito_relacion_laboral_fecha_finiquito` date NOT NULL,
  `finiquito_relacion_laboral_monto_finiquito` int(11) unsigned NOT NULL,
  `finiquito_relacion_laboral_anios_servicio` int(3) unsigned NOT NULL DEFAULT '0',
  `finiquito_relacion_laboral_anios_adicionales` int(3) unsigned NOT NULL DEFAULT '0',
  `finiquito_relacion_laboral_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`finiquito_relacion_laboral_id`),
  KEY `fk_finiquito_id_relacion_laboral_idx` (`finiquito_relacion_laboral_id_relacion_laboral`),
  KEY `fk_finiquito_causal_finiquito_idx` (`finiquito_relacion_laboral_id_causal_finiquito`),
  CONSTRAINT `fk_finiquito_causal_finiquito` FOREIGN KEY (`finiquito_relacion_laboral_id_causal_finiquito`) REFERENCES `causal_finiquito` (`causal_finiquito_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_finiquito_id_relacion_laboral` FOREIGN KEY (`finiquito_relacion_laboral_id_relacion_laboral`) REFERENCES `relacion_laboral` (`relacion_laboral_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finiquito_relacion_laboral`
--

LOCK TABLES `finiquito_relacion_laboral` WRITE;
/*!40000 ALTER TABLE `finiquito_relacion_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `finiquito_relacion_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flota`
--

DROP TABLE IF EXISTS `flota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flota` (
  `flota_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `flota_nombre` varchar(45) NOT NULL,
  `flota_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`flota_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flota`
--

LOCK TABLES `flota` WRITE;
/*!40000 ALTER TABLE `flota` DISABLE KEYS */;
INSERT INTO `flota` VALUES (107,'Sin Flota','2017-04-20 12:53:24');
/*!40000 ALTER TABLE `flota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_pago`
--

DROP TABLE IF EXISTS `forma_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_pago` (
  `forma_pago_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Efectivo, cheque, vale vista, depósito',
  `forma_pago_nombre` varchar(45) NOT NULL,
  `forma_pago_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`forma_pago_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pago`
--

LOCK TABLES `forma_pago` WRITE;
/*!40000 ALTER TABLE `forma_pago` DISABLE KEYS */;
INSERT INTO `forma_pago` VALUES (1,'EFECTIVO ','2017-04-29 12:18:20'),(2,'CHEQUE','2017-04-29 12:18:20'),(3,'TRANSFERENCIA','2017-04-29 12:18:20'),(4,'DEPOSITO ','2017-04-29 12:18:20');
/*!40000 ALTER TABLE `forma_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frecuencia_servicio`
--

DROP TABLE IF EXISTS `frecuencia_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frecuencia_servicio` (
  `frecuencia_servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `frecuencia_servicio_id_servicio` int(11) unsigned NOT NULL,
  `frecuencia_servicio_id_tipo_dia` int(11) unsigned NOT NULL,
  `frecuencia_servicio_id_periodo` int(11) unsigned NOT NULL,
  `frecuencia_servicio_id_tipo_demanda` int(11) unsigned NOT NULL,
  `frecuencia_servicio_numero_buses` int(11) unsigned NOT NULL DEFAULT '0',
  `frecuencia_servicio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`frecuencia_servicio_id`),
  KEY `fk_frecuencia_servicio_id_servicio_idx` (`frecuencia_servicio_id_servicio`),
  KEY `fk_frecuencia_servicio_id_tipo_dia_frecuencia_idx` (`frecuencia_servicio_id_tipo_dia`),
  KEY `fk_frecuencia_servicio_id_periodo_frecuencia_idx` (`frecuencia_servicio_id_periodo`),
  KEY `fk_frecuencia_servicio_id_tipo_demanda_idx` (`frecuencia_servicio_id_tipo_demanda`),
  CONSTRAINT `fk_frecuencia_servicio_id_periodo_frecuencia` FOREIGN KEY (`frecuencia_servicio_id_periodo`) REFERENCES `periodo_frecuencia` (`periodo_frecuencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_frecuencia_servicio_id_servicio` FOREIGN KEY (`frecuencia_servicio_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_frecuencia_servicio_id_tipo_demanda` FOREIGN KEY (`frecuencia_servicio_id_tipo_demanda`) REFERENCES `tipo_demanda` (`tipo_demanda_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_frecuencia_servicio_id_tipo_dia_frecuencia` FOREIGN KEY (`frecuencia_servicio_id_tipo_dia`) REFERENCES `tipo_dia_frecuencia` (`tipo_dia_frecuencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frecuencia_servicio`
--

LOCK TABLES `frecuencia_servicio` WRITE;
/*!40000 ALTER TABLE `frecuencia_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `frecuencia_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gasto_administracion_mensual`
--

DROP TABLE IF EXISTS `gasto_administracion_mensual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gasto_administracion_mensual` (
  `gasto_administracion_mensual_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gasto_administracion_mensual_id_departamento` int(11) unsigned NOT NULL,
  `gasto_administracion_mensual_folio` int(11) unsigned NOT NULL,
  `gasto_administracion_mensual_nombre` varchar(45) NOT NULL,
  `gasto_administracion_mensual_fecha_gasto` date NOT NULL,
  `gasto_administracion_mensual_valor` int(11) unsigned NOT NULL,
  `gasto_administracion_mensual_observacion` longtext,
  `gasto_administracion_mensual_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`gasto_administracion_mensual_id`),
  KEY `fk_gastos_administracion_mensual_id_departamento_idx` (`gasto_administracion_mensual_id_departamento`),
  CONSTRAINT `fk_gastos_administracion_mensual_id_departamento` FOREIGN KEY (`gasto_administracion_mensual_id_departamento`) REFERENCES `departamento` (`departamento_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto_administracion_mensual`
--

LOCK TABLES `gasto_administracion_mensual` WRITE;
/*!40000 ALTER TABLE `gasto_administracion_mensual` DISABLE KEYS */;
/*!40000 ALTER TABLE `gasto_administracion_mensual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guia`
--

DROP TABLE IF EXISTS `guia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guia` (
  `guia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `guia_id_bus` int(11) unsigned NOT NULL,
  `guia_id_trabajador` int(11) unsigned NOT NULL,
  `guia_id_caja_terminal` int(11) unsigned NOT NULL,
  `guia_id_estado` int(11) unsigned NOT NULL,
  `guia_folio` int(11) NOT NULL,
  `guia_fecha` date NOT NULL,
  `guia_recaudacion` date DEFAULT NULL,
  `guia_total_ingresos` int(11) DEFAULT '0',
  `guia_total_egresos` int(11) DEFAULT '0',
  `guia_viaje_especial` int(11) DEFAULT NULL,
  `guia_otros_ingresos` int(11) DEFAULT NULL,
  `guia_saldo` int(11) NOT NULL,
  `guia_numero_vueltas` int(11) DEFAULT '0',
  `guia_turno` int(11) DEFAULT '0',
  `guia_observacion` varchar(100) DEFAULT '0',
  `guia_recaudada` tinyint(1) DEFAULT '0',
  `guia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`guia_id`),
  UNIQUE KEY `folio_UNIQUE` (`guia_folio`),
  KEY `fk_guia_id_bus_idx` (`guia_id_bus`),
  KEY `fk_guia_id_trabajador_idx` (`guia_id_trabajador`),
  KEY `fk_guia_id_estado_guia_idx` (`guia_id_estado`),
  KEY `fk_guia_id_proceso_idx` (`guia_id_caja_terminal`),
  CONSTRAINT `fk_guia_id_bus` FOREIGN KEY (`guia_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_guia_id_estado_guia` FOREIGN KEY (`guia_id_estado`) REFERENCES `estado_guia` (`estado_guia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_guia_id_proceso` FOREIGN KEY (`guia_id_caja_terminal`) REFERENCES `caja_terminal` (`caja_terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_guia_id_trabajador` FOREIGN KEY (`guia_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guia`
--

LOCK TABLES `guia` WRITE;
/*!40000 ALTER TABLE `guia` DISABLE KEYS */;
/*!40000 ALTER TABLE `guia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `haber_liquidacion`
--

DROP TABLE IF EXISTS `haber_liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `haber_liquidacion` (
  `haber_liquidacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `haber_liquidacion_id_haber` int(11) unsigned NOT NULL,
  `haber_liquidacion_id_liquidacion_sueldo` int(11) unsigned NOT NULL,
  `haber_liquidacion_fecha` date NOT NULL,
  `haber_liquidacion_monto_haber` int(11) NOT NULL DEFAULT '0',
  `haber_liquidacion_descripcion` varchar(45) NOT NULL,
  `haber_liquidacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`haber_liquidacion_id`),
  KEY `fk_hbl_id_haber_trabajador_idx` (`haber_liquidacion_id_haber`),
  KEY `fk_hbl_id_liquidacion_sueldo_idx` (`haber_liquidacion_id_liquidacion_sueldo`),
  CONSTRAINT `fk_hbl_id_haber_trabajador` FOREIGN KEY (`haber_liquidacion_id_haber`) REFERENCES `haber_trabajador` (`haber_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hbl_id_liquidacion_sueldo` FOREIGN KEY (`haber_liquidacion_id_liquidacion_sueldo`) REFERENCES `liquidacion_sueldo` (`liquidacion_sueldo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `haber_liquidacion`
--

LOCK TABLES `haber_liquidacion` WRITE;
/*!40000 ALTER TABLE `haber_liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `haber_liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `haber_trabajador`
--

DROP TABLE IF EXISTS `haber_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `haber_trabajador` (
  `haber_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `haber_trabajador_id_haber` int(11) unsigned NOT NULL,
  `haber_trabajador_id_trabajador` int(11) unsigned NOT NULL,
  `haber_trabajador_fecha_inicio` date NOT NULL,
  `haber_trabajador_fecha_termino` date NOT NULL,
  `haber_trabajador_cuota_actual` int(11) unsigned NOT NULL DEFAULT '0',
  `haber_trabajador_total_cuotas` int(11) unsigned NOT NULL,
  `haber_trabajador_monto_fijo` int(11) unsigned NOT NULL,
  `haber_trabajador_descripcion` varchar(45) DEFAULT NULL,
  `haber_trabajador_activo` tinyint(1) NOT NULL DEFAULT '1',
  `haber_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`haber_trabajador_id`),
  KEY `fk_haber_trabajador_tipo_haber_idx` (`haber_trabajador_id_haber`),
  KEY `fk_haber_trabajador_trabajador_idx` (`haber_trabajador_id_trabajador`),
  CONSTRAINT `fk_haber_trabajador_tipo_haber` FOREIGN KEY (`haber_trabajador_id_haber`) REFERENCES `tipo_haber_trabajador` (`tipo_haber_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_haber_trabajador_trabajador` FOREIGN KEY (`haber_trabajador_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `haber_trabajador`
--

LOCK TABLES `haber_trabajador` WRITE;
/*!40000 ALTER TABLE `haber_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `haber_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hora_extra_trabajador`
--

DROP TABLE IF EXISTS `hora_extra_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hora_extra_trabajador` (
  `hora_extra_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hora_extra_trabajador_id_trabajador` int(11) unsigned NOT NULL,
  `hora_extra_trabajador_cantidad` int(11) unsigned NOT NULL,
  `hora_extra_trabajador_fecha` date DEFAULT NULL,
  `hora_extra_trabajador_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hora_extra_trabajador_id`),
  KEY `fk_horas_extras_trabajador_id_trabajador_idx` (`hora_extra_trabajador_id_trabajador`),
  CONSTRAINT `fk_horas_extras_trabajador_id_trabajador` FOREIGN KEY (`hora_extra_trabajador_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hora_extra_trabajador`
--

LOCK TABLES `hora_extra_trabajador` WRITE;
/*!40000 ALTER TABLE `hora_extra_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `hora_extra_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_jornada`
--

DROP TABLE IF EXISTS `horario_jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario_jornada` (
  `horario_jornada_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `horario_jornada_inicio_horario` time NOT NULL,
  `horario_jornada_termino_horario` time NOT NULL,
  `horario_jornada_horario_variable` tinyint(1) DEFAULT NULL,
  `horario_jornada_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`horario_jornada_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_jornada`
--

LOCK TABLES `horario_jornada` WRITE;
/*!40000 ALTER TABLE `horario_jornada` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario_jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario_servicio`
--

DROP TABLE IF EXISTS `horario_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario_servicio` (
  `horario_servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `horario_servicio_id_servicio` int(11) unsigned NOT NULL,
  `horario_servicio_id_tipo_estacionalidad` int(11) unsigned NOT NULL,
  `horario_servicio_nombre` varchar(45) NOT NULL,
  `horario_servicio_desde` time NOT NULL,
  `horario_servicio_hasta` time NOT NULL,
  `horario_servicio_lunes` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_martes` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_miercoles` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_jueves` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_viernes` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_sabado` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_domingo` tinyint(1) NOT NULL DEFAULT '0',
  `horario_servicio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`horario_servicio_id`),
  KEY `fk_horario_servicio_id_servicio_idx` (`horario_servicio_id_servicio`),
  KEY `fk_horario_servicio_is_estacionalidad_idx` (`horario_servicio_id_tipo_estacionalidad`),
  CONSTRAINT `fk_horario_servicio_id_servicio` FOREIGN KEY (`horario_servicio_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_horario_servicio_is_estacionalidad` FOREIGN KEY (`horario_servicio_id_tipo_estacionalidad`) REFERENCES `tipo_estacionalidad` (`tipo_estacionalidad_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_servicio`
--

LOCK TABLES `horario_servicio` WRITE;
/*!40000 ALTER TABLE `horario_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impuesto_segunda_categoria`
--

DROP TABLE IF EXISTS `impuesto_segunda_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `impuesto_segunda_categoria` (
  `impuesto_segunda_categoria_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `impuesto_segunda_categoria_desde` float(10,3) NOT NULL,
  `impuesto_segunda_categoria_hasta` float(10,3) NOT NULL,
  `impuesto_segunda_categoria_factor` float(10,3) DEFAULT NULL,
  `impuesto_segunda_categoria_rebaja` float(10,3) DEFAULT NULL,
  `impuesto_segunda_categoria_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`impuesto_segunda_categoria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impuesto_segunda_categoria`
--

LOCK TABLES `impuesto_segunda_categoria` WRITE;
/*!40000 ALTER TABLE `impuesto_segunda_categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `impuesto_segunda_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_apv`
--

DROP TABLE IF EXISTS `institucion_apv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_apv` (
  `institucion_apv_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `institucion_apv_id_tipo` int(11) unsigned NOT NULL,
  `institucion_apv_nombre` varchar(100) NOT NULL,
  `institucion_apv_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`institucion_apv_id`),
  KEY `fk_institucion_apv_tipo_idx` (`institucion_apv_id_tipo`),
  CONSTRAINT `fk_institucion_apv_tipo` FOREIGN KEY (`institucion_apv_id_tipo`) REFERENCES `tipo_institucion_apv` (`tipo_institucion_apv_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_apv`
--

LOCK TABLES `institucion_apv` WRITE;
/*!40000 ALTER TABLE `institucion_apv` DISABLE KEYS */;
INSERT INTO `institucion_apv` VALUES (1,1,'SIN INFORMACIÓN ','2017-04-29 12:17:33');
/*!40000 ALTER TABLE `institucion_apv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_prevision`
--

DROP TABLE IF EXISTS `institucion_prevision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_prevision` (
  `institucion_prevision_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `institucion_prevision_nombre` varchar(255) NOT NULL,
  `institucion_prevision_comision` float(5,3) DEFAULT NULL,
  `institucion_prevision_porcentaje_descuento` float(5,3) NOT NULL,
  `institucion_prevision_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `institucion_prevision_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`institucion_prevision_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_prevision`
--

LOCK TABLES `institucion_prevision` WRITE;
/*!40000 ALTER TABLE `institucion_prevision` DISABLE KEYS */;
INSERT INTO `institucion_prevision` VALUES (1,'SIN INFORMACIÓN ',0.000,0.000,'2017-04-29 12:16:39',NULL);
/*!40000 ALTER TABLE `institucion_prevision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institucion_salud`
--

DROP TABLE IF EXISTS `institucion_salud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institucion_salud` (
  `institucion_salud_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `institucion_salud_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`institucion_salud_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institucion_salud`
--

LOCK TABLES `institucion_salud` WRITE;
/*!40000 ALTER TABLE `institucion_salud` DISABLE KEYS */;
INSERT INTO `institucion_salud` VALUES (1,'SIN INFORMACIÓN');
/*!40000 ALTER TABLE `institucion_salud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervalos_administracion`
--

DROP TABLE IF EXISTS `intervalos_administracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intervalos_administracion` (
  `intervalos_administracion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `intervalos_administracion_nombre` varchar(45) NOT NULL,
  `intervalos_administracion_desde` int(11) unsigned NOT NULL,
  `intervalos_administracion_hasta` int(11) unsigned NOT NULL,
  PRIMARY KEY (`intervalos_administracion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervalos_administracion`
--

LOCK TABLES `intervalos_administracion` WRITE;
/*!40000 ALTER TABLE `intervalos_administracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `intervalos_administracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario_caja`
--

DROP TABLE IF EXISTS `inventario_caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario_caja` (
  `inventario_caja_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `inventario_caja_id_inventario_interno` int(11) unsigned NOT NULL,
  `inventario_caja_id_caja` int(11) unsigned NOT NULL,
  `inventario_caja_estado` tinyint(1) NOT NULL DEFAULT '0',
  `inventario_caja_serie` int(11) NOT NULL,
  `inventario_caja_identificador` varchar(10) NOT NULL,
  `inventario_caja_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`inventario_caja_id`),
  KEY `fk_inventario_caja_idx` (`inventario_caja_id_inventario_interno`),
  KEY `fk_inventario_caja_id_caja_idx` (`inventario_caja_id_caja`),
  CONSTRAINT `fk_inventario_caja` FOREIGN KEY (`inventario_caja_id_inventario_interno`) REFERENCES `inventario_interno` (`inventario_interno_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_caja_id_caja` FOREIGN KEY (`inventario_caja_id_caja`) REFERENCES `caja_terminal` (`caja_terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario_caja`
--

LOCK TABLES `inventario_caja` WRITE;
/*!40000 ALTER TABLE `inventario_caja` DISABLE KEYS */;
INSERT INTO `inventario_caja` VALUES (1,1,38,0,123001,'ASD','2017-04-29 12:10:37'),(2,101,38,0,1234001,'ASE','2017-04-29 12:22:51'),(3,102,38,0,1235001,'ASE','2017-04-29 12:22:54'),(4,103,38,0,1236001,'ASE','2017-04-29 12:22:56'),(5,104,38,0,1237001,'ASE','2017-04-29 12:22:58'),(6,311,38,0,1134001,'ESL','2017-04-29 12:51:45'),(7,312,38,0,1135001,'ESL','2017-04-29 12:52:06'),(8,313,38,0,1136001,'ESL','2017-04-29 12:52:21'),(9,201,38,0,4455001,'WER','2017-04-29 12:53:45'),(10,1,39,0,123001,'ASD','2017-04-29 12:55:44'),(11,431,40,0,222001,'QQQ','2017-04-29 14:00:57'),(12,432,40,0,223001,'QQQ','2017-04-29 14:00:59'),(13,433,40,0,224001,'QQQ','2017-04-29 14:01:06'),(14,331,40,0,111001,'PPP','2017-04-29 14:01:13'),(15,332,40,0,112001,'PPP','2017-04-29 14:01:16');
/*!40000 ALTER TABLE `inventario_caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario_interno`
--

DROP TABLE IF EXISTS `inventario_interno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario_interno` (
  `inventario_interno_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `inventario_interno_id_boleto` int(11) unsigned NOT NULL,
  `inventario_interno_serie` int(11) unsigned NOT NULL,
  `inventario_interno_identificador` varchar(45) NOT NULL DEFAULT '0',
  `inventario_interno_estado` tinyint(1) NOT NULL DEFAULT '0',
  `inventario_interno_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`inventario_interno_id`),
  KEY `fk_bodega_boleto_id_boleto_idx` (`inventario_interno_id_boleto`),
  CONSTRAINT `fk_inventario_interno_id_boleto` FOREIGN KEY (`inventario_interno_id_boleto`) REFERENCES `boleto` (`boleto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1131 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario_interno`
--

LOCK TABLES `inventario_interno` WRITE;
/*!40000 ALTER TABLE `inventario_interno` DISABLE KEYS */;
INSERT INTO `inventario_interno` VALUES (1,1,123001,'ASD',0,'2017-04-28 10:04:40'),(2,1,124001,'ASD',0,'2017-04-28 10:04:40'),(3,1,125001,'ASD',0,'2017-04-28 10:04:40'),(4,1,126001,'ASD',0,'2017-04-28 10:04:40'),(5,1,127001,'ASD',0,'2017-04-28 10:04:40'),(6,1,128001,'ASD',0,'2017-04-28 10:04:40'),(7,1,129001,'ASD',0,'2017-04-28 10:04:40'),(8,1,130001,'ASD',0,'2017-04-28 10:04:40'),(9,1,131001,'ASD',0,'2017-04-28 10:04:40'),(10,1,132001,'ASD',0,'2017-04-28 10:04:40'),(11,1,133001,'ASD',0,'2017-04-28 10:04:40'),(12,1,134001,'ASD',0,'2017-04-28 10:04:40'),(13,1,135001,'ASD',0,'2017-04-28 10:04:40'),(14,1,136001,'ASD',0,'2017-04-28 10:04:40'),(15,1,137001,'ASD',0,'2017-04-28 10:04:40'),(16,1,138001,'ASD',0,'2017-04-28 10:04:40'),(17,1,139001,'ASD',0,'2017-04-28 10:04:40'),(18,1,140001,'ASD',0,'2017-04-28 10:04:40'),(19,1,141001,'ASD',0,'2017-04-28 10:04:40'),(20,1,142001,'ASD',0,'2017-04-28 10:04:40'),(21,1,143001,'ASD',0,'2017-04-28 10:04:40'),(22,1,144001,'ASD',0,'2017-04-28 10:04:40'),(23,1,145001,'ASD',0,'2017-04-28 10:04:40'),(24,1,146001,'ASD',0,'2017-04-28 10:04:40'),(25,1,147001,'ASD',0,'2017-04-28 10:04:40'),(26,1,148001,'ASD',0,'2017-04-28 10:04:40'),(27,1,149001,'ASD',0,'2017-04-28 10:04:40'),(28,1,150001,'ASD',0,'2017-04-28 10:04:40'),(29,1,151001,'ASD',0,'2017-04-28 10:04:40'),(30,1,152001,'ASD',0,'2017-04-28 10:04:40'),(31,1,153001,'ASD',0,'2017-04-28 10:04:40'),(32,1,154001,'ASD',0,'2017-04-28 10:04:40'),(33,1,155001,'ASD',0,'2017-04-28 10:04:40'),(34,1,156001,'ASD',0,'2017-04-28 10:04:40'),(35,1,157001,'ASD',0,'2017-04-28 10:04:40'),(36,1,158001,'ASD',0,'2017-04-28 10:04:40'),(37,1,159001,'ASD',0,'2017-04-28 10:04:40'),(38,1,160001,'ASD',0,'2017-04-28 10:04:41'),(39,1,161001,'ASD',0,'2017-04-28 10:04:41'),(40,1,162001,'ASD',0,'2017-04-28 10:04:41'),(41,1,163001,'ASD',0,'2017-04-28 10:04:41'),(42,1,164001,'ASD',0,'2017-04-28 10:04:41'),(43,1,165001,'ASD',0,'2017-04-28 10:04:41'),(44,1,166001,'ASD',0,'2017-04-28 10:04:41'),(45,1,167001,'ASD',0,'2017-04-28 10:04:41'),(46,1,168001,'ASD',0,'2017-04-28 10:04:41'),(47,1,169001,'ASD',0,'2017-04-28 10:04:41'),(48,1,170001,'ASD',0,'2017-04-28 10:04:41'),(49,1,171001,'ASD',0,'2017-04-28 10:04:41'),(50,1,172001,'ASD',0,'2017-04-28 10:04:41'),(51,1,173001,'ASD',0,'2017-04-28 10:04:41'),(52,1,174001,'ASD',0,'2017-04-28 10:04:41'),(53,1,175001,'ASD',0,'2017-04-28 10:04:41'),(54,1,176001,'ASD',0,'2017-04-28 10:04:41'),(55,1,177001,'ASD',0,'2017-04-28 10:04:41'),(56,1,178001,'ASD',0,'2017-04-28 10:04:41'),(57,1,179001,'ASD',0,'2017-04-28 10:04:41'),(58,1,180001,'ASD',0,'2017-04-28 10:04:41'),(59,1,181001,'ASD',0,'2017-04-28 10:04:41'),(60,1,182001,'ASD',0,'2017-04-28 10:04:41'),(61,1,183001,'ASD',0,'2017-04-28 10:04:41'),(62,1,184001,'ASD',0,'2017-04-28 10:04:41'),(63,1,185001,'ASD',0,'2017-04-28 10:04:41'),(64,1,186001,'ASD',0,'2017-04-28 10:04:41'),(65,1,187001,'ASD',0,'2017-04-28 10:04:41'),(66,1,188001,'ASD',0,'2017-04-28 10:04:41'),(67,1,189001,'ASD',0,'2017-04-28 10:04:41'),(68,1,190001,'ASD',0,'2017-04-28 10:04:41'),(69,1,191001,'ASD',0,'2017-04-28 10:04:41'),(70,1,192001,'ASD',0,'2017-04-28 10:04:41'),(71,1,193001,'ASD',0,'2017-04-28 10:04:41'),(72,1,194001,'ASD',0,'2017-04-28 10:04:41'),(73,1,195001,'ASD',0,'2017-04-28 10:04:41'),(74,1,196001,'ASD',0,'2017-04-28 10:04:41'),(75,1,197001,'ASD',0,'2017-04-28 10:04:41'),(76,1,198001,'ASD',0,'2017-04-28 10:04:41'),(77,1,199001,'ASD',0,'2017-04-28 10:04:41'),(78,1,200001,'ASD',0,'2017-04-28 10:04:41'),(79,1,201001,'ASD',0,'2017-04-28 10:04:41'),(80,1,202001,'ASD',0,'2017-04-28 10:04:41'),(81,1,203001,'ASD',0,'2017-04-28 10:04:41'),(82,1,204001,'ASD',0,'2017-04-28 10:04:41'),(83,1,205001,'ASD',0,'2017-04-28 10:04:41'),(84,1,206001,'ASD',0,'2017-04-28 10:04:41'),(85,1,207001,'ASD',0,'2017-04-28 10:04:41'),(86,1,208001,'ASD',0,'2017-04-28 10:04:41'),(87,1,209001,'ASD',0,'2017-04-28 10:04:41'),(88,1,210001,'ASD',0,'2017-04-28 10:04:41'),(89,1,211001,'ASD',0,'2017-04-28 10:04:41'),(90,1,212001,'ASD',0,'2017-04-28 10:04:41'),(91,1,213001,'ASD',0,'2017-04-28 10:04:41'),(92,1,214001,'ASD',0,'2017-04-28 10:04:41'),(93,1,215001,'ASD',0,'2017-04-28 10:04:41'),(94,1,216001,'ASD',0,'2017-04-28 10:04:41'),(95,1,217001,'ASD',0,'2017-04-28 10:04:41'),(96,1,218001,'ASD',0,'2017-04-28 10:04:41'),(97,1,219001,'ASD',0,'2017-04-28 10:04:41'),(98,1,220001,'ASD',0,'2017-04-28 10:04:41'),(99,1,221001,'ASD',0,'2017-04-28 10:04:41'),(100,1,222001,'ASD',0,'2017-04-28 10:04:41'),(101,2,1234001,'ASE',0,'2017-04-28 10:04:41'),(102,2,1235001,'ASE',0,'2017-04-28 10:04:41'),(103,2,1236001,'ASE',0,'2017-04-28 10:04:41'),(104,2,1237001,'ASE',0,'2017-04-28 10:04:41'),(105,2,1238001,'ASE',0,'2017-04-28 10:04:41'),(106,2,1239001,'ASE',0,'2017-04-28 10:04:41'),(107,2,1240001,'ASE',0,'2017-04-28 10:04:41'),(108,2,1241001,'ASE',0,'2017-04-28 10:04:41'),(109,2,1242001,'ASE',0,'2017-04-28 10:04:41'),(110,2,1243001,'ASE',0,'2017-04-28 10:04:41'),(111,2,1244001,'ASE',0,'2017-04-28 10:04:41'),(112,2,1245001,'ASE',0,'2017-04-28 10:04:41'),(113,2,1246001,'ASE',0,'2017-04-28 10:04:41'),(114,2,1247001,'ASE',0,'2017-04-28 10:04:41'),(115,2,1248001,'ASE',0,'2017-04-28 10:04:41'),(116,2,1249001,'ASE',0,'2017-04-28 10:04:41'),(117,2,1250001,'ASE',0,'2017-04-28 10:04:41'),(118,2,1251001,'ASE',0,'2017-04-28 10:04:41'),(119,2,1252001,'ASE',0,'2017-04-28 10:04:41'),(120,2,1253001,'ASE',0,'2017-04-28 10:04:41'),(121,2,1254001,'ASE',0,'2017-04-28 10:04:41'),(122,2,1255001,'ASE',0,'2017-04-28 10:04:41'),(123,2,1256001,'ASE',0,'2017-04-28 10:04:41'),(124,2,1257001,'ASE',0,'2017-04-28 10:04:41'),(125,2,1258001,'ASE',0,'2017-04-28 10:04:41'),(126,2,1259001,'ASE',0,'2017-04-28 10:04:41'),(127,2,1260001,'ASE',0,'2017-04-28 10:04:41'),(128,2,1261001,'ASE',0,'2017-04-28 10:04:41'),(129,2,1262001,'ASE',0,'2017-04-28 10:04:41'),(130,2,1263001,'ASE',0,'2017-04-28 10:04:41'),(131,2,1264001,'ASE',0,'2017-04-28 10:04:41'),(132,2,1265001,'ASE',0,'2017-04-28 10:04:41'),(133,2,1266001,'ASE',0,'2017-04-28 10:04:41'),(134,2,1267001,'ASE',0,'2017-04-28 10:04:41'),(135,2,1268001,'ASE',0,'2017-04-28 10:04:41'),(136,2,1269001,'ASE',0,'2017-04-28 10:04:41'),(137,2,1270001,'ASE',0,'2017-04-28 10:04:41'),(138,2,1271001,'ASE',0,'2017-04-28 10:04:41'),(139,2,1272001,'ASE',0,'2017-04-28 10:04:41'),(140,2,1273001,'ASE',0,'2017-04-28 10:04:41'),(141,2,1274001,'ASE',0,'2017-04-28 10:04:41'),(142,2,1275001,'ASE',0,'2017-04-28 10:04:41'),(143,2,1276001,'ASE',0,'2017-04-28 10:04:41'),(144,2,1277001,'ASE',0,'2017-04-28 10:04:41'),(145,2,1278001,'ASE',0,'2017-04-28 10:04:41'),(146,2,1279001,'ASE',0,'2017-04-28 10:04:41'),(147,2,1280001,'ASE',0,'2017-04-28 10:04:41'),(148,2,1281001,'ASE',0,'2017-04-28 10:04:41'),(149,2,1282001,'ASE',0,'2017-04-28 10:04:41'),(150,2,1283001,'ASE',0,'2017-04-28 10:04:41'),(151,2,1284001,'ASE',0,'2017-04-28 10:04:41'),(152,2,1285001,'ASE',0,'2017-04-28 10:04:41'),(153,2,1286001,'ASE',0,'2017-04-28 10:04:41'),(154,2,1287001,'ASE',0,'2017-04-28 10:04:41'),(155,2,1288001,'ASE',0,'2017-04-28 10:04:41'),(156,2,1289001,'ASE',0,'2017-04-28 10:04:41'),(157,2,1290001,'ASE',0,'2017-04-28 10:04:41'),(158,2,1291001,'ASE',0,'2017-04-28 10:04:41'),(159,2,1292001,'ASE',0,'2017-04-28 10:04:41'),(160,2,1293001,'ASE',0,'2017-04-28 10:04:41'),(161,2,1294001,'ASE',0,'2017-04-28 10:04:41'),(162,2,1295001,'ASE',0,'2017-04-28 10:04:41'),(163,2,1296001,'ASE',0,'2017-04-28 10:04:41'),(164,2,1297001,'ASE',0,'2017-04-28 10:04:41'),(165,2,1298001,'ASE',0,'2017-04-28 10:04:41'),(166,2,1299001,'ASE',0,'2017-04-28 10:04:41'),(167,2,1300001,'ASE',0,'2017-04-28 10:04:41'),(168,2,1301001,'ASE',0,'2017-04-28 10:04:41'),(169,2,1302001,'ASE',0,'2017-04-28 10:04:41'),(170,2,1303001,'ASE',0,'2017-04-28 10:04:41'),(171,2,1304001,'ASE',0,'2017-04-28 10:04:41'),(172,2,1305001,'ASE',0,'2017-04-28 10:04:41'),(173,2,1306001,'ASE',0,'2017-04-28 10:04:41'),(174,2,1307001,'ASE',0,'2017-04-28 10:04:41'),(175,2,1308001,'ASE',0,'2017-04-28 10:04:41'),(176,2,1309001,'ASE',0,'2017-04-28 10:04:41'),(177,2,1310001,'ASE',0,'2017-04-28 10:04:41'),(178,2,1311001,'ASE',0,'2017-04-28 10:04:41'),(179,2,1312001,'ASE',0,'2017-04-28 10:04:41'),(180,2,1313001,'ASE',0,'2017-04-28 10:04:41'),(181,2,1314001,'ASE',0,'2017-04-28 10:04:41'),(182,2,1315001,'ASE',0,'2017-04-28 10:04:41'),(183,2,1316001,'ASE',0,'2017-04-28 10:04:41'),(184,2,1317001,'ASE',0,'2017-04-28 10:04:41'),(185,2,1318001,'ASE',0,'2017-04-28 10:04:41'),(186,2,1319001,'ASE',0,'2017-04-28 10:04:41'),(187,2,1320001,'ASE',0,'2017-04-28 10:04:41'),(188,2,1321001,'ASE',0,'2017-04-28 10:04:41'),(189,2,1322001,'ASE',0,'2017-04-28 10:04:41'),(190,2,1323001,'ASE',0,'2017-04-28 10:04:41'),(191,2,1324001,'ASE',0,'2017-04-28 10:04:41'),(192,2,1325001,'ASE',0,'2017-04-28 10:04:41'),(193,2,1326001,'ASE',0,'2017-04-28 10:04:41'),(194,2,1327001,'ASE',0,'2017-04-28 10:04:41'),(195,2,1328001,'ASE',0,'2017-04-28 10:04:41'),(196,2,1329001,'ASE',0,'2017-04-28 10:04:41'),(197,2,1330001,'ASE',0,'2017-04-28 10:04:41'),(198,2,1331001,'ASE',0,'2017-04-28 10:04:41'),(199,2,1332001,'ASE',0,'2017-04-28 10:04:41'),(200,2,1333001,'ASE',0,'2017-04-28 10:04:41'),(201,4,4455001,'WER',0,'2017-04-28 10:04:41'),(202,4,4456001,'WER',0,'2017-04-28 10:04:41'),(203,4,4457001,'WER',0,'2017-04-28 10:04:41'),(204,4,4458001,'WER',0,'2017-04-28 10:04:41'),(205,4,4459001,'WER',0,'2017-04-28 10:04:41'),(206,4,4460001,'WER',0,'2017-04-28 10:04:41'),(207,4,4461001,'WER',0,'2017-04-28 10:04:41'),(208,4,4462001,'WER',0,'2017-04-28 10:04:41'),(209,4,4463001,'WER',0,'2017-04-28 10:04:41'),(210,4,4464001,'WER',0,'2017-04-28 10:04:41'),(211,4,4465001,'WER',0,'2017-04-28 10:04:41'),(212,4,4466001,'WER',0,'2017-04-28 10:04:41'),(213,4,4467001,'WER',0,'2017-04-28 10:04:41'),(214,4,4468001,'WER',0,'2017-04-28 10:04:41'),(215,4,4469001,'WER',0,'2017-04-28 10:04:41'),(216,4,4470001,'WER',0,'2017-04-28 10:04:41'),(217,4,4471001,'WER',0,'2017-04-28 10:04:41'),(218,4,4472001,'WER',0,'2017-04-28 10:04:41'),(219,4,4473001,'WER',0,'2017-04-28 10:04:41'),(220,4,4474001,'WER',0,'2017-04-28 10:04:41'),(221,4,4475001,'WER',0,'2017-04-28 10:04:41'),(222,4,4476001,'WER',0,'2017-04-28 10:04:41'),(223,4,4477001,'WER',0,'2017-04-28 10:04:41'),(224,4,4478001,'WER',0,'2017-04-28 10:04:41'),(225,4,4479001,'WER',0,'2017-04-28 10:04:41'),(226,4,4480001,'WER',0,'2017-04-28 10:04:41'),(227,4,4481001,'WER',0,'2017-04-28 10:04:41'),(228,4,4482001,'WER',0,'2017-04-28 10:04:41'),(229,4,4483001,'WER',0,'2017-04-28 10:04:41'),(230,4,4484001,'WER',0,'2017-04-28 10:04:41'),(231,4,4485001,'WER',0,'2017-04-28 10:04:41'),(232,4,4486001,'WER',0,'2017-04-28 10:04:42'),(233,4,4487001,'WER',0,'2017-04-28 10:04:42'),(234,4,4488001,'WER',0,'2017-04-28 10:04:42'),(235,4,4489001,'WER',0,'2017-04-28 10:04:42'),(236,4,4490001,'WER',0,'2017-04-28 10:04:42'),(237,4,4491001,'WER',0,'2017-04-28 10:04:42'),(238,4,4492001,'WER',0,'2017-04-28 10:04:42'),(239,4,4493001,'WER',0,'2017-04-28 10:04:42'),(240,4,4494001,'WER',0,'2017-04-28 10:04:42'),(241,4,4495001,'WER',0,'2017-04-28 10:04:42'),(242,4,4496001,'WER',0,'2017-04-28 10:04:42'),(243,4,4497001,'WER',0,'2017-04-28 10:04:42'),(244,4,4498001,'WER',0,'2017-04-28 10:04:42'),(245,4,4499001,'WER',0,'2017-04-28 10:04:42'),(246,4,4500001,'WER',0,'2017-04-28 10:04:42'),(247,4,4501001,'WER',0,'2017-04-28 10:04:42'),(248,4,4502001,'WER',0,'2017-04-28 10:04:42'),(249,4,4503001,'WER',0,'2017-04-28 10:04:42'),(250,4,4504001,'WER',0,'2017-04-28 10:04:42'),(251,4,4505001,'WER',0,'2017-04-28 10:04:42'),(252,4,4506001,'WER',0,'2017-04-28 10:04:42'),(253,4,4507001,'WER',0,'2017-04-28 10:04:42'),(254,4,4508001,'WER',0,'2017-04-28 10:04:42'),(255,4,4509001,'WER',0,'2017-04-28 10:04:42'),(256,4,4510001,'WER',0,'2017-04-28 10:04:42'),(257,4,4511001,'WER',0,'2017-04-28 10:04:42'),(258,4,4512001,'WER',0,'2017-04-28 10:04:42'),(259,4,4513001,'WER',0,'2017-04-28 10:04:42'),(260,4,4514001,'WER',0,'2017-04-28 10:04:42'),(261,4,4515001,'WER',0,'2017-04-28 10:04:42'),(262,4,4516001,'WER',0,'2017-04-28 10:04:42'),(263,4,4517001,'WER',0,'2017-04-28 10:04:42'),(264,4,4518001,'WER',0,'2017-04-28 10:04:42'),(265,4,4519001,'WER',0,'2017-04-28 10:04:42'),(266,4,4520001,'WER',0,'2017-04-28 10:04:42'),(267,4,4521001,'WER',0,'2017-04-28 10:04:42'),(268,4,4522001,'WER',0,'2017-04-28 10:04:42'),(269,4,4523001,'WER',0,'2017-04-28 10:04:42'),(270,4,4524001,'WER',0,'2017-04-28 10:04:42'),(271,4,4525001,'WER',0,'2017-04-28 10:04:42'),(272,4,4526001,'WER',0,'2017-04-28 10:04:42'),(273,4,4527001,'WER',0,'2017-04-28 10:04:42'),(274,4,4528001,'WER',0,'2017-04-28 10:04:42'),(275,4,4529001,'WER',0,'2017-04-28 10:04:42'),(276,4,4530001,'WER',0,'2017-04-28 10:04:42'),(277,4,4531001,'WER',0,'2017-04-28 10:04:42'),(278,4,4532001,'WER',0,'2017-04-28 10:04:42'),(279,4,4533001,'WER',0,'2017-04-28 10:04:42'),(280,4,4534001,'WER',0,'2017-04-28 10:04:42'),(281,4,4535001,'WER',0,'2017-04-28 10:04:42'),(282,4,4536001,'WER',0,'2017-04-28 10:04:42'),(283,4,4537001,'WER',0,'2017-04-28 10:04:42'),(284,4,4538001,'WER',0,'2017-04-28 10:04:42'),(285,4,4539001,'WER',0,'2017-04-28 10:04:42'),(286,4,4540001,'WER',0,'2017-04-28 10:04:42'),(287,4,4541001,'WER',0,'2017-04-28 10:04:42'),(288,4,4542001,'WER',0,'2017-04-28 10:04:42'),(289,4,4543001,'WER',0,'2017-04-28 10:04:42'),(290,4,4544001,'WER',0,'2017-04-28 10:04:42'),(291,4,4545001,'WER',0,'2017-04-28 10:04:42'),(292,4,4546001,'WER',0,'2017-04-28 10:04:42'),(293,4,4547001,'WER',0,'2017-04-28 10:04:42'),(294,4,4548001,'WER',0,'2017-04-28 10:04:42'),(295,4,4549001,'WER',0,'2017-04-28 10:04:42'),(296,4,4550001,'WER',0,'2017-04-28 10:04:42'),(297,4,4551001,'WER',0,'2017-04-28 10:04:42'),(298,4,4552001,'WER',0,'2017-04-28 10:04:42'),(299,4,4553001,'WER',0,'2017-04-28 10:04:42'),(300,4,4554001,'WER',0,'2017-04-28 10:04:42'),(301,4,7896001,'ESC',0,'2017-04-29 12:50:12'),(302,4,7897001,'ESC',0,'2017-04-29 12:50:12'),(303,4,7898001,'ESC',0,'2017-04-29 12:50:12'),(304,4,7899001,'ESC',0,'2017-04-29 12:50:12'),(305,4,7900001,'ESC',0,'2017-04-29 12:50:12'),(306,4,7901001,'ESC',0,'2017-04-29 12:50:12'),(307,4,7902001,'ESC',0,'2017-04-29 12:50:12'),(308,4,7903001,'ESC',0,'2017-04-29 12:50:12'),(309,4,7904001,'ESC',0,'2017-04-29 12:50:12'),(310,4,7905001,'ESC',0,'2017-04-29 12:50:12'),(311,5,1134001,'ESL',0,'2017-04-29 12:50:12'),(312,5,1135001,'ESL',0,'2017-04-29 12:50:12'),(313,5,1136001,'ESL',0,'2017-04-29 12:50:12'),(314,5,1137001,'ESL',0,'2017-04-29 12:50:12'),(315,5,1138001,'ESL',0,'2017-04-29 12:50:12'),(316,5,1139001,'ESL',0,'2017-04-29 12:50:12'),(317,5,1140001,'ESL',0,'2017-04-29 12:50:12'),(318,5,1141001,'ESL',0,'2017-04-29 12:50:12'),(319,5,1142001,'ESL',0,'2017-04-29 12:50:12'),(320,5,1143001,'ESL',0,'2017-04-29 12:50:12'),(321,5,1144001,'ESL',0,'2017-04-29 12:50:12'),(322,5,1145001,'ESL',0,'2017-04-29 12:50:12'),(323,5,1146001,'ESL',0,'2017-04-29 12:50:12'),(324,5,1147001,'ESL',0,'2017-04-29 12:50:12'),(325,5,1148001,'ESL',0,'2017-04-29 12:50:12'),(326,5,1149001,'ESL',0,'2017-04-29 12:50:12'),(327,5,1150001,'ESL',0,'2017-04-29 12:50:12'),(328,5,1151001,'ESL',0,'2017-04-29 12:50:12'),(329,5,1152001,'ESL',0,'2017-04-29 12:50:12'),(330,5,1153001,'ESL',0,'2017-04-29 12:50:12'),(331,3,111001,'PPP',0,'2017-04-29 14:00:11'),(332,3,112001,'PPP',0,'2017-04-29 14:00:11'),(333,3,113001,'PPP',0,'2017-04-29 14:00:11'),(334,3,114001,'PPP',0,'2017-04-29 14:00:11'),(335,3,115001,'PPP',0,'2017-04-29 14:00:11'),(336,3,116001,'PPP',0,'2017-04-29 14:00:11'),(337,3,117001,'PPP',0,'2017-04-29 14:00:11'),(338,3,118001,'PPP',0,'2017-04-29 14:00:11'),(339,3,119001,'PPP',0,'2017-04-29 14:00:11'),(340,3,120001,'PPP',0,'2017-04-29 14:00:11'),(341,3,121001,'PPP',0,'2017-04-29 14:00:11'),(342,3,122001,'PPP',0,'2017-04-29 14:00:11'),(343,3,123001,'PPP',0,'2017-04-29 14:00:11'),(344,3,124001,'PPP',0,'2017-04-29 14:00:11'),(345,3,125001,'PPP',0,'2017-04-29 14:00:11'),(346,3,126001,'PPP',0,'2017-04-29 14:00:11'),(347,3,127001,'PPP',0,'2017-04-29 14:00:11'),(348,3,128001,'PPP',0,'2017-04-29 14:00:11'),(349,3,129001,'PPP',0,'2017-04-29 14:00:11'),(350,3,130001,'PPP',0,'2017-04-29 14:00:11'),(351,3,131001,'PPP',0,'2017-04-29 14:00:11'),(352,3,132001,'PPP',0,'2017-04-29 14:00:11'),(353,3,133001,'PPP',0,'2017-04-29 14:00:11'),(354,3,134001,'PPP',0,'2017-04-29 14:00:11'),(355,3,135001,'PPP',0,'2017-04-29 14:00:11'),(356,3,136001,'PPP',0,'2017-04-29 14:00:11'),(357,3,137001,'PPP',0,'2017-04-29 14:00:11'),(358,3,138001,'PPP',0,'2017-04-29 14:00:11'),(359,3,139001,'PPP',0,'2017-04-29 14:00:11'),(360,3,140001,'PPP',0,'2017-04-29 14:00:11'),(361,3,141001,'PPP',0,'2017-04-29 14:00:11'),(362,3,142001,'PPP',0,'2017-04-29 14:00:11'),(363,3,143001,'PPP',0,'2017-04-29 14:00:11'),(364,3,144001,'PPP',0,'2017-04-29 14:00:11'),(365,3,145001,'PPP',0,'2017-04-29 14:00:11'),(366,3,146001,'PPP',0,'2017-04-29 14:00:11'),(367,3,147001,'PPP',0,'2017-04-29 14:00:11'),(368,3,148001,'PPP',0,'2017-04-29 14:00:11'),(369,3,149001,'PPP',0,'2017-04-29 14:00:11'),(370,3,150001,'PPP',0,'2017-04-29 14:00:11'),(371,3,151001,'PPP',0,'2017-04-29 14:00:11'),(372,3,152001,'PPP',0,'2017-04-29 14:00:11'),(373,3,153001,'PPP',0,'2017-04-29 14:00:11'),(374,3,154001,'PPP',0,'2017-04-29 14:00:11'),(375,3,155001,'PPP',0,'2017-04-29 14:00:11'),(376,3,156001,'PPP',0,'2017-04-29 14:00:11'),(377,3,157001,'PPP',0,'2017-04-29 14:00:11'),(378,3,158001,'PPP',0,'2017-04-29 14:00:11'),(379,3,159001,'PPP',0,'2017-04-29 14:00:11'),(380,3,160001,'PPP',0,'2017-04-29 14:00:11'),(381,3,161001,'PPP',0,'2017-04-29 14:00:11'),(382,3,162001,'PPP',0,'2017-04-29 14:00:11'),(383,3,163001,'PPP',0,'2017-04-29 14:00:11'),(384,3,164001,'PPP',0,'2017-04-29 14:00:11'),(385,3,165001,'PPP',0,'2017-04-29 14:00:11'),(386,3,166001,'PPP',0,'2017-04-29 14:00:11'),(387,3,167001,'PPP',0,'2017-04-29 14:00:11'),(388,3,168001,'PPP',0,'2017-04-29 14:00:11'),(389,3,169001,'PPP',0,'2017-04-29 14:00:11'),(390,3,170001,'PPP',0,'2017-04-29 14:00:11'),(391,3,171001,'PPP',0,'2017-04-29 14:00:12'),(392,3,172001,'PPP',0,'2017-04-29 14:00:12'),(393,3,173001,'PPP',0,'2017-04-29 14:00:12'),(394,3,174001,'PPP',0,'2017-04-29 14:00:12'),(395,3,175001,'PPP',0,'2017-04-29 14:00:12'),(396,3,176001,'PPP',0,'2017-04-29 14:00:12'),(397,3,177001,'PPP',0,'2017-04-29 14:00:12'),(398,3,178001,'PPP',0,'2017-04-29 14:00:12'),(399,3,179001,'PPP',0,'2017-04-29 14:00:12'),(400,3,180001,'PPP',0,'2017-04-29 14:00:12'),(401,3,181001,'PPP',0,'2017-04-29 14:00:12'),(402,3,182001,'PPP',0,'2017-04-29 14:00:12'),(403,3,183001,'PPP',0,'2017-04-29 14:00:12'),(404,3,184001,'PPP',0,'2017-04-29 14:00:12'),(405,3,185001,'PPP',0,'2017-04-29 14:00:12'),(406,3,186001,'PPP',0,'2017-04-29 14:00:12'),(407,3,187001,'PPP',0,'2017-04-29 14:00:12'),(408,3,188001,'PPP',0,'2017-04-29 14:00:12'),(409,3,189001,'PPP',0,'2017-04-29 14:00:12'),(410,3,190001,'PPP',0,'2017-04-29 14:00:12'),(411,3,191001,'PPP',0,'2017-04-29 14:00:12'),(412,3,192001,'PPP',0,'2017-04-29 14:00:12'),(413,3,193001,'PPP',0,'2017-04-29 14:00:12'),(414,3,194001,'PPP',0,'2017-04-29 14:00:12'),(415,3,195001,'PPP',0,'2017-04-29 14:00:12'),(416,3,196001,'PPP',0,'2017-04-29 14:00:12'),(417,3,197001,'PPP',0,'2017-04-29 14:00:12'),(418,3,198001,'PPP',0,'2017-04-29 14:00:12'),(419,3,199001,'PPP',0,'2017-04-29 14:00:12'),(420,3,200001,'PPP',0,'2017-04-29 14:00:12'),(421,3,201001,'PPP',0,'2017-04-29 14:00:12'),(422,3,202001,'PPP',0,'2017-04-29 14:00:12'),(423,3,203001,'PPP',0,'2017-04-29 14:00:12'),(424,3,204001,'PPP',0,'2017-04-29 14:00:12'),(425,3,205001,'PPP',0,'2017-04-29 14:00:12'),(426,3,206001,'PPP',0,'2017-04-29 14:00:12'),(427,3,207001,'PPP',0,'2017-04-29 14:00:12'),(428,3,208001,'PPP',0,'2017-04-29 14:00:12'),(429,3,209001,'PPP',0,'2017-04-29 14:00:12'),(430,3,210001,'PPP',0,'2017-04-29 14:00:12'),(431,6,222001,'QQQ',0,'2017-04-29 14:00:12'),(432,6,223001,'QQQ',0,'2017-04-29 14:00:12'),(433,6,224001,'QQQ',0,'2017-04-29 14:00:12'),(434,6,225001,'QQQ',0,'2017-04-29 14:00:12'),(435,6,226001,'QQQ',0,'2017-04-29 14:00:12'),(436,6,227001,'QQQ',0,'2017-04-29 14:00:12'),(437,6,228001,'QQQ',0,'2017-04-29 14:00:12'),(438,6,229001,'QQQ',0,'2017-04-29 14:00:12'),(439,6,230001,'QQQ',0,'2017-04-29 14:00:12'),(440,6,231001,'QQQ',0,'2017-04-29 14:00:12'),(441,6,232001,'QQQ',0,'2017-04-29 14:00:12'),(442,6,233001,'QQQ',0,'2017-04-29 14:00:12'),(443,6,234001,'QQQ',0,'2017-04-29 14:00:12'),(444,6,235001,'QQQ',0,'2017-04-29 14:00:12'),(445,6,236001,'QQQ',0,'2017-04-29 14:00:12'),(446,6,237001,'QQQ',0,'2017-04-29 14:00:12'),(447,6,238001,'QQQ',0,'2017-04-29 14:00:12'),(448,6,239001,'QQQ',0,'2017-04-29 14:00:12'),(449,6,240001,'QQQ',0,'2017-04-29 14:00:12'),(450,6,241001,'QQQ',0,'2017-04-29 14:00:12'),(451,6,242001,'QQQ',0,'2017-04-29 14:00:12'),(452,6,243001,'QQQ',0,'2017-04-29 14:00:12'),(453,6,244001,'QQQ',0,'2017-04-29 14:00:12'),(454,6,245001,'QQQ',0,'2017-04-29 14:00:12'),(455,6,246001,'QQQ',0,'2017-04-29 14:00:12'),(456,6,247001,'QQQ',0,'2017-04-29 14:00:12'),(457,6,248001,'QQQ',0,'2017-04-29 14:00:12'),(458,6,249001,'QQQ',0,'2017-04-29 14:00:12'),(459,6,250001,'QQQ',0,'2017-04-29 14:00:12'),(460,6,251001,'QQQ',0,'2017-04-29 14:00:12'),(461,6,252001,'QQQ',0,'2017-04-29 14:00:12'),(462,6,253001,'QQQ',0,'2017-04-29 14:00:12'),(463,6,254001,'QQQ',0,'2017-04-29 14:00:12'),(464,6,255001,'QQQ',0,'2017-04-29 14:00:12'),(465,6,256001,'QQQ',0,'2017-04-29 14:00:12'),(466,6,257001,'QQQ',0,'2017-04-29 14:00:12'),(467,6,258001,'QQQ',0,'2017-04-29 14:00:12'),(468,6,259001,'QQQ',0,'2017-04-29 14:00:12'),(469,6,260001,'QQQ',0,'2017-04-29 14:00:12'),(470,6,261001,'QQQ',0,'2017-04-29 14:00:12'),(471,6,262001,'QQQ',0,'2017-04-29 14:00:12'),(472,6,263001,'QQQ',0,'2017-04-29 14:00:12'),(473,6,264001,'QQQ',0,'2017-04-29 14:00:12'),(474,6,265001,'QQQ',0,'2017-04-29 14:00:12'),(475,6,266001,'QQQ',0,'2017-04-29 14:00:12'),(476,6,267001,'QQQ',0,'2017-04-29 14:00:12'),(477,6,268001,'QQQ',0,'2017-04-29 14:00:12'),(478,6,269001,'QQQ',0,'2017-04-29 14:00:12'),(479,6,270001,'QQQ',0,'2017-04-29 14:00:12'),(480,6,271001,'QQQ',0,'2017-04-29 14:00:12'),(481,6,272001,'QQQ',0,'2017-04-29 14:00:12'),(482,6,273001,'QQQ',0,'2017-04-29 14:00:12'),(483,6,274001,'QQQ',0,'2017-04-29 14:00:12'),(484,6,275001,'QQQ',0,'2017-04-29 14:00:12'),(485,6,276001,'QQQ',0,'2017-04-29 14:00:12'),(486,6,277001,'QQQ',0,'2017-04-29 14:00:12'),(487,6,278001,'QQQ',0,'2017-04-29 14:00:12'),(488,6,279001,'QQQ',0,'2017-04-29 14:00:12'),(489,6,280001,'QQQ',0,'2017-04-29 14:00:12'),(490,6,281001,'QQQ',0,'2017-04-29 14:00:12'),(491,6,282001,'QQQ',0,'2017-04-29 14:00:12'),(492,6,283001,'QQQ',0,'2017-04-29 14:00:12'),(493,6,284001,'QQQ',0,'2017-04-29 14:00:12'),(494,6,285001,'QQQ',0,'2017-04-29 14:00:12'),(495,6,286001,'QQQ',0,'2017-04-29 14:00:12'),(496,6,287001,'QQQ',0,'2017-04-29 14:00:12'),(497,6,288001,'QQQ',0,'2017-04-29 14:00:12'),(498,6,289001,'QQQ',0,'2017-04-29 14:00:12'),(499,6,290001,'QQQ',0,'2017-04-29 14:00:12'),(500,6,291001,'QQQ',0,'2017-04-29 14:00:12'),(501,6,292001,'QQQ',0,'2017-04-29 14:00:12'),(502,6,293001,'QQQ',0,'2017-04-29 14:00:12'),(503,6,294001,'QQQ',0,'2017-04-29 14:00:12'),(504,6,295001,'QQQ',0,'2017-04-29 14:00:12'),(505,6,296001,'QQQ',0,'2017-04-29 14:00:12'),(506,6,297001,'QQQ',0,'2017-04-29 14:00:12'),(507,6,298001,'QQQ',0,'2017-04-29 14:00:12'),(508,6,299001,'QQQ',0,'2017-04-29 14:00:12'),(509,6,300001,'QQQ',0,'2017-04-29 14:00:12'),(510,6,301001,'QQQ',0,'2017-04-29 14:00:12'),(511,6,302001,'QQQ',0,'2017-04-29 14:00:12'),(512,6,303001,'QQQ',0,'2017-04-29 14:00:12'),(513,6,304001,'QQQ',0,'2017-04-29 14:00:12'),(514,6,305001,'QQQ',0,'2017-04-29 14:00:12'),(515,6,306001,'QQQ',0,'2017-04-29 14:00:12'),(516,6,307001,'QQQ',0,'2017-04-29 14:00:12'),(517,6,308001,'QQQ',0,'2017-04-29 14:00:12'),(518,6,309001,'QQQ',0,'2017-04-29 14:00:12'),(519,6,310001,'QQQ',0,'2017-04-29 14:00:12'),(520,6,311001,'QQQ',0,'2017-04-29 14:00:12'),(521,6,312001,'QQQ',0,'2017-04-29 14:00:12'),(522,6,313001,'QQQ',0,'2017-04-29 14:00:12'),(523,6,314001,'QQQ',0,'2017-04-29 14:00:12'),(524,6,315001,'QQQ',0,'2017-04-29 14:00:12'),(525,6,316001,'QQQ',0,'2017-04-29 14:00:12'),(526,6,317001,'QQQ',0,'2017-04-29 14:00:12'),(527,6,318001,'QQQ',0,'2017-04-29 14:00:12'),(528,6,319001,'QQQ',0,'2017-04-29 14:00:12'),(529,6,320001,'QQQ',0,'2017-04-29 14:00:12'),(530,6,321001,'QQQ',0,'2017-04-29 14:00:12'),(531,2,123001,'ASD',0,'2017-05-02 09:57:28'),(532,2,124001,'ASD',0,'2017-05-02 09:57:28'),(533,2,125001,'ASD',0,'2017-05-02 09:57:28'),(534,2,126001,'ASD',0,'2017-05-02 09:57:28'),(535,2,127001,'ASD',0,'2017-05-02 09:57:28'),(536,2,128001,'ASD',0,'2017-05-02 09:57:28'),(537,2,129001,'ASD',0,'2017-05-02 09:57:28'),(538,2,130001,'ASD',0,'2017-05-02 09:57:28'),(539,2,131001,'ASD',0,'2017-05-02 09:57:28'),(540,2,132001,'ASD',0,'2017-05-02 09:57:28'),(541,2,133001,'ASD',0,'2017-05-02 09:57:28'),(542,2,134001,'ASD',0,'2017-05-02 09:57:28'),(543,2,135001,'ASD',0,'2017-05-02 09:57:28'),(544,2,136001,'ASD',0,'2017-05-02 09:57:28'),(545,2,137001,'ASD',0,'2017-05-02 09:57:28'),(546,2,138001,'ASD',0,'2017-05-02 09:57:28'),(547,2,139001,'ASD',0,'2017-05-02 09:57:28'),(548,2,140001,'ASD',0,'2017-05-02 09:57:28'),(549,2,141001,'ASD',0,'2017-05-02 09:57:28'),(550,2,142001,'ASD',0,'2017-05-02 09:57:28'),(551,2,143001,'ASD',0,'2017-05-02 09:57:28'),(552,2,144001,'ASD',0,'2017-05-02 09:57:28'),(553,2,145001,'ASD',0,'2017-05-02 09:57:28'),(554,2,146001,'ASD',0,'2017-05-02 09:57:28'),(555,2,147001,'ASD',0,'2017-05-02 09:57:28'),(556,2,148001,'ASD',0,'2017-05-02 09:57:28'),(557,2,149001,'ASD',0,'2017-05-02 09:57:28'),(558,2,150001,'ASD',0,'2017-05-02 09:57:28'),(559,2,151001,'ASD',0,'2017-05-02 09:57:28'),(560,2,152001,'ASD',0,'2017-05-02 09:57:28'),(561,2,153001,'ASD',0,'2017-05-02 09:57:28'),(562,2,154001,'ASD',0,'2017-05-02 09:57:28'),(563,2,155001,'ASD',0,'2017-05-02 09:57:28'),(564,2,156001,'ASD',0,'2017-05-02 09:57:28'),(565,2,157001,'ASD',0,'2017-05-02 09:57:28'),(566,2,158001,'ASD',0,'2017-05-02 09:57:28'),(567,2,159001,'ASD',0,'2017-05-02 09:57:28'),(568,2,160001,'ASD',0,'2017-05-02 09:57:28'),(569,2,161001,'ASD',0,'2017-05-02 09:57:28'),(570,2,162001,'ASD',0,'2017-05-02 09:57:28'),(571,2,163001,'ASD',0,'2017-05-02 09:57:28'),(572,2,164001,'ASD',0,'2017-05-02 09:57:28'),(573,2,165001,'ASD',0,'2017-05-02 09:57:28'),(574,2,166001,'ASD',0,'2017-05-02 09:57:28'),(575,2,167001,'ASD',0,'2017-05-02 09:57:28'),(576,2,168001,'ASD',0,'2017-05-02 09:57:28'),(577,2,169001,'ASD',0,'2017-05-02 09:57:28'),(578,2,170001,'ASD',0,'2017-05-02 09:57:28'),(579,2,171001,'ASD',0,'2017-05-02 09:57:28'),(580,2,172001,'ASD',0,'2017-05-02 09:57:28'),(581,2,173001,'ASD',0,'2017-05-02 09:57:28'),(582,2,174001,'ASD',0,'2017-05-02 09:57:28'),(583,2,175001,'ASD',0,'2017-05-02 09:57:28'),(584,2,176001,'ASD',0,'2017-05-02 09:57:28'),(585,2,177001,'ASD',0,'2017-05-02 09:57:28'),(586,2,178001,'ASD',0,'2017-05-02 09:57:28'),(587,2,179001,'ASD',0,'2017-05-02 09:57:28'),(588,2,180001,'ASD',0,'2017-05-02 09:57:28'),(589,2,181001,'ASD',0,'2017-05-02 09:57:28'),(590,2,182001,'ASD',0,'2017-05-02 09:57:28'),(591,2,183001,'ASD',0,'2017-05-02 09:57:28'),(592,2,184001,'ASD',0,'2017-05-02 09:57:28'),(593,2,185001,'ASD',0,'2017-05-02 09:57:28'),(594,2,186001,'ASD',0,'2017-05-02 09:57:28'),(595,2,187001,'ASD',0,'2017-05-02 09:57:28'),(596,2,188001,'ASD',0,'2017-05-02 09:57:28'),(597,2,189001,'ASD',0,'2017-05-02 09:57:28'),(598,2,190001,'ASD',0,'2017-05-02 09:57:28'),(599,2,191001,'ASD',0,'2017-05-02 09:57:28'),(600,2,192001,'ASD',0,'2017-05-02 09:57:28'),(601,2,193001,'ASD',0,'2017-05-02 09:57:28'),(602,2,194001,'ASD',0,'2017-05-02 09:57:28'),(603,2,195001,'ASD',0,'2017-05-02 09:57:28'),(604,2,196001,'ASD',0,'2017-05-02 09:57:28'),(605,2,197001,'ASD',0,'2017-05-02 09:57:28'),(606,2,198001,'ASD',0,'2017-05-02 09:57:28'),(607,2,199001,'ASD',0,'2017-05-02 09:57:28'),(608,2,200001,'ASD',0,'2017-05-02 09:57:28'),(609,2,201001,'ASD',0,'2017-05-02 09:57:28'),(610,2,202001,'ASD',0,'2017-05-02 09:57:28'),(611,2,203001,'ASD',0,'2017-05-02 09:57:28'),(612,2,204001,'ASD',0,'2017-05-02 09:57:28'),(613,2,205001,'ASD',0,'2017-05-02 09:57:28'),(614,2,206001,'ASD',0,'2017-05-02 09:57:28'),(615,2,207001,'ASD',0,'2017-05-02 09:57:28'),(616,2,208001,'ASD',0,'2017-05-02 09:57:28'),(617,2,209001,'ASD',0,'2017-05-02 09:57:28'),(618,2,210001,'ASD',0,'2017-05-02 09:57:28'),(619,2,211001,'ASD',0,'2017-05-02 09:57:28'),(620,2,212001,'ASD',0,'2017-05-02 09:57:28'),(621,2,213001,'ASD',0,'2017-05-02 09:57:28'),(622,2,214001,'ASD',0,'2017-05-02 09:57:28'),(623,2,215001,'ASD',0,'2017-05-02 09:57:28'),(624,2,216001,'ASD',0,'2017-05-02 09:57:28'),(625,2,217001,'ASD',0,'2017-05-02 09:57:28'),(626,2,218001,'ASD',0,'2017-05-02 09:57:28'),(627,2,219001,'ASD',0,'2017-05-02 09:57:28'),(628,2,220001,'ASD',0,'2017-05-02 09:57:28'),(629,2,221001,'ASD',0,'2017-05-02 09:57:28'),(630,2,222001,'ASD',0,'2017-05-02 09:57:28'),(631,2,11441101,'EWR',0,'2017-05-02 09:57:28'),(632,2,11442101,'EWR',0,'2017-05-02 09:57:28'),(633,2,11443101,'EWR',0,'2017-05-02 09:57:28'),(634,2,11444101,'EWR',0,'2017-05-02 09:57:28'),(635,2,11445101,'EWR',0,'2017-05-02 09:57:28'),(636,2,11446101,'EWR',0,'2017-05-02 09:57:28'),(637,2,11447101,'EWR',0,'2017-05-02 09:57:28'),(638,2,11448101,'EWR',0,'2017-05-02 09:57:28'),(639,2,11449101,'EWR',0,'2017-05-02 09:57:28'),(640,2,11450101,'EWR',0,'2017-05-02 09:57:28'),(641,2,11451101,'EWR',0,'2017-05-02 09:57:28'),(642,2,11452101,'EWR',0,'2017-05-02 09:57:28'),(643,2,11453101,'EWR',0,'2017-05-02 09:57:28'),(644,2,11454101,'EWR',0,'2017-05-02 09:57:28'),(645,2,11455101,'EWR',0,'2017-05-02 09:57:28'),(646,2,11456101,'EWR',0,'2017-05-02 09:57:28'),(647,2,11457101,'EWR',0,'2017-05-02 09:57:28'),(648,2,11458101,'EWR',0,'2017-05-02 09:57:28'),(649,2,11459101,'EWR',0,'2017-05-02 09:57:28'),(650,2,11460101,'EWR',0,'2017-05-02 09:57:28'),(651,2,11461101,'EWR',0,'2017-05-02 09:57:28'),(652,2,11462101,'EWR',0,'2017-05-02 09:57:28'),(653,2,11463101,'EWR',0,'2017-05-02 09:57:28'),(654,2,11464101,'EWR',0,'2017-05-02 09:57:28'),(655,2,11465101,'EWR',0,'2017-05-02 09:57:28'),(656,2,11466101,'EWR',0,'2017-05-02 09:57:28'),(657,2,11467101,'EWR',0,'2017-05-02 09:57:28'),(658,2,11468101,'EWR',0,'2017-05-02 09:57:28'),(659,2,11469101,'EWR',0,'2017-05-02 09:57:28'),(660,2,11470101,'EWR',0,'2017-05-02 09:57:28'),(661,2,11471101,'EWR',0,'2017-05-02 09:57:28'),(662,2,11472101,'EWR',0,'2017-05-02 09:57:28'),(663,2,11473101,'EWR',0,'2017-05-02 09:57:28'),(664,2,11474101,'EWR',0,'2017-05-02 09:57:28'),(665,2,11475101,'EWR',0,'2017-05-02 09:57:28'),(666,2,11476101,'EWR',0,'2017-05-02 09:57:28'),(667,2,11477101,'EWR',0,'2017-05-02 09:57:28'),(668,2,11478101,'EWR',0,'2017-05-02 09:57:28'),(669,2,11479101,'EWR',0,'2017-05-02 09:57:28'),(670,2,11480101,'EWR',0,'2017-05-02 09:57:28'),(671,2,11481101,'EWR',0,'2017-05-02 09:57:28'),(672,2,11482101,'EWR',0,'2017-05-02 09:57:28'),(673,2,11483101,'EWR',0,'2017-05-02 09:57:28'),(674,2,11484101,'EWR',0,'2017-05-02 09:57:28'),(675,2,11485101,'EWR',0,'2017-05-02 09:57:28'),(676,2,11486101,'EWR',0,'2017-05-02 09:57:28'),(677,2,11487101,'EWR',0,'2017-05-02 09:57:28'),(678,2,11488101,'EWR',0,'2017-05-02 09:57:28'),(679,2,11489101,'EWR',0,'2017-05-02 09:57:28'),(680,2,11490101,'EWR',0,'2017-05-02 09:57:28'),(681,2,11491101,'EWR',0,'2017-05-02 09:57:28'),(682,2,11492101,'EWR',0,'2017-05-02 09:57:28'),(683,2,11493101,'EWR',0,'2017-05-02 09:57:28'),(684,2,11494101,'EWR',0,'2017-05-02 09:57:28'),(685,2,11495101,'EWR',0,'2017-05-02 09:57:28'),(686,2,11496101,'EWR',0,'2017-05-02 09:57:28'),(687,2,11497101,'EWR',0,'2017-05-02 09:57:28'),(688,2,11498101,'EWR',0,'2017-05-02 09:57:28'),(689,2,11499101,'EWR',0,'2017-05-02 09:57:28'),(690,2,11500101,'EWR',0,'2017-05-02 09:57:28'),(691,2,11501101,'EWR',0,'2017-05-02 09:57:28'),(692,2,11502101,'EWR',0,'2017-05-02 09:57:28'),(693,2,11503101,'EWR',0,'2017-05-02 09:57:28'),(694,2,11504101,'EWR',0,'2017-05-02 09:57:28'),(695,2,11505101,'EWR',0,'2017-05-02 09:57:28'),(696,2,11506101,'EWR',0,'2017-05-02 09:57:28'),(697,2,11507101,'EWR',0,'2017-05-02 09:57:28'),(698,2,11508101,'EWR',0,'2017-05-02 09:57:28'),(699,2,11509101,'EWR',0,'2017-05-02 09:57:28'),(700,2,11510101,'EWR',0,'2017-05-02 09:57:28'),(701,2,11511101,'EWR',0,'2017-05-02 09:57:28'),(702,2,11512101,'EWR',0,'2017-05-02 09:57:28'),(703,2,11513101,'EWR',0,'2017-05-02 09:57:28'),(704,2,11514101,'EWR',0,'2017-05-02 09:57:28'),(705,2,11515101,'EWR',0,'2017-05-02 09:57:28'),(706,2,11516101,'EWR',0,'2017-05-02 09:57:28'),(707,2,11517101,'EWR',0,'2017-05-02 09:57:28'),(708,2,11518101,'EWR',0,'2017-05-02 09:57:28'),(709,2,11519101,'EWR',0,'2017-05-02 09:57:28'),(710,2,11520101,'EWR',0,'2017-05-02 09:57:28'),(711,2,11521101,'EWR',0,'2017-05-02 09:57:29'),(712,2,11522101,'EWR',0,'2017-05-02 09:57:29'),(713,2,11523101,'EWR',0,'2017-05-02 09:57:29'),(714,2,11524101,'EWR',0,'2017-05-02 09:57:29'),(715,2,11525101,'EWR',0,'2017-05-02 09:57:29'),(716,2,11526101,'EWR',0,'2017-05-02 09:57:29'),(717,2,11527101,'EWR',0,'2017-05-02 09:57:29'),(718,2,11528101,'EWR',0,'2017-05-02 09:57:29'),(719,2,11529101,'EWR',0,'2017-05-02 09:57:29'),(720,2,11530101,'EWR',0,'2017-05-02 09:57:29'),(721,2,11531101,'EWR',0,'2017-05-02 09:57:29'),(722,2,11532101,'EWR',0,'2017-05-02 09:57:29'),(723,2,11533101,'EWR',0,'2017-05-02 09:57:29'),(724,2,11534101,'EWR',0,'2017-05-02 09:57:29'),(725,2,11535101,'EWR',0,'2017-05-02 09:57:29'),(726,2,11536101,'EWR',0,'2017-05-02 09:57:29'),(727,2,11537101,'EWR',0,'2017-05-02 09:57:29'),(728,2,11538101,'EWR',0,'2017-05-02 09:57:29'),(729,2,11539101,'EWR',0,'2017-05-02 09:57:29'),(730,2,11540101,'EWR',0,'2017-05-02 09:57:29'),(731,1,123001,'WWW',0,'2017-05-02 10:09:18'),(732,1,124001,'WWW',0,'2017-05-02 10:09:18'),(733,1,125001,'WWW',0,'2017-05-02 10:09:18'),(734,1,126001,'WWW',0,'2017-05-02 10:09:18'),(735,1,127001,'WWW',0,'2017-05-02 10:09:18'),(736,1,128001,'WWW',0,'2017-05-02 10:09:18'),(737,1,129001,'WWW',0,'2017-05-02 10:09:18'),(738,1,130001,'WWW',0,'2017-05-02 10:09:18'),(739,1,131001,'WWW',0,'2017-05-02 10:09:18'),(740,1,132001,'WWW',0,'2017-05-02 10:09:18'),(741,1,133001,'WWW',0,'2017-05-02 10:09:18'),(742,1,134001,'WWW',0,'2017-05-02 10:09:18'),(743,1,135001,'WWW',0,'2017-05-02 10:09:18'),(744,1,136001,'WWW',0,'2017-05-02 10:09:18'),(745,1,137001,'WWW',0,'2017-05-02 10:09:18'),(746,1,138001,'WWW',0,'2017-05-02 10:09:18'),(747,1,139001,'WWW',0,'2017-05-02 10:09:18'),(748,1,140001,'WWW',0,'2017-05-02 10:09:18'),(749,1,141001,'WWW',0,'2017-05-02 10:09:18'),(750,1,142001,'WWW',0,'2017-05-02 10:09:18'),(751,1,143001,'WWW',0,'2017-05-02 10:09:18'),(752,1,144001,'WWW',0,'2017-05-02 10:09:18'),(753,1,145001,'WWW',0,'2017-05-02 10:09:18'),(754,1,146001,'WWW',0,'2017-05-02 10:09:18'),(755,1,147001,'WWW',0,'2017-05-02 10:09:18'),(756,1,148001,'WWW',0,'2017-05-02 10:09:18'),(757,1,149001,'WWW',0,'2017-05-02 10:09:18'),(758,1,150001,'WWW',0,'2017-05-02 10:09:18'),(759,1,151001,'WWW',0,'2017-05-02 10:09:18'),(760,1,152001,'WWW',0,'2017-05-02 10:09:18'),(761,1,153001,'WWW',0,'2017-05-02 10:09:18'),(762,1,154001,'WWW',0,'2017-05-02 10:09:18'),(763,1,155001,'WWW',0,'2017-05-02 10:09:18'),(764,1,156001,'WWW',0,'2017-05-02 10:09:18'),(765,1,157001,'WWW',0,'2017-05-02 10:09:18'),(766,1,158001,'WWW',0,'2017-05-02 10:09:18'),(767,1,159001,'WWW',0,'2017-05-02 10:09:18'),(768,1,160001,'WWW',0,'2017-05-02 10:09:18'),(769,1,161001,'WWW',0,'2017-05-02 10:09:18'),(770,1,162001,'WWW',0,'2017-05-02 10:09:18'),(771,1,163001,'WWW',0,'2017-05-02 10:09:18'),(772,1,164001,'WWW',0,'2017-05-02 10:09:18'),(773,1,165001,'WWW',0,'2017-05-02 10:09:18'),(774,1,166001,'WWW',0,'2017-05-02 10:09:18'),(775,1,167001,'WWW',0,'2017-05-02 10:09:18'),(776,1,168001,'WWW',0,'2017-05-02 10:09:18'),(777,1,169001,'WWW',0,'2017-05-02 10:09:18'),(778,1,170001,'WWW',0,'2017-05-02 10:09:18'),(779,1,171001,'WWW',0,'2017-05-02 10:09:18'),(780,1,172001,'WWW',0,'2017-05-02 10:09:18'),(781,1,173001,'WWW',0,'2017-05-02 10:09:18'),(782,1,174001,'WWW',0,'2017-05-02 10:09:18'),(783,1,175001,'WWW',0,'2017-05-02 10:09:18'),(784,1,176001,'WWW',0,'2017-05-02 10:09:18'),(785,1,177001,'WWW',0,'2017-05-02 10:09:18'),(786,1,178001,'WWW',0,'2017-05-02 10:09:18'),(787,1,179001,'WWW',0,'2017-05-02 10:09:18'),(788,1,180001,'WWW',0,'2017-05-02 10:09:18'),(789,1,181001,'WWW',0,'2017-05-02 10:09:18'),(790,1,182001,'WWW',0,'2017-05-02 10:09:18'),(791,1,183001,'WWW',0,'2017-05-02 10:09:18'),(792,1,184001,'WWW',0,'2017-05-02 10:09:18'),(793,1,185001,'WWW',0,'2017-05-02 10:09:18'),(794,1,186001,'WWW',0,'2017-05-02 10:09:18'),(795,1,187001,'WWW',0,'2017-05-02 10:09:18'),(796,1,188001,'WWW',0,'2017-05-02 10:09:18'),(797,1,189001,'WWW',0,'2017-05-02 10:09:18'),(798,1,190001,'WWW',0,'2017-05-02 10:09:18'),(799,1,191001,'WWW',0,'2017-05-02 10:09:18'),(800,1,192001,'WWW',0,'2017-05-02 10:09:18'),(801,1,193001,'WWW',0,'2017-05-02 10:09:18'),(802,1,194001,'WWW',0,'2017-05-02 10:09:18'),(803,1,195001,'WWW',0,'2017-05-02 10:09:18'),(804,1,196001,'WWW',0,'2017-05-02 10:09:18'),(805,1,197001,'WWW',0,'2017-05-02 10:09:18'),(806,1,198001,'WWW',0,'2017-05-02 10:09:18'),(807,1,199001,'WWW',0,'2017-05-02 10:09:18'),(808,1,200001,'WWW',0,'2017-05-02 10:09:18'),(809,1,201001,'WWW',0,'2017-05-02 10:09:18'),(810,1,202001,'WWW',0,'2017-05-02 10:09:18'),(811,1,203001,'WWW',0,'2017-05-02 10:09:18'),(812,1,204001,'WWW',0,'2017-05-02 10:09:18'),(813,1,205001,'WWW',0,'2017-05-02 10:09:18'),(814,1,206001,'WWW',0,'2017-05-02 10:09:18'),(815,1,207001,'WWW',0,'2017-05-02 10:09:18'),(816,1,208001,'WWW',0,'2017-05-02 10:09:18'),(817,1,209001,'WWW',0,'2017-05-02 10:09:18'),(818,1,210001,'WWW',0,'2017-05-02 10:09:18'),(819,1,211001,'WWW',0,'2017-05-02 10:09:18'),(820,1,212001,'WWW',0,'2017-05-02 10:09:18'),(821,1,213001,'WWW',0,'2017-05-02 10:09:18'),(822,1,214001,'WWW',0,'2017-05-02 10:09:18'),(823,1,215001,'WWW',0,'2017-05-02 10:09:18'),(824,1,216001,'WWW',0,'2017-05-02 10:09:18'),(825,1,217001,'WWW',0,'2017-05-02 10:09:18'),(826,1,218001,'WWW',0,'2017-05-02 10:09:18'),(827,1,219001,'WWW',0,'2017-05-02 10:09:18'),(828,1,220001,'WWW',0,'2017-05-02 10:09:18'),(829,1,221001,'WWW',0,'2017-05-02 10:09:18'),(830,1,222001,'WWW',0,'2017-05-02 10:09:18'),(831,2,12201,'QWE',0,'2017-05-02 10:09:18'),(832,2,13201,'QWE',0,'2017-05-02 10:09:18'),(833,2,14201,'QWE',0,'2017-05-02 10:09:18'),(834,2,15201,'QWE',0,'2017-05-02 10:09:18'),(835,2,16201,'QWE',0,'2017-05-02 10:09:18'),(836,2,17201,'QWE',0,'2017-05-02 10:09:19'),(837,2,18201,'QWE',0,'2017-05-02 10:09:19'),(838,2,19201,'QWE',0,'2017-05-02 10:09:19'),(839,2,20201,'QWE',0,'2017-05-02 10:09:19'),(840,2,21201,'QWE',0,'2017-05-02 10:09:19'),(841,2,22201,'QWE',0,'2017-05-02 10:09:19'),(842,2,23201,'QWE',0,'2017-05-02 10:09:19'),(843,2,24201,'QWE',0,'2017-05-02 10:09:19'),(844,2,25201,'QWE',0,'2017-05-02 10:09:19'),(845,2,26201,'QWE',0,'2017-05-02 10:09:19'),(846,2,27201,'QWE',0,'2017-05-02 10:09:19'),(847,2,28201,'QWE',0,'2017-05-02 10:09:19'),(848,2,29201,'QWE',0,'2017-05-02 10:09:19'),(849,2,30201,'QWE',0,'2017-05-02 10:09:19'),(850,2,31201,'QWE',0,'2017-05-02 10:09:19'),(851,2,32201,'QWE',0,'2017-05-02 10:09:19'),(852,2,33201,'QWE',0,'2017-05-02 10:09:19'),(853,2,34201,'QWE',0,'2017-05-02 10:09:19'),(854,2,35201,'QWE',0,'2017-05-02 10:09:19'),(855,2,36201,'QWE',0,'2017-05-02 10:09:19'),(856,2,37201,'QWE',0,'2017-05-02 10:09:19'),(857,2,38201,'QWE',0,'2017-05-02 10:09:19'),(858,2,39201,'QWE',0,'2017-05-02 10:09:19'),(859,2,40201,'QWE',0,'2017-05-02 10:09:19'),(860,2,41201,'QWE',0,'2017-05-02 10:09:19'),(861,2,42201,'QWE',0,'2017-05-02 10:09:19'),(862,2,43201,'QWE',0,'2017-05-02 10:09:19'),(863,2,44201,'QWE',0,'2017-05-02 10:09:19'),(864,2,45201,'QWE',0,'2017-05-02 10:09:19'),(865,2,46201,'QWE',0,'2017-05-02 10:09:19'),(866,2,47201,'QWE',0,'2017-05-02 10:09:19'),(867,2,48201,'QWE',0,'2017-05-02 10:09:19'),(868,2,49201,'QWE',0,'2017-05-02 10:09:19'),(869,2,50201,'QWE',0,'2017-05-02 10:09:19'),(870,2,51201,'QWE',0,'2017-05-02 10:09:19'),(871,2,52201,'QWE',0,'2017-05-02 10:09:19'),(872,2,53201,'QWE',0,'2017-05-02 10:09:19'),(873,2,54201,'QWE',0,'2017-05-02 10:09:19'),(874,2,55201,'QWE',0,'2017-05-02 10:09:19'),(875,2,56201,'QWE',0,'2017-05-02 10:09:19'),(876,2,57201,'QWE',0,'2017-05-02 10:09:19'),(877,2,58201,'QWE',0,'2017-05-02 10:09:19'),(878,2,59201,'QWE',0,'2017-05-02 10:09:19'),(879,2,60201,'QWE',0,'2017-05-02 10:09:19'),(880,2,61201,'QWE',0,'2017-05-02 10:09:19'),(881,2,62201,'QWE',0,'2017-05-02 10:09:19'),(882,2,63201,'QWE',0,'2017-05-02 10:09:19'),(883,2,64201,'QWE',0,'2017-05-02 10:09:19'),(884,2,65201,'QWE',0,'2017-05-02 10:09:19'),(885,2,66201,'QWE',0,'2017-05-02 10:09:19'),(886,2,67201,'QWE',0,'2017-05-02 10:09:19'),(887,2,68201,'QWE',0,'2017-05-02 10:09:19'),(888,2,69201,'QWE',0,'2017-05-02 10:09:19'),(889,2,70201,'QWE',0,'2017-05-02 10:09:19'),(890,2,71201,'QWE',0,'2017-05-02 10:09:19'),(891,2,72201,'QWE',0,'2017-05-02 10:09:19'),(892,2,73201,'QWE',0,'2017-05-02 10:09:19'),(893,2,74201,'QWE',0,'2017-05-02 10:09:19'),(894,2,75201,'QWE',0,'2017-05-02 10:09:19'),(895,2,76201,'QWE',0,'2017-05-02 10:09:19'),(896,2,77201,'QWE',0,'2017-05-02 10:09:19'),(897,2,78201,'QWE',0,'2017-05-02 10:09:19'),(898,2,79201,'QWE',0,'2017-05-02 10:09:19'),(899,2,80201,'QWE',0,'2017-05-02 10:09:19'),(900,2,81201,'QWE',0,'2017-05-02 10:09:19'),(901,2,82201,'QWE',0,'2017-05-02 10:09:19'),(902,2,83201,'QWE',0,'2017-05-02 10:09:19'),(903,2,84201,'QWE',0,'2017-05-02 10:09:19'),(904,2,85201,'QWE',0,'2017-05-02 10:09:19'),(905,2,86201,'QWE',0,'2017-05-02 10:09:19'),(906,2,87201,'QWE',0,'2017-05-02 10:09:19'),(907,2,88201,'QWE',0,'2017-05-02 10:09:19'),(908,2,89201,'QWE',0,'2017-05-02 10:09:19'),(909,2,90201,'QWE',0,'2017-05-02 10:09:19'),(910,2,91201,'QWE',0,'2017-05-02 10:09:19'),(911,2,92201,'QWE',0,'2017-05-02 10:09:19'),(912,2,93201,'QWE',0,'2017-05-02 10:09:19'),(913,2,94201,'QWE',0,'2017-05-02 10:09:19'),(914,2,95201,'QWE',0,'2017-05-02 10:09:19'),(915,2,96201,'QWE',0,'2017-05-02 10:09:19'),(916,2,97201,'QWE',0,'2017-05-02 10:09:19'),(917,2,98201,'QWE',0,'2017-05-02 10:09:19'),(918,2,99201,'QWE',0,'2017-05-02 10:09:19'),(919,2,100201,'QWE',0,'2017-05-02 10:09:19'),(920,2,101201,'QWE',0,'2017-05-02 10:09:19'),(921,2,102201,'QWE',0,'2017-05-02 10:09:19'),(922,2,103201,'QWE',0,'2017-05-02 10:09:19'),(923,2,104201,'QWE',0,'2017-05-02 10:09:19'),(924,2,105201,'QWE',0,'2017-05-02 10:09:19'),(925,2,106201,'QWE',0,'2017-05-02 10:09:19'),(926,2,107201,'QWE',0,'2017-05-02 10:09:19'),(927,2,108201,'QWE',0,'2017-05-02 10:09:19'),(928,2,109201,'QWE',0,'2017-05-02 10:09:19'),(929,2,110201,'QWE',0,'2017-05-02 10:09:19'),(930,2,111201,'QWE',0,'2017-05-02 10:09:19'),(931,1,123001,'ASD',0,'2017-05-02 10:12:07'),(932,1,124001,'ASD',0,'2017-05-02 10:12:07'),(933,1,125001,'ASD',0,'2017-05-02 10:12:07'),(934,1,126001,'ASD',0,'2017-05-02 10:12:07'),(935,1,127001,'ASD',0,'2017-05-02 10:12:07'),(936,1,128001,'ASD',0,'2017-05-02 10:12:07'),(937,1,129001,'ASD',0,'2017-05-02 10:12:07'),(938,1,130001,'ASD',0,'2017-05-02 10:12:07'),(939,1,131001,'ASD',0,'2017-05-02 10:12:07'),(940,1,132001,'ASD',0,'2017-05-02 10:12:07'),(941,1,133001,'ASD',0,'2017-05-02 10:12:07'),(942,1,134001,'ASD',0,'2017-05-02 10:12:07'),(943,1,135001,'ASD',0,'2017-05-02 10:12:07'),(944,1,136001,'ASD',0,'2017-05-02 10:12:07'),(945,1,137001,'ASD',0,'2017-05-02 10:12:07'),(946,1,138001,'ASD',0,'2017-05-02 10:12:07'),(947,1,139001,'ASD',0,'2017-05-02 10:12:07'),(948,1,140001,'ASD',0,'2017-05-02 10:12:07'),(949,1,141001,'ASD',0,'2017-05-02 10:12:07'),(950,1,142001,'ASD',0,'2017-05-02 10:12:07'),(951,1,143001,'ASD',0,'2017-05-02 10:12:07'),(952,1,144001,'ASD',0,'2017-05-02 10:12:07'),(953,1,145001,'ASD',0,'2017-05-02 10:12:07'),(954,1,146001,'ASD',0,'2017-05-02 10:12:07'),(955,1,147001,'ASD',0,'2017-05-02 10:12:07'),(956,1,148001,'ASD',0,'2017-05-02 10:12:07'),(957,1,149001,'ASD',0,'2017-05-02 10:12:07'),(958,1,150001,'ASD',0,'2017-05-02 10:12:07'),(959,1,151001,'ASD',0,'2017-05-02 10:12:07'),(960,1,152001,'ASD',0,'2017-05-02 10:12:07'),(961,1,153001,'ASD',0,'2017-05-02 10:12:07'),(962,1,154001,'ASD',0,'2017-05-02 10:12:07'),(963,1,155001,'ASD',0,'2017-05-02 10:12:07'),(964,1,156001,'ASD',0,'2017-05-02 10:12:07'),(965,1,157001,'ASD',0,'2017-05-02 10:12:07'),(966,1,158001,'ASD',0,'2017-05-02 10:12:07'),(967,1,159001,'ASD',0,'2017-05-02 10:12:07'),(968,1,160001,'ASD',0,'2017-05-02 10:12:07'),(969,1,161001,'ASD',0,'2017-05-02 10:12:07'),(970,1,162001,'ASD',0,'2017-05-02 10:12:07'),(971,1,163001,'ASD',0,'2017-05-02 10:12:07'),(972,1,164001,'ASD',0,'2017-05-02 10:12:07'),(973,1,165001,'ASD',0,'2017-05-02 10:12:07'),(974,1,166001,'ASD',0,'2017-05-02 10:12:07'),(975,1,167001,'ASD',0,'2017-05-02 10:12:07'),(976,1,168001,'ASD',0,'2017-05-02 10:12:07'),(977,1,169001,'ASD',0,'2017-05-02 10:12:07'),(978,1,170001,'ASD',0,'2017-05-02 10:12:07'),(979,1,171001,'ASD',0,'2017-05-02 10:12:07'),(980,1,172001,'ASD',0,'2017-05-02 10:12:07'),(981,1,173001,'ASD',0,'2017-05-02 10:12:07'),(982,1,174001,'ASD',0,'2017-05-02 10:12:07'),(983,1,175001,'ASD',0,'2017-05-02 10:12:07'),(984,1,176001,'ASD',0,'2017-05-02 10:12:07'),(985,1,177001,'ASD',0,'2017-05-02 10:12:07'),(986,1,178001,'ASD',0,'2017-05-02 10:12:07'),(987,1,179001,'ASD',0,'2017-05-02 10:12:07'),(988,1,180001,'ASD',0,'2017-05-02 10:12:07'),(989,1,181001,'ASD',0,'2017-05-02 10:12:07'),(990,1,182001,'ASD',0,'2017-05-02 10:12:07'),(991,1,183001,'ASD',0,'2017-05-02 10:12:07'),(992,1,184001,'ASD',0,'2017-05-02 10:12:07'),(993,1,185001,'ASD',0,'2017-05-02 10:12:07'),(994,1,186001,'ASD',0,'2017-05-02 10:12:07'),(995,1,187001,'ASD',0,'2017-05-02 10:12:07'),(996,1,188001,'ASD',0,'2017-05-02 10:12:07'),(997,1,189001,'ASD',0,'2017-05-02 10:12:07'),(998,1,190001,'ASD',0,'2017-05-02 10:12:07'),(999,1,191001,'ASD',0,'2017-05-02 10:12:07'),(1000,1,192001,'ASD',0,'2017-05-02 10:12:07'),(1001,1,193001,'ASD',0,'2017-05-02 10:12:07'),(1002,1,194001,'ASD',0,'2017-05-02 10:12:07'),(1003,1,195001,'ASD',0,'2017-05-02 10:12:07'),(1004,1,196001,'ASD',0,'2017-05-02 10:12:07'),(1005,1,197001,'ASD',0,'2017-05-02 10:12:07'),(1006,1,198001,'ASD',0,'2017-05-02 10:12:07'),(1007,1,199001,'ASD',0,'2017-05-02 10:12:07'),(1008,1,200001,'ASD',0,'2017-05-02 10:12:07'),(1009,1,201001,'ASD',0,'2017-05-02 10:12:07'),(1010,1,202001,'ASD',0,'2017-05-02 10:12:07'),(1011,1,203001,'ASD',0,'2017-05-02 10:12:07'),(1012,1,204001,'ASD',0,'2017-05-02 10:12:07'),(1013,1,205001,'ASD',0,'2017-05-02 10:12:07'),(1014,1,206001,'ASD',0,'2017-05-02 10:12:07'),(1015,1,207001,'ASD',0,'2017-05-02 10:12:07'),(1016,1,208001,'ASD',0,'2017-05-02 10:12:07'),(1017,1,209001,'ASD',0,'2017-05-02 10:12:07'),(1018,1,210001,'ASD',0,'2017-05-02 10:12:07'),(1019,1,211001,'ASD',0,'2017-05-02 10:12:07'),(1020,1,212001,'ASD',0,'2017-05-02 10:12:07'),(1021,1,213001,'ASD',0,'2017-05-02 10:12:07'),(1022,1,214001,'ASD',0,'2017-05-02 10:12:07'),(1023,1,215001,'ASD',0,'2017-05-02 10:12:07'),(1024,1,216001,'ASD',0,'2017-05-02 10:12:07'),(1025,1,217001,'ASD',0,'2017-05-02 10:12:07'),(1026,1,218001,'ASD',0,'2017-05-02 10:12:07'),(1027,1,219001,'ASD',0,'2017-05-02 10:12:07'),(1028,1,220001,'ASD',0,'2017-05-02 10:12:07'),(1029,1,221001,'ASD',0,'2017-05-02 10:12:07'),(1030,1,222001,'ASD',0,'2017-05-02 10:12:07'),(1031,2,123001,'PIO',0,'2017-05-02 10:12:07'),(1032,2,124001,'PIO',0,'2017-05-02 10:12:07'),(1033,2,125001,'PIO',0,'2017-05-02 10:12:07'),(1034,2,126001,'PIO',0,'2017-05-02 10:12:07'),(1035,2,127001,'PIO',0,'2017-05-02 10:12:07'),(1036,2,128001,'PIO',0,'2017-05-02 10:12:07'),(1037,2,129001,'PIO',0,'2017-05-02 10:12:07'),(1038,2,130001,'PIO',0,'2017-05-02 10:12:07'),(1039,2,131001,'PIO',0,'2017-05-02 10:12:07'),(1040,2,132001,'PIO',0,'2017-05-02 10:12:07'),(1041,2,133001,'PIO',0,'2017-05-02 10:12:07'),(1042,2,134001,'PIO',0,'2017-05-02 10:12:07'),(1043,2,135001,'PIO',0,'2017-05-02 10:12:07'),(1044,2,136001,'PIO',0,'2017-05-02 10:12:07'),(1045,2,137001,'PIO',0,'2017-05-02 10:12:07'),(1046,2,138001,'PIO',0,'2017-05-02 10:12:07'),(1047,2,139001,'PIO',0,'2017-05-02 10:12:07'),(1048,2,140001,'PIO',0,'2017-05-02 10:12:07'),(1049,2,141001,'PIO',0,'2017-05-02 10:12:07'),(1050,2,142001,'PIO',0,'2017-05-02 10:12:07'),(1051,2,143001,'PIO',0,'2017-05-02 10:12:07'),(1052,2,144001,'PIO',0,'2017-05-02 10:12:07'),(1053,2,145001,'PIO',0,'2017-05-02 10:12:07'),(1054,2,146001,'PIO',0,'2017-05-02 10:12:07'),(1055,2,147001,'PIO',0,'2017-05-02 10:12:07'),(1056,2,148001,'PIO',0,'2017-05-02 10:12:07'),(1057,2,149001,'PIO',0,'2017-05-02 10:12:07'),(1058,2,150001,'PIO',0,'2017-05-02 10:12:07'),(1059,2,151001,'PIO',0,'2017-05-02 10:12:07'),(1060,2,152001,'PIO',0,'2017-05-02 10:12:07'),(1061,2,153001,'PIO',0,'2017-05-02 10:12:07'),(1062,2,154001,'PIO',0,'2017-05-02 10:12:07'),(1063,2,155001,'PIO',0,'2017-05-02 10:12:07'),(1064,2,156001,'PIO',0,'2017-05-02 10:12:07'),(1065,2,157001,'PIO',0,'2017-05-02 10:12:07'),(1066,2,158001,'PIO',0,'2017-05-02 10:12:07'),(1067,2,159001,'PIO',0,'2017-05-02 10:12:07'),(1068,2,160001,'PIO',0,'2017-05-02 10:12:07'),(1069,2,161001,'PIO',0,'2017-05-02 10:12:07'),(1070,2,162001,'PIO',0,'2017-05-02 10:12:07'),(1071,2,163001,'PIO',0,'2017-05-02 10:12:07'),(1072,2,164001,'PIO',0,'2017-05-02 10:12:07'),(1073,2,165001,'PIO',0,'2017-05-02 10:12:07'),(1074,2,166001,'PIO',0,'2017-05-02 10:12:07'),(1075,2,167001,'PIO',0,'2017-05-02 10:12:07'),(1076,2,168001,'PIO',0,'2017-05-02 10:12:07'),(1077,2,169001,'PIO',0,'2017-05-02 10:12:08'),(1078,2,170001,'PIO',0,'2017-05-02 10:12:08'),(1079,2,171001,'PIO',0,'2017-05-02 10:12:08'),(1080,2,172001,'PIO',0,'2017-05-02 10:12:08'),(1081,2,173001,'PIO',0,'2017-05-02 10:12:08'),(1082,2,174001,'PIO',0,'2017-05-02 10:12:08'),(1083,2,175001,'PIO',0,'2017-05-02 10:12:08'),(1084,2,176001,'PIO',0,'2017-05-02 10:12:08'),(1085,2,177001,'PIO',0,'2017-05-02 10:12:08'),(1086,2,178001,'PIO',0,'2017-05-02 10:12:08'),(1087,2,179001,'PIO',0,'2017-05-02 10:12:08'),(1088,2,180001,'PIO',0,'2017-05-02 10:12:08'),(1089,2,181001,'PIO',0,'2017-05-02 10:12:08'),(1090,2,182001,'PIO',0,'2017-05-02 10:12:08'),(1091,2,183001,'PIO',0,'2017-05-02 10:12:08'),(1092,2,184001,'PIO',0,'2017-05-02 10:12:08'),(1093,2,185001,'PIO',0,'2017-05-02 10:12:08'),(1094,2,186001,'PIO',0,'2017-05-02 10:12:08'),(1095,2,187001,'PIO',0,'2017-05-02 10:12:08'),(1096,2,188001,'PIO',0,'2017-05-02 10:12:08'),(1097,2,189001,'PIO',0,'2017-05-02 10:12:08'),(1098,2,190001,'PIO',0,'2017-05-02 10:12:08'),(1099,2,191001,'PIO',0,'2017-05-02 10:12:08'),(1100,2,192001,'PIO',0,'2017-05-02 10:12:08'),(1101,2,193001,'PIO',0,'2017-05-02 10:12:08'),(1102,2,194001,'PIO',0,'2017-05-02 10:12:08'),(1103,2,195001,'PIO',0,'2017-05-02 10:12:08'),(1104,2,196001,'PIO',0,'2017-05-02 10:12:08'),(1105,2,197001,'PIO',0,'2017-05-02 10:12:08'),(1106,2,198001,'PIO',0,'2017-05-02 10:12:08'),(1107,2,199001,'PIO',0,'2017-05-02 10:12:08'),(1108,2,200001,'PIO',0,'2017-05-02 10:12:08'),(1109,2,201001,'PIO',0,'2017-05-02 10:12:08'),(1110,2,202001,'PIO',0,'2017-05-02 10:12:08'),(1111,2,203001,'PIO',0,'2017-05-02 10:12:08'),(1112,2,204001,'PIO',0,'2017-05-02 10:12:08'),(1113,2,205001,'PIO',0,'2017-05-02 10:12:08'),(1114,2,206001,'PIO',0,'2017-05-02 10:12:08'),(1115,2,207001,'PIO',0,'2017-05-02 10:12:08'),(1116,2,208001,'PIO',0,'2017-05-02 10:12:08'),(1117,2,209001,'PIO',0,'2017-05-02 10:12:08'),(1118,2,210001,'PIO',0,'2017-05-02 10:12:08'),(1119,2,211001,'PIO',0,'2017-05-02 10:12:08'),(1120,2,212001,'PIO',0,'2017-05-02 10:12:08'),(1121,2,213001,'PIO',0,'2017-05-02 10:12:08'),(1122,2,214001,'PIO',0,'2017-05-02 10:12:08'),(1123,2,215001,'PIO',0,'2017-05-02 10:12:08'),(1124,2,216001,'PIO',0,'2017-05-02 10:12:08'),(1125,2,217001,'PIO',0,'2017-05-02 10:12:08'),(1126,2,218001,'PIO',0,'2017-05-02 10:12:08'),(1127,2,219001,'PIO',0,'2017-05-02 10:12:08'),(1128,2,220001,'PIO',0,'2017-05-02 10:12:08'),(1129,2,221001,'PIO',0,'2017-05-02 10:12:08'),(1130,2,222001,'PIO',0,'2017-05-02 10:12:08');
/*!40000 ALTER TABLE `inventario_interno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada_laboral`
--

DROP TABLE IF EXISTS `jornada_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornada_laboral` (
  `jornada_laboral_id` int(11) unsigned NOT NULL,
  `jornada_laboral_id_horario_jornada` int(11) unsigned NOT NULL,
  `jornada_laboral_nombre` varchar(60) NOT NULL,
  `jornada_laboral_lunes` tinyint(1) NOT NULL DEFAULT '0',
  `jornada_laboral_martes` tinyint(1) NOT NULL DEFAULT '0',
  `jornada_laboral_miercoles` tinyint(1) NOT NULL DEFAULT '0',
  `jornada_laboral_jueves` tinyint(1) NOT NULL DEFAULT '0',
  `jornada_laboral_viernes` tinyint(1) NOT NULL DEFAULT '0',
  `jornada_laboral_sabado` tinyint(1) NOT NULL DEFAULT '0',
  `jornada_laboral_domingo` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`jornada_laboral_id`),
  KEY `fk_jornada_laboral_id_horario_idx` (`jornada_laboral_id_horario_jornada`),
  CONSTRAINT `fk_jornada_laboral_id_horario` FOREIGN KEY (`jornada_laboral_id_horario_jornada`) REFERENCES `horario_jornada` (`horario_jornada_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada_laboral`
--

LOCK TABLES `jornada_laboral` WRITE;
/*!40000 ALTER TABLE `jornada_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornada_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada_trabajador`
--

DROP TABLE IF EXISTS `jornada_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornada_trabajador` (
  `jornada_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `jornada_trabajador_id_trabajador` int(11) unsigned NOT NULL,
  `jornada_trabajador_id_jornada_laboral` int(11) unsigned NOT NULL,
  `jornada_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`jornada_trabajador_id`),
  KEY `fk_jornada_trabajador_id_trb_idx` (`jornada_trabajador_id_trabajador`),
  KEY `fk_jornada_trabajado_id_jornada_idx` (`jornada_trabajador_id_jornada_laboral`),
  CONSTRAINT `fk_jornada_trabajado_id_jornada` FOREIGN KEY (`jornada_trabajador_id_jornada_laboral`) REFERENCES `jornada_laboral` (`jornada_laboral_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jornada_trabajador_id_trb` FOREIGN KEY (`jornada_trabajador_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada_trabajador`
--

LOCK TABLES `jornada_trabajador` WRITE;
/*!40000 ALTER TABLE `jornada_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornada_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `licencia_medica`
--

DROP TABLE IF EXISTS `licencia_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `licencia_medica` (
  `licencia_medica_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `licencia_medica_id_trabajador` int(11) unsigned NOT NULL,
  `licencia_medica_numero` varchar(45) DEFAULT NULL,
  `licencia_medica_fecha_recepcion` date NOT NULL,
  `licencia_medica_fecha_emision` date NOT NULL,
  `licencia_medica_fecha_desde_reposo` date NOT NULL,
  `licencia_medica_fecha_hasta_reposo` date NOT NULL,
  `licencia_medica_diagnostico_licencia` varchar(100) DEFAULT NULL,
  `licencia_medica_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`licencia_medica_id`),
  KEY `fk_licencia_medica_id_trabajador_idx` (`licencia_medica_id_trabajador`),
  CONSTRAINT `fk_licencia_medica_id_trabajador` FOREIGN KEY (`licencia_medica_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licencia_medica`
--

LOCK TABLES `licencia_medica` WRITE;
/*!40000 ALTER TABLE `licencia_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `licencia_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liquidacion_empresa`
--

DROP TABLE IF EXISTS `liquidacion_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liquidacion_empresa` (
  `liquidacion_empresa_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `liquidacion_empresa_id_empresa` int(11) unsigned NOT NULL,
  `liquidacion_empresa_fecha_liquidacion` date NOT NULL,
  `liquidacion_empresa_fecha_pago` date NOT NULL,
  `liquidacion_empresa_total_abonos` int(11) unsigned NOT NULL,
  `liquidacion_empresa_total_cargos` int(11) unsigned NOT NULL,
  `liquidacion_empresa_saldo` int(11) unsigned NOT NULL,
  `liquidacion_empresa_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`liquidacion_empresa_id`),
  KEY `fk_liquidacion_empresa_id_empresa_idx` (`liquidacion_empresa_id_empresa`),
  CONSTRAINT `fk_liquidacion_empresa_id_empresa` FOREIGN KEY (`liquidacion_empresa_id_empresa`) REFERENCES `empresa` (`empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liquidacion_empresa`
--

LOCK TABLES `liquidacion_empresa` WRITE;
/*!40000 ALTER TABLE `liquidacion_empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `liquidacion_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liquidacion_sueldo`
--

DROP TABLE IF EXISTS `liquidacion_sueldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liquidacion_sueldo` (
  `liquidacion_sueldo_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `liquidacion_sueldo_id_trabajador` int(11) unsigned NOT NULL,
  `liquidacion_sueldo_id_empresa` int(11) unsigned NOT NULL,
  `liquidacion_sueldo_id_tipo_contrato` int(11) unsigned NOT NULL,
  `liquidacion_sueldo_id_terminal` int(11) unsigned NOT NULL,
  `liquidacion_sueldo_nombre_terminal` varchar(100) NOT NULL,
  `liquidacion_sueldo_fecha` date NOT NULL,
  `liquidacion_sueldo_monto_bruto` int(11) NOT NULL,
  `liquidacion_sueldo_monto_sueldo_base` int(11) NOT NULL,
  `liquidacion_sueldo_horas_extras` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_imponible` int(11) NOT NULL,
  `liquidacion_sueldo_monto_imponible_ajustado` int(11) unsigned NOT NULL,
  `liquidacion_sueldo_monto_cinco_total` int(11) NOT NULL,
  `liquidacion_sueldo_monto_cinco_filtrado` int(11) NOT NULL,
  `liquidacion_sueldo_monto_ahorro` int(11) DEFAULT '0',
  `liquidacion_sueldo_monto_abono` int(11) NOT NULL,
  `liquidacion_sueldo_monto_feriado` int(11) DEFAULT '0',
  `liquidacion_sueldo_monto_no_imponible` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_numero_carga` int(11) DEFAULT '0',
  `liquidacion_sueldo_monto_carga` int(11) DEFAULT '0',
  `liquidacion_sueldo_monto_retroactivo` int(11) DEFAULT '0',
  `liquidacion_sueldo_fecha_contrato` date NOT NULL,
  `liquidacion_sueldo_fecha_finiquito` date DEFAULT NULL,
  `liquidacion_sueldo_fecha_desde_feriado` date DEFAULT NULL,
  `liquidacion_sueldo_fecha_hasta_feriado` date DEFAULT NULL,
  `liquidacion_sueldo_dias_feriado` int(11) DEFAULT NULL,
  `liquidacion_sueldo_dias_licencias` int(11) DEFAULT NULL,
  `liquidacion_sueldo_dias_descanso` int(11) DEFAULT NULL,
  `liquidacion_sueldo_dias_guias` int(11) DEFAULT NULL,
  `liquidacion_sueldo_dias_trabajados` int(11) DEFAULT NULL,
  `liquidacion_sueldo_nombre_prevision` varchar(100) DEFAULT NULL,
  `liquidacion_sueldo_porcentaje_prevision` varchar(45) DEFAULT NULL,
  `liquidacion_sueldo_monto_prevision` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_apv` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_cesantia_trabajador` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_cesantia_empresa` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_cesantia_total` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_sis` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_inp` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_caja_compensacion` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_nombre_isapre` varchar(45) DEFAULT ' ',
  `liquidacion_sueldo_monto_isapre` int(11) NOT NULL,
  `liquidacion_sueldo_monto_sindicato` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_saldo_anterior` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_retencion_judicial` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_credito_salud` int(11) NOT NULL,
  `liquidacion_sueldo_monto_seguro` int(11) NOT NULL,
  `liquidacion_sueldo_monto_total_descuentos` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_alcance_liquido` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_anticipo` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_monto_saldo` int(11) NOT NULL DEFAULT '0',
  `liquidacion_sueldo_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `liquidacion_sueldo_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`liquidacion_sueldo_id`),
  KEY `fk_liquidacion_sueldo_idx` (`liquidacion_sueldo_id_trabajador`),
  KEY `fk_liquidacion_sueldo_id_empresa_idx` (`liquidacion_sueldo_id_empresa`),
  KEY `fk_liquidacion_sueldo_id_tipocontrato_idx` (`liquidacion_sueldo_id_tipo_contrato`),
  KEY `fk_liquidacion_sueldo_id_terminal_idx` (`liquidacion_sueldo_id_terminal`),
  CONSTRAINT `fk_liquidacion_sueldo_id_empresa` FOREIGN KEY (`liquidacion_sueldo_id_empresa`) REFERENCES `empresa` (`empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_liquidacion_sueldo_id_terminal` FOREIGN KEY (`liquidacion_sueldo_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_liquidacion_sueldo_id_tipocontrato` FOREIGN KEY (`liquidacion_sueldo_id_tipo_contrato`) REFERENCES `tipo_contrato` (`tipo_contrato_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_liquidacion_sueldo_id_trabajador` FOREIGN KEY (`liquidacion_sueldo_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liquidacion_sueldo`
--

LOCK TABLES `liquidacion_sueldo` WRITE;
/*!40000 ALTER TABLE `liquidacion_sueldo` DISABLE KEYS */;
/*!40000 ALTER TABLE `liquidacion_sueldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `log_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `log_id_usuario` int(11) unsigned NOT NULL,
  `log_id_privilegio` int(11) unsigned NOT NULL,
  `log_tipo_accion` varchar(45) NOT NULL,
  `log_descripcion` varchar(255) NOT NULL,
  `log_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_id`),
  KEY `fk_log_id_usuario_idx` (`log_id_usuario`),
  KEY `fk_log_id_privilegio_idx` (`log_id_privilegio`),
  CONSTRAINT `fk_log_id_privilegio` FOREIGN KEY (`log_id_privilegio`) REFERENCES `privilegio` (`privilegio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_id_usuario` FOREIGN KEY (`log_id_usuario`) REFERENCES `usuario` (`usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca_bus`
--

DROP TABLE IF EXISTS `marca_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca_bus` (
  `marca_bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `marca_bus_nombre` varchar(45) NOT NULL,
  `marca_bus_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`marca_bus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_bus`
--

LOCK TABLES `marca_bus` WRITE;
/*!40000 ALTER TABLE `marca_bus` DISABLE KEYS */;
INSERT INTO `marca_bus` VALUES (6,'Mercedes Benz','2017-04-20 15:58:16');
/*!40000 ALTER TABLE `marca_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pago`
--

DROP TABLE IF EXISTS `metodo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metodo_pago` (
  `metodo_pago_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `metodo_pago_nombre` varchar(100) NOT NULL,
  `metodo_pago_valor` int(11) NOT NULL,
  `metodo_pago_activo` tinyint(1) NOT NULL DEFAULT '0',
  `metodo_pago_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`metodo_pago_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pago`
--

LOCK TABLES `metodo_pago` WRITE;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo_marca_bus`
--

DROP TABLE IF EXISTS `modelo_marca_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modelo_marca_bus` (
  `modelo_marca_bus_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `modelo_marca_bus_id_marca` int(11) unsigned NOT NULL,
  `modelo_marca_bus_nombre` varchar(45) NOT NULL,
  `modelo_marca_bus_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`modelo_marca_bus_id`),
  KEY `fk_modelo_marca_bus_id_marca_bus_idx` (`modelo_marca_bus_id_marca`),
  CONSTRAINT `fk_modelo_marca_bus_id_marca_bus` FOREIGN KEY (`modelo_marca_bus_id_marca`) REFERENCES `marca_bus` (`marca_bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo_marca_bus`
--

LOCK TABLES `modelo_marca_bus` WRITE;
/*!40000 ALTER TABLE `modelo_marca_bus` DISABLE KEYS */;
INSERT INTO `modelo_marca_bus` VALUES (40,6,'Lo 916','2017-04-20 16:00:48'),(41,6,'Lo 915','2017-04-20 16:01:07');
/*!40000 ALTER TABLE `modelo_marca_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moneda_pactada_institucion_salud`
--

DROP TABLE IF EXISTS `moneda_pactada_institucion_salud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneda_pactada_institucion_salud` (
  `moneda_pactada_institucion_salud_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `moneda_pactada_institucion_salud_nombre` varchar(45) NOT NULL,
  `moneda_pactada_institucion_salud_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`moneda_pactada_institucion_salud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda_pactada_institucion_salud`
--

LOCK TABLES `moneda_pactada_institucion_salud` WRITE;
/*!40000 ALTER TABLE `moneda_pactada_institucion_salud` DISABLE KEYS */;
/*!40000 ALTER TABLE `moneda_pactada_institucion_salud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mutual`
--

DROP TABLE IF EXISTS `mutual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mutual` (
  `mutual_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mutual_nombre` varchar(255) NOT NULL,
  `mutual_comision` float(5,3) DEFAULT NULL,
  `mutual_porcentaje_descuento` float(5,3) NOT NULL,
  `mutual_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mutual_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mutual`
--

LOCK TABLES `mutual` WRITE;
/*!40000 ALTER TABLE `mutual` DISABLE KEYS */;
INSERT INTO `mutual` VALUES (1,'Sin Mutual',0.000,0.000,'2017-04-20 13:00:59');
/*!40000 ALTER TABLE `mutual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `numeral_surtidor`
--

DROP TABLE IF EXISTS `numeral_surtidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `numeral_surtidor` (
  `numeral_surtidor_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `numeral_surtidor_id_surtidor` int(11) unsigned NOT NULL,
  `numeral_surtidor_fecha_numeral` date NOT NULL,
  `numeral_surtidor_numeral_inicial` int(11) unsigned NOT NULL,
  `numeral_surtidor_numeral_final` int(11) unsigned NOT NULL,
  `numeral_surtidor_indicador_muestra` int(11) unsigned DEFAULT '0',
  `numeral_surtidor_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`numeral_surtidor_id`),
  KEY `fk_numeral_surtidor_id_surtidor_idx` (`numeral_surtidor_id_surtidor`),
  CONSTRAINT `fk_numeral_surtidor_id_surtidor` FOREIGN KEY (`numeral_surtidor_id_surtidor`) REFERENCES `surtidor` (`surtidor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `numeral_surtidor`
--

LOCK TABLES `numeral_surtidor` WRITE;
/*!40000 ALTER TABLE `numeral_surtidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `numeral_surtidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observacion_trabajador`
--

DROP TABLE IF EXISTS `observacion_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observacion_trabajador` (
  `observacion_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `observacion_trabajador_id_trabajador` int(11) unsigned NOT NULL,
  `observacion_trabajador_id_tipo_observacion` int(11) unsigned NOT NULL,
  `observacion_trabajador_fecha` date NOT NULL,
  `observacion_trabajador_descripcion` text NOT NULL,
  `observacion_trabajador_detalle` varchar(45) NOT NULL,
  `observacion_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`observacion_trabajador_id`),
  KEY `fk_observacion_trabajador_idtrabajador_idx` (`observacion_trabajador_id_trabajador`),
  KEY `fk_observacion_tipo_observacion_idx` (`observacion_trabajador_id_tipo_observacion`),
  CONSTRAINT `fk_observacion_tipo_observacion` FOREIGN KEY (`observacion_trabajador_id_tipo_observacion`) REFERENCES `tipo_observacion` (`tipo_observacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_observacion_trabajador_idtrabajador` FOREIGN KEY (`observacion_trabajador_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observacion_trabajador`
--

LOCK TABLES `observacion_trabajador` WRITE;
/*!40000 ALTER TABLE `observacion_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `observacion_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operador_transporte`
--

DROP TABLE IF EXISTS `operador_transporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operador_transporte` (
  `operador_transporte_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operador_transporte_rut` varchar(9) NOT NULL,
  `operador_transporte_nombre` varchar(45) NOT NULL,
  `operador_transporte_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`operador_transporte_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operador_transporte`
--

LOCK TABLES `operador_transporte` WRITE;
/*!40000 ALTER TABLE `operador_transporte` DISABLE KEYS */;
INSERT INTO `operador_transporte` VALUES (1,'165015096','Sol y Mar S.A. ','2017-04-20 16:54:12');
/*!40000 ALTER TABLE `operador_transporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro_cesantia`
--

DROP TABLE IF EXISTS `parametro_cesantia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro_cesantia` (
  `parametro_cesantia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parametro_cesantia_empleador` float(5,3) unsigned NOT NULL,
  `parametro_cesantia_trabajador` float(5,3) unsigned NOT NULL,
  `parametro_cesantia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `parametro_cesantia_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`parametro_cesantia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro_cesantia`
--

LOCK TABLES `parametro_cesantia` WRITE;
/*!40000 ALTER TABLE `parametro_cesantia` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametro_cesantia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro_interpolacion`
--

DROP TABLE IF EXISTS `parametro_interpolacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro_interpolacion` (
  `parametro_interpolacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parametro_interpolacion_nombre` varchar(100) NOT NULL,
  `parametro_interpolacion_valor` float(5,5) unsigned NOT NULL,
  `parametro_interpolacion_unidad` varchar(45) NOT NULL,
  PRIMARY KEY (`parametro_interpolacion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro_interpolacion`
--

LOCK TABLES `parametro_interpolacion` WRITE;
/*!40000 ALTER TABLE `parametro_interpolacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametro_interpolacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parentesco_carga`
--

DROP TABLE IF EXISTS `parentesco_carga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parentesco_carga` (
  `parentesco_carga_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parentesco_carga_nombre` varchar(45) NOT NULL,
  `parentesco_carga_fecha_ingreso` datetime NOT NULL,
  PRIMARY KEY (`parentesco_carga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parentesco_carga`
--

LOCK TABLES `parentesco_carga` WRITE;
/*!40000 ALTER TABLE `parentesco_carga` DISABLE KEYS */;
/*!40000 ALTER TABLE `parentesco_carga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo_frecuencia`
--

DROP TABLE IF EXISTS `periodo_frecuencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodo_frecuencia` (
  `periodo_frecuencia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `periodo_frecuencia_desde` time NOT NULL,
  `periodo_frecuencia_hasta` time NOT NULL,
  `periodo_frecuencia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`periodo_frecuencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo_frecuencia`
--

LOCK TABLES `periodo_frecuencia` WRITE;
/*!40000 ALTER TABLE `periodo_frecuencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodo_frecuencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio_combustible`
--

DROP TABLE IF EXISTS `precio_combustible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precio_combustible` (
  `precio_combustible_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `precio_combustible_id_tipo_combustible` int(11) unsigned NOT NULL,
  `precio_combustible_valor` int(11) unsigned NOT NULL,
  `precio_combustible_fecha_precio_combustible` date NOT NULL,
  `precio_combustible_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`precio_combustible_id`),
  KEY `fk_precio_combustible_id_tipo_idx` (`precio_combustible_id_tipo_combustible`),
  CONSTRAINT `fk_precio_combustible_id_tipo` FOREIGN KEY (`precio_combustible_id_tipo_combustible`) REFERENCES `tipo_combustible` (`tipo_combustible_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio_combustible`
--

LOCK TABLES `precio_combustible` WRITE;
/*!40000 ALTER TABLE `precio_combustible` DISABLE KEYS */;
/*!40000 ALTER TABLE `precio_combustible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilegio`
--

DROP TABLE IF EXISTS `privilegio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilegio` (
  `privilegio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `privilegio_nombre` varchar(255) NOT NULL,
  `privilegio_descripcion` varchar(255) NOT NULL,
  `privilegio_menu_link` varchar(100) DEFAULT NULL,
  `privilegio_tabla` varchar(100) DEFAULT NULL,
  `privilegio_icon` varchar(45) DEFAULT NULL,
  `privilegio_numero_orden` int(3) NOT NULL DEFAULT '0',
  `privilegio_color` varchar(10) DEFAULT NULL,
  `privilegio_tipo_menu` int(3) unsigned NOT NULL,
  `privilegio_root_menu` int(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`privilegio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilegio`
--

LOCK TABLES `privilegio` WRITE;
/*!40000 ALTER TABLE `privilegio` DISABLE KEYS */;
INSERT INTO `privilegio` VALUES (1,'Mantenedor de Entidades','Realizar operaciones de tipo CRUD',' ',NULL,'fa fa-bars',0,'#00a65a',1,NULL),(2,'Buses','Mantenedor de Buses','/app/bus/index','bus',NULL,1,NULL,2,1),(3,'Empresas','Mantenedor de Empresas','/app/empresa/index','empresa',NULL,2,NULL,2,1),(4,'Flotas','Mantenedor de Flotas','/app/flota/index','flota',NULL,4,NULL,2,1),(5,'Terminal','Mantenedor de Terminales','/app/terminal/index','terminal',NULL,5,NULL,2,1),(6,'Servicios','Mantenedor de Servicios','/app/servicio/index','servicio',NULL,6,NULL,2,1),(7,'Unidades de Negocios','Mantenedor de Unidades de Negocios','/app/unidadNegocio/index','unidadNegocio',NULL,7,NULL,2,1),(8,'Marcas de Buses','Mantenedor de Marcas de Buses','/app/marcaBus/index','marcaBus',NULL,8,NULL,2,1),(9,'Modelos de Buses','Mantenedor de Modelos de Buses','/app/modeloMarcaBus/index','modeloMarcaBus',NULL,9,NULL,2,1),(10,'Estado de Buses','Mantenedor de Estados de Buses','/app/estadoBus/index','estadoBus',NULL,10,NULL,2,1),(11,'Bancos','Mantenedor de Instituciones Bancarias','/app/banco/index','banco',NULL,11,NULL,2,1),(12,'Cajas de Recaudacion','Mantenedor de Cajas de Recaudación','/app/cajaTerminal/index','cajaTerminal',NULL,12,NULL,2,1),(13,'Proceso Recaudacion','Mantenedor de Procesos de Recaudacion','/app/procesoRecaudacion/index','procesoRecaudacion',NULL,13,NULL,2,1),(14,'Asigmación Familiar','Mantenedor de Valores de Asignación Familiar','/app/asignacionFamiliar/index','asignacionFamiliar',NULL,14,NULL,2,1),(15,'Boletos','Mantenedor de Boletos','/app/boleto/index','boleto',NULL,15,NULL,2,1),(16,'Cajas de Compensación','Mantenedor de Cajas de Compensación','/app/cajaCompensacion/index','cajaCompensacion',NULL,16,NULL,2,1),(17,'Causal Finiquito','Mantenedor de Causales de Finiquitos ','/app/causalFiniquito/index','causalFiniquito',NULL,17,NULL,2,1),(18,'Centro de Costo','Mantenedor de Centros de Costos','/app/centroCosto/index','centroCosto',NULL,18,NULL,2,1),(19,'Ciudades','Mantenedor de Ciudades','/app/ciudad/index','ciudad',NULL,19,NULL,2,1),(20,'Comuna','Mantenedor de Comunas','/app/comuna/index','comuna',NULL,20,NULL,2,1),(21,'Contrato x Unidad','Mantenedor de Contratos Operativos entre la Autoridad Ministerial y la Unidad de Negocio','/app/contratoUnidad/index','contratoUnidad',NULL,21,NULL,2,1),(22,'Departamentos ','Mantenedor de Departamentos de administración','/app/departamento/index','departamento',NULL,22,NULL,2,1),(23,'Días Festivos','Mantenedor de Días Festivos según calendario oficial ','/app/diaFestivo/index','diaFestivo',NULL,23,NULL,2,1),(24,'Tipos de Efectivo','Mantenedor de Tipos de Efectivo','/app/efectivoCaja/index','efectivoCaja',NULL,24,NULL,2,1),(25,'Egresos','Mantenedor de Egresos de Guías','/app/egreso/index','egreso',NULL,25,NULL,2,1),(26,'Estado Guía ','Mantenedor de Estados de Guías ','/app/estadoGuia/index','estadoGuia',NULL,26,NULL,2,1),(27,'Metodos de Pagos','Mantenedor de Métodos de Pago de Remuneraciones / Liquidaciones','/app/formaPago/index','formaPago',NULL,27,NULL,2,1),(28,'AFP ','Mantenedor de Administradoras de Fondos de Pensiones','/app/institucionPrevision/index','institucionPrevion',NULL,28,NULL,2,1),(29,'ISAPRE','Mantenedor de Isapres','/app/institucionSalud/index','institucionSalud',NULL,29,NULL,2,1),(30,'APV','Mantenedor de Instituciones de Ahorro Previsional Voluntario','/app/institucionApv/index','institucionApv',NULL,30,NULL,2,1),(31,'Mutual','Mantenedor de Mutuales','/app/mutual/index','mutual',NULL,31,NULL,2,1),(32,'Operadores de Transportes','Mantenedor de Operadores de Unidades de Negocio','/app/operadorTransporte/index','operadorTransporte',NULL,32,NULL,2,1),(33,'Seguro de Cesantía','Mantenedor de Parámetro de Cesantía','/app/parametroCesantia/index','parametroCesantia',NULL,33,NULL,2,1),(34,'Parentesco Cargas','Mantenedor de Parentesco de Cargas','/app/parentescoCarga/index','parentescoCarga',NULL,34,NULL,2,1),(35,'Precio Combustible','Mantenedor de Precios de Combustible','/app/precioCombustible/index','precioCombustible',NULL,35,NULL,2,1),(36,'Regiones','Mantenedor de Regiones','/app/region/index','region',NULL,36,NULL,2,1),(37,'Representante Legal','Mantenedor de Representantes Legales','/app/representanteLegal','representanteLegal',NULL,37,NULL,2,1),(38,'Sindicatos','Mantenedor de Sindicatos','/app/sindicato/index','sindicato',NULL,38,NULL,2,1),(39,'Sueldo Base','Mantenedor de Valor Sueldo Base','/app/sueldoBase/index','sueldoBase',NULL,39,NULL,2,1),(40,'Surtidores','Mantenedor de Surtidores','/app/surtidor/index','surtidor',NULL,40,NULL,2,1),(41,'Tipo Carga Familiar','Mantenedores de Tipos de Cargas Familiares','/app/tipoCargaFamiliar/index','tipoCargaFamiliar',NULL,41,NULL,2,1),(42,'Tipos de Combustible','Mantenedor de Tipos de Combustible','/app/tipoCombustible/index','tipoCombustible',NULL,42,NULL,2,1),(43,'Tipos de Contrato','Mantenedor de Tipos de Contratos Laborales','/app/tipoContrato/index','tipoContrato',NULL,43,NULL,2,1),(44,'Tipo Cotización ','Mantenedor de Tipos de Cotización ','/app/tipoCotizacion/index','tipoCotizacion',NULL,44,NULL,2,1),(45,'Tipo Cuenta Banco ','Mantenedor de Tipos de Cuentas Bancarias ','/app/tipoCuentaBanco/index','tipoCuentaBanco',NULL,45,NULL,2,1),(46,'Tipos de Trabajadores','Mantenedor de Tipos de Trabajadores','/app/tipoTrabajador/index','tipoTrabajador',NULL,46,NULL,2,1),(47,'Trabajador','Mantenedor de Trabajadores','/app/trabajador/index','trabajador',NULL,47,NULL,2,1),(48,'Valor Minuto','Mantenedor del Valor Minutos ','/app/valorMinuto/index','valorMinuto',NULL,48,NULL,2,1),(49,'Valor Rollo x Unidad ','Mantenedor del Valor x Rollo de Boleto según Unidad de Negocio','/app/valorRolloUnidad/index','valorRolloUnidad',NULL,49,NULL,2,1),(50,'Valor UF','Mantenedor del Valor de la UF','/app/valorUf/index','valorUf',NULL,50,NULL,2,1),(51,'Valor UTM','Mantenedor del Valor de la UTM ','/app/valorUtm/index','valorUtm',NULL,51,NULL,2,1),(52,'Inspector','Tareas Inspector',' ',NULL,'fa fa-bars',52,'#00a65a',1,NULL),(53,'Registro de Guías','Creación de Guías y actualización de series de boletos','/app/guia/index','guia',NULL,53,NULL,2,52),(54,'Recaudación','Recaudación de Guías, Minutos y Ventas de Boletos',' ',NULL,'fa fa-bars',54,'#00a65a',1,NULL),(55,'Venta de Boleto','Venta de Boletos según inventario asignado a cada caja','/app/ventaBoleto/index','ventaBoleto',NULL,55,NULL,2,54),(56,'Recaudación de Guías','Recauda administración, combustible, minutos, etc.','/app/egresoGuia/index','egresoGuia',NULL,56,NULL,2,54),(57,'Inventario de Boletos','Administra la compra y asignación de boletos a cada caja del terminal ',' ',NULL,'fa fa-bars',57,'#00a65a',1,NULL),(58,'Compra de Boletos','Registra cada paquete en la base de datos','/app/compraBoleto/index','compraBoleto',NULL,58,NULL,2,57),(59,'Inventario Interno','Listado con el Inventario a la fecha actual','/app/inventarioInterno/index','inventarioInterno',NULL,59,NULL,2,57),(60,'Inventario x Caja','Listado con el Inventario por Caja','/app/inventarioCaja/index','inventarioCaja',NULL,60,NULL,2,57),(61,'Traspaso de Boletos a Cajas ','Asigna boletos a las cajas registradas','/app/traspaso-Caja/index',NULL,NULL,61,NULL,2,57),(62,'Ventas x Fechas ','Listado con Ventas según parámetro fecha','/app/ventaBoleto/index','ventaBoleto',NULL,62,NULL,2,57),(63,'Digitación','Módulo de digitación en central',' ',NULL,'fa fa-bars',63,'#00a65a',1,NULL),(64,'Registro de Guías','Digitación de Guías','/app/digitacion-Guia/index','guia',NULL,64,NULL,2,63);
/*!40000 ALTER TABLE `privilegio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proceso_recaudacion`
--

DROP TABLE IF EXISTS `proceso_recaudacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proceso_recaudacion` (
  `proceso_recaudacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `proceso_recaudacion_nombre` varchar(45) NOT NULL,
  `proceso_recaudacion_activo` tinyint(1) NOT NULL,
  `proceso_recaudacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`proceso_recaudacion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proceso_recaudacion`
--

LOCK TABLES `proceso_recaudacion` WRITE;
/*!40000 ALTER TABLE `proceso_recaudacion` DISABLE KEYS */;
INSERT INTO `proceso_recaudacion` VALUES (1,'General',1,'2017-04-20 12:32:36'),(2,'Albatros',1,'2017-04-20 12:57:20');
/*!40000 ALTER TABLE `proceso_recaudacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reconocimiento_deuda`
--

DROP TABLE IF EXISTS `reconocimiento_deuda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reconocimiento_deuda` (
  `reconocimiento_deuda_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `reconocimiento_deuda_id_trabajador` int(11) unsigned NOT NULL,
  `reconocimiento_deuda_id_empresa` int(11) unsigned NOT NULL,
  `reconocimiento_deuda_monto_reconocimiento` int(11) NOT NULL,
  `reconocimiento_deuda_descripcion_reconocimiento` varchar(255) DEFAULT NULL,
  `reconocimiento_deuda_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reconocimiento_deuda_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`reconocimiento_deuda_id`),
  KEY `fk_reconocimiento_id_trabajador_idx` (`reconocimiento_deuda_id_trabajador`),
  KEY `fk_reconocimiento_id_empresa_idx` (`reconocimiento_deuda_id_empresa`),
  CONSTRAINT `fk_reconocimiento_id_empresa` FOREIGN KEY (`reconocimiento_deuda_id_empresa`) REFERENCES `empresa` (`empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reconocimiento_id_trabajador` FOREIGN KEY (`reconocimiento_deuda_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reconocimiento_deuda`
--

LOCK TABLES `reconocimiento_deuda` WRITE;
/*!40000 ALTER TABLE `reconocimiento_deuda` DISABLE KEYS */;
/*!40000 ALTER TABLE `reconocimiento_deuda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `region_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `region_numero` int(11) NOT NULL,
  `region_nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,5,'Valparaíso');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_minuto`
--

DROP TABLE IF EXISTS `registro_minuto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro_minuto` (
  `registro_minuto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `registro_minuto_id_valor_minuto` int(11) unsigned NOT NULL,
  `registro_minuto_desde_id_bus` int(11) unsigned NOT NULL,
  `registro_minuto_hasta_id_bus` int(11) unsigned NOT NULL,
  `registro_minuto_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registro_minuto_fecha_minuto` datetime NOT NULL,
  `registro_minuto_monto` int(11) unsigned NOT NULL,
  PRIMARY KEY (`registro_minuto_id`),
  KEY `fk_registro_minuto_idx` (`registro_minuto_id_valor_minuto`),
  KEY `fk_registro_minuto_desde_idx` (`registro_minuto_desde_id_bus`),
  KEY `fk_registro_minuto_hasta_idx` (`registro_minuto_hasta_id_bus`),
  CONSTRAINT `fk_registro_minuto` FOREIGN KEY (`registro_minuto_id_valor_minuto`) REFERENCES `valor_minuto` (`valor_minuto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registro_minuto_desde` FOREIGN KEY (`registro_minuto_desde_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registro_minuto_hasta` FOREIGN KEY (`registro_minuto_hasta_id_bus`) REFERENCES `bus` (`bus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_minuto`
--

LOCK TABLES `registro_minuto` WRITE;
/*!40000 ALTER TABLE `registro_minuto` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_minuto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relacion_laboral`
--

DROP TABLE IF EXISTS `relacion_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relacion_laboral` (
  `relacion_laboral_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `relacion_laboral_id_trabajador` int(11) unsigned NOT NULL,
  `relacion_laboral_id_empresa` int(11) unsigned NOT NULL,
  `relacion_laboral_id_tipo_trabajador` int(11) unsigned NOT NULL,
  `relacion_laboral_id_tipo_contrato` int(11) unsigned NOT NULL,
  `relacion_laboral_id_terminal` int(11) unsigned NOT NULL,
  `relacion_laboral_id_operador` int(11) unsigned NOT NULL DEFAULT '0',
  `relacion_laboral_fecha_inicio` date NOT NULL,
  `relacion_laboral_fecha_fin` date NOT NULL,
  `relacion_laboral_sueldo_base` int(11) NOT NULL DEFAULT '0',
  `relacion_laboral_activo` tinyint(1) NOT NULL DEFAULT '0',
  `relacion_laboral_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`relacion_laboral_id`),
  KEY `id_empresa` (`relacion_laboral_id_empresa`),
  KEY `id_trabajador` (`relacion_laboral_id_trabajador`),
  KEY `fk_relacion_laboral_tipo_trabajador_idx` (`relacion_laboral_id_tipo_trabajador`),
  KEY `fk_relacion_laboral_Id_tipo_contrato_idx` (`relacion_laboral_id_tipo_contrato`),
  KEY `fk_relacion_id_terminal_idx` (`relacion_laboral_id_terminal`),
  KEY `fk_relacion_laboral_id_operador_idx` (`relacion_laboral_id_operador`),
  CONSTRAINT `fk_relacion_id_terminal` FOREIGN KEY (`relacion_laboral_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_relacion_laboral_Id_tipo_contrato` FOREIGN KEY (`relacion_laboral_id_tipo_contrato`) REFERENCES `tipo_contrato` (`tipo_contrato_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_relacion_laboral_id_empresa` FOREIGN KEY (`relacion_laboral_id_empresa`) REFERENCES `empresa` (`empresa_id`),
  CONSTRAINT `fk_relacion_laboral_id_operador` FOREIGN KEY (`relacion_laboral_id_operador`) REFERENCES `operador_transporte` (`operador_transporte_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_relacion_laboral_id_trabajador` FOREIGN KEY (`relacion_laboral_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`),
  CONSTRAINT `fk_relacion_laboral_tipo_trabajador` FOREIGN KEY (`relacion_laboral_id_tipo_trabajador`) REFERENCES `tipo_trabajador` (`tipo_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relacion_laboral`
--

LOCK TABLES `relacion_laboral` WRITE;
/*!40000 ALTER TABLE `relacion_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `relacion_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representante_empresa`
--

DROP TABLE IF EXISTS `representante_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `representante_empresa` (
  `representante_empresa_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `representante_empresa_id_empresa` int(11) unsigned NOT NULL,
  `representante_empresa_id_representante_legal` int(11) unsigned NOT NULL,
  PRIMARY KEY (`representante_empresa_id`),
  KEY `fk_representante_empresa_id_empresa_idx` (`representante_empresa_id_empresa`),
  KEY `fk_representante_empresa_id_representante_legal_idx` (`representante_empresa_id_representante_legal`),
  CONSTRAINT `fk_representante_empresa_id_empresa` FOREIGN KEY (`representante_empresa_id_empresa`) REFERENCES `empresa` (`empresa_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_representante_empresa_id_representante_legal` FOREIGN KEY (`representante_empresa_id_representante_legal`) REFERENCES `representante_legal` (`representante_legal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representante_empresa`
--

LOCK TABLES `representante_empresa` WRITE;
/*!40000 ALTER TABLE `representante_empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `representante_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representante_legal`
--

DROP TABLE IF EXISTS `representante_legal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `representante_legal` (
  `representante_legal_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `representante_legal_nombre` varchar(45) NOT NULL,
  `representante_legal_rut` varchar(45) NOT NULL,
  `representante_legal_direccion` varchar(45) DEFAULT NULL,
  `representante_legal_telefono` varchar(45) DEFAULT NULL,
  `representante_legal_email` varchar(45) DEFAULT NULL,
  `representante_legal_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`representante_legal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representante_legal`
--

LOCK TABLES `representante_legal` WRITE;
/*!40000 ALTER TABLE `representante_legal` DISABLE KEYS */;
/*!40000 ALTER TABLE `representante_legal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resumen_recaudacion`
--

DROP TABLE IF EXISTS `resumen_recaudacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resumen_recaudacion` (
  `resumen_recaudacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `resumen_recaudacion_id_caja` int(11) unsigned NOT NULL,
  `resumen_recaudacion_fecha_recaudacion` date NOT NULL,
  `resumen_recaudacion_total_recaudacion` int(11) unsigned NOT NULL,
  `resumen_recaudacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`resumen_recaudacion_id`),
  KEY `fk_resumen_recaudacion_id_caja_idx` (`resumen_recaudacion_id_caja`),
  CONSTRAINT `fk_resumen_recaudacion_id_caja` FOREIGN KEY (`resumen_recaudacion_id_caja`) REFERENCES `caja_terminal` (`caja_terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resumen_recaudacion`
--

LOCK TABLES `resumen_recaudacion` WRITE;
/*!40000 ALTER TABLE `resumen_recaudacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `resumen_recaudacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `rol_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rol_nombre` varchar(255) NOT NULL,
  `rol_descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'root','Super administrador'),(2,'Inspector','s');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_privilegio`
--

DROP TABLE IF EXISTS `rol_privilegio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_privilegio` (
  `rol_privilegio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rol_privilegio_id_rol` int(11) unsigned NOT NULL,
  `rol_privilegio_id_privilegio` int(11) unsigned NOT NULL,
  PRIMARY KEY (`rol_privilegio_id`),
  KEY `fk_rol_privilegio_id_rol_idx` (`rol_privilegio_id_rol`),
  KEY `fk_rol_privilegio_id_pri_idx` (`rol_privilegio_id_privilegio`),
  CONSTRAINT `fk_rol_privilegio_id_pri` FOREIGN KEY (`rol_privilegio_id_privilegio`) REFERENCES `privilegio` (`privilegio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rol_privilegio_id_rol` FOREIGN KEY (`rol_privilegio_id_rol`) REFERENCES `rol` (`rol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_privilegio`
--

LOCK TABLES `rol_privilegio` WRITE;
/*!40000 ALTER TABLE `rol_privilegio` DISABLE KEYS */;
INSERT INTO `rol_privilegio` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(23,1,52),(24,1,53),(25,1,54),(26,1,55),(27,1,56),(28,1,57),(29,1,58),(30,1,59),(31,1,60),(32,1,61),(33,1,62),(34,1,63),(35,1,64),(36,1,25);
/*!40000 ALTER TABLE `rol_privilegio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serie_boleto_guia`
--

DROP TABLE IF EXISTS `serie_boleto_guia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serie_boleto_guia` (
  `serie_boleto_guia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serie_boleto_guia_id_guia` int(11) unsigned NOT NULL,
  `serie_boleto_guia_id_boleto` int(11) unsigned NOT NULL,
  `serie_boleto_guia_numero_vuelta` int(11) unsigned NOT NULL,
  `serie_boleto_guia_valor_serie_boleto` int(11) unsigned NOT NULL,
  `serie_boleto_guia_serie_boleto` int(11) unsigned NOT NULL,
  `serie_boleto_guia_termino_boleto` int(11) unsigned NOT NULL,
  `serie_boleto_guia_cantidad_boleto` int(11) unsigned NOT NULL,
  `serie_boleto_guia_total_boleto` int(11) unsigned NOT NULL,
  `serie_boleto_guia_nuevo_boleto` tinyint(1) NOT NULL,
  `serie_boleto_guia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serie_boleto_guia_id`),
  KEY `fk_serie_boleto_guia_id_guia_idx` (`serie_boleto_guia_id_guia`),
  KEY `fk_serie_boleto_guia_id_boleto_idx` (`serie_boleto_guia_id_boleto`),
  CONSTRAINT `fk_serie_boleto_guia_id_boleto` FOREIGN KEY (`serie_boleto_guia_id_boleto`) REFERENCES `boleto` (`boleto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_serie_boleto_guia_id_guia` FOREIGN KEY (`serie_boleto_guia_id_guia`) REFERENCES `guia` (`guia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie_boleto_guia`
--

LOCK TABLES `serie_boleto_guia` WRITE;
/*!40000 ALTER TABLE `serie_boleto_guia` DISABLE KEYS */;
/*!40000 ALTER TABLE `serie_boleto_guia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicio` (
  `servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `servicio_numero_servicio` int(11) unsigned NOT NULL,
  `servicio_id_terminal` int(11) unsigned NOT NULL,
  `servicio_id_unidad` int(11) unsigned NOT NULL,
  `servicio_longitud` int(11) unsigned NOT NULL,
  `servicio_nombre` varchar(45) NOT NULL,
  `servicio_origen` varchar(45) NOT NULL,
  `servicio_destino` varchar(45) NOT NULL,
  `servicio_folio` varchar(45) DEFAULT NULL,
  `servicio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`servicio_id`),
  KEY `fk_servicio_id_terminal_idx` (`servicio_id_terminal`),
  KEY `fk_servicio_id_unidad_negocio_idx` (`servicio_id_unidad`),
  CONSTRAINT `fk_servicio_id_terminal` FOREIGN KEY (`servicio_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_servicio_id_unidad_negocio` FOREIGN KEY (`servicio_id_unidad`) REFERENCES `unidad_negocio` (`unidad_negocio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,302,1,4,93,'Marga-Marga','Peñablanca','Con-Cón','4100301','2017-04-21 11:49:05');
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sindicato`
--

DROP TABLE IF EXISTS `sindicato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sindicato` (
  `sindicato_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sindicato_nombre` varchar(100) NOT NULL,
  `sindicato_cuota` int(11) unsigned NOT NULL,
  PRIMARY KEY (`sindicato_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sindicato`
--

LOCK TABLES `sindicato` WRITE;
/*!40000 ALTER TABLE `sindicato` DISABLE KEYS */;
INSERT INTO `sindicato` VALUES (1,'SIN SINDICATO',0);
/*!40000 ALTER TABLE `sindicato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sueldo_base`
--

DROP TABLE IF EXISTS `sueldo_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sueldo_base` (
  `sueldo_base_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sueldo_base_base_normal` int(11) unsigned NOT NULL,
  `sueldo_base_base_partime` varchar(45) DEFAULT NULL,
  `sueldo_base_desde` date NOT NULL,
  `sueldo_base_hasta` date NOT NULL,
  `sueldo_base_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sueldo_base_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sueldo_base`
--

LOCK TABLES `sueldo_base` WRITE;
/*!40000 ALTER TABLE `sueldo_base` DISABLE KEYS */;
/*!40000 ALTER TABLE `sueldo_base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surtidor`
--

DROP TABLE IF EXISTS `surtidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `surtidor` (
  `surtidor_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `surtidor_id_terminal` int(11) unsigned NOT NULL,
  `surtidor_identificador` varchar(45) NOT NULL,
  `surtidor_observaciones` varchar(200) DEFAULT NULL,
  `surtidor_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`surtidor_id`),
  KEY `fk_surtidor_id_terminal_idx` (`surtidor_id_terminal`),
  CONSTRAINT `fk_surtidor_id_terminal` FOREIGN KEY (`surtidor_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surtidor`
--

LOCK TABLES `surtidor` WRITE;
/*!40000 ALTER TABLE `surtidor` DISABLE KEYS */;
INSERT INTO `surtidor` VALUES (1,1,'N°  1','dasd','2017-04-20 16:23:10');
/*!40000 ALTER TABLE `surtidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa_servicio`
--

DROP TABLE IF EXISTS `tarifa_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarifa_servicio` (
  `tarifa_servicio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tarifa_servicio_id_boleto` int(11) unsigned NOT NULL,
  `tarifa_servicio_id_servicio` int(11) unsigned NOT NULL,
  `tarifa_servicio_valor_tarifa` int(11) unsigned NOT NULL,
  `tarifa_servicio_fecha_tarifa` date NOT NULL,
  `tarifa_servicio_tarifa_activa` tinyint(1) NOT NULL,
  `tarifa_servicio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tarifa_servicio_id`),
  KEY `fk_tarifa_servicio_id_boleto_idx` (`tarifa_servicio_id_boleto`),
  KEY `fk_tarifa_servicio_id_servicio_idx` (`tarifa_servicio_id_servicio`),
  CONSTRAINT `fk_tarifa_servicio_id_boleto` FOREIGN KEY (`tarifa_servicio_id_boleto`) REFERENCES `boleto` (`boleto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarifa_servicio_id_servicio` FOREIGN KEY (`tarifa_servicio_id_servicio`) REFERENCES `servicio` (`servicio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa_servicio`
--

LOCK TABLES `tarifa_servicio` WRITE;
/*!40000 ALTER TABLE `tarifa_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarifa_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminal`
--

DROP TABLE IF EXISTS `terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminal` (
  `terminal_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `terminal_id_comuna` int(11) unsigned NOT NULL,
  `terminal_nombre` varchar(45) NOT NULL,
  `terminal_direccion` varchar(45) NOT NULL,
  `terminal_superficie` int(11) unsigned DEFAULT '0',
  `terminal_telefono` varchar(45) DEFAULT NULL,
  `terminal_email` varchar(45) DEFAULT NULL,
  `terminal_ubicacion_longitud` double(10,6) DEFAULT NULL,
  `terminal_ubicacion_latitud` double(10,6) DEFAULT NULL,
  `terminal_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`terminal_id`),
  KEY `fk_terminal_id_comuna_idx` (`terminal_id_comuna`),
  CONSTRAINT `fk_terminal_id_comuna` FOREIGN KEY (`terminal_id_comuna`) REFERENCES `comuna` (`comuna_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal`
--

LOCK TABLES `terminal` WRITE;
/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
INSERT INTO `terminal` VALUES (1,1,'Terminal de Prueba','Sin información',0,'Sin información','Sin información',0.000000,0.000000,'2017-04-20 10:28:29'),(24,1,'Terminal Ñandu','Las Acacias 0188',6000,'Sin Información','Sin Información',0.000000,0.000000,NULL);
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_abono`
--

DROP TABLE IF EXISTS `tipo_abono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_abono` (
  `tipo_abono_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_abono_nombre` varchar(45) NOT NULL,
  `tipo_abono_monto_defecto` int(11) unsigned NOT NULL DEFAULT '0',
  `tipo_abono_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_abono_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_abono`
--

LOCK TABLES `tipo_abono` WRITE;
/*!40000 ALTER TABLE `tipo_abono` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_abono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_carga_familiar`
--

DROP TABLE IF EXISTS `tipo_carga_familiar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_carga_familiar` (
  `tipo_carga_familiar_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_carga_familiar_nombre` varchar(45) NOT NULL,
  `tipo_carga_familiar_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_carga_familiar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_carga_familiar`
--

LOCK TABLES `tipo_carga_familiar` WRITE;
/*!40000 ALTER TABLE `tipo_carga_familiar` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_carga_familiar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cargo`
--

DROP TABLE IF EXISTS `tipo_cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cargo` (
  `tipo_cargo_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_cargo_nombre` varchar(45) NOT NULL,
  `tipo_cargo_monto_defecto` int(11) DEFAULT '0',
  `tipo_cargo_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_cargo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cargo`
--

LOCK TABLES `tipo_cargo` WRITE;
/*!40000 ALTER TABLE `tipo_cargo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_combustible`
--

DROP TABLE IF EXISTS `tipo_combustible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_combustible` (
  `tipo_combustible_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_combustible_nombre` varchar(45) NOT NULL,
  `tipo_combustible_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_combustible_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_combustible`
--

LOCK TABLES `tipo_combustible` WRITE;
/*!40000 ALTER TABLE `tipo_combustible` DISABLE KEYS */;
INSERT INTO `tipo_combustible` VALUES (1,'Petroleo','2017-04-20 16:23:58');
/*!40000 ALTER TABLE `tipo_combustible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_contrato`
--

DROP TABLE IF EXISTS `tipo_contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_contrato` (
  `tipo_contrato_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_contrato_nombre` varchar(100) NOT NULL,
  `tipo_contrato_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_contrato_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_contrato`
--

LOCK TABLES `tipo_contrato` WRITE;
/*!40000 ALTER TABLE `tipo_contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_control`
--

DROP TABLE IF EXISTS `tipo_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_control` (
  `tipo_control_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_control_nombre` varchar(45) NOT NULL,
  `tipo_control_clasificacion` varchar(45) DEFAULT NULL,
  `tipo_control_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_control_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_control`
--

LOCK TABLES `tipo_control` WRITE;
/*!40000 ALTER TABLE `tipo_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cotizacion_trabajador`
--

DROP TABLE IF EXISTS `tipo_cotizacion_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cotizacion_trabajador` (
  `tipo_cotizacion_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_cotizacion_trabajador_nombre` varchar(100) NOT NULL,
  `tipo_cotizacion_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_cotizacion_trabajador_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cotizacion_trabajador`
--

LOCK TABLES `tipo_cotizacion_trabajador` WRITE;
/*!40000 ALTER TABLE `tipo_cotizacion_trabajador` DISABLE KEYS */;
INSERT INTO `tipo_cotizacion_trabajador` VALUES (1,'ACTIVO Y COTIZA','2017-04-29 12:15:21');
/*!40000 ALTER TABLE `tipo_cotizacion_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cuenta_banco`
--

DROP TABLE IF EXISTS `tipo_cuenta_banco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cuenta_banco` (
  `tipo_cuenta_banco_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_cuenta_banco_nombre` varchar(45) NOT NULL,
  `tipo_cuenta_banco_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_cuenta_banco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cuenta_banco`
--

LOCK TABLES `tipo_cuenta_banco` WRITE;
/*!40000 ALTER TABLE `tipo_cuenta_banco` DISABLE KEYS */;
INSERT INTO `tipo_cuenta_banco` VALUES (1,'Corriente','2017-04-20 12:40:46'),(2,'Vista','2017-04-20 12:40:46'),(3,'Chequera Electrónica','2017-04-20 12:40:46'),(4,'Rut','2017-04-20 12:40:46');
/*!40000 ALTER TABLE `tipo_cuenta_banco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_demanda`
--

DROP TABLE IF EXISTS `tipo_demanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_demanda` (
  `tipo_demanda_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_demanda_nombre` varchar(45) NOT NULL,
  `tipo_demanda_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_demanda_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_demanda`
--

LOCK TABLES `tipo_demanda` WRITE;
/*!40000 ALTER TABLE `tipo_demanda` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_demanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_descuento_trabajador`
--

DROP TABLE IF EXISTS `tipo_descuento_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_descuento_trabajador` (
  `tipo_descuento_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_descuento_trabajador_nombre` varchar(255) NOT NULL,
  `tipo_descuento_trabajador_monto_defecto` int(11) DEFAULT NULL,
  `tipo_descuento_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_descuento_trabajador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_descuento_trabajador`
--

LOCK TABLES `tipo_descuento_trabajador` WRITE;
/*!40000 ALTER TABLE `tipo_descuento_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_descuento_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_dia_frecuencia`
--

DROP TABLE IF EXISTS `tipo_dia_frecuencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_dia_frecuencia` (
  `tipo_dia_frecuencia_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_dia_frecuencia_nombre` varchar(45) NOT NULL,
  `tipo_dia_frecuencia_activo` tinyint(1) NOT NULL DEFAULT '1',
  `tipo_dia_frecuencia_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_dia_frecuencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_dia_frecuencia`
--

LOCK TABLES `tipo_dia_frecuencia` WRITE;
/*!40000 ALTER TABLE `tipo_dia_frecuencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_dia_frecuencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_estacionalidad`
--

DROP TABLE IF EXISTS `tipo_estacionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_estacionalidad` (
  `tipo_estacionalidad_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_estacionalidad_nombre` varchar(100) NOT NULL,
  `tipo_estacionalidad_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_estacionalidad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_estacionalidad`
--

LOCK TABLES `tipo_estacionalidad` WRITE;
/*!40000 ALTER TABLE `tipo_estacionalidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_estacionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_haber_trabajador`
--

DROP TABLE IF EXISTS `tipo_haber_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_haber_trabajador` (
  `tipo_haber_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_haber_trabajador_nombre` varchar(255) NOT NULL,
  `tipo_haber_trabajador_monto_defecto` int(11) unsigned NOT NULL DEFAULT '0',
  `tipo_haber_trabajador_imponible` tinyint(1) NOT NULL,
  `tipo_haber_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_haber_trabajador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_haber_trabajador`
--

LOCK TABLES `tipo_haber_trabajador` WRITE;
/*!40000 ALTER TABLE `tipo_haber_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_haber_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_institucion_apv`
--

DROP TABLE IF EXISTS `tipo_institucion_apv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_institucion_apv` (
  `tipo_institucion_apv_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_institucion_apv_nombre` varchar(100) NOT NULL,
  `tipo_institucion_apv_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_institucion_apv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_institucion_apv`
--

LOCK TABLES `tipo_institucion_apv` WRITE;
/*!40000 ALTER TABLE `tipo_institucion_apv` DISABLE KEYS */;
INSERT INTO `tipo_institucion_apv` VALUES (1,'AFP ','2017-04-29 12:17:25'),(2,'BANCO ','2017-04-29 12:17:25');
/*!40000 ALTER TABLE `tipo_institucion_apv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_linea`
--

DROP TABLE IF EXISTS `tipo_linea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_linea` (
  `tipo_linea_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_linea_nombre` varchar(45) NOT NULL,
  `tipo_linea_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_linea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_linea`
--

LOCK TABLES `tipo_linea` WRITE;
/*!40000 ALTER TABLE `tipo_linea` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_linea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_movimiento_personal`
--

DROP TABLE IF EXISTS `tipo_movimiento_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_movimiento_personal` (
  `tipo_movimiento_personal_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_movimiento_personal_nombre` varchar(100) NOT NULL,
  `tipo_movimiento_personal_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_movimiento_personal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_movimiento_personal`
--

LOCK TABLES `tipo_movimiento_personal` WRITE;
/*!40000 ALTER TABLE `tipo_movimiento_personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_movimiento_personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_nomina`
--

DROP TABLE IF EXISTS `tipo_nomina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_nomina` (
  `tipo_nomina_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_nomina_nombre` varchar(45) NOT NULL,
  `tipo_nomina_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_nomina_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_nomina`
--

LOCK TABLES `tipo_nomina` WRITE;
/*!40000 ALTER TABLE `tipo_nomina` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_nomina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_observacion`
--

DROP TABLE IF EXISTS `tipo_observacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_observacion` (
  `tipo_observacion_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_observacion_nombre` varchar(100) NOT NULL,
  `tipo_observacion_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_observacion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_observacion`
--

LOCK TABLES `tipo_observacion` WRITE;
/*!40000 ALTER TABLE `tipo_observacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_observacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_trabajador`
--

DROP TABLE IF EXISTS `tipo_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_trabajador` (
  `tipo_trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_trabajador_nombre` varchar(45) NOT NULL,
  `tipo_trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipo_trabajador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_trabajador`
--

LOCK TABLES `tipo_trabajador` WRITE;
/*!40000 ALTER TABLE `tipo_trabajador` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tope`
--

DROP TABLE IF EXISTS `tope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tope` (
  `tope_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tope_nombre` varchar(255) NOT NULL,
  `tope_monto_uf` float(5,3) NOT NULL,
  PRIMARY KEY (`tope_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tope`
--

LOCK TABLES `tope` WRITE;
/*!40000 ALTER TABLE `tope` DISABLE KEYS */;
/*!40000 ALTER TABLE `tope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador` (
  `trabajador_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `trabajador_id_terminal` int(11) unsigned NOT NULL,
  `trabajador_id_sindicato` int(11) unsigned NOT NULL,
  `trabajador_id_tipo_cotizacion_trabajador` int(11) unsigned NOT NULL,
  `trabajador_id_asignacion_familiar` int(11) unsigned NOT NULL,
  `trabajador_id_institucion_salud` int(11) unsigned NOT NULL,
  `trabajador_id_institucion_prevision` int(11) unsigned NOT NULL,
  `trabajador_id_institucion_apv` int(11) unsigned NOT NULL,
  `trabajador_id_centro_costo` int(11) unsigned NOT NULL,
  `trabajador_id_forma_pago` int(11) unsigned NOT NULL,
  `trabajador_id_comuna` int(11) unsigned NOT NULL,
  `trabajador_id_estado_civil` int(11) unsigned NOT NULL,
  `trabajador_codigo` int(11) unsigned NOT NULL,
  `trabajador_rut` varchar(9) NOT NULL,
  `trabajador_nombres` varchar(255) NOT NULL,
  `trabajador_apellido_paterno` varchar(255) NOT NULL,
  `trabajador_apellido_materno` varchar(255) NOT NULL,
  `trabajador_fecha_nacimiento` date DEFAULT NULL,
  `trabajador_nacionalidad` tinyint(1) DEFAULT NULL,
  `trabajador_sexo` tinyint(1) DEFAULT NULL,
  `trabajador_calle` varchar(255) DEFAULT NULL,
  `trabajador_numero_direccion` varchar(45) DEFAULT NULL,
  `trabajador_telefono_fijo` varchar(255) DEFAULT NULL,
  `trabajador_celular` varchar(255) DEFAULT NULL,
  `trabajador_email` varchar(255) DEFAULT NULL,
  `trabajador_numero_cargas` int(11) unsigned NOT NULL DEFAULT '0',
  `trabajador_monto_salud` float(5,4) unsigned DEFAULT '0.0000',
  `trabajador_forma_pago_apv` tinyint(1) unsigned DEFAULT NULL COMMENT 'Directa o Indirecta',
  `trabajador_monto_apv` int(11) DEFAULT '0',
  `trabajador_subsidio_joven` tinyint(1) DEFAULT NULL,
  `trabajador_cesantia` tinyint(1) DEFAULT '0',
  `trabajador_contratado` tinyint(1) DEFAULT '0',
  `trabajador_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trabajador_id`),
  UNIQUE KEY `rut_UNIQUE` (`trabajador_rut`),
  KEY `fk_trabajador_id_salud_idx` (`trabajador_id_institucion_salud`),
  KEY `fk_trabajador_id_prevision_idx` (`trabajador_id_institucion_prevision`),
  KEY `fk_trabajador_id_apv_idx` (`trabajador_id_institucion_apv`),
  KEY `fk_trabajador_id_cotizacion_idx` (`trabajador_id_tipo_cotizacion_trabajador`),
  KEY `fk_trabajador_id_comuna_idx` (`trabajador_id_comuna`),
  KEY `fk_trabajador_id_asignacion_familiar_idx` (`trabajador_id_asignacion_familiar`),
  KEY `fk_trabajador_id_terminal_idx` (`trabajador_id_terminal`),
  KEY `fk_trabajador_id_sindicato_idx` (`trabajador_id_sindicato`),
  KEY `fk_trabajador_id_centro_costo_idx` (`trabajador_id_centro_costo`),
  KEY `fk_trabajador_id_forma_pago_idx` (`trabajador_id_forma_pago`),
  KEY `fk_trabajador_id_estado_civil_idx` (`trabajador_id_estado_civil`),
  CONSTRAINT `fk_trabajador_id_apv` FOREIGN KEY (`trabajador_id_institucion_apv`) REFERENCES `institucion_apv` (`institucion_apv_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_asignacion_familiar` FOREIGN KEY (`trabajador_id_asignacion_familiar`) REFERENCES `asignacion_familiar` (`asignacion_familiar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_centro_costo` FOREIGN KEY (`trabajador_id_centro_costo`) REFERENCES `centro_costo` (`centro_costo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_comuna` FOREIGN KEY (`trabajador_id_comuna`) REFERENCES `comuna` (`comuna_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_cotizacion` FOREIGN KEY (`trabajador_id_tipo_cotizacion_trabajador`) REFERENCES `tipo_cotizacion_trabajador` (`tipo_cotizacion_trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_estado_civil` FOREIGN KEY (`trabajador_id_estado_civil`) REFERENCES `estado_civil` (`estado_civil_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_forma_pago` FOREIGN KEY (`trabajador_id_forma_pago`) REFERENCES `forma_pago` (`forma_pago_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_prevision` FOREIGN KEY (`trabajador_id_institucion_prevision`) REFERENCES `institucion_prevision` (`institucion_prevision_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_salud` FOREIGN KEY (`trabajador_id_institucion_salud`) REFERENCES `institucion_salud` (`institucion_salud_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_sindicato` FOREIGN KEY (`trabajador_id_sindicato`) REFERENCES `sindicato` (`sindicato_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_id_terminal` FOREIGN KEY (`trabajador_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (1,1,1,1,1,1,1,1,1,1,1,1,1,'104116116','CONDUCTOR DE PRUEBA','APELLIDO DE PRUEBA 1','APELLIDO DE PRUEBA 2','1960-01-01',1,1,'SIN INFORMACIÓN','SIN INFORMACIÓN ','SIN INFORMACIÓN ','SIN INFORMACIÓN ','SIN INFORMACIÓN ',0,0.0000,0,0,0,0,0,'2017-04-29 12:21:02');
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador_adicional_salud`
--

DROP TABLE IF EXISTS `trabajador_adicional_salud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador_adicional_salud` (
  `trabajador_adicional_salud_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `trabajador_adicional_salud_id_trabajador` int(11) unsigned NOT NULL,
  `trabajador_adicional_salud_id_moneda` int(11) unsigned NOT NULL,
  `trabajador_adicional_salud_porcentaje` float(5,4) unsigned NOT NULL,
  `trabajador_adicional_salud_monto_fijo` int(11) unsigned NOT NULL,
  PRIMARY KEY (`trabajador_adicional_salud_id`),
  KEY `fk_trabajador_adicional_id_moneda_idx` (`trabajador_adicional_salud_id_moneda`),
  KEY `fk_trabajador_adicional_id_trabajador_idx` (`trabajador_adicional_salud_id_trabajador`),
  CONSTRAINT `fk_trabajador_adicional_id_moneda` FOREIGN KEY (`trabajador_adicional_salud_id_moneda`) REFERENCES `moneda_pactada_institucion_salud` (`moneda_pactada_institucion_salud_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_adicional_id_trabajador` FOREIGN KEY (`trabajador_adicional_salud_id_trabajador`) REFERENCES `trabajador` (`trabajador_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador_adicional_salud`
--

LOCK TABLES `trabajador_adicional_salud` WRITE;
/*!40000 ALTER TABLE `trabajador_adicional_salud` DISABLE KEYS */;
/*!40000 ALTER TABLE `trabajador_adicional_salud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_negocio`
--

DROP TABLE IF EXISTS `unidad_negocio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidad_negocio` (
  `unidad_negocio_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `unidad_negocio_id_region` int(11) unsigned NOT NULL,
  `unidad_negocio_id_operador_transporte` int(11) unsigned NOT NULL,
  `unidad_negocio_nombre` varchar(45) NOT NULL,
  `unidad_negocio_numero` int(11) unsigned NOT NULL,
  `unidad_negocio_folio` varchar(45) DEFAULT NULL,
  `unidad_negocio_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`unidad_negocio_id`),
  KEY `fk_unidad_negocio_id_region_idx` (`unidad_negocio_id_region`),
  KEY `fk_unidad_negocio_id_operador_idx` (`unidad_negocio_id_operador_transporte`),
  CONSTRAINT `fk_unidad_negocio_id_operador` FOREIGN KEY (`unidad_negocio_id_operador_transporte`) REFERENCES `operador_transporte` (`operador_transporte_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_unidad_negocio_id_region` FOREIGN KEY (`unidad_negocio_id_region`) REFERENCES `region` (`region_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_negocio`
--

LOCK TABLES `unidad_negocio` WRITE;
/*!40000 ALTER TABLE `unidad_negocio` DISABLE KEYS */;
INSERT INTO `unidad_negocio` VALUES (4,1,1,'SM03',3,'430000','2017-04-20 16:56:06');
/*!40000 ALTER TABLE `unidad_negocio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `usuario_id_terminal` int(11) unsigned NOT NULL,
  `usuario_id_rol` int(11) unsigned NOT NULL,
  `usuario_rut` varchar(9) NOT NULL,
  `usuario_pass` varchar(45) NOT NULL,
  `usuario_nombres` varchar(255) DEFAULT NULL,
  `usuario_apellido_paterno` varchar(255) DEFAULT NULL,
  `usuario_apellido_materno` varchar(255) DEFAULT NULL,
  `usuario_email` varchar(255) DEFAULT NULL,
  `usuario_activo` tinyint(1) DEFAULT '1',
  `usuario_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`usuario_id`),
  KEY `fk_usuario_id_rol_idx` (`usuario_id_rol`),
  KEY `fk_usuario_id_terminal_idx` (`usuario_id_terminal`),
  CONSTRAINT `fk_usuario_id_rol` FOREIGN KEY (`usuario_id_rol`) REFERENCES `rol` (`rol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_id_terminal` FOREIGN KEY (`usuario_id_terminal`) REFERENCES `terminal` (`terminal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,1,'165015096','NintendO64','Ian ','Concha','Herrera','i.concha@areatecnica.cl',1,'2017-04-20 10:29:40'),(6,24,1,'13193467k','poly','Paola ','Morales','Pimentel','',1,'2017-04-20 12:36:41');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valor_fijo`
--

DROP TABLE IF EXISTS `valor_fijo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_fijo` (
  `valor_fijo_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `valor_fijo_nombre_valor` varchar(255) NOT NULL,
  `valor_fijo_monto` float(5,2) DEFAULT NULL,
  `valor_fijo_fecha_ingreso` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`valor_fijo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_fijo`
--

LOCK TABLES `valor_fijo` WRITE;
/*!40000 ALTER TABLE `valor_fijo` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_fijo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valor_minuto`
--

DROP TABLE IF EXISTS `valor_minuto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_minuto` (
  `valor_minuto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `valor_minuto_nombre` varchar(100) NOT NULL,
  `valor_minuto_precio` int(11) unsigned NOT NULL,
  `valor_minuto_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`valor_minuto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_minuto`
--

LOCK TABLES `valor_minuto` WRITE;
/*!40000 ALTER TABLE `valor_minuto` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_minuto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valor_rollo_unidad`
--

DROP TABLE IF EXISTS `valor_rollo_unidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_rollo_unidad` (
  `valor_rollo_unidad_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `valor_rollo_unidad_id_unidad` int(11) unsigned NOT NULL,
  `valor_rollo_unidad_compra` int(11) unsigned NOT NULL,
  `valor_rollo_unidad_venta` int(11) unsigned NOT NULL,
  PRIMARY KEY (`valor_rollo_unidad_id`),
  KEY `fk_valor_rollo_unidad_id_unidad_idx` (`valor_rollo_unidad_id_unidad`),
  CONSTRAINT `fk_valor_rollo_unidad_id_unidad` FOREIGN KEY (`valor_rollo_unidad_id_unidad`) REFERENCES `unidad_negocio` (`unidad_negocio_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_rollo_unidad`
--

LOCK TABLES `valor_rollo_unidad` WRITE;
/*!40000 ALTER TABLE `valor_rollo_unidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_rollo_unidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valor_uf`
--

DROP TABLE IF EXISTS `valor_uf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_uf` (
  `valor_uf_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `valor_uf_fecha` date NOT NULL,
  `valor_uf_monto` float(6,4) unsigned NOT NULL,
  `valor_uf_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`valor_uf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_uf`
--

LOCK TABLES `valor_uf` WRITE;
/*!40000 ALTER TABLE `valor_uf` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_uf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valor_utm`
--

DROP TABLE IF EXISTS `valor_utm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valor_utm` (
  `valor_utm_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `valor_utm_fecha` date NOT NULL,
  `valor_utm_monto` float(6,4) unsigned NOT NULL,
  `valor_utm_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`valor_utm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valor_utm`
--

LOCK TABLES `valor_utm` WRITE;
/*!40000 ALTER TABLE `valor_utm` DISABLE KEYS */;
/*!40000 ALTER TABLE `valor_utm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta_boleto`
--

DROP TABLE IF EXISTS `venta_boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta_boleto` (
  `venta_boleto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `venta_boleto_id_guia` int(11) unsigned NOT NULL,
  `venta_boleto_id_inventario_caja` int(11) unsigned NOT NULL,
  `venta_boleto_numero_boleta` int(11) DEFAULT NULL,
  `venta_boleto_valor_venta_boleto` int(11) unsigned NOT NULL,
  `venta_boleto_recaudado` tinyint(1) NOT NULL DEFAULT '0',
  `venta_boleto_fecha_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`venta_boleto_id`),
  KEY `fk_venta_boleto_guia_id_guia_idx` (`venta_boleto_id_guia`),
  KEY `fk_venta_boleto_guia_id_inventario_idx` (`venta_boleto_id_inventario_caja`),
  CONSTRAINT `fk_venta_boleto_guia_id_guia` FOREIGN KEY (`venta_boleto_id_guia`) REFERENCES `guia` (`guia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_boleto_guia_id_inventario` FOREIGN KEY (`venta_boleto_id_inventario_caja`) REFERENCES `inventario_caja` (`inventario_caja_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_boleto`
--

LOCK TABLES `venta_boleto` WRITE;
/*!40000 ALTER TABLE `venta_boleto` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta_boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta_combustible`
--

DROP TABLE IF EXISTS `venta_combustible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta_combustible` (
  `venta_combustible_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `venta_combustible_id_surtidor` int(11) unsigned NOT NULL,
  `venta_combustible_id_guia` int(11) unsigned NOT NULL,
  `venta_combustible_fecha` datetime NOT NULL,
  `venta_combustible_valor` int(11) NOT NULL,
  `venta_combustible_cantidad` float(5,2) NOT NULL,
  `venta_combustible_valor_combustible` int(11) DEFAULT NULL,
  `venta_combustible_numero_boleta` int(11) NOT NULL,
  PRIMARY KEY (`venta_combustible_id`),
  KEY `fk_venta_combustible_id_surtidor_idx` (`venta_combustible_id_surtidor`),
  KEY `fk_venta_combustible_id_guia_idx` (`venta_combustible_id_guia`),
  CONSTRAINT `fk_venta_combustible_id_guia` FOREIGN KEY (`venta_combustible_id_guia`) REFERENCES `guia` (`guia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_combustible_id_surtidor` FOREIGN KEY (`venta_combustible_id_surtidor`) REFERENCES `surtidor` (`surtidor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_combustible`
--

LOCK TABLES `venta_combustible` WRITE;
/*!40000 ALTER TABLE `venta_combustible` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta_combustible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sigf_v2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-02 22:46:22
