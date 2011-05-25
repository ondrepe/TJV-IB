package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class AccountListCommand extends ListCommand<AccountPO, Account> {

  public AccountListCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<AccountPO> list() {
    List<AccountPO> list;
    CustomerPO custPo = getCustomer();

    if(custPo != null) {
      list = custPo.getAccounts();
    } else {
      Query query = em.createNamedQuery("AccountPO.findByValid");
      query.setParameter("valid", "Y");
      list = query.getResultList();
    }
    return list;
  }

  @Override
  protected List<Account> convert(List<AccountPO> listPo) {
    List<Account> list = new ArrayList<Account>();
    for(AccountPO accPO : listPo) {
      Account acc = new Account();
      acc.setId(accPO.getId());
      acc.setCurrencyCode(accPO.getCurrency().getCode());
      acc.setAccountNumber(accPO.getAccountNumber());
      acc.setBalance(accPO.getBalance());
      list.add(acc);
    }
    return list;
  }

  @Override
  protected boolean authorize() {
    return isCustomer() || isManager();
  }
  
}
