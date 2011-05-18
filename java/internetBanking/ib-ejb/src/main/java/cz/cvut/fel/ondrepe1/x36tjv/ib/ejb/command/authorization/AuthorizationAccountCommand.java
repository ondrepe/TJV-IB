package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.authorization;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer.CustomerGetLoggedCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.AccountPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class AuthorizationAccountCommand extends CommonCommand implements IAuthorization {

  public AuthorizationAccountCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  public boolean authorize(int id) {
    CustomerPO customer = getLoggedCustomer();
    if (customer != null) {
      for (AccountPO accPo : customer.getAccounts()) {
        if (accPo.getId() == id) {
          return true;
        }
      }
    }
    return false;
  }

  private CustomerPO getLoggedCustomer() {
    CustomerGetLoggedCommand command = new CustomerGetLoggedCommand(em, ctx);
    return command.getLoggedCustomerPO();
  }
}
