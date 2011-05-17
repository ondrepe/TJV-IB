package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ITransactionBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.ABean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class TransactionA extends ABean<Transaction> {

  @EJB
  private ITransactionBean transactionBean;
  @EJB
  private IAccountBean accountBean;
  @EJB
  private ICurrencyCodeBean currencyCodeBean;
  @EJB
  private IBankCodeBean bankCodeBean;
  
  @Override
  protected void addItem(Transaction item) throws CommonIBException {
    transactionBean.transferMoney(item);
  }

  @Override
  protected Transaction initItem() {
    return new Transaction();
  }
  
  public List<Account> getAccounts() {
    return accountBean.getList();
  }
  
  public List<BankCode> getBanks() {
    return bankCodeBean.getAll();
  }
  
  public List<CurrencyCode> getCurrencies() {
    return currencyCodeBean.getAll();
  }
}
