package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.IAutentizationBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.ejb.Stateless;
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
public class AutentizationBean implements IAutentizationBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public Customer getCustomerByLogin(String string) {
    Query query = em.createNamedQuery("AutentizationPO.getCustomerByLogin");
    query.setParameter("login", string);
    CustomerPO cPo = (CustomerPO) query.getSingleResult();  
    Customer cust = new Customer(cPo.getId(), cPo.getFirstName(), cPo.getLastName(), cPo.getEmail());
    return cust;
  }
  
}
