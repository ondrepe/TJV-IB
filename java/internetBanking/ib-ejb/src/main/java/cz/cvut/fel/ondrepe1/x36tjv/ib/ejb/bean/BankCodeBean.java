package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.bank.BankSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.BankPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IBankCodeBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
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
  public List<BankCode> getAll() {
    Query query = em.createNamedQuery("BankPO.findAll");
    return query.getResultList();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void delete(int i) throws CommonIBException {
    BankPO bank = em.find(BankPO.class, i);
    em.remove(bank);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void set(BankCode bc) throws CommonIBException {
    CommonSetCommand command = new BankSetCommand(em);
    command.set(bc);
  }
  
}
