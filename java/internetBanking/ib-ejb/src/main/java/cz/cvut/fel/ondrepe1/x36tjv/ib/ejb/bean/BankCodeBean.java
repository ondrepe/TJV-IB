package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.BankCode;
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
public class BankCodeBean implements IBankCodeBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public boolean existBankCode(int i) {
    BankPO bank = em.find(BankPO.class, i);
    if(bank != null) {
      return true;
    }
    return false;
  }

  @Override
  public boolean existBankName(String string) {
    Query query = em.createNamedQuery("BankPO.findByName").setParameter("name", string);
    List<BankCode> banks = query.getResultList();
    if(banks != null && !banks.isEmpty()) {
      return true;
    }
    return false;
  }

  @Override
  public List<BankCode> getAll() {
    Query query = em.createNamedQuery("BankPO.findAll");
    return query.getResultList();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void delete(int i) {
    BankPO bank = em.find(BankPO.class, i);
    em.remove(bank);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void set(BankCode bc) {
    BankPO bank = new BankPO(bc.getCode(), bc.getName());
    em.persist(bank);
  }
  
}
