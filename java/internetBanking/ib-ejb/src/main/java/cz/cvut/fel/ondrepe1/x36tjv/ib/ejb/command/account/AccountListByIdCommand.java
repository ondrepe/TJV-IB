package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class AccountListByIdCommand extends CommonListByIdCommand<Account, AccountPO> {

  public AccountListByIdCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected List<AccountPO> execute(int id) {
    CustomerPO cPo = em.find(CustomerPO.class, id);
    return cPo.getAccounts();
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
  
}
