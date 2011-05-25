package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.CurrencyRateTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CurrencyRateListCommand extends ListCommand<CurrentCurrencyRatePO, CurrencyRate> {

  public CurrencyRateListCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<CurrentCurrencyRatePO> list() {
    Query query = em.createNamedQuery("CurrentCurrencyRatePO.findAll");
    return query.getResultList();
  }

  @Override
  protected List<CurrencyRate> convert(List<CurrentCurrencyRatePO> list) {
    CurrencyRateTranslator translator = new CurrencyRateTranslator();
    return translator.translateList(list);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
  
}
