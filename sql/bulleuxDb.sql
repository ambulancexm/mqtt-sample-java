-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 28 nov. 2020 à 12:14
-- Version du serveur :  10.3.27-MariaDB
-- Version de PHP : 7.3.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bulleuxDb`
--
CREATE DATABASE IF NOT EXISTS bulleuxDb
-- --------------------------------------------------------

--
-- Structure de la table `sensorTest`
--

CREATE TABLE IF NOT EXISTS `sensorTest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projet` varchar(50) DEFAULT NULL,
  `line` int(11) DEFAULT NULL,
  `mac` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `val` double NOT NULL,
  `date` date NOT NULL,
  `time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `sensorTest`
--
ALTER TABLE `sensorTest`
  ADD PRIMARY KEY (`id`);

