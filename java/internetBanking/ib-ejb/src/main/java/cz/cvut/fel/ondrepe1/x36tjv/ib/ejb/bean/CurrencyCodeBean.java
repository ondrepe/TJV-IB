package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteStringIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency.CurrencyDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency.CurrencyListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency.CurrencySetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
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
public class CurrencyCodeBean implements ICurrencyCodeBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;

  @Override
  @RolesAllowed({"MANAGER", "CUSTOMER"})
  public List<CurrencyCode> getList() {
    ListCommand command = new CurrencyListCommand(em, ctx);
    return command.execute();
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void delete(String code) {
    DeleteStringIdCommand command = new CurrencyDeleteCommand(em, ctx);
    command.execute(code);
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void set(CurrencyCode cc) {
    SetCommand command = new CurrencySetCommand(em, ctx);
    command.execute(cc);
  }
  
}
