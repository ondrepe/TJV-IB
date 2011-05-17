package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyRateBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
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
public class AccountA extends ABean<Account> {

  @EJB
  private IAccountBean accountBean;
  @EJB
  private ICustomerBean customerBean;
  @EJB
  private ICurrencyRateBean currencyRateBean;
  
  @Override
  protected void addItem(Account item) throws CommonIBException {
    accountBean.add(item);
  }

  @Override
  protected Account initItem() {
    return new Account();
  }
  
  public List<Customer> getCustomers() {
    return customerBean.getList();
  }
  
  public List<CurrencyRate> getCurrency() {
    return currencyRateBean.getAll();
  }
  
}
