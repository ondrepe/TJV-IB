package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.transaction;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ITransactionBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
import cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.common.CommonAddBean;
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
public class TransactionAddBean extends CommonAddBean<Transaction> {

  @EJB
  private ITransactionBean transactionBean;
  @EJB
  private IAccountBean accountBean;
  @EJB
  private ICurrencyCodeBean currencyCodeBean;
  @EJB
  private IBankCodeBean bankCodeBean;
  private List<Account> accounts;
  private List<BankCode> banks;
  private List<CurrencyCode> currencies;
  private boolean renderSend;

  @Override
  protected void customInit() {
    accounts = accountBean.getList();
    if (accounts != null && !accounts.isEmpty()) {
      banks = bankCodeBean.getList();
      currencies = currencyCodeBean.getList();
      renderSend = true;
    } else {
      renderSend = false;
    }
  }

  @Override
  protected void addItem(Transaction item) {
    transactionBean.transferMoney(item);
  }

  @Override
  protected Transaction initItem() {
    return new Transaction();
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public List<BankCode> getBanks() {
    return banks;
  }

  public List<CurrencyCode> getCurrencies() {
    return currencies;
  }

  public boolean isRenderSend() {
    return renderSend;
  }
}
