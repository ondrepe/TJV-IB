package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonSetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AutentizationGroupPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AutentizationPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.EntityExistIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CustomerSetCommand extends CommonSetCommand<Customer> {

  public CustomerSetCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected void validate(Customer cstmr) throws CommonIBException {
    cstmr.setFirstName(cstmr.getFirstName().trim());
    cstmr.setLastName(cstmr.getLastName().trim());
    cstmr.setEmail(cstmr.getEmail().trim());
    
    //validace jestli uz existuje
    Query query = em.createNamedQuery("CustomerPO.findByAll");
    query.setParameter("firstName", cstmr.getFirstName());
    query.setParameter("lastName", cstmr.getLastName());
    query.setParameter("email", cstmr.getEmail());
    try {
      CustomerPO cRate = (CustomerPO) query.getSingleResult();
      if (cRate != null) {
        throw new EntityExistIBException("Customer");
      }
    } catch (PersistenceException ex) {}
  }

  @Override
  protected void execute(Customer cstmr) {
    CustomerPO cust;
    if (cstmr.getId() == null) {
      AutentizationGroupPO agPo = em.find(AutentizationGroupPO.class, "CUSTOMER");
      AutentizationPO aPo = new AutentizationPO();
      aPo.setLogin(createLogin(cstmr));
      aPo.setPassword("955db0b81ef1989b4a4dfeae8061a9a6");
      aPo.setGroup(agPo);
      
      cust = new CustomerPO();
      cust.setAutentization(aPo);
    } else {
      cust = em.find(CustomerPO.class, cstmr.getId());
    }
    cust.setFirstName(cstmr.getFirstName());
    cust.setLastName(cstmr.getLastName());
    cust.setEmail(cstmr.getEmail());
    cust.setValid("Y");
    em.persist(cust);
  }
  
  protected String createLogin(Customer cstmr) {
    StringBuilder builder = new StringBuilder();
    builder.append(cstmr.getLastName().substring(0, 5).toLowerCase());
    builder.append(cstmr.getFirstName().substring(0, 3).toLowerCase());
    return builder.toString();
  } 
}
