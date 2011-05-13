package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.currency.CurrencySetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CurrencyPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICurrencyCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
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
  public List<CurrencyCode> getAll() {
    Query query = em.createNamedQuery("CurrencyPO.findAll");
    return query.getResultList();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void delete(String code) throws CommonIBException {
    CurrencyPO currency = em.find(CurrencyPO.class, code.toUpperCase());
    em.remove(currency);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void set(CurrencyCode cc) throws CommonIBException {
    CommonSetCommand command = new CurrencySetCommand(em);
    command.set(cc);
  }
  
}
