package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CommonListCommand<T extends AbstractIFaceObject, PO> extends CommonCommand {

  public CommonListCommand(EntityManager em) {
    super(em);
  }
  
  public CommonListCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public List<T> list() {
    List listPO = execute();
    return convert(listPO);
  }
  
  protected abstract List<PO> execute();
  protected abstract List<T> convert(List<PO> listPo);
}
