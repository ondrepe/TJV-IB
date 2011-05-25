package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.DeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class CustomerDeleteCommand extends DeleteCommand {

  public CustomerDeleteCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  @Override
  protected void delete(int id) {
    CustomerPO cstmr = em.find(CustomerPO.class, id);
    cstmr.setValid("N");
    em.persist(cstmr);
  }

  @Override
  protected boolean authorize() {
    return isManager();
  }
  
}
