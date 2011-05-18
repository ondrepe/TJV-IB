package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.bean;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.ICustomerBean;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
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
@DeclareRoles({"MANAGER", "CUSTOMER"})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CustomerBean implements ICustomerBean {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  @RolesAllowed({"MANAGER"})
  public List<Customer> getList() {
    Query query = em.createNamedQuery("CustomerPO.findByValid").setParameter("valid", "Y");
    List<CustomerPO> list = query.getResultList();
    List<Customer> resultList = new ArrayList<Customer>();
    for(CustomerPO cstmrPO : list) {
      Customer cstmr = new Customer();
      cstmr.setId(cstmrPO.getId());
      cstmr.setFirstName(cstmrPO.getFirstName());
      cstmr.setLastName(cstmrPO.getLastName());
      cstmr.setEmail(cstmrPO.getEmail());
      resultList.add(cstmr);
    }
    return resultList;
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void set(Customer cstmr) throws CommonIBException {
    CommonSetCommand command = new CustomerSetCommand(em);
    command.set(cstmr);
  }

  @Override
  @RolesAllowed({"MANAGER"})
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void delete(int i) throws CommonIBException {
    CommonDeleteCommand command = new CustomerDeleteCommand(em);
    command.delete(new Integer(i));
  }
  
}
