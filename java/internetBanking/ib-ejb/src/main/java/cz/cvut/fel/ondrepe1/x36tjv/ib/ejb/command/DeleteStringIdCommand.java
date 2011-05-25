package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class DeleteStringIdCommand extends CommonCommand {
  
  public DeleteStringIdCommand(EntityManager em) {
    super(em);
  }
  
  public DeleteStringIdCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public void execute(String id) {
    if(authorize()) {
      this.delete(id);
    } else {
      throw new IBException(IBExceptionCode.AUTORIZATION_FAILED);
    }
  }
  
  protected abstract void delete(String id);
}
