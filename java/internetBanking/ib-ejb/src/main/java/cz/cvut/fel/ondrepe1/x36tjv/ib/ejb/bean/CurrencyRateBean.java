package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrentCurrencyRatePO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyRateBean;
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
  public void delete(String string) {
    CurrentCurrencyRatePO ccRate = em.find(CurrentCurrencyRatePO.class, string);
    CurrencyRatePO cRate = em.find(CurrencyRatePO.class, string);
    em.remove(ccRate);
    em.remove(cRate);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void set(CurrencyRate cr) {
    CurrentCurrencyRatePO ccRate = em.find(CurrentCurrencyRatePO.class, cr.getCode());
    CurrencyRatePO cRate;
    String code = cr.getCode().trim().toUpperCase();
    BigDecimal rate = new BigDecimal(cr.getRate());
    if(ccRate == null) {
      ccRate = new CurrentCurrencyRatePO(code, rate);
      cRate = new CurrencyRatePO(code, rate);
      em.persist(cRate);
    } else {
      ccRate.setRate(rate);
    }
    em.persist(ccRate);
  }
  
}
