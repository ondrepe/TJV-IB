package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.po.CommonPO;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBException;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.exception.IBExceptionCode;
import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.CommonTO;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class ListByIdCommand<PO extends CommonPO, TO extends CommonTO> extends CommonCommand {

  public ListByIdCommand(EntityManager em) {
    super(em);
  }

  public ListByIdCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }

  public List<TO> execute(int id) {
    List<TO> convertedList = null;
    if(authorize()) {
      List<PO> list = list(id);
      convertedList = convert(list);
    } else {
      throw new IBException(IBExceptionCode.AUTORIZATION_FAILED);
    }
    return convertedList;
  }
  
  protected abstract List<PO> list(int id);
  protected abstract List<TO> convert(List<PO> list);
  
}
