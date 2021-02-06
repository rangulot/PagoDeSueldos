-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 05-02-2021 a las 20:21:40
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pago_de_sueldos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `idcontrato` int(11) DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `sueldobasico` double NOT NULL,
  `puestodestino` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idcategoria`),
  KEY `idcontrato` (`idcontrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `complementos`
--

DROP TABLE IF EXISTS `complementos`;
CREATE TABLE IF NOT EXISTS `complementos` (
  `idcomplemento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `valor` double NOT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idcomplemento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

DROP TABLE IF EXISTS `contratos`;
CREATE TABLE IF NOT EXISTS `contratos` (
  `idcontrato` int(11) NOT NULL AUTO_INCREMENT,
  `idempleado` int(11) NOT NULL,
  `idcategoria` int(11) DEFAULT NULL,
  `idcomplemento` int(11) DEFAULT NULL,
  `tipoContrato` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_alta` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_baja` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idcontrato`),
  KEY `idempleado` (`idempleado`),
  KEY `contratos_ibfk_2` (`idcategoria`),
  KEY `contratos_ibfk_3` (`idcomplemento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `deducciones`
--

DROP TABLE IF EXISTS `deducciones`;
CREATE TABLE IF NOT EXISTS `deducciones` (
  `iddeduccion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `contingencias_comunes` double NOT NULL,
  `seguridad_social` double NOT NULL,
  `desempleo` double NOT NULL,
  `formacion_profesional` double NOT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`iddeduccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallesnominas`
--

DROP TABLE IF EXISTS `detallesnominas`;
CREATE TABLE IF NOT EXISTS `detallesnominas` (
  `iddetallenomina` int(11) NOT NULL AUTO_INCREMENT,
  `idnomina` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `iddeduccion` int(11) NOT NULL,
  `responsable` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`iddetallenomina`),
  KEY `idnomina` (`idnomina`),
  KEY `idempleado` (`idempleado`),
  KEY `iddeduccion` (`iddeduccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `idempleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `dni` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `cuenta_corriente` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idempleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominas`
--

DROP TABLE IF EXISTS `nominas`;
CREATE TABLE IF NOT EXISTS `nominas` (
  `idnomina` int(11) NOT NULL AUTO_INCREMENT,
  `encargado` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `totalapagar` double NOT NULL,
  `cortemes` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_actualizacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idnomina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD CONSTRAINT `categorias_ibfk_1` FOREIGN KEY (`idcontrato`) REFERENCES `contratos` (`idcontrato`);

--
-- Filtros para la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD CONSTRAINT `contratos_ibfk_1` FOREIGN KEY (`idempleado`) REFERENCES `empleados` (`idempleado`),
  ADD CONSTRAINT `contratos_ibfk_2` FOREIGN KEY (`idcategoria`) REFERENCES `categorias` (`idcategoria`),
  ADD CONSTRAINT `contratos_ibfk_3` FOREIGN KEY (`idcomplemento`) REFERENCES `complementos` (`idcomplemento`);

--
-- Filtros para la tabla `detallesnominas`
--
ALTER TABLE `detallesnominas`
  ADD CONSTRAINT `detallesnominas_ibfk_1` FOREIGN KEY (`idnomina`) REFERENCES `nominas` (`idnomina`),
  ADD CONSTRAINT `detallesnominas_ibfk_2` FOREIGN KEY (`idempleado`) REFERENCES `empleados` (`idempleado`),
  ADD CONSTRAINT `detallesnominas_ibfk_3` FOREIGN KEY (`iddeduccion`) REFERENCES `deducciones` (`iddeduccion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
