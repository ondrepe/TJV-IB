package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class ExistCommand extends CommonCommand {

  public ExistCommand(EntityManager em) {
    super(em);
  }

  public ExistCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  public boolean execute(int id) {
    boolean result;
    if (authorize()) {
      result = this.exist(id);
    } else {
      throw new IBException(IBExceptionCode.AUTORIZATION_FAILED);
    }
    return result;
  }

  protected abstract boolean exist(int id);
}
