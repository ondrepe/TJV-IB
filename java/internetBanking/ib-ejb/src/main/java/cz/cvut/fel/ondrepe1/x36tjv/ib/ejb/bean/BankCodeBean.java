package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank.BankDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank.BankListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank.BankListWithoutMyBankCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank.BankSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
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
public class BankCodeBean implements IBankCodeBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;

  @Override
  @RolesAllowed({"MANAGER", "CUSTOMER"})
  public List<BankCode> getList() {
    ListCommand command = new BankListCommand(em, ctx);
    return command.execute();
  }
  
  @Override
  @RolesAllowed({"MANAGER"})
  public List<BankCode> getListWithoutMyBank() {
    ListCommand command = new BankListWithoutMyBankCommand(em, ctx);
    return command.execute();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  @RolesAllowed({"MANAGER"})
  public void delete(int i) {
    DeleteCommand command = new BankDeleteCommand(em, ctx);
    command.execute(i);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  @RolesAllowed({"MANAGER"})
  public void set(BankCode bc) {
    SetCommand command = new BankSetCommand(em, ctx);
    command.execute(bc);
  }
  
}
