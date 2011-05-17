package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GlobalParamCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.ChangeBalanceCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transfer.ChangeBalanceEnum;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.ws.client.CentralBankClient;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.math.MathContext;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class TransactionTransferMoneyCommand extends CommonSetCommand<Transaction> {

  private boolean mypay;
  private BankPO myBank;
  private BankPO bankTo;
  private AccountPO accountTo;
  private AccountPO accountFrom;

  public TransactionTransferMoneyCommand(EntityManager em) {
    super(em);
  }

  @Override
  public void execute(Transaction trn) {
    Query query = em.createNamedQuery("AccountPO.findByAccNum");
    query.setParameter("accountNumber", trn.getAccountFrom());
    accountFrom = (AccountPO) query.getSingleResult();
    ChangeBalanceCommand cbCommand = new ChangeBalanceCommand(em);
    CurrencyPO curr = em.find(CurrencyPO.class, trn.getCurrencyCode());

    BankTransactionPO trPo = new BankTransactionPO();
    trPo.setAccountFrom(accountFrom);
    trPo.setAccountNumberFrom(accountFrom.getAccountNumber());
    trPo.setBankFrom(myBank);
    trPo.setCreationTime(new Date());
    trPo.setCurrencyFrom(accountFrom.getCurrency());
    trPo.setDescription(trn.getDescription());
    trPo.setAmountFrom(cbCommand.changeBalance(accountFrom, curr, trn.getAmmountTo(), ChangeBalanceEnum.OUTPUT));
    accountFrom.setBalance(accountFrom.getBalance().add(trPo.getAmountFrom(), new MathContext(accountFrom.getCurrency().getDecimalDigits())));
    //TODO: kontola jestli neni accountFrom v minusu
    if (mypay) {
      trPo.setBankTo(myBank);
      trPo.setAccountTo(accountTo);
      trPo.setAccountNumberTo(accountTo.getAccountNumber());
      trPo.setAmountTo(cbCommand.changeBalance(accountTo, curr, trn.getAmmountTo(), ChangeBalanceEnum.INPUT));
      accountTo.setBalance(accountTo.getBalance().add(trPo.getAmountTo(), new MathContext(accountTo.getCurrency().getDecimalDigits())));
      trPo.setCurrencyTo(accountTo.getCurrency());
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
    if(mypay) {
      em.persist(accountTo);
    }
  }

  @Override
  protected void validate(Transaction object) throws CommonIBException {
    GlobalParamCommand gp = new GlobalParamCommand(em);
    int myBankCode = gp.getNumberParam("MYBANKCODE");
    myBank = em.find(BankPO.class, myBankCode);

    if (object.getBankTo() == myBankCode) {
      mypay = true;
      // kontrola jestli existuje cislo uctu
      Query query = em.createNamedQuery("AccountPO.findByAccNum");
      query.setParameter("accountNumber", object.getAccountTo());
      accountTo = (AccountPO) query.getSingleResult();
      bankTo = myBank;
    } else {
      mypay = false;
      bankTo = em.find(BankPO.class, object.getBankTo());
    }
  }
}
