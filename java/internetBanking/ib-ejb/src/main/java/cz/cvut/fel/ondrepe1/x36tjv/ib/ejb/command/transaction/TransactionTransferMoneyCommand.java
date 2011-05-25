package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GlobalParamCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.AccountTransferMoneyCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.TransferEnum;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client.CentralBankClient;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class TransactionTransferMoneyCommand extends SetCommand<Transaction> {

  private boolean mypay;
  private BankPO bankFrom;
  private BankPO bankTo;
  private AccountPO accountTo;
  private AccountPO accountFrom;
  private CurrencyPO curr;

  public TransactionTransferMoneyCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  public void set(Transaction trn) {
    AccountTransferMoneyCommand trCommand = new AccountTransferMoneyCommand(em);

    BankTransactionPO trPo = new BankTransactionPO();
    trPo.setAccountFrom(accountFrom);
    trPo.setAccountNumberFrom(accountFrom.getAccountNumber());
    trPo.setBankFrom(bankFrom);
    trPo.setCreationTime(new Date());
    trPo.setCurrencyFrom(accountFrom.getCurrency());
    trPo.setDescription(trn.getDescription());
    trPo.setAmountTo(trn.getAmmountTo());
    trPo.setCurrencyTo(curr);

    if (mypay) {
      trPo.setBankTo(bankFrom);
      trPo.setAccountTo(accountTo);
      trPo.setAccountNumberTo(accountTo.getAccountNumber());
    } else {
      trPo.setBankTo(bankTo);
      trPo.setAccountNumberTo(trn.getAccountTo());
    }

    BigDecimal amountFrom = trCommand.transferMoney(accountFrom, trn.getAmmountTo(), curr, TransferEnum.OUTPUT);
    trPo.setAmountFrom(amountFrom);
    em.persist(trPo);
    if (mypay) {
      trCommand.transferMoney(accountTo, trn.getAmmountTo(), curr, TransferEnum.INPUT);
    } else {
      CentralBankClient cbClient = new CentralBankClient(em);
      cbClient.transfer(trPo);
    }
  }

  @Override
  protected void validate(Transaction object) {

    if (object != null) {
      // validace account from
      if (object.getAccountFrom() == null || object.getAccountFrom().trim().isEmpty()) {
        throw new IBException("AccountFrom is not seted!", IBExceptionCode.VALIDATION_FAILED);
      } else {
        object.setAccountFrom(object.getAccountFrom().trim());
      }
      // validace account to
      if (object.getAccountTo() == null || object.getAccountTo().trim().isEmpty()) {
        throw new IBException("AccountTo is not seted!", IBExceptionCode.VALIDATION_FAILED);
      } else {
        object.setAccountTo(object.getAccountTo().trim());
      }
      // validace bank to
      if (object.getBankTo() == null) {
        throw new IBException("BankTo is not seted!", IBExceptionCode.VALIDATION_FAILED);
      }
      // validace account to
      if (object.getCurrencyCodeTo() == null || object.getCurrencyCodeTo().trim().isEmpty()) {
        throw new IBException("AccountTo is not seted!", IBExceptionCode.VALIDATION_FAILED);
      } else {
        object.setCurrencyCodeTo(object.getCurrencyCodeTo().trim());
        curr = em.find(CurrencyPO.class, object.getCurrencyCodeTo());
        if (curr == null) {
          throw new IBException("Currency " + object.getCurrencyCodeTo() + " does not exist!", IBExceptionCode.VALIDATION_FAILED);
        }
      }
      // validace bank to
      if (object.getAmmountTo() == null) {
        throw new IBException("Ammount is not seted!", IBExceptionCode.VALIDATION_FAILED);
      } else if (object.getAmmountTo().setScale(curr.getDecimalDigits(), RoundingMode.HALF_UP).doubleValue() <= 0) {
        throw new IBException("Ammount is less or equal 0!", IBExceptionCode.VALIDATION_FAILED);
      } else {
        object.setAmmountTo(object.getAmmountTo().setScale(4, RoundingMode.HALF_UP));
      }

      if (object.getTransactionTime() == null) {
        object.setTransactionTime(new Date());
      }
    } else {
      throw new IBException("Transaction object does not exist!", IBExceptionCode.VALIDATION_FAILED);
    }
    this.initData(object);
  }

  /**
   * Inicializace tridnich promennych.
   * 
   * @param object transackcni objekt
   */
  private void initData(Transaction object) {
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
   */
  private void setAccountFrom(Transaction trn) {
    try {
      Query query = em.createNamedQuery("AccountPO.findByAccNum");
      query.setParameter("accountNumber", trn.getAccountFrom());
      query.setParameter("valid", "Y");
      accountFrom = (AccountPO) query.getSingleResult();
    } catch (Exception ex) {
      throw new IBException("AccountFrom " + trn.getAccountFrom() + " does not exist!", IBExceptionCode.VALIDATION_FAILED, ex);
    }
  }

  /**
   * Pokud se jedna o domaci platbu tak se nastavi prichozi ucet do tridni promeny
   * 
   * @param trn transakcni objekt
   */
  private void setAccountTo(Transaction trn) {
    try {
      Query query = em.createNamedQuery("AccountPO.findByAccNum");
      query.setParameter("accountNumber", trn.getAccountTo());
      query.setParameter("valid", "Y");
      accountTo = (AccountPO) query.getSingleResult();
    } catch (Exception ex) {
      throw new IBException("AccountTo " + trn.getAccountFrom() + " does not exist in this bank!", IBExceptionCode.VALIDATION_FAILED, ex);
    }
  }

  /**
   * Metoda pro ziskani kodu domaci banky a nastevni banky do tridni promeny.
   * 
   * @return kod domaci banky
   */
  private int setBankFrom() {
    GlobalParamCommand gp = new GlobalParamCommand(em);
    int myBankCode = gp.getNumberParam("MYBANKCODE");
    bankFrom = em.find(BankPO.class, myBankCode);
    if (bankFrom == null) {
      throw new IBException("Bank " + myBankCode + " does not exist!", IBExceptionCode.VALIDATION_FAILED);
    }
    return myBankCode;
  }

  /**
   * Metoda pro nastevni prichozi banky do tridni promeny.
   * 
   * @param mypay jestli se jedna o platbu v ramci banky
   * @param object transakcni object
   */
  private void setBankTo(boolean mypay, Transaction object) {
    if (!mypay) {
      bankTo = em.find(BankPO.class, object.getBankTo());
      if (bankTo == null) {
        throw new IBException("Bank " + object.getBankTo() + " does not exist!", IBExceptionCode.VALIDATION_FAILED);
      }
    } else {
      bankTo = bankFrom;
    }
  }

  @Override
  protected boolean authorize() {
    return isCustomer();
  }
}
