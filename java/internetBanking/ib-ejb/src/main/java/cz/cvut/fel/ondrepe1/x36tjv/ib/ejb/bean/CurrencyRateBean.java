package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currencyrate.CurrencyRateSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyRateBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyRate;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CurrencyRateBean implements ICurrencyRateBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public List<CurrencyRate> getAll() {
    Query query = em.createNamedQuery("CurrentCurrencyRatePO.findAll");
    return query.getResultList();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void delete(String string) throws CommonIBException {
    CurrentCurrencyRatePO ccRate = em.find(CurrentCurrencyRatePO.class, string);
    CurrencyRatePO cRate = em.find(CurrencyRatePO.class, string);
    em.remove(ccRate);
    em.remove(cRate);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void set(CurrencyRate cr) throws CommonIBException {
    CommonSetCommand command = new CurrencyRateSetCommand(em);
    command.set(cr);
  }
  
}
