-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16-Jun-2016 às 04:39
-- Versão do servidor: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `distribuido2`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `evento`
--

CREATE TABLE IF NOT EXISTS `evento` (
  `id` int(11) NOT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `data_ini` varchar(5) DEFAULT NULL,
  `data_fim` varchar(5) DEFAULT NULL,
  `tipo_evento_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `data` varchar(10) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `evento`
--

INSERT INTO `evento` (`id`, `nome`, `data_ini`, `data_fim`, `tipo_evento_id`, `usuario_id`, `data`) VALUES
(5, 'primeiro', '20:00', '22:00', 1, 2, '30/07/2016'),
(6, 'primerio', '22:00', '23:00', 0, 2, '30/07/1990'),
(8, 'teste', '22:00', '23:00', 3, 2, '12/05/2016'),
(9, 'teste evento', '18:00', '20:00', 2, 2, '10/10/2016'),
(10, 'teste evento 2', '10:00', '12:00', 3, 2, '20/06/2016'),
(11, 'teste evento labo', '10:00', '12:00', 4, 2, '30/07/2016');

--
-- Acionadores `evento`
--
DELIMITER $$
CREATE TRIGGER `evento_controlado` AFTER INSERT ON `evento`
 FOR EACH ROW BEGIN
	INSERT INTO presenca_evento(id_evento, controlado) values (new.id, 0);	
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `presenca_evento`
--

CREATE TABLE IF NOT EXISTS `presenca_evento` (
  `id` int(11) NOT NULL,
  `id_evento` int(11) DEFAULT NULL,
  `id_usuario_controle` int(11) DEFAULT NULL,
  `data_hora_ini` date DEFAULT NULL,
  `data_hora_fim` date DEFAULT NULL,
  `controlado` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `presenca_evento`
--

INSERT INTO `presenca_evento` (`id`, `id_evento`, `id_usuario_controle`, `data_hora_ini`, `data_hora_fim`, `controlado`) VALUES
(7, 8, 2, '2016-06-15', NULL, 0),
(9, 10, 2, '2016-06-15', NULL, 0),
(10, 11, 2, '2016-06-15', NULL, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `presenca_usuario`
--

CREATE TABLE IF NOT EXISTS `presenca_usuario` (
  `id` int(11) NOT NULL,
  `id_presenca_evento` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `presente` int(11) DEFAULT NULL,
  `id_evento` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `presenca_usuario`
--

INSERT INTO `presenca_usuario` (`id`, `id_presenca_evento`, `id_usuario`, `presente`, `id_evento`) VALUES
(1, NULL, 3, NULL, 2),
(2, NULL, 1488465, NULL, 10),
(3, NULL, 1488465, NULL, 11),
(4, NULL, 1234567, NULL, 11),
(5, NULL, 1488465, NULL, 11),
(6, NULL, 1488465, NULL, 11),
(7, NULL, 1488465, NULL, 10),
(8, NULL, 1488465, NULL, 10),
(9, NULL, 1488465, NULL, 11),
(10, NULL, 1488465, NULL, 11),
(11, NULL, 1488465, NULL, 8),
(12, NULL, 1488465, NULL, 10),
(13, NULL, 12345678, NULL, 11),
(14, NULL, 999999, NULL, 8),
(15, NULL, 1488465, NULL, 8),
(16, NULL, 999999, NULL, 10),
(17, NULL, 1488465, NULL, 10),
(18, NULL, 1234567, NULL, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_evento`
--

CREATE TABLE IF NOT EXISTS `tipo_evento` (
  `id` int(11) NOT NULL,
  `descricao` varchar(40) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipo_evento`
--

INSERT INTO `tipo_evento` (`id`, `descricao`) VALUES
(3, 'Palestra'),
(4, 'Labo'),
(5, 'Teste');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `senha` varchar(10) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `curso` varchar(20) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  `turno` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `ra` varchar(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `senha`, `status`, `curso`, `periodo`, `turno`, `email`, `telefone`, `ra`) VALUES
(2, 'usuario', '123456', 1, 'ASB', 1, NULL, 'lc@hotmailc.com', '(42)99336864', '1488565'),
(3, 'Luiz Carlos', NULL, NULL, 'AS35', 4, NULL, 'lc.pg@hotmail.com', '(42)99336864', '1488465'),
(4, 'Teste null', NULL, NULL, 'ASV', 1, NULL, 'lc@teste.com', '(42)99403439', '18273823'),
(5, 'alunor7', NULL, NULL, 'SET', 7, NULL, 'sete@sete.com', '(77)77777777', '1234567'),
(6, 'aluno8', NULL, NULL, 'ASW', 3, NULL, 'oito@oito.com', '(42)88888888', '12345678'),
(7, 'aluno9', NULL, NULL, 'ASX', 4, NULL, 'nove@nove.com', '(99)99999999', '999999');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `presenca_evento`
--
ALTER TABLE `presenca_evento`
  ADD PRIMARY KEY (`id`), ADD KEY `id` (`id`);

--
-- Indexes for table `presenca_usuario`
--
ALTER TABLE `presenca_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tipo_evento`
--
ALTER TABLE `tipo_evento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `evento`
--
ALTER TABLE `evento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `presenca_evento`
--
ALTER TABLE `presenca_evento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `presenca_usuario`
--
ALTER TABLE `presenca_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `tipo_evento`
--
ALTER TABLE `tipo_evento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
