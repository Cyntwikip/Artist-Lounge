-- MySQL Script generated by MySQL Workbench
-- 04/13/16 23:25:02
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema artist
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `artist` ;

-- -----------------------------------------------------
-- Schema artist
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `artist` DEFAULT CHARACTER SET utf8 ;
USE `artist` ;

-- -----------------------------------------------------
-- Table `artist`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`Account` (
  `idAccount` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profilepic` VARCHAR(80) NULL,
  `name` VARCHAR(45) NOT NULL,
  `timeCreated` DATETIME NOT NULL,
  `birthDate` DATE NOT NULL,
  PRIMARY KEY (`idAccount`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`Challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`Challenge` (
  `idChallenge` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `startTime` DATETIME NOT NULL,
  `endTime` DATETIME NULL,
  PRIMARY KEY (`idChallenge`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`Post` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `time` DATETIME NOT NULL,
  `paint` VARCHAR(80) NOT NULL,
  `description` VARCHAR(255) NULL,
  `private` TINYINT(1) NOT NULL DEFAULT 0,
  `Account_idAccount` INT NOT NULL,
  `Challenge_idChallenge` INT NULL,
  PRIMARY KEY (`idPost`),
  INDEX `fk_Post_Account1_idx` (`Account_idAccount` ASC),
  INDEX `fk_Post_Challenge1_idx` (`Challenge_idChallenge` ASC),
  CONSTRAINT `fk_Post_Account1`
    FOREIGN KEY (`Account_idAccount`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_Challenge1`
    FOREIGN KEY (`Challenge_idChallenge`)
    REFERENCES `artist`.`Challenge` (`idChallenge`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`Challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`Challenge` (
  `idChallenge` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `startTime` DATETIME NOT NULL,
  `endTime` DATETIME NULL,
  PRIMARY KEY (`idChallenge`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`LikeChallenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`LikeChallenge` (
  `lc_idAccount` INT NOT NULL,
  `lc_idChallenge` INT NOT NULL,
  INDEX `fk_LikeChallenge_Challenge1_idx` (`lc_idChallenge` ASC),
  INDEX `fk_LikeChallenge_Account1_idx` (`lc_idAccount` ASC),
  CONSTRAINT `fk_LikeChallenge_Challenge1`
    FOREIGN KEY (`lc_idChallenge`)
    REFERENCES `artist`.`Challenge` (`idChallenge`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LikeChallenge_Account1`
    FOREIGN KEY (`lc_idAccount`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`CommentChallenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`CommentChallenge` (
  `cc_idAccount` INT NOT NULL,
  `cc_idChallenge` INT NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `time` DATETIME NOT NULL,
  INDEX `fk_CommentChallenge_Account1_idx` (`cc_idAccount` ASC),
  INDEX `fk_CommentChallenge_Challenge1_idx` (`cc_idChallenge` ASC),
  CONSTRAINT `fk_CommentChallenge_Account1`
    FOREIGN KEY (`cc_idAccount`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CommentChallenge_Challenge1`
    FOREIGN KEY (`cc_idChallenge`)
    REFERENCES `artist`.`Challenge` (`idChallenge`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`LikeTable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`LikeTable` (
  `Account_idAccount` INT NOT NULL,
  `Post_idPost` INT NOT NULL,
  INDEX `fk_LikePost_Account1_idx` (`Account_idAccount` ASC),
  INDEX `fk_LikePost_Post1_idx` (`Post_idPost` ASC),
  CONSTRAINT `fk_LikePost_Account1`
    FOREIGN KEY (`Account_idAccount`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LikePost_Post1`
    FOREIGN KEY (`Post_idPost`)
    REFERENCES `artist`.`Post` (`idPost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`Comment` (
  `idComment` INT NOT NULL AUTO_INCREMENT,
  `Account_idAccount` INT NOT NULL,
  `Post_idPost` INT NOT NULL,
  `comment` VARCHAR(1000) NOT NULL,
  `time` DATETIME NOT NULL,
  INDEX `fk_CommentPost_Account1_idx` (`Account_idAccount` ASC),
  INDEX `fk_CommentPost_Post1_idx` (`Post_idPost` ASC),
  PRIMARY KEY (`idComment`),
  CONSTRAINT `fk_CommentPost_Account1`
    FOREIGN KEY (`Account_idAccount`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CommentPost_Post1`
    FOREIGN KEY (`Post_idPost`)
    REFERENCES `artist`.`Post` (`idPost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`FriendsList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`FriendsList` (
  `user1` INT NOT NULL,
  `user2` INT NOT NULL,
  INDEX `fk_Connections_Account1_idx` (`user1` ASC),
  INDEX `fk_Connections_Account2_idx` (`user2` ASC),
  CONSTRAINT `fk_Connections_Account1`
    FOREIGN KEY (`user1`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Connections_Account2`
    FOREIGN KEY (`user2`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`FriendRequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`FriendRequest` (
  `senderid` INT NOT NULL,
  `receiverid` INT NOT NULL,
  `pending` TINYINT(1) NOT NULL DEFAULT 1,
  `time` DATETIME NOT NULL,
  INDEX `fk_FriendRequest_Account1_idx` (`senderid` ASC),
  INDEX `fk_FriendRequest_Account2_idx` (`receiverid` ASC),
  CONSTRAINT `fk_FriendRequest_Account1`
    FOREIGN KEY (`senderid`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FriendRequest_Account2`
    FOREIGN KEY (`receiverid`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`.`ChallengeRequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist`.`ChallengeRequest` (
  `senderid` INT NOT NULL,
  `receiverid` INT NOT NULL,
  `pending` TINYINT(1) NOT NULL DEFAULT 1,
  `time` DATETIME NOT NULL,
  INDEX `fk_ChallengeRequest_Account1_idx` (`senderid` ASC),
  INDEX `fk_ChallengeRequest_Account2_idx` (`receiverid` ASC),
  CONSTRAINT `fk_ChallengeRequest_Account1`
    FOREIGN KEY (`senderid`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ChallengeRequest_Account2`
    FOREIGN KEY (`receiverid`)
    REFERENCES `artist`.`Account` (`idAccount`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;