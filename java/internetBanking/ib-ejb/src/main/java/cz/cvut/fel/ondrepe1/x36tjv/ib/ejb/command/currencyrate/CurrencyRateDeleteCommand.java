package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteStringIdCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class CurrencyRateDeleteCommand extends DeleteStringIdCommand {

  public CurrencyRateDeleteCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void delete(String id) {
    CurrentCurrencyRatePO ccRate = em.find(CurrentCurrencyRatePO.class, id);
    CurrencyRatePO cRate = em.find(CurrencyRatePO.class, id);
    em.remove(ccRate);
    em.remove(cRate);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
  
}
