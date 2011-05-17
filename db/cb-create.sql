SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `CentralBankDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci ;
USE `CentralBankDB` ;

-- -----------------------------------------------------
-- Table `CentralBankDB`.`transfer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `CentralBankDB`.`transfer` (
  `idTransfer` INT NOT NULL ,
  `bankFrom` VARCHAR(45) NOT NULL ,
  `accountFrom` VARCHAR(250) NOT NULL ,
  `bankTo` INT NOT NULL ,
  `accountTo` VARCHAR(250) NOT NULL ,
  `amount` DECIMAL(20,2) NOT NULL ,
  `currency` VARCHAR(3) NOT NULL ,
  `description` VARCHAR(250) NOT NULL ,
  `transferTime` DATETIME NOT NULL ,
  PRIMARY KEY (`idTransfer`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_czech_ci;


-- -----------------------------------------------------
-- Table `CentralBankDB`.`idtable`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `CentralBankDB`.`idtable` (
  `name` VARCHAR(50) NOT NULL ,
  `val` INT NOT NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `CentralBankDB`.`idtable`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `CentralBankDB`;
INSERT INTO `CentralBankDB`.`idtable` (`name`, `val`) VALUES ('transaction', 10000);

COMMIT;
