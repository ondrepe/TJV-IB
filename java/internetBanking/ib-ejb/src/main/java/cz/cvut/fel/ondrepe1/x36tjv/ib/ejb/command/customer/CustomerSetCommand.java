package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.SetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AutentizationGroupPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AutentizationPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CustomerSetCommand extends SetCommand<Customer> {

  public CustomerSetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void validate(Customer cstmr) {
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
        throw new IBException("Customer exist", IBExceptionCode.VALIDATION_FAILED);
      }
    } catch (PersistenceException ex) {}
  }

  @Override
  protected void set(Customer cstmr) {
    CustomerPO cust;
    if (cstmr.getId() == null) {
      AutentizationGroupPO agPo = em.find(AutentizationGroupPO.class, "CUSTOMER");
      AutentizationPO aPo = new AutentizationPO();
      aPo.setLogin(createLogin(cstmr));
      aPo.setPassword("955db0b81ef1989b4a4dfeae8061a9a6");
      aPo.setGroup(agPo);
      
      cust = new CustomerPO();
      cust.setAutentization(aPo);
      aPo.setCustomer(cust);
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

  @Override
  protected boolean authorize() {
    return isManager();
  }
}
