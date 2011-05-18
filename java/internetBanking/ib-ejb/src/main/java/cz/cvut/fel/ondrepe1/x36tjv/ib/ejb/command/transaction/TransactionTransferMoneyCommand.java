package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GlobalParamCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.GetAmountCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.GetAmountEnum;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client.CentralBankClient;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.ValidationIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class TransactionTransferMoneyCommand extends CommonSetCommand<Transaction> {

  private boolean mypay;
  private BankPO bankFrom;
  private BankPO bankTo;
  private AccountPO accountTo;
  private AccountPO accountFrom;
  private CurrencyPO curr;

  public TransactionTransferMoneyCommand(EntityManager em) {
    super(em);
  }

  @Override
  public void execute(Transaction trn) throws CommonIBException {
    GetAmountCommand cbCommand = new GetAmountCommand(em);

    BankTransactionPO trPo = new BankTransactionPO();
    trPo.setAccountFrom(accountFrom);
    trPo.setAccountNumberFrom(accountFrom.getAccountNumber());
    trPo.setBankFrom(bankFrom);
    trPo.setCreationTime(new Date());
    trPo.setCurrencyFrom(accountFrom.getCurrency());
    trPo.setDescription(trn.getDescription());
    
    BigDecimal amountFrom = cbCommand.getAmount(accountFrom, curr, trn.getAmmountTo(), GetAmountEnum.OUTPUT);
    trPo.setAmountFrom(amountFrom);
    
    BigDecimal actualBalance = accountFrom.getBalance().add(trPo.getAmountFrom(), new MathContext(24, RoundingMode.HALF_UP));
    actualBalance = actualBalance.setScale(accountFrom.getCurrency().getDecimalDigits(), RoundingMode.HALF_UP);
    
    if(actualBalance.doubleValue() < 0) {
      throw new ValidationIBException("AccountFrom does not have enough money!");
    }
    accountFrom.setBalance(actualBalance);
    
    if (mypay) {
      trPo.setBankTo(bankFrom);
      trPo.setAccountTo(accountTo);
      trPo.setAccountNumberTo(accountTo.getAccountNumber());
      trPo.setCurrencyTo(accountTo.getCurrency());
      
      BigDecimal amountTo = cbCommand.getAmount(accountTo, curr, trn.getAmmountTo(), GetAmountEnum.INPUT);
      trPo.setAmountTo(amountTo);
      
      BigDecimal newBalance = accountTo.getBalance().add(trPo.getAmountTo(), new MathContext(24, RoundingMode.HALF_UP));
      newBalance = newBalance.setScale(accountTo.getCurrency().getDecimalDigits(), RoundingMode.HALF_UP);
      accountTo.setBalance(newBalance);
    } else {
      trPo.setBankTo(bankTo);
      trPo.setAccountNumberTo(trn.getAccountTo());
      trPo.setAmountTo(trn.getAmmountTo());
      trPo.setCurrencyTo(curr);
      
      CentralBankClient cbClient = new CentralBankClient(em);
      cbClient.transfer(trPo);
    }
    em.persist(trPo);
    em.persist(accountFrom);
    if (mypay) {
      em.persist(accountTo);
    }
  }

  @Override
  protected void validate(Transaction object) throws CommonIBException {
    
    if(object != null) {
      // validace account from
      if(object.getAccountFrom() == null || object.getAccountFrom().trim().isEmpty()) {
        throw new ValidationIBException("AccountFrom is not seted!");
      } else {
        object.setAccountFrom(object.getAccountFrom().trim());
      }
      // validace account to
      if(object.getAccountTo() == null || object.getAccountTo().trim().isEmpty()) {
        throw new ValidationIBException("AccountTo is not seted!");
      } else {
        object.setAccountTo(object.getAccountTo().trim());
      }
      // validace bank to
      if(object.getBankTo() == null) {
        throw new ValidationIBException("BankTo is not seted!");
      }
      // validace account to
      if(object.getCurrencyCodeTo() == null || object.getCurrencyCodeTo().trim().isEmpty()) {
        throw new ValidationIBException("AccountTo is not seted!");
      } else {
        object.setCurrencyCodeTo(object.getCurrencyCodeTo().trim());
        curr = em.find(CurrencyPO.class, object.getCurrencyCodeTo());
        if(curr == null) {
          throw new ValidationIBException("Currency " + object.getCurrencyCodeTo() + " does not exist!");
        }
      }
      // validace bank to
      if(object.getAmmountTo() == null) {
        throw new ValidationIBException("Ammount is not seted!");
      } else if(object.getAmmountTo().setScale(curr.getDecimalDigits(), RoundingMode.HALF_UP).doubleValue() <= 0) {
        throw new ValidationIBException("Ammount is less or equal 0!");
      } else {
        object.setAmmountTo(object.getAmmountTo().setScale(4, RoundingMode.HALF_UP));
      }
      
      if(object.getTransactionTime() == null) {
        object.setTransactionTime(new Date());
      }
    } else {
      throw new ValidationIBException("Transaction object does not exist!");
    }
    this.initData(object);
  }
  
  /**
   * Inicializace tridnich promennych.
   * 
   * @param object transackcni objekt
   * @throws CommonIBException 
   */
  private void initData(Transaction object) throws CommonIBException {
    this.setAccountFrom(object);
    int myBankCode = this.setBankFrom();

    if (object.getBankTo() == myBankCode) {
      mypay = true;
      this.setAccountTo(object);
    } else {
      mypay = false;
    }
    
    this.setBankTo(mypay, object);
  }

  /**
   * Nastavi odchoziho ucetu do tridni promeny
   * 
   * @param trn transakcni objekt
   * @throws CommonIBException ucet neeexistuje
   */
  private void setAccountFrom(Transaction trn) throws CommonIBException {
    try {
      Query query = em.createNamedQuery("AccountPO.findByAccNum");
      query.setParameter("accountNumber", trn.getAccountFrom());
      query.setParameter("valid", "Y");
      accountFrom = (AccountPO) query.getSingleResult();
    } catch (Exception ex) {
      throw new ValidationIBException("AccountFrom " + trn.getAccountFrom() + " does not exist!", ex);
    }
  }

  /**
   * Pokud se jedna o domaci platbu tak se nastavi prichozi ucet do tridni promeny
   * 
   * @param trn transakcni objekt
   * @throws CommonIBException ucet neeexistuje
   */
  private void setAccountTo(Transaction trn) throws CommonIBException {
    try {
      Query query = em.createNamedQuery("AccountPO.findByAccNum");
      query.setParameter("accountNumber", trn.getAccountTo());
      query.setParameter("valid", "Y");
      accountTo = (AccountPO) query.getSingleResult();
    } catch (Exception ex) {
      throw new ValidationIBException("AccountTo " + trn.getAccountFrom() + " does not exist in this bank!", ex);
    }
  }

  /**
   * Metoda pro ziskani kodu domaci banky a nastevni banky do tridni promeny.
   * 
   * @return kod domaci banky
   * @throws CommonIBException banka neexistuje 
   */
  private int setBankFrom() throws CommonIBException {
    GlobalParamCommand gp = new GlobalParamCommand(em);
    int myBankCode = gp.getNumberParam("MYBANKCODE");
    bankFrom = em.find(BankPO.class, myBankCode);
    if (bankFrom == null) {
      throw new ValidationIBException("Bank " + myBankCode + " does not exist!");
    }
    return myBankCode;
  }

  /**
   * Metoda pro nastevni prichozi banky do tridni promeny.
   * 
   * @param mypay jestli se jedna o platbu v ramci banky
   * @param object transakcni object
   * @throws CommonIBException banka neexistuje
   */
  private void setBankTo(boolean mypay, Transaction object) throws CommonIBException {
    if (mypay) {
      bankTo = em.find(BankPO.class, object.getBankTo());
      if (bankTo == null) {
        throw new ValidationIBException("Bank " + object.getBankTo() + " does not exist!");
      }
    } else {
      bankTo = bankFrom;
    }
  }
}
