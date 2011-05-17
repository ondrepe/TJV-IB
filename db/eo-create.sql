SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `exchangeofficedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci ;
USE `exchangeofficedb` ;

-- -----------------------------------------------------
-- Table `exchangeofficedb`.`exchangerate`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `exchangeofficedb`.`exchangerate` (
  `currency` VARCHAR(3) NOT NULL ,
  `rate` DECIMAL(14,4) NOT NULL ,
  PRIMARY KEY (`currency`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `exchangeofficedb`.`exchangerate`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `exchangeofficedb`;
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('CZK', 1.0);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('EUR', 24.46);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('USD', 17.26);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('TRY', 10.886);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('AUD', 18.264);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('JPY', 0.2114);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('PLN', 6.226);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('RUB', 0.6127);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('CHF', 19.458);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('CNY', 2.653);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('DKK', 3.28);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('HKD', 2.22);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('HRK', 3.306);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('CAD', 17.699);
INSERT INTO `exchangeofficedb`.`exchangerate` (`currency`, `rate`) VALUES ('GBP', 28.022);

COMMIT;
