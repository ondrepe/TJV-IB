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
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
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
@DeclareRoles({"MANAGER", "CUSTOMER"})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountBean implements IAccountBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;

  @Override
  @RolesAllowed({"MANAGER", "CUSTOMER"})
  public List<Account> getList() {
    CommonListCommand command = new AccountListCommand(em, ctx);
    return command.list();
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void add(Account acc) throws CommonIBException {
    CommonSetCommand command = new AccountSetCommand(em);
    command.set(acc);
  }

  @Override
  @RolesAllowed({"MANAGER"})
  public List<Account> getListByCustomerId(int customerId) {
    CommonListByIdCommand command = new AccountListByIdCommand(em);
    return command.listById(customerId);
  }
}
