package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CommonDeleteCommand {
  
  protected EntityManager em;

  public CommonDeleteCommand(EntityManager em) {
    this.em = em;
  }
  
  public void delete(Object id) throws CommonIBException {
    try {
      execute(id);
    } catch(Throwable t) {
      throw new CommonIBException("Exception occured in " + this.getClass().getSimpleName() + ".", t);
    }
  }
  
  protected abstract void execute(Object id);
}
