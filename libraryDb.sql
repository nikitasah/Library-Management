-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET latin1 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book` (
  `isbn10` VARCHAR(12) NOT NULL,
  `isbn13` VARCHAR(15) NOT NULL,
  `title` VARCHAR(500) NOT NULL,
  `cover` VARCHAR(100) NULL DEFAULT NULL,
  `publisher` VARCHAR(100) NULL DEFAULT NULL,
  `pages` INT(5) NULL DEFAULT NULL,
  `no_of_copies_available` INT(3) NULL DEFAULT '1',
  PRIMARY KEY (`isbn13`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `library`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`author` (
  `author_name` VARCHAR(200) NOT NULL,
  `isbn13` VARCHAR(15) NOT NULL,
  INDEX `ISBN13_idx` (`isbn13` ASC),
  CONSTRAINT `author_ISBN13`
    FOREIGN KEY (`isbn13`)
    REFERENCES `library`.`book` (`isbn13`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `library`.`borrowers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`borrowers` (
  `card_id` VARCHAR(10) NOT NULL,
  `ssn` CHAR(15) NOT NULL,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `address` MEDIUMTEXT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(15) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`card_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `library`.`loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`loan` (
  `loan_id` INT(6) NOT NULL AUTO_INCREMENT,
  `isbn13` VARCHAR(15) NOT NULL,
  `card_id` VARCHAR(10) NOT NULL,
  `date_out` DATE NULL DEFAULT NULL,
  `due_date` DATE NULL DEFAULT NULL,
  `date_in` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  INDEX `ISBN13_idx` (`isbn13` ASC),
  INDEX `card_id_idx` (`card_id` ASC),
  CONSTRAINT `card_id`
    FOREIGN KEY (`card_id`)
    REFERENCES `library`.`borrowers` (`card_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `loan_ISBN13`
    FOREIGN KEY (`isbn13`)
    REFERENCES `library`.`book` (`isbn13`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `library`.`fine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`fine` (
  `loan_id` INT(6) NOT NULL,
  `fine_amt` DECIMAL(10,2) NOT NULL,
  `paid` TINYINT(4) NULL DEFAULT '0',
  PRIMARY KEY (`loan_id`),
  CONSTRAINT `loan_id`
    FOREIGN KEY (`loan_id`)
    REFERENCES `library`.`loan` (`loan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
