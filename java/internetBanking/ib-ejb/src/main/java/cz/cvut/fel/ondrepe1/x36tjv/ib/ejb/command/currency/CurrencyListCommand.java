package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.CurrencyTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CurrencyListCommand extends ListCommand<CurrencyPO, CurrencyCode> {

  public CurrencyListCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<CurrencyPO> list() {
    Query query = em.createNamedQuery("CurrencyPO.findAll");
    return query.getResultList();
  }

  @Override
  protected List<CurrencyCode> convert(List<CurrencyPO> list) {
    CurrencyTranslator translator = new CurrencyTranslator();
    return translator.translateList(list);
  }

  @Override
  protected boolean authorize() {
    return isManager() || isCustomer();
  }
  
}
