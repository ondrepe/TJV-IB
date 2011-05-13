package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.ejb.exception.CommonIBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CommonSetCommand<T extends AbstractIFaceObject> {
  
  protected EntityManager em;

  public CommonSetCommand(EntityManager em) {
    this.em = em;
  }
  
  public void set(T object) throws CommonIBException {
    validate(object);
    try {
      execute(object);
    } catch(Throwable t) {
      throw new CommonIBException("Exception occured in " + this.getClass().getSimpleName() + ".", t);
    }
  }
  
  protected abstract void validate(T object) throws CommonIBException;
  protected abstract void execute(T object);
}
