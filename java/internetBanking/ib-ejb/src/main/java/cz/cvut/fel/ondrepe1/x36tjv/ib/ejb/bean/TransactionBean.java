package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction.TransactionListByIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction.TransactionListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.transaction.TransactionTransferMoneyCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ITransactionBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Transaction;
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
public class TransactionBean implements ITransactionBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;
  
  @Override
  @RolesAllowed({"MANAGER"})
  public List<Transaction> getList() {
    CommonListCommand command = new TransactionListCommand(em);
    return command.list();
  }

  @Override
  @RolesAllowed({"MANAGER", "CUSTOMER"})
  public List<Transaction> getListByAccountId(int i) {
    CommonListByIdCommand command = new TransactionListByIdCommand(em, ctx);
    return command.listById(i);
  }

  @Override
  @RolesAllowed({"CUSTOMER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void transferMoney(Transaction trnsfr) throws CommonIBException {
    CommonSetCommand<Transaction> command = new TransactionTransferMoneyCommand(em);
    command.set(trnsfr);
  }
  
}
