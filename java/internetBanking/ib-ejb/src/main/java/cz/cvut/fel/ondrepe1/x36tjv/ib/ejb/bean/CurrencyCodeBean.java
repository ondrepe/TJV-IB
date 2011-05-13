package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CurrencyCode;
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
public class CurrencyCodeBean implements ICurrencyCodeBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public boolean existCurrencyCode(String code) {
    CurrencyPO currency = em.find(CurrencyPO.class, code.toUpperCase());
    if(currency != null) {
      return true;
    }
    return false;
  }

  @Override
  public boolean existCurrencyName(String string) {
    Query query = em.createNamedQuery("CurrencyPO.findByName").setParameter("name", string);
    List<CurrencyCode> currencies = query.getResultList();
    if(currencies != null && !currencies.isEmpty()) {
      return true;
    }
    return false;
  }

  @Override
  public List<CurrencyCode> getAll() {
    Query query = em.createNamedQuery("CurrencyPO.findAll");
    return query.getResultList();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void delete(String code) {
    CurrencyPO currency = em.find(CurrencyPO.class, code.toUpperCase());
    em.remove(currency);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void set(CurrencyCode cc) {
    CurrencyPO currency = new CurrencyPO(cc.getCode().toUpperCase(), cc.getName(), cc.getDecimalDigits());
    em.persist(currency);
  }
  
}
