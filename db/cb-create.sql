SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `centralbankdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci ;
USE `centralbankdb` ;

-- -----------------------------------------------------
-- Table `centralbankdb`.`transfer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `centralbankdb`.`transfer` (
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
-- Table `centralbankdb`.`idtable`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `centralbankdb`.`idtable` (
  `name` VARCHAR(50) NOT NULL ,
  `val` INT NOT NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `centralbankdb`.`idtable`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `centralbankdb`;
INSERT INTO `centralbankdb`.`idtable` (`name`, `val`) VALUES ('transaction', 10000);

COMMIT;
