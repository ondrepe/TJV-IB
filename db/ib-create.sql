SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `internetbanking` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci ;
USE `internetbanking` ;

-- -----------------------------------------------------
-- Table `internetbanking`.`bank`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`bank` (
  `code` INT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`code`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`currency`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`currency` (
  `code` VARCHAR(3) NOT NULL ,
  `name` VARCHAR(250) NOT NULL ,
  `decimalDigits` INT NOT NULL ,
  PRIMARY KEY (`code`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`customer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`customer` (
  `id` INT NOT NULL ,
  `firstName` VARCHAR(250) NOT NULL ,
  `lastName` VARCHAR(250) NOT NULL ,
  `email` VARCHAR(500) NOT NULL ,
  `valid` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`currencyrate`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`currencyrate` (
  `code` VARCHAR(3) NOT NULL ,
  `rate` DECIMAL(5,2) NOT NULL ,
  INDEX `fk_CurrencyRate_Currency1` (`code` ASC) ,
  PRIMARY KEY (`code`) ,
  CONSTRAINT `fk_CurrencyRate_Currency1`
    FOREIGN KEY (`code` )
    REFERENCES `internetbanking`.`currency` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`account`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`account` (
  `id` INT NOT NULL ,
  `accountNumber` VARCHAR(250) NOT NULL ,
  `code` VARCHAR(3) NOT NULL ,
  `balance` DECIMAL(20,2) NOT NULL ,
  `valid` VARCHAR(1) NOT NULL DEFAULT 'Y' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Account_Currency` (`code` ASC) ,
  CONSTRAINT `fk_Account_Currency`
    FOREIGN KEY (`code` )
    REFERENCES `internetbanking`.`currency` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`customeraccount`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`customeraccount` (
  `id` INT NOT NULL ,
  `idAccount` INT NOT NULL ,
  `idCustomer` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_CustomerAccount_Account1` (`idAccount` ASC) ,
  INDEX `fk_CustomerAccount_Customer1` (`idCustomer` ASC) ,
  CONSTRAINT `fk_CustomerAccount_Account1`
    FOREIGN KEY (`idAccount` )
    REFERENCES `internetbanking`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CustomerAccount_Customer1`
    FOREIGN KEY (`idCustomer` )
    REFERENCES `internetbanking`.`customer` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`banktransaction`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`banktransaction` (
  `id` INT NOT NULL ,
  `accountFrom` VARCHAR(250) NOT NULL ,
  `bankFrom` INT NOT NULL ,
  `idAccountFrom` INT NULL ,
  `accountTo` VARCHAR(250) NOT NULL ,
  `bankTo` INT NOT NULL ,
  `idAccountTo` INT NULL ,
  `amountFrom` DECIMAL(20,2) NOT NULL ,
  `currencyFrom` VARCHAR(3) NOT NULL ,
  `amountTo` DECIMAL(20,2) NOT NULL ,
  `currencyTo` VARCHAR(3) NOT NULL ,
  `description` VARCHAR(250) NOT NULL ,
  `creationTime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_BankTransaction_Bank1` (`bankFrom` ASC) ,
  INDEX `fk_BankTransaction_Bank2` (`bankTo` ASC) ,
  INDEX `fk_BankTransaction_Currency1` (`currencyFrom` ASC) ,
  INDEX `fk_BankTransaction_Currency2` (`currencyTo` ASC) ,
  INDEX `fk_BankTransaction_Account1` (`idAccountFrom` ASC) ,
  INDEX `fk_BankTransaction_Account2` (`idAccountTo` ASC) ,
  CONSTRAINT `fk_BankTransaction_Bank1`
    FOREIGN KEY (`bankFrom` )
    REFERENCES `internetbanking`.`bank` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankTransaction_Bank2`
    FOREIGN KEY (`bankTo` )
    REFERENCES `internetbanking`.`bank` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankTransaction_Currency1`
    FOREIGN KEY (`currencyFrom` )
    REFERENCES `internetbanking`.`currency` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankTransaction_Currency2`
    FOREIGN KEY (`currencyTo` )
    REFERENCES `internetbanking`.`currency` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankTransaction_Account1`
    FOREIGN KEY (`idAccountFrom` )
    REFERENCES `internetbanking`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankTransaction_Account2`
    FOREIGN KEY (`idAccountTo` )
    REFERENCES `internetbanking`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `internetbanking`.`currentcurrencyrate`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `internetbanking`.`currentcurrencyrate` (
  `code` VARCHAR(3) NOT NULL ,
  `rate` DECIMAL(5,2) NOT NULL ,
  PRIMARY KEY (`code`) ,
  CONSTRAINT `fk_CurrentCurrencyRate_CurrencyRate1`
    FOREIGN KEY (`code` )
    REFERENCES `internetbanking`.`currencyrate` (`code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
