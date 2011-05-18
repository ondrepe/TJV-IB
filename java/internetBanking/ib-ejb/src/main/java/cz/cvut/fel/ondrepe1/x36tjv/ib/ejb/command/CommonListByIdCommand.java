package cz.cvut.fel.ondrepe1.x36tjv.ib.ejb.command;

import cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to.AbstractIFaceObject;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

/**
 *
 * @author ondrepe
 */
public abstract class CommonListByIdCommand<T extends AbstractIFaceObject, PO> extends CommonCommand {

  public CommonListByIdCommand(EntityManager em) {
    super(em);
  }
  
  public CommonListByIdCommand(EntityManager em, SessionContext ctx) {
    super(em, ctx);
  }
  
  public ArrayList<T> listById(int id) {
    List listPO = execute(id);
    return convert(listPO);
  }
  
  protected abstract List<PO> execute(int id);
  protected abstract ArrayList<T> convert(List<PO> listPo);
  
}
