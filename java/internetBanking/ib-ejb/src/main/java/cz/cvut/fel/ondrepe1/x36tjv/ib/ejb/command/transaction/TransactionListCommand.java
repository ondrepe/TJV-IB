package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankTransactionPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class TransactionListCommand extends CommonListCommand<Transaction, BankTransactionPO> {

  public TransactionListCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected List<BankTransactionPO> execute() {
    Query query = em.createNamedQuery("BankTransactionPO.findAll");
    return query.getResultList();
  }

  @Override
  protected List<Transaction> convert(List<BankTransactionPO> listPo) {
    ArrayList<Transaction> trList = new ArrayList<Transaction>();
    
    for(BankTransactionPO trPo : listPo) {
      Transaction tr = new Transaction();
      tr.setId(trPo.getId());
      tr.setAccountFrom(trPo.getAccountNumberFrom());
      tr.setAccountTo(trPo.getAccountNumberTo());
      tr.setBankFrom(trPo.getBankFrom().getCode());
      tr.setBankTo(trPo.getBankTo().getCode());
      tr.setDescription(trPo.getDescription());
      tr.setTransactionTime(trPo.getCreationTime());
      tr.setCurrencyCodeTo(trPo.getCurrencyTo().getCode());
      tr.setCurrencyCodeFrom(trPo.getCurrencyFrom().getCode());
      BigDecimal amountTo = trPo.getAmountTo();
      tr.setAmmountTo(amountTo.setScale(trPo.getCurrencyTo().getDecimalDigits(), RoundingMode.HALF_UP));
      BigDecimal amountFrom = trPo.getAmountFrom();
      tr.setAmmountFrom(amountFrom.setScale(trPo.getCurrencyFrom().getDecimalDigits(), RoundingMode.HALF_UP));
      trList.add(tr);
    }
    
    return trList;
  }
  
}
