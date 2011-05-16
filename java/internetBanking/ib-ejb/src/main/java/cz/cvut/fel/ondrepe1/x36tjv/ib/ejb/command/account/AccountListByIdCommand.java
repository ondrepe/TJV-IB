package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    Query query = em.createNamedQuery("CustomerAccountPO.findAllAccByCustomer");
    query.setParameter("customerId", id);
    query.setParameter("valid", "Y");
    return query.getResultList();
  }

  @Override
  protected List<Account> convert(List<AccountPO> listPo) {
    List<Account> list = new ArrayList<Account>();
    for(AccountPO accPO : listPo) {
      Account acc = new Account();
      acc.setId(accPO.getId());
      acc.setCurrencyCode(accPO.getCode().getCode());
      acc.setBalance(accPO.getBalance());
      list.add(acc);
    }
    return list;
  }
  
}
