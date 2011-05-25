package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.GetCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.translator.impl.CustomerTranslator;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.Customer;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class CustomerGetCommand extends GetCommand<CustomerPO, Customer> {

  public CustomerGetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected CustomerPO get(int id) {
    CustomerPO cPo;
    if (isManager()) {
      cPo = em.find(CustomerPO.class, id);
    } else {
      cPo = getCustomer();
    }
    return cPo;
  }

  @Override
  protected Customer convert(CustomerPO object) {
    CustomerTranslator translator = new CustomerTranslator();
    return translator.translate(object);
  }

  @Override
  protected boolean authorize() {
    return isCustomer() || isManager();
  }
}
