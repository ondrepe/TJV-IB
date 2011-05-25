package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.authorization.AuthorizationAccountCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.authorization.IAuthorization;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class TransactionListByIdCommand extends ListByIdCommand<BankTransactionPO, Transaction> {

  public TransactionListByIdCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<BankTransactionPO> list(int id) {
    List<BankTransactionPO> list = null;
    boolean isAuthorized = false;
    IAuthorization command = new AuthorizationAccountCommand(em, ctx);
    isAuthorized = isManager() ? true : command.authorize(id);
    
    if (isAuthorized) {
      Query query = em.createNamedQuery("BankTransactionPO.findById2");
      query.setParameter("id", id);
      list = query.getResultList();
    }
    return list;
  }

  @Override
  protected ArrayList<Transaction> convert(List<BankTransactionPO> listPo) {
    ArrayList<Transaction> trList = new ArrayList<Transaction>();

    for (BankTransactionPO trPo : listPo) {
      Transaction tr = new Transaction();
      tr.setId(trPo.getId());
      tr.setAccountFrom(trPo.getAccountNumberFrom());
      tr.setAccountTo(trPo.getAccountNumberTo());
      tr.setBankFrom(trPo.getBankFrom().getCode());
      tr.setBankTo(trPo.getBankTo().getCode());
      tr.setDescription(trPo.getDescription());
      tr.setTransactionTime(trPo.getCreationTime());
      tr.setCurrencyCodeFrom(trPo.getCurrencyFrom().getCode());
      BigDecimal amountFrom = trPo.getAmountFrom();
      tr.setAmmountFrom(amountFrom.setScale(trPo.getCurrencyFrom().getDecimalDigits(), RoundingMode.HALF_UP));
      tr.setCurrencyCodeTo(trPo.getCurrencyTo().getCode());
      BigDecimal amountTo = trPo.getAmountTo();
      tr.setAmmountTo(amountTo.setScale(trPo.getCurrencyTo().getDecimalDigits(), RoundingMode.HALF_UP));

      trList.add(tr);
    }

    return trList;
  }

  @Override
  protected boolean authorize() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
