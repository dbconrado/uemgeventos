-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema uemgeventos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema uemgeventos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `uemgeventos` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `uemgeventos` ;

-- -----------------------------------------------------
-- Table `uemgeventos`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`evento` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(255) NOT NULL COMMENT '',
  `data_inicio` DATE NULL COMMENT '',
  `data_termino` DATE NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`atividade` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `aceita_inscricao` TINYINT(1) NOT NULL COMMENT '',
  `carga_horaria` INT NOT NULL COMMENT '',
  `responsavel` VARCHAR(255) NOT NULL COMMENT '',
  `nome` VARCHAR(255) NOT NULL COMMENT '',
  `vagas` INT NULL COMMENT '',
  `evento_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_atividade_evento1_idx` (`evento_id` ASC)  COMMENT '',
  CONSTRAINT `fk_atividade_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `uemgeventos`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`ocorrencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`ocorrencia` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `horario_inicio` TIME NOT NULL COMMENT '',
  `horario_termino` TIME NOT NULL COMMENT '',
  `local` VARCHAR(255) NOT NULL COMMENT '',
  `atividade_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_ocorrencia_atividade_idx` (`atividade_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ocorrencia_atividade`
    FOREIGN KEY (`atividade_id`)
    REFERENCES `uemgeventos`.`atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(255) NOT NULL COMMENT '',
  `email` VARCHAR(255) NOT NULL COMMENT '',
  `telefone` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`inscricao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`inscricao` (
  `pessoa_id` INT NOT NULL COMMENT '',
  `atividade_id` INT NOT NULL COMMENT '',
  `comissao` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`pessoa_id`, `atividade_id`)  COMMENT '',
  INDEX `fk_pessoa_has_atividade_atividade1_idx` (`atividade_id` ASC)  COMMENT '',
  INDEX `fk_pessoa_has_atividade_pessoa1_idx` (`pessoa_id` ASC)  COMMENT '',
  CONSTRAINT `fk_pessoa_has_atividade_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `uemgeventos`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_has_atividade_atividade1`
    FOREIGN KEY (`atividade_id`)
    REFERENCES `uemgeventos`.`atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`frequencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`frequencia` (
  `inscricao_pessoa_id` INT NOT NULL COMMENT '',
  `inscricao_atividade_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`inscricao_pessoa_id`, `inscricao_atividade_id`)  COMMENT '',
  CONSTRAINT `fk_frequencia_inscricao1`
    FOREIGN KEY (`inscricao_pessoa_id` , `inscricao_atividade_id`)
    REFERENCES `uemgeventos`.`inscricao` (`pessoa_id` , `atividade_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`registro` (
  `frequencia_inscricao_pessoa_id` INT NOT NULL COMMENT '',
  `frequencia_inscricao_atividade_id` INT NOT NULL COMMENT '',
  `hora_entrada` TIMESTAMP NULL COMMENT '',
  `hora_saida` TIMESTAMP NULL COMMENT '',
  PRIMARY KEY (`frequencia_inscricao_pessoa_id`, `frequencia_inscricao_atividade_id`)  COMMENT '',
  CONSTRAINT `fk_registro_frequencia1`
    FOREIGN KEY (`frequencia_inscricao_pessoa_id` , `frequencia_inscricao_atividade_id`)
    REFERENCES `uemgeventos`.`frequencia` (`inscricao_pessoa_id` , `inscricao_atividade_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uemgeventos`.`teste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uemgeventos`.`teste` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `campo` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;

-----------
-- CRIANDO USUARIO
-----------
GRANT ALL PRIVILEDGES ON `uemgeventos`.* TO `eventos_user`@`localhost` IDENTIFIED BY `@eventos123`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
