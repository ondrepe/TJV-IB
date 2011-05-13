package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.customer;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command.CommonDeleteCommand;
import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CustomerPO;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public class CustomerDeleteCommand extends CommonDeleteCommand {

  public CustomerDeleteCommand(EntityManager em) {
    super(em);
  }

  @Override
  protected void execute(Object id) {
    Integer intValue = (Integer) id;
    CustomerPO cstmr = em.find(CustomerPO.class, intValue);
    cstmr.setValid("N");
    em.persist(cstmr);
  }
  
}
