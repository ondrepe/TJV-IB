package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CustomerGetLoggedCommand extends CommonCommand {

  public CustomerGetLoggedCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  public CustomerPO getLoggedCustomerPO() {
    CustomerPO customerPO = null;
    Boolean isCustomer = null;
    isCustomer = ctx.isCallerInRole("CUSTOMER");

    if (isCustomer != null && isCustomer) {
      String login = ctx.getCallerPrincipal().getName();
      Query query = em.createNamedQuery("AutentizationPO.getCustomerByLogin");
      query.setParameter("login", login);
      try {
        customerPO = (CustomerPO) query.getSingleResult();
      } catch (NoResultException ex) {
        customerPO = null;
      }
    }

    return customerPO;
  }
  
  public Customer getLoggedCustomer() {
    Customer cust = null;
    CustomerPO cPo = getLoggedCustomerPO();
    if (cPo != null) {
      cust = new Customer(cPo.getId(), cPo.getFirstName(), cPo.getLastName(), cPo.getEmail()); 
    }
    return cust;
  }
}
