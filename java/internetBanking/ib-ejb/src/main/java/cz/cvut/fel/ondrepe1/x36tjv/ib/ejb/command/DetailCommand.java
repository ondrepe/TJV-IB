package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class DetailCommand<T extends CommonTO> extends CommonCommand {

  public DetailCommand(EntityManager em) {
    super(em);
  }
  
  public DetailCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public T execute(int id) {
    T to = null;
    if(authorize()) {
      to = detail(id);
    } else {
      throw new IBException(IBExceptionCode.AUTORIZATION_FAILED);
    }
    return to;
  }
  
  protected abstract T detail(int id);
}
