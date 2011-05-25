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
public abstract class GetCommand<PO extends CommonPO, TO extends CommonTO> extends CommonCommand {

  public GetCommand(EntityManager em) {
    super(em);
  }
  
  public GetCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public TO execute(int id) {
    TO to = null;
    if(authorize()) {
      PO po = get(id);
      to = convert(po);
    } else {
      throw new IBException(IBExceptionCode.AUTORIZATION_FAILED);
    }
    return to;
  }
  
  protected abstract PO get(int id);
  protected abstract TO convert(PO object);
}
