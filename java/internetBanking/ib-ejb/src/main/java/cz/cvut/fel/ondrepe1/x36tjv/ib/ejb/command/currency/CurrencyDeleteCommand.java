package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteStringIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class CurrencyDeleteCommand extends DeleteStringIdCommand {

  public CurrencyDeleteCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void delete(String id) {
    CurrencyPO currency = em.find(CurrencyPO.class, id.toUpperCase());
    em.remove(currency);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
  
}
