package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CommonPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class SetCommand<TO extends CommonTO> extends CommonCommand {

  public SetCommand(EntityManager em) {
    super(em);
  }
  
  public SetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public void execute(TO data) {
    if(authorize()) {
      validate(data);
      this.set(data);
    } else {
      throw new IBException(IBExceptionCode.AUTORIZATION_FAILED);
    }
  }
  
  protected abstract void set(TO object);
  protected abstract void validate(TO object);
}
