package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.ListCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.CustomerTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ondrepe
 */
public class CustomerListCommand extends ListCommand<CustomerPO, Customer> {

  public CustomerListCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected List<CustomerPO> list() {
    Query query = em.createNamedQuery("CustomerPO.findByValid").setParameter("valid", "Y");
    return query.getResultList();
  }

  @Override
  protected List<Customer> convert(List<CustomerPO> list) {
    CustomerTranslator translator = new CustomerTranslator();
    return translator.translateList(list);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
  
}
