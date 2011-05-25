package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteStringIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate.CurrencyRateDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate.CurrencyRateListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate.CurrencyRateSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyRateBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
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
public class CurrencyRateBean implements ICurrencyRateBean {

  @PersistenceContext
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;
  
  @Override
  @RolesAllowed({"MANAGER"})
  public List<CurrencyRate> getList() {
    ListCommand command = new CurrencyRateListCommand(em, ctx);
    return command.execute();
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void delete(String string) {
    DeleteStringIdCommand command = new CurrencyRateDeleteCommand(em, ctx);
    command.execute(string);
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void set(CurrencyRate cr) {
    SetCommand command = new CurrencyRateSetCommand(em, ctx);
    command.execute(cr);
  }
  
}
