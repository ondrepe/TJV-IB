package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account.AccountListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account.AccountListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.account.AccountSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAccountBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Account;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ondrepe
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountBean implements IAccountBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public List<Account> getList() {
    CommonListCommand command = new AccountListCommand(em);
    return command.list();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void add(Account acc) throws CommonIBException {
    CommonSetCommand command = new AccountSetCommand(em);
    command.set(acc);
  }

  @Override
  public List<Account> getListByCustomerId(int customerId) {
    CommonListByIdCommand command = new AccountListByIdCommand(em);
    return command.listById(customerId);
  }
  
}
